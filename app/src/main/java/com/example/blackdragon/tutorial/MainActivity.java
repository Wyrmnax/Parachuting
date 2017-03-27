package com.example.blackdragon.tutorial;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        Constants.CLOUD_WIDTH = Constants.SCREEN_WIDTH /4;
        Constants.CLOUD_HEIGHT = Constants.SCREEN_HEIGHT /10;
		
		Constants.AIRPLANE_WIDTH = Constants.SCREEN_WIDTH /4;
        Constants.AIRPLANE_HEIGHT = Constants.SCREEN_HEIGHT /10;
		
		Constants.ROCKET_WIDTH = Constants.SCREEN_WIDTH /8;
        Constants.ROCKET_HEIGHT = Constants.SCREEN_HEIGHT /6;
		
		Constants.PARACHUTIST_HEIGHT = Constants.SCREEN_WIDTH /6;
        Constants.PARACHUTIST_WIDTH = Constants.SCREEN_HEIGHT /13;

        setContentView(new GamePanel(this));
    }
}
