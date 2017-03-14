package com.example.blackdragon.tutorial;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by BlackDragon on 12/03/2017.
 */

public class ObstacleAirplane implements IObstacle {

    private Rect rectangle;
    private int color;
    private int startX;
    private int playerGap;
    Random rnd = new Random();
    private int moveDirection;
    private int moveSpeedDivider;

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

    public  ObstacleAirplane(int rectHeight, int color, int startX, int startY, int playerGap){
        this.color = Color.BLUE;
        this.startX = Constants.SCREEN_WIDHT;
        this.moveDirection = rnd.nextInt(2);
        this.moveSpeedDivider = rnd.nextInt(200) + 100;
        if (moveDirection == 0) {
            rectangle = new Rect(0, startY, 200, startY + rectHeight);
        }
        else{
            rectangle = new Rect(Constants.SCREEN_WIDHT-200, startY, Constants.SCREEN_WIDHT, startY + rectHeight);
        }

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

    @Override
    public void move() {
        if(moveDirection == 0)
            this.incrementX(Constants.SCREEN_WIDHT/moveSpeedDivider);
        else
            this.incrementX(-Constants.SCREEN_WIDHT/moveSpeedDivider);
    }

    @Override
    public boolean gameEndOnHit() {
        return true;

    }
}
