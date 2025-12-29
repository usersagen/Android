package com.example.hsj135.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.hsj135.R;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
                boolean isFirst = sharedPreferences.getBoolean("isFirst", true);
                if (isFirst) {
                    Intent intent = new Intent(LauncherActivity.this, IntroActivity.class);
                    startActivity(intent);
                    sharedPreferences.edit().putBoolean("isFirst", false).apply();
                } else {
                    Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        },1000);
        Glide.with(this).load("https://wimg.588ku.com/gif320/24/07/09/5d6394b3084c462dac2f54c9952dee6b.gif").preload();
    }
}