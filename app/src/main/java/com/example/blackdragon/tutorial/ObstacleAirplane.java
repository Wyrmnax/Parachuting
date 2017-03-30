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
 * Created by BlackDragon on 12/03/2017.
 */

public class ObstacleAirplane implements IObstacle {

    private Rect rectangle;
    private int startX;
    Random rnd = new Random();
    private int moveDirection;
    private int moveSpeedDivider;

    private Animation walkRight;
    private Animation walkLeft;
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

    public  ObstacleAirplane(int rectHeight, int color, int startY, int playerGap){
        this.moveDirection = rnd.nextInt(2);
        this.moveSpeedDivider = rnd.nextInt(200) + 100;
        if (moveDirection == 0) {
            this.startX = - rnd.nextInt(400);
        }
        else{
            this.startX = rnd.nextInt(400)+Constants.SCREEN_WIDTH;
        }
        rectangle = new Rect(startX, startY, startX + Constants.AIRPLANE_WIDTH, startY + Constants.AIRPLANE_HEIGHT);
		
		BitmapFactory bf = new BitmapFactory();
        Bitmap Img1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aviao01);
        Bitmap Img2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.aviao02);

        walkRight = new Animation (new Bitmap[]{Img1, Img2}, 0.5f);
		
		Matrix m = new Matrix();
        m.preScale(-1, 1);
        Img1 = Bitmap.createBitmap(Img1, 0, 0, Img1.getWidth(), Img1.getHeight(), m, false);
        Img2 = Bitmap.createBitmap(Img2, 0, 0, Img2.getWidth(), Img2.getHeight(), m, false);

        walkLeft = new Animation(new Bitmap[]{Img1, Img2}, 0.5f);

        animManager = new AnimationManager(new Animation[]{walkLeft,walkRight});

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
		animManager.playAnim(moveDirection);
        animManager.update();
    }

    @Override
    public void move() {
        if(moveDirection == 0)
            this.incrementX(Constants.SCREEN_WIDTH /moveSpeedDivider);
        else
            this.incrementX(-Constants.SCREEN_WIDTH /moveSpeedDivider);
    }

    @Override
    public boolean gameEndOnHit() {
        return true;

    }

    @Override
    public int getLeft() {
        return 0;
    }
}
