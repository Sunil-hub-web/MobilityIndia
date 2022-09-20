package com.example.mobilityindia.education.educationadapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.education.EducationActivity;
import com.example.mobilityindia.sync.model.EducationData;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;


public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> {
    private final Activity context;
    private List<String> data = new ArrayList<>();

    public EducationAdapter(List<String> data, Activity context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.healthlayout, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String educationData = data.get(position);

        //Log.d("sunilarraydata0",educationData);

        holder.textView.setText(educationData);

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommonClass.datestring = educationData;

                Intent intent = new Intent(context, EducationActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialButton textView;
        private final RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.textdate);
            this.relativeLayout = itemView.findViewById(R.id.healthrelation);

        }
    }
}
