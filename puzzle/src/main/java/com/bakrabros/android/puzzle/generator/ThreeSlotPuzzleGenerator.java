package com.bakrabros.android.puzzle.generator;

import android.content.Context;

import com.bakrabros.android.puzzle.constant.SpriteStatus;
import com.bakrabros.android.puzzle.model.BitmapSprite;
import com.bakrabros.android.puzzle.model.Puzzle;
import com.bakrabros.android.puzzle.model.PuzzlePiece;
import com.bakrabros.android.puzzle.parser.PuzzleParser;
import com.bakrabros.android.puzzle.parser.model.PuzzleDto;
import com.bakrabros.android.puzzle.parser.model.PuzzlePieceDto;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author BakraBros
 */
public class ThreeSlotPuzzleGenerator {

    private final int canvasWidth;
    private final int canvasHeight;
    private final PuzzleDto puzzleDto;
    private final Context context;

    public ThreeSlotPuzzleGenerator(Context context, String dataPath, int canvasWidth, int canvasHeight) {
        this.context = context;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.puzzleDto = PuzzleParser.parse(context, dataPath);
    }

    public Puzzle generateNewPuzzle() {
        final Integer slotHeight = canvasHeight / 4;
        final Integer centerX = canvasWidth / 2;

        final List<PuzzlePieceDto> puzzlePieces = puzzleDto.getPieces();
        Collections.shuffle(puzzlePieces, new Random());

        final Puzzle puzzle = new Puzzle();

        int j = 0;
        for (PuzzlePieceDto puzzlePieceDto : puzzlePieces.subList(0, 3)) {
            final BitmapSprite bitmapSprite = new BitmapSprite(context, puzzlePieceDto.getSlotPath());

            bitmapSprite.setScaleFactor((slotHeight * 0.8f) / bitmapSprite.getHeight());

            final float pieceSolveX = (float) (Math.random() * (canvasWidth - bitmapSprite.getWidth()));
            final float pieceSolveY = j * slotHeight + (slotHeight * 0.1f);

            bitmapSprite.setX(pieceSolveX);
            bitmapSprite.setY(pieceSolveY);

            puzzle.addSlot(bitmapSprite);

            final PuzzlePiece piece = new PuzzlePiece(context, puzzlePieceDto.getPuzzlePiecePath(), puzzlePieceDto.getName(), pieceSolveX, pieceSolveY);

            piece.setScaleFactor((slotHeight * 0.8f) / piece.getHeight());
            piece.setX(centerX - piece.getWidth() / 2);
            piece.setY(3 * slotHeight + (slotHeight * 0.1f));
            piece.setFallbackX(centerX - piece.getWidth() / 2);
            piece.setFallbackY(3 * slotHeight + (slotHeight * 0.1f));
            piece.setStatus(SpriteStatus.HIDDEN);
            piece.setSoundPath(puzzlePieceDto.getSoundPath());
            piece.setIndex(j);

            puzzle.addPiece(piece);

            j++;
        }

        puzzle.shuffle();

        return puzzle;
    }
}
