package com.example.mobilityindia.syn1.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.attendance.database.AttendanceClass;
import com.example.mobilityindia.attendance.database.RoomDB;
import com.example.mobilityindia.beneficarylist.beneficaryresponce.BeneficaryResponse;
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
import com.example.mobilityindia.syn1.view.allresponse.distic.Example_Distic;
import com.example.mobilityindia.syn1.view.allresponse.gp.Datum_Gp;
import com.example.mobilityindia.syn1.view.allresponse.gp.Example_Gp;
import com.example.mobilityindia.syn1.view.allresponse.village.Datum_Villaga;
import com.example.mobilityindia.syn1.view.allresponse.village.Example_Village;
import com.example.mobilityindia.sync.model.ActionPlanData;
import com.example.mobilityindia.sync.model.ActionPlanMonth;
import com.example.mobilityindia.sync.model.ActivityReportAttendanceData;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.model.BlockData;
import com.example.mobilityindia.sync.model.DistData;
import com.example.mobilityindia.sync.model.GPData;
import com.example.mobilityindia.sync.model.VillageData;
import com.example.mobilityindia.sync.model.WorkPlanData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.utils.Utils;
import com.example.mobilityindia.workplan.MyJsonArrayRequest;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;

public class SyncActivityAll extends AppCompatActivity {

    ActivitySyncAllBinding binding;
    SessinoManager sessinoManager;
    String userId;
    RoomDB localdataBase;
    JSONArray jsonArray_attendance;
    AttendanceClass attendanceClass;
    JSONArray jsonArray = new JSONArray();
    JSONArray jsonArray1 = new JSONArray();
    int progress = 0;
    LocalRepo localRepo;
    private ApiRequest apiRequest;
    ActionPlanData actionPlanData;
    ActionPlanMonth actionPlanMonth;

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

        sessinoManager = new SessinoManager(SyncActivityAll.this);
        userId = sessinoManager.getUSERID();
        //Initialize database
        localdataBase = RoomDB.getInstance(this);
        localRepo = new LocalRepo(SyncActivityAll.this);
        // setProgressValue(progress);

        binding.btnProceed.setVisibility(View.GONE);

        if (Utils.isNetworkAvailable(SyncActivityAll.this)) {

            getAttendanceReportLocalDataBase();
            storeActionPlan();
            // State MAster
            callStateMasterData();
            getBeneficuaryLocal();

        } else {

            Toast.makeText(this, "Internet is not avilable", Toast.LENGTH_SHORT).show();
        }

        binding.serviceDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // clockInOut(userId);

                if (Utils.isNetworkAvailable(SyncActivityAll.this)) {

                    binding.btnProceed.setVisibility(View.VISIBLE);

                    clockInOut(userId);
                    callActionPlanData(userId);
                    //callActivityPlanData(userId);
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

                    /*localRepo.deleteAllAttendance();

                    attendanceClass = new AttendanceClass();
                    localRepo.DeleteAttendance(attendanceClass);*/

                } else {

                    Toast.makeText(SyncActivityAll.this, "Internet Is Not Avilable", Toast.LENGTH_SHORT).show();
                }

                //binding.btnProceed.setVisibility(View.VISIBLE);
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

                    Toast.makeText(SyncActivityAll.this, "Data sync Successful", Toast.LENGTH_SHORT).show();

