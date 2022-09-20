package com.example.mobilityindia.actionplan;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.actionplan.adapter.ActionMonthAdapter;
import com.example.mobilityindia.actionplan.model.Plans_ModelClass;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityMonthActionPlanBinding;
import com.example.mobilityindia.landingpage.view.LandingPageActivity;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.syn1.view.SyncActivityAll;
import com.example.mobilityindia.syn1.view.allresponse.actionplan.Datum_ActionPlan;
import com.example.mobilityindia.syn1.view.allresponse.actionplan.Example_ActionPlan;
import com.example.mobilityindia.syn1.view.allresponse.downloadpdf.DownloadPDF;
import com.example.mobilityindia.sync.model.ActionPlanData;
import com.example.mobilityindia.sync.model.ActionPlanMonth;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.utils.Utils;
import com.example.mobilityindia.workplan.MyJsonArrayRequest;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import retrofit2.Call;
import retrofit2.Callback;

public class MonthActionPlanActivity extends AppCompatActivity {

    ActivityMonthActionPlanBinding binding;
    ActionMonthAdapter monthAdapter;
    ArrayList<Plans_ModelClass> dayList = new ArrayList<Plans_ModelClass>();
    ArrayList<ActionPlanMonth> dayList2 = new ArrayList<>();
    JSONArray jsonArray = new JSONArray();
    private static int SPLASH_SCREEN_TIME_OUT = 1000;

    SessinoManager sessinoManager;

    String monthyear, userID, id, status1;

    Dialog dialog;

    LocalRepo localRepo;

    SwipeRefreshLayout mSwipeRefreshLayout;

