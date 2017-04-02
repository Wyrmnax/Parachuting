package com.example.blackdragon.tutorial;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by BlackDragon on 09/03/2017.
 */

public interface IScene {
    public void update();

    public void draw(Canvas canvas);

    public void terminate();

    public void receiveTouch(MotionEvent event);
}
