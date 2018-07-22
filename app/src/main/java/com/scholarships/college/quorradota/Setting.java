package com.scholarships.college.quorradota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Setting extends AppCompatActivity {

    PreferenceHelper mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mPref = new PreferenceHelper(getApplicationContext());

        final EditText idBanner = findViewById(R.id.idbanner);
        final EditText idInterstial = findViewById(R.id.idinterstial);
        final EditText idVideo = findViewById(R.id.idVideo);
        final EditText noBanner = findViewById(R.id.nobanner);
        final EditText timeBanner_min = findViewById(R.id.timebanner_min);
        final EditText timeBanner_max = findViewById(R.id.timebanner_max);
        final EditText timeInterstial_min = findViewById(R.id.timeinter_min);
        final EditText timeInterstial_max = findViewById(R.id.timeinter_max);
        final EditText timeVideo_min = findViewById(R.id.timevideo_min);
        final EditText timeVideo_max = findViewById(R.id.timevideo_max);
        final EditText afterVideo = findViewById(R.id.aftervideo);
        final EditText keyword1 = findViewById(R.id.keyword1);
        final EditText keyword2 = findViewById(R.id.keyword2);
        final EditText keyword3 = findViewById(R.id.keyword3);

        Button buttonOK = findViewById(R.id.buttonOK);

        idBanner.setText(mPref.getID_banner());
        idInterstial.setText(mPref.getID_interstial());
        idVideo.setText(mPref.getID_video());
        noBanner.setText(String.valueOf(mPref.getNoBanner()));
        timeBanner_min.setText(String.valueOf(mPref.getTimeBanner()));
        timeBanner_max.setText(String.valueOf(mPref.getTimeBanner2()));
        timeInterstial_min.setText(String.valueOf(mPref.getTimeIntersial()));
        timeInterstial_max.setText(String.valueOf(mPref.getTimeIntersial2()));
        timeVideo_min.setText(String.valueOf(mPref.getTimeVideo()));
        timeVideo_max.setText(String.valueOf(mPref.getTimeVideo2()));
        afterVideo.setText(String.valueOf(mPref.getAfterVideo()));
        keyword1.setText(mPref.getKeyword1());
        keyword2.setText(mPref.getKeyword2());
        keyword3.setText(mPref.getKeyword3());




        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPref.setID_banner(idBanner.getText().toString());
                mPref.setID_interstial(idInterstial.getText().toString());
                mPref.setID_video(idVideo.getText().toString());
                mPref.setNoBanner(Integer.valueOf(noBanner.getText().toString()));
                mPref.setTimeBanner(Integer.valueOf(timeBanner_min.getText().toString()), Integer.valueOf(timeBanner_max.getText().toString()));
                mPref.setTimeInterstial(Integer.valueOf(timeInterstial_min.getText().toString()), Integer.valueOf(timeInterstial_max.getText().toString()));
                mPref.setTimeVideo(Integer.valueOf(timeVideo_min.getText().toString()), Integer.valueOf(timeVideo_max.getText().toString()));
                mPref.setAfterVideo(Integer.valueOf(afterVideo.getText().toString()));
                mPref.setKeyword1(keyword1.getText().toString());
                mPref.setKeyword2(keyword2.getText().toString());
                mPref.setKeyword3(keyword3.getText().toString());

                startActivity(new Intent(getBaseContext(), Main2Activity.class));
                finish();

            }
        });
    }
}
