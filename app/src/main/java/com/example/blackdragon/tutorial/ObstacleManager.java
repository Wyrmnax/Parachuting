package com.example.blackdragon.tutorial;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by BlackDragon on 08/03/2017.
 */

public class ObstacleManager {

    //higher index = lower on scree = higher y value
    private ArrayList<IObstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;
    public boolean movingDown = false;

    private long startTime;
    private long initTime;

    Random rnd = new Random();

    private int score = 0;

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int color){
        obstacles = new ArrayList<>();
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;

        startTime = initTime = System.currentTimeMillis();

        populateObstacles();
    }

    public boolean playerCollide (RectPlayer player){
        for(IObstacle ob: obstacles) {
            if (ob.playerCollide(player)) {
                return ob.gameEndOnHit();
            }
        }
        return false;
    }

    //create initial obstacles
    private void populateObstacles(){
        int currY = 12*Constants.SCREEN_HEIGHT /4;
        while(currY > Constants.SCREEN_HEIGHT){
            int xStart = (int)(Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(new ObstacleCloud(obstacleHeight,color,currY,playerGap));
            currY -=  obstacleHeight + obstacleGap + rnd.nextInt(700);
        }
        obstacles.add(new InitialAirplane(obstacleHeight,color,(Constants.SCREEN_HEIGHT/4)-100,playerGap));
    }

    public void update(){
        if(startTime<Constants.INIT_TIME)
            startTime = Constants.INIT_TIME;
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = (float)(Math.sqrt(1 + (startTime - initTime)/2000.0))* Constants.SCREEN_HEIGHT/(10000.0f);
        float negSpeed = 0f - speed;

        for(IObstacle ob:obstacles){
            if (movingDown) {
                ob.incrementY(negSpeed * elapsedTime);
            }
            ob.move();
            if (ob.getClass() == InitialAirplane.class && (ob.getLeft()) + 100 > Constants.SCREEN_WIDTH /2)
            {
                movingDown = true;
            }
            ob.update();
        }
        if(obstacles.get(obstacles.size()-1).getRectangle().bottom <= 0){
            int yStart = (obstacles.get(0).getRectangle().top + obstacleHeight + obstacleGap)+ rnd.nextInt(700);
            obstacles.add(0, generateRandomObstacle(yStart));
            obstacles.remove(obstacles.size() -1);
            score ++;
        }


    }

    private IObstacle generateRandomObstacle(int yStart)
    {
        switch (rnd.nextInt(5)) {
            case 0:
                return new ObstacleCloud(obstacleHeight, color, yStart, playerGap);
            case 1:
                return new ObstacleRocket(obstacleHeight, color, yStart, playerGap);
            case 2:
                return new ObstacleAirplane(obstacleHeight, color, yStart, playerGap);
            case 3:
                return new ObstacleDuck(obstacleHeight, color, yStart, playerGap);
            case 4:
                return new ObstacleDuckFlight(obstacleHeight, color, yStart, playerGap);
            default:
                return new ObstacleRocket(obstacleHeight, color, yStart, playerGap);
        }
    }

    public void draw(Canvas canvas){
        for(IObstacle ob : obstacles)
            ob.draw(canvas);
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("" + score, 50, 50 + paint.descent() - paint.ascent(), paint);
    }
}
