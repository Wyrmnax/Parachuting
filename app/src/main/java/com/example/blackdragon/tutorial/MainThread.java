package com.example.blackdragon.tutorial;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by BlackDragon on 08/03/2017.
 */

public class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    public double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public void setRunning(boolean running){
        this.running = running;
    }

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        long starttime;
        long timeMillis = 1000/MAX_FPS;
        long waitTime;
        int framecount = 0;
        int totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while (running){
            starttime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - starttime)/1000000;
            waitTime = targetTime - timeMillis;

            try{
                if (waitTime > 0)
                    this.sleep(waitTime);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            totalTime += System.nanoTime() - starttime;
            framecount ++;

            if(framecount == MAX_FPS){
                averageFPS = 1000/((totalTime/framecount)/1000000);
                framecount = 0;
                totalTime = 0;
                System.out.print(averageFPS);
                System.out.flush();
            }
        }

    }

}
