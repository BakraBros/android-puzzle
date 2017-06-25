package com.bakrabros.android.puzzle.manager;


import android.graphics.Canvas;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.bakrabros.android.puzzle.channel.Channel;
import com.bakrabros.android.puzzle.constant.PuzzleEvents;
import com.bakrabros.android.puzzle.constant.SpriteStatus;
import com.bakrabros.android.puzzle.model.BitmapSprite;
import com.bakrabros.android.puzzle.model.Puzzle;
import com.bakrabros.android.puzzle.model.PuzzlePiece;
import com.bakrabros.android.puzzle.renderer.SpriteRenderer;
import com.bakrabros.android.puzzle.renderer.SpriteRendererImpl;
import com.bakrabros.android.puzzle.service.PieceDragService;
import com.bakrabros.android.puzzle.service.PieceDragServiceImpl;
import com.bakrabros.android.puzzle.util.ObjectUtils;
import com.bakrabros.android.puzzle.util.java8.Consumer;

import java.util.List;

/**
 * @author BakraBros
 */
public class ThreeSlotPuzzleManagerImpl implements PuzzleManager {

    /**
     * Distance to snap into solved position
     */
    private float snapDistance;

    /**
     * Responsible for rendering sprites
     */
    private final SpriteRenderer spriteRenderer;

    /**
     * Manage user touch interaction
     */
    private final PieceDragService pieceDragService;

    /**
     * Puzzle data
     */
    private final Puzzle puzzle;

    /**
     * Puzzle channel
     */
    private final Channel channel;

    private View view;

    public ThreeSlotPuzzleManagerImpl(final Channel channel, final Puzzle puzzle, float snapDistance) {
        ObjectUtils.requireNonNull(channel, "channel is null");
        ObjectUtils.requireNonNull(puzzle, "puzzle is null");
        ObjectUtils.requireNonNull(snapDistance, "snapDistance is null");

        this.spriteRenderer = new SpriteRendererImpl();
        this.pieceDragService = new PieceDragServiceImpl();
        this.channel = channel;
        this.puzzle = puzzle;
        this.snapDistance = snapDistance;

        channel.unsubscribe(PuzzleEvents.SHOW_NEXT_PUZZLE_PIECE);
        channel.subscribe(PuzzleEvents.SHOW_NEXT_PUZZLE_PIECE, new Consumer<Void>() {
            @Override
            public void accept(Void aVoid) {
                Boolean foundNextPiece = false;
                for (final PuzzlePiece puzzlePiece : puzzle.getPieces()) {
                    if (puzzlePiece.getStatus() != SpriteStatus.VISIBLE) {
                        puzzlePiece.setStatus(SpriteStatus.VISIBLE);
                        foundNextPiece = true;

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                channel.emit(PuzzleEvents.PLAY_SOUND, puzzlePiece.getSoundPath());
                            }
                        }, 1000);

                        break;
                    }
                }

                if (!foundNextPiece) {
                    channel.emit(PuzzleEvents.PUZZLE_COMPLETED, view);
                }
            }
        });
    }

    @Override
    public void draw(Canvas canvas) {
        ObjectUtils.requireNonNull(canvas, "canvas is null");

        for (BitmapSprite slot : puzzle.getSlots()) {
            spriteRenderer.draw(canvas, slot, slot.getScaleFactor());
        }

        for (PuzzlePiece piece : puzzle.getPieces()) {
            if (piece.getStatus() != SpriteStatus.HIDDEN) {
                spriteRenderer.draw(canvas, piece, piece.getScaleFactor());
            }
        }
    }

    @Override
    public Boolean onTouchEvent(View view, MotionEvent event) {
        ObjectUtils.requireNonNull(view, "view is null");
        ObjectUtils.requireNonNull(event, "event is null");

        this.view = view;

        final float touchX = event.getX();
        final float touchY = event.getY();

        final List<PuzzlePiece> pieces = puzzle.getPieces();

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = pieces.size() - 1; i >= 0; i--) {
                    final PuzzlePiece piece = pieces.get(i);
                    if (pieceDragService.isTouched(touchX, touchY, piece) && piece.isDraggable() && piece.getStatus() == SpriteStatus.VISIBLE) {
                        channel.emit(PuzzleEvents.START_PARTICLE_EFFECT, event);
                        final PuzzlePiece draggable = pieces.remove(i);
                        draggable.setDrawAlternative(true);
                        pieceDragService.setDraggable(draggable);
                        pieces.add(draggable);

                        return true;
                    }
                }

                return false;

            case MotionEvent.ACTION_UP:
                channel.emit(PuzzleEvents.STOP_PARTICLE_EFFECT, event);

                pieceDragService.getDraggable().setDrawAlternative(false);

                if (pieceDragService.isSolved(snapDistance)) {
                    pieces.remove(pieces.size() - 1);
                    pieces.add(0, pieceDragService.getDraggable());
                    pieceDragService.getDraggable().setDraggable(false);
                    view.invalidate();

                    channel.emit(PuzzleEvents.PUZZLE_PIECE_SNAPPED, pieceDragService.getDraggable());

                    channel.emit(PuzzleEvents.SHOW_NEXT_PUZZLE_PIECE);

                    pieceDragService.setDraggable(null);
                    return true;
                }

                return false;

            case MotionEvent.ACTION_CANCEL:
                pieceDragService.setDraggable(null);

                return true;

            case MotionEvent.ACTION_MOVE:
                pieceDragService.moveDraggable(touchX, touchY);
                view.invalidate();

                channel.emit(PuzzleEvents.MOVE_PARTICLE_EFFECT, event);

                return true;
        }

        return false;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }
}
