package com.bakrabros.android.puzzle.manager;


import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.bakrabros.android.puzzle.channel.Channel;

/**
 * @author BakraBros
 */
public interface PuzzleManager {

    void draw(Canvas canvas);

    Boolean onTouchEvent(View view, MotionEvent event);

    Channel getChannel();
}
