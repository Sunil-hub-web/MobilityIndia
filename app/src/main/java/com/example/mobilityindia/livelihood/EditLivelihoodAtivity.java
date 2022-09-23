package com.example.mobilityindia.livelihood;

import static com.example.mobilityindia.constant.CommonClass.getOccupationMaster;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.beneficarylist.view.BeneficaryDetailActivity;
import com.example.mobilityindia.beneficarylist.view.BeneficaryListActivity;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEditLivelihoodAtivityBinding;
import com.example.mobilityindia.sync.model.LivehoodData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditLivelihoodAtivity extends AppCompatActivity {
    ActivityEditLivelihoodAtivityBinding binding;
    String switchskilldevelopment = "",financialservices = "",socialsecurity = "",vocationtraning = "",
            serviceother = "",id = "",createdAt = "",Benificiaryid = "",userid = "",benfeciaryID = "",genderStr = "",occuptionId = "";
    LocalRepo localRepo;
    LivehoodData livelihood;
    DatePickerDialog datePicker;
    List<String> occuption;
    List<String> occuptionID;


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
        occuption = new ArrayList<>();
        occuptionID = new ArrayList<>();
        localRepo = new LocalRepo(EditLivelihoodAtivity.this);
        livelihood = new LivehoodData();
        benfeciaryID = String.valueOf(CommonClass.benfeciary_ID);
        if(benfeciaryID.equals("") || benfeciaryID.equals("null")){

            callofflinedata();
        }else{

            callofflinedata1();
        }

        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);

      /*  String geteducation = getOccupationMaster(EditLivelihoodAtivity.this);
        try {
            JSONObject obj = new JSONObject(geteducation);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    occuption.add(jsonObject.getString("occupation_name"));
                    occuptionID.add(jsonObject.getString("id"));

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapteredu = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, occuption);
        adapteredu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.familylivelihood.setAdapter(adapteredu);*/
        binding.familylivelihood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*String livli = binding.familylivelihood.getText().toString().trim();
                if(livli.equals("") || livli.equals("null")){

                    binding.familylivelihood.showDropDown();
                }else{

                    binding.familylivelihood.setText("");
                    binding.familylivelihood.showDropDown();
                }*/

                binding.familylivelihood.showDropDown();

            }
        });
        binding.familylivelihood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                occuptionId = occuptionID.get(position);
                CommonClass.occuption1 = occuptionId;
                genderStr = occuption.get(position);

                Log.d("bvjhgv",genderStr);

                if(genderStr.equalsIgnoreCase("Others ")){

                    binding.OtherSpecifyData.setVisibility(View.VISIBLE);

                }else{

                    binding.OtherSpecifyData.setVisibility(View.GONE);
                }

            }
        });

        binding.skillwhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditLivelihoodAtivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        //binding.skillwhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                        binding.skillwhen.setText(year + "-" + (month + 1));
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
                        //binding.finencialwhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                        binding.finencialwhen.setText(year + "-" + (month + 1));
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
                        //binding.socialwhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                        binding.socialwhen.setText(year + "-" + (month + 1));
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
                        //binding.vocationwhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                        binding.vocationwhen.setText(year + "-" + (month + 1));
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
                       // binding.servicewhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                        binding.servicewhen.setText(year + "-" + (month + 1));
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

                if(benfeciaryID.equals("")){

                    updateDataLocal();

                }else{

                    updateDataLocal1();

                }
            }
        });

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

                    String geteducation = getOccupationMaster(EditLivelihoodAtivity.this);
                    try {
                        JSONObject obj = new JSONObject(geteducation);
                        if (obj.optString("status", "fail").equals("true")) {
                            JSONArray jsonArray = obj.optJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                occuption.add(jsonObject.getString("occupation_name"));
                                occuptionID.add(jsonObject.getString("id"));

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ArrayAdapter<String> adapteredu = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, occuption);
                    adapteredu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.familylivelihood.setAdapter(adapteredu);

                    binding.otherspecify.setText(singleMember.get(0).getLivelihoodother());

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;

                    switchskilldevelopment = String.valueOf(singleMember.get(0).getSkilldev());
                    if(switchskilldevelopment.equals("") || switchskilldevelopment.equals("null")){}
                    else if(switchskilldevelopment.equalsIgnoreCase("yes")||switchskilldevelopment.equalsIgnoreCase("true")){
                        binding.switchskilldevelopment.setOn(true);
                        binding.skillldaveloplayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.switchskilldevelopment.setOn(false);
                        binding.skillldaveloplayout.setVisibility(View.GONE);

                    }
                    binding.skillwhen.setText(singleMember.get(0).getSkilldevwhen());
                    binding.skillwhere.setText(singleMember.get(0).getSkilldevwhere());
                    binding.skillwhat.setText(singleMember.get(0).getSkilldevwhat());


                    financialservices = String.valueOf(singleMember.get(0).getFinservice());
                    if(financialservices.equals("") || financialservices.equals("null")){}
                    else if(financialservices.equalsIgnoreCase("yes")||financialservices.equalsIgnoreCase("true")){
                        binding.financialservices.setOn(true);
                        binding.financiallayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.financialservices.setOn(false);
                        binding.financiallayout.setVisibility(View.GONE);
                    }
                    binding.finencialwhen.setText(singleMember.get(0).getFinservicewhen());
                    binding.finencialwhere.setText(singleMember.get(0).getFinservicewhere());
                    binding.finencialwhat.setText(singleMember.get(0).getFinservicewhat());

                    socialsecurity = String.valueOf(singleMember.get(0).getSocialsecurity());
                    if(socialsecurity.equals("") || socialsecurity.equals("null")){}
                    else if(socialsecurity.equalsIgnoreCase("yes")||socialsecurity.equalsIgnoreCase("true")){
                        binding.socialsecurity.setOn(true);
                        binding.sociallayout.setVisibility(View.VISIBLE);

                    }else{
                        binding.socialsecurity.setOn(false);
                        binding.sociallayout.setVisibility(View.GONE);

                    }
                    binding.socialwhen.setText(singleMember.get(0).getSocialsecuritywhen());
                    binding.socialwhere.setText(singleMember.get(0).getSocialsecuritywhere());
                    binding.socialwhat.setText(singleMember.get(0).getSocialsecuritywhat());


                    vocationtraning = String.valueOf(singleMember.get(0).getVocationaltraining());
                    if(vocationtraning.equals("") || vocationtraning.equals("null")){}
                    else if(vocationtraning.equalsIgnoreCase("yes")||vocationtraning.equalsIgnoreCase("true")){
                        binding.vocationtraning.setOn(true);
                        binding.vocationlayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.vocationtraning.setOn(false);
                        binding.vocationlayout.setVisibility(View.GONE);
                    }
                    binding.vocationwhen.setText(singleMember.get(0).getVocationaltrainingWhen());
                    binding.vocationwhere.setText(singleMember.get(0).getVocationaltrainingWhere());
                    binding.vocationwhat.setText(singleMember.get(0).getVocationaltrainingWhat());

                    serviceother = String.valueOf(singleMember.get(0).getServiceother());
                    if(serviceother.equals("") || serviceother.equals("null")){}
                    else if(serviceother.equalsIgnoreCase("yes")||serviceother.equalsIgnoreCase("true")){
                        binding.serviceother.setOn(true);
                        binding.servicelotherlayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.serviceother.setOn(false);
                        binding.servicelotherlayout.setVisibility(View.GONE);

                    }
                    binding.servicewhen.setText(singleMember.get(0).getServiceotherwhen());
                    binding.servicewhere.setText(singleMember.get(0).getServiceotherwhere());
                    binding.servicewhat.setText(singleMember.get(0).getServiceotherwhat());

                    Benificiaryid = singleMember.get(0).getBenificiary_id();
                    createdAt = singleMember.get(0).getCreatedAt();
                    id = singleMember.get(0).getId();


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
                    String geteducation = getOccupationMaster(EditLivelihoodAtivity.this);
                    try {
                        JSONObject obj = new JSONObject(geteducation);
                        if (obj.optString("status", "fail").equals("true")) {
                            JSONArray jsonArray = obj.optJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                occuption.add(jsonObject.getString("occupation_name"));
                                occuptionID.add(jsonObject.getString("id"));

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ArrayAdapter<String> adapteredu = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, occuption);
                    adapteredu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.familylivelihood.setAdapter(adapteredu);


                    binding.otherspecify.setText(singleMember.get(0).getLivelihoodother());

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;

                    switchskilldevelopment = String.valueOf(singleMember.get(0).getSkilldev());
                    if(switchskilldevelopment.equals("") || switchskilldevelopment.equals("null")){}
                    else if(switchskilldevelopment.equalsIgnoreCase("yes")||switchskilldevelopment.equalsIgnoreCase("true")){
                        binding.switchskilldevelopment.setOn(true);
                        binding.skillldaveloplayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.switchskilldevelopment.setOn(false);
                        binding.skillldaveloplayout.setVisibility(View.GONE);

                    }
                    binding.skillwhen.setText(singleMember.get(0).getSkilldevwhen());
                    binding.skillwhere.setText(singleMember.get(0).getSkilldevwhere());
                    binding.skillwhat.setText(singleMember.get(0).getSkilldevwhat());


                    financialservices = String.valueOf(singleMember.get(0).getFinservice());
                    if(financialservices.equals("") || financialservices.equals("null")){}
                    else if(financialservices.equalsIgnoreCase("yes")||financialservices.equalsIgnoreCase("true")){
                        binding.financialservices.setOn(true);
                        binding.financiallayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.financialservices.setOn(false);
                        binding.financiallayout.setVisibility(View.GONE);
                    }
                    binding.finencialwhen.setText(singleMember.get(0).getFinservicewhen());
                    binding.finencialwhere.setText(singleMember.get(0).getFinservicewhere());
                    binding.finencialwhat.setText(singleMember.get(0).getFinservicewhat());

                    socialsecurity = String.valueOf(singleMember.get(0).getSocialsecurity());
                    if(socialsecurity.equals("") || socialsecurity.equals("null")){}
                    else if(socialsecurity.equalsIgnoreCase("yes")||socialsecurity.equalsIgnoreCase("true")){
                        binding.socialsecurity.setOn(true);
                        binding.sociallayout.setVisibility(View.VISIBLE);

                    }else{
                        binding.socialsecurity.setOn(false);
                        binding.sociallayout.setVisibility(View.GONE);

                    }
                    binding.socialwhen.setText(singleMember.get(0).getSocialsecuritywhen());
                    binding.socialwhere.setText(singleMember.get(0).getSocialsecuritywhere());
                    binding.socialwhat.setText(singleMember.get(0).getSocialsecuritywhat());


                    vocationtraning = String.valueOf(singleMember.get(0).getVocationaltraining());
                    if(vocationtraning.equals("") || vocationtraning.equals("null")){}
                    else if(vocationtraning.equalsIgnoreCase("yes")||vocationtraning.equalsIgnoreCase("true")){
                        binding.vocationtraning.setOn(true);
                        binding.vocationlayout.setVisibility(View.VISIBLE);
                    }else{
                        binding.vocationtraning.setOn(false);
                        binding.vocationlayout.setVisibility(View.GONE);
                    }
                    binding.vocationwhen.setText(singleMember.get(0).getVocationaltrainingWhen());
                    binding.vocationwhere.setText(singleMember.get(0).getVocationaltrainingWhere());
                    binding.vocationwhat.setText(singleMember.get(0).getVocationaltrainingWhat());

                    serviceother = String.valueOf(singleMember.get(0).getServiceother());
                    if(serviceother.equals("") || serviceother.equals("null")){}
                    else if(serviceother.equalsIgnoreCase("yes")||serviceother.equalsIgnoreCase("true")){
                        binding.serviceother.setOn(true);
                        binding.serotherlayut.setVisibility(View.VISIBLE);
                    }else{
                        binding.serviceother.setOn(false);
                        binding.serotherlayut.setVisibility(View.GONE);

                    }
                    binding.servicewhen.setText(singleMember.get(0).getServiceotherwhen());
                    binding.servicewhere.setText(singleMember.get(0).getServiceotherwhere());
                    binding.servicewhat.setText(singleMember.get(0).getServiceotherwhat());

                    Benificiaryid = singleMember.get(0).getBenificiary_id();
                    createdAt = singleMember.get(0).getCreatedAt();
                    id = singleMember.get(0).getId();

                }
            }
        });
    }

    public void updateDataLocal(){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date date = new Date();

        livelihood.setId(CommonClass.tempid);
        livelihood.setLivelihood(occuptionId);
        livelihood.setLivelihoodother(binding.otherspecify.getText().toString());
        livelihood.setLivelihoodName(binding.familylivelihood.getText().toString());

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
        livelihood.setCreatedAt(createdAt);
        livelihood.setUpdatedAt(formatter.format(date));
        livelihood.setFlag("update");
        callupdatelocaledata();
    }

    public void updateDataLocal1(){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date date = new Date();

        livelihood.setId(id);
        livelihood.setBenificiary_id(Benificiaryid);
        livelihood.setLivelihood(occuptionId);
        livelihood.setLivelihoodother(binding.otherspecify.getText().toString());
        livelihood.setLivelihoodName(binding.familylivelihood.getText().toString());

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
        livelihood.setCreatedAt(createdAt);
        livelihood.setFlag("update");
        livelihood.setUpdatedAt(formatter.format(date));


        callupdatelocaledata1();

    }

    public void callupdatelocaledata( ){

        localRepo.updateLivehoodData(livelihood);
       // onBackPressed();

        Toast.makeText(this, "Livelihood updated in locally.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EditLivelihoodAtivity.this, BeneficaryDetailActivity.class);
        startActivity(intent);


    }

    public void callupdatelocaledata1( ){

        localRepo.updateLivehoodData(livelihood);
        // onBackPressed();

        Toast.makeText(this, "Livelihood updated in locally.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EditLivelihoodAtivity.this, BeneficaryDetailActivity.class);
        startActivity(intent);


    }
}