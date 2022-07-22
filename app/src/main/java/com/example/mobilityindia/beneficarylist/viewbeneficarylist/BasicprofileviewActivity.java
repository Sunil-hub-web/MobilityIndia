package com.example.mobilityindia.beneficarylist.viewbeneficarylist;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.beneficarylist.editbeneficarylist.EditBasicProfileActivity;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityBasicProfileViewBinding;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.List;

public class BasicprofileviewActivity extends AppCompatActivity {
    ActivityBasicProfileViewBinding binding;
    LocalRepo localRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_basic_profile_view);
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
        binding.filtericon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(BasicprofileviewActivity.this, EditBasicProfileActivity.class));
            }
        });
        localRepo = new LocalRepo(BasicprofileviewActivity.this);
        callofflinelocaldatdata();

    }
    private void callofflinelocaldatdata()
    {
        localRepo.getSelectedBene(Integer.valueOf(CommonClass.benfeciary_ID)).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {
                if(singleMember.size() > 0) {
                    binding.name.setText(singleMember.get(0).name);
                    binding.parent.setText(singleMember.get(0).parentName);
                    binding.relation.setText(singleMember.get(0).relation);
                    binding.dateofbrth.setText(singleMember.get(0).dob);
                    binding.age.setText(singleMember.get(0).age);
                    binding.gender.setText(singleMember.get(0).gender);
                    binding.caste.setText(singleMember.get(0).casteName);
                    binding.religion.setText(singleMember.get(0).religionName);
                    binding.adharno.setText(singleMember.get(0).adhaarNo);
                    binding.economicstatu.setText(singleMember.get(0).economicName);
                    binding.anualincme.setText(singleMember.get(0).annualName);
                    binding.maritalstatus.setText(singleMember.get(0).maritalName);
                    binding.edu.setText(singleMember.get(0).educationName);
                    binding.occuption.setText(singleMember.get(0).occupationName);
                    binding.typeofdisability.setText(singleMember.get(0).disabilityName);
                    binding.typeofsubdisability.setText(singleMember.get(0).subDisabilityName);
                    binding.percentofdisability.setText(singleMember.get(0).percentageOfDisability);
                    binding.idcardno.setText(singleMember.get(0).idCardNo);
                    binding.phpamount.setText(singleMember.get(0).phpAmount);
                    binding.typeofbeneficary.setText(singleMember.get(0).typeOfBeneficiary);
                    binding.purposeofvisit.setText(singleMember.get(0).visitName);

                }
            }
        });
    }

    @Override
    protected void onStart() {
        callofflinelocaldatdata();
        super.onStart();
    }
}