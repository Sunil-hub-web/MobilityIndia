package com.example.mobilityindia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.mobilityindia.loginpage.view.MainActivity;

public class SessinoManager {

    private static final String PREF_NAME = "sharedcheckLogin";
    private static final String MONTH_STATUS = "monthstatus";
    private static final String IS_LOGIN = "login";
    private static final String IS_USERID = "userid";
    private static final String IS_ID = "id";
    private static final String IS_ID1 = "id1";
    private static final String IS_MONTHYEAR = "monthyerar";
    private static final String IS_benfeciary_ID = "benfeciary_ID";
    private static final String IS_DATETIME = "datetime";
    private static final String IS_TODAYdATE = "todaydate";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;


    public SessinoManager(Context context) {

        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }


    public String getDATETIME() {

        return sharedPreferences.getString(IS_DATETIME, "DEFAULT");
    }

    public void setDATETIME(String DATETIME) {

        editor.putString(IS_DATETIME, DATETIME);
        editor.commit();
    }

    public String getbenfeciaryID() {

        return sharedPreferences.getString(IS_benfeciary_ID, "DEFAULT");
    }

    public void setbenfeciaryID(String benfeciaryID) {

        editor.putString(IS_benfeciary_ID, benfeciaryID);
        editor.commit();
    }



    public String getID() {

        return sharedPreferences.getString(IS_ID, "DEFAULT");
    }

    public void setID(String id) {

        editor.putString(IS_ID, id);
        editor.commit();
    }

    public String getAID() {

        return sharedPreferences.getString(IS_ID1, "DEFAULT");
    }

    public void setAID(String id1) {

        editor.putString(IS_ID1, id1);
        editor.commit();
    }

    public String getTodayDate() {

        return sharedPreferences.getString(IS_TODAYdATE, "DEFAULT");
    }

    public void setTodayDate(String id) {

        editor.putString(IS_TODAYdATE, id);
        editor.commit();
    }


    public void setMonthYear(String monthyear){

        editor.putString(IS_MONTHYEAR,monthyear);
        editor.commit();
    }

    public String getmonthYear(){

        return sharedPreferences.getString(IS_MONTHYEAR,"DEFAULT");
    }

    public String getMONTHSTATUS(){

        return  sharedPreferences.getString(MONTH_STATUS,"DEFAULT");
    }

    public void setMONTHSTATUS(String MONTHSTATUS){

        editor.putString(MONTH_STATUS,MONTHSTATUS);
        editor.commit();

    }

    public String getUSERID(){

        return  sharedPreferences.getString(IS_USERID,"DEFAULT");
    }

    public void setUSERID(String USERID){

        editor.putString(IS_USERID,USERID);
        editor.commit();

    }

    public Boolean isLogin(){

        return sharedPreferences.getBoolean(IS_LOGIN, false);

    }
    public void setLogin(){

        editor.putBoolean(IS_LOGIN, true);
        editor.commit();

    }



    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}
