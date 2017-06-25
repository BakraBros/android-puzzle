package com.bakrabros.android.puzzle.model;

import android.content.Context;

/**
 * @author BakraBros
 */
public class PuzzlePiece extends BitmapSprite {

    /**
     * Puzzle Solve x
     */
    private final float solveX;

    /**
     * Puzzle Solve y
     */
    private final float solveY;

    /**
     * The piece fallback x
     */
    private float fallbackX;

    /**
     * The piece fallback y
     */
    private float fallbackY;

    /**
     * Puzzle piece name
     */
    private String name;

    /**
     * Can be dragged
     */
    private Boolean draggable;

    /**
     * Path of sound
     */
    private String soundPath;

    /**
     * Fist index of Puzzle piece in game
     */
    private Integer index;

    {
        draggable = true;
    }

    public PuzzlePiece(Context context, String pathName, String name, float solveX, float solveY) {
        this(context, pathName, name, solveX, solveY, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    public PuzzlePiece(Context context, String pathName, String name, float solveX, float solveY, float fallbackX, float fallbackY) {
        this(context, pathName, name, solveX, solveY, fallbackX, fallbackY, 0.0f, 0.0f);

    }

    public PuzzlePiece(Context context, String pathName, String name, float solveX, float solveY, float fallbackX, float fallbackY, float x, float y) {
        super(context, pathName, x, y);

        this.name = name;
        this.solveX = solveX;
        this.solveY = solveY;
        this.fallbackX = fallbackX;
        this.fallbackY = fallbackY;
    }

    public float getSolveX() {
        return solveX;
    }

    public float getSolveY() {
        return solveY;
    }

    public Boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(Boolean draggable) {
        this.draggable = draggable;
    }

    public float getFallbackX() {
        return fallbackX;
    }

    public void setFallbackX(float fallbackX) {
        this.fallbackX = fallbackX;
    }

    public float getFallbackY() {
        return fallbackY;
    }

    public void setFallbackY(float fallbackY) {
        this.fallbackY = fallbackY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDraggable() {
        return draggable;
    }

    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
