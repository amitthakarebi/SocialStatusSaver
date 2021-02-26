package com.amitthakare.socialstatussaver.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.amitthakare.socialstatussaver.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


public class AdUtils {
    public static int adCounter = 1;

    public static boolean isloadFbAd = true;


    public static void initAd(Context context) {
        //MobileAds.initialize(context, context.getString(R.string.admob_app_id));

        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });
    }

    public static void loadBannerAd(Context context, LinearLayout adContainer) {
        AdView adView = new AdView(context);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(context.getString(R.string.admob_banner_id));
        adView.loadAd(adRequest);
        adContainer.addView(adView);
    }

    static InterstitialAd mInterstitialAd;

    public static void loadInterAd(Context context) {
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(context, context.getString(R.string.admob_interstitial), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                //super.onAdFailedToLoad(loadAdError);
                Log.i("Interstitial", loadAdError.getMessage());
                mInterstitialAd = null;
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // super.onAdLoaded(interstitialAd);
                mInterstitialAd = interstitialAd;
            }

        });

        //mInterstitialAd = new InterstitialAd(context);
        //mInterstitialAd.setAdUnitId(context.getString(R.string.admob_interstitial));
        //mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build());
    }

    public static void showInterAd(final Context context, final Intent intent) {
        if (adCounter == 6) {
            adCounter = 1;

            if (mInterstitialAd != null) {
                Activity activity = (Activity) context;
                mInterstitialAd.show(activity);
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.e("TAG", "The ad was dismissed.");
                        loadInterAd(context);
                        startActivity(context, intent);
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.e("TAG", "The ad failed to show.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.e("TAG", "The ad was shown.");
                    }
                });
            } else {
                startActivity(context, intent);
            }
        } else {
            startActivity(context, intent);
        }
    }

    static void startActivity(Context context, Intent intent) {
        if (intent != null) {
            context.startActivity(intent);
        }
    }


    public static void initFbAd(Context context) {
        //MobileAds.initialize(context, context.getString(R.string.admob_app_id));

        AudienceNetworkAds.initialize(context);
    }

    static com.facebook.ads.AdView adView;

    public static void fbBannerAd(Context context, LinearLayout adContainer) {
        adView = new com.facebook.ads.AdView(context, context.getString(R.string.fbad_banner_id), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        //1071833826661562_1071834936661451
        //1071833826661562_1071835906661354
        adContainer.addView(adView);
        adView.loadAd();
    }

    static com.facebook.ads.InterstitialAd fbInterstitialAd;

    public static void loadFbInterAd(Context context,Intent intent) {

        if (adCounter == 6)
        {
            adCounter=1;
            fbInterstitialAd = new com.facebook.ads.InterstitialAd(context, context.getString(R.string.fbad_interstitial_id));
            //1071833826661562_1071836779994600
            String TAG = "FbInterstitial";
            //fbInterstitialAd.loadAd();

            InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {
                    // Interstitial ad displayed callback
                    Log.e(TAG, "Interstitial ad displayed.");
                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    // Interstitial dismissed callback
                    Log.e(TAG, "Interstitial ad dismissed.");
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    // Ad error callback
                    Log.e(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    // Interstitial ad is loaded and ready to be displayed
                    Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                    // Show the ad
                    fbInterstitialAd.show();
                }

                @Override
                public void onAdClicked(Ad ad) {
                    // Ad clicked callback
                    Log.d(TAG, "Interstitial ad clicked!");
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                    // Ad impression logged callback
                    Log.d(TAG, "Interstitial ad impression logged!");
                }
            };

            // For auto play video ads, it's recommended to load the ad
            // at least 30 seconds before it is shown
            fbInterstitialAd.loadAd(
                    fbInterstitialAd.buildLoadAdConfig()
                            .withAdListener(interstitialAdListener)
                            .build());
        }else {
            startActivity(context, intent);
        }
    }

    public static void showFbInterAd(final Context context, final Intent intent) {
        if (adCounter == 6) {
            adCounter = 1;
            //fbInterstitialAd.show();
            Log.e("Hello", "Inside Show");


        } else {
            startActivity(context, intent);
        }
    }

    public static void destroyFbAd() {
        if (adView != null) {
            adView.destroy();
        }
        if (fbInterstitialAd != null) {
            fbInterstitialAd.destroy();
        }
    }
}
