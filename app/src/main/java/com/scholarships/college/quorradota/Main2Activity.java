package com.scholarships.college.quorradota;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.VpnService;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    // Remove the below line after defining your own ad unit ID.
    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    
    AdView adView1;
    AdView adView2;
    AdView adView3;
    AdView adView4;
    AdView adView5;
    AdView adView6;
    AdView adView7;
    AdView adView8;
    AdView adView9;
    AdView adView10;
    public boolean statFail = true;
    boolean statConnection = true;
    CountDownTimer timerAdmob;
    Button start;
    TextView bLoad;
    TextView bError;
    TextView bClick;
    TextView iLoad;
    TextView iError;
    TextView cLoad;
    TextView iClick;
    Button reset;


    PreferenceHelper mPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mPref = new PreferenceHelper(getApplicationContext());

        final TextView timer = findViewById(R.id.timer);
        start = findViewById(R.id.start);

         bLoad = findViewById(R.id.bannerLoad);
         bError = findViewById(R.id.bannerError);
         bClick = findViewById(R.id.bannerClick);
         iLoad = findViewById(R.id.interstialLoad);
         iError = findViewById(R.id.interstialError);
         iClick = findViewById(R.id.interstialClick);
         cLoad = findViewById(R.id.currentLoad);
         reset = findViewById(R.id.reset);

        bLoad.setText("B Load : " + mPref.getBanner());
        bError.setText("B Error : " + mPref.getBannerError());
        bClick.setText("B Click : " + mPref.getClickBanner());
        iLoad.setText("I Load : " + mPref.getInterstial());
        iError.setText("I Error : " + mPref.getInterstialError());
        iClick.setText("I Click : " + mPref.getClickInterstial());
        cLoad.setText("Current Load : " + mPref.getCurrentLoad());

        if(mPref.getCurrentLoad() >= 200){
            finish();
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPref.setBanner(0);
                mPref.setBannerError(0);
                mPref.setClickBanner(0);
                mPref.setInterstial(0);
                mPref.setInterstialError(0);
                mPref.setClickInterstial(0);
                mPref.setCurrentLoad(0);
                bLoad.setText("B Load : " + mPref.getBanner());
                bError.setText("B Error : " + mPref.getBannerError());
                bClick.setText("B Click : " + mPref.getClickBanner());
                iLoad.setText("I Load : " + mPref.getInterstial());
                iError.setText("I Error : " + mPref.getInterstialError());
                iClick.setText("I Click : " + mPref.getClickInterstial());
                cLoad.setText("Current Load : " + mPref.getCurrentLoad());
            }
        });





        // Load an ad into the AdMob banner view.
        final LinearLayout linearLayout = findViewById(R.id.admob);
        adView1 = new AdView(this);
        adView1.setAdSize(AdSize.BANNER);
        adView1.setAdUnitId(mPref.getID_banner());
        adView2= new AdView(this);
        adView2.setAdSize(AdSize.BANNER);
        adView2.setAdUnitId(mPref.getID_banner());
        adView3= new AdView(this);
        adView3.setAdSize(AdSize.BANNER);
        adView3.setAdUnitId(mPref.getID_banner());
        adView4= new AdView(this);
        adView4.setAdSize(AdSize.BANNER);
        adView4.setAdUnitId(mPref.getID_banner());
        adView5= new AdView(this);
        adView5.setAdSize(AdSize.BANNER);
        adView5.setAdUnitId(mPref.getID_banner());
        adView6= new AdView(this);
        adView6.setAdSize(AdSize.BANNER);
        adView6.setAdUnitId(mPref.getID_banner());
        adView7= new AdView(this);
        adView7.setAdSize(AdSize.BANNER);
        adView7.setAdUnitId(mPref.getID_banner());
        adView8= new AdView(this);
        adView8.setAdSize(AdSize.BANNER);
        adView8.setAdUnitId(mPref.getID_banner());
        adView9= new AdView(this);
        adView9.setAdSize(AdSize.BANNER);
        adView9.setAdUnitId(mPref.getID_banner());
        adView10= new AdView(this);
        adView10.setAdSize(AdSize.BANNER);
        adView10.setAdUnitId(mPref.getID_banner());

        linearLayout.addView(adView1);
        linearLayout.addView(adView2);
        linearLayout.addView(adView3);
        linearLayout.addView(adView4);
        linearLayout.addView(adView5);
        linearLayout.addView(adView6);
        linearLayout.addView(adView7);
        linearLayout.addView(adView8);
        linearLayout.addView(adView9);
        linearLayout.addView(adView10);

        Random rand = new Random();
        int randomNum = mPref.getTimeBanner() + rand.nextInt((mPref.getTimeBanner2() - mPref.getTimeBanner()) + 1);
        //Toast.makeText(this, "random " + randomNum, Toast.LENGTH_SHORT).show();
        timerAdmob = new CountDownTimer(randomNum * 1000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText(""+millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done!");
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
            }
        };


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(start.getText().equals("START")) {
                    mPref.setFirstTime(false);
                    timerAdmob.start();
                    start.setText("STOP");
                    if(mPref.getNoBanner()==1){
                        adViewWOW(adView1);
                    }else if(mPref.getNoBanner()==2){
                        adViewWOW(adView1);
                        adViewWOW(adView2);
                    }else if(mPref.getNoBanner()==3){
                        adViewWOW(adView1);
                        adViewWOW(adView2);
                        adViewWOW(adView3);
                    }else if(mPref.getNoBanner()==4){
                        adViewWOW(adView1);
                        adViewWOW(adView2);
                        adViewWOW(adView3);
                        adViewWOW(adView4);
                    }else if(mPref.getNoBanner()==5){
                        adViewWOW(adView1);
                        adViewWOW(adView2);
                        adViewWOW(adView3);
                        adViewWOW(adView4);
                        adViewWOW(adView5);
                    }else if(mPref.getNoBanner()==6){
                        adViewWOW(adView1);
                        adViewWOW(adView2);
                        adViewWOW(adView3);
                        adViewWOW(adView4);
                        adViewWOW(adView5);
                        adViewWOW(adView6);
                    }else if(mPref.getNoBanner()==7){
                        adViewWOW(adView1);
                        adViewWOW(adView2);
                        adViewWOW(adView3);
                        adViewWOW(adView4);
                        adViewWOW(adView5);
                        adViewWOW(adView6);
                        adViewWOW(adView7);
                    }else if(mPref.getNoBanner()==8){
                        adViewWOW(adView1);
                        adViewWOW(adView2);
                        adViewWOW(adView3);
                        adViewWOW(adView4);
                        adViewWOW(adView5);
                        adViewWOW(adView6);
                        adViewWOW(adView7);
                        adViewWOW(adView8);
                    }else  if(mPref.getNoBanner()==9){
                        adViewWOW(adView1);
                        adViewWOW(adView2);
                        adViewWOW(adView3);
                        adViewWOW(adView4);
                        adViewWOW(adView5);
                        adViewWOW(adView6);
                        adViewWOW(adView7);
                        adViewWOW(adView8);
                        adViewWOW(adView9);
                    }else  if(mPref.getNoBanner()==10){
                        adViewWOW(adView1);
                        adViewWOW(adView2);
                        adViewWOW(adView3);
                        adViewWOW(adView4);
                        adViewWOW(adView5);
                        adViewWOW(adView6);
                        adViewWOW(adView7);
                        adViewWOW(adView8);
                        adViewWOW(adView9);
                        adViewWOW(adView10);
                    }


                }else{
                    mPref.setFirstTime(true);
                    timerAdmob.cancel();
                    start.setText("START");
                }



            }
        });

        if(!mPref.getFirstTime()){
            start.performClick();
        }
        // Toasts the test ad message on the screen. Remove this after defining your own ad unit ID.
        //Toast.makeText(this, TOAST_TEXT, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(getBaseContext(), Setting.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void adViewWOW(AdView adView){
        if(statFail==true){
            if(checkVPN()){

                AdRequest adRequest = new AdRequest.Builder()
                        .setRequestAgent("android_studio:ad_template")
                        .addKeyword(mPref.getKeyword1())
                        .addKeyword(mPref.getKeyword2())
                        .addKeyword(mPref.getKeyword3()).build();
                adView.setAdListener(new AdListener(){
                    @Override
                    public void onAdLoaded() {
                        // Code to be executed when an ad finishes loading
                        mPref.setBanner(mPref.getBanner()+1);
                        mPref.setCurrentLoad(mPref.getCurrentLoad()+1);
                        bLoad.setText("B Load : " + mPref.getBanner());
                        bError.setText("B Error : " + mPref.getBannerError());
                        bClick.setText("B Click : " + mPref.getClickBanner());
                        iLoad.setText("I Load : " + mPref.getInterstial());
                        iError.setText("I Error : " + mPref.getInterstialError());
                        cLoad.setText("Current Load : " + mPref.getCurrentLoad());
                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // Code to be executed when an ad request fails.
                        statFail = false;
                        if(errorCode == ERROR_CODE_NETWORK_ERROR){
                            Toast.makeText(Main2Activity.this, "Network Error", Toast.LENGTH_SHORT).show();
                        }else
                        if(errorCode == ERROR_CODE_NO_FILL){
                            Toast.makeText(Main2Activity.this, "The ad request was successful, but no ad was returned due to lack of ad inventory.", Toast.LENGTH_SHORT).show();
                        }else
                        if(errorCode == ERROR_CODE_INVALID_REQUEST){
                            Toast.makeText(Main2Activity.this, "Invalid ID Unit", Toast.LENGTH_SHORT).show();
                        }else
                        if(errorCode == ERROR_CODE_INTERNAL_ERROR){
                            Toast.makeText(Main2Activity.this, "invalid response was received from the ad server.", Toast.LENGTH_SHORT).show();
                        }
                        mPref.setBannerError(mPref.getBannerError()+1);
                        timerAdmob.cancel();
                        bLoad.setText("B Load : " + mPref.getBanner());
                        bError.setText("B Error : " + mPref.getBannerError());
                        bClick.setText("B Click : " + mPref.getClickBanner());
                        iLoad.setText("I Load : " + mPref.getInterstial());
                        iError.setText("I Error : " + mPref.getInterstialError());
                        cLoad.setText("Current Load : " + mPref.getCurrentLoad());
                    }

                    @Override
                    public void onAdOpened() {
                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                        mPref.setClickBanner(mPref.getClickBanner()+1);
                        timerAdmob.cancel();
                        finish();
                    }

                    @Override
                    public void onAdLeftApplication() {
                        // Code to be executed when the user has left the app.
                    }

                    @Override
                    public void onAdClosed() {
                        // Code to be executed when when the user is about to return
                        // to the app after tapping on an ad.
                    }

                });

                adView.loadAd(adRequest);

            }else{
                Toast.makeText(Main2Activity.this, "VPN not Connect", Toast.LENGTH_SHORT).show();
                timerAdmob.cancel();
                finish();
            }
        }else{
            timerAdmob.cancel();
        }


    }

    private boolean checkVPN() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getNetworkInfo(ConnectivityManager.TYPE_VPN).isConnectedOrConnecting();
    }

}
