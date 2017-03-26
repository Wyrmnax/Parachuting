package com.example.blackdragon.tutorial;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by BlackDragon on 12/03/2017.
 */

public interface IObstacle {
        Rect getRectangle();
        void incrementY(float y);
        void incrementX(float y);
        boolean playerCollide (RectPlayer player);
        void draw(Canvas canvas);
        void update();
        void move();
        boolean gameEndOnHit();
        int getLeft();
}
