package com.example.mobilityindia.workplan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.sync.model.WorkPlanData;
import com.example.mobilityindia.workplan.MonthWorkPlanActivity;
import com.example.mobilityindia.workplan.WorkMonthDetailActivity;
import com.example.mobilityindia.workplan.WorkPlanActivity;
import com.google.android.material.button.MaterialButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WorkingMonthPlanAdapter extends RecyclerView.Adapter<WorkingMonthPlanAdapter.ViewHolder> {

    Context context;
    List<WorkPlanData> monthplan;
    //OnNoteListener monNoteListener;

    String strDate;

    SessinoManager sessinoManager;

    public WorkingMonthPlanAdapter(List<WorkPlanData> monthplan, WorkPlanActivity workPlanActivity) {

        this.monthplan = monthplan;
        this.context = workPlanActivity;
        //this.monNoteListener = monNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_plan,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        WorkPlanData planmonth = monthplan.get(position);
        //holder.sevenBtn.setText(planmonth.getMonth_year());

        sessinoManager = new SessinoManager(context);

        //Log.d("ghchv",status1);

        try {

            SimpleDateFormat sourceDateFormat = new SimpleDateFormat("MM-yyyy");
            Date date = sourceDateFormat.parse(planmonth.getMonthYear());
            SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy");
            strDate = formatter.format(date);
            holder.sevenBtn.setText(strDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String plan = planmonth.getStatus();
        plan = String.valueOf(plan);

        if (plan.equals("0")) {

            holder.sevenBtn.setBackgroundTintList(context.getResources().getColorStateList(R.color.purple_500));

        } else if (plan.equals("1")) {

            holder.sevenBtn.setBackgroundTintList(context.getResources().getColorStateList(R.color.Yellow));

        } else if (plan.equals("2")) {

            holder.sevenBtn.setBackgroundTintList(context.getResources().getColorStateList(R.color.Green));

        } else if (plan.equals("3")) {

            holder.sevenBtn.setBackgroundTintList(context.getResources().getColorStateList(R.color.dimredcolor));
        }
    }

    @Override
    public int getItemCount() {
        return monthplan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

         MaterialButton sevenBtn;
         //OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sevenBtn = itemView.findViewById(R.id.sevenBtn);
            itemView.setOnClickListener(this);

            sevenBtn.setOnClickListener(this);

            //this.onNoteListener = onNoteListener;
        }

        @Override
        public void onClick(View v) {

            //onNoteListener.onNoteClick(getAdapterPosition());

            int position = this.getAdapterPosition();
            WorkPlanData planmonth1 = monthplan.get(position);

            sessinoManager.setMONTHSTATUS(planmonth1.getStatus());

            sessinoManager.setID(planmonth1.getId());
            sessinoManager.setUSERID(planmonth1.getUserId());
            sessinoManager.setMonthYear(planmonth1.getMonthYear());

            String plan = planmonth1.getStatus();
            plan = String.valueOf(plan);

            if (plan.equals("0")) {

                Intent intent = new Intent(context, WorkMonthDetailActivity.class);
                intent.putExtra("userid", planmonth1.getUserId());
                intent.putExtra("id", planmonth1.getId());
                intent.putExtra("MonthYr", planmonth1.getMonthYear());
                intent.putExtra("statues", planmonth1.getStatus());
                context.startActivity(intent);

            } else if (plan.equals("2")) {

                Intent intent = new Intent(context, MonthWorkPlanActivity.class);
                intent.putExtra("userid", planmonth1.getUserId());
                intent.putExtra("id", planmonth1.getId());
                intent.putExtra("MonthYr", planmonth1.getMonthYear());
                intent.putExtra("statues", planmonth1.getStatus());
                context.startActivity(intent);

            } else {

                Intent intent = new Intent(context, WorkMonthDetailActivity.class);
                intent.putExtra("userid", planmonth1.getUserId());
                intent.putExtra("id", planmonth1.getId());
                intent.putExtra("MonthYr", planmonth1.getMonthYear());
                /*intent.putExtra("noOfVillageVisit", planmonth1.getNoVillageVisit());
                intent.putExtra("noOfGovtVisit", planmonth1.getNoGovtVisit());
                intent.putExtra("meetingInternal", planmonth1.getMeetingAttendInternal());
                intent.putExtra("meetingExternal", planmonth1.getMeetingAttendExternal());
                intent.putExtra("noOfTrainingAttended", planmonth1.getNoOfTrainingAttend());
                intent.putExtra("noOfTrainingFacilated", planmonth1.getNoOfTrainingFacilited());
                intent.putExtra("noOfOtherEvent", planmonth1.getOtherEvents());*/
                intent.putExtra("statues", planmonth1.getStatus());
                context.startActivity(intent);
            }

        }
    }

    /*public interface OnNoteListener{

        void onNoteClick(int position);
    }*/
}