                    //binding.btnProceed.setVisibility(View.VISIBLE);

                } else {

                    for (int i = 0; i < attendanceClasses.size(); i++) {

                        int id = attendanceClasses.get(i).getId();
                        Double clockinlang = attendanceClasses.get(i).getClockin_long();
                        Double getClockinlat = attendanceClasses.get(i).getClockin_lat();
                        Double clockout_long = attendanceClasses.get(i).getClockout_long();
                        Double clockout_lat = attendanceClasses.get(i).getClockout_lat();
                        String clocktime = attendanceClasses.get(i).getClockin();
                        String clockouttime = attendanceClasses.get(i).getClockout();
                        String dateattendance = attendanceClasses.get(i).getDate_attendance();
                        String city = attendanceClasses.get(i).getCity();


                        JSONObject jsonObject = new JSONObject();

                        try {

                            //jsonObject.put("id",id);
                            jsonObject.put("user_id", userId);
                            jsonObject.put("date", dateattendance);
                            jsonObject.put("checkin_time", clocktime);
                            jsonObject.put("clockout_time", clockouttime);
                            jsonObject.put("clockin_lat", getClockinlat);
                            jsonObject.put("clockin_long", clockinlang);
                            jsonObject.put("clockout_lat", clockout_lat);
                            jsonObject.put("clockout_long", clockout_long);
                            jsonObject.put("city", city);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        jsonArray.put(jsonObject);


                    }
                    Log.d("gjusgjhbs", jsonArray.toString());

                    storeAttendanceInDataBase(jsonArray);

                }

            }
        });
    }

    public void storeAttendanceInDataBase(JSONArray jsonArray) {

        ProgressDialog progressDialog = new ProgressDialog(SyncActivityAll.this);
        progressDialog.setMessage("Data sync please Wait...");
        progressDialog.show();

        String url = "https://mimis.zbapps.in/api/uploadClockin";

        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equals("true")) {

                        Toast.makeText(SyncActivityAll.this, "Data Sync Successful", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(SyncActivityAll.this, "Data not Retrive", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                Toast.makeText(SyncActivityAll.this, "" + error, Toast.LENGTH_SHORT).show();

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

        String url = "https://mimis.zbapps.in/api/clockInList";

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("user_id", userId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //setProgressValue(progress);
                progressDialog.dismiss();

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");
                    String data = response.getString("data");

                    if (status.equals("true")) {

                        attendanceClass = new AttendanceClass();

                        localRepo.deleteAttendance(attendanceClass);
                        localRepo.deleteAllAttendance();

                        JSONArray jsonArray_data = new JSONArray(data);

                        Toast.makeText(SyncActivityAll.this, "Data Sync Successful", Toast.LENGTH_SHORT).show();

                        //localdataBase.attendanceDao().deleteAll();

                        if (jsonArray_data.length() != 0) {

                            for (int i = 0; i < jsonArray_data.length(); i++) {

                                JSONObject jsonObject1_data = jsonArray_data.getJSONObject(i);

                                String id = jsonObject1_data.getString("id");
                                String user_id = jsonObject1_data.getString("user_id");
                                String date = jsonObject1_data.getString("date");
                                String checkin_time = jsonObject1_data.getString("checkin_time");
                                String clockout_time = jsonObject1_data.getString("clockout_time");
                               /* String clockin_lat = jsonObject1_data.getString("clockin_lat");
                                String clockin_long = jsonObject1_data.getString("clockin_long");
                                String clockout_lat = jsonObject1_data.getString("clockin_long");
                                String clockout_long = jsonObject1_data.getString("clockout_long");
                                String city = jsonObject1_data.getString("clockout_long");*/

                                /*Double dclockin_lat = Double.valueOf(clockin_lat);
                                Double dclockin_long = Double.valueOf(clockin_long);
                                Double dclockout_lat = Double.valueOf(clockout_lat);
                                Double dclockout_long = Double.valueOf(clockout_long);
*/
                                attendanceClass.setUser_id(user_id);
                                attendanceClass.setDate_attendance(date);
                                attendanceClass.setClockin(checkin_time);
                                attendanceClass.setClockout(clockout_time);
                               /* attendanceClass.setClockin_lat(dclockin_lat);
                                attendanceClass.setClockin_long(dclockin_long);
                                attendanceClass.setClockout_lat(dclockout_lat);
                                attendanceClass.setClockout_long(dclockout_long);
                                attendanceClass.setCity(city);*/

                                //localdataBase.attendanceDao().insertAttandance(attendanceClass);

                                localRepo.insertAttandance(attendanceClass);

                                // localRepo.appDatabase.attendanceDao().insertAttandance(data);
                            }

                            Log.d("gshvhjvud", attendanceClass.toString());
                            binding.btnProceed.setVisibility(View.VISIBLE);

                        } else {

                            Toast.makeText(SyncActivityAll.this, "Data sync Successful", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(SyncActivityAll.this, message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                Toast.makeText(SyncActivityAll.this, "Api Server error", Toast.LENGTH_SHORT).show();

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

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(SyncActivityAll.this);
        requestQueue.add(jsonObjectRequest);
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

    private void setProgressValue(final int progress) {

        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 1);
            }
        });
        thread.start();
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

                //isprogress.setValue(10);
            }
        });
    }

    public void getBeneficuaryLocal() {

        localRepo.getBeneList().observe(this, new Observer<List<BeneData>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(@Nullable List<BeneData> beneDataa) {
                if (beneDataa.size() != 0) {
                    for (int i = 0; i < beneDataa.size(); i++) {

                        LocalDate dateObj = LocalDate.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String currentdate = dateObj.format(formatter);

                        Random r = new Random();
                        String randomNumber = String.format("%04d", Integer.valueOf(r.nextInt(1001)));
                        String finalrandomnumber = "TR001" + randomNumber;

                        SessinoManager sessinoManager = new SessinoManager(SyncActivityAll.this);
                        String userId = sessinoManager.getUSERID();
                        ProgressDialog pd = new ProgressDialog(SyncActivityAll.this);
                        pd.setMessage("Loading...");
                        pd.setCancelable(false);
                        pd.show();
                        Map<String, Object> mapData = new HashMap<>();

                        mapData.put("temp_id", finalrandomnumber);
                        mapData.put("registration_date", currentdate);
                        mapData.put("name", CommonClass.shgnameee);
                        mapData.put("parent_name", CommonClass.parent);
                        mapData.put("relation", CommonClass.startdateofshgg);
                        mapData.put("dob", CommonClass.dojshg);
                        mapData.put("gender", CommonClass.gender);
                        mapData.put("caste", CommonClass.caste);
                        mapData.put("religion", CommonClass.reigion);
                        mapData.put("adhaar_no", CommonClass.adhaarno);
                        mapData.put("annual_income", CommonClass.annualincome);
                        mapData.put("economic_status", CommonClass.ecconomicstatus);
                        mapData.put("marital_status", CommonClass.meritalstatus);
                        mapData.put("education", CommonClass.edu);
                        mapData.put("occupation", CommonClass.occuption);
                        mapData.put("type_of_disability", CommonClass.typeofdisability);
                        mapData.put("type_of_sub_disability", CommonClass.typeofsubdisability);
                        mapData.put("percentage_of_disability", CommonClass.percentofdisability);
                        mapData.put("id_card_no", CommonClass.idcardno);
                        mapData.put("php_amount", CommonClass.phpammount);
                        mapData.put("type_of_beneficiary", CommonClass.typeofbenificary);
                        mapData.put("purpose_of_visit", CommonClass.purposeofvisit);
                        mapData.put("purpose_visit_details", CommonClass.purposevisitdetails);
                        mapData.put("have_bank_acc", CommonClass.havebankacc);
                        mapData.put("acc_num", CommonClass.bankname);
                        mapData.put("acc_holder_name", CommonClass.accountHolderName);
                        mapData.put("ifsc", CommonClass.ifscCode);
                        mapData.put("acc_type", CommonClass.accountType);
                        mapData.put("name_of_pwd_cwd", CommonClass.nameofpwdcwd);
                        mapData.put("village_id", CommonClass.villageID);
                        mapData.put("address", CommonClass.address);
                        mapData.put("school_anganwadi_name", CommonClass.schoolname);
                        mapData.put("contact_no", CommonClass.contactNo1);
                        mapData.put("contact_no_other", CommonClass.contactNo2);
                        mapData.put("email_id", CommonClass.email);
                        mapData.put("ration_card_no", CommonClass.rationcard);
                        mapData.put("sanitation_toilet", CommonClass.sanitation);
                        mapData.put("appliances", CommonClass.appliance);
                        mapData.put("surgery", CommonClass.surgery);
                        mapData.put("govt_facilities", CommonClass.govtfacility);
                        mapData.put("family_member", CommonClass.familymemberadultm);
                        mapData.put("family_member_adults", CommonClass.familymemberadultf);
                        mapData.put("family_member_child_m", CommonClass.fmlymemberchildrnm);
                        mapData.put("family_member_child_f", CommonClass.fmlymembrchilf);
                        mapData.put("children_undergoing_education_m", CommonClass.childrnundergeducationm);
                        mapData.put("children_undergoing_education_f", CommonClass.childrenundergoeeseducationf);
                        mapData.put("dropouts_less_14_m", CommonClass.drpuotlessthen14m);
                        mapData.put("dropouts_less_14_f", CommonClass.dropoutlessthen14f);
                        mapData.put("dropouts_14_18_m", CommonClass.dropoutm);
                        mapData.put("dropouts_14_18_f", CommonClass.dropoutf);
                        mapData.put("earning_members_fly_m", CommonClass.earingmemberflym);
                        mapData.put("earning_members_fly_f", CommonClass.earingmemberflyf);
                        mapData.put("wheather_cbo_member", CommonClass.weathershgornot);
                        mapData.put("cbo_name", CommonClass.shgname);
                        mapData.put("start_year_of_cbo", CommonClass.startdateofshg);
                        mapData.put("year_join_cbo", CommonClass.edoshg);
                        mapData.put("user_id", userId);

                        System.out.println(mapData);

                        CommonClass.APP_TOKEN = CommonClass.getToken(SyncActivityAll.this);
                        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
                        apiRequest.addbeneficary(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<BeneficaryResponse>() {
                            @Override
                            public void onResponse(Call<BeneficaryResponse> call, retrofit2.Response<BeneficaryResponse> response) {
                                Log.d("TAG", "onResponse response:: " + response.body());
                                if (response.body() != null) {
                                    /*Toast.makeText(SyncActivityAll.this,"Beneficiary Added ",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SyncActivityAll.this, BeneficaryListActivity.class)
                                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));*/
                                    pd.dismiss();
                                }
                            }

                            @Override
                            public void onFailure(Call<BeneficaryResponse> call, Throwable t) {
                                pd.dismiss();
                            }
                        });

                    }
                } else {

                    Toast.makeText(SyncActivityAll.this, "Dtaa Sync SuccessFully", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Example_ActionPlan> call, Throwable t) {
                //isprogress.setValue(10);

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void storeActionPlan() {

        localRepo.getAllActionPlanMonthList().observe(SyncActivityAll.this, new Observer<List<ActionPlanMonth>>() {
            @Override
            public void onChanged(List<ActionPlanMonth> actionPlanMonths) {

                if (actionPlanMonths.size() != 0) {

                    for (int i = 0; i < actionPlanMonths.size(); i++) {

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

        String url = "https://mimis.zbapps.in/api/updateactionplan";

        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equals("true")) {

                        Toast.makeText(SyncActivityAll.this, "Data sync Successful", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pd.dismiss();

                //Toast.makeText(SyncActivityAll.this, "Api Not Respons Properly", Toast.LENGTH_SHORT).show();
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

                    for (WorkPlanData data : response.body().getData()) {

                        localRepo.insertWorkPlan(data);
                    }

                }
            }

            @Override
            public void onFailure(Call<Example_ActivityReport> call, Throwable t) {

                // isprogress.setValue(10);
            }
        });
    }

    public void callActivityPlanData1(String UserId) {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", UserId);
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getActivityReport1(mapData).enqueue(new Callback<ExapmleActivityReport>() {
            @Override
            public void onResponse(Call<ExapmleActivityReport> call, retrofit2.Response<ExapmleActivityReport> response) {
                Log.d("TAG", "onResponse response:: " + response.body().getData());
                //isprogress.setValue(10);

                if (response.isSuccessful()) {

                    WorkPlanData workPlanData = new WorkPlanData();
                    ActivityReportAttendanceData attendance = new ActivityReportAttendanceData();

                    localRepo.DeleteWorkPlan(workPlanData);
                    localRepo.deleteWorkplan();

                    localRepo.deleteActivityRep(attendance);
                    localRepo.deleteBeneatten();

                    if (response.body() != null) {

                        for (Datum_ActivityReport data : response.body().getData()) {

                            localRepo.insertWorkPlan(data.getActivityReport());

                            for (int i = 0; i < data.getAttendances().size(); i++) {

                                localRepo.insertActivityRep(data.getAttendances().get(i));
                            }
                        }
                    }

                } else {

                    Toast.makeText(SyncActivityAll.this, "Data Retrive Not Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ExapmleActivityReport> call, Throwable t) {

                // isprogress.setValue(10);

                Toast.makeText(SyncActivityAll.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }


}