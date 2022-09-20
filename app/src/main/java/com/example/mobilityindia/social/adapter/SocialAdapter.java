package com.example.mobilityindia.social.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.social.view.SocialActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class SocialAdapter extends RecyclerView.Adapter<SocialAdapter.ViewHolder> {

    private final Activity context;
    private ArrayList<String> data = new ArrayList<>();

    public SocialAdapter(ArrayList<String> data, Activity context) {
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
        holder.materialButton.setText(data.get(position));
        holder.materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonClass.datestring = data.get(position);
                Intent intent = new Intent(context, SocialActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       // private final TextView textView;
        private final RelativeLayout relativeLayout;
        private final MaterialButton materialButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.textView = itemView.findViewById(R.id.textdate);
            this.relativeLayout = itemView.findViewById(R.id.healthrelation);
            this.materialButton = itemView.findViewById(R.id.textdate);

        }
    }

}
