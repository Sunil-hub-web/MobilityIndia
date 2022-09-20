package com.example.mobilityindia.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private Utils()
    {
        // private constructor
    }
    public static String getToken(Context context)
    {
        return ""+context.getSharedPreferences("userData", Context.MODE_PRIVATE).getString("token","");
    }






    public static String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();

        // Get the Base64 string

        return Base64.encodeToString(byteFormat, Base64.NO_WRAP);

    }

    public static Bitmap getImgaeFrombase64string(String string)
    {
        byte[] decodedString = Base64.decode(string, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

    }



    public static String formatdate(String fdate,String inputFormatstr,String finalformat)
    {


        String datetime=null;
        DateFormat inputFormat = new SimpleDateFormat(inputFormatstr);
        DateFormat d= new SimpleDateFormat(finalformat);
        try {
            Date convertedDate = inputFormat.parse(fdate);
            datetime = d.format(convertedDate);

        }catch (ParseException e)
        {

        }
        return  datetime;
    }

    public static String customMonthYear(String dateStr){
        String formatedStr = "";


        String[] separated = dateStr.split("/");
        String monStr = separated[0];
        String yrStr = separated[1];

        if (monStr.equalsIgnoreCase("01")){
            formatedStr = "Jan "+yrStr;
        }
        else if (monStr.equalsIgnoreCase("02")){
            formatedStr = "Feb "+yrStr;
        }
       else  if (monStr.equalsIgnoreCase("03")){
            formatedStr = "Mar "+yrStr;
        }
        else  if (monStr.equalsIgnoreCase("04")){
            formatedStr = "Apr "+yrStr;
        }
        else  if (monStr.equalsIgnoreCase("05")){
            formatedStr = "May "+yrStr;
        }
        else  if (monStr.equalsIgnoreCase("06")){
            formatedStr = "Jun "+yrStr;
        }
        else  if (monStr.equalsIgnoreCase("07")){
            formatedStr = "Jul "+yrStr;
        }
        else  if (monStr.equalsIgnoreCase("08")){
            formatedStr = "Aug "+yrStr;
        }
        else  if (monStr.equalsIgnoreCase("09")){
            formatedStr = "Sep "+yrStr;
        }
        else  if (monStr.equalsIgnoreCase("10")){
            formatedStr = "Oct "+yrStr;
        }
        else  if (monStr.equalsIgnoreCase("11")){
            formatedStr = "Nov "+yrStr;
        }
        else  if (monStr.equalsIgnoreCase("12")){
            formatedStr = "Dec "+yrStr;
        }


        return formatedStr;
    }


    public static String  getRandomNumber() {
        int max  = 999;
        int min  = 100;
        String result = "";
        Random random =  new Random();
        result = String.valueOf(random.nextInt((max - min) + 1)+min);

        return result;
    }

    public static String cuurentDate(){
        //String dateTime ="";
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        System.out.println("Current time => " + formattedDate);
        return formattedDate;
    }

    public static String cuurentYear(){
        //String dateTime ="";
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        System.out.println("Current time => " + formattedDate);
        return formattedDate;
    }

    public static String cuurentMonth(){
        //String dateMonth ="";
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM", Locale.getDefault());
        String formattedDate = df.format(c);
        System.out.println("Current time => " + formattedDate);
        return formattedDate;
    }

    public static String cuurentMonthDetail( String no_month){
        String str = "";

        if(no_month.equalsIgnoreCase("01")){
            str = "Jan";
        }else if(no_month.equalsIgnoreCase("02")){
            str = "Feb";
        }
        else if(no_month.equalsIgnoreCase("03")){
            str = "March";
        }
        else if(no_month.equalsIgnoreCase("04")){
            str = "April";
        }
        else if(no_month.equalsIgnoreCase("05")){
            str = "May";
        }else if(no_month.equalsIgnoreCase("06")){
            str = "June";
        }else if(no_month.equalsIgnoreCase("07")){
            str = "July";
        }else if(no_month.equalsIgnoreCase("08")){
            str = "Aug";
        }else if(no_month.equalsIgnoreCase("09")){
            str = "Sept";
        }else if(no_month.equalsIgnoreCase("10")){
            str = "Oct";
        }else if(no_month.equalsIgnoreCase("11")){
            str = "Nov";
        }else if(no_month.equalsIgnoreCase("12")){
            str = "Dec";
        }




        return str;
    }





    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.getState() == NetworkInfo.State.CONNECTED;
        } else {
            return false;
        }
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

}

