package com.example.mobilityindia.workplan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import com.example.mobilityindia.databinding.ActivityWorkMonthDetailBinding;
import com.example.mobilityindia.sync.model.WorkPlanData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WorkMonthDetailActivity extends AppCompatActivity {
    ActivityWorkMonthDetailBinding binding;
    String dateStr = "";
    String dateStr2 = "";
    String formatedMonthYr = "";
    LocalRepo localRepo;
    String tempId = "", monYr = "", tempUserId = "", strDate = "", noOfVillageVisit, noOfGovtVisit, meetingInternal,
            meetingExternal, noOfTrainingAttended, noOfTrainingFacilated, noOfOtherEvent, statues = "";

    boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_work_month_detail);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_work_month_detail);
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

        localRepo = new LocalRepo(WorkMonthDetailActivity.this);

        monYr = getIntent().getStringExtra("MonthYr");
        tempId = getIntent().getStringExtra("id");
        tempUserId = getIntent().getStringExtra("userid");
        statues = getIntent().getStringExtra("statues");

        /*if(Utils.isNetworkAvailable(WorkMonthDetailActivity.this)){

            noOfVillageVisit = getIntent().getStringExtra("noOfVillageVisit");
            noOfGovtVisit = getIntent().getStringExtra("noOfGovtVisit");
            meetingInternal = getIntent().getStringExtra("meetingInternal");
            meetingExternal = getIntent().getStringExtra("meetingExternal");
            noOfTrainingAttended = getIntent().getStringExtra("noOfTrainingAttended");
            noOfTrainingFacilated = getIntent().getStringExtra("noOfTrainingFacilated");
            noOfOtherEvent = getIntent().getStringExtra("noOfOtherEvent");

            binding.noOfVillageVisit.setText(noOfVillageVisit);
            binding.noOfGovtVisit.setText(noOfGovtVisit);
            binding.meetingInternal.setText(meetingInternal);
            binding.meetingExternal.setText(meetingExternal);
            binding.noOfTrainingAttended.setText(noOfTrainingAttended);
            binding.noOfTrainingFacilated.setText(noOfTrainingFacilated);
            binding.noOfOtherEvent.setText(noOfOtherEvent);

        }else{
*/
            localRepo.getSelectedWorkPlan(monYr).observe(WorkMonthDetailActivity.this, new Observer<List<WorkPlanData>>() {
                @Override
                public void onChanged(List<WorkPlanData> workPlanDataList) {
                    System.out.println("SIZE: "+workPlanDataList.size());
                    for (int i = 0;i<workPlanDataList.size();i++){
                        tempId = workPlanDataList.get(0).getId();
                        tempUserId = workPlanDataList.get(0).getUserId();
                        binding.noOfVillageVisit.setText(workPlanDataList.get(0).getNoVillageVisit());
                        binding.noOfGovtVisit.setText(workPlanDataList.get(0).getNoGovtVisit());
                        binding.meetingInternal.setText(workPlanDataList.get(0).getMeetingAttendInternal());
                        binding.meetingExternal.setText(workPlanDataList.get(0).getMeetingAttendExternal());
                        binding.noOfTrainingAttended.setText(workPlanDataList.get(0).getNoOfTrainingAttend());
                        binding.noOfTrainingFacilated.setText(workPlanDataList.get(0).getNoOfTrainingFacilited());
                        binding.noOfOtherEvent.setText(workPlanDataList.get(0).getOtherEvents());

                    }
                }
            });

        if (statues.equals("0")) {

            binding.saveBtn.setVisibility(View.VISIBLE);

        } else if (statues.equals("1")) {

            binding.saveBtn.setVisibility(View.GONE);

            binding.noOfVillageVisit.setEnabled(false);
            binding.noOfGovtVisit.setEnabled(false);
            binding.meetingInternal.setEnabled(false);
            binding.meetingExternal.setEnabled(false);
            binding.noOfTrainingAttended.setEnabled(false);
            binding.noOfTrainingFacilated.setEnabled(false);
            binding.noOfOtherEvent.setEnabled(false);

        } else if (statues.equals("2")) {

            binding.saveBtn.setVisibility(View.GONE);

            binding.noOfVillageVisit.setEnabled(false);
            binding.noOfGovtVisit.setEnabled(false);
            binding.meetingInternal.setEnabled(false);
            binding.meetingExternal.setEnabled(false);
            binding.noOfTrainingAttended.setEnabled(false);
            binding.noOfTrainingFacilated.setEnabled(false);
            binding.noOfOtherEvent.setEnabled(false);

        } else if (statues.equals("3")) {

            binding.saveBtn.setVisibility(View.VISIBLE);
        }

        //}

        //localRepo = new LocalRepo(WorkMonthDetailActivity.this);

        System.out.println(monYr + "  " + tempId + "  " + tempUserId);

        Log.d("hhdbkjy", monYr + "  " + tempId + "  " + tempUserId);

        try {

            SimpleDateFormat sourceDateFormat = new SimpleDateFormat("MM-yyyy");
            Date date = sourceDateFormat.parse(monYr);
            SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
            strDate = formatter.format(date);
            binding.dateTx.setText(strDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //formatedMonthYr = Utils.formatdate(monYr,"MMM yyyy","MM/yyyy");
        //binding.dateTx.setText(monYr);

        //-----------------------

        //--------------------------
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (statues.equals("1")) {

                    Intent intent = new Intent(WorkMonthDetailActivity.this, MonthWorkPlanActivity.class);
                    intent.putExtra("MonthYr", monYr);
                    intent.putExtra("userid", tempUserId);
                    intent.putExtra("id", tempId);
                    startActivity(intent);

                } else if (binding.noOfVillageVisit.getText().toString().isEmpty()) {
                    binding.noOfVillageVisit.setError("Please enter no of village visit.");
                } else if (binding.noOfGovtVisit.getText().toString().isEmpty()) {
                    binding.noOfGovtVisit.setError("Please enter no of Govt visit.");
                } else if (binding.meetingInternal.getText().toString().isEmpty()) {
                    binding.meetingInternal.setError("Please enter no of Internal Meetings.");
                } else if (binding.meetingExternal.getText().toString().isEmpty()) {
                    binding.meetingExternal.setError("Please enter no of External Meetings.");
                } else if (binding.noOfTrainingAttended.getText().toString().isEmpty()) {
                    binding.noOfTrainingAttended.setError("Please enter no of Training attend");
                } else if (binding.noOfTrainingFacilated.getText().toString().isEmpty()) {
                    binding.noOfTrainingFacilated.setError("Please enter no of Training facilated");
                } else if (binding.noOfOtherEvent.getText().toString().isEmpty()) {
                    binding.noOfOtherEvent.setError("Please enter no of Other Event");
                }
                else {

                    if(Utils.isNetworkAvailable(WorkMonthDetailActivity.this)){

                        WorkPlanData workPlanData = new WorkPlanData();
                        workPlanData.setId(tempId);
                        workPlanData.setUserId(tempUserId);
                        workPlanData.setMonthYear(monYr);
                        workPlanData.setStatus("Pending");
                        workPlanData.setNoVillageVisit(binding.noOfVillageVisit.getText().toString());
                        workPlanData.setNoGovtVisit(binding.noOfGovtVisit.getText().toString());
                        workPlanData.setMeetingAttendInternal(binding.meetingInternal.getText().toString());
                        workPlanData.setMeetingAttendExternal(binding.meetingExternal.getText().toString());
                        workPlanData.setNoOfTrainingAttend(binding.noOfTrainingAttended.getText().toString());
                        workPlanData.setNoOfTrainingFacilited(binding.noOfTrainingFacilated.getText().toString());
                        workPlanData.setOtherEvents(binding.noOfOtherEvent.getText().toString());

                        localRepo.DeleteWorkPlan(workPlanData);
                        localRepo.insertWorkPlan(workPlanData);

                        String noOfVillageVisit = binding.noOfVillageVisit.getText().toString();
                        String noOfGovtVisit = binding.noOfGovtVisit.getText().toString();
                        String meetingInternal = binding.meetingInternal.getText().toString();
                        String meetingExternal = binding.meetingExternal.getText().toString();
                        String noOfTrainingAttended = binding.noOfTrainingAttended.getText().toString();
                        String noOfTrainingFacilated = binding.noOfTrainingFacilated.getText().toString();
                        String noOfOtherEvent = binding.noOfOtherEvent.getText().toString();

                        callSaveapi(tempUserId,tempId,monYr,noOfVillageVisit,noOfGovtVisit,meetingInternal,meetingExternal,
                                noOfTrainingAttended,noOfTrainingFacilated,noOfOtherEvent,"submitBtn");

                    }else{

                        //tempUserId = getRandomNumber();
                        WorkPlanData workPlanData = new WorkPlanData();
                        workPlanData.setId(tempId);
                        workPlanData.setUserId(tempUserId);
                        workPlanData.setMonthYear(monYr);
                        workPlanData.setStatus("Pending");
                        workPlanData.setNoVillageVisit(binding.noOfVillageVisit.getText().toString());
                        workPlanData.setNoGovtVisit(binding.noOfGovtVisit.getText().toString());
                        workPlanData.setMeetingAttendInternal(binding.meetingInternal.getText().toString());
                        workPlanData.setMeetingAttendExternal(binding.meetingExternal.getText().toString());
                        workPlanData.setNoOfTrainingAttend(binding.noOfTrainingAttended.getText().toString());
                        workPlanData.setNoOfTrainingFacilited(binding.noOfTrainingFacilated.getText().toString());
                        workPlanData.setOtherEvents(binding.noOfOtherEvent.getText().toString());

                        localRepo.DeleteWorkPlan(workPlanData);
                        localRepo.insertWorkPlan(workPlanData);

                        Toast.makeText(WorkMonthDetailActivity.this, "Internet is not available,data saved in locally", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(WorkMonthDetailActivity.this,MonthWorkPlanActivity.class);
                        intent.putExtra("MonthYr",monYr);
                        intent.putExtra("userid",tempUserId);
                        intent.putExtra("id",tempId);
                        startActivity(intent);

                        if(Utils.isNetworkAvailable(WorkMonthDetailActivity.this)){

                        }

                    }
                }

            }
        });

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.noOfVillageVisit.getText().toString().isEmpty()){
                    binding.noOfVillageVisit.setError("Please enter no of village visit.");
                }
                else if(binding.noOfGovtVisit.getText().toString().isEmpty()){
                    binding.noOfGovtVisit.setError("Please enter no of Govt visit.");
                }
                else if(binding.meetingInternal.getText().toString().isEmpty()){
                    binding.meetingInternal.setError("Please enter no of Internal Meetings.");
                }
                else if(binding.meetingExternal.getText().toString().isEmpty()){
                    binding.meetingExternal.setError("Please enter no of External Meetings.");
                }
                else if(binding.noOfTrainingAttended.getText().toString().isEmpty()){
                    binding.noOfTrainingAttended.setError("Please enter no of Training attend");
                }
                else if(binding.noOfTrainingFacilated.getText().toString().isEmpty()){
                    binding.noOfTrainingFacilated.setError("Please enter no of Training facilated");
                }
                else if(binding.noOfOtherEvent.getText().toString().isEmpty()){
                    binding.noOfOtherEvent.setError("Please enter no of Other Event");
                }
                else {

                    if(Utils.isNetworkAvailable(WorkMonthDetailActivity.this)){

                        WorkPlanData workPlanData = new WorkPlanData();
                        workPlanData.setId(tempId);
                        workPlanData.setUserId(tempUserId);
                        workPlanData.setMonthYear(monYr);
                        workPlanData.setStatus("Pending");
                        workPlanData.setNoVillageVisit(binding.noOfVillageVisit.getText().toString());
                        workPlanData.setNoGovtVisit(binding.noOfGovtVisit.getText().toString());
                        workPlanData.setMeetingAttendInternal(binding.meetingInternal.getText().toString());
                        workPlanData.setMeetingAttendExternal(binding.meetingExternal.getText().toString());
                        workPlanData.setNoOfTrainingAttend(binding.noOfTrainingAttended.getText().toString());
                        workPlanData.setNoOfTrainingFacilited(binding.noOfTrainingFacilated.getText().toString());
                        workPlanData.setOtherEvents(binding.noOfOtherEvent.getText().toString());

                        localRepo.DeleteWorkPlan(workPlanData);
                        localRepo.insertWorkPlan(workPlanData);

                        String noOfVillageVisit = binding.noOfVillageVisit.getText().toString();
                        String noOfGovtVisit = binding.noOfGovtVisit.getText().toString();
                        String meetingInternal = binding.meetingInternal.getText().toString();
                        String meetingExternal = binding.meetingExternal.getText().toString();
                        String noOfTrainingAttended = binding.noOfTrainingAttended.getText().toString();
                        String noOfTrainingFacilated = binding.noOfTrainingFacilated.getText().toString();
                        String noOfOtherEvent = binding.noOfOtherEvent.getText().toString();

                        callSaveapi(tempUserId,tempId,monYr,noOfVillageVisit,noOfGovtVisit,meetingInternal,meetingExternal,
                                noOfTrainingAttended,noOfTrainingFacilated,noOfOtherEvent,"saveBtn");

                    }else{

                        //tempUserId = getRandomNumber();
                        WorkPlanData workPlanData = new WorkPlanData();
                        workPlanData.setId(tempId);
                        workPlanData.setUserId(tempUserId);
                        workPlanData.setMonthYear(monYr);
                        workPlanData.setStatus("Pending");
                        workPlanData.setNoVillageVisit(binding.noOfVillageVisit.getText().toString());
                        workPlanData.setNoGovtVisit(binding.noOfGovtVisit.getText().toString());
                        workPlanData.setMeetingAttendInternal(binding.meetingInternal.getText().toString());
                        workPlanData.setMeetingAttendExternal(binding.meetingExternal.getText().toString());
                        workPlanData.setNoOfTrainingAttend(binding.noOfTrainingAttended.getText().toString());
                        workPlanData.setNoOfTrainingFacilited(binding.noOfTrainingFacilated.getText().toString());
                        workPlanData.setOtherEvents(binding.noOfOtherEvent.getText().toString());

                        localRepo.DeleteWorkPlan(workPlanData);
                        localRepo.insertWorkPlan(workPlanData);

                        Toast.makeText(WorkMonthDetailActivity.this, "Internet is not available,data saved in locally", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(WorkMonthDetailActivity.this,MonthWorkPlanActivity.class);
                        intent.putExtra("MonthYr",monYr);
                        intent.putExtra("userid",tempUserId);
                        intent.putExtra("id",tempId);
                        startActivity(intent);

                        /*if(Utils.isNetworkAvailable(WorkMonthDetailActivity.this)){

                        }*/

                    }
                }

            }
        });
    }

  /*  //-----------
    private void callSaveapi(String userId) {

        ProgressDialog pd = new ProgressDialog(WorkMonthDetailActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        String DateStr = Utils.cuurentDate();

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", userId);
        mapData.put("month_year", formatedMonthYr);
        mapData.put("no_village_visit", binding.noOfVillageVisit.getText().toString());
        mapData.put("no_govt_visit", binding.noOfGovtVisit.getText().toString());
        mapData.put("meeting_attend_internal", binding.meetingInternal.getText().toString());
        mapData.put("meeting_attend_external", binding.meetingExternal.getText().toString());
        mapData.put("no_of_training_attend", binding.noOfTrainingAttended.getText().toString());
        mapData.put("no_of_training_facilited", binding.noOfTrainingFacilated.getText().toString());
        mapData.put("other_events", binding.noOfOtherEvent.getText().toString());
        mapData.put("status", "Pending");
        System.out.println(mapData);



        CommonClass.APP_TOKEN  = CommonClass.getToken(WorkMonthDetailActivity.this);
        apiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(apiRequest.class);
        apiRequest.addActivityReport(CommonClass.APP_TOKEN,mapData).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                pd.dismiss();
                if (response.body() != null) {

                    Toast.makeText(WorkMonthDetailActivity.this, "Save Successfully", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                pd.dismiss();
            }
        });
    }*/

    public void callSaveapi(String user_id,String id,String month_year,String no_village_visit,String no_govt_visit,String meeting_attend_internal,
                            String meeting_attend_external,String no_of_training_attend,String no_of_training_facilited,String other_events,String mesagelis){

        ProgressDialog pd = new ProgressDialog(WorkMonthDetailActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        String url = "https://mis.mobility-india.org/api/activityreport/update_own";

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("user_id",user_id);
            jsonObject.put("id",id);
            jsonObject.put("month_year",month_year);
            jsonObject.put("no_village_visit",no_village_visit);
            jsonObject.put("no_govt_visit",no_govt_visit);
            jsonObject.put("meeting_attend_internal",meeting_attend_internal);
            jsonObject.put("meeting_attend_external",meeting_attend_external);
            jsonObject.put("no_of_training_attend",no_of_training_attend);
            jsonObject.put("no_of_training_facilited",no_of_training_facilited);
            jsonObject.put("other_events",other_events);

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
                    String messagelis = mesagelis;

                    if(status.equals("true")){

                        if(messagelis.equals("saveBtn")){

                            Toast.makeText(WorkMonthDetailActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(WorkMonthDetailActivity.this,MonthWorkPlanActivity.class);
                            intent.putExtra("MonthYr",monYr);
                            intent.putExtra("userid",tempUserId);
                            intent.putExtra("id",id);
                            startActivity(intent);

                        }else{

                            //Toast.makeText(WorkMonthDetailActivity.this, message, Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(WorkMonthDetailActivity.this,MonthWorkPlanActivity.class);
                            intent.putExtra("MonthYr",monYr);
                            intent.putExtra("userid",tempUserId);
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }

                    }else{

                        Toast.makeText(WorkMonthDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pd.dismiss();
                Toast.makeText(WorkMonthDetailActivity.this, ""+error, Toast.LENGTH_SHORT).show();
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

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(WorkMonthDetailActivity.this);
        requestQueue.add(jsonObjectRequest);
    }
}