package com.example.blackdragon.tutorial;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by BlackDragon on 09/03/2017.
 */

public class SceneManager {
    private ArrayList<IScene> IScenes = new ArrayList<>();
    public static int ACTIVE_SCENE;

    public SceneManager() {
        ACTIVE_SCENE = 0;
        IScenes.add(new GameplayIScene());
    }

    public void receiveTouch(MotionEvent event) {
        IScenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update() {
        IScenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas) {
        IScenes.get(ACTIVE_SCENE).draw(canvas);
    }

}
