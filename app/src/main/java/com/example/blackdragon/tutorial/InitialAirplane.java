package com.example.blackdragon.tutorial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by BlackDragon on 19/03/2017.
 */

public class InitialAirplane implements IObstacle{

        private Rect rectangle;
        private int color;
        private int startX;
        Random rnd = new Random();

        private Animation idle;
        private Animation walkRight;
        private AnimationManager animManager;

        public Rect getRectangle() {
            return rectangle;
        }

        public void incrementY(float y){
            rectangle.top += y;
            rectangle.bottom +=y;
        }

        public void incrementX(float x){
            rectangle.left += x;
            rectangle.right +=x;
        }

        public  InitialAirplane(int rectHeight, int color, int startY, int playerGap){
            this.color = Color.BLUE;
            this.startX = 0;
            rectangle = new Rect(startX, startY, startX +200, startY + rectHeight);
        }

        public boolean playerCollide (RectPlayer player){
            return Rect.intersects(rectangle, player.getRectangle());
        }

        @Override
        public void draw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(color);
            canvas.drawRect(rectangle, paint);
        }

        @Override
        public void update() {
        }

        public int getLeft()
        {
            return rectangle.left;
        }

        @Override
        public void move() {
                this.incrementX(Constants.SCREEN_WIDHT/100);
        }

        @Override
        public boolean gameEndOnHit() {
            return false;
        }
    }

