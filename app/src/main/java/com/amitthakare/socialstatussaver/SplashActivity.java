package com.amitthakare.socialstatussaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.amitthakare.socialstatussaver.utils.LayManager;

public class SplashActivity extends AppCompatActivity {
    ImageView spicon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        spicon= findViewById(R.id.spicon);
        ((TextView) findViewById(R.id.txt)).setTypeface(LayManager.getTypeface(SplashActivity.this));
        Glide.with(SplashActivity.this)
                .load(R.drawable.app_icon)
                .into(spicon);

        //For Not to have Status Bar
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        }, 2*1000);
    }
}
