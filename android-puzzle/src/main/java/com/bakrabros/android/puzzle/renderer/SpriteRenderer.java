package com.bakrabros.android.puzzle.renderer;


import android.graphics.Canvas;

import com.bakrabros.android.puzzle.model.BitmapSprite;

/**
 * @author BakraBros
 */
public interface SpriteRenderer {

    void draw(Canvas canvas, BitmapSprite sprite);

    void draw(Canvas canvas, BitmapSprite sprite, float scaleFactor);

    void draw(Canvas canvas, BitmapSprite sprite, float scaleFactor, int marginX, int marginY);
}
