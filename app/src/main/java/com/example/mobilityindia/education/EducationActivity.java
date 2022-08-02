package com.example.mobilityindia.education;

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
import com.example.mobilityindia.databinding.ActivityEducationBinding;
import com.example.mobilityindia.sync.model.EducationData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.List;

public class EducationActivity extends AppCompatActivity {
    ActivityEducationBinding binding;
    LocalRepo localRepo;
    String classaccessible = "" ,skilldevelopment = "",sittingmodification = "",accseetotlm ="",accesstotoilet ="",
            accesstolibrary ="",schoolenroll = "",acesstosportactivity ="",childpartialament ="",summercamp ="",vocationcourse ="",individualeducattionplan ="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_education);
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
        localRepo = new LocalRepo(EducationActivity.this);
        binding.nextpage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EducationActivity.this, EditEducationActivity.class));
            }
        });
        callofflinedata();

    }


    private void callofflinedata()
    {
        localRepo.getSelectedEducationList((CommonClass.benfeciary_ID)).observe(this, new Observer<List<EducationData>>() {
            @Override
            public void onChanged(@Nullable List<EducationData> singleMember) {
                if(singleMember.size() > 0)
                {
                    schoolenroll = singleMember.get(0).getSchool();

                    if(schoolenroll.equalsIgnoreCase("yes")||schoolenroll.equalsIgnoreCase("true")){
                        binding.schoolenroll.setOn(true);
                        binding.schoollayoutid.setVisibility(View.VISIBLE);
                    }else{
                        binding.schoolenroll.setOn(false);
                        binding.schoollayoutid.setVisibility(View.GONE);
                    }

                    binding.enrollmentno.setText(singleMember.get(0).getEnrollmentno());
                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;
                    binding.attendingclassregularly.setText(singleMember.get(0).getAttendingclass());


                    classaccessible = singleMember.get(0).getCec();
//                    binding.classaccessible.setOn(classaccessible.equalsIgnoreCase("yes") || classaccessible.equalsIgnoreCase("true"));

                                 ///////swagitikaask
//                    skilldevelopment = singleMember.get(0).getCec();
//                    if(skilldevelopment.equalsIgnoreCase("yes")||skilldevelopment.equalsIgnoreCase("true")){
//                        binding.skilldevelopment.setOn(true);
//                    }else{
//                        binding.skilldevelopment.setOn(false);
//                    }

                    sittingmodification = singleMember.get(0).getSitting();
                    binding.sittingmodification.setOn(sittingmodification.equalsIgnoreCase("yes") || sittingmodification.equalsIgnoreCase("true"));

                    accseetotlm = singleMember.get(0).getTlm();
                    binding.accseetotlm.setOn(accseetotlm.equalsIgnoreCase("yes") || accseetotlm.equalsIgnoreCase("true"));

                    accesstotoilet = singleMember.get(0).getToilet();
                    binding.accesstotoilet.setOn(accesstotoilet.equalsIgnoreCase("yes") || accesstotoilet.equalsIgnoreCase("true"));

                    accesstolibrary = singleMember.get(0).getLibrary();
                    binding.accesstolibrary.setOn(accesstolibrary.equalsIgnoreCase("yes") || accesstolibrary.equalsIgnoreCase("true"));



                    acesstosportactivity = singleMember.get(0).getSports();
                    binding.acesstosportactivity.setOn(acesstosportactivity.equalsIgnoreCase("yes") || acesstosportactivity.equalsIgnoreCase("true"));

                   // binding.anyother.setText(singleMember.get(0).get());


                    childpartialament = singleMember.get(0).getParliament();
                    //binding.childpartialament.setOn(childpartialament.equalsIgnoreCase("yes") || childpartialament.equalsIgnoreCase("true"));

                    summercamp = singleMember.get(0).getSummercamp();
                    //binding.summercamp.setOn(summercamp.equalsIgnoreCase("yes") || summercamp.equalsIgnoreCase("true"));

                    //vocationcourse = singleMember.get(0).getVocational();
                    //binding.vocationcourse.setOn(vocationcourse.equalsIgnoreCase("yes") || vocationcourse.equalsIgnoreCase("true"));

                    individualeducattionplan = singleMember.get(0).getIep();
                    //binding.individualeducattionplan.setOn(individualeducattionplan.equalsIgnoreCase("yes") || individualeducattionplan.equalsIgnoreCase("true"));
                    //binding.mentiondetailvocationcourse.setText(singleMember.get(0).getVocationaldetail());

                }
            }
        });
    }
}