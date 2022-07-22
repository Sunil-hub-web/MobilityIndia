package com.example.mobilityindia.social.view;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEditSocialBinding;
import com.example.mobilityindia.sync.model.SocialData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import java.util.List;

public class EditSocialActivity extends AppCompatActivity {
    ActivityEditSocialBinding binding;
    LocalRepo localRepo;
    String partofanycultural = "No";
    String partofpwd = "No";
    String partoflocal = "No";
    String traningparticipate = "No";
    SocialData socialData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_social);
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
        localRepo = new LocalRepo(EditSocialActivity.this);
        socialData = new SocialData();
        callofflinedata();

        binding.partofanyculture.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    partofanycultural = "Yes";
                    //switch  is on
                }else{
                    partofanycultural = "No";
                    //switch is off
                }
            }
        });

        binding.partofpwd.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    partofpwd = "Yes";
                    //switch  is on
                }else{
                    partofpwd = "No";
                    //switch is off
                }
            }
        });

        binding.partofanylocal.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    partoflocal = "Yes";
                    binding.tlName.setVisibility(View.VISIBLE);
                    //switch  is on
                }else{
                    partoflocal = "No";
                    binding.tlName.setVisibility(View.GONE);
                    binding.localgovtwhat.setText("");

                    //switch is off
                }
            }
        });

        binding.traningparticipate.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    traningparticipate = "Yes";
                    binding.tranarticipate.setVisibility(View.VISIBLE);
                    //switch  is on
                }else{
                    traningparticipate = "No";
                    binding.tranarticipate.setVisibility(View.GONE);
                    binding.whatkindoftraning.setText("");
                    binding.traningwhere.setText("");
                    //switch is off
                }
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                socialData.setSocialsports(partofanycultural);
                socialData.setSpcialdpo(partofpwd);

                socialData.setSocialgovt(partoflocal);
                socialData.setSocialgovtwhat(binding.localgovtwhat.getText().toString());

                socialData.setSocialtraining(traningparticipate);
                socialData.setSocialtrainingwhat(binding.whatkindoftraning.getText().toString());
                socialData.setSocialtrainingwhere(binding.traningwhere.getText().toString());
                callupdatelocaledata();
            }
        });
    }
    private void callofflinedata()
    {
        localRepo.getSocialionCreatedate((CommonClass.datestring)).observe(this, new Observer<List<SocialData>>() {
            @Override
            public void onChanged(@Nullable List<SocialData> singleMember) {
                if(singleMember.size() > 0)
                {
                    socialData = singleMember.get(0);

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;


                    partofanycultural = singleMember.get(0).getSocialsports();
                    binding.partofanyculture.setOn(partofanycultural.equalsIgnoreCase("yes") || partofanycultural.equalsIgnoreCase("true"));

                    ///////swagitikaask

                    partofpwd = singleMember.get(0).getSpcialdpo();
                    binding.partofpwd.setOn(partofpwd.equalsIgnoreCase("yes") || partofpwd.equalsIgnoreCase("true"));

                    partoflocal = singleMember.get(0).getSocialgovt();
                    if(partoflocal.equalsIgnoreCase("yes")||partoflocal.equalsIgnoreCase("true")){
                        binding.partofanylocal.setOn(true);
                        binding.tlName.setVisibility(View.VISIBLE);

                    }else{
                        binding.partofanylocal.setOn(false);
                        binding.tlName.setVisibility(View.GONE);
                        binding.localgovtwhat.setText("");
                    }

                    traningparticipate = singleMember.get(0).getSocialtraining();
                    if(traningparticipate.equalsIgnoreCase("yes")||traningparticipate.equalsIgnoreCase("true")){
                        binding.traningparticipate.setOn(true);
                        binding.tranarticipate.setVisibility(View.VISIBLE);
                    }else{
                        binding.traningparticipate.setOn(false);
                        binding.tranarticipate.setVisibility(View.GONE);
                        binding.whatkindoftraning.setText("");
                        binding.traningwhere.setText("");

                    }

                    binding.localgovtwhat.setText(singleMember.get(0).getSocialgovtwhat());
                    binding.whatkindoftraning.setText(singleMember.get(0).getSocialtrainingwhat());
                    binding.traningwhere.setText(singleMember.get(0).getSocialtrainingwhere());

                }
            }
        });
    }

    public void callupdatelocaledata( ){

        localRepo.updateSocialData(socialData);
        onBackPressed();

    }

}