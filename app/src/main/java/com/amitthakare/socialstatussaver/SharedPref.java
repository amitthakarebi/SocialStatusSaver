package com.amitthakare.socialstatussaver;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.amitthakare.socialstatussaver.utils.AdUtils;

public class SharedPref {

    public static void makeSharedPreferences(Context context) {

        SharedPreferences pref = context.getSharedPreferences("ads", 0); // 0 - for private mode
        if (!pref.contains("isLoadFbAd"))
        {
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("isLoadFbAd","true");
            editor.apply();
        }else
            Log.e("Pref","Already Data");

    }

    public static void checkAdType(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences("ads", 0); // 0 - for private mode
        if (pref.getString("isLoadFbAd",null).equals("true"))
        {
            AdUtils.isloadFbAd = true;
        }else
        {
            AdUtils.isloadFbAd = false;
        }
    }

}
