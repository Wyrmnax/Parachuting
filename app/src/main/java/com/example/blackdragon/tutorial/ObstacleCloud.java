package com.example.blackdragon.tutorial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by BlackDragon on 12/03/2017.
 */

public class ObstacleCloud implements IObstacle {

    private Rect rectangle;
    private int color;
    private int startX;
    private Point cloudPoint;

    private Animation idle;
    private AnimationManager animManager;

    public Rect getRectangle() {
        return rectangle;
    }

    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;
    }

    public void incrementX(float x) {
        rectangle.left += x;
        rectangle.right += x;
    }

    public ObstacleCloud(int rectHeight, int color, int startY, int playerGap) {
        this.color = Color.GREEN;
        this.startX = ((int) (Math.random() * 1000) * Constants.SCREEN_WIDTH) / 1000;
        rectangle = new Rect(startX, startY, startX + Constants.CLOUD_WIDTH, startY + Constants.CLOUD_HEIGHT);

        cloudPoint = new Point(startX + 100, startY + (rectHeight / 2));

        BitmapFactory bf = new BitmapFactory();
        Bitmap idleImg1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.nuvemgrande1);

        idle = new Animation(new Bitmap[]{idleImg1}, 2f);

        animManager = new AnimationManager(new Animation[]{idle});
    }

    public boolean playerCollide(RectPlayer player) {
        return Rect.intersects(rectangle, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        //Paint paint = new Paint();
        //paint.setColor(color);
        //canvas.drawRect(rectangle,paint );
        animManager.draw(canvas, rectangle);
    }

    @Override
    public void update() {
        animManager.playAnim(0);
        animManager.update();
    }

    public void update(Point point) {
        float oldLeft = rectangle.left;

        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);

        int state = 0;

        animManager.playAnim(state);
        animManager.update();
    }

    @Override
    public void move() {
    }

    @Override
    public boolean gameEndOnHit() {
        return false;
    }

    @Override
    public int getLeft() {
        return 0;
    }
}
