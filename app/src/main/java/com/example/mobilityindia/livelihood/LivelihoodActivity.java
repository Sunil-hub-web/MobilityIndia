package com.example.mobilityindia.livelihood;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityLivelihoodBinding;
import com.example.mobilityindia.sync.model.LivehoodData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.List;

public class LivelihoodActivity extends AppCompatActivity {
    ActivityLivelihoodBinding binding;
    LocalRepo localRepo;
    String switchskilldevelopment = "",financialservices = "",socialsecurity = "",vocationtraning = "",serviceother = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_livelihood);
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
        localRepo = new LocalRepo(LivelihoodActivity.this);

        binding.nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LivelihoodActivity.this, EditLivelihoodAtivity.class));
            }
        });

        callofflinedata();

    }

    @Override
    protected void onResume() {
        callofflinedata();
        super.onResume();
    }

    private void callofflinedata()
    {
        localRepo.getSelectedLivelihoodDate((CommonClass.datestring)).observe(this, new Observer<List<LivehoodData>>() {
            @Override
            public void onChanged(@Nullable List<LivehoodData> singleMember) {
                if(singleMember.size() > 0)
                {
                    binding.familylivelihood.setText(singleMember.get(0).getLivelihood());

                    binding.otherspecify.setText(singleMember.get(0).getLivelihoodother());

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;

                    switchskilldevelopment = singleMember.get(0).getSkilldev();
                    if(switchskilldevelopment.equalsIgnoreCase("yes")||switchskilldevelopment.equalsIgnoreCase("true")){
                        binding.switchskilldevelopment.setOn(true);
                        binding.skillldaveloplayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.switchskilldevelopment.setOn(false);
                        binding.skillldaveloplayout.setVisibility(View.GONE);

                    }
                    binding.skillwhen.setText(singleMember.get(0).getSkilldevwhen());
                    binding.skillwhere.setText(singleMember.get(0).getSkilldevwhere());
                    binding.skillwhat.setText(singleMember.get(0).getSkilldevwhat());


                    financialservices = singleMember.get(0).getFinservice();
                    if(financialservices.equalsIgnoreCase("yes")||financialservices.equalsIgnoreCase("true")){
                        binding.financialservices.setOn(true);
                        binding.financiallayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.financialservices.setOn(false);
                        binding.financiallayout.setVisibility(View.GONE);
                    }
                    binding.finencialwhen.setText(singleMember.get(0).getFinservicewhen());
                    binding.finencialwhere.setText(singleMember.get(0).getFinservicewhere());
                    binding.finencialwhat.setText(singleMember.get(0).getFinservicewhat());

                    socialsecurity = singleMember.get(0).getSocialsecurity();
                    if(socialsecurity.equalsIgnoreCase("yes")||socialsecurity.equalsIgnoreCase("true")){
                        binding.socialsecurity.setOn(true);
                        binding.sociallayout.setVisibility(View.VISIBLE);

                    }else{
                        binding.socialsecurity.setOn(false);
                        binding.sociallayout.setVisibility(View.GONE);

                    }
                    binding.socialwhen.setText(singleMember.get(0).getSocialsecuritywhen());
                    binding.socialwhere.setText(singleMember.get(0).getSocialsecuritywhere());
                    binding.socialwhat.setText(singleMember.get(0).getSocialsecuritywhat());


                    vocationtraning = singleMember.get(0).getVocationaltraining();
                    if(vocationtraning.equalsIgnoreCase("yes")||vocationtraning.equalsIgnoreCase("true")){
                        binding.vocationtraning.setOn(true);
                        binding.vocationlayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.vocationtraning.setOn(false);
                        binding.vocationlayout.setVisibility(View.GONE);
                    }
                    binding.vocationwhen.setText(singleMember.get(0).getVocationaltrainingWhen());
                    binding.vocationwhere.setText(singleMember.get(0).getVocationaltrainingWhere());
                    binding.vocationwhat.setText(singleMember.get(0).getVocationaltrainingWhat());

                    serviceother = singleMember.get(0).getServiceother();
                    if(serviceother.equalsIgnoreCase("yes")||serviceother.equalsIgnoreCase("true")){
                        binding.serviceother.setOn(true);
                        binding.servicelotherlayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.serviceother.setOn(false);
                        binding.servicelotherlayout.setVisibility(View.GONE);

                    }
                    binding.servicewhen.setText(singleMember.get(0).getServiceotherwhen());
                    binding.servicewhere.setText(singleMember.get(0).getServiceotherwhere());
                    binding.servicewhat.setText(singleMember.get(0).getServiceotherwhat());


                }
            }
        });
    }
}