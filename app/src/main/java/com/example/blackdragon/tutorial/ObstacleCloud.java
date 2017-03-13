package com.example.blackdragon.tutorial;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by BlackDragon on 12/03/2017.
 */

public class ObstacleCloud implements IObstacle {

    private Rect rectangle;
    private int color;
    private int startX;
    private int playerGap;

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

    public  ObstacleCloud(int rectHeight, int color, int startX, int startY, int playerGap){
        this.color = Color.GREEN;
        this.startX = ((int)(Math.random()*1000) * Constants.SCREEN_WIDHT)/1000;
        rectangle = new Rect(startX,startY, startX + 200, startY + rectHeight);
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
    }

    @Override
    public boolean gameEndOnonHit() {
        return false;
    }
}
