package com.example.mobilityindia.social.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivitySocialMainBinding;
import com.example.mobilityindia.social.adapter.SocialAdapter;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.ArrayList;
import java.util.List;

public class SocialMainActivity extends AppCompatActivity
{
    ActivitySocialMainBinding binding;
    LocalRepo localRepo;
    String benfeciaryID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_social_main);
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
        localRepo = new LocalRepo(SocialMainActivity.this);
        binding.btnaddbanificary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SocialMainActivity.this, AddSocialActivity.class));
            }
        });

        benfeciaryID = String.valueOf(CommonClass.benfeciary_ID);

        if(benfeciaryID.equals("") || benfeciaryID.equals("null")){

            getdatehealthdate();

        }else{

            getdatehealthdate1();
        }


    }

    private void getdatehealthdate() {
        localRepo.getSocialCreatedList(CommonClass.tempid).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> caselist) {
                System.out.println("SIZE>>>>>> "+caselist.size());

                if(caselist.size() > 0)
                {
                    binding.recycleview.setLayoutManager(new LinearLayoutManager(SocialMainActivity.this, RecyclerView.VERTICAL, false));
                    SocialAdapter healthAdapter = new SocialAdapter(new ArrayList<>(caselist),SocialMainActivity.this);
                    binding.recycleview.setAdapter(healthAdapter);
                    healthAdapter.notifyDataSetChanged();
                }
            }
        });

    }

    private void getdatehealthdate1() {

        localRepo.getSocialCreatedList1(CommonClass.benfeciary_ID).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> caselist) {
                System.out.println("SIZE>>>>>> "+caselist.size());

                if(caselist.size() > 0)
                {
                    binding.recycleview.setLayoutManager(new LinearLayoutManager(SocialMainActivity.this, RecyclerView.VERTICAL, false));
                    SocialAdapter healthAdapter = new SocialAdapter(new ArrayList<>(caselist),SocialMainActivity.this);
                    binding.recycleview.setAdapter(healthAdapter);
                    healthAdapter.notifyDataSetChanged();
                }else{

                    //Toast.makeText(SocialMainActivity.this, "Data Is Not Avilable", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        getdatehealthdate();
        super.onResume();
    }
}