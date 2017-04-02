package com.example.blackdragon.tutorial;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by BlackDragon on 08/03/2017.
 */

public class RectPlayer implements IGameObject {

    private Rect rectangle;
    private int color;

    private Animation idle;
    private AnimationManager animManager;

    public Rect getRectangle(){
        return rectangle;
    }

    public RectPlayer(Rect rectangle, int color){
        this.rectangle = rectangle;
        this.color = color;

        BitmapFactory bf = new BitmapFactory();
        Bitmap idleImg1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.paraquedista1);
        Bitmap idleImg2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.paraquedista2);

        idle = new Animation (new Bitmap[]{idleImg1, idleImg2}, 0.5f);

        animManager = new AnimationManager(new Animation[]{idle});
    }

    @Override
    public void draw(Canvas canvas){
        animManager.draw(canvas, rectangle);
    }

    @Override
    public void update() {
            animManager.update();
    }

    public void update(Point point){
        float oldLeft = rectangle.left;

        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);

        int state = 0;

        animManager.playAnim(state);
        animManager.update();
    }
}
