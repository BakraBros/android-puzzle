package com.bakrabros.android.puzzle.service;


import com.bakrabros.android.puzzle.model.BitmapSprite;
import com.bakrabros.android.puzzle.model.PuzzlePiece;

/**
 * @author BakraBros
 */
public class PieceDragServiceImpl implements PieceDragService {

    private PuzzlePiece draggable;

    @Override
    public Boolean isTouched(final float touchX, final float touchY, final BitmapSprite sprite) {
        final Boolean touchedX = touchX >= sprite.getX() && touchX <= sprite.getX() + sprite.getWidth();
        final Boolean touchedY = touchY >= sprite.getY() && touchY <= sprite.getY() + sprite.getHeight();

        return touchedX && touchedY && (sprite.getBitmap().getPixel((int) ((touchX - sprite.getX()) / sprite.getScaleFactor()), (int) ((touchY - sprite.getY()) / sprite.getScaleFactor())) & 0xff000000) != 0;
    }

    @Override
    public void setDraggable(PuzzlePiece piece) {
        this.draggable = piece;
    }

    @Override
    public PuzzlePiece getDraggable() {
        return this.draggable;
    }

    @Override
    public void moveDraggable(float touchX, float touchY) {
        if (draggable != null && draggable.hasCenterCoordinates()) {
            draggable.setX(touchX);
            draggable.setY(touchY);
        } else if (draggable != null && !draggable.hasCenterCoordinates()) {
            draggable.setX(touchX - draggable.getWidth() / 2);
            draggable.setY(touchY - draggable.getHeight() / 2);
        }
    }

    @Override
    public boolean isSolved(float snapDistance) {
        if (draggable != null &&
                Math.abs(draggable.getX() - draggable.getSolveX()) < snapDistance &&
                Math.abs(draggable.getY() - draggable.getSolveY()) < snapDistance) {
            draggable.setX(draggable.getSolveX());
            draggable.setY(draggable.getSolveY());
            return true;
        }

        return false;
    }
}
