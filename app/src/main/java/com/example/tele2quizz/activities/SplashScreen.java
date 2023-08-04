package com.example.tele2quizz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.tele2quizz.R;

import java.util.Calendar;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init(){
        SharedPreferences sPref1 = getSharedPreferences("saveToken", MODE_PRIVATE);
        String entryFirst = sPref1.getString("tokenSave", "");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (entryFirst.isEmpty()){
                    Intent intent = new Intent(SplashScreen.this, EntryActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashScreen.this, MainMenuActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_SCREEN);
    }
}