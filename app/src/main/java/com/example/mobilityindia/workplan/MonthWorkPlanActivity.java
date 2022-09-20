package com.example.mobilityindia.workplan;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.actionplan.MonthActionPlanActivity;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityMonthWorkPlanBinding;
import com.example.mobilityindia.landingpage.view.LandingPageActivity;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.syn1.view.allresponse.activityreport.Example_ActivityReport;
import com.example.mobilityindia.syn1.view.allresponse.downloadpdf.DownloadPDF;
import com.example.mobilityindia.sync.model.ActivityReportAttendanceData;
import com.example.mobilityindia.sync.model.WorkPlanData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.utils.Utils;
import com.example.mobilityindia.workplan.adapter.WorkMonthAdapter;
import com.example.mobilityindia.workplan.adapter.WorkMonthPalnAdapter;
import com.example.mobilityindia.workplan.model.DataofPaln_Model;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class MonthWorkPlanActivity extends AppCompatActivity {

    ActivityMonthWorkPlanBinding binding;
    WorkMonthAdapter monthAdapter;
    ArrayList<String> dayList = new ArrayList<String>();
    ArrayList<String> dayList2 = new ArrayList<String>();
    LocalRepo localRepo;
    String userID, monthyear, id, noOfVillageVisit, noOfGovtVisit, meetingInternal, meetingExternal, noOfTrainingAttended,
            noOfTrainingFacilated, noOfOtherEvent, monthStatues;
    WorkMonthPalnAdapter workMonthPalnAdapter;
    ArrayList<DataofPaln_Model> dateofplan;
    ArrayList<ActivityReportAttendanceData> dateofplan1;
    LinearLayoutManager linearLayoutManager;
    //JSONObject jsonObject = new JSONArray();
    JSONArray jsonArray = new JSONArray();
    JSONObject jsonObject = new JSONObject();
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList1 = new ArrayList<>();

    int size1, size2 = 0;

    Dialog dialog;

    SessinoManager sessinoManager;

    boolean connected = false;

    SwipeRefreshLayout mSwipeRefreshLayout;

    ApiRequest apiRequest;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_month_work_plan);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sessinoManager = new SessinoManager(MonthWorkPlanActivity.this);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;

        monthyear = getIntent().getStringExtra("MonthYr");
        userID = getIntent().getStringExtra("userid");
        id = getIntent().getStringExtra("id");

        monthyear = sessinoManager.getmonthYear();
        userID = sessinoManager.getUSERID();
        id = sessinoManager.getID();

        Log.d("hdgjgds", monthyear + userID + id);

        localRepo = new LocalRepo(MonthWorkPlanActivity.this);

        Log.d("hhyunbjfe", userID + "  " + monthyear);

        monthStatues = sessinoManager.getMONTHSTATUS();

        if (monthStatues.equals("0")) {

            binding.saveBtn.setVisibility(View.VISIBLE);
            binding.submitBtn.setVisibility(View.VISIBLE);

        } else if (monthStatues.equals("1")) {

            binding.saveBtn.setVisibility(View.GONE);
            binding.submitBtn.setVisibility(View.GONE);

        } else if (monthStatues.equals("2")) {

            binding.saveBtn.setVisibility(View.GONE);
            binding.submitBtn.setVisibility(View.GONE);

        } else if (monthStatues.equals("3")) {

            binding.saveBtn.setVisibility(View.VISIBLE);
            binding.submitBtn.setVisibility(View.VISIBLE);
        }


        //Toast.makeText(this, userID + "  " + monthyear, Toast.LENGTH_LONG).show();

        //Log.d("hsjhjuydgjhb",localRepo.getActivityRepList().toString());

        binding.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                localRepo.getSelectedActivityReportListMon(monthyear).observe(MonthWorkPlanActivity.this, new Observer<List<ActivityReportAttendanceData>>() {
                    @Override
                    public void onChanged(List<ActivityReportAttendanceData> activityReportAttendanceData) {

                        dateofplan1 = new ArrayList<>();

                        dateofplan1.clear();

                        for (int i = 0; i < activityReportAttendanceData.size(); i++) {

                            dateofplan1.add(activityReportAttendanceData.get(i));

                        }

                        linearLayoutManager = new LinearLayoutManager(MonthWorkPlanActivity.this, LinearLayoutManager.VERTICAL, false);
                        workMonthPalnAdapter = new WorkMonthPalnAdapter(dateofplan1, MonthWorkPlanActivity.this, arrayList, monthyear);
                        binding.dayList.setLayoutManager(linearLayoutManager);
                        binding.dayList.setHasFixedSize(true);
                        binding.dayList.setAdapter(workMonthPalnAdapter);
                        workMonthPalnAdapter.notifyDataSetChanged();

                        size1 = dateofplan1.size();
                    }
                });

                binding.mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        localRepo.getSelectedActivityReportListMon(monthyear).observe(MonthWorkPlanActivity.this, new Observer<List<ActivityReportAttendanceData>>() {
            @Override
            public void onChanged(List<ActivityReportAttendanceData> activityReportAttendanceData) {

                dateofplan1 = new ArrayList<>();

                dateofplan1.clear();

                for (int i = 0; i < activityReportAttendanceData.size(); i++) {

                    dateofplan1.add(activityReportAttendanceData.get(i));

                }

                linearLayoutManager = new LinearLayoutManager(MonthWorkPlanActivity.this, LinearLayoutManager.VERTICAL, false);
                workMonthPalnAdapter = new WorkMonthPalnAdapter(dateofplan1, MonthWorkPlanActivity.this, arrayList, monthyear);
                binding.dayList.setLayoutManager(linearLayoutManager);
                binding.dayList.setHasFixedSize(true);
                binding.dayList.setAdapter(workMonthPalnAdapter);

                size1 = dateofplan1.size();

                Log.d("gfhgvhg",String.valueOf(size1));
            }
        });

       /* localRepo.getActivityRepList().observe(MonthWorkPlanActivity.this, new Observer<List<ActivityReportAttendanceData>>() {
            @Override
            public void onChanged(List<ActivityReportAttendanceData> activityReportAttendanceData) {

                int size = activityReportAttendanceData.size();

                //Toast.makeText(MonthWorkPlanActivity.this, ""+size, Toast.LENGTH_SHORT).show();

                if (activityReportAttendanceData.size() == 0) {

                    Toast.makeText(MonthWorkPlanActivity.this, "No", Toast.LENGTH_SHORT).show();

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

                        jsonArray.put(jsonObject);

                    }

                    Log.d("hgfhb", jsonArray.toString());
                }

            }
        });*/

        /*localRepo.getSelectedActivityYrMon(userID).observe(MonthWorkPlanActivity.this, new Observer<List<ActivityReportAttendanceData>>() {
            @Override
            public void onChanged(List<ActivityReportAttendanceData> activityReportAttendanceData) {

                for (int i = 0; i < activityReportAttendanceData.size(); i++) {

                    String ID = activityReportAttendanceData.get(i).getAttendance();

                    arrayList.add(ID);

                    Log.d("hgfhdhb", arrayList.toString());

                    //Toast.makeText(MonthWorkPlanActivity.this, ""+arrayList.toString(), Toast.LENGTH_SHORT).show();

                }

                Log.d("hgfhdhb", arrayList.toString());

            }
        });*/


        //retriveAttendDetails(userID, monthyear);

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Utils.isNetworkAvailable(MonthWorkPlanActivity.this)) {

                    openDialog_Submit();

                } else {

                    Toast.makeText(MonthWorkPlanActivity.this, "Internet is not available,data save in locally", Toast.LENGTH_SHORT).show();
                }


            }
        });

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //openDialog_Save();

                if (Utils.isNetworkAvailable(MonthWorkPlanActivity.this)) {

                    StoreDataDataBase();

                } else {

                    Toast.makeText(MonthWorkPlanActivity.this, "Internet is not available,data save in locally", Toast.LENGTH_SHORT).show();
                }


            }
        });

        binding.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog_View();
            }
        });

        localRepo.getSelectedWorkPlan(monthyear).observe(MonthWorkPlanActivity.this, new Observer<List<WorkPlanData>>() {
            @Override
            public void onChanged(List<WorkPlanData> workPlanDataList) {
                System.out.println("SIZE: " + workPlanDataList.size());
                for (int i = 0; i < workPlanDataList.size(); i++) {
                    id = workPlanDataList.get(0).getId();
                    userID = workPlanDataList.get(0).getUserId();
                    String mon = workPlanDataList.get(0).getMonthYear();
                    noOfVillageVisit = workPlanDataList.get(0).getNoVillageVisit();
                    noOfGovtVisit = workPlanDataList.get(0).getNoGovtVisit();
                    meetingInternal = workPlanDataList.get(0).getMeetingAttendInternal();
                    meetingExternal = workPlanDataList.get(0).getMeetingAttendExternal();
                    noOfTrainingAttended = workPlanDataList.get(0).getNoOfTrainingAttend();
                    noOfTrainingFacilated = workPlanDataList.get(0).getNoOfTrainingFacilited();
                    noOfOtherEvent = workPlanDataList.get(0).getOtherEvents();

                    try {

                        jsonObject.put("id", id);
                        jsonObject.put("user_id", userID);
                        jsonObject.put("month_year", monthyear);
                        jsonObject.put("no_village_visit", noOfVillageVisit);
                        jsonObject.put("no_govt_visit", noOfGovtVisit);
                        jsonObject.put("meeting_attend_internal", meetingInternal);
                        jsonObject.put("meeting_attend_external", meetingExternal);
                        jsonObject.put("no_of_training_attend", noOfTrainingAttended);
                        jsonObject.put("no_of_training_facilited", noOfTrainingFacilated);
                        jsonObject.put("other_events", noOfOtherEvent);
                        jsonObject.put("status",1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    Log.d("hgsfvja", noOfVillageVisit + noOfGovtVisit + meetingInternal + meetingExternal + noOfTrainingAttended + noOfTrainingFacilated + noOfOtherEvent);
                }
            }
        });

        /*localRepo = new LocalRepo(MonthWorkPlanActivity.this);
        localRepo.getActivityRepList().observe(MonthWorkPlanActivity.this, new Observer<List<ActivityReportAttendanceData>>() {
            @Override
            public void onChanged(List<ActivityReportAttendanceData> activityReportAttendanceData) {
                for (int i = 0;i<activityReportAttendanceData.size();i++){
                    String dateStr = activityReportAttendanceData.get(i).getDate_attendance();
                    System.out.println(">>>>>>>  "+dateStr);
                    String MonthStr = Utils.customMonthYear(dateStr);
                }
            }
        });
        binding.dayList.setLayoutManager(new LinearLayoutManager(MonthWorkPlanActivity.this, RecyclerView.VERTICAL, false));
        monthAdapter = new WorkMonthAdapter(MonthWorkPlanActivity.this, dayList,dayList2);
        binding.dayList.setAdapter(monthAdapter);
        monthAdapter.notifyDataSetChanged();*/

        arraysize();

    }

    public void retriveAttendDetails(String userID, String monthyear) {

        ProgressDialog pd = new ProgressDialog(MonthWorkPlanActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        String url = "https://midev.zbapps.in/api/activityreport/attendance";

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("user_id", userID);
            jsonObject.put("month_year", monthyear);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");
                    String data = response.getString("data");

                    dateofplan = new ArrayList<>();

                    if (status.equals("true")) {

                        //Toast.makeText(MonthWorkPlanActivity.this, message, Toast.LENGTH_SHORT).show();

                        JSONArray jsonArray_data = response.getJSONArray("data");

                        if (jsonArray_data.length() != 0) {

                            for (int i = 0; i < jsonArray_data.length(); i++) {

                                JSONObject jsonObject1_data = jsonArray_data.getJSONObject(i);

                                String id = jsonObject1_data.getString("id");
                                String user_id = jsonObject1_data.getString("user_id");
                                String date_attendance = jsonObject1_data.getString("date_attendance");
                                String attendance = jsonObject1_data.getString("attendance");
                                String first_half = jsonObject1_data.getString("first_half");
                                String second_half = jsonObject1_data.getString("second_half");
                                String status1 = jsonObject1_data.getString("status");

                                DataofPaln_Model dataofPaln_model = new DataofPaln_Model(
                                        id, user_id, date_attendance, attendance, first_half, second_half, status1
                                );

                                dateofplan.add(dataofPaln_model);
                            }

                            size1 = dateofplan.size();

                            linearLayoutManager = new LinearLayoutManager(MonthWorkPlanActivity.this, LinearLayoutManager.VERTICAL, false);
                            //workMonthPalnAdapter = new WorkMonthPalnAdapter(dateofplan, MonthWorkPlanActivity.this, arrayList, monthyear);
                            binding.dayList.setLayoutManager(linearLayoutManager);
                            binding.dayList.setHasFixedSize(true);
                            binding.dayList.setAdapter(workMonthPalnAdapter);


                        } else {
                            Toast.makeText(MonthWorkPlanActivity.this, "No Data In The List", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(MonthWorkPlanActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pd.dismiss();
                Toast.makeText(MonthWorkPlanActivity.this, "" + error, Toast.LENGTH_SHORT).show();
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
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(MonthWorkPlanActivity.this);
        requestQueue.add(jsonObjectRequest);
    }


    public void updateAttendance(JSONArray jsonArray) {

        ProgressDialog pd = new ProgressDialog(MonthWorkPlanActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        String url = "https://midev.zbapps.in/api/activityreport/update_attendance";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                pd.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pd.dismiss();
                Toast.makeText(MonthWorkPlanActivity.this, "" + error, Toast.LENGTH_SHORT).show();

            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(MonthWorkPlanActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    public void StoreDataDataBase() {

        localRepo.getSelectedActivityReportListMon(monthyear).observe(MonthWorkPlanActivity.this, new Observer<List<ActivityReportAttendanceData>>() {
            @Override
            public void onChanged(List<ActivityReportAttendanceData> activityReportAttendanceData) {

                int size = activityReportAttendanceData.size();

                //Toast.makeText(MonthWorkPlanActivity.this, ""+size, Toast.LENGTH_SHORT).show();

                if (activityReportAttendanceData.size() == 0) {

                    Toast.makeText(MonthWorkPlanActivity.this, "No", Toast.LENGTH_SHORT).show();

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

                        jsonArray.put(jsonObject);
                        //arrayList.add(ID);


                        Log.d("hgfhdhb", arrayList.toString());

                        //Toast.makeText(MonthWorkPlanActivity.this, ""+arrayList.toString(), Toast.LENGTH_SHORT).show();

                    }

                    Log.d("hgfhb", jsonArray.toString());

                    updateAttendance1(jsonArray);

                    Log.d("gshhdjhd", jsonArray.toString());
                }

            }
        });

    }

    public void updateAttendance1(JSONArray jsonArray) {

        ProgressDialog pd = new ProgressDialog(MonthWorkPlanActivity.this);
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

                        Toast.makeText(MonthWorkPlanActivity.this, "Data Save Successfully", Toast.LENGTH_SHORT).show();

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

                Toast.makeText(MonthWorkPlanActivity.this, ""+error, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(MonthWorkPlanActivity.this);
        requestQueue.add(myJsonArrayRequest);
    }

    public void SubmitAttendance(JSONArray jsonArray) {

        ProgressDialog pd = new ProgressDialog(MonthWorkPlanActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        String url = "https://midev.zbapps.in/api/activityreport/group_update_activity_report";

        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equals("true")) {

                        dialog.dismiss();

                        //Toast.makeText(MonthWorkPlanActivity.this, message, Toast.LENGTH_SHORT).show();

                        //retriveAttendDetails(userID, monthyear);

                        callActivityPlanData(userID);

                    }else{

                        Toast.makeText(MonthWorkPlanActivity.this, message, Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        RequestQueue requestQueue = Volley.newRequestQueue(MonthWorkPlanActivity.this);
        requestQueue.add(myJsonArrayRequest);
    }


    public void openDialog_Save() {

        dialog = new Dialog(MonthWorkPlanActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.savdata_database);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        MaterialButton cancelBtn = dialog.findViewById(R.id.cancelBtn);
        MaterialButton saveBtn = dialog.findViewById(R.id.saveBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        dialog.show();


    }

    public void openDialog_View() {

        dialog = new Dialog(MonthWorkPlanActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.viewdata_database);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        String monthStatues = sessinoManager.getMONTHSTATUS();

        Log.d("hdgvjh", monthStatues);

        MaterialButton cancelBtn = dialog.findViewById(R.id.cancelBtn);
        MaterialButton saveBtn = dialog.findViewById(R.id.saveBtn);
        TextView showtext = dialog.findViewById(R.id.showtext);

        if (monthStatues.equals("2")) {

            showtext.setText("Download PDF");
            saveBtn.setVisibility(View.VISIBLE);

        }

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                downloadPdfAndroid();

            }
        });



        dialog.show();


    }

    public void openDialog_Submit() {

        dialog = new Dialog(MonthWorkPlanActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.submitdata_database);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        MaterialButton cancelBtn = dialog.findViewById(R.id.cancelBtn);
        MaterialButton saveBtn = dialog.findViewById(R.id.saveBtn);
        TextView showtext = dialog.findViewById(R.id.showtext);

        saveBtn.setVisibility(View.VISIBLE);

        arraysize();
        StoreDataDataBase();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Utils.isNetworkAvailable(MonthWorkPlanActivity.this)) {

                    if (size2 == 0) {

                        Toast.makeText(MonthWorkPlanActivity.this, "Insert Data", Toast.LENGTH_SHORT).show();

                    } else {

                        Log.d("hvhjvv", size1 +"  "+size2);

                        if (size1 == size2) {

                            JSONObject jsonObject_data = new JSONObject();

                            try {

                                jsonObject_data.put("activity_report", jsonObject);
                                jsonObject_data.put("attendances", jsonArray);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            JSONArray jsonArray_data = new JSONArray();
                            jsonArray_data.put(jsonObject_data);

                            Log.d("hfudjhvyuf", jsonArray_data.toString());

                            SubmitAttendance(jsonArray_data);

                        }else{

                            showtext.setText("You have not submitted the attendance for all days ?");
                            saveBtn.setVisibility(View.GONE);
                            cancelBtn.setText("Go Back");
                        }

                    }
                }else{

                    showtext.setText("Please Check Internet Connection");
                }

            }
        });

        dialog.show();


    }



    /*@Override
    protected void onResume() {
        super.onResume();

        localRepo.getSelectedActivityReportListMon(monthyear).observe(MonthWorkPlanActivity.this, new Observer<List<ActivityReportAttendanceData>>() {
            @Override
            public void onChanged(List<ActivityReportAttendanceData> activityReportAttendanceData) {

                dateofplan1 = new ArrayList<>();

                dateofplan1.clear();

                for (int i = 0; i < activityReportAttendanceData.size(); i++) {

                    dateofplan1.add(activityReportAttendanceData.get(i));

                }

                linearLayoutManager = new LinearLayoutManager(MonthWorkPlanActivity.this, LinearLayoutManager.VERTICAL, false);
                workMonthPalnAdapter = new WorkMonthPalnAdapter(dateofplan1, MonthWorkPlanActivity.this, arrayList, monthyear);
                binding.dayList.setLayoutManager(linearLayoutManager);
                binding.dayList.setHasFixedSize(true);
                binding.dayList.setAdapter(workMonthPalnAdapter);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        localRepo.getSelectedActivityReportListMon(monthyear).observe(MonthWorkPlanActivity.this, new Observer<List<ActivityReportAttendanceData>>() {
            @Override
            public void onChanged(List<ActivityReportAttendanceData> activityReportAttendanceData) {

                dateofplan1 = new ArrayList<>();

                dateofplan1.clear();

                for (int i = 0; i < activityReportAttendanceData.size(); i++) {

                    dateofplan1.add(activityReportAttendanceData.get(i));

                }

                linearLayoutManager = new LinearLayoutManager(MonthWorkPlanActivity.this, LinearLayoutManager.VERTICAL, false);
                workMonthPalnAdapter = new WorkMonthPalnAdapter(dateofplan1, MonthWorkPlanActivity.this, arrayList, monthyear);
                binding.dayList.setLayoutManager(linearLayoutManager);
                binding.dayList.setHasFixedSize(true);
                binding.dayList.setAdapter(workMonthPalnAdapter);
            }
        });
    }*/

    // Activity Report
    public void callActivityPlanData(String UserId) {

        ProgressDialog progressDialog = new ProgressDialog(MonthWorkPlanActivity.this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
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


                    progressDialog.dismiss();

                    Intent intent = new Intent(MonthWorkPlanActivity.this, LandingPageActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<Example_ActivityReport> call, Throwable t) {

                // isprogress.setValue(10);
            }
        });
    }

    public void arraysize(){

        localRepo.getSelectedActivityReportListMon(monthyear).observe(MonthWorkPlanActivity.this, new Observer<List<ActivityReportAttendanceData>>() {
            @Override
            public void onChanged(List<ActivityReportAttendanceData> activityReportAttendanceData) {

                if (activityReportAttendanceData.size() == 0) {

                    Toast.makeText(MonthWorkPlanActivity.this, "No Data Avilable", Toast.LENGTH_SHORT).show();

                } else {

                    arrayList1.clear();

                    for (int i = 0; i < activityReportAttendanceData.size(); i++) {

                        String ID = activityReportAttendanceData.get(i).getId();
                        String attendance = String.valueOf(activityReportAttendanceData.get(i).getAttendance());

                        if(attendance.equals("null") || attendance.equals("")){}else{

                            arrayList1.add(attendance);
                        }

                        Log.d("hgfhdhb", arrayList1.toString());

                        //Toast.makeText(MonthWorkPlanActivity.this, ""+arrayList.toString(), Toast.LENGTH_SHORT).show();

                    }

                    Log.d("hgfhdhb", arrayList1.toString());

                    size2 = arrayList1.size();
                }
            }
        });
    }

    public void downloadPdfAndroid(){

        SessinoManager sessinoManager = new SessinoManager(MonthWorkPlanActivity.this);
        String id = sessinoManager.getID();
        ProgressDialog progressDialog = new ProgressDialog(MonthWorkPlanActivity.this);
        progressDialog.setMessage("Download PDF");
        progressDialog.show();

        Map<String,Object> mapData = new HashMap<>();
        mapData.put("id",id);

        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        Call<DownloadPDF> downloadPDFCall = apiRequest.reportDownLoadPdf(CommonClass.APP_TOKEN, mapData);
        downloadPDFCall.enqueue(new Callback<DownloadPDF>() {
            @Override
            public void onResponse(Call<DownloadPDF> call, retrofit2.Response<DownloadPDF> response) {

                if(response.body() != null){
                    progressDialog.dismiss();
                    if(response.isSuccessful()){

                        progressDialog.dismiss();

                        String url = response.body().getPdfUrl();

                        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                        String title = URLUtil.guessFileName(url,null,null);
                        request.setTitle(title);
                        request.setDescription("Downloading File please wait...");
                        String cookie = CookieManager.getInstance().getCookie(url);
                        request.addRequestHeader("cookie",cookie);
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);

                        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        downloadManager.enqueue(request);

                        Toast.makeText(MonthWorkPlanActivity.this, "Downloading Started", Toast.LENGTH_SHORT).show();



                    }else{

                        Toast.makeText(MonthWorkPlanActivity.this, "PDF not Download", Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(MonthWorkPlanActivity.this, "PDF not Download", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DownloadPDF> call, Throwable t) {

                Toast.makeText(MonthWorkPlanActivity.this, ""+t, Toast.LENGTH_SHORT).show();

            }
        });

    }
}