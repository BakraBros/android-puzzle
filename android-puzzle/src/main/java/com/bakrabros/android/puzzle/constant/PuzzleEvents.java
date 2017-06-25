package com.bakrabros.android.puzzle.constant;

import android.view.MotionEvent;
import android.view.View;

import com.bakrabros.android.puzzle.channel.BakraEvent;
import com.bakrabros.android.puzzle.model.PuzzlePiece;

/**
 * @author BakraBros
 */
public class PuzzleEvents {

    public static final BakraEvent<PuzzlePiece, Void> PUZZLE_PIECE_SNAPPED = new BakraEvent<>("PUZZLE_PIECE_SNAPPED");
    public static final BakraEvent<Void, Void> SHOW_NEXT_PUZZLE_PIECE = new BakraEvent<>("SHOW_NEXT_PUZZLE_PIECE");
    public static final BakraEvent<View, Void> PUZZLE_COMPLETED = new BakraEvent<>("PUZZLE_COMPLETED");

    public static final BakraEvent<MotionEvent, Void> START_PARTICLE_EFFECT = new BakraEvent<>("START_PARTICLE_EFFECT");
    public static final BakraEvent<MotionEvent, Void> MOVE_PARTICLE_EFFECT = new BakraEvent<>("MOVE_PARTICLE_EFFECT");
    public static final BakraEvent<MotionEvent, Void> STOP_PARTICLE_EFFECT = new BakraEvent<>("STOP_PARTICLE_EFFECT");

    public static final BakraEvent<String, Void> PLAY_SOUND = new BakraEvent<>("PLAY_SOUND");
}
