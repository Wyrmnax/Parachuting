package com.example.blackdragon.tutorial;

import android.graphics.Canvas;

/**
 * Created by BlackDragon on 12/03/2017.
 */

public interface IObstacle {
        public void incrementY(float y);
        public void incrementX(float y);
        public boolean playerCollide (RectPlayer player);
        public void draw(Canvas canvas);
        public void update();

}
