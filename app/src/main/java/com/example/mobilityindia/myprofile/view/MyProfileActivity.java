package com.example.mobilityindia.myprofile.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.changepin.view.ChangepiinActivity;
import com.example.mobilityindia.constant.AppUtils;
import com.example.mobilityindia.databinding.ActivityMyProfileBinding;
import com.example.mobilityindia.myprofile.view.myprofileviewmodel.MyProfileViewModel;


public class MyProfileActivity extends AppCompatActivity {
    SessinoManager sessinoManager;
    private ActivityMyProfileBinding binding;
    private MyProfileViewModel myProfileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_profile);
        myProfileViewModel = new ViewModelProvider(this).get(MyProfileViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setMyProfileViewModel(myProfileViewModel);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        sessinoManager = new SessinoManager(MyProfileActivity.this);

        if (AppUtils.isNetworkAvailable(MyProfileActivity.this)) {

            String userId = sessinoManager.getUSERID();
            myProfileViewModel.onClick(userId);

        }else {
            Toast.makeText(this, "Please Check your Internet Connection", Toast.LENGTH_SHORT).show();
        }

        binding.btnchangepin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyProfileActivity.this, ChangepiinActivity.class));
            }
        });

    }
}