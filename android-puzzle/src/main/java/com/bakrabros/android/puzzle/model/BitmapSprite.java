package com.bakrabros.android.puzzle.model;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

import com.bakrabros.android.puzzle.constant.SpriteStatus;
import com.bakrabros.android.puzzle.util.AssetUtils;
import com.bakrabros.android.puzzle.util.BitmapUtils;

/**
 * @author BakraBros
 */
public class BitmapSprite {

    /**
     * Android resource id
     */
    private int resourceId;

    /**
     * The image to draw
     */
    private Bitmap bitmap;

    /**
     * The image with shadows to draw
     */
    private Bitmap alternativeBitmap;

    /**
     * Draw alternative bitmap. default false
     */
    private Boolean drawAlternative;

    /**
     * x axis in pixels
     */
    protected float x;

    /**
     * y axis in pixels
     */
    protected float y;

    /**
     * It is isRendered by a renderer
     */
    private Boolean isRendered;

    /**
     * Is x and y the center of the sprite
     */
    private Boolean hasCenterCoordinates;

    /**
     * Rendering status
     */
    private String status;

    /**
     * How much is scaled
     */
    private float scaleFactor;

    {
        hasCenterCoordinates = false;
        isRendered = false;
        drawAlternative = false;
        scaleFactor = 1;
        status = SpriteStatus.VISIBLE;
    }

    public BitmapSprite(Context context, String pathName) {
        this(context, pathName, 0.0f, 0.0f);
    }

    public BitmapSprite(Context context, String pathName, float x, float y) {
        this.x = x;
        this.y = y;

        this.bitmap = AssetUtils.getBitmap(context, pathName);
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getBitmapWidth() {
        return bitmap.getWidth();
    }

    public int getBitmapHeight() {
        return bitmap.getHeight();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Boolean hasCenterCoordinates() {
        return hasCenterCoordinates;
    }

    public void setHasCenterCoordinates(Boolean hasCenterCoordinates) {
        this.hasCenterCoordinates = hasCenterCoordinates;
    }

    public Boolean isRendered() {
        return isRendered;
    }

    public void setRendered(Boolean rendered) {
        this.isRendered = rendered;
    }

    public float getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }

    public float getWidth() {
        return bitmap.getWidth() * scaleFactor;
    }

    public float getHeight() {
        return bitmap.getHeight() * scaleFactor;
    }

    public String getStatus() {
        return status;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bitmap getAlternativeBitmap() {
        return alternativeBitmap;
    }

    public void setAlternativeBitmap(Bitmap alternativeBitmap) {
        this.alternativeBitmap = alternativeBitmap;
    }

    public Boolean drawAlternative() {
        return drawAlternative;
    }

    public void setDrawAlternative(Boolean drawAlternative) {
        if (drawAlternative) {
            alternativeBitmap = BitmapUtils.addShadow(bitmap, bitmap.getHeight(), getBitmap().getWidth(), Color.BLACK, 3, 5, 5);
        } else {
            alternativeBitmap = null;
        }

        this.drawAlternative = drawAlternative;
    }
}
