package com.example.mobilityindia.workplan.adapter;

import static com.example.mobilityindia.utils.Utils.getRandomNumber;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilityindia.R;
import com.example.mobilityindia.actionplan.model.ActionPlanResponseL;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WorkMonthAdapter extends RecyclerView.Adapter<WorkMonthAdapter.ViewHolder> {

    private final Activity context;
    private ArrayList<String> data = new ArrayList<>();
    private ArrayList<String> data2 = new ArrayList<>();


    public WorkMonthAdapter(Activity context, ArrayList<String> data,ArrayList<String> data2){
        this.data = data;
        this.data2 = data2;
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

        holder.row_txt.setText(data.get(position));


        holder.row_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialouge();

            }
        });
    }

    private void OpenDialouge() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.attendance_dialoug);
       // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView attandance_edt =dialog.findViewById(R.id.attandance_edt);
        TextView first_half =dialog.findViewById(R.id.first_half);
        TextView second_half_txt =dialog.findViewById(R.id.second_half_txt);
        TextView saveBtn = dialog.findViewById(R.id.saveBtn);
        TextView cancelBtn = (TextView) dialog.findViewById(R.id.cancelBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(attandance_edt.getText().toString().equalsIgnoreCase("")){
                    attandance_edt.setError("Please enter your Attendance");
                }else if(first_half.getText().toString().equalsIgnoreCase("")){
                    first_half.setError("Please enter your First half attendance");
                }
                else if(second_half_txt.getText().toString().equalsIgnoreCase("")){
                    second_half_txt.setError("Please enter your second half attendance");
                }else{
                    String planTx = attandance_edt.getText().toString();
                    String outcomeTx = first_half.getText().toString();
                    String remarkTx = second_half_txt.getText().toString();
               //callSaveapi(planTx,outcomeTx,remarkTx,dialog);
                }
            }
        });


        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void callSaveapi(String planTx, String outcomeTx, String remarkTx,Dialog dialog) {

        ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        String DateStr = Utils.cuurentDate();

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", getRandomNumber());
        mapData.put("remarks", remarkTx);
        mapData.put("status", "Submitted");
        mapData.put("plan", planTx);
        mapData.put("result", outcomeTx);
        mapData.put("date", DateStr);
        System.out.println(mapData);

        CommonClass.APP_TOKEN  = CommonClass.getToken(context);
        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.addActionPlan(CommonClass.APP_TOKEN,mapData).enqueue(new Callback<ActionPlanResponseL>() {
            @Override
            public void onResponse(Call<ActionPlanResponseL> call, Response<ActionPlanResponseL> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
               pd.dismiss();
                if (response.body() != null) {

                    dialog.dismiss();


                }
            }
            @Override
            public void onFailure(Call<ActionPlanResponseL> call, Throwable t) {
               pd.dismiss();
            }
        });


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
