package com.example.mobilityindia.actionplan;

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
import com.example.mobilityindia.actionplan.adapter.ActionMonthPlanAdapter;
import com.example.mobilityindia.actionplan.model.MonthPlanAction_Model;
import com.example.mobilityindia.actionplan.model.Plans_ModelClass;
import com.example.mobilityindia.databinding.ActivityActionPlanBinding;
import com.example.mobilityindia.sync.model.ActionPlanData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionPlanActivity extends AppCompatActivity {

    ActivityActionPlanBinding binding;
    LocalRepo localRepo;
    ArrayList<MonthPlanAction_Model> monthplan;
    List<ActionPlanData> monthplan1 = new ArrayList<>();
    ArrayList<Plans_ModelClass> plansmodel;
    LinearLayoutManager linearLayoutManager;
    ActionMonthPlanAdapter actionMonthPlanAdapter;

    SessinoManager sessinoManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_action_plan);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_action_plan);
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

        sessinoManager = new SessinoManager(ActionPlanActivity.this);

        String userId = sessinoManager.getUSERID();

        // actionMonthDetails(userId);

        localRepo = new LocalRepo(ActionPlanActivity.this);

        localRepo.getFilterActionPlanList().observe(ActionPlanActivity.this, new Observer<List<ActionPlanData>>() {
            @Override
            public void onChanged(List<ActionPlanData> actionPlanData) {

                for (int i = 0; i < actionPlanData.size(); i++) {

                    monthplan1.add(actionPlanData.get(i));

                    Log.d("cffxfcbvcf", actionPlanData.get(i).toString());

                }

                linearLayoutManager = new LinearLayoutManager(ActionPlanActivity.this, LinearLayoutManager.VERTICAL, false);
                actionMonthPlanAdapter = new ActionMonthPlanAdapter(monthplan1, ActionPlanActivity.this);
                binding.actionPlanMonth.setLayoutManager(linearLayoutManager);
                binding.actionPlanMonth.setHasFixedSize(true);
                binding.actionPlanMonth.setAdapter(actionMonthPlanAdapter);

            }

        });


    }

    public void actionMonthDetails(String userID) {

        String url = "https://mis.mobility-india.org/api/getactionplan";

        ProgressDialog progressDialog = new ProgressDialog(ActionPlanActivity.this);
        progressDialog.setMessage("View ActionPlan Details...");
        progressDialog.show();

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("user_id", userID);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                Log.d("jhdjbhjb", response.toString());

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");
                    String data = response.getString("data");

                    if (status.equals("true")) {

                        monthplan = new ArrayList<>();

                        JSONArray jsonArray_data = new JSONArray(data);

                        for (int i = 0; i < jsonArray_data.length(); i++) {

                            JSONObject jsonObject_data = jsonArray_data.getJSONObject(i);

                            String activity_report = jsonObject_data.getString("monthly_status");
                            String attendances = jsonObject_data.getString("plans");

                            JSONObject jsonObject1_activity_report = new JSONObject(activity_report);

                            String id = jsonObject1_activity_report.getString("id");
                            String user_id = jsonObject1_activity_report.getString("user_id");
                            String month_year = jsonObject1_activity_report.getString("month_year");
                            String status1 = jsonObject1_activity_report.getString("status");
                            //String name = jsonObject1_activity_report.getString("name");
                            String created_by = jsonObject1_activity_report.getString("created_by");

                            plansmodel = new ArrayList<>();

                            JSONArray jsonArray_paln = new JSONArray(attendances);

                            for (int j = 0; j < jsonArray_paln.length(); j++) {

                                JSONObject jsonObject1_Paln = jsonArray_paln.getJSONObject(i);

                                String id1 = jsonObject1_Paln.getString("id");
                                String user_id1 = jsonObject1_Paln.getString("user_id");
                                String month_year1 = jsonObject1_Paln.getString("month_year");
                                String date = jsonObject1_Paln.getString("date");
                                String plan = jsonObject1_Paln.getString("plan");
                                String result = jsonObject1_Paln.getString("result");
                                String remarks = jsonObject1_Paln.getString("remarks");
                                String name = jsonObject1_Paln.getString("name");
                                String user_type_id = jsonObject1_Paln.getString("user_type_id");

                                Plans_ModelClass plans_modelClass = new Plans_ModelClass(
                                        id1, user_id1, month_year1, date, plan, result, remarks
                                );

                                plansmodel.add(plans_modelClass);
                            }


                            MonthPlanAction_Model monthPlan_model = new MonthPlanAction_Model(
                                    id, user_id, month_year, status1, "name", created_by, plansmodel
                            );

                            monthplan.add(monthPlan_model);

                        }

                        linearLayoutManager = new LinearLayoutManager(ActionPlanActivity.this, LinearLayoutManager.VERTICAL, false);
                        //actionMonthPlanAdapter = new ActionMonthPlanAdapter(monthplan,ActionPlanActivity.this);
                        binding.actionPlanMonth.setLayoutManager(linearLayoutManager);
                        binding.actionPlanMonth.setHasFixedSize(true);
                        binding.actionPlanMonth.setAdapter(actionMonthPlanAdapter);

                    } else {

                        Toast.makeText(ActionPlanActivity.this, message, Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(ActionPlanActivity.this, "" + error, Toast.LENGTH_SHORT).show();

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
        RequestQueue requestQueue = Volley.newRequestQueue(ActionPlanActivity.this);
        requestQueue.add(jsonObjectRequest);
    }

}