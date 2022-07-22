package com.example.mobilityindia.actionplan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.actionplan.ActionPlanActivity;
import com.example.mobilityindia.actionplan.MonthActionPlanActivity;
import com.example.mobilityindia.actionplan.model.Plans_ModelClass;
import com.example.mobilityindia.sync.model.ActionPlanData;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ActionMonthPlanAdapter extends RecyclerView.Adapter<ActionMonthPlanAdapter.ViewHolder> {

    Context context;
    List<ActionPlanData> monthplan;
    ArrayList<Plans_ModelClass> plans_modelClasses = new ArrayList<>();

    SessinoManager sessinoManager;

    public ActionMonthPlanAdapter(List<ActionPlanData> monthplan, ActionPlanActivity actionPlanActivity) {

        this.monthplan = monthplan;
        this.context = actionPlanActivity;
    }

    @NonNull
    @Override
    public ActionMonthPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.work_plan,parent,false);
        return new ActionMonthPlanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionMonthPlanAdapter.ViewHolder holder, int position) {

        sessinoManager = new SessinoManager(context);

        ActionPlanData planmonth = monthplan.get(position);
        holder.sevenBtn.setText(planmonth.getMonthYear());

        //Log.d("hdygjbdjb", planmonth.getStatus());

        String plan = planmonth.getStatus();
        plan = String.valueOf(plan);


        if (plan.equals("draft")) {

            holder.sevenBtn.setBackgroundTintList(context.getResources().getColorStateList(R.color.Blue));

        } else if (plan.equals("submitted")) {

            holder.sevenBtn.setBackgroundTintList(context.getResources().getColorStateList(R.color.Yellow));

        } else if (plan.equals("approved")) {

            holder.sevenBtn.setBackgroundTintList(context.getResources().getColorStateList(R.color.Green));

        } else if (plan.equals("rework")) {

            holder.sevenBtn.setBackgroundTintList(context.getResources().getColorStateList(R.color.dimredcolor));
        }

        holder.sevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //plans_modelClasses = planmonth.getPalndetails();

              /*  Intent intent = new Intent(context, MonthActionPlanActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)plans_modelClasses);
                intent.putExtra("BUNDLE",args);
                context.startActivity(intent);*/

                sessinoManager.setMONTHSTATUS(planmonth.getStatus());

                Intent intent = new Intent(context, MonthActionPlanActivity.class);
                intent.putExtra("MonthYr", planmonth.getMonthYear());
                intent.putExtra("userid", planmonth.getUserId());
                intent.putExtra("id", planmonth.getId());
                intent.putExtra("status", planmonth.getStatus());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return monthplan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MaterialButton sevenBtn;
        //OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sevenBtn = itemView.findViewById(R.id.sevenBtn);
        }
    }
}