    ApiRequest apiRequest;
    JSONObject jsonObject1_allData = new JSONObject();
    JSONArray jsonArray_allData = new JSONArray();
    JSONObject jsonObject_month = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_month_action_plan);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_month_action_plan);
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

        sessinoManager = new SessinoManager(MonthActionPlanActivity.this);

        monthyear = getIntent().getStringExtra("MonthYr");
        userID = getIntent().getStringExtra("userid");
        id = getIntent().getStringExtra("id");
        status1 = getIntent().getStringExtra("status");
        status1 = String.valueOf(status1);
        if (status1.equals("null")) {
        } else if (status1.equals("draft")) {

            binding.saveBtn.setVisibility(View.VISIBLE);
            binding.submitBtn.setVisibility(View.VISIBLE);

        } else if (status1.equals("submitted")) {

            binding.saveBtn.setVisibility(View.GONE);
            binding.submitBtn.setVisibility(View.GONE);

        } else if (status1.equals("approved")) {

            binding.saveBtn.setVisibility(View.GONE);
            binding.submitBtn.setVisibility(View.GONE);

        } else if (status1.equals("rework")) {

            binding.saveBtn.setVisibility(View.VISIBLE);
            binding.submitBtn.setVisibility(View.VISIBLE);
        }

        Log.d("hdbgbfiu", monthyear + userID + id);

        localRepo = new LocalRepo(MonthActionPlanActivity.this);

        localRepo.getSelectedActionPlanMonthList(monthyear).observe(MonthActionPlanActivity.this, new Observer<List<ActionPlanMonth>>() {
            @Override
            public void onChanged(List<ActionPlanMonth> actionPlanMonths) {

                dayList2.clear();

                for (int i = 0; i < actionPlanMonths.size(); i++) {

                    dayList2.add(actionPlanMonths.get(i));
                }

                binding.dayList.setLayoutManager(new LinearLayoutManager(MonthActionPlanActivity.this, RecyclerView.VERTICAL, false));
                monthAdapter = new ActionMonthAdapter(MonthActionPlanActivity.this, dayList2);
                binding.dayList.setHasFixedSize(true);
                binding.dayList.setAdapter(monthAdapter);
                monthAdapter.notifyDataSetChanged();
            }
        });


        binding.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                localRepo.getSelectedActionPlanMonthList(monthyear).observe(MonthActionPlanActivity.this, new Observer<List<ActionPlanMonth>>() {
                    @Override
                    public void onChanged(List<ActionPlanMonth> actionPlanMonths) {

                        dayList2.clear();

                        for (int i = 0; i < actionPlanMonths.size(); i++) {

                            dayList2.add(actionPlanMonths.get(i));
                        }

                        binding.dayList.setLayoutManager(new LinearLayoutManager(MonthActionPlanActivity.this, RecyclerView.VERTICAL, false));
                        monthAdapter = new ActionMonthAdapter(MonthActionPlanActivity.this, dayList2);
                        binding.dayList.setHasFixedSize(true);
                        binding.dayList.setAdapter(monthAdapter);
                        monthAdapter.notifyDataSetChanged();

                    }
                });

                binding.mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        //retriveAttendDetails(userID, monthyear);

        /* dayList =  (ArrayList<Plans_ModelClass>)getIntent().getSerializableExtra("bundle");*/

       /* Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ArrayList<Plans_ModelClass> dayList = (ArrayList<Plans_ModelClass>) args.getSerializable("ARRAYLIST");

        Log.d("gjhsgjhv",dayList.toString());*/

        // monthAdapter = new ActionMonthAdapter(MonthActionPlanActivity.this, dayList, dayList2);

        binding.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog_View();

            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Utils.isNetworkAvailable(MonthActionPlanActivity.this)) {

                    openDialog_Submit();

                } else {

                    Toast.makeText(MonthActionPlanActivity.this, "Internet Is not available data saved in locally", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Utils.isNetworkAvailable(MonthActionPlanActivity.this)) {

                    Toast.makeText(MonthActionPlanActivity.this, "Data Saved SuccessFully", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(MonthActionPlanActivity.this, "Internet Is not available data saved in locally", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }

    public void retriveAttendDetails(String userID, String monthyear) {

        ProgressDialog pd = new ProgressDialog(MonthActionPlanActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        String url = "https://midev.zbapps.in/api/monthlyPlan";

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

                    dayList = new ArrayList<Plans_ModelClass>();

                    if (status.equals("true")) {

                        //Toast.makeText(MonthActionPlanActivity.this, message, Toast.LENGTH_SHORT).show();

                        JSONArray jsonArray_data = response.getJSONArray("data");

                        if (jsonArray_data.length() != 0) {

                            for (int i = 0; i < jsonArray_data.length(); i++) {

                                JSONObject jsonObject1_Paln = jsonArray_data.getJSONObject(i);

                                String id1 = jsonObject1_Paln.getString("id");
                                String user_id1 = jsonObject1_Paln.getString("user_id");
                                String month_year1 = jsonObject1_Paln.getString("month_year");
                                String date = jsonObject1_Paln.getString("date");
                                String plan = jsonObject1_Paln.getString("plan");
                                String result = jsonObject1_Paln.getString("result");
                                String remarks = jsonObject1_Paln.getString("remarks");

                                Plans_ModelClass plans_modelClass = new Plans_ModelClass(
                                        id1, user_id1, month_year1, date, plan, result, remarks
                                );
                                dayList.add(plans_modelClass);
                            }

                            binding.dayList.setLayoutManager(new LinearLayoutManager(MonthActionPlanActivity.this, RecyclerView.VERTICAL, false));
                            //monthAdapter = new ActionMonthAdapter(MonthActionPlanActivity.this, dayList);
                            binding.dayList.setHasFixedSize(true);
                            binding.dayList.setAdapter(monthAdapter);
                            monthAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(MonthActionPlanActivity.this, "No Data In The List", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(MonthActionPlanActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pd.dismiss();
                Toast.makeText(MonthActionPlanActivity.this, "" + error, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(MonthActionPlanActivity.this);
        requestQueue.add(jsonObjectRequest);
    }

    public void retriveAttendDetails1(String userID, String monthyear) {

        ProgressDialog pd = new ProgressDialog(MonthActionPlanActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        String url = "https://midev.zbapps.in/api/monthlyPlan";

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

                    dayList = new ArrayList<Plans_ModelClass>();


                    if (status.equals("true")) {

                        Toast.makeText(MonthActionPlanActivity.this, message, Toast.LENGTH_SHORT).show();

                        JSONArray jsonArray_data = response.getJSONArray("data");

                        if (jsonArray_data.length() != 0) {

                            for (int i = 0; i < jsonArray_data.length(); i++) {

                                JSONObject jsonObject1_Paln = jsonArray_data.getJSONObject(i);

                                String id1 = jsonObject1_Paln.getString("id");
                                String user_id1 = jsonObject1_Paln.getString("user_id");
                                String month_year1 = jsonObject1_Paln.getString("month_year");
                                String date = jsonObject1_Paln.getString("date");
                                String plan = jsonObject1_Paln.getString("plan");
                                String result = jsonObject1_Paln.getString("result");
                                String remarks = jsonObject1_Paln.getString("remarks");

                                JSONObject jsonObject = new JSONObject();

                                try {
                                    jsonObject.put("id", id1);
                                    jsonObject.put("user_id", user_id1);
                                    jsonObject.put("month_year", month_year1);
                                    jsonObject.put("date", date);
                                    jsonObject.put("plan", plan);
                                    jsonObject.put("result", result);
                                    jsonObject.put("remarks", remarks);

                                } catch (JSONException ex) {
                                    ex.printStackTrace();
                                }


                                //jsonArray.put(jsonObject);

                            }

                            Log.d("hdhvyjbs", jsonArray.toString());

                            JSONObject jsonObject_month = new JSONObject();

                            try {

                                jsonObject_month.put("id", id);
                                jsonObject_month.put("user_id", userID);
                                jsonObject_month.put("month_year", monthyear);
                                jsonObject_month.put("status", "submitted");

                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }

                            Log.d("jdbcacsb", jsonObject_month.toString());

                            JSONObject jsonObject1_allData = new JSONObject();

                            try {

                                jsonObject1_allData.put("monthly_actionplan", jsonObject_month);
                                jsonObject1_allData.put("actionplans", jsonArray);

                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }

                            Log.d("gcvavsu", jsonObject1_allData.toString());

                            JSONArray jsonArray_allData = new JSONArray();

                            jsonArray_allData.put(jsonObject1_allData);

                            Log.d("gcvavsu", jsonArray_allData.toString());


                            //updateAttendance1(jsonArray_allData);


                        } else {
                            //Toast.makeText(MonthActionPlanActivity.this, "No Data In The List", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(MonthActionPlanActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pd.dismiss();
                Toast.makeText(MonthActionPlanActivity.this, "" + error, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(MonthActionPlanActivity.this);
        requestQueue.add(jsonObjectRequest);
    }

    public void openDialog_Save() {

        dialog = new Dialog(MonthActionPlanActivity.this);
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

        dialog = new Dialog(MonthActionPlanActivity.this);
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

        MaterialButton cancelBtn = dialog.findViewById(R.id.cancelBtn);
        MaterialButton saveBtn = dialog.findViewById(R.id.saveBtn);
        TextView showtext = dialog.findViewById(R.id.showtext);

        String monthstatus = sessinoManager.getMONTHSTATUS();

        if (monthstatus.equals("approved")) {

            showtext.setText("Download PDF");
            saveBtn.setVisibility(View.VISIBLE);
        }

        //saveBtn.setVisibility(View.VISIBLE);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                downloadPdfAndroid();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();


    }

    public void openDialog_Submit() {

        dialog = new Dialog(MonthActionPlanActivity.this);
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

        try {

            jsonObject_month.put("id", id);
            jsonObject_month.put("user_id", userID);
            jsonObject_month.put("month_year", monthyear);
            jsonObject_month.put("status", "submitted");

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        Log.d("hdsbgkjb", jsonObject_month.toString());

        retriveAttendDetails();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Utils.isNetworkAvailable(MonthActionPlanActivity.this)) {

                    //retriveAttendDetails1(userID, monthyear);
                    try {

                        jsonObject1_allData.put("monthly_actionplan", jsonObject_month);
                        jsonObject1_allData.put("actionplans", jsonArray);

                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }

                    Log.d("gcvavsu", jsonObject1_allData.toString());


                    jsonArray_allData.put(jsonObject1_allData);

                    Log.d("gcvavsu", jsonArray_allData.toString());


                    updateAttendance1(jsonArray_allData);


                } else {

                    showtext.setText("Internet Connection is Not Avilable");
                }

            }
        });

        dialog.show();


    }

 /*   @Override
    protected void onResume() {
        super.onResume();

        localRepo.getSelectedActionPlanMonthList(monthyear).observe(MonthActionPlanActivity.this, new Observer<List<ActionPlanMonth>>() {
            @Override
            public void onChanged(List<ActionPlanMonth> actionPlanMonths) {

                dayList2.clear();

                for (int i = 0; i < actionPlanMonths.size(); i++) {

                    dayList2.add(actionPlanMonths.get(i));
                }

                binding.dayList.setLayoutManager(new LinearLayoutManager(MonthActionPlanActivity.this, RecyclerView.VERTICAL, false));
                monthAdapter = new ActionMonthAdapter(MonthActionPlanActivity.this, dayList2);
                binding.dayList.setHasFixedSize(true);
                binding.dayList.setAdapter(monthAdapter);
                monthAdapter.notifyDataSetChanged();
            }
        });
    }*/

 /*   @Override
    protected void onRestart() {
        super.onRestart();

        localRepo.getSelectedActionPlanMonthList(monthyear).observe(MonthActionPlanActivity.this, new Observer<List<ActionPlanMonth>>() {
            @Override
            public void onChanged(List<ActionPlanMonth> actionPlanMonths) {

                dayList2.clear();

                for (int i = 0; i < actionPlanMonths.size(); i++) {

                    dayList2.add(actionPlanMonths.get(i));
                }

                binding.dayList.setLayoutManager(new LinearLayoutManager(MonthActionPlanActivity.this, RecyclerView.VERTICAL, false));
                monthAdapter = new ActionMonthAdapter(MonthActionPlanActivity.this, dayList2);
                binding.dayList.setHasFixedSize(true);
                binding.dayList.setAdapter(monthAdapter);
                monthAdapter.notifyDataSetChanged();
            }
        });
    }*/

    public void retriveAttendDetails() {

        localRepo.getSelectedActionPlanMonthList1(monthyear).observe(MonthActionPlanActivity.this, new Observer<List<ActionPlanMonth>>() {
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
                            jsonObject.put("month_year", month_year1);
                            jsonObject.put("date", date);
                            jsonObject.put("plan", plan);
                            jsonObject.put("result", result);
                            jsonObject.put("remarks", remarks);

                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }

                        jsonArray.put(jsonObject);

                    }

                    Log.d("hdhvyjbs", jsonArray.toString());

                } else {

                    //Toast.makeText(MonthActionPlanActivity.this, "No Data In The List", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void updateAttendance1(JSONArray jsonArray) {

        ProgressDialog pd = new ProgressDialog(MonthActionPlanActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        String url = "https://midev.zbapps.in/api/uploadPlan";

        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equals("true")) {

                        //Toast.makeText(MonthActionPlanActivity.this, message, Toast.LENGTH_SHORT).show();

                        callActionPlanData(userID);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pd.dismiss();

                Toast.makeText(MonthActionPlanActivity.this, "api Not Respons Properly", Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(MonthActionPlanActivity.this);
        requestQueue.add(myJsonArrayRequest);
    }

    // ActionPlan Data
    public void callActionPlanData(String userId) {

        ProgressDialog progressDialog = new ProgressDialog(MonthActionPlanActivity.this);
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();
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

                        for (int i = 0; i < data.getPlans().size(); i++) {

                            localRepo.insertActionPlanMonth(data.getPlans().get(i));
                        }
                    }

                    dialog.dismiss();

                    progressDialog.dismiss();

                    Intent intent = new Intent(MonthActionPlanActivity.this, LandingPageActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<Example_ActionPlan> call, Throwable t) {
                //isprogress.setValue(10);

                Toast.makeText(MonthActionPlanActivity.this, "" + t, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public void downloadPdfAndroid(){

        SessinoManager sessinoManager = new SessinoManager(MonthActionPlanActivity.this);
        String id = sessinoManager.getAID();
        ProgressDialog progressDialog = new ProgressDialog(MonthActionPlanActivity.this);
        progressDialog.setMessage("Download PDF");
        progressDialog.show();

        Map<String,Object> mapData = new HashMap<>();
        mapData.put("id",id);

        Log.d("hbxbjbas",mapData.toString());

        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        Call<DownloadPDF> downloadPDFCall = apiRequest.actionDownLoadPdf(CommonClass.APP_TOKEN, mapData);
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

                        Toast.makeText(MonthActionPlanActivity.this, "Downloading Started", Toast.LENGTH_SHORT).show();



                    }else{

                        Toast.makeText(MonthActionPlanActivity.this, "PDF not Download", Toast.LENGTH_SHORT).show();
                    }
                }else{

                    Toast.makeText(MonthActionPlanActivity.this, "PDF not Download", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DownloadPDF> call, Throwable t) {

                Toast.makeText(MonthActionPlanActivity.this, ""+t, Toast.LENGTH_SHORT).show();

            }
        });

    }
}