package com.example.blackdragon.tutorial;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by BlackDragon on 12/03/2017.
 */

public class ObstacleRocket implements IObstacle {

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

    public ObstacleRocket(int rectHeight, int color, int startX, int startY, int playerGap){
        this.color = Color.RED;
        //this.startX = Constants.SCREEN_WIDHT;

        rectangle = new Rect(startX, startY, startX+ 50, startY + 300);
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
        this.incrementY(-10);
    }

    @Override
    public boolean gameEndOnHit() {
        return true;
    }
}
