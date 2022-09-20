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

import java.util.ArrayList;
import java.util.List;

public class BasicprofileviewActivity extends AppCompatActivity {
    ActivityBasicProfileViewBinding binding;
    LocalRepo localRepo;
    List<String> visitename = new ArrayList<>();

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
        localRepo.getSelectedBene(CommonClass.tempid).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {

                if(singleMember.size() > 0) {

                    binding.name.setText(singleMember.get(0).getName());
                    binding.parent.setText(singleMember.get(0).getParentName());
                    binding.relation.setText(singleMember.get(0).getRationCardNo());
                    binding.dateofbrth.setText(singleMember.get(0).getDob());
                    binding.age.setText(singleMember.get(0).getAge());
                    binding.gender.setText(singleMember.get(0).getGender());
                    binding.caste.setText(singleMember.get(0).getCasteName());
                    binding.religion.setText(singleMember.get(0).getReligionName());
                    binding.adharno.setText(singleMember.get(0).getAdhaarNo());
                    binding.economicstatu.setText(singleMember.get(0).getEconomicName());
                    binding.anualincme.setText(singleMember.get(0).getAnnualIncome());
                    binding.maritalstatus.setText(singleMember.get(0).getMaritalName());
                    binding.edu.setText(singleMember.get(0).getEducationName());
                    binding.occuption.setText(singleMember.get(0).getOccupationName());
                    binding.typeofdisability.setText(singleMember.get(0).getDisabilityName());
                    String disi = singleMember.get(0).getTypeOfSubDisability();
                    binding.typeofsubdisability.setText(disi);
                    binding.percentofdisability.setText(singleMember.get(0).getPercentageOfDisability());
                    binding.idcardno.setText(singleMember.get(0).getIdCardNo());
                    binding.phpamount.setText(singleMember.get(0).getPhpAmount());
                    binding.typeofbeneficary.setText(singleMember.get(0).getTypeOfBeneficiary());
                    binding.purposeofvisitDetails.setText(singleMember.get(0).getPurposeVisitDetails());
                    binding.havebankaccountNumber.setText(singleMember.get(0).getHaveBankAcc());
                    binding.nameofpwdcwd.setText(singleMember.get(0).getNameOfPwdCwd());

                    if(singleMember.get(0).getHaveBankAcc().equals("No")){

                        binding.accnumber.setVisibility(View.GONE);
                        binding.accholdname.setVisibility(View.GONE);
                        binding.ifsc.setVisibility(View.GONE);
                        binding.typeacc.setVisibility(View.GONE);

                    }else{

                        binding.AccountNumber.setText(singleMember.get(0).getAccNum());
                        binding.AccountHolderName.setText(singleMember.get(0).getAccHolderName());
                        binding.IFSCCode.setText(singleMember.get(0).getIfsc());
                        binding.Typeofaccount.setText(singleMember.get(0).getAccType());
                    }

                    visitename = singleMember.get(0).visitName;

                    StringBuffer sb = new StringBuffer();

                    if (visitename.size() != 0) {

                        for (String s : visitename) {

                            sb.append(s);
                            sb.append(",");
                        }

                        String visitname = sb.toString();

                        binding.purposeofvisit.setText(visitname);


                    }else{

                        binding.purposeofvisit.setText("");
                    }
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