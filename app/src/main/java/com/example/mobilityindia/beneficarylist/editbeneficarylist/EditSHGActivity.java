package com.example.mobilityindia.beneficarylist.editbeneficarylist;

import android.app.DatePickerDialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEditShgactivityBinding;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import java.util.Calendar;
import java.util.List;

public class EditSHGActivity extends AppCompatActivity {
    ActivityEditShgactivityBinding binding;
    DatePickerDialog datePicker;
    LocalRepo localRepo;
    String shgmember ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_shgactivity);
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

        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        localRepo = new LocalRepo(EditSHGActivity.this);


//        binding.switch4.setOnToggledListener(new OnToggledListener() {
//            @Override
//            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
//                // Implement your switching logic here
//            }
//        });

        binding.startdateofshg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditSHGActivity.this, new DatePickerDialog.OnDateSetListener() {
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

        binding.dojshg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditSHGActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {

                        binding.dojshg.setText(dayOfMonth + "-" + (month + 1) + "-" + year);

                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });
        callofflinelocaldatdata();
        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callupdatelocaledata();
            }
        });

        binding.switch4.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    shgmember = "Yes";
                    CommonClass.weathershgornot =shgmember;
                    binding.layoutshow.setVisibility(View.VISIBLE);
                    //switch  is on
                }else{
                    shgmember = "No";
                    CommonClass.weathershgornot =shgmember;
                    binding.layoutshow.setVisibility(View.GONE);
                    //switch is off
                }
            }
        });


    }

    private void callofflinelocaldatdata()
    {
        localRepo.getSelectedBene(Integer.valueOf(CommonClass.benfeciary_ID)).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {
                if(singleMember.size() > 0) {
                     shgmember = singleMember.get(0).wheatherCboMember;
                     if(shgmember.equalsIgnoreCase("yes")||shgmember.equalsIgnoreCase("true")){
                         binding.switch4.setOn(true);
                         binding.layoutshow.setVisibility(View.VISIBLE);
                     }else{
                         binding.switch4.setOn(false);
                         binding.layoutshow.setVisibility(View.GONE);
                     }
                    binding.shgname.setText(singleMember.get(0).cboName);
                    binding.startdateofshg.setText(singleMember.get(0).startYearOfCbo);
                    binding.dojshg.setText(singleMember.get(0).yearJoinCbo);
                   /* binding.bankname.setText(singleMember.get(0).bank_name);
                    binding.shgbankaccno.setText(singleMember.get(0).shg_bank_account_no);
                    binding.nameofpwdcwd.setText(singleMember.get(0).name_of_pwd_cwd);*/
                }
            }
        });
    }

    private void callupdatelocaledata()
    {
        localRepo.getSelectedBene(Integer.valueOf(CommonClass.benfeciary_ID)).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {
                if (singleMember.size() > 0)
                {
                    singleMember.get(0).setWheatherCboMember(shgmember);
                    singleMember.get(0).setCboName(binding.shgname.getText().toString());
                    singleMember.get(0).setStartYearOfCbo(binding.startdateofshg.getText().toString());

                    singleMember.get(0).setYearJoinCbo(binding.dojshg.getText().toString());
               /*     singleMember.get(0).setBank_name(binding.bankname.getText().toString());
                    singleMember.get(0).setShg_bank_account_no(binding.shgbankaccno.getText().toString());
                    singleMember.get(0).setName_of_pwd_cwd(binding.nameofpwdcwd.getText().toString());*/


                    localRepo.updateBene(singleMember.get(0));
                   // Toast.makeText(EditSHGActivity.this, "SHG update Successfully", Toast.LENGTH_SHORT).show();
                    onBackPressed();


                }
            }
        });
    }


}