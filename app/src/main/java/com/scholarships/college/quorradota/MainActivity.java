package com.scholarships.college.quorradota;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mAd;
    private RewardedVideoAd mRewardedVideoAd;
    PreferenceHelper mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPref = new PreferenceHelper(getApplicationContext());

        MobileAds.initialize(getApplicationContext(),
                mPref.getID_video());

        // Get reference to singleton RewardedVideoAd object
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);



        final TextView timer = findViewById(R.id.app_title);

        Random rand = new Random();
        int randomNum;
        if(mPref.getInterstial()%mPref.getAfterVideo()==0 && mPref.getStatVideo() && mPref.getAfterVideo()==0) {
            randomNum = mPref.getTimeVideo() + rand.nextInt((mPref.getTimeVideo2() - mPref.getTimeVideo()) + 1);
        }else{
            randomNum = mPref.getTimeIntersial() + rand.nextInt((mPref.getTimeIntersial2() - mPref.getTimeIntersial()) + 1);
        }
        new CountDownTimer(randomNum * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done!");
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                finish();
            }
        }.start();



        // Create the next level button, which tries to show an interstitial when clicked.
        /*
        mNextLevelButton = ((Button) findViewById(R.id.next_level_button));
        mNextLevelButton.setEnabled(false);
        mNextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
            }
        });*/

        // Create the text view to show the level number.
        //mLevelTextView = (TextView) findViewById(R.id.level);

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        if(mPref.getInterstial()%mPref.getAfterVideo()==0 && mPref.getStatVideo() && mPref.getAfterVideo()==0){
            mPref.setStatVideo(false);
            Toast.makeText(MainActivity.this, "Load Video", Toast.LENGTH_SHORT).show();
            loadVideo();
        }else{
            mPref.setStatVideo(true);
            mInterstitialAd = newInterstitialAd();
            loadInterstitial();
        }

        //showInterstitial();
        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        //Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private InterstitialAd newInterstitialAd() {
        final InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(mPref.getID_interstial());
        
        //interstitialAd.setAdListener(new AdListener());

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                showInterstitial();
                mPref.setInterstial(mPref.getInterstial()+1);
            }



            @Override
            public void onAdFailedToLoad(int errorCode) {
                if(errorCode == ERROR_CODE_NETWORK_ERROR){
                    Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                }else
                if(errorCode == ERROR_CODE_NO_FILL){
                    Toast.makeText(MainActivity.this, "The ad request was successful, but no ad was returned due to lack of ad inventory.", Toast.LENGTH_SHORT).show();
                }else
                if(errorCode == ERROR_CODE_INVALID_REQUEST){
                    Toast.makeText(MainActivity.this, "Invalid ID Unit", Toast.LENGTH_SHORT).show();
                }else
                if(errorCode == ERROR_CODE_INTERNAL_ERROR){
                    Toast.makeText(MainActivity.this, "invalid response was received from the ad server.", Toast.LENGTH_SHORT).show();
                }
                mPref.setInterstialError(mPref.getInterstialError()+1);
                mPref.setFirstTime(true);
                finish();
            }


            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                //goToNextLevel();
                //finish();
            }

            @Override
            public void onAdLeftApplication(){
                mPref.setClickInterstial(mPref.getClickInterstial()+1);
                mPref.setFirstTime(true);
                finish();
            }


        });

        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            //startActivity(new Intent(getBaseContext(), Main2Activity.class));
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            //goToNextLevel();
        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        //mNextLevelButton.setEnabled(false);

        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template")
                .addKeyword(mPref.getKeyword1())
                .addKeyword(mPref.getKeyword2())
                .addKeyword(mPref.getKeyword3()).build();


        mInterstitialAd.loadAd(adRequest);
    }

    private void loadVideo(){

        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

                Toast.makeText(MainActivity.this, "Success Load", Toast.LENGTH_SHORT).show();
                mRewardedVideoAd.show();
            }

            @Override
            public void onRewardedVideoAdOpened() {
                finish();
            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                // Reward the user for watching the ad.
                Toast.makeText(getBaseContext(), "Ad triggered reward.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {
                finish();
            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int errorCode) {
                if(errorCode == ERROR_CODE_NETWORK_ERROR){
                    Toast.makeText(MainActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                }else
                if(errorCode == ERROR_CODE_NO_FILL){
                    Toast.makeText(MainActivity.this, "The ad request was successful, but no ad was returned due to lack of ad inventory.", Toast.LENGTH_SHORT).show();
                }else
                if(errorCode == ERROR_CODE_INVALID_REQUEST){
                    Toast.makeText(MainActivity.this, "Invalid ID Unit", Toast.LENGTH_SHORT).show();
                }else
                if(errorCode == ERROR_CODE_INTERNAL_ERROR){
                    Toast.makeText(MainActivity.this, "invalid response was received from the ad server.", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onRewardedVideoCompleted() {
                Toast.makeText(MainActivity.this, "Complete Video", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                finish();
            }

        });

        // Load a reward based video ad
        mRewardedVideoAd.loadAd(mPref.getID_video(), new AdRequest.Builder().build());


    }

    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }

}
