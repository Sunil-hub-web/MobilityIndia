package com.example.mobilityindia.livelihood;

import android.app.DatePickerDialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEditLivelihoodAtivityBinding;
import com.example.mobilityindia.sync.model.LivehoodData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import java.util.Calendar;
import java.util.List;

public class EditLivelihoodAtivity extends AppCompatActivity {
    ActivityEditLivelihoodAtivityBinding binding;
    String switchskilldevelopment = "",financialservices = "",socialsecurity = "",vocationtraning = "",serviceother = "";
    LocalRepo localRepo;
    LivehoodData livelihood;
    DatePickerDialog datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_livelihood_ativity);
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
        localRepo = new LocalRepo(EditLivelihoodAtivity.this);
        callofflinedata();
        livelihood = new LivehoodData();

        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);

        binding.familylivelihood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.familylivelihood.showDropDown();
            }
        });
        binding.familylivelihood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        binding.skillwhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditLivelihoodAtivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.skillwhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.finencialwhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditLivelihoodAtivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.finencialwhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.socialwhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditLivelihoodAtivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.socialwhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.vocationwhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditLivelihoodAtivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.vocationwhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.servicewhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditLivelihoodAtivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.servicewhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.switchskilldevelopment.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    switchskilldevelopment = "Yes";
                    binding.skillldaveloplayout.setVisibility(View.VISIBLE);
                    //switch  is on
                }else{
                    switchskilldevelopment = "No";
                    binding.skillldaveloplayout.setVisibility(View.GONE);
                    binding.skillwhen.setText("");
                    binding.skillwhat.setText("");
                    binding.skillwhere.setText("");
                    //switch is off
                }
            }
        });

        binding.financialservices.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    financialservices = "Yes";
                    binding.financiallayout.setVisibility(View.VISIBLE);
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    financialservices = "No";
                    binding.financiallayout.setVisibility(View.GONE);
                    binding.finencialwhen.setText("");
                    binding.finencialwhere.setText("");
                    binding.finencialwhat.setText("");
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.socialsecurity.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    socialsecurity = "Yes";
                    binding.sociallayout.setVisibility(View.VISIBLE);

                    //switch  is on
                }else{

                    socialsecurity = "No";
                    binding.sociallayout.setVisibility(View.GONE);
                    binding.socialwhen.setText("");
                    binding.socialwhere.setText("");
                    binding.socialwhat.setText("");
                    //switch is off
                }
            }
        });

        binding.vocationtraning.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    vocationtraning = "Yes";
                    binding.vocationlayout.setVisibility(View.VISIBLE);
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    vocationtraning = "No";
                    binding.vocationlayout.setVisibility(View.GONE);
                    binding.vocationwhen.setText("");
                    binding.vocationwhere.setText("");
                    binding.vocationwhat.setText("");
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.serviceother.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    serviceother = "Yes";
                    binding.serotherlayut.setVisibility(View.VISIBLE);
                    //switch  is on
                }else{
                    serviceother = "No";
                    binding.serotherlayut.setVisibility(View.GONE);
                    binding.servicewhen.setText("");
                    binding.servicewhere.setText("");
                    binding.servicewhat.setText("");
                    //switch is off
                }
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                livelihood.setLivelihood(binding.familylivelihood.getText().toString());
                livelihood.setLivelihoodother(binding.otherspecify.getText().toString());

                livelihood.setSkilldev(switchskilldevelopment);
                livelihood.setSkilldevwhen(binding.skillwhen.getText().toString());
                livelihood.setSkilldevwhat(binding.skillwhat.getText().toString());
                livelihood.setSkilldevwhere(binding.skillwhere.getText().toString());

                livelihood.setFinservice(financialservices);
                livelihood.setFinservicewhen(binding.finencialwhen.getText().toString());
                livelihood.setFinservicewhere(binding.finencialwhere.getText().toString());
                livelihood.setFinservicewhat(binding.finencialwhat.getText().toString());

                livelihood.setSocialsecurity(socialsecurity);
                livelihood.setSocialsecuritywhen(binding.socialwhen.getText().toString());
                livelihood.setSocialsecuritywhere(binding.socialwhere.getText().toString());
                livelihood.setSocialsecuritywhat(binding.socialwhat.getText().toString());

                livelihood.setVocationaltraining(vocationtraning);
                livelihood.setVocationaltrainingWhen(binding.vocationwhen.getText().toString());
                livelihood.setVocationaltrainingWhere(binding.vocationwhere.getText().toString());
                livelihood.setVocationaltrainingWhat(binding.vocationwhat.getText().toString());

                livelihood.setServiceother(serviceother);
                livelihood.setServiceotherwhen(binding.servicewhen.getText().toString());
                livelihood.setServiceotherwhere(binding.servicewhere.getText().toString());
                livelihood.setServiceotherwhat(binding.servicewhat.getText().toString());
                callupdatelocaledata();

            }
        });

    }
    private void callofflinedata()
    {
        localRepo.getSelectedLivelihoodDate((CommonClass.datestring)).observe(this, new Observer<List<LivehoodData>>() {
            @Override
            public void onChanged(@Nullable List<LivehoodData> singleMember) {
                if(singleMember.size() > 0)

                {
                    livelihood = singleMember.get(0);
                    binding.familylivelihood.setText(singleMember.get(0).getLivelihood());
                    String[] itemNames = getResources().getStringArray(R.array.FamielyLivelihood);
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.familylivelihood.setAdapter(adapter2);
                    binding.otherspecify.setText(singleMember.get(0).getLivelihoodother());

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;

                    switchskilldevelopment = singleMember.get(0).getSkilldev();
                    if(switchskilldevelopment.equalsIgnoreCase("yes")||switchskilldevelopment.equalsIgnoreCase("true")){
                        binding.financialservices.setOn(true);
                        binding.financiallayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.financialservices.setOn(false);
                        binding.financiallayout.setVisibility(View.GONE);
                    }
                    binding.skillwhen.setText(singleMember.get(0).getSkilldevwhen());
                    binding.skillwhere.setText(singleMember.get(0).getSkilldevwhere());
                    binding.skillwhat.setText(singleMember.get(0).getSkilldevwhat());


                    financialservices = singleMember.get(0).getFinservice();
                    if(financialservices.equalsIgnoreCase("yes")||financialservices.equalsIgnoreCase("true")){
                        binding.socialsecurity.setOn(true);
                        binding.sociallayout.setVisibility(View.VISIBLE);

                    }else{
                        binding.socialsecurity.setOn(false);
                        binding.sociallayout.setVisibility(View.GONE);

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
                        binding.serotherlayut.setVisibility(View.VISIBLE);
                    }else{
                        binding.serviceother.setOn(false);
                        binding.serotherlayut.setVisibility(View.GONE);

                    }
                    binding.servicewhen.setText(singleMember.get(0).getServiceotherwhen());
                    binding.servicewhere.setText(singleMember.get(0).getServiceotherwhere());
                    binding.servicewhat.setText(singleMember.get(0).getServiceotherwhat());


                }
            }
        });
    }

    public void callupdatelocaledata( ){

        localRepo.updateLivehoodData(livelihood);
        onBackPressed();


    }
}