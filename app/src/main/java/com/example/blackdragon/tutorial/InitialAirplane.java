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
            this.startX = (Constants.SCREEN_WIDTH/2) - 200;
            rectangle = new Rect(startX, startY, startX +400, startY + 200);

            BitmapFactory bf = new BitmapFactory();
            Bitmap Img1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aviaoparaquedas1);
            Bitmap Img2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aviaoparaquedas2);

            idle = new Animation (new Bitmap[]{Img1, Img2}, 0.5f);

            animManager = new AnimationManager(new Animation[]{idle});
        }

        public boolean playerCollide (RectPlayer player){
            return Rect.intersects(rectangle, player.getRectangle());
        }

        @Override
        public void draw(Canvas canvas) {
            animManager.draw(canvas, rectangle);
        }

        @Override
        public void update() {
            animManager.playAnim(0);
            animManager.update();
        }

        public int getLeft()
        {
            return rectangle.left;
        }

        @Override
        public void move() {
                this.incrementX(Constants.SCREEN_WIDTH /100);
        }

        @Override
        public boolean gameEndOnHit() {
            return false;
        }
    }

