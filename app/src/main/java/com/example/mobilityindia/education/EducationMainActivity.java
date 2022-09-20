package com.example.mobilityindia.education;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEducationMainBinding;
import com.example.mobilityindia.education.educationadapter.EducationAdapter;
import com.example.mobilityindia.sync.model.EducationData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.ArrayList;
import java.util.List;

public class EducationMainActivity extends AppCompatActivity {
    ActivityEducationMainBinding binding;
    LocalRepo localRepo;
    String benid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_education_main);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        localRepo = new LocalRepo(EducationMainActivity.this);
        binding.btnaddbanificary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EducationMainActivity.this, AddEducationActivity.class));
            }
        });


        benid = String.valueOf(CommonClass.benfeciary_ID);

        if(benid.equals("") || benid.equals("null")){

            getdatehealthdate();

        }else{

            getdatehealthdate1();
        }

        binding.mSwipRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if(benid.equals("") || benid.equals("null")){

                    getdatehealthdate();

                }else{

                    getdatehealthdate1();
                }

                binding.mSwipRefreshLayout.setRefreshing(false);

            }
        });

        //Log.d("sunilbenid",benid);

    }

    @Override
    protected void onResume() {
        getdatehealthdate();
        super.onResume();
    }

    private void getdatehealthdate()
    {

        localRepo.getSelectedCreatd(CommonClass.tempid).observe(EducationMainActivity.this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> educationData) {

             if(educationData.size() != 0 ){

                 binding.recycleview.setLayoutManager(new LinearLayoutManager(EducationMainActivity.this, RecyclerView.VERTICAL, false));
                 EducationAdapter healthAdapter = new EducationAdapter(educationData,EducationMainActivity.this);
                 binding.recycleview.setAdapter(healthAdapter);
                 healthAdapter.notifyDataSetChanged();


             }else{

                 //Toast.makeText(EducationMainActivity.this, "Eduction List Not There", Toast.LENGTH_SHORT).show();
             }


            }
        });

    }

    private void getdatehealthdate1()
    {

        localRepo.getSelectedCreatd1(CommonClass.benfeciary_ID).observe(EducationMainActivity.this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> educationData) {

                if(educationData.size() != 0 ){

                    binding.recycleview.setLayoutManager(new LinearLayoutManager(EducationMainActivity.this, RecyclerView.VERTICAL, false));
                    EducationAdapter healthAdapter = new EducationAdapter(educationData,EducationMainActivity.this);
                    binding.recycleview.setAdapter(healthAdapter);
                    healthAdapter.notifyDataSetChanged();


                }else{

                    //Toast.makeText(EducationMainActivity.this, "Eduction List Not There", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(benid.equals("") || benid.equals("null")){

            getdatehealthdate();

        }else{

            getdatehealthdate1();
        }
    }
}