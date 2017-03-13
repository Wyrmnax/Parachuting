package com.example.blackdragon.tutorial;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by BlackDragon on 12/03/2017.
 */

public interface IObstacle {
        public Rect getRectangle();
        public void incrementY(float y);
        public void incrementX(float y);
        public boolean playerCollide (RectPlayer player);
        public void draw(Canvas canvas);
        public void update();

}
