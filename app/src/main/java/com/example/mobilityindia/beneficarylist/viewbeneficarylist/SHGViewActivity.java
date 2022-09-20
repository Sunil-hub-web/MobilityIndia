package com.example.mobilityindia.beneficarylist.viewbeneficarylist;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.beneficarylist.editbeneficarylist.EditSHGActivity;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityShgviewBinding;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import java.util.List;

public class SHGViewActivity extends AppCompatActivity {
    LocalRepo localRepo;
    String membershg = "";
    boolean isOnn = true;
    private ActivityShgviewBinding binding;
    SessinoManager sessinoManager;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shgview);
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
                startActivity(new Intent(SHGViewActivity.this, EditSHGActivity.class));

            }
        });
        localRepo = new LocalRepo(SHGViewActivity.this);
        binding.switch4.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                binding.switch4.setEnabled(isOn);

            }
        });

        sessinoManager = new SessinoManager(SHGViewActivity.this);
        id = sessinoManager.getbenfeciaryID();
        callofflinelocaldatdata(CommonClass.tempid);

        Log.d("hdgbj", id);
    }

    private void callofflinelocaldatdata(String id) {
        localRepo.getSelectedBene(id).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {
                if (singleMember.size() > 0) {
                    membershg = singleMember.get(0).wheatherCboMember;
                    if (membershg.equalsIgnoreCase("yes") || membershg.equalsIgnoreCase("true")) {
                        binding.switch4.setOn(true);
                        binding.shglayout.setVisibility(View.VISIBLE);
                    } else {
                        binding.shglayout.setVisibility(View.GONE);
                        binding.switch4.setOn(false);
                    }
                    binding.shgname.setText(singleMember.get(0).cboName);
                    binding.startdateofshg.setText(singleMember.get(0).startYearOfCbo);
                    binding.dateofjoining.setText(singleMember.get(0).yearJoinCbo);
                   /* binding.bankname.setText(singleMember.get(0).bank_name);
                    binding.shgaccounno.setText(singleMember.get(0).shg_bank_account_no);
                    binding.nameofpwdcwd.setText(singleMember.get(0).name_of_pwd_cwd);
*/
                    Log.d("nsgigsakj", membershg);


                }
            }
        });
    }

    @Override
    protected void onStart() {
        callofflinelocaldatdata(CommonClass.tempid);
        super.onStart();
    }
}