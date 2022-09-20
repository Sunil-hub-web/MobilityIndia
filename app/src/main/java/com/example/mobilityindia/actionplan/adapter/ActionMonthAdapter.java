package com.example.mobilityindia.actionplan.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.mobilityindia.actionplan.MonthActionPlanActivity;
import com.example.mobilityindia.sync.model.ActionPlanMonth;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.utils.Utils;
import com.example.mobilityindia.workplan.MonthWorkPlanActivity;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ActionMonthAdapter extends RecyclerView.Adapter<ActionMonthAdapter.ViewHolder> {

    //private ArrayList<String> data2 = new ArrayList<>();
    private final Activity context;
    Dialog dialog;
    TextView planTxt, outcome, remark_txt;
    MaterialButton cancelBtn, saveBtn;
    SessinoManager sessinoManager;
    private final List<ActionPlanMonth> data;
    LocalRepo localRepo;

    public ActionMonthAdapter(Activity context, List<ActionPlanMonth> data) {
        this.data = data;
        //this.data2 = data2;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_row_layout, parent, false);

        return new ViewHolder(rowItem);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        localRepo = new LocalRepo(context);
        sessinoManager = new SessinoManager(context);

        String ststues = sessinoManager.getMONTHSTATUS();

        ActionPlanMonth plandetails = data.get(position);
        holder.row_txt.setText(plandetails.getDate());

        String plan = plandetails.getPlan();
        plan = String.valueOf(plan);

        if (plan.equals("null")) {

            holder.row_txt.setBackgroundTintList(context.getResources().getColorStateList(R.color.purple_500));

        }else if(plan.equals("")) {

            holder.row_txt.setBackgroundTintList(context.getResources().getColorStateList(R.color.purple_500));

        }else {

            holder.row_txt.setBackgroundTintList(context.getResources().getColorStateList(R.color.Green));
        }

        holder.row_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ststues.equalsIgnoreCase("submitted")){

                    //saveBtn.setFocusable(false);

                    String str_id = plandetails.getId();
                    String userid = plandetails.getUserId();
                    String monthyear = plandetails.getMonthYear();
                    String date = plandetails.getDate();
                    String name = plandetails.getName();
                    String createdby = plandetails.getCreatedBy();
                    String createdat = plandetails.getCreatedAt();
                    String usertypeid = plandetails.getUserTypeId();

                    OpenDialouge(str_id, userid, monthyear,date,name,createdby,createdat,usertypeid);

                    planTxt.setText(plandetails.getPlan());
                    outcome.setText(plandetails.getResult());
                    remark_txt.setText(plandetails.getRemarks());

                    planTxt.setEnabled(false);
                    outcome.setEnabled(false);
                    remark_txt.setEnabled(false);

                    saveBtn.setVisibility(View.GONE);

                }else if(ststues.equalsIgnoreCase("approved")) {

                    //saveBtn.setFocusable(false);

                    String str_id = plandetails.getId();
                    String userid = plandetails.getUserId();
                    String monthyear = plandetails.getMonthYear();
                    String date = plandetails.getDate();
                    String name = plandetails.getName();
                    String createdby = plandetails.getCreatedBy();
                    String createdat = plandetails.getCreatedAt();
                    String usertypeid = plandetails.getUserTypeId();
                    OpenDialouge(str_id, userid, monthyear,date,name,createdby,createdat,usertypeid);

                    planTxt.setText(plandetails.getPlan());
                    outcome.setText(plandetails.getResult());
                    remark_txt.setText(plandetails.getRemarks());

                    planTxt.setEnabled(false);
                    outcome.setEnabled(false);
                    remark_txt.setEnabled(false);

                    //saveBtn.setEnabled(false);
                    saveBtn.setVisibility(View.GONE);

                }else {

                    String str_id = plandetails.getId();
                    String userid = plandetails.getUserId();
                    String monthyear = plandetails.getMonthYear();
                    String date = plandetails.getDate();
                    String name = plandetails.getName();
                    String createdby = plandetails.getCreatedBy();
                    String createdat = plandetails.getCreatedAt();
                    String usertypeid = plandetails.getUserTypeId();


                    String result = plandetails.getResult();
                    result = String.valueOf(result);
                    if (result.equals("null")) {

                        OpenDialouge(str_id, userid, monthyear,date,name,createdby,createdat,usertypeid);

                    } else {

                        OpenDialouge(str_id, userid, monthyear,date,name,createdby,createdat,usertypeid);

                        planTxt.setText(plandetails.getPlan());
                        outcome.setText(plandetails.getResult());
                        remark_txt.setText(plandetails.getRemarks());

                        //saveBtn.setFocusable(true);

                        saveBtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }

    private void OpenDialouge(String id, String userid, String monthyear ,String date,String name,String createdby,
                              String createdat,String usertypeid) {


        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.action_dialouge);
        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        planTxt = dialog.findViewById(R.id.planTxt);
        outcome = dialog.findViewById(R.id.outcome);
        remark_txt = dialog.findViewById(R.id.remark_txt);
        saveBtn = dialog.findViewById(R.id.saveBtn);
        cancelBtn = dialog.findViewById(R.id.cancelBtn);

        String statues = sessinoManager.getMONTHSTATUS();


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (planTxt.getText().toString().equalsIgnoreCase("")) {
                    planTxt.setError("Please enter your Plan");
                } else if (outcome.getText().toString().equalsIgnoreCase("")) {
                    outcome.setError("Please enter your Outcome");
                } else if (remark_txt.getText().toString().equalsIgnoreCase("")) {
                    remark_txt.setError("Please enter your Remark");
                } else {

                    String planTx = planTxt.getText().toString();
                    String outcomeTx = outcome.getText().toString();
                    String remarkTx = remark_txt.getText().toString();

                    int int_id = Integer.parseInt(id);

                    if (Utils.isNetworkAvailable(context)) {

                        ActionPlanMonth actionPlanMonth = new ActionPlanMonth();

                        actionPlanMonth.setId(id);
                        actionPlanMonth.setUserId(userid);
                        actionPlanMonth.setMonthYear(monthyear);
                        actionPlanMonth.setPlan(planTx);
                        actionPlanMonth.setResult(outcomeTx);
                        actionPlanMonth.setRemarks(remarkTx);
                        actionPlanMonth.setDate(date);
                        actionPlanMonth.setName(name);
                        actionPlanMonth.setCreatedBy(createdby);
                        actionPlanMonth.setCreatedAt(createdat);
                        actionPlanMonth.setUserTypeId(usertypeid);
                        actionPlanMonth.setFlag("update");

                        localRepo.updateActionPlanMonth(actionPlanMonth);

                        //callSaveapi(id,planTx,outcomeTx,remarkTx);
                        Toast.makeText(context, "Data Saved in Locally", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                        context.finish();
                        context.overridePendingTransition( 0, 0);
                        context.startActivity(context.getIntent());
                        context.overridePendingTransition( 0, 0);


                    } else {

                        ActionPlanMonth actionPlanMonth = new ActionPlanMonth();

                        actionPlanMonth.setId(id);
                        actionPlanMonth.setUserId(userid);
                        actionPlanMonth.setMonthYear(monthyear);
                        actionPlanMonth.setPlan(planTx);
                        actionPlanMonth.setResult(outcomeTx);
                        actionPlanMonth.setRemarks(remarkTx);
                        actionPlanMonth.setDate(date);
                        actionPlanMonth.setName(name);
                        actionPlanMonth.setCreatedBy(createdby);
                        actionPlanMonth.setCreatedAt(createdat);
                        actionPlanMonth.setUserTypeId(usertypeid);
                        actionPlanMonth.setFlag("update");

                        localRepo.updateActionPlanMonth(actionPlanMonth);

                        Toast.makeText(context, "Data Saved in Locally", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                        context.finish();
                        context.overridePendingTransition( 0, 0);
                        context.startActivity(context.getIntent());
                        context.overridePendingTransition( 0, 0);
                    }
                }
            }
        });


        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void callSaveapi(String id, String plan, String result, String remarks) {

        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Store Data");
        progressDialog.show();


        String url = "https://midev.zbapps.in/api/updateplan";

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("id", id);
            jsonObject.put("plan", plan);
            jsonObject.put("result", result);
            jsonObject.put("remarks", remarks);

        } catch (JSONException e) {
            e.printStackTrace();
        }



        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");
                    String data = response.getString("data");

                    if(status.equals("true")){

                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                    }else{

                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(context, ""+error, Toast.LENGTH_SHORT).show();

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

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 3, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }


    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView row_txt;
        private final LinearLayout rowitem;


        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.row_txt = view.findViewById(R.id.row_txt);
            this.rowitem = view.findViewById(R.id.rowitem);
        }

        @Override
        public void onClick(View view) {


        }
    }

}
