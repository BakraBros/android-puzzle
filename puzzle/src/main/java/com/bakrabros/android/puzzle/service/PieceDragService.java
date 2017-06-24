package com.bakrabros.android.puzzle.service;


import com.bakrabros.android.puzzle.model.BitmapSprite;
import com.bakrabros.android.puzzle.model.PuzzlePiece;

/**
 * @author BakraBros
 */
public interface PieceDragService {

    Boolean isTouched(float touchX, float touchY, BitmapSprite sprite);

    void setDraggable(PuzzlePiece piece);

    PuzzlePiece getDraggable();

    void moveDraggable(float touchX, float touchY);

    boolean isSolved(float snapDistance);
}
