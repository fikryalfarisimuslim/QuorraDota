package com.scholarships.college.quorradota;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceHelper {

    private SharedPreferences customCachedPrefs;
    private Context context;

    public PreferenceHelper(Context context) {
        super();
        this.context = context;
        customCachedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public Context getContex() {
        return context;
    }

    public SharedPreferences getCustomPref() {
        return customCachedPrefs;
    }

    public void setFirstTime(Boolean first) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putBoolean("first",first);
        mEditor.apply();
    }

    public Boolean getFirstTime() {
        return customCachedPrefs.getBoolean("first", true);
    }


    public void setBanner(int banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("banner",banner);
        mEditor.apply();

    }

    public int getBanner() {
        return customCachedPrefs.getInt("banner", 0);
    }

    public void setBannerError(int banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("banner_error",banner);
        mEditor.apply();

    }

    public int getBannerError() {
        return customCachedPrefs.getInt("banner_error", 0);
    }

    public void setInterstial(int interstial) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("interstial",interstial);
        mEditor.apply();
    }

    public int getInterstial() {
        return customCachedPrefs.getInt("interstial", 0);
    }

    public void setInterstialError(int interstial) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("interstial_error",interstial);
        mEditor.apply();
    }

    public int getInterstialError() {
        return customCachedPrefs.getInt("interstial_error", 0);
    }


    public void setClickBanner(int banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("click_banner",banner);
        mEditor.apply();

    }

    public int getClickBanner() {
        return customCachedPrefs.getInt("click_banner", 0);
    }

    public void setClickInterstial(int banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("click_interstial",banner);
        mEditor.apply();

    }

    public int getClickInterstial() {
        return customCachedPrefs.getInt("click_interstial", 0);
    }

    public void setCurrentLoad(int banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("current_load",banner);
        mEditor.apply();

    }

    public int getCurrentLoad() {
        return customCachedPrefs.getInt("current_load", 0);
    }


    public void setStatVideo(Boolean first) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putBoolean("stat_video",first);
        mEditor.apply();
    }

    public Boolean getStatVideo() {
        return customCachedPrefs.getBoolean("stat_video", true);
    }


    public void setNoBanner(int banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("no_banner",banner);
        mEditor.apply();

    }

    public int getNoBanner() {
        return customCachedPrefs.getInt("no_banner", 5);
    }

    public void setReachBanner(int banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("reach_banner",banner);
        mEditor.apply();
    }

    public int getReachBanner() {
        return customCachedPrefs.getInt("reach_banner", 200);
    }

    public void setTimeBanner(int banner, int banner2) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("time_banner_min",banner);
        mEditor.putInt("time_banner_max",banner2);
        mEditor.apply();
    }

    public int getTimeBanner() {
        return customCachedPrefs.getInt("time_banner_min", 30);
    }
    public int getTimeBanner2() {
        return customCachedPrefs.getInt("time_banner_max", 40);
    }

    public void setTimeInterstial(int banner, int banner2) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("time_interstial_min",banner);
        mEditor.putInt("time_interstial_max",banner2);
        mEditor.apply();
    }



    public int getTimeIntersial() {
        return customCachedPrefs.getInt("time_interstial_min", 30);
    }
    public int getTimeIntersial2() {
        return customCachedPrefs.getInt("time_interstial_max", 40);
    }

    public void setTimeVideo(int banner, int banner2) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("time_video_min",banner);
        mEditor.putInt("time_video_max",banner2);
        mEditor.apply();
    }

    public int getTimeVideo() {
        return customCachedPrefs.getInt("time_video_min", 40);
    }
    public int getTimeVideo2() {
        return customCachedPrefs.getInt("time_video_max", 60);
    }

    public void setAfterVideo(int banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putInt("after_video",banner);
        mEditor.apply();
    }

    public int getAfterVideo() {
        return customCachedPrefs.getInt("after_video", 3);
    }

    public void setID_banner(String banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putString("id_banner",banner);
        mEditor.apply();
    }

    public String getID_banner() {
        return customCachedPrefs.getString("id_banner", "ca-app-pub-3940256099942544/6300978111");
    }

    public void setID_interstial(String banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putString("id_interstial",banner);
        mEditor.apply();
    }

    public String getID_interstial() {
        return customCachedPrefs.getString("id_interstial", "ca-app-pub-3940256099942544/1033173712");
    }

    public void setID_video(String banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putString("id_video",banner);
        mEditor.apply();
    }

    public String getID_video() {
        return customCachedPrefs.getString("id_video", "ca-app-pub-3940256099942544/8691691433");
    }

    public void setKeyword1(String banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putString("keyword_1",banner);
        mEditor.apply();
    }

    public String getKeyword1() {
        return customCachedPrefs.getString("keyword_1", "houston maritime lawyers");
    }

    public void setKeyword2(String banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putString("keyword_2",banner);
        mEditor.apply();
    }

    public String getKeyword2() {
        return customCachedPrefs.getString("keyword_2", "maritime lawyers new orleans");
    }

    public void setKeyword3(String banner) {
        SharedPreferences.Editor mEditor = customCachedPrefs.edit();
        mEditor.putString("keyword_3",banner);
        mEditor.apply();
    }

    public String getKeyword3() {
        return customCachedPrefs.getString("keyword_3", "accident attorneys ga");
    }

}
