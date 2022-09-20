package com.example.mobilityindia.workplan;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.example.mobilityindia.databinding.ActivityWorkPlanBinding;
import com.example.mobilityindia.sync.model.WorkPlanData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.workplan.adapter.WorkingMonthPlanAdapter;
import com.example.mobilityindia.workplan.model.MonthPlanWork_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkPlanActivity extends AppCompatActivity{

    ActivityWorkPlanBinding binding;
    LocalRepo localRepo;
    WorkingMonthPlanAdapter workingMonthPlanAdapter;
    ArrayList<MonthPlanWork_Model> monthplan;
    ArrayList<WorkPlanData> monthplan1;
    LinearLayoutManager linearLayoutManager;

    SessinoManager sessinoManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_work_plan);
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
        sessinoManager = new SessinoManager(WorkPlanActivity.this);

        String userid = sessinoManager.getUSERID();

        //workingMonthDetails(userid);

        localRepo = new LocalRepo(WorkPlanActivity.this);
        localRepo.getFilterWorkPlanList().observe(WorkPlanActivity.this, new Observer<List<WorkPlanData>>() {
            @Override
            public void onChanged(List<WorkPlanData> workPlanDataList) {

                monthplan1 = new ArrayList<>();
                monthplan1.clear();

                for (int i = 0; i < workPlanDataList.size(); i++) {

                    monthplan1.add(workPlanDataList.get(i));

                }

                linearLayoutManager = new LinearLayoutManager(WorkPlanActivity.this, LinearLayoutManager.VERTICAL, false);
                workingMonthPlanAdapter = new WorkingMonthPlanAdapter(monthplan1, WorkPlanActivity.this);
                binding.recyclerviewWorkpaln.setLayoutManager(linearLayoutManager);
                binding.recyclerviewWorkpaln.setHasFixedSize(true);
                binding.recyclerviewWorkpaln.setAdapter(workingMonthPlanAdapter);
            }
        });

    }

    public void workingMonthDetails(String userID){

        String url = "https://midev.zbapps.in/api/activityreport/own";

        ProgressDialog progressDialog = new ProgressDialog(WorkPlanActivity.this);
        progressDialog.setMessage("View Activity Report details...");
        progressDialog.show();

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("user_id",userID);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                Log.d("jhdjbhjb",response.toString());

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");
                    String data = response.getString("data");

                    if(status.equals("true")) {

                        monthplan = new ArrayList<>();

                        JSONArray jsonArray_data = new JSONArray(data);

                        for (int i = 0; i < jsonArray_data.length(); i++) {

                            JSONObject jsonObject_data = jsonArray_data.getJSONObject(i);

                            String activity_report = jsonObject_data.getString("activity_report");
                            String attendances = jsonObject_data.getString("attendances");

                            JSONObject jsonObject1_activity_report = new JSONObject(activity_report);

                            String id = jsonObject1_activity_report.getString("id");
                            String user_id = jsonObject1_activity_report.getString("user_id");
                            String month_year = jsonObject1_activity_report.getString("month_year");
                            String status1 = jsonObject1_activity_report.getString("status");
                            String name = jsonObject1_activity_report.getString("name");
                            String user_type_id = jsonObject1_activity_report.getString("user_type_id");
                            String no_village_visit = jsonObject1_activity_report.getString("no_village_visit");
                            String no_govt_visit = jsonObject1_activity_report.getString("no_govt_visit");
                            String meeting_attend_internal = jsonObject1_activity_report.getString("meeting_attend_internal");
                            String meeting_attend_external = jsonObject1_activity_report.getString("meeting_attend_external");
                            String no_of_training_attend = jsonObject1_activity_report.getString("no_of_training_attend");
                            String no_of_training_facilited = jsonObject1_activity_report.getString("no_of_training_facilited");
                            String other_events = jsonObject1_activity_report.getString("no_of_training_facilited");



                            MonthPlanWork_Model monthPlan_model = new MonthPlanWork_Model(

                                    id, user_id, month_year, status1, name, user_type_id,no_village_visit,
                                    no_govt_visit,meeting_attend_internal,meeting_attend_external,no_of_training_attend,
                                    no_of_training_facilited,other_events
                            );

                            monthplan.add(monthPlan_model);

                        }

                        linearLayoutManager = new LinearLayoutManager(WorkPlanActivity.this,LinearLayoutManager.VERTICAL,false);
                        //workingMonthPlanAdapter = new WorkingMonthPlanAdapter(monthplan,WorkPlanActivity.this);
                        binding.recyclerviewWorkpaln.setLayoutManager(linearLayoutManager);
                        binding.recyclerviewWorkpaln.setHasFixedSize(true);
                        binding.recyclerviewWorkpaln.setAdapter(workingMonthPlanAdapter);

                    }else{

                        Toast.makeText(WorkPlanActivity.this, message, Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(WorkPlanActivity.this, ""+error, Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                String creds = String.format("%s:%s","mobility","admin@123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);

                return params;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(WorkPlanActivity.this);
        requestQueue.add(jsonObjectRequest);

    }

  /*  @Override
    public void onNoteClick(int position) {

      *//*  Intent intent = new Intent(WorkPlanActivity.this,WorkMonthDetailActivity.class);
        startActivity(intent);*//*

    }*/
}