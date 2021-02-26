package com.amitthakare.socialstatussaver;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amitthakare.socialstatussaver.model.Constants;
import com.amitthakare.socialstatussaver.utils.AdUtils;
import com.amitthakare.socialstatussaver.utils.Helpers;
import com.amitthakare.socialstatussaver.utils.LayManager;

import static android.view.View.*;

public class HomeActivity extends AppCompatActivity implements OnClickListener {

    LinearLayout wsBtn;
    LinearLayout waBusiBtn;
    LinearLayout insBtn;
    LinearLayout tokBtn;
    LinearLayout likBtn;
    LinearLayout fbBtn;
    LinearLayout tweatBtn;
    LinearLayout galBtn;
    ImageView moreapp;
    ImageView policy;
    ImageView shareapp;
    ImageView rateapp;

    String[] permissionsList = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPref.checkAdType(this);

        init();

        handleNotificationData();

        LinearLayout adContainer = findViewById(R.id.banner_container);

        if (!AdUtils.isloadFbAd) {
            //admob
            AdUtils.initAd(HomeActivity.this);
            AdUtils.loadBannerAd(HomeActivity.this, adContainer);
            AdUtils.loadInterAd(HomeActivity.this);
            Log.e("Test","admob");
        } else {
            //Fb banner Ads
            AdUtils.initFbAd(HomeActivity.this);
            AdUtils.fbBannerAd(HomeActivity.this, adContainer);
            //AdUtils.loadFbInterAd(HomeActivity.this);
            Log.e("Test","fb");
        }


    }

    void init() {
        wsBtn = findViewById(R.id.wsBtn);
        wsBtn.setOnClickListener(this);
        waBusiBtn = findViewById(R.id.waBusiBtn);
        waBusiBtn.setOnClickListener(this);
        insBtn = findViewById(R.id.insBtn);
        insBtn.setOnClickListener(this);
        tokBtn = findViewById(R.id.tokBtn);
        tokBtn.setOnClickListener(this);
        fbBtn = findViewById(R.id.fbBtn);
        fbBtn.setOnClickListener(this);
        galBtn = findViewById(R.id.galBtn);
        galBtn.setOnClickListener(this);
        tweatBtn = findViewById(R.id.tweatBtn);
        tweatBtn.setOnClickListener(this);
        likBtn = findViewById(R.id.likBtn);
        likBtn.setOnClickListener(this);

        moreapp = findViewById(R.id.moreapp);
        moreapp.setOnClickListener(this);
        policy = findViewById(R.id.policy);
        policy.setOnClickListener(this);
        shareapp = findViewById(R.id.shareapp);
        shareapp.setOnClickListener(this);
        rateapp = findViewById(R.id.rateapp);
        rateapp.setOnClickListener(this);

        ((TextView) findViewById(R.id.txt)).setTypeface(LayManager.getTypeface(HomeActivity.this));
        ((TextView) findViewById(R.id.txt1)).setTypeface(LayManager.getTypeface(HomeActivity.this));
        ((TextView) findViewById(R.id.txt2)).setTypeface(LayManager.getTypeface(HomeActivity.this));
        ((TextView) findViewById(R.id.txt3)).setTypeface(LayManager.getTypeface(HomeActivity.this));
        ((TextView) findViewById(R.id.txt4)).setTypeface(LayManager.getTypeface(HomeActivity.this));
        ((TextView) findViewById(R.id.txt5)).setTypeface(LayManager.getTypeface(HomeActivity.this));
        ((TextView) findViewById(R.id.txt6)).setTypeface(LayManager.getTypeface(HomeActivity.this));
        ((TextView) findViewById(R.id.txt7)).setTypeface(LayManager.getTypeface(HomeActivity.this));
        ((TextView) findViewById(R.id.txt8)).setTypeface(LayManager.getTypeface(HomeActivity.this));

    }

    @Override
    protected void onDestroy() {
        AdUtils.destroyFbAd();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wsBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                } else {
                    startActivityes(new Intent(HomeActivity.this, WAppActivity.class));
                }
                break;

            case R.id.waBusiBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                } else {
                    startActivityes(new Intent(HomeActivity.this, WABusiActivity.class));
                }
                break;

            case R.id.insBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                } else {
                    startActivityes(new Intent(HomeActivity.this, InstaActivity.class));
                }
                break;

            case R.id.likBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                } else {
                    startActivityes(new Intent(HomeActivity.this, LikeeActivity.class));
                }
                break;

            case R.id.tokBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                } else {
                    startActivityes(new Intent(HomeActivity.this, TikActivity.class));
                }
                break;

            case R.id.fbBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                } else {
                    startActivityes(new Intent(HomeActivity.this, FBActivity.class));
                }
                break;

            case R.id.tweatBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                } else {
                    startActivityes(new Intent(HomeActivity.this, TwetActivity.class));
                }
                break;

            case R.id.galBtn:
                if (!checkPermissions(this, permissionsList)) {
                    ActivityCompat.requestPermissions(this, permissionsList, 21);
                } else {
                    startActivityes(new Intent(HomeActivity.this, MyGalleryActivity.class));
                }
                break;

            case R.id.rateapp:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                break;

            case R.id.shareapp:
                Helpers.mShareText("Download this awesome app\n https://play.google.com/store/apps/details?id=" + getPackageName() + " \n", HomeActivity.this);
                break;

            case R.id.policy:
                startActivityes(new Intent(HomeActivity.this, PrivacyActivity.class));
                break;

            case R.id.moreapp:
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/developer?id=Amit+Rajendra+Thakare&hl=en")));
                break;

                default:
                    break;
        }
    }

    void startActivityes(Intent intent) {
        if (!AdUtils.isloadFbAd) {
            AdUtils.adCounter++;
            AdUtils.showInterAd(HomeActivity.this, intent);
        } else {
            AdUtils.adCounter++;
            AdUtils.loadFbInterAd(HomeActivity.this,intent);
            //AdUtils.showFbInterAd(HomeActivity.this, intent);
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (this.doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000L);
    }


    public static boolean checkPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 21 && !checkPermissions(this, permissionsList)) {
                ActivityCompat.requestPermissions(this, permissionsList, 21);
        }
    }

    private void handleNotificationData() {

        if (Constants.datakey.equals("playstore") && Constants.isClicked.equals("Yes"))
        {
            final String appPackageName = Constants.datavalue;
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }else if (Constants.datakey.equals("admob") && Constants.isClicked.equals("Yes"))
        {
            final String activeOrNot = Constants.datavalue;
            Log.e("Handle Data", "Value : " + Constants.datavalue);
            if (activeOrNot.equals("active"))
            {
                //admob active means AdUtils.isloadFbAd is false, show admob ads
                AdUtils.isloadFbAd = false;
                SharedPreferences pref = getApplicationContext().getSharedPreferences("ads", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("isLoadFbAd","false");
                editor.apply();
            }else
            {
                AdUtils.isloadFbAd = true;
                SharedPreferences pref = getApplicationContext().getSharedPreferences("ads", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("isLoadFbAd","true");
                editor.apply();
            }
        }

        Constants.datavalue="No";
        Constants.datakey="No";
        Constants.isClicked="No";
    }

    // jevha main activity on asel tevha hai run hote
    //this will be handle when the ap is already open and user click on the notification.
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("New Intent", "New Intent Called");
        Constants.isClicked="Yes";
        handleNotificationData();
    }

}
