package com.example.mobilityindia.constant;

import android.content.Context;

public class CommonClass
{
    public static String CURRENTPIN ="";
    public static String NEWPIN ="";
    public static String REENTERPIN ="";

    public static String USERiD ="";
    public static String ERROR_MSG ="";
    public static String APP_TOKEN ="";
    public static String benfeciary_ID = "";
    public static String MONTH_DETAIL = "";
    public static String tempid = "";
    public static String shgnameee ="";
    public static String parent ="";
    public static String DateOfRegistration ="";
    public static String startdateofshgg ="";
    public static String dojshg ="";
    public static String age ="";
    public static String gender ="";
    public static String caste ="";
    public static String reigion ="";
    public static String adhaarno ="";

    public static String ecconomicstatus ="";
    public static String annualincome ="";
    public static String meritalstatus ="";
    public static String edu ="";
    public static String occuption ="";
    public static String occuption1 ="";

    public static String typeofdisability ="";
    public static String typeofsubdisability ="";
    public static String percentofdisability ="";
    public static String idcardno ="";
    public static String phpammount ="";
    public static String typeofbenificary ="";
    public static String purposeofvisit ="";


    public static String villageID ="";
    public static String address ="";
    public static String schoolname ="";
    public static String contactNo1 ="";
    public static String contactNo2 ="";
    public static String email ="";


    public static String rationcard ="";
    public static String sanitation ="";
    public static String appliance ="";
    public static String surgery ="";
    public static String govtfacility ="";
    public static String govtfacility_mention ="";


    public static String familymemberadultm ="";
    public static String familymemberadultf = "";
    public static String fmlymemberchildrnm = "";
    public static String fmlymembrchilf = "";
    public static String childrnundergeducationm = "";
    public static String childrenundergoeeseducationf = "";

    public static String drpuotlessthen14m = "";
    public static String dropoutlessthen14f = "";
    public static String dropoutm = "";
    public static String dropoutf = "";
    public static String earingmemberflym = "";
    public static String earingmemberflyf = "";

    public static String weathershgornot = "";


    public static String shgname = "";
    public static String startdateofshg = "";
    public static String edoshg = "";
    public static String bankname = "";
    public static String purposevisitdetails = "";
    public static String havebankacc = "";
    public static String accountHolderName = "";
    public static String accountType = "";
    public static String ifscCode = "";
    public static String shgbankno = "";
    public static String nameofpwdcwd = "";
    public static String datestring = "";
    public static String healthId = "";

    public static String service_done = "";
    public static String screeningdate = "";
    public static String assessmentdate = "";
    public static String assessmentwho = "";
    public static String assessmentwhere = "";
    public static String referral1 = "";
    public static String referralplace = "";
    public static String referralprescription = "";
    public static String trialwhat = "";
    public static String trialdate1 = "";
    public static String socialsecurity1 = "";
    public static String socialsecuritywhen = "";
    public static String gaitfrequency1 = "";
    public static String gaithowmany = "";
    public static String therapyfrequency = "";
    public static String therapysessions = "";
    public static String fitmentwho1 = "";
    public static String fitmentwhere1 = "";
    public static String fitmentdevice = "";
    public static String surgery1 = "";
    public static String surgerywhere1 = "";
    public static String surgerywherewhat = "";
    public static String homerecommend = "";
    public static String homerecommendwhat = "";


    public static String getToken(Context context) {
        return "" + context.getSharedPreferences("userData", Context.MODE_PRIVATE).getString("token", "");
    }

    public static String getStateMaster(Context context) {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("statemaster", "");
    }

    public static String getDistMaster(Context context) {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("disticmaster", "");
    }

    public static String getBlockMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("blockmaster","");
    }

    public static String getGPMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("gpmaster","");
    }

    public static String getVillageMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("villagemaster","");
    }
    public static String getHoboliMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("hobolimaster","");
    }
    public static String getCasteMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("castemaster","");
    }

    public static String getReligionMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("religionmaster","");
    }
    //====

    public static String getEconomicMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("economicmaster","");
    }

    public static String getAnnualIncomeMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("annualincomemaster","");
    }

    public static String getMaritialStatusMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("maritialStatusmaster","");
    }

    public static String getEducationMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("educationmaster","");
    }

    public static String getOccupationMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("occupationmaster","");
    }

    public static String getTypeDisMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("typedismaster","");
    }

    public static String getSubDisabilityMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("subdisabilemaster","");
    }

    public static String getPurposeVisitMaster(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("purposevisitmaster","");
    }

    public static String getSocalTranning(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("socaltranning","");
    }

    public static String getHealthActivity(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("healthactivity","");
    }

    public static String getMasterOccuption(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("masteroccuption","");
    }

    public static String getHealthDevices(Context context)
    {
        return context.getSharedPreferences("masterData", Context.MODE_PRIVATE).getString("masterdevices","");
    }



}
