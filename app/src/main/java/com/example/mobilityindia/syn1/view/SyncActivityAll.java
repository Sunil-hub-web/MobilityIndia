package com.example.mobilityindia.syn1.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.attendance.database.AttendanceClass;
import com.example.mobilityindia.attendance.database.RoomDB;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivitySyncAllBinding;
import com.example.mobilityindia.landingpage.view.LandingPageActivity;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.syn1.view.allresponse.actionplan.Datum_ActionPlan;
import com.example.mobilityindia.syn1.view.allresponse.actionplan.Example_ActionPlan;
import com.example.mobilityindia.syn1.view.allresponse.activityreport.Datum_ActivityReport;
import com.example.mobilityindia.syn1.view.allresponse.activityreport.Example_ActivityReport;
import com.example.mobilityindia.syn1.view.allresponse.activityreport.ExapmleActivityReport;
import com.example.mobilityindia.syn1.view.allresponse.benificiary.Example_Benificiary;
import com.example.mobilityindia.syn1.view.allresponse.block.Datum_Block;
import com.example.mobilityindia.syn1.view.allresponse.block.Example_Block;
import com.example.mobilityindia.syn1.view.allresponse.clickinout.Example_ClickInOut;
import com.example.mobilityindia.syn1.view.allresponse.distic.Example_Distic;
import com.example.mobilityindia.syn1.view.allresponse.eduction.Example_Eduction;
import com.example.mobilityindia.syn1.view.allresponse.gp.Datum_Gp;
import com.example.mobilityindia.syn1.view.allresponse.gp.Example_Gp;
import com.example.mobilityindia.syn1.view.allresponse.health.Health_Example;
import com.example.mobilityindia.syn1.view.allresponse.livelihood.Livelihood_Example;
import com.example.mobilityindia.syn1.view.allresponse.social.DataDemo_Social;
import com.example.mobilityindia.syn1.view.allresponse.social.Social_Example;
import com.example.mobilityindia.syn1.view.allresponse.village.Datum_Villaga;
import com.example.mobilityindia.syn1.view.allresponse.village.Example_Village;
import com.example.mobilityindia.sync.model.ActionPlanData;
import com.example.mobilityindia.sync.model.ActionPlanMonth;
import com.example.mobilityindia.sync.model.ActivityReportAttendanceData;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.model.BlockData;
import com.example.mobilityindia.sync.model.DistData;
import com.example.mobilityindia.sync.model.EducationData;
import com.example.mobilityindia.sync.model.GPData;
import com.example.mobilityindia.sync.model.HealthCareData;
import com.example.mobilityindia.sync.model.LivehoodData;
import com.example.mobilityindia.sync.model.SocialData;
import com.example.mobilityindia.sync.model.VillageData;
import com.example.mobilityindia.sync.model.WorkPlanData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.utils.Utils;
import com.example.mobilityindia.workplan.MyJsonArrayRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class SyncActivityAll extends AppCompatActivity {

    ActivitySyncAllBinding binding;
    SessinoManager sessinoManager;
    String userId, str_activityReportValue = "", str_activityReportValue1 = "", sync_UserId = "", sync_UserId1 = "", str_button = "";
    RoomDB localdataBase;
    JSONArray jsonArray_attendance;
    AttendanceClass attendanceClass = new AttendanceClass();
    JSONArray jsonArrayf = new JSONArray();
    JSONArray jsonArray1 = new JSONArray();
    JSONArray jsonArray2 = new JSONArray();
    JSONArray jsonArray3 = new JSONArray();
    JSONArray jsonArray4 = new JSONArray();
    JSONArray jsonArray5 = new JSONArray();
    JSONArray jsonArray6 = new JSONArray();
    JSONArray jsonArrayatt = new JSONArray();
    LocalRepo localRepo;
    private ApiRequest apiRequest;
    ActionPlanData actionPlanData;
    ActionPlanMonth actionPlanMonth;
    ProgressBar progressView;
    int progress = 0;
    List<String> socialtrainingwhat;
    List<String> socialtrainingwhere;
    List<String> serviceArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sync_all);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sync_all);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        CommonClass.APP_TOKEN = CommonClass.getToken(this);
        binding.setLifecycleOwner(this);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        socialtrainingwhat = new ArrayList<>();
        socialtrainingwhere = new ArrayList<>();
        serviceArray = new ArrayList<>();

        sessinoManager = new SessinoManager(SyncActivityAll.this);
        userId = sessinoManager.getUSERID();
        //Initialize database
        localdataBase = RoomDB.getInstance(this);
        localRepo = new LocalRepo(SyncActivityAll.this);

        binding.progressView.setVisibility(View.GONE);
        // setProgressValue(progress);

        binding.btnProceed.setVisibility(View.GONE);

        SharedPreferences sharedPreferencesUserId = getSharedPreferences("MySharedPref_userId", MODE_PRIVATE);
        sync_UserId = sharedPreferencesUserId.getString("sync_UserId", null);
        sync_UserId1 = String.valueOf(sync_UserId);

        binding.serviceDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_button = binding.dataupdwn.getText().toString();

                if (sync_UserId1.equals("") || sync_UserId1.equals("null")) {

                    SharedPreferences sharedPreferencesUserId = getSharedPreferences("MySharedPref_userId", MODE_PRIVATE);
                    SharedPreferences.Editor myEdit1 = sharedPreferencesUserId.edit();
                    myEdit1.putString("sync_UserId", userId);
                    myEdit1.commit();

                    storeAllData();

                } else {

                    SharedPreferences sharedPreferencesUserId = getSharedPreferences("MySharedPref_userId", MODE_PRIVATE);
                    sync_UserId = sharedPreferencesUserId.getString("sync_UserId", null);
                    sync_UserId1 = String.valueOf(sync_UserId);

                    Log.d("jhuydhbh", sync_UserId1);

                    if (sync_UserId1.equals(userId)) {

                        storeAllData();

                    } else {

                        SharedPreferences sharedPreferencesUserId1 = getSharedPreferences("MySharedPref_userId", MODE_PRIVATE);
                        SharedPreferences.Editor myEdit1 = sharedPreferencesUserId1.edit();
                        myEdit1.putString("sync_UserId", userId);
                        myEdit1.commit();


                        storeAllData1();
                    }

                }


            }

        });

        binding.btnProceed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SyncActivityAll.this, LandingPageActivity.class));
            }
        });

    }

    public void getAttendanceReportLocalDataBase() {

        localRepo.getSelectedAttendanceList(userId).observe(SyncActivityAll.this, new Observer<List<AttendanceClass>>() {
            @Override
            public void onChanged(List<AttendanceClass> attendanceClasses) {

                //localRepo.deleteAllAttendance();

                if (attendanceClasses.size() == 0) {

                    // Toast.makeText(SyncActivityAll.this, "Data sync Successful", Toast.LENGTH_SHORT).show();

                    //binding.btnProceed.setVisibility(View.VISIBLE);

                } else {

                    String value = "";

                    for (int i = 0; i < attendanceClasses.size(); i++) {

                        String flag = String.valueOf(attendanceClasses.get(i).getFlag());
                        String flag1 = (flag.equals("null")) ? value : flag;

                        if(flag1.equals("update")){

                            String clockinlang = attendanceClasses.get(i).getClockinLong();
                            String getClockinlat = attendanceClasses.get(i).getClockinLat();
                            String clockout_long = attendanceClasses.get(i).getClockoutLong();
                            String clockout_lat = attendanceClasses.get(i).getClockinLat();
                            String clocktime = attendanceClasses.get(i).getCheckinTime();
                            String clockouttime = attendanceClasses.get(i).getClockoutTime();
                            String dateattendance = attendanceClasses.get(i).getDate();
                            String city = attendanceClasses.get(i).getCity();


                            JSONObject jsonObject = new JSONObject();

                            try {

                                jsonObject.put("user_id", userId);
                                jsonObject.put("date", dateattendance);
                                jsonObject.put("checkin_time", clocktime);
                                jsonObject.put("clockout_time", clockouttime);
                                jsonObject.put("clockin_lat", getClockinlat);
                                jsonObject.put("clockin_long", clockinlang);
                                jsonObject.put("clockout_lat", clockout_lat);
                                jsonObject.put("clockout_long", clockout_long);
                                jsonObject.put("city", "");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            jsonArrayf.put(jsonObject);

                        }
                    }

                    Log.d("gjusgjhbs", jsonArrayf.toString());

                    storeAttendanceInDataBase(jsonArrayf);

                }

            }
        });
    }

    public void storeAttendanceInDataBase(JSONArray jsonArray) {

        ProgressDialog progressDialog = new ProgressDialog(SyncActivityAll.this);
        progressDialog.setMessage("Data sync please Wait...");
        progressDialog.show();

        String url = "https://midev.zbapps.in/api/uploadClockin";

        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equals("true")) {

                        //Toast.makeText(SyncActivityAll.this, "Data Sync Successful", Toast.LENGTH_SHORT).show();

                    } else {

                        //Toast.makeText(SyncActivityAll.this, "Data not Retrive", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

               // Toast.makeText(SyncActivityAll.this, "" + error, Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                String creds = String.format("%s:%s", "mobility", "admin@123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);

                return params;
            }
        };
        myJsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SyncActivityAll.this);
        requestQueue.add(myJsonArrayRequest);

    }

    public void clockInOut(String userId) {

        ProgressDialog progressDialog = new ProgressDialog(SyncActivityAll.this);
        progressDialog.setMessage("Data sync Please Wait...");
        progressDialog.show();

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", userId);
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.listclockInList(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<Example_ClickInOut>() {
            @Override
            public void onResponse(Call<Example_ClickInOut> call, retrofit2.Response<Example_ClickInOut> response) {

                //Log.d("TAG", "Eductionresponse" + response.body().getData().toString());
                //Log.d("TAG", "onResponse response Eduction:" + response.body().toString());

                progressDialog.dismiss();

                if (response.body() != null) {

                    AttendanceClass attendanceClass = new AttendanceClass();
                    localRepo.deleteAllAttendance();
                    localRepo.deleteAttendance(attendanceClass);

                    for (AttendanceClass data : response.body().getData()) {

                        localRepo.insertAttandance(data);
                        attendanceClass.setFlag("store");

                        Log.d("sunilLivehood", data.toString());

                    }

                    // Log.d("sunilLivehood11", data.toString());
                }

            }

            @Override
            public void onFailure(Call<Example_ClickInOut> call, Throwable t) {

                progressDialog.dismiss();

                Toast.makeText(SyncActivityAll.this, "ClickInClockOut" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Caste Master
    public void callCasteMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getCasteMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponseresponse:: " + response.body());

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        //Snackbar.make(binding.relativeLayout,response.body().toString(),Snackbar.LENGTH_LONG).setTextColor(Color.BLUE).show();

                        getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("castemaster", response.body().toString()).commit();


                    }

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
                //isprogress.setValue(10);
            }
        });
    }

    //Caste Master
    public void CallMasterOccuption() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getMasterOccuption(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponseresponse:: " + response.body());

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        //Snackbar.make(binding.relativeLayout,response.body().toString(),Snackbar.LENGTH_LONG).setTextColor(Color.BLUE).show();

                        getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("masteroccuption", response.body().toString()).commit();


                    }

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
                //isprogress.setValue(10);
            }
        });
    }

    //Economic Master
    public void callEconomicMasterData() {
        //binding.ProgressView.setProgress(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getEconomicMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {

                    getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("economicmaster", response.body().toString()).commit();
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                //isprogress.setValue(10);
            }
        });
    }

    //AnnualIncome Master
    public void callAnnualIncomeMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getAnualincomeMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {

                    getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("annualincomemaster", response.body().toString()).commit();
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                //isprogress.setValue(10);
            }
        });
    }

    //MaratialStatus Master
    public void callMaritialStatusMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getMaritialStatusMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("maritialStatusmaster", response.body().toString()).commit();
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                //isprogress.setValue(10);
            }
        });
    }

    //Education Master
    public void callEducationMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getEducationMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {

                    getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("educationmaster", response.body().toString()).commit();
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                //isprogress.setValue(10);
            }
        });
    }

    //Ocupation Master
    public void callOcupationMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getOcupationMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {

                    getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("occupationmaster", response.body().toString()).commit();
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                //isprogress.setValue(10);
            }
        });
    }

    //TypeDisability Master
    public void callTypeDisabilityMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getDisabilityMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {

                    getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("typedismaster", response.body().toString()).commit();

                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                //isprogress.setValue(10);
            }
        });
    }

    //PurposeVisit Master
    public void callPurposeVisitDisabilityMasterData() {

        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getPurposeVisitMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {

                    getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("purposevisitmaster", response.body().toString()).commit();
                    // isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                //isprogress.setValue(10);
            }
        });
    }

    // Benificiary Data
    public void callBeneFiciaryData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", userId);
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getBenificiaryData(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<Example_Benificiary>() {
            @Override
            public void onResponse(Call<Example_Benificiary> call, retrofit2.Response<Example_Benificiary> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                //isprogress.setValue(10);
                if (response.body() != null) {
                    //isprogress.setValue(10);
                    BeneData beneData = new BeneData();
                    localRepo.deleteBene(beneData);
                    localRepo.deleteBene();

                    for (BeneData data : response.body().getData()) {


                        localRepo.insertBene(data);

                    }

                }
            }

            @Override
            public void onFailure(Call<Example_Benificiary> call, Throwable t) {

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
                //isprogress.setValue(10);
            }
        });
    }

    public void getBeneficuaryLocal() {

        localRepo.getBeneList().observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> beneDataa) {

                String value = "";

                if (beneDataa.size() != 0) {

                    for (int i = 0; i < beneDataa.size(); i++) {

                        String flag = String.valueOf(beneDataa.get(i).getFlag());
                        String flag1 = (flag.equals("null")) ? value : flag;

                        if (flag1.equals("update")) {

                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String Datetime = sdf.format(c.getTime());

                            String tempid = String.valueOf(beneDataa.get(i).getTempId());
                            String tempid1 = (tempid.equals("null")) ? value : tempid;

                            //String userid = beneDataa.get(i).getUser_id();

                            String registrationdate = String.valueOf(beneDataa.get(i).getRegistrationDate());
                            String registrationdate1 = (registrationdate.equals("null")) ? value : registrationdate;

                            String name = String.valueOf(beneDataa.get(i).getName());
                            String name1 = (name.equals("null")) ? value : name;

                            String parentname = String.valueOf(beneDataa.get(i).getParentName());
                            String parentname1 = (parentname.equals("null")) ? value : parentname;

                            String relation = String.valueOf(beneDataa.get(i).getRelation());
                            String relation1 = (relation.equals("null")) ? value : relation;

                            String dob = String.valueOf(beneDataa.get(i).getDob());
                            String dob1 = (dob.equals("null")) ? value : dob;

                            String age = String.valueOf(beneDataa.get(i).getAge());
                            String age1 = (age.equals("null")) ? value : age;

                            String gender = String.valueOf(beneDataa.get(i).getGender());
                            String gender1 = (gender.equals("null")) ? value : gender;

                            String caste = String.valueOf(beneDataa.get(i).getCaste());
                            String caste1 = (caste.equals("null")) ? value : caste;

                            String religion = String.valueOf(beneDataa.get(i).getReligion());
                            String religion1 = (religion.equals("null")) ? value : religion;

                            String adhaarno = String.valueOf(beneDataa.get(i).getAdhaarNo());
                            String adhaarno1 = (adhaarno.equals("null")) ? value : adhaarno;

                            String annualincome = String.valueOf(beneDataa.get(i).getAnnualIncome());
                            String annualincome1 = (annualincome.equals("null")) ? value : annualincome;

                            String economicstatus = String.valueOf(beneDataa.get(i).getEconomicStatus());
                            String economicstatus1 = (economicstatus.equals("null")) ? value : economicstatus;

                            String maritalstatus = String.valueOf(beneDataa.get(i).getMaritalStatus());
                            String maritalstatus1 = (maritalstatus.equals("null")) ? value : maritalstatus;

                            String education = String.valueOf(beneDataa.get(i).getEducation());
                            String education1 = (education.equals("null")) ? value : education;

                            String occupation = String.valueOf(beneDataa.get(i).getOccupation());
                            String occupation1 = (occupation.equals("null")) ? value : occupation;

                            String typeofdisability = String.valueOf(beneDataa.get(i).getTypeOfDisability());
                            String typeofdisability1 = (typeofdisability.equals("null")) ? value : typeofdisability;

                            String typeofsubdisability = String.valueOf(beneDataa.get(i).getTypeOfSubDisability());
                            String typeofsubdisability1 = (typeofsubdisability.equals("null")) ? value : typeofsubdisability;

                            String percentageofdisability = String.valueOf(beneDataa.get(i).getPercentageOfDisability());
                            String percentageofdisability1 = (percentageofdisability.equals("null")) ? value : percentageofdisability;

                            String idcardno = String.valueOf(beneDataa.get(i).getIdCardNo());
                            String idcardno1 = (idcardno.equals("null")) ? value : idcardno;

                            String phpamount = String.valueOf(beneDataa.get(i).getPhpAmount());
                            String phpamount1 = (phpamount.equals("null")) ? value : phpamount;

                            String typeofbeneficiary = String.valueOf(beneDataa.get(i).getTypeOfBeneficiary());
                            String typeofbeneficiary1 = (typeofbeneficiary.equals("null")) ? value : typeofbeneficiary;

                            String purposeofvisit = String.valueOf(beneDataa.get(i).getPurposeOfVisit());
                            String purposeofvisit1 = (purposeofvisit.equals("null")) ? value : purposeofvisit;

                            String purposevisitdetails = String.valueOf(beneDataa.get(i).getPurposeVisitDetails());
                            String purposevisitdetails1 = (purposevisitdetails.equals("null")) ? value : purposevisitdetails;

                            String havebankacc = String.valueOf(beneDataa.get(i).getHaveBankAcc());
                            String havebankacc1 = (havebankacc.equals("null")) ? value : havebankacc;

                            String accnum = String.valueOf(beneDataa.get(i).getAccNum());
                            String accnum1 = (accnum.equals("null")) ? value : accnum;

                            String accholdername = String.valueOf(beneDataa.get(i).getAccHolderName());
                            String accholdername1 = (accholdername.equals("null")) ? value : accholdername;

                            String ifsc = String.valueOf(beneDataa.get(i).getIfsc());
                            String ifsc1 = (ifsc.equals("null")) ? value : ifsc;

                            String acctype = String.valueOf(beneDataa.get(i).getAccType());
                            String acctype1 = (acctype.equals("null")) ? value : acctype;

                            String nameofpwdcwd = String.valueOf(beneDataa.get(i).getNameOfPwdCwd());
                            String nameofpwdcwd1 = (nameofpwdcwd.equals("null")) ? value : nameofpwdcwd;

                            String benificiaryid = String.valueOf(beneDataa.get(i).getBenificiaryId());
                            String benificiaryid1 = (benificiaryid.equals("null")) ? value : benificiaryid;

                            String registrationno = String.valueOf(beneDataa.get(i).getRegistrationNo());
                            String registrationno1 = (registrationno.equals("null")) ? value : registrationno;

                            String villageid = String.valueOf(beneDataa.get(i).getVillageId());
                            String villageid1 = (villageid.equals("null")) ? value : villageid;

                            String address = String.valueOf(beneDataa.get(i).getAddress());
                            String address1 = (address.equals("null")) ? value : address;

                            String schoolanganwadiname = String.valueOf(beneDataa.get(i).getSchoolAnganwadiName());
                            String schoolanganwadiname1 = (schoolanganwadiname.equals("null")) ? value : schoolanganwadiname;

                            String contactno = String.valueOf(beneDataa.get(i).getContactNo());
                            String contactno1 = (contactno.equals("null")) ? value : contactno;

                            String contactnoother = String.valueOf(beneDataa.get(i).getContactNoOther());
                            String contactnoother1 = (contactnoother.equals("null")) ? value : contactnoother;

                            String emailid = String.valueOf(beneDataa.get(i).getEmailId());
                            String emailid1 = (emailid.equals("null")) ? value : emailid;

                            String rationcardno = String.valueOf(beneDataa.get(i).getRationCardNo());
                            String rationcardno1 = (rationcardno.equals("null")) ? value : rationcardno;

                            String sanitationtoilet = String.valueOf(beneDataa.get(i).getSanitationToilet());
                            String sanitationtoilet1 = (sanitationtoilet.equals("null")) ? value : sanitationtoilet;

                            String appliances = String.valueOf(beneDataa.get(i).getAppliances());
                            String appliances1 = (appliances.equals("null")) ? value : appliances;

                            String surgery = String.valueOf(beneDataa.get(i).getSurgery());
                            String surgery1 = (surgery.equals("null")) ? value : surgery;

                            String govtfacilities = String.valueOf(beneDataa.get(i).getGovtFacilities());
                            String govtfacilities1 = (govtfacilities.equals("null")) ? value : govtfacilities;

                            String govtfacilitymention = String.valueOf(beneDataa.get(i).getGovtFacilityMention());
                            String govtfacilitymention1 = (govtfacilitymention.equals("null")) ? value : govtfacilitymention;

                            String familymember = String.valueOf(beneDataa.get(i).getFamilyMember());
                            String familymember1 = (familymember.equals("null")) ? value : familymember;

                            String familymemberadults = String.valueOf(beneDataa.get(i).getFamilyMemberAdults());
                            String familymemberadults1 = (familymemberadults.equals("null")) ? value : familymemberadults;

                            String familymemberchildm = String.valueOf(beneDataa.get(i).getFamilyMemberChildM());
                            String familymemberchildm1 = (familymemberchildm.equals("null")) ? value : familymemberchildm;

                            String familymemberchildf = String.valueOf(beneDataa.get(i).getFamilyMemberAdults());
                            String familymemberchildf1 = (familymemberchildf.equals("null")) ? value : familymemberchildf;

                            String childrenundergoingeducationm = String.valueOf(beneDataa.get(i).getChildrenUndergoingEducationM());
                            String childrenundergoingeducationm1 = (childrenundergoingeducationm.equals("null")) ? value : childrenundergoingeducationm;

                            String childrenundergoingeducationf = String.valueOf(beneDataa.get(i).getChildrenUndergoingEducationM());
                            String childrenundergoingeducationf1 = (childrenundergoingeducationf.equals("null")) ? value : childrenundergoingeducationf;

                            String dropoutsless14f = String.valueOf(beneDataa.get(i).getDropoutsLess14F());
                            String dropoutsless14f1 = (dropoutsless14f.equals("null")) ? value : dropoutsless14f;

                            String dropoutsless14m = String.valueOf(beneDataa.get(i).getDropoutsLess14M());
                            String dropoutsless14m1 = (dropoutsless14m.equals("null")) ? value : dropoutsless14m;

                            String dropouts1418m = String.valueOf(beneDataa.get(i).getDropouts1418M());
                            String dropouts1418m1 = (dropouts1418m.equals("null")) ? value : dropouts1418m;

                            String dropouts1418f = String.valueOf(beneDataa.get(i).getDropouts1418M());
                            String dropouts1418f1 = (dropouts1418f.equals("null")) ? value : dropouts1418f;

                            String earningmembersflym = String.valueOf(beneDataa.get(i).getEarningMembersFlyM());
                            String earningmembersflym1 = (earningmembersflym.equals("null")) ? value : earningmembersflym;

                            String earningmembersflyf = String.valueOf(beneDataa.get(i).getEarningMembersFlyF());
                            String earningmembersflyf1 = (earningmembersflyf.equals("null")) ? value : earningmembersflyf;

                            String benefid = String.valueOf(beneDataa.get(i).getBenefId());
                            String benefid1 = (benefid.equals("null")) ? value : benefid;

                            String wheathercbomember = String.valueOf(beneDataa.get(i).getWheatherCboMember());
                            String wheathercbomember1 = (wheathercbomember.equals("null")) ? value : wheathercbomember;

                            String cboname = String.valueOf(beneDataa.get(i).getCasteName());
                            String cboname1 = (cboname.equals("null")) ? value : cboname;

                            String startyearofcbo = String.valueOf(beneDataa.get(i).getStartYearOfCbo());
                            String startyearofcbo1 = (startyearofcbo.equals("null")) ? value : startyearofcbo;

                            String yearjoincbo = String.valueOf(beneDataa.get(i).getYearJoinCbo());
                            String yearjoincbo1 = (yearjoincbo.equals("null")) ? value : yearjoincbo;


                            JSONObject jsonObject = new JSONObject();

                            try {

                                jsonObject.put("temp_id", tempid1);
                                jsonObject.put("user_id", userId);
                                jsonObject.put("registration_date", registrationdate1);
                                jsonObject.put("name", name1);
                                jsonObject.put("parent_name", parentname1);
                                jsonObject.put("relation", relation1);
                                jsonObject.put("dob", dob1);
                                jsonObject.put("gender", gender1);
                                jsonObject.put("caste", caste1);
                                jsonObject.put("religion", religion1);
                                jsonObject.put("adhaar_no", adhaarno1);
                                jsonObject.put("annual_income", annualincome1);
                                jsonObject.put("economic_status", economicstatus1);
                                jsonObject.put("marital_status", maritalstatus1);
                                jsonObject.put("education", education1);
                                jsonObject.put("occupation", occupation1);
                                jsonObject.put("type_of_disability", typeofdisability1);
                                jsonObject.put("type_of_sub_disability", typeofsubdisability1);
                                jsonObject.put("percentage_of_disability", percentageofdisability1);
                                jsonObject.put("id_card_no", idcardno1);
                                jsonObject.put("php_amount", phpamount1);
                                jsonObject.put("type_of_beneficiary", typeofbeneficiary1);
                                jsonObject.put("purpose_of_visit", purposeofvisit1);
                                jsonObject.put("purpose_visit_details", purposevisitdetails1);
                                jsonObject.put("have_bank_acc", havebankacc1);
                                jsonObject.put("acc_num", accnum1);
                                jsonObject.put("acc_holder_name", accholdername1);
                                jsonObject.put("ifsc", ifsc1);
                                jsonObject.put("acc_type", acctype1);
                                jsonObject.put("name_of_pwd_cwd", nameofpwdcwd1);
                                jsonObject.put("created_at", nameofpwdcwd1);
                                jsonObject.put("updated_at", Datetime);
                                jsonObject.put("benificiary_id", benificiaryid1);
                                jsonObject.put("registration_no", registrationno1);
                                jsonObject.put("village_id", villageid1);
                                jsonObject.put("address", address1);
                                jsonObject.put("school_anganwadi_name", schoolanganwadiname1);
                                jsonObject.put("contact_no", contactno1);
                                jsonObject.put("contact_no_other", contactnoother1);
                                jsonObject.put("email_id", emailid1);
                                jsonObject.put("ration_card_no", rationcardno1);
                                jsonObject.put("sanitation_toilet", sanitationtoilet1);
                                jsonObject.put("appliances", appliances1);
                                jsonObject.put("surgery", surgery1);
                                jsonObject.put("govt_facilities", govtfacilities1);
                                jsonObject.put("govt_facility_mention", govtfacilitymention1);
                                jsonObject.put("family_member", familymember1);
                                jsonObject.put("family_member_adults", familymemberadults1);
                                jsonObject.put("family_member_child_m", familymemberchildm1);
                                jsonObject.put("family_member_child_f", familymemberchildf1);
                                jsonObject.put("children_undergoing_education_m", childrenundergoingeducationm1);
                                jsonObject.put("children_undergoing_education_f", childrenundergoingeducationf1);
                                jsonObject.put("dropouts_less_14_m", dropoutsless14m1);
                                jsonObject.put("dropouts_less_14_f", dropoutsless14f1);
                                jsonObject.put("dropouts_14_18_m", dropouts1418m1);
                                jsonObject.put("dropouts_14_18_f", dropouts1418f1);
                                jsonObject.put("earning_members_fly_m", earningmembersflym1);
                                jsonObject.put("earning_members_fly_f", earningmembersflyf1);
                                jsonObject.put("benef_id", benefid1);
                                jsonObject.put("wheather_cbo_member", wheathercbomember1);
                                jsonObject.put("cbo_name", cboname1);
                                jsonObject.put("start_year_of_cbo", startyearofcbo1);
                                jsonObject.put("year_join_cbo", yearjoincbo1);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            jsonArray3.put(jsonObject);

                        }
                    }

                    Log.d("gjusgjhbs", jsonArray3.toString());

                    storeBeneficiaryInDataBase(jsonArray3);

                } else {

                    //Toast.makeText(SyncActivityAll.this, "Data Sync SuccessFully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void storeBeneficiaryInDataBase(JSONArray jsonArray) {

        ProgressDialog progressDialog = new ProgressDialog(SyncActivityAll.this);
        progressDialog.setMessage("Data sync please Wait...");
        progressDialog.show();

        String url = "https://midev.zbapps.in/api/uploadListben";

        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equals("true")) {

                        //Toast.makeText(SyncActivityAll.this, "Data Sync Successful", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(SyncActivityAll.this, "Data Sync Successful", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(SyncActivityAll.this, "Beneficary " + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                String creds = String.format("%s:%s", "mobility", "admin@123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);

                return params;
            }
        };
        myJsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SyncActivityAll.this);
        requestQueue.add(myJsonArrayRequest);
    }

    //Religion Master
    public void callReligionMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getReligionMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {

                    getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("religionmaster", response.body().toString()).commit();
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                //isprogress.setValue(10);
            }
        });
    }

    // State MAster
    public void callStateMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getStateMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {

                    getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("statemaster", response.body().toString()).commit();
                    // syncviewModel.callDisticMasterData();
                    //isprogress.setValue(10);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Hoboli Master
    public void callHoboliMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getHoboliMaster(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponse response Hoboli master:: " + response.body());
                if (response.body() != null) {
                    //HoboliMutableLiveData.setValue(response.body());
                    //isprogress.setValue(10);

                    getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("hobolimaster", response.body().toString()).commit();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                //isprogress.setValue(10);

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Distic Master
    public void callDisticMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getDistMaster(CommonClass.APP_TOKEN).enqueue(new Callback<Example_Distic>() {
            @Override
            public void onResponse(Call<Example_Distic> call, retrofit2.Response<Example_Distic> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {

                    //isprogress.setValue(10);

                    localRepo.deleteDist();

                    for (int i = 0; i < response.body().getData().size(); i++) {

                        DistData distData = new DistData();
                        distData.setDistrict_id(response.body().getData().get(i).getDistrictId());
                        distData.setDistrict_name(response.body().getData().get(i).getDistrictName());
                        distData.setState_id(response.body().getData().get(i).getStateId());

                        localRepo.insertDist(distData);
                    }
                }
            }

            @Override
            public void onFailure(Call<Example_Distic> call, Throwable t) {
                //DisticMutableLiveData.setValue(null);
                //isprogress.setValue(10);

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Block Master
    public void callBlockMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getBlockMaster(CommonClass.APP_TOKEN).enqueue(new Callback<Example_Block>() {
            @Override
            public void onResponse(Call<Example_Block> call, retrofit2.Response<Example_Block> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {

                    localRepo.deleteBlock();

                    for (Datum_Block data : response.body().getData()) {

                        BlockData blockData = new BlockData();
                        blockData.setBlockId(data.getBlockId());
                        blockData.setBlockName(data.getBlockName());
                        blockData.setDistrictId(data.getDistrictId());
                        blockData.setStateId(data.getStateId());

                        localRepo.insertBlock(blockData);
                    }
                }
            }

            @Override
            public void onFailure(Call<Example_Block> call, Throwable t) {
                //BlockMutableLiveData.setValue(null);
                //isprogress.setValue(10);
            }
        });
    }

    //GP Master
    public void callGPMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getGpMaster(CommonClass.APP_TOKEN).enqueue(new Callback<Example_Gp>() {
            @Override
            public void onResponse(Call<Example_Gp> call, retrofit2.Response<Example_Gp> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    //isprogress.setValue(10);

                    localRepo.deleteGP();
                    for (Datum_Gp data : response.body().getData()) {

                        GPData gpData = new GPData();

                        gpData.setStateId(data.getStateId());
                        gpData.setBlockId(data.getBlockId());
                        gpData.setDistrictId(data.getDistrictId());
                        gpData.setGpName(data.getGpName());
                        gpData.setGpId(data.getGpId());

                        localRepo.insertGP(gpData);

                    }
                }
            }

            @Override
            public void onFailure(Call<Example_Gp> call, Throwable t) {
                //isprogress.setValue(10);
            }
        });
    }

    //Village Master
    public void callVillageMasterData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getVillageMaster(CommonClass.APP_TOKEN).enqueue(new Callback<Example_Village>() {
            @Override
            public void onResponse(Call<Example_Village> call, retrofit2.Response<Example_Village> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    //isprogress.setValue(10);

                    localRepo.deleteVillage();

                    for (Datum_Villaga data : response.body().getData()) {

                        VillageData villageData = new VillageData();

                        villageData.setId(data.getId());
                        villageData.setVillageName(data.getVillageName());
                        villageData.setBlockId(data.getBlockId());
                        villageData.setDistrictId(data.getDistrictId());
                        villageData.setGpId(data.getGpId());
                        villageData.setStateId(data.getStateId());
                        villageData.setHobliId(data.getHobliId());

                        localRepo.insertVillage(villageData);


                    }
                }
            }

            @Override
            public void onFailure(Call<Example_Village> call, Throwable t) {
                //isprogress.setValue(10);
            }
        });
    }

    // ActionPlan Data
    public void callActionPlanData(String userId) {

        /* if(str_actionPlanValue.equalsIgnoreCase("save")){}else{*/

        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", userId);
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getActionPlanData(mapData).enqueue(new Callback<Example_ActionPlan>() {
            @Override
            public void onResponse(Call<Example_ActionPlan> call, retrofit2.Response<Example_ActionPlan> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                //isprogress.setValue(10);
                if (response.body() != null) {

                    ActionPlanData actionPlanData = new ActionPlanData();
                    ActionPlanMonth actionPlanMonth = new ActionPlanMonth();

                    localRepo.deleteActionPlan(actionPlanData);
                    localRepo.deleteBene1();

                    localRepo.deleteActionPlanMonth(actionPlanMonth);
                    localRepo.deleteBeneMonth();

                    for (Datum_ActionPlan data : response.body().getData()) {

                        localRepo.insertActionPlan(data.getMonthlyStatus());

                        Log.d("hshvuydg", data.getMonthlyStatus().toString());

                        for (int i = 0; i < data.getPlans().size(); i++) {

                            localRepo.insertActionPlanMonth(data.getPlans().get(i));
                            actionPlanMonth.setFlag("store");
                        }
                    }

                    // str_actionPlanValue = "save";
                }
            }

            @Override
            public void onFailure(Call<Example_ActionPlan> call, Throwable t) {
                //isprogress.setValue(10);

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
        //}

    }

    public void storeActionPlan() {

        localRepo.getAllActionPlanMonthList().observe(SyncActivityAll.this, new Observer<List<ActionPlanMonth>>() {
            @Override
            public void onChanged(List<ActionPlanMonth> actionPlanMonths) {

                String value = "";
                if (actionPlanMonths.size() != 0) {

                    for (int i = 0; i < actionPlanMonths.size(); i++) {

                        String flag = String.valueOf(actionPlanMonths.get(i).getFlag());
                        String flag1 = (flag.equals("null")) ? value : flag;

                        if(flag1.equals("update")){

                            String id1 = actionPlanMonths.get(i).getId();
                            String user_id1 = actionPlanMonths.get(i).getUserId();
                            String month_year1 = actionPlanMonths.get(i).getMonthYear();
                            String date = actionPlanMonths.get(i).getDate();
                            String plan = actionPlanMonths.get(i).getPlan();
                            String result = actionPlanMonths.get(i).getResult();
                            String remarks = actionPlanMonths.get(i).getRemarks();

                            JSONObject jsonObject = new JSONObject();

                            try {
                                jsonObject.put("id", id1);
                                jsonObject.put("user_id", user_id1);
                                jsonObject.put("date", date);
                                jsonObject.put("plan", plan);
                                jsonObject.put("result", result);
                                jsonObject.put("remarks", remarks);

                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                            jsonArray1.put(jsonObject);
                        }

                    }

                    updateAttendance1(jsonArray1);

                    Log.d("hhcgfc", jsonArray1.toString());
                } else {

                    Toast.makeText(SyncActivityAll.this, "Data sync Successful", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void updateAttendance1(JSONArray jsonArray) {

        ProgressDialog pd = new ProgressDialog(SyncActivityAll.this);
        pd.setMessage("Data sync Please Wait...");
        pd.show();
        pd.show();

        String url = "https://midev.zbapps.in/api/updateactionplan";

        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equals("true")) {

                        //Toast.makeText(SyncActivityAll.this, "Data sync Successful", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pd.dismiss();

                Toast.makeText(SyncActivityAll.this, "api Not Respons Properly", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                String creds = String.format("%s:%s", "mobility", "admin@123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);

                return params;
            }

        };

        myJsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SyncActivityAll.this);
        requestQueue.add(myJsonArrayRequest);
    }

    // Activity Report
    public void callActivityPlanData(String UserId) {
/*
        if (str_activityReportValue.equalsIgnoreCase("save")) {}
        else {*/
            //isprogress.setValue(0);
            Map<String, Object> mapData = new HashMap<>();
            mapData.put("user_id", UserId);
            apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
            apiRequest.getActivityReport(mapData).enqueue(new Callback<Example_ActivityReport>() {
                @Override
                public void onResponse(Call<Example_ActivityReport> call, retrofit2.Response<Example_ActivityReport> response) {
                    Log.d("TAG", "onResponse response:: " + response.body());
                    //isprogress.setValue(10);
                    if (response.body() != null) {

                        WorkPlanData workPlanData = new WorkPlanData();
                        localRepo.DeleteWorkPlan(workPlanData);
                        localRepo.deleteWorkplan();

                        for (WorkPlanData data : response.body().getData()) {

                            localRepo.insertWorkPlan(data);
                        }

                        str_activityReportValue = "save";

                    }
                }

                @Override
                public void onFailure(Call<Example_ActivityReport> call, Throwable t) {

                    // isprogress.setValue(10);
                }
            });
        //}
    }

    public void callActivityPlanData1(String UserId) {

        /*if (str_activityReportValue1.equalsIgnoreCase("save")) {
        } else {*/

            //isprogress.setValue(0);
            Map<String, Object> mapData = new HashMap<>();
            mapData.put("user_id", UserId);
            apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
            apiRequest.getActivityReport1(mapData).enqueue(new Callback<ExapmleActivityReport>() {
                @Override
                public void onResponse(Call<ExapmleActivityReport> call, retrofit2.Response<ExapmleActivityReport> response) {
                    //Log.d("TAG", "onResponse response:: " + response.body().getData());
                    //isprogress.setValue(10);

                    if (response.isSuccessful()) {

                        WorkPlanData workPlanData = new WorkPlanData();
                        ActivityReportAttendanceData attendance = new ActivityReportAttendanceData();

                        localRepo.DeleteWorkPlan(workPlanData);
                        localRepo.deleteWorkplan();

                        localRepo.deleteActivityRep(attendance);
                        localRepo.deleteBeneatten();

                        str_activityReportValue1 = "save";

                        if (response.body() != null) {

                            for (Datum_ActivityReport data : response.body().getData()) {

                                localRepo.insertWorkPlan(data.getActivityReport());

                                for (int i = 0; i < data.getAttendances().size(); i++) {

                                    localRepo.insertActivityRep(data.getAttendances().get(i));
                                }
                            }
                        }

                    } else {

                       // Toast.makeText(SyncActivityAll.this, "Data Retrive Not Success", Toast.LENGTH_SHORT).show();
                    }

                    str_activityReportValue1 = "save";
                }

                @Override
                public void onFailure(Call<ExapmleActivityReport> call, Throwable t) {

                    // isprogress.setValue(10);

                    Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
                }
            });
        //}

    }

    private void getEductionDetails() {

        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", userId);
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.listEducationService(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<Example_Eduction>() {
            @Override
            public void onResponse(Call<Example_Eduction> call, retrofit2.Response<Example_Eduction> response) {

                //Log.d("TAG", "Eductionresponse" + response.body().getEducationdata().toString());
                //Log.d("TAG", "onResponse response Eduction:" + response.body().toString());


                if (response.body() != null) {

                    EducationData educationData = new EducationData();
                    localRepo.deleteEducation(educationData);
                    localRepo.deleteEducation();

                    for (EducationData data : response.body().getEducationdata()) {

                        localRepo.insertEducationData(data);
                        educationData.setFlag("store");

                        Log.d("sunilEduction", data.toString());

                    }
                }

            }

            @Override
            public void onFailure(Call<Example_Eduction> call, Throwable t) {

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();

            }
        });

    }

    //Socal tranning
    public void callSocialTranning() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.gettraining(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponseresponse:: " + response.body());

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        //Snackbar.make(binding.relativeLayout,response.body().toString(),Snackbar.LENGTH_LONG).setTextColor(Color.BLUE).show();

                        getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("socaltranning", response.body().toString()).commit();


                    }

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
                //isprogress.setValue(10);
            }
        });
    }

    //Helth Services
    public void callHealthActivity() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getHealthView(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponseresponse:: " + response.body());

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        //Snackbar.make(binding.relativeLayout,response.body().toString(),Snackbar.LENGTH_LONG).setTextColor(Color.BLUE).show();

                        getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("healthactivity", response.body().toString()).commit();


                    }

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
                //isprogress.setValue(10);
            }
        });
    }

    public void getAllEductionDeatils() {

        localRepo.getEducationList().observe(SyncActivityAll.this, new Observer<List<EducationData>>() {
            @Override
            public void onChanged(List<EducationData> eductionDemos) {

                //localRepo.deleteAllAttendance();

                if (eductionDemos.size() == 0) {

                    Toast.makeText(SyncActivityAll.this, "Data sync Successful", Toast.LENGTH_SHORT).show();

                    //binding.btnProceed.setVisibility(View.VISIBLE);

                } else {

                    String value = "";

                    for (int i = 0; i < eductionDemos.size(); i++) {

                        String flag = String.valueOf(eductionDemos.get(i).getFlag());
                        String flag1 = (flag.equals("null")) ? value : flag;

                        if (flag1.equals("update")) {

                            String benificiaryid = eductionDemos.get(i).getBenificiaryId();
                            //String userid = eductionDemos.get(i).getUser_id();
                            String school = eductionDemos.get(i).getSchool();
                            String enrollmentno = String.valueOf(eductionDemos.get(i).getEnrollmentno());
                            String enrollmentno1 = (enrollmentno.equals("null")) ? value : enrollmentno;

                            String attendingclass = String.valueOf(eductionDemos.get(i).getAttendingclass());
                            String attendingclass1 = (attendingclass.equals("null")) ? value : attendingclass;

                            String schoolaccess = String.valueOf(eductionDemos.get(i).getSchoolaccess());
                            String schoolaccess1 = (schoolaccess.equals("null")) ? value : schoolaccess;

                            String sitting = String.valueOf(eductionDemos.get(i).getSitting());
                            String sitting1 = (sitting.equals("null")) ? value : sitting;

                            String tlm = String.valueOf(eductionDemos.get(i).getTlm());
                            String tlm1 = (tlm.equals("null")) ? value : tlm;

                            String toilet = String.valueOf(eductionDemos.get(i).getToilet());
                            String toilet1 = (toilet.equals("null")) ? value : toilet;

                            String library = String.valueOf(eductionDemos.get(i).getLibrary());
                            String library1 = (library.equals("null")) ? value : library;

                            String sports = String.valueOf(eductionDemos.get(i).getSports());
                            String sports1 = (sports.equals("null")) ? value : sports;

                            String cocurricular = String.valueOf(eductionDemos.get(i).getCocurricular());
                            String cocurricular1 = (cocurricular.equals("null")) ? value : cocurricular;

                            String schoolother = String.valueOf(eductionDemos.get(i).getSchoolother());
                            String schoolother1 = (schoolother.equals("null")) ? value : schoolother;

                            String cec = String.valueOf(eductionDemos.get(i).getCec());
                            String cec1 = (cec.equals("null")) ? value : cec;

                            String parliament = String.valueOf(eductionDemos.get(i).getParliament());
                            String parliament1 = (parliament.equals("null")) ? value : parliament;

                            String gramsabha = String.valueOf(eductionDemos.get(i).getGramsabha());
                            String gramsabha1 = (gramsabha.equals("null")) ? value : gramsabha;

                            String summercamp = String.valueOf(eductionDemos.get(i).getSummercamp());
                            String summercamp1 = (summercamp.equals("null")) ? value : summercamp;

                            String activityone = String.valueOf(eductionDemos.get(i).getActivityOne());
                            String activityone1 = (activityone.equals("null")) ? value : activityone;

                            String activitytwo = String.valueOf(eductionDemos.get(i).getActivityTwo());
                            String activitytwo1 = (activitytwo.equals("null")) ? value : activitytwo;

                            String activitythree = String.valueOf(eductionDemos.get(i).getActivityThree());
                            String activitythree1 = (activitythree.equals("null")) ? value : activitythree;

                            String activityfour = String.valueOf(eductionDemos.get(i).getActivityFour());
                            String activityfour1 = (activityfour.equals("null")) ? value : activityfour;

                            String activityfive = String.valueOf(eductionDemos.get(i).getActivityFive());
                            String activityfive1 = (activityfive.equals("null")) ? value : activityfive;

                            String iep = String.valueOf(eductionDemos.get(i).getIep());
                            String iep1 = (iep.equals("null")) ? value : iep;

                            List<String> iepdoc = eductionDemos.get(i).getIepdoc();

                            String createdat = String.valueOf(eductionDemos.get(i).getCreatedAt());
                            String updatedat = String.valueOf(eductionDemos.get(i).getCreatedAt());


                            JSONObject jsonObject = new JSONObject();

                            try {

                                //jsonObject.put("id",id);
                                jsonObject.put("benificiary_id", benificiaryid);
                                jsonObject.put("user_id", userId);
                                jsonObject.put("school", school);
                                jsonObject.put("enrollmentno", enrollmentno1);
                                jsonObject.put("attendingclass", attendingclass1);
                                jsonObject.put("schoolaccess", schoolaccess1);
                                jsonObject.put("sitting", sitting1);
                                jsonObject.put("tlm", tlm1);
                                jsonObject.put("toilet", toilet1);
                                jsonObject.put("library", library1);
                                jsonObject.put("sports", sports1);
                                jsonObject.put("cocurricular", cocurricular1);
                                jsonObject.put("schoolother", schoolother1);
                                jsonObject.put("cec", cec1);
                                jsonObject.put("parliament", parliament1);
                                jsonObject.put("gramsabha", gramsabha1);
                                jsonObject.put("summercamp", summercamp1);
                                jsonObject.put("activity_one", activityone1);
                                jsonObject.put("activity_two", activitytwo1);
                                jsonObject.put("activity_three", activitythree1);
                                jsonObject.put("activity_four", activityfour1);
                                jsonObject.put("activity_five", activityfive1);
                                jsonObject.put("iep", iep1);
                                jsonObject.put("iepdoc", "");
                                jsonObject.put("created_at", createdat);
                                jsonObject.put("updated_at", updatedat);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            jsonArray2.put(jsonObject);

                        }

                    }
                    Log.d("gjusgjhbs", jsonArray2.toString());

                    storeEductionDetails(jsonArray2);
                }

            }
        });
    }

    public void storeEductionDetails(JSONArray jsonArray) {

        ProgressDialog progressDialog = new ProgressDialog(SyncActivityAll.this);
        progressDialog.setMessage("Data sync please Wait...");
        progressDialog.show();

        String url = "https://midev.zbapps.in/api/uploadEducation";

        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equals("true")) {

                        // Toast.makeText(SyncActivityAll.this, "Data Sync Successful", Toast.LENGTH_SHORT).show();

                    } else {

                        //Toast.makeText(SyncActivityAll.this, "Data not Retrive", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                Toast.makeText(SyncActivityAll.this, "Eduction" + error, Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                String creds = String.format("%s:%s", "mobility", "admin@123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);

                return params;
            }
        };
        myJsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SyncActivityAll.this);
        requestQueue.add(myJsonArrayRequest);

    }

    private void getdataHealthActivity() {

        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", userId);
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.listHealthService(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<Health_Example>() {
            @Override
            public void onResponse(Call<Health_Example> call, retrofit2.Response<Health_Example> response) {

              //  Log.d("TAG", "Eductionresponse" + response.body().getHealthdata().toString());
               // Log.d("TAG", "onResponse response Eduction:" + response.body().toString());

                if (response.body() != null) {

                    HealthCareData healthCareData = new HealthCareData();
                    localRepo.deleteHealth();
                    localRepo.deleteHealthCare(healthCareData);

                    for (HealthCareData data : response.body().getHealthdata()) {

                        localRepo.insertHealthCareData(data);

                       // Log.d("sunilEduction", data.toString());

                    }

                    Log.d("sunilEduction", response.body().getHealthdata().toString());
                }

            }

            @Override
            public void onFailure(Call<Health_Example> call, Throwable t) {

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getLivelihoodService() {

        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", userId);
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.listLivelihoodService(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<Livelihood_Example>() {
            @Override
            public void onResponse(Call<Livelihood_Example> call, retrofit2.Response<Livelihood_Example> response) {

//                Log.d("TAG", "Eductionresponse" + response.body().getLivelihooddata().toString());
           //     Log.d("TAG", "onResponse response Eduction:" + response.body().toString());


                if (response.body() != null) {

                    LivehoodData livehoodData = new LivehoodData();

                    localRepo.deleteLivehood(livehoodData);
                    localRepo.deleteLivehood();

                    for (LivehoodData data : response.body().getLivelihooddata()) {

                        localRepo.insertLivehoodData(data);
                        livehoodData.setFlag("store");

                        Log.d("sunilLivehood", data.toString());

                    }

                    // Log.d("sunilLivehood11", data.toString());
                }

            }

            @Override
            public void onFailure(Call<Livelihood_Example> call, Throwable t) {

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void getAllLivelihoodService() {

        localRepo.getLiveHoodList().observe(SyncActivityAll.this, new Observer<List<LivehoodData>>() {
            @Override
            public void onChanged(List<LivehoodData> livehoodData) {

                //localRepo.deleteAllAttendance();

                if (livehoodData.size() == 0) {

                    // Toast.makeText(SyncActivityAll.this, "Data sync Successful", Toast.LENGTH_SHORT).show();

                    //binding.btnProceed.setVisibility(View.VISIBLE);

                } else {

                    String value = "";

                    for (int i = 0; i < livehoodData.size(); i++) {

                        String flag = String.valueOf(livehoodData.get(i).getFlag());
                        String flag1 = (flag.equals("null")) ? value : flag;

                        if (flag1.equals("update")) {

                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String Datetime = sdf.format(c.getTime());

                            String benificiaryid = String.valueOf(livehoodData.get(i).getBenificiary_id());
                            String benificiaryid1 = (benificiaryid.equals("null")) ? value : benificiaryid;

                            //String userid = eductionDemos.get(i).getUser_id();

                            String livelihood = String.valueOf(livehoodData.get(i).getLivelihood());
                            String livelihood1 = (livelihood.equals("null")) ? value : livelihood;

                            String livelihoodother = String.valueOf(livehoodData.get(i).getLivelihoodother());
                            String livelihoodother1 = (livelihoodother.equals("null")) ? value : livelihoodother;

                            String skilldev = String.valueOf(livehoodData.get(i).getSkilldev());
                            String skilldev1 = (skilldev.equals("null")) ? value : skilldev;

                            String skilldevwhen = String.valueOf(livehoodData.get(i).getSkilldevwhen());
                            String skilldevwhen1 = (skilldevwhen.equals("null")) ? value : skilldevwhen;

                            String skilldevwhere = String.valueOf(livehoodData.get(i).getSkilldevwhere());
                            String skilldevwhere1 = (skilldevwhere.equals("null")) ? value : skilldevwhere;

                            String skilldevwhat = String.valueOf(livehoodData.get(i).getSkilldevwhat());
                            String skilldevwhat1 = (skilldevwhat.equals("null")) ? value : skilldevwhat;

                            String finservice = String.valueOf(livehoodData.get(i).getFinservice());
                            String finservice1 = (finservice.equals("null")) ? value : finservice;

                            String finservicewhen = String.valueOf(livehoodData.get(i).getFinservicewhen());
                            String finservicewhen1 = (finservicewhen.equals("null")) ? value : finservicewhen;

                            String finservicewhat = String.valueOf(livehoodData.get(i).getFinservicewhat());
                            String finservicewhat1 = (finservicewhat.equals("null")) ? value : finservicewhat;

                            String finservicewhere = String.valueOf(livehoodData.get(i).getFinservicewhere());
                            String finservicewhere1 = (finservicewhere.equals("null")) ? value : finservicewhere;

                            String socialsecurity = String.valueOf(livehoodData.get(i).getSocialsecurity());
                            String socialsecurity1 = (socialsecurity.equals("null")) ? value : socialsecurity;

                            String socialsecuritywhen = String.valueOf(livehoodData.get(i).getSocialsecuritywhen());
                            String socialsecuritywhen1 = (socialsecuritywhen.equals("null")) ? value : socialsecuritywhen;

                            String socialsecuritywhere = String.valueOf(livehoodData.get(i).getSocialsecuritywhere());
                            String socialsecuritywhere1 = (socialsecuritywhere.equals("null")) ? value : socialsecuritywhere;

                            String socialsecuritywhat = String.valueOf(livehoodData.get(i).getSocialsecuritywhat());
                            String socialsecuritywhat1 = (socialsecuritywhat.equals("null")) ? value : socialsecuritywhat;

                            String vocationaltraining = String.valueOf(livehoodData.get(i).getVocationaltraining());
                            String vocationaltraining1 = (vocationaltraining.equals("null")) ? value : vocationaltraining;

                            String vocationaltraining_when = String.valueOf(livehoodData.get(i).getVocationaltrainingWhen());
                            String vocationaltraining_when1 = (vocationaltraining_when.equals("null")) ? value : vocationaltraining_when;

                            String vocationaltraining_what = String.valueOf(livehoodData.get(i).getVocationaltrainingWhat());
                            String vocationaltraining_what1 = (vocationaltraining_what.equals("null")) ? value : vocationaltraining_what;

                            String vocationaltraining_where = String.valueOf(livehoodData.get(i).getVocationaltrainingWhere());
                            String vocationaltraining_where1 = (vocationaltraining_where.equals("null")) ? value : vocationaltraining_where;

                            String serviceother = String.valueOf(livehoodData.get(i).getServiceother());
                            String serviceother1 = (serviceother.equals("null")) ? value : serviceother;

                            String serviceotherwhen = String.valueOf(livehoodData.get(i).getServiceotherwhen());
                            String serviceotherwhen1 = (serviceotherwhen.equals("null")) ? value : serviceotherwhen;

                            String serviceotherwhere = String.valueOf(livehoodData.get(i).getServiceotherwhere());
                            String serviceotherwhere1 = (serviceotherwhere.equals("null")) ? value : serviceotherwhere;

                            String serviceotherwhat = String.valueOf(livehoodData.get(i).getServiceotherwhat());
                            String serviceotherwhat1 = (serviceotherwhat.equals("null")) ? value : serviceotherwhat;

                            String created_at = String.valueOf(livehoodData.get(i).getCreatedAt());
                            String created_at1 = (created_at.equals("null")) ? value : created_at;

                            String updated_at = String.valueOf(livehoodData.get(i).getUpdatedAt());
                            String updated_at1 = (updated_at.equals("null")) ? value : updated_at;


                            //String createdat = String.valueOf(eductionDemos.get(i).getCreatedAt());
                            //String updatedat = String.valueOf(eductionDemos.get(i).getCreatedAt());


                            JSONObject jsonObject = new JSONObject();

                            try {

                                //jsonObject.put("id",id);
                                jsonObject.put("benificiary_id", benificiaryid1);
                                jsonObject.put("user_id", userId);
                                jsonObject.put("livelihood", livelihood1);
                                jsonObject.put("livelihoodother", livelihoodother1);
                                jsonObject.put("skilldev", skilldev1);
                                jsonObject.put("skilldevwhen", skilldevwhen1);
                                jsonObject.put("skilldevwhere", skilldevwhere1);
                                jsonObject.put("skilldevwhat", skilldevwhat1);
                                jsonObject.put("finservice", finservice1);
                                jsonObject.put("finservicewhen", finservicewhen1);
                                jsonObject.put("finservicewhat", finservicewhat1);
                                jsonObject.put("finservicewhere", finservicewhere1);
                                jsonObject.put("socialsecurity", socialsecurity1);
                                jsonObject.put("socialsecuritywhen", socialsecuritywhen1);
                                jsonObject.put("socialsecuritywhere", socialsecuritywhere1);
                                jsonObject.put("socialsecuritywhat", socialsecuritywhat1);
                                jsonObject.put("vocationaltraining", vocationaltraining1);
                                jsonObject.put("vocationaltraining_when", vocationaltraining_when1);
                                jsonObject.put("vocationaltraining_what", vocationaltraining_what1);
                                jsonObject.put("vocationaltraining_where", vocationaltraining_where1);
                                jsonObject.put("serviceother", serviceother1);
                                jsonObject.put("serviceotherwhen", serviceotherwhen1);
                                jsonObject.put("serviceotherwhere", serviceotherwhere1);
                                jsonObject.put("serviceotherwhat", serviceotherwhat1);
                                jsonObject.put("created_at", created_at1);
                                jsonObject.put("updated_at", Datetime);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            jsonArray4.put(jsonObject);

                        }

                    }
                    Log.d("gjusgjhbs", jsonArray4.toString());

                    storeAllLivelihoodService(jsonArray4);
                }

            }
        });
    }

    public void storeAllLivelihoodService(JSONArray jsonArray) {

        String url = "https://midev.zbapps.in/api/uploadLivelihood";

        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equals("true")) {

                        // Toast.makeText(SyncActivityAll.this, "Data Sync Successful", Toast.LENGTH_SHORT).show();

                    } else {

                       // Toast.makeText(SyncActivityAll.this, "Data not Retrive", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SyncActivityAll.this, "Livelihood " + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                String creds = String.format("%s:%s", "mobility", "admin@123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);

                return params;
            }
        };
        myJsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SyncActivityAll.this);
        requestQueue.add(myJsonArrayRequest);

    }

    public void getAllSocialServicesList(){

        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", userId);
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.listSocialService(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<Social_Example>() {
            @Override
            public void onResponse(Call<Social_Example> call, retrofit2.Response<Social_Example> response) {

            //    Log.d("TAG", "Eductionresponse" + response.body().getData().toString());
            //    Log.d("TAG", "onResponse response Eduction:" + response.body().toString());

                if (response.body() != null) {

                    SocialData socialData = new SocialData();
                    localRepo.deleteSocial(socialData);
                    localRepo.deleteSocial();

                    for(DataDemo_Social data : response.body().getData()){

                        localRepo.insertSocialData(data.getSocialdata());

                        Log.d("hdbgkbnkj",data.getSocialdata().toString());

                    }
                }

            }

            @Override
            public void onFailure(Call<Social_Example> call, Throwable t) {

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void storeSocialData() {

        localRepo.getSocialList().observe(SyncActivityAll.this, new Observer<List<SocialData>>() {
            @Override
            public void onChanged(List<SocialData> socialData) {

                if (socialData.size() == 0) {

                    //Toast.makeText(SyncActivityAll.this, "Data sync Successful", Toast.LENGTH_SHORT).show();


                } else {

                    String value = "";

                    for (int i = 0; i < socialData.size(); i++) {

                        String flag = String.valueOf(socialData.get(i).getFlag());
                        String flag1 = (flag.equals("null")) ? value : flag;

                        if (flag1.equals("update")) {

                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String Datetime = sdf.format(c.getTime());


                            String benificiaryid = String.valueOf(socialData.get(i).getBenificiaryId());
                            String benificiaryid1 = (benificiaryid.equals("null")) ? value : benificiaryid;

                            //String userid = eductionDemos.get(i).getUser_id();

                            String socialsports = String.valueOf(socialData.get(i).getSocialsports());
                            String socialsports1 = (socialsports.equals("null")) ? value : socialsports;

                            String socialsports_mention = String.valueOf(socialData.get(i).getSocialsports());
                            String socialsports_mention1 = (socialsports_mention.equals("null")) ? value : socialsports_mention;

                            String socialgovt = String.valueOf(socialData.get(i).getSocialgovt());
                            String socialgovt1 = (socialgovt.equals("null")) ? value : socialgovt;

                            String socialgovtwhat = String.valueOf(socialData.get(i).getSocialgovt());
                            String socialgovtwhat1 = (socialgovtwhat.equals("null")) ? value : socialgovtwhat;

                            String socialtraining = String.valueOf(socialData.get(i).getSocialtraining());
                            String socialtraining1 = (socialtraining.equals("null")) ? value : socialtraining;


                            String created_at = String.valueOf(socialData.get(i).getCreatedAt());
                            String created_at1 = (created_at.equals("null")) ? value : created_at;

                            String created_by = String.valueOf(socialData.get(i).getCreatedAt());
                            String created_by1 = (created_by.equals("null")) ? value : created_by;

                            socialtrainingwhat = socialData.get(i).getSocialtrainingwhat();
                            socialtrainingwhere = socialData.get(i).getSocialtrainingwhere();


                            //String createdat = String.valueOf(eductionDemos.get(i).getCreatedAt());
                            //String updatedat = String.valueOf(eductionDemos.get(i).getCreatedAt());


                            JSONObject jsonObject = new JSONObject();

                            try {

                                //jsonObject.put("id",id);
                                jsonObject.put("benificiary_id", benificiaryid1);
                                jsonObject.put("user_id", userId);
                                jsonObject.put("socialsports", socialsports1);
                                jsonObject.put("socialsports_mention", socialsports_mention1);
                                jsonObject.put("socialgovt", socialgovt1);
                                jsonObject.put("socialgovtwhat", socialgovtwhat1);
                                jsonObject.put("socialtraining", socialtraining1);
                                jsonObject.put("socialtrainingwhat", socialtrainingwhat);
                                jsonObject.put("socialtrainingwhere", socialtrainingwhere);
                                jsonObject.put("created_at", created_at1);
                                jsonObject.put("updated_at", Datetime);
                                jsonObject.put("created_by", created_by1);
                                jsonObject.put("updated_by", userId);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            jsonArray5.put(jsonObject);

                        }

                    }

                    Log.d("gjusgjhbs", jsonArray5.toString());

                    storeAllSocialServices(jsonArray5);
                }

            }
        });

    }

    public void storeAllSocialServices(JSONArray jsonArray) {

        String url = "https://midev.zbapps.in/api/uploadSocial";

        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equals("true")) {

                        // Toast.makeText(SyncActivityAll.this, "Data Sync Successful", Toast.LENGTH_SHORT).show();

                    } else {

                      //  Toast.makeText(SyncActivityAll.this, "Data not Retrive", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SyncActivityAll.this, "Social :" + error, Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                String creds = String.format("%s:%s", "mobility", "admin@123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);

                return params;
            }
        };
        myJsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SyncActivityAll.this);
        requestQueue.add(myJsonArrayRequest);

    }

    public void StoreDataDataBase() {

        localRepo.getActivityRepList().observe(SyncActivityAll.this, new Observer<List<ActivityReportAttendanceData>>() {
            @Override
            public void onChanged(List<ActivityReportAttendanceData> activityReportAttendanceData) {

                int size = activityReportAttendanceData.size();

                //Toast.makeText(MonthWorkPlanActivity.this, ""+size, Toast.LENGTH_SHORT).show();

                if (activityReportAttendanceData.size() == 0) {

                   // Toast.makeText(SyncActivityAll.this, "No", Toast.LENGTH_SHORT).show();

                } else {

                    for (int i = 0; i < activityReportAttendanceData.size(); i++) {

                        String dateStr = activityReportAttendanceData.get(i).getDateAttendance();
                        String userID = activityReportAttendanceData.get(i).getUserId();
                        String ID = activityReportAttendanceData.get(i).getId();
                        String attendance = activityReportAttendanceData.get(i).getAttendance();
                        String firstHalf = activityReportAttendanceData.get(i).getFirstHalf();
                        String secondHalf = activityReportAttendanceData.get(i).getSecondHalf();

                        System.out.println("kjhiubdkjsbh  " + dateStr + userID + ID + attendance + firstHalf + secondHalf);
                        //String MonthStr = Utils.customMonthYear(dateStr);

                        Log.d("hwghvhfd", dateStr + userID + ID + attendance + firstHalf + secondHalf);

                        JSONObject jsonObject = new JSONObject();

                        try {

                            jsonObject.put("id", ID);
                            jsonObject.put("user_id", userID);
                            jsonObject.put("date_attendance", dateStr);
                            jsonObject.put("attendance", attendance);
                            jsonObject.put("first_half", firstHalf);
                            jsonObject.put("second_half", secondHalf);

                        } catch (Exception e) {

                            e.printStackTrace();
                        }

                        jsonArrayatt.put(jsonObject);
                        //arrayList.add(ID);


                    }

                    Log.d("hgfhb", jsonArrayatt.toString());

                    updateAttendance2(jsonArrayatt);

                    Log.d("gshhdjhd", jsonArrayatt.toString());
                }

            }
        });

    }

    public void updateAttendance2(JSONArray jsonArray) {

        ProgressDialog pd = new ProgressDialog(SyncActivityAll.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        String url = "https://midev.zbapps.in/api/activityreport/update_attendance";

        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equals("true")) {

                        //Toast.makeText(SyncActivityAll.this, "Data Save Successfully", Toast.LENGTH_SHORT).show();

                        //dialog.dismiss();

                        //retriveAttendDetails(userID, monthyear);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(SyncActivityAll.this, "" + error, Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                String creds = String.format("%s:%s", "mobility", "admin@123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);

                return params;
            }

        };

        myJsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SyncActivityAll.this);
        requestQueue.add(myJsonArrayRequest);
    }

    //Helth DevicesList
    public void callHealthDeviceList() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getHealthDevices(CommonClass.APP_TOKEN).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, retrofit2.Response<JsonObject> response) {
                Log.d("TAG", "onResponseresponse:: " + response.body());

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        //Snackbar.make(binding.relativeLayout,response.body().toString(),Snackbar.LENGTH_LONG).setTextColor(Color.BLUE).show();

                        getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("masterdevices", response.body().toString()).commit();


                    }

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
                //isprogress.setValue(10);
            }
        });
    }

    public void getHealthLocal(){

        localRepo.getHealthCarenList().observe(this, new Observer<List<HealthCareData>>() {
            @Override
            public void onChanged(List<HealthCareData> healthCareData) {

                String value = "";

                if (healthCareData.size() != 0) {

                    for (int i = 0; i < healthCareData.size(); i++) {

                        String flag = String.valueOf(healthCareData.get(i).getFlag());
                        String flag1 = (flag.equals("null")) ? value : flag;

                        if (flag1.equals("update")) {

                            Calendar c = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String Datetime = sdf.format(c.getTime());


                            String benificiaryid = String.valueOf(healthCareData.get(i).getBenificiaryId());
                            String benificiaryid1 = (benificiaryid.equals("null")) ? value : benificiaryid;

                            String servicedone = String.valueOf(healthCareData.get(i).getServiceDone());
                            String servicedone1 = (servicedone.equals("null")) ? value : servicedone;

                            String[] strArray = null;

                            if(servicedone1.length() == 1){

                                serviceArray.add(servicedone1);

                            }else{

                                strArray = servicedone1.split(",");
                                for (int j = 0; j<strArray.length; i++){
                                    System.out.println(strArray[i]);
                                }
                                serviceArray = Arrays.asList(strArray);

                            }

                            String screeningdate = String.valueOf(healthCareData.get(i).getScreeningdate());
                            String screeningdate1 = (screeningdate.equals("null")) ? value : screeningdate;

                            String assessmentdate = String.valueOf(healthCareData.get(i).getAssessmentdate());
                            String assessmentdate1 = (assessmentdate.equals("null")) ? value : assessmentdate;

                            String assessmentwho = String.valueOf(healthCareData.get(i).getAssessmentwho());
                            String assessmentwho1 = (assessmentwho.equals("null")) ? value : assessmentwho;

                            String assessmentwhere = String.valueOf(healthCareData.get(i).getAssessmentwhere());
                            String assessmentwhere1 = (assessmentwhere.equals("null")) ? value : assessmentwhere;

                            String referral = String.valueOf(healthCareData.get(i).getReferral());
                            String referral1 = (referral.equals("null")) ? value : referral;

                            String referralplace = String.valueOf(healthCareData.get(i).getReferralplace());
                            String referralplace1 = (referralplace.equals("null")) ? value : referralplace;

                            String referralprescription = String.valueOf(healthCareData.get(i).getReferralprescription());
                            String referralprescription1 = (referralprescription.equals("null")) ? value : referralprescription;

                            String trialwhat = String.valueOf(healthCareData.get(i).getTrialwhat());
                            String trialwhat1 = (trialwhat.equals("null")) ? value : trialwhat;

                            String trialdate = String.valueOf(healthCareData.get(i).getTrialdate());
                            String trialdate1 = (trialdate.equals("null")) ? value : trialdate;

                            String gaitfrequency = String.valueOf(healthCareData.get(i).getFluency());
                            String gaitfrequency1 = (gaitfrequency.equals("null")) ? value : gaitfrequency;

                            String gaithowmany = String.valueOf(healthCareData.get(i).getGaithowmany());
                            String gaithowmany1 = (gaithowmany.equals("null")) ? value : gaithowmany;

                            String therapyfrequency = String.valueOf(healthCareData.get(i).getTherapyfrequency());
                            String therapyfrequency1 = (therapyfrequency.equals("null")) ? value : therapyfrequency;

                            String therapysessions = String.valueOf(healthCareData.get(i).getTherapysessions());
                            String therapysessions1 = (therapysessions.equals("null")) ? value : therapysessions;

                            String fitmentwho = String.valueOf(healthCareData.get(i).getFitmentwho());
                            String fitmentwho1 = (fitmentwho.equals("null")) ? value : fitmentwho;

                            String fitmentwhere = String.valueOf(healthCareData.get(i).getFitmentwhere());
                            String fitmentwhere1 = (fitmentwhere.equals("null")) ? value : fitmentwhere;

                            String fitmentdevice = String.valueOf(healthCareData.get(i).getFitmentdevice());
                            String fitmentdevice1 = (fitmentdevice.equals("null")) ? value : fitmentdevice;

                            String followupfrequency = String.valueOf(healthCareData.get(i).getFollowupfrequency());
                            String followupfrequency1 = (followupfrequency.equals("null")) ? value : followupfrequency;

                            String followupsheet = String.valueOf(healthCareData.get(i).getFollowupsheet());
                            String followupsheet1 = (followupsheet.equals("null")) ? value : followupsheet;

                            String surgery = String.valueOf(healthCareData.get(i).getSurgery());
                            String surgery1 = (surgery.equals("null")) ? value : surgery;

                            String surgerywhere = String.valueOf(healthCareData.get(i).getSurgerywhere());
                            String surgerywhere1 = (surgerywhere.equals("null")) ? value : surgerywhere;

                            String surgerywherewhat = String.valueOf(healthCareData.get(i).getSurgerywherewhat());
                            String surgerywherewhat1 = (surgerywherewhat.equals("null")) ? value : surgerywherewhat;

                            String homerecommend = String.valueOf(healthCareData.get(i).getHomerecommend());
                            String homerecommend1 = (homerecommend.equals("null")) ? value : homerecommend;

                            String homerecommendwhat = String.valueOf(healthCareData.get(i).getHomerecommendwhat());
                            String homerecommendwhat1 = (homerecommendwhat.equals("null")) ? value : homerecommendwhat;

                            String ihp = String.valueOf(healthCareData.get(i).getIhp());
                            String ihp1 = (ihp.equals("null")) ? value : ihp;

                            List<String> ihp_doc1 =  healthCareData.get(i).getIhpdocs();

                            String createdat = String.valueOf(healthCareData.get(i).getCreatedAt());
                            String createdat1 = (createdat.equals("null")) ? value : createdat;

                            String updatedat = String.valueOf(healthCareData.get(i).getUpdatedAt());
                            String updatedat1 = (updatedat.equals("null")) ? value : updatedat;

                            String createdby = String.valueOf(healthCareData.get(i).getCreatedBy());
                            String createdby1 = (createdby.equals("null")) ? value : createdby;

                            String updatedby = String.valueOf(healthCareData.get(i).getUpdatedBy());
                            String updatedby1 = (updatedby.equals("null")) ? value : updatedby;

                            String speechlangdev = String.valueOf(healthCareData.get(i).getSpeechLangDev());
                            String speechlangdev1 = (speechlangdev.equals("null")) ? value : speechlangdev;

                            String opmedd = String.valueOf(healthCareData.get(i).getOpmeDd());
                            String opmedd1 = (opmedd.equals("null")) ? value : opmedd;

                            String ifabnormal = String.valueOf(healthCareData.get(i).getIfAbnormal());
                            String ifabnormal1 = (ifabnormal.equals("null")) ? value : ifabnormal;

                            String speecharticulation = String.valueOf(healthCareData.get(i).getSpeechArticulation());
                            String speecharticulation1 = (speecharticulation.equals("null")) ? value : speecharticulation;

                            String ifmisarticulation = String.valueOf(healthCareData.get(i).getIfMisarticulation());
                            String ifmisarticulation1 = (ifmisarticulation.equals("null")) ? value : ifmisarticulation;

                            String fluency = String.valueOf(healthCareData.get(i).getFluency());
                            String fluency1 = (fluency.equals("null")) ? value : fluency;

                            String voicequality = String.valueOf(healthCareData.get(i).getVoiceQuality());
                            String voicequality1 = (voicequality.equals("null")) ? value : voicequality;

                            String voicepitch = String.valueOf(healthCareData.get(i).getVoicePitch());
                            String voicepitch1 = (voicepitch.equals("null")) ? value : voicepitch;

                            String voiceloudness = String.valueOf(healthCareData.get(i).getVoiceLoudness());
                            String voiceloudness1 = (voiceloudness.equals("null")) ? value : voiceloudness;

                            String modecommunication = String.valueOf(healthCareData.get(i).getModeCommunication());
                            String modecommunication1 = (modecommunication.equals("null")) ? value : modecommunication;

                            String languagecomprehension = String.valueOf(healthCareData.get(i).getLanguageComprehension());
                            String languagecomprehension1 = (languagecomprehension.equals("null")) ? value : languagecomprehension;

                            String languageexpression = String.valueOf(healthCareData.get(i).getLanguageExpression());
                            String languageexpression1 = (languageexpression.equals("null")) ? value : languageexpression;

                            String testresult = String.valueOf(healthCareData.get(i).getTestResult());
                            String testresult1 = (testresult.equals("null")) ? value : testresult;

                            String rightotoscopy = String.valueOf(healthCareData.get(i).getRightOtoscopy());
                            String rightotoscopy1 = (rightotoscopy.equals("null")) ? value : rightotoscopy;

                            String leftotoscopy = String.valueOf(healthCareData.get(i).getLeftOtoscopy());
                            String leftotoscopy1 = (leftotoscopy.equals("null")) ? value : leftotoscopy;

                            String puretoneaudioright = String.valueOf(healthCareData.get(i).getPureToneAudioRight());
                            String puretoneaudioright1 = (puretoneaudioright.equals("null")) ? value : puretoneaudioright;

                            String puretoneaudioleft = String.valueOf(healthCareData.get(i).getPureToneAudioLeft());
                            String puretoneaudioleft1 = (puretoneaudioleft.equals("null")) ? value : puretoneaudioleft;

                            String speechaudiotest = String.valueOf(healthCareData.get(i).getSpeechAudioTest());
                            String speechaudiotest1 = (speechaudiotest.equals("null")) ? value : speechaudiotest;

                            String eardischarge = String.valueOf(healthCareData.get(i).getEarDischarge());
                            String eardischarge1 = (eardischarge.equals("null")) ? value : eardischarge;

                            String lingsound = String.valueOf(healthCareData.get(i).getLingSound());
                            String lingsound1 = (lingsound.equals("null")) ? value : lingsound;

                            String hearingmodelright = String.valueOf(healthCareData.get(i).getHearingModelRight());
                            String hearingmodelright1 = (hearingmodelright.equals("null")) ? value : hearingmodelright;

                            String hearingmodelleft = String.valueOf(healthCareData.get(i).getHearingModelLeft());
                            String hearingmodelleft1 = (hearingmodelleft.equals("null")) ? value : hearingmodelleft;

                            String earmouldright = String.valueOf(healthCareData.get(i).getEarMouldRight());
                            String earmouldright1 = (earmouldright.equals("null")) ? value : earmouldright;

                            String earmouldleft = String.valueOf(healthCareData.get(i).getEarMouldLeft());
                            String earmouldleft1 = (earmouldleft.equals("null")) ? value : earmouldleft;

                            String specialistsremark = String.valueOf(healthCareData.get(i).getSpecialistsRemark());
                            String specialistsremark1 = (specialistsremark.equals("null")) ? value : specialistsremark;



                            JSONObject jsonObject = new JSONObject();

                            try {

                                jsonObject.put("user_id", userId);
                                jsonObject.put("benificiary_id", benificiaryid1);
                                jsonObject.put("service_done", serviceArray);
                                jsonObject.put("screeningdate", screeningdate1);
                                jsonObject.put("assessmentdate", assessmentdate1);
                                jsonObject.put("assessmentwho", assessmentwho1);
                                jsonObject.put("assessmentwhere", assessmentwhere1);
                                jsonObject.put("referral", referral1);
                                jsonObject.put("referralplace", referralplace1);
                                jsonObject.put("referralprescription", referralprescription1);
                                jsonObject.put("trialwhat", trialwhat1);
                                jsonObject.put("trialdate", trialdate1);
                                jsonObject.put("gaitfrequency", gaitfrequency1);
                                jsonObject.put("gaithowmany", gaithowmany1);
                                jsonObject.put("therapyfrequency", therapyfrequency1);
                                jsonObject.put("therapysessions", therapysessions1);
                                jsonObject.put("fitmentwho", fitmentwho1);
                                jsonObject.put("fitmentwhere", fitmentwhere1);
                                jsonObject.put("fitmentdevice", fitmentdevice1);
                                jsonObject.put("followupfrequency", followupfrequency1);
                                jsonObject.put("followupsheet", followupsheet1);
                                jsonObject.put("surgery", surgery1);
                                jsonObject.put("surgerywhere", surgerywhere1);
                                jsonObject.put("surgerywherewhat", surgerywherewhat1);
                                jsonObject.put("homerecommend", homerecommend1);
                                jsonObject.put("homerecommendwhat", homerecommendwhat1);
                                jsonObject.put("ihp", ihp1);
                                jsonObject.put("ihp_doc", ihp_doc1);
                                jsonObject.put("created_at", createdat1);
                                jsonObject.put("updated_at", updatedat1);
                                jsonObject.put("created_by", createdby1);
                                jsonObject.put("updated_by", updatedby1);
                                jsonObject.put("speech_lang_dev", speechlangdev1);
                                jsonObject.put("opme_dd", opmedd1);
                                jsonObject.put("if_abnormal", ifabnormal1);
                                jsonObject.put("speech_articulation", speecharticulation1);
                                jsonObject.put("if_misarticulation", ifmisarticulation1);
                                jsonObject.put("fluency", fluency1);
                                jsonObject.put("voice_quality", voicequality1);
                                jsonObject.put("voice_pitch", voicepitch1);
                                jsonObject.put("voice_loudness", voiceloudness1);
                                jsonObject.put("mode_communication", modecommunication1);
                                jsonObject.put("language_comprehension", languagecomprehension1);
                                jsonObject.put("language_expression", languageexpression1);
                                jsonObject.put("test_result", testresult1);
                                jsonObject.put("right_otoscopy", rightotoscopy1);
                                jsonObject.put("left_otoscopy", leftotoscopy1);
                                jsonObject.put("pure_tone_audio_right", puretoneaudioright1);
                                jsonObject.put("pure_tone_audio_left", puretoneaudioleft1);
                                jsonObject.put("speech_audio_test", speechaudiotest1);
                                jsonObject.put("ear_discharge", eardischarge1);
                                jsonObject.put("ling_sound", lingsound1);
                                jsonObject.put("hearing_model_right", hearingmodelright1);
                                jsonObject.put("hearing_model_left", hearingmodelleft1);
                                jsonObject.put("ear_mould_right", earmouldright1);
                                jsonObject.put("ear_mould_left", earmouldleft1);
                                jsonObject.put("specialists_remark", specialistsremark1);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            jsonArray6.put(jsonObject);

                        }
                    }

                    Log.d("gjusgjhbs", jsonArray6.toString());

                    storeAllHealthService(jsonArray6);

                } else {

                    //Toast.makeText(SyncActivityAll.this, "Data Sync SuccessFully", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void storeAllHealthService(JSONArray jsonArray) {

        String url = "https://mimis.zbapps.in/api/uploadService";

       MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {

               try {

                   String status = response.getString("status");
                   String message = response.getString("message");

                   if (status.equals("true")) {

                       // Toast.makeText(SyncActivityAll.this, "Data Sync Successful", Toast.LENGTH_SHORT).show();

                   } else {

                       // Toast.makeText(SyncActivityAll.this, "Data not Retrive", Toast.LENGTH_SHORT).show();
                   }

               } catch (JSONException e) {
                   e.printStackTrace();
               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

               Toast.makeText(SyncActivityAll.this, "Health : "+error, Toast.LENGTH_SHORT).show();
           }
       }){

           @Override
           public Map<String, String> getHeaders() throws AuthFailureError {
               Map<String, String> params = new HashMap<String, String>();

               String creds = String.format("%s:%s", "mobility", "admin@123");
               String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

               params.put("Content-Type", "application/json");
               params.put("Authorization", auth);

               return params;
           }
       };
        myJsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SyncActivityAll.this);
        requestQueue.add(myJsonArrayRequest);

    }

    public void storeAllData() {

        if (str_button.equals("Upload")) {

            if (Utils.isNetworkAvailable(SyncActivityAll.this)) {

                getAttendanceReportLocalDataBase();
                storeActionPlan();
                StoreDataDataBase();
                //State MAster
                callStateMasterData();
                getBeneficuaryLocal();
                getAllEductionDeatils();
                getAllLivelihoodService();
                storeSocialData();
                //getHealthLocal();

                binding.dataupdwn.setText("Download");

            } else {

                Toast.makeText(SyncActivityAll.this, "Internet is not available", Toast.LENGTH_SHORT).show();
            }

        } else {

            if (Utils.isNetworkAvailable(SyncActivityAll.this)) {

                binding.btnProceed.setVisibility(View.VISIBLE);

                clockInOut(userId);
                callActionPlanData(userId);
                callActivityPlanData(userId);
                callActivityPlanData1(userId);
                callCasteMasterData();
                callEconomicMasterData();
                callAnnualIncomeMasterData();
                callMaritialStatusMasterData();
                callEducationMasterData();
                callOcupationMasterData();
                callTypeDisabilityMasterData();
                callPurposeVisitDisabilityMasterData();
                callReligionMasterData();
                callHoboliMasterData();
                callDisticMasterData();
                callBlockMasterData();
                callGPMasterData();
                callVillageMasterData();
                callBeneFiciaryData();
                getEductionDetails();
                callSocialTranning();
                callHealthActivity();
                getdataHealthActivity();
                getLivelihoodService();
                CallMasterOccuption();
                getAllSocialServicesList();
                callHealthDeviceList();

                binding.dataupdwn.setText("Upload");


            } else {

                Toast.makeText(SyncActivityAll.this, "Internet Is not nvailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void storeAllData1() {

        if (str_button.equals("Upload")) {

            if (Utils.isNetworkAvailable(SyncActivityAll.this)) {

              /*  getAttendanceReportLocalDataBase();
                storeActionPlan();
                //StoreDataDataBase();
                //State MAster
                callStateMasterData();
                getBeneficuaryLocal();
                getAllEductionDeatils();
                getAllLivelihoodService();*/

                binding.dataupdwn.setText("Download");

            } else {

                Toast.makeText(SyncActivityAll.this, "Internet is not available", Toast.LENGTH_SHORT).show();
            }

        } else {

            if (Utils.isNetworkAvailable(SyncActivityAll.this)) {

                binding.btnProceed.setVisibility(View.VISIBLE);

                clockInOut(userId);
                callActionPlanData(userId);
                callActivityPlanData(userId);
                callActivityPlanData1(userId);
                callCasteMasterData();
                callEconomicMasterData();
                callAnnualIncomeMasterData();
                callMaritialStatusMasterData();
                callEducationMasterData();
                callOcupationMasterData();
                callTypeDisabilityMasterData();
                callPurposeVisitDisabilityMasterData();
                callReligionMasterData();
                callHoboliMasterData();
                callDisticMasterData();
                callBlockMasterData();
                callGPMasterData();
                callVillageMasterData();
                callBeneFiciaryData();
                getEductionDetails();
                callSocialTranning();
                callHealthActivity();
                getdataHealthActivity();
                getLivelihoodService();
                CallMasterOccuption();
                getAllSocialServicesList();
                callHealthDeviceList();

                binding.dataupdwn.setText("Upload");


            } else {

                Toast.makeText(SyncActivityAll.this, "Internet Is not nvailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

}