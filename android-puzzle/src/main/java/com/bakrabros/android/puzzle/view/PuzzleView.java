package com.bakrabros.android.puzzle.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bakrabros.android.puzzle.manager.PuzzleManager;
import com.bakrabros.android.puzzle.util.ObjectUtils;

/**
 * @author BakraBros
 */
public class PuzzleView extends View {

    private PuzzleManager puzzleManager;

    private Boolean initiated;

    {
        initiated = false;
    }

    public PuzzleView(Context context) {
        super(context);
    }

    public PuzzleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PuzzleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ObjectUtils.requireNonNull(canvas, "canvas is null");

        if (initiated) {
            super.onDraw(canvas);
            this.puzzleManager.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ObjectUtils.requireNonNull(motionEvent, "motionEvent is null");

        return initiated ? this.puzzleManager.onTouchEvent(this, motionEvent) : false;
    }

    public void setPuzzleManager(PuzzleManager puzzleManager) {
        ObjectUtils.requireNonNull(puzzleManager, "puzzleManager is null");

        this.puzzleManager = puzzleManager;
        this.initiated = true;
    }
}
