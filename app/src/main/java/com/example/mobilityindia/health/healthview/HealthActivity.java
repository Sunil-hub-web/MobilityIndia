package com.example.mobilityindia.health.healthview;

import android.app.DatePickerDialog;
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
import com.example.mobilityindia.databinding.ActivityHealthBinding;
import com.example.mobilityindia.sync.model.HealthCareData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.Calendar;
import java.util.List;

public class HealthActivity extends AppCompatActivity {
    ActivityHealthBinding binding;
    DatePickerDialog datePicker;
    LocalRepo localRepo;
    String fallowsheetupdated = "",correctivesurgery = "",homemodification = "",ihp = "",referincal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_health);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
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
        binding.nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthActivity.this,EditHealthActivity.class));
            }
        });
        localRepo = new LocalRepo(HealthActivity.this);
        callofflinedata();
    }

    private void callofflinedata()
    {
        localRepo.getHealthCarenListDate((CommonClass.datestring)).observe(this, new Observer<List<HealthCareData>>() {
            @Override
            public void onChanged(@Nullable List<HealthCareData> singleMember) {
                if(singleMember.size() > 0)
                {
                    binding.startdateofshg.setText(singleMember.get(0).getScreeningdate());

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;

                    binding.dateofassiment.setText(singleMember.get(0).getAssessmentdate());
                    binding.whodidassesment.setText(singleMember.get(0).getAssessmentwho());
                    binding.whaereassesment.setText(singleMember.get(0).getAssessmentwhere());


                    referincal = singleMember.get(0).getReferral();
                    binding.referincal.setOn(referincal.equalsIgnoreCase("yes") || referincal.equalsIgnoreCase("true"));
                    binding.refertowhichplace.setText(singleMember.get(0).getReferralplace());
                    binding.presecription.setText(singleMember.get(0).getReferralprescription());


                    binding.trailforwhat.setText(singleMember.get(0).getTrialwhat());
                    binding.traildate.setText(singleMember.get(0).getTrialdate());

                    binding.gaitfrequency.setText(singleMember.get(0).getGaitfrequency());
                    binding.howanydone.setText(singleMember.get(0).getGaithowmany());


                    binding.thrpynumberoftime.setText(singleMember.get(0).getTherapyfrequency());
                    binding.numberofsession.setText(singleMember.get(0).getTherapysessions());


                    binding.fitmentwho.setText(singleMember.get(0).getFitmentwho());
                    binding.fitmentwhere.setText(singleMember.get(0).getFitmentwhere());
                    binding.fitmentkind.setText(singleMember.get(0).getFitmentdevice());

                    binding.fallowwhat.setText(singleMember.get(0).getFollowupfrequency());
                    fallowsheetupdated = singleMember.get(0).getFollowupsheet();
                    binding.fallowsheetupdated.setOn(fallowsheetupdated.equalsIgnoreCase("yes") || fallowsheetupdated.equalsIgnoreCase("true"));


                    correctivesurgery = singleMember.get(0).getSurgery();
                    binding.correctivesurgery.setOn(correctivesurgery.equalsIgnoreCase("yes") || correctivesurgery.equalsIgnoreCase("true"));
                    binding.surgerywhere.setText(singleMember.get(0).getSurgerywhere());
                    binding.surgerywhat.setText(singleMember.get(0).getSurgerywherewhat());



                    homemodification = singleMember.get(0).getHomerecommend();
                    binding.homemodification.setOn(homemodification.equalsIgnoreCase("yes") || homemodification.equalsIgnoreCase("true"));
                    binding.homemodificationwhere.setText(singleMember.get(0).getHomerecommendwhat());


                    ihp = singleMember.get(0).getIhp();
                    binding.ihp.setOn(ihp.equalsIgnoreCase("yes") || ihp.equalsIgnoreCase("true"));


                }
            }
        });
    }
}