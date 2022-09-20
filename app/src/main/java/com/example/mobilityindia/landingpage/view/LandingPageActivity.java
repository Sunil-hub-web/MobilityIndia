package com.example.mobilityindia.landingpage.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mobilityindia.R;
import com.example.mobilityindia.actionplan.ActionPlanActivity;
import com.example.mobilityindia.attendance.Attendance;
import com.example.mobilityindia.beneficarylist.view.BeneficaryListActivity;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityLandingPageBinding;
import com.example.mobilityindia.more.MoreActivity;
import com.example.mobilityindia.social.view.SocialActivity;
import com.example.mobilityindia.workplan.WorkPlanActivity;

public class LandingPageActivity extends AppCompatActivity {
    private ActivityLandingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_landing_page);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        String userId =  CommonClass.USERiD;

        //Toast.makeText(this, userId, Toast.LENGTH_SHORT).show();

        Log.d("suniluserid",userId);

        binding.moreactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPageActivity.this, MoreActivity.class));
            }
        });

        binding.beneficarylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPageActivity.this, BeneficaryListActivity.class));

            }
        });
        binding.activityPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPageActivity.this, ActionPlanActivity.class));

            }
        });
        binding.workplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPageActivity.this, WorkPlanActivity.class));
            }
        });

       /* binding.servicelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPageActivity.this, SocialActivity.class));
            }
        });*/

        binding.attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LandingPageActivity.this, Attendance.class));

            }
        });
    }

}