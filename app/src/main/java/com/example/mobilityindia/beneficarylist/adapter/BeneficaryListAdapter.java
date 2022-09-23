package com.example.mobilityindia.beneficarylist.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.beneficarylist.view.BeneficaryDetailActivity;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.sync.model.BeneData;

import java.util.ArrayList;

public class BeneficaryListAdapter extends RecyclerView.Adapter<BeneficaryListAdapter.ViewHolder> {
    private final Activity context;
    private ArrayList<BeneData> data = new ArrayList<>();

    SessinoManager sessinoManager;

    public BeneficaryListAdapter(ArrayList<BeneData> data, Activity context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.beneficarylaistlayout, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        sessinoManager = new SessinoManager(context);

        holder.nameMember.setText(data.get(position).getName());
        holder.swatchId.setText(data.get(position).getAge());
        holder.kkpID.setText(data.get(position).getRegistrationNo());

        holder.rowitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonClass.benfeciary_ID = data.get(position).getBeneficiaryId();
                CommonClass.tempid = data.get(position).getTempId();

                sessinoManager.setbenfeciaryID(data.get(position).getBeneficiaryId());

                SharedPreferences shared_bendata = context.getSharedPreferences("bendata",context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared_bendata.edit();
                editor.putString("benificaryregno1",data.get(position).getRegistrationNo());
                editor.putString("benificaryregdate1",data.get(position).getRegistrationDate());
                editor.putString("tempid1",data.get(position).getTempId());
                editor.putString("name1",data.get(position).getName());
                editor.commit();

                Intent intent = new Intent(context, BeneficaryDetailActivity.class);
                intent.putExtra("CBOName", data.get(position).getCboName());
                intent.putExtra("membershg", data.get(position).getWheatherCboMember());
                intent.putExtra("dateofcbo", data.get(position).getStartYearOfCbo());
                intent.putExtra("dojcbo", data.get(position).getYearJoinCbo());
                intent.putExtra("name", data.get(position).getName());
                intent.putExtra("benificaryregno", data.get(position).getRegistrationNo());
                intent.putExtra("benificaryregdate", data.get(position).getRegistrationDate());
                intent.putExtra("tempid", data.get(position).getTempId());
                //intent.putExtra("benfeciary_ID", data.get(position).getBeneficiaryId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameMember;
        private final TextView swatchId;
        private final TextView kkpID;
        private final RelativeLayout rowitem;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.nameMember = view.findViewById(R.id.et_patientname);
            this.swatchId = view.findViewById(R.id.dateofprocedure);
            this.kkpID = view.findViewById(R.id.procedure);
            this.rowitem = view.findViewById(R.id.rowid);
        }
    }
}
