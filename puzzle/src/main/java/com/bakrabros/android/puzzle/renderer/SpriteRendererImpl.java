package com.bakrabros.android.puzzle.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.bakrabros.android.puzzle.model.BitmapSprite;
import com.bakrabros.android.puzzle.util.ObjectUtils;

/**
 * @author BakraBros
 */
public class SpriteRendererImpl implements SpriteRenderer {

    @Override
    public void draw(Canvas canvas, BitmapSprite sprite) {
        ObjectUtils.requireNonNull(canvas, "canvas is null");
        ObjectUtils.requireNonNull(sprite, "sprite is null");

        draw(canvas, sprite, 1, 0, 0);
    }

    @Override
    public void draw(Canvas canvas, BitmapSprite sprite, float scaleFactor) {
        ObjectUtils.requireNonNull(canvas, "canvas is null");
        ObjectUtils.requireNonNull(sprite, "sprite is null");
        ObjectUtils.requireNonNull(scaleFactor, "scaleFactor is null");

        draw(canvas, sprite, scaleFactor, 0, 0);
    }

    /**
     * Draws on a canvas
     *
     * @param canvas      Canvas we are drawing on
     * @param sprite      The sprite to draw
     * @param scaleFactor Percentage we scale bitmap when we draw
     * @param marginX     Margin x value in pixels
     * @param marginY     Margin y value in pixels
     */
    @Override
    public void draw(Canvas canvas, BitmapSprite sprite, float scaleFactor, int marginX, int marginY) {
        ObjectUtils.requireNonNull(canvas, "canvas is null");
        ObjectUtils.requireNonNull(sprite, "sprite is null");
        ObjectUtils.requireNonNull(scaleFactor, "scaleFactor is null");
        ObjectUtils.requireNonNull(marginX, "marginX is null");
        ObjectUtils.requireNonNull(marginY, "marginY is null");

        canvas.save();

        canvas.translate(marginX + sprite.getX(), marginY + sprite.getY());

        canvas.scale(scaleFactor, scaleFactor);

        final Bitmap bitmap;
        if (!sprite.drawAlternative()) {
            bitmap = sprite.getBitmap();
        } else {
            bitmap = sprite.getAlternativeBitmap();
        }

        if (sprite.hasCenterCoordinates()) {
            canvas.translate(-bitmap.getWidth() / 2, -bitmap.getHeight() / 2);
        }

        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.restore();

        sprite.setRendered(true);
    }
}
