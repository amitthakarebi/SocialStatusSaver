package com.amitthakare.socialstatussaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amitthakare.socialstatussaver.model.Constants;
import com.bumptech.glide.Glide;
import com.amitthakare.socialstatussaver.utils.LayManager;

public class SplashActivity extends AppCompatActivity {
    ImageView spicon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handleNotificationData();
        SharedPref.makeSharedPreferences(this);

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

    private void handleNotificationData()
    {
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null)
        {
            if (bundle.containsKey("playstore"))
            {
                //here we just attaching the values and then we handle it in main activity
                //this will be handle if user has closed our app
                Constants.datakey = "playstore";
                Constants.datavalue = bundle.getString("playstore");
                Constants.isClicked="Yes";

                Log.e("Handle Data","Playstore : "+bundle.getString("playstore"));
            }else if (bundle.containsKey("admob"))
            {
                //here we just attaching the values and then we handle it in main activity
                //this will be handle if user has closed our app
                Constants.datakey = "admob";
                Constants.datavalue = bundle.getString("admob");
                Constants.isClicked="Yes";

                Log.e("Handle Data","Admob : "+bundle.getString("admob"));
            }


        }
    }
}
