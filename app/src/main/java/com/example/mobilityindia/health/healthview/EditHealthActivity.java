package com.example.mobilityindia.health.healthview;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEditHealthBinding;
import com.example.mobilityindia.sync.model.HealthCareData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.Calendar;
import java.util.List;

public class EditHealthActivity extends AppCompatActivity {
    ActivityEditHealthBinding binding;
    DatePickerDialog datePicker;
    LocalRepo localRepo;
    String fallowsheetupdated = "",correctivesurgery = "",homemodification = "",ihp = "",referincal = "";
    HealthCareData healthCareData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_health);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        localRepo = new LocalRepo(EditHealthActivity.this);
        healthCareData = new HealthCareData();


        binding.startdateofshg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditHealthActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.startdateofshg.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.dateofassiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditHealthActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.dateofassiment.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.traildate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditHealthActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.traildate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                healthCareData.setAssessmentwhere(binding.wherewasitdonea.getText().toString());
                callupdatelocaledata();
            }
        });
        callofflinedata();
    }

    public void callupdatelocaledata( ){

        localRepo.updateHealthCareData(healthCareData);

    }

    private void callofflinedata()
    {
        localRepo.getHealthCarenListDate((CommonClass.datestring)).observe(this, new Observer<List<HealthCareData>>() {
            @Override
            public void onChanged(@Nullable List<HealthCareData> singleMember) {
                if(singleMember.size() > 0)
                {
                    healthCareData = singleMember.get(0);
                    binding.startdateofshg.setText(singleMember.get(0).getScreeningdate());

                    binding.dateofassiment.setText(singleMember.get(0).getAssessmentdate());
                    binding.whodidassesmenta.setText(singleMember.get(0).getAssessmentwho());
                    binding.wherewasitdonea.setText(singleMember.get(0).getAssessmentwhere());


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