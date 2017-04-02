package com.example.blackdragon.tutorial;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by BlackDragon on 08/03/2017.
 */

public class Obstacle implements IObstacle {

    private Rect rectangle;
    private Rect rectangle2;
    private int color;
    private int startX;
    private int playerGap;

    public Rect getRectangle() {
        return rectangle;
    }

    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;

        rectangle2.top += y;
        rectangle2.bottom += y;
    }

    @Override
    public void incrementX(float y) {

    }

    public Obstacle(int rectHeight, int color, int startX, int startY, int playerGap) {
        this.color = color;

        rectangle = new Rect(0, startY, startX, startY + rectHeight);
        rectangle2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
    }

    public boolean playerCollide(RectPlayer player) {
        return Rect.intersects(rectangle, player.getRectangle()) || Rect.intersects(rectangle2, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
        canvas.drawRect(rectangle2, paint);
    }

    @Override
    public void update() {

    }

    @Override
    public void move() {

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
