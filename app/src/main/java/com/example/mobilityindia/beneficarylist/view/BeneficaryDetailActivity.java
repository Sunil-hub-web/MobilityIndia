package com.example.mobilityindia.beneficarylist.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.beneficarylist.viewbeneficarylist.BasicprofileviewActivity;
import com.example.mobilityindia.beneficarylist.viewbeneficarylist.MoreBasicProfileActivity;
import com.example.mobilityindia.beneficarylist.viewbeneficarylist.SHGViewActivity;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityBeneficaryDetailBinding;
import com.example.mobilityindia.education.EducationMainActivity;
import com.example.mobilityindia.health.healthview.HealthMainActivity;
import com.example.mobilityindia.livelihood.LivelihoodMailActivity;
import com.example.mobilityindia.social.view.SocialMainActivity;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.List;

public class BeneficaryDetailActivity extends AppCompatActivity {
    ActivityBeneficaryDetailBinding binding;
    LocalRepo localRepo;
    String tempid,name,benificaryregno,benificaryregdate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_beneficary_detail);
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
        binding.basicprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(BeneficaryDetailActivity.this, BasicprofileviewActivity.class));
            }
        });
        binding.morebasicprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BeneficaryDetailActivity.this, MoreBasicProfileActivity.class));
            }
        });

        binding.health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BeneficaryDetailActivity.this, HealthMainActivity.class));
               // EditHealthActivity
              //  startActivity(new Intent(BeneficaryDetailActivity.this, EditHealthActivity.class));

            }
        });

        binding.education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BeneficaryDetailActivity.this, EducationMainActivity.class));
            }
        });

        binding.livelihood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BeneficaryDetailActivity.this, LivelihoodMailActivity.class));
            }
        });

        binding.social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BeneficaryDetailActivity.this, SocialMainActivity.class));
            }
        });

        binding.shginfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BeneficaryDetailActivity.this, SHGViewActivity.class));
            }
        });
        localRepo = new LocalRepo(BeneficaryDetailActivity.this);
        //callofflinelocaldatdata();

        String id = CommonClass.benfeciary_ID;

        //Log.d("jdhias",id);

        name = getIntent().getStringExtra("name");
        benificaryregno = getIntent().getStringExtra("benificaryregno");
        benificaryregdate = getIntent().getStringExtra("benificaryregdate");
        tempid = getIntent().getStringExtra("tempid");
       // String benfeciary_ID = getIntent().getStringExtra("benfeciary_ID");

        SharedPreferences shared_bendata = getSharedPreferences("bendata",MODE_PRIVATE);
        name = shared_bendata.getString("name1",null);
        benificaryregno = shared_bendata.getString("benificaryregno1",null);
        benificaryregdate = shared_bendata.getString("benificaryregdate1",null);
        tempid = shared_bendata.getString("tempid1",null);

        binding.name.setText(name);
        binding.benificaryregno.setText(benificaryregno);
        binding.benificaryregdate.setText(benificaryregdate);
    }

    @Override
    protected void onResume() {

        SharedPreferences shared_bendata = getSharedPreferences("bendata",MODE_PRIVATE);
        name = shared_bendata.getString("name1",null);
        benificaryregno = shared_bendata.getString("benificaryregno1",null);
        benificaryregdate = shared_bendata.getString("benificaryregdate1",null);
        tempid = shared_bendata.getString("tempid1",null);

        binding.name.setText(name);
        binding.benificaryregno.setText(benificaryregno);
        binding.benificaryregdate.setText(benificaryregdate);

        super.onResume();
    }

    @Override
    protected void onRestart() {

        SharedPreferences shared_bendata = getSharedPreferences("bendata",MODE_PRIVATE);
        name = shared_bendata.getString("name1",null);
        benificaryregno = shared_bendata.getString("benificaryregno1",null);
        benificaryregdate = shared_bendata.getString("benificaryregdate1",null);
        tempid = shared_bendata.getString("tempid1",null);

        binding.name.setText(name);
        binding.benificaryregno.setText(benificaryregno);
        binding.benificaryregdate.setText(benificaryregdate);

        super.onRestart();
    }

    @Override
    protected void onStart() {


        SharedPreferences shared_bendata = getSharedPreferences("bendata",MODE_PRIVATE);
        name = shared_bendata.getString("name1",null);
        benificaryregno = shared_bendata.getString("benificaryregno1",null);
        benificaryregdate = shared_bendata.getString("benificaryregdate1",null);
        tempid = shared_bendata.getString("tempid1",null);

        binding.name.setText(name);
        binding.benificaryregno.setText(benificaryregno);
        binding.benificaryregdate.setText(benificaryregdate);

        super.onStart();
    }

    private void callofflinelocaldatdata()
    {
        localRepo.getSelectedBene(tempid).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {

                Log.d("sunilsingleMember",singleMember.toString());

                if(singleMember.size() > 0)
                {
                    binding.name.setText(singleMember.get(0).getName());
                    binding.benificaryregno.setText(singleMember.get(0).getRegistrationNo());
                    binding.benificaryregdate.setText(singleMember.get(0).getRegistrationDate());

                }else{

                    //Toast.makeText(BeneficaryDetailActivity.this, "Data Not There", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}