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
    String switchskilldevelopment = "",financialservices = "",socialsecurity = "",vocationtraning = "",
            serviceother = "",benfeciaryID = "";

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

        benfeciaryID = String.valueOf(CommonClass.benfeciary_ID);

        if(benfeciaryID.equals("") || benfeciaryID.equals("null")){

            callofflinedata();

        }else{

            callofflinedata1();
        }

    }

    @Override
    protected void onResume() {
        benfeciaryID = String.valueOf(CommonClass.benfeciary_ID);

        if(benfeciaryID.equals("") || benfeciaryID.equals("null")){

            callofflinedata();

        }else{

            callofflinedata1();
        }
        super.onResume();
    }

    private void callofflinedata()
    {
        localRepo.getSelectedLivelihoodDate2((CommonClass.tempid)).observe(this, new Observer<List<LivehoodData>>() {
            @Override
            public void onChanged(@Nullable List<LivehoodData> singleMember) {
                if(singleMember.size() > 0)
                {
                    String familylivelihood = String.valueOf(singleMember.get(0).getLivelihoodName());

                    if(familylivelihood.equals("") || familylivelihood.equals("null")){}
                    else{
                        binding.familylivelihood.setText(singleMember.get(0).getLivelihoodName());
                    }

                    if(familylivelihood.equals("") || familylivelihood.equals("null")){}
                    else if(singleMember.get(0).getLivelihoodName().equalsIgnoreCase("Others ")){

                        binding.OtherSpecifyData.setVisibility(View.VISIBLE);
                    }

                    binding.otherspecify.setText(singleMember.get(0).getLivelihoodother());

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;

                    switchskilldevelopment = String.valueOf(singleMember.get(0).getSkilldev());
                    if(switchskilldevelopment.equals("") || switchskilldevelopment.equals("null")){}
                    else if(switchskilldevelopment.equalsIgnoreCase("Yes")){
                        binding.switchskilldevelopment.setText(switchskilldevelopment);
                        binding.skillldaveloplayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.switchskilldevelopment.setText(switchskilldevelopment);;
                        binding.skillldaveloplayout.setVisibility(View.GONE);

                    }
                    binding.skillwhen.setText(singleMember.get(0).getSkilldevwhen());
                    binding.skillwhere.setText(singleMember.get(0).getSkilldevwhere());
                    binding.skillwhat.setText(singleMember.get(0).getSkilldevwhat());


                    financialservices = String.valueOf(singleMember.get(0).getFinservice());
                    if(financialservices.equals("") || financialservices.equals("null")){}
                    else if(financialservices.equalsIgnoreCase("Yes")||financialservices.equalsIgnoreCase("true")){
                        binding.financialservices.setText(financialservices);
                        binding.financiallayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.financialservices.setText(financialservices);;
                        binding.financiallayout.setVisibility(View.GONE);
                    }
                    binding.finencialwhen.setText(singleMember.get(0).getFinservicewhen());
                    binding.finencialwhere.setText(singleMember.get(0).getFinservicewhere());
                    binding.finencialwhat.setText(singleMember.get(0).getFinservicewhat());

                    socialsecurity = String.valueOf(singleMember.get(0).getSocialsecurity());
                    if(socialsecurity.equals("") || socialsecurity.equals("null")){}
                    else if(socialsecurity.equalsIgnoreCase("Yes")||socialsecurity.equalsIgnoreCase("true")){
                        binding.socialsecurity.setText(socialsecurity);;
                        binding.sociallayout.setVisibility(View.VISIBLE);

                    }else{
                        binding.socialsecurity.setText(socialsecurity);;
                        binding.sociallayout.setVisibility(View.GONE);

                    }
                    binding.socialwhen.setText(singleMember.get(0).getSocialsecuritywhen());
                    binding.socialwhere.setText(singleMember.get(0).getSocialsecuritywhere());
                    binding.socialwhat.setText(singleMember.get(0).getSocialsecuritywhat());


                    vocationtraning = String.valueOf(singleMember.get(0).getVocationaltraining());
                    if(vocationtraning.equals("") || vocationtraning.equals("null")){}
                    else if(vocationtraning.equalsIgnoreCase("Yes")||vocationtraning.equalsIgnoreCase("true")){
                        binding.vocationtraning.setText(vocationtraning);;
                        binding.vocationlayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.vocationtraning.setText(vocationtraning);;
                        binding.vocationlayout.setVisibility(View.GONE);
                    }
                    binding.vocationwhen.setText(singleMember.get(0).getVocationaltrainingWhen());
                    binding.vocationwhere.setText(singleMember.get(0).getVocationaltrainingWhere());
                    binding.vocationwhat.setText(singleMember.get(0).getVocationaltrainingWhat());

                    serviceother = String.valueOf(singleMember.get(0).getServiceother());
                    if(serviceother.equals("") || serviceother.equals("null")){}
                    else if(serviceother.equalsIgnoreCase("Yes")||serviceother.equalsIgnoreCase("true")){
                        binding.serviceother.setText(serviceother);;
                        binding.servicelotherlayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.serviceother.setText(serviceother);;
                        binding.servicelotherlayout.setVisibility(View.GONE);

                    }
                    binding.servicewhen.setText(singleMember.get(0).getServiceotherwhen());
                    binding.servicewhere.setText(singleMember.get(0).getServiceotherwhere());
                    binding.servicewhat.setText(singleMember.get(0).getServiceotherwhat());


                }
            }
        });
    }

    private void callofflinedata1()
    {
        localRepo.getSelectedLivelihoodDate1((CommonClass.benfeciary_ID)).observe(this, new Observer<List<LivehoodData>>() {
            @Override
            public void onChanged(@Nullable List<LivehoodData> singleMember) {
                if(singleMember.size() > 0)
                {
                    String familylivelihood = String.valueOf(singleMember.get(0).getLivelihoodName());

                    if(familylivelihood.equals("") || familylivelihood.equals("null")){}
                    else{
                        binding.familylivelihood.setText(singleMember.get(0).getLivelihoodName());
                    }

                    if(familylivelihood.equals("") || familylivelihood.equals("null")){}
                    else if(singleMember.get(0).getLivelihoodName().equalsIgnoreCase("Others ")){

                        binding.OtherSpecifyData.setVisibility(View.VISIBLE);
                    }

                    binding.otherspecify.setText(singleMember.get(0).getLivelihoodother());

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;

                    switchskilldevelopment = String.valueOf(singleMember.get(0).getSkilldev());
                    if(switchskilldevelopment.equals("") || switchskilldevelopment.equals("null")){}
                    else if(switchskilldevelopment.equalsIgnoreCase("Yes")||switchskilldevelopment.equalsIgnoreCase("true")){
                        binding.switchskilldevelopment.setText(switchskilldevelopment);;
                        binding.skillldaveloplayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.switchskilldevelopment.setText(switchskilldevelopment);;
                        binding.skillldaveloplayout.setVisibility(View.GONE);

                    }
                    binding.skillwhen.setText(singleMember.get(0).getSkilldevwhen());
                    binding.skillwhere.setText(singleMember.get(0).getSkilldevwhere());
                    binding.skillwhat.setText(singleMember.get(0).getSkilldevwhat());


                    financialservices = String.valueOf(singleMember.get(0).getFinservice());
                    if(financialservices.equals("") || financialservices.equals("null")){}
                    else if(financialservices.equalsIgnoreCase("Yes")||financialservices.equalsIgnoreCase("true")){
                        binding.financialservices.setText(financialservices);;
                        binding.financiallayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.financialservices.setText(financialservices);;
                        binding.financiallayout.setVisibility(View.GONE);
                    }
                    binding.finencialwhen.setText(singleMember.get(0).getFinservicewhen());
                    binding.finencialwhere.setText(singleMember.get(0).getFinservicewhere());
                    binding.finencialwhat.setText(singleMember.get(0).getFinservicewhat());

                    socialsecurity = String.valueOf(singleMember.get(0).getSocialsecurity());
                    if(socialsecurity.equals("") || socialsecurity.equals("null")){}
                    else if(socialsecurity.equalsIgnoreCase("Yes")||socialsecurity.equalsIgnoreCase("true")){
                        binding.socialsecurity.setText(socialsecurity);;
                        binding.sociallayout.setVisibility(View.VISIBLE);

                    }else{
                        binding.socialsecurity.setText(socialsecurity);;
                        binding.sociallayout.setVisibility(View.GONE);

                    }
                    binding.socialwhen.setText(singleMember.get(0).getSocialsecuritywhen());
                    binding.socialwhere.setText(singleMember.get(0).getSocialsecuritywhere());
                    binding.socialwhat.setText(singleMember.get(0).getSocialsecuritywhat());


                    vocationtraning = String.valueOf(singleMember.get(0).getVocationaltraining());
                    if(vocationtraning.equals("") || vocationtraning.equals("null")){}
                    else if(vocationtraning.equalsIgnoreCase("Yes")||vocationtraning.equalsIgnoreCase("true")){
                        binding.vocationtraning.setText(vocationtraning);;
                        binding.vocationlayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.vocationtraning.setText(vocationtraning);;
                        binding.vocationlayout.setVisibility(View.GONE);
                    }
                    binding.vocationwhen.setText(singleMember.get(0).getVocationaltrainingWhen());
                    binding.vocationwhere.setText(singleMember.get(0).getVocationaltrainingWhere());
                    binding.vocationwhat.setText(singleMember.get(0).getVocationaltrainingWhat());

                    serviceother = String.valueOf(singleMember.get(0).getServiceother());
                    if(serviceother.equals("") || serviceother.equals("null")){}
                    else if(serviceother.equalsIgnoreCase("Yes")||serviceother.equalsIgnoreCase("true")){
                        binding.serviceother.setText(serviceother);;
                        binding.servicelotherlayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.serviceother.setText(serviceother);;
                        binding.servicelotherlayout.setVisibility(View.GONE);

                    }
                    binding.servicewhen.setText(singleMember.get(0).getServiceotherwhen());
                    binding.servicewhere.setText(singleMember.get(0).getServiceotherwhere());
                    binding.servicewhat.setText(singleMember.get(0).getServiceotherwhat());



                }
            }
        });
    }

    @Override
    protected void onStart() {
        callofflinedata1();
        super.onStart();
    }
}