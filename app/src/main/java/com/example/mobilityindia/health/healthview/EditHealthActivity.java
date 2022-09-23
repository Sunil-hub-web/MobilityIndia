package com.example.mobilityindia.health.healthview;

import static com.example.mobilityindia.constant.CommonClass.getHealthActivity;
import static com.example.mobilityindia.constant.CommonClass.getHealthDevices;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.beneficarylist.VisitModelClass;
import com.example.mobilityindia.beneficarylist.view.BeneficaryDetailActivity;
import com.example.mobilityindia.beneficarylist.view.BeneficaryListActivity;
import com.example.mobilityindia.constant.AppUtils;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEditHealthBinding;
import com.example.mobilityindia.livelihood.EditLivelihoodAtivity;
import com.example.mobilityindia.sync.model.HealthCareData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.utils.ImageFilePath;
import com.example.mobilityindia.utils.Utils;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.constant.ImageProvider;
import com.github.dhaval2404.imagepicker.listener.DismissListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class EditHealthActivity extends AppCompatActivity {
    ActivityEditHealthBinding binding;
    DatePickerDialog datePicker;
    LocalRepo localRepo;
    String correctivesurgery = "", homemodification = "", referincal = "";
    HealthCareData healthCareData;
    private static final int PROFILE_IMAGE_REQ_CODE = 101;
    private static final String TAG = "mainActivity";

    String ihp = "No", healthServices = "", fallowsheetupdated = "No", userId = "", benificiary_id = "", individualeducattionplan = "", FinalString = "",
            service_done = "", screeningdate = "", assessmentdate = "", assessmentwho = "", assessmentwhere = "", referral = "", referralplace = "",
            referralprescription = "", trialwhat = "", trialdate = "", socialsecurity = "", socialsecuritywhen = "", gaitfrequency = "",
            gaithowmany = "", therapyfrequency = "", therapysessions = "", fitmentwho = "", fitmentwhere = "", fitmentdevice = "",
            surgery = "", surgerywhere = "", surgerywherewhat = "", homerecommend = "", homerecommendwhat = "", str_aidsAppliances = "", benid = "",
            benificaryId = "", id = "";

    List<String> HealthActivityId;
    List<String> HealthActivityName;
    ArrayList<VisitModelClass> HealthActivityServices;
    String getvalue = "1";
    List<String> imagelist;
    List<String> eardischargeDD;
    List<String> earRightDD;
    List<String> earLeftDD;
    List<String> idarray;
    List<String> idarray1;
    List<String> idarray2;
    List<String> idarray3;
    List<String> idarray4;
    List<String> idarray5;
    List<String> HealthDevicesID;
    List<String> HealthDevicesName;
    List<String> id_Array;
    List<String> id_Array1;
    ArrayList<VisitModelClass> HealthDevices;
    SessinoManager sessinoManager;
    List<KeyPairBoolData> listArray1;
    List<KeyPairBoolData> listArray2;
    MultiSpinnerSearch multiSelectSpinnerWithSearch;

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


        HealthActivityId = new ArrayList<>();
        HealthActivityName = new ArrayList<>();
        idarray = new ArrayList<>();
        idarray1 = new ArrayList<>();
        idarray2 = new ArrayList<>();
        idarray3 = new ArrayList<>();
        idarray4 = new ArrayList<>();
        idarray5 = new ArrayList<>();
        HealthDevices = new ArrayList<>();
        HealthDevicesID = new ArrayList<>();
        HealthDevicesName = new ArrayList<>();
        HealthActivityServices = new ArrayList<>();
        sessinoManager = new SessinoManager(this);
        eardischargeDD = new ArrayList<>();
        earRightDD = new ArrayList<>();
        earLeftDD = new ArrayList<>();
        imagelist = new ArrayList<>();
        listArray1 = new ArrayList<>();
        listArray2 = new ArrayList<>();
        id_Array = new ArrayList<>();
        id_Array1 = new ArrayList<>();
        localRepo = new LocalRepo(EditHealthActivity.this);

        userId = sessinoManager.getUSERID();

        benid = String.valueOf(CommonClass.benfeciary_ID);

        if (benid.equals("") || benid.equals("null")) {

            callofflinedata();

        } else {

            callofflinedata1();
        }

        /*String gettdisability = getHealthActivity(EditHealthActivity.this);
        try {
            JSONObject obj = new JSONObject(gettdisability);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    HealthActivityName.add(jsonObject.getString("service_name"));
                    HealthActivityId.add(jsonObject.getString("id"));

                    String name = jsonObject.getString("service_name");
                    String id = jsonObject.getString("id");

                    long lon_id = Long.valueOf(id);

                    VisitModelClass visitModelClass = new VisitModelClass(lon_id, name);
                    HealthActivityServices.add(visitModelClass);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

       // final List<KeyPairBoolData> listArray1 = new ArrayList<>();

        for (int i = 0; i < HealthActivityServices.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(HealthActivityServices.get(i).getId());
            h.setName(HealthActivityServices.get(i).getName());

            if(idarray2.contains(HealthActivityServices.get(i).getName())){

                h.setSelected(true);
            }
            //h.setSelected(i < 5);
            listArray2.add(h);

            Log.d("hdgbdjb", listArray2.toString());
        }

        MultiSpinnerSearch multiSelectSpinnerWithSearch = findViewById(R.id.Services);

        // Pass true If you want searchView above the list. Otherwise false. default = true.
        multiSelectSpinnerWithSearch.setSearchEnabled(true);

        multiSelectSpinnerWithSearch.setHintText("Select Services");

        //A text that will display in clear text button
        multiSelectSpinnerWithSearch.setClearText("Close & Clear");

        // A text that will display in search hint.
        multiSelectSpinnerWithSearch.setSearchHint("Select your Services");

        // Set text that will display when search result not found...
        multiSelectSpinnerWithSearch.setEmptyTitle("Not Data Found!");

        // If you will set the limit, this button will not display automatically.
        multiSelectSpinnerWithSearch.setShowSelectAllButton(true);

        // Removed second parameter, position. Its not required now..
        // If you want to pass preselected items, you can do it while making listArray,
        // Pass true in setSelected of any item that you want to preselect

        multiSelectSpinnerWithSearch.setItems(listArray2, items -> {

            idarray1.clear();
            idarray.clear();

            if (items.size() == 0) {

                binding.linScreening.setVisibility(View.GONE);
                binding.linHearing.setVisibility(View.GONE);
                binding.linGAITTraining.setVisibility(View.GONE);
                binding.linSpeechLanguage.setVisibility(View.GONE);
                binding.linFallowUp.setVisibility(View.GONE);
                binding.linAssessment.setVisibility(View.GONE);
                binding.linMedicalReferral.setVisibility(View.GONE);
                binding.linTherapyServices.setVisibility(View.GONE);
                binding.linFitment.setVisibility(View.GONE);
                binding.linCorrectiveSurgery.setVisibility(View.GONE);
                binding.linHomeModificcation.setVisibility(View.GONE);
                binding.linTrial.setVisibility(View.GONE);
                binding.linHearingAid.setVisibility(View.GONE);
                binding.linIndividualHealthPlan.setVisibility(View.GONE);
                binding.linRepair.setVisibility(View.GONE);

            } else {

                //The followings are selected items.
                for (int i = 0; i < items.size(); i++) {

                    if (items.get(i).isSelected()) {

                        Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());

                        String userid = String.valueOf(items.get(i).getId());
                        idarray.add(userid);

                        String name = String.valueOf(items.get(i).getName());
                        idarray1.add(name);

                        Log.d("sunilarraynnn",idarray.toString());

                        Toast.makeText(this, "Sunil : "+idarray, Toast.LENGTH_SHORT).show();

                    }

                }
            }

            Log.d("bbhbbnsi",idarray1.toString());

            boolean ans_Screening = idarray1.contains("Screening");
            if (ans_Screening)
                binding.linScreening.setVisibility(View.VISIBLE);
            else
                binding.linScreening.setVisibility(View.GONE);

            boolean ans_Trial = idarray1.contains("Trial");
            if (ans_Trial)
                binding.linTrial.setVisibility(View.VISIBLE);
            else
                binding.linTrial.setVisibility(View.GONE);

            boolean ans_GAITTraining = idarray1.contains("GAIT Training");
            if (ans_GAITTraining)
                binding.linGAITTraining.setVisibility(View.VISIBLE);
            else
                binding.linGAITTraining.setVisibility(View.GONE);

            boolean ans_Assessment = idarray1.contains("Assessment");
            if (ans_Assessment)
                binding.linAssessment.setVisibility(View.VISIBLE);
            else
                binding.linAssessment.setVisibility(View.GONE);

            boolean ans_MedicalReferral = idarray1.contains("Medical Referral");
            if (ans_MedicalReferral)
                binding.linMedicalReferral.setVisibility(View.VISIBLE);
            else
                binding.linMedicalReferral.setVisibility(View.GONE);

            boolean ans_TherapyServices = idarray1.contains("Therapy Service");
            if (ans_TherapyServices)
                binding.linTherapyServices.setVisibility(View.VISIBLE);
            else
                binding.linTherapyServices.setVisibility(View.GONE);

            boolean ans_Fitment = idarray1.contains("Fitment");
            if (ans_Fitment)
                binding.linFitment.setVisibility(View.VISIBLE);
            else
                binding.linFitment.setVisibility(View.GONE);

            boolean ans_CorrectiveSurgery = idarray1.contains("Corrective Surgery");
            if (ans_CorrectiveSurgery)
                binding.linCorrectiveSurgery.setVisibility(View.VISIBLE);
            else
                binding.linCorrectiveSurgery.setVisibility(View.GONE);

            boolean ans_HomeModificcation = idarray1.contains("Home Modification");
            if (ans_HomeModificcation)
                binding.linHomeModificcation.setVisibility(View.VISIBLE);
            else
                binding.linHomeModificcation.setVisibility(View.GONE);

            boolean ans_SpeechLanguage = idarray1.contains("Speech & Language");
            if (ans_SpeechLanguage)
                binding.linSpeechLanguage.setVisibility(View.VISIBLE);
            else
                binding.linSpeechLanguage.setVisibility(View.GONE);

            boolean ans_Hearing = idarray1.contains("Hearing");
            if (ans_Hearing)
                binding.linHearing.setVisibility(View.VISIBLE);
            else
                binding.linHearing.setVisibility(View.GONE);

            boolean ans_Hearing1 = idarray1.contains("Hearing");
            if (ans_Hearing1)
                binding.linHearingAid.setVisibility(View.VISIBLE);
            else
                binding.linHearingAid.setVisibility(View.GONE);

            boolean ans_IHP = idarray1.contains("IHP");
            if (ans_IHP)
                binding.linIndividualHealthPlan.setVisibility(View.VISIBLE);
            else
                binding.linIndividualHealthPlan.setVisibility(View.GONE);

            boolean ans_Followup = idarray1.contains("Followup");
            if (ans_Followup)
                binding.linFallowUp.setVisibility(View.VISIBLE);
            else
                binding.linFallowUp.setVisibility(View.GONE);

            boolean ans_AARepair = idarray1.contains("A&A Repair");
            if(ans_AARepair)
                binding.linRepair1.setVisibility(View.VISIBLE);
            else
                binding.linRepair1.setVisibility(View.GONE);

            boolean ans_CBRAARepair = idarray1.contains("CBR A&A Repair");
            if (ans_CBRAARepair)
                binding.linRepair.setVisibility(View.VISIBLE);
            else
                binding.linRepair.setVisibility(View.GONE);

        });*/


        ArrayAdapter<String> adapterpurposevisit = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, HealthActivityName);
        adapterpurposevisit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.Services.setAdapter(adapterpurposevisit);
        //binding.purposeofvisit.setThreshold(1);
        //binding.Services.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        binding.startdateofshg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditHealthActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.startdateofshg.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
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
                        binding.traildate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
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
                        binding.dateofassiment.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        binding.referincal.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    referincal = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                    binding.medicalRef.setVisibility(View.VISIBLE);
                } else {
                    referincal = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off

                    binding.medicalRef.setVisibility(View.GONE);
                }
            }
        });


        /* binding.correctivesurgery.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    correctivesurgery = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    correctivesurgery = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });*/


        String[] itemName_sugg = getResources().getStringArray(R.array.selectone1);
        ArrayAdapter<String> adapter_sugg = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemName_sugg);
        adapter_sugg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.correctivesurgery.setAdapter(adapter_sugg);

        binding.correctivesurgery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.correctivesurgery).showDropDown();
            }
        });
        binding.correctivesurgery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                correctivesurgery = binding.correctivesurgery.getAdapter().getItem(position).toString();
            }
        });
        binding.homemodification.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    homemodification = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on

                    binding.modificationWhereisit.setVisibility(View.VISIBLE);
                    binding.recommend.setText("Recommended");
                } else {
                    homemodification = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off

                    binding.modificationWhereisit.setVisibility(View.GONE);
                    binding.recommend.setText("Not Recommend");
                }
            }
        });

        String[] itemNames_howOften = getResources().getStringArray(R.array.Howoften);
        ArrayAdapter<String> adapter_HowOften = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_howOften);
        adapter_HowOften.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.gaitfrequency.setAdapter(adapter_HowOften);

        binding.gaitfrequency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.gaitfrequency).showDropDown();
            }
        });
        binding.gaitfrequency.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.gaitfrequency.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames = getResources().getStringArray(R.array.selectone1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.EardischargeDD.setAdapter(adapter2);

        binding.EarRightDD.setAdapter(adapter2);
        binding.EarLeftDD.setAdapter(adapter2);

        binding.EardischargeDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.EardischargeDD).showDropDown();
            }
        });
        binding.EardischargeDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.EardischargeDD.getAdapter().getItem(position).toString();
            }
        });

        binding.EarRightDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.EarRightDD).showDropDown();
            }
        });
        binding.EarRightDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.EarRightDD.getAdapter().getItem(position).toString();
            }
        });

        binding.EarLeftDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.EarLeftDD).showDropDown();
            }
        });
        binding.EarLeftDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.EarLeftDD.getAdapter().getItem(position).toString();
            }
        });

        binding.fallowsheetupdated.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    fallowsheetupdated = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    fallowsheetupdated = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        String[] itemNames_howOften1 = getResources().getStringArray(R.array.Howoften);
        ArrayAdapter<String> adapter_HowOften1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_howOften1);
        adapter_HowOften1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.fallowwhat.setAdapter(adapter_HowOften1);

        binding.fallowwhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.fallowwhat).showDropDown();
            }
        });
        binding.fallowwhat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.fallowwhat.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_developmentDD = getResources().getStringArray(R.array.speechandlanguage);
        ArrayAdapter<String> adapter_developmentDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_developmentDD);
        adapter_developmentDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.developmentDD.setAdapter(adapter_developmentDD);

        binding.developmentDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.developmentDD).showDropDown();
            }
        });
        binding.developmentDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.developmentDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_OPMEDD = getResources().getStringArray(R.array.OPMEDD);
        ArrayAdapter<String> adapter_OPMEDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_OPMEDD);
        adapter_OPMEDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.OPMEDD.setAdapter(adapter_OPMEDD);

        binding.OPMEDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.OPMEDD).showDropDown();
            }
        });
        binding.OPMEDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.OPMEDD.getAdapter().getItem(position).toString();

                if (genderStr.equals("Abnormal")) {

                    binding.tlAbnormal.setVisibility(View.VISIBLE);
                }
            }
        });

        String[] itemNames_ArticulationDD = getResources().getStringArray(R.array.articulationDD);
        ArrayAdapter<String> adapter_ArticulationDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_ArticulationDD);
        adapter_ArticulationDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.SpeechArticulationDD.setAdapter(adapter_ArticulationDD);

        binding.SpeechArticulationDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.SpeechArticulationDD).showDropDown();
            }
        });
        binding.SpeechArticulationDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.SpeechArticulationDD.getAdapter().getItem(position).toString();

                if (genderStr.equals("Mis-articulation")) {

                    binding.tlMisarticulation.setVisibility(View.VISIBLE);
                }
            }
        });

        String[] itemNames_FluencyDD = getResources().getStringArray(R.array.fluencyDD);
        ArrayAdapter<String> adapter_FluencyDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_FluencyDD);
        adapter_FluencyDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.FluencyDD.setAdapter(adapter_FluencyDD);

        binding.FluencyDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.FluencyDD).showDropDown();
            }
        });
        binding.FluencyDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.FluencyDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_QualityDD = getResources().getStringArray(R.array.qualityDD);
        ArrayAdapter<String> adapter_QualityDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_QualityDD);
        adapter_QualityDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.QualityDD.setAdapter(adapter_QualityDD);

        binding.QualityDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.QualityDD).showDropDown();
            }
        });
        binding.QualityDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.QualityDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_PitchDD = getResources().getStringArray(R.array.pitchDD);
        ArrayAdapter<String> adapter_PitchDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_PitchDD);
        adapter_PitchDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.PitchDD.setAdapter(adapter_PitchDD);

        binding.PitchDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.PitchDD).showDropDown();
            }
        });
        binding.PitchDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.PitchDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_LoudnessDD = getResources().getStringArray(R.array.loudnessDD);
        ArrayAdapter<String> adapter_LoudnessDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_LoudnessDD);
        adapter_LoudnessDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.LoudnessDD.setAdapter(adapter_LoudnessDD);

        binding.LoudnessDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.LoudnessDD).showDropDown();
            }
        });
        binding.LoudnessDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.LoudnessDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_communicationDD = getResources().getStringArray(R.array.modeofcommunicationDD);
        ArrayAdapter<String> adapter_communicationDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_communicationDD);
        adapter_communicationDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ModeofcommunicationDD.setAdapter(adapter_communicationDD);

        binding.ModeofcommunicationDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.ModeofcommunicationDD).showDropDown();
            }
        });
        binding.ModeofcommunicationDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.ModeofcommunicationDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_ComprehensionDD = getResources().getStringArray(R.array.comprehensionDD);
        ArrayAdapter<String> adapter_ComprehensionDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_ComprehensionDD);
        adapter_ComprehensionDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ComprehensionDD.setAdapter(adapter_ComprehensionDD);

        binding.ComprehensionDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.ComprehensionDD).showDropDown();
            }
        });
        binding.ComprehensionDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.ComprehensionDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_ExpressionDD = getResources().getStringArray(R.array.comprehensionDD);
        ArrayAdapter<String> adapter_ExpressionDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_ExpressionDD);
        adapter_ExpressionDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ExpressionDD.setAdapter(adapter_ExpressionDD);

        binding.ExpressionDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.ExpressionDD).showDropDown();
            }
        });
        binding.ExpressionDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.ExpressionDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_RightDD = getResources().getStringArray(R.array.otoscopyRightLeftDD);
        ArrayAdapter<String> adapter_RightDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_RightDD);
        adapter_RightDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.RightDD.setAdapter(adapter_RightDD);

        binding.RightDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.RightDD).showDropDown();
            }
        });
        binding.RightDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.RightDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_LeftDD = getResources().getStringArray(R.array.otoscopyRightLeftDD);
        ArrayAdapter<String> adapter_LeftDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_LeftDD);
        adapter_LeftDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.LeftDD.setAdapter(adapter_LeftDD);

        binding.LeftDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.LeftDD).showDropDown();
            }
        });
        binding.LeftDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.LeftDD.getAdapter().getItem(position).toString();
            }
        });

        binding.ihp.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    individualeducattionplan = "Yes";
                    binding.ihpview.setVisibility(View.VISIBLE);
                    binding.imagesiew.setVisibility(View.VISIBLE);
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    individualeducattionplan = "No";
                    binding.ihpview.setVisibility(View.GONE);
                    binding.imagesiew.setVisibility(View.GONE);
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPdf();
            }
        });

        binding.document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGalleryIntentfordoc();
            }
        });

        binding.cameraaview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //capturePhoto();
            }
        });

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imageview1.setImageBitmap(null);
                getvalue = "1";
                binding.frame1.setVisibility(View.GONE);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imageview2.setImageBitmap(null);
                getvalue = "2";
                binding.frame2.setVisibility(View.GONE);
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imageview3.setImageBitmap(null);
                getvalue = "3";
                binding.frame3.setVisibility(View.GONE);
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imageview4.setImageBitmap(null);
                getvalue = "4";
                binding.frame4.setVisibility(View.GONE);
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imageview5.setImageBitmap(null);
                getvalue = "5";
                binding.frame5.setVisibility(View.GONE);
            }
        });
        binding.ihpview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imagesiew.setVisibility(View.VISIBLE);
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (idarray2.size() != 0) {

                    if (idarray3.size() == 0) {

                        benid = String.valueOf(CommonClass.benfeciary_ID);

                        if (benid.equals("") || benid.equals("null")) {

                            localbeneficaryDataCall();

                        } else {

                            localbeneficaryDataCall1();
                        }

                    } else {

                        benid = String.valueOf(CommonClass.benfeciary_ID);

                        if (benid.equals("") || benid.equals("null")) {

                            localbeneficaryDataCall();

                        } else {

                            localbeneficaryDataCall1();
                        }
                    }

                }

            }
        });

        binding.totalcost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                binding.donorcontribution.setText(addNumbers1());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.patientcontribution.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                binding.donorcontribution.setText(addNumbers1());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.repaircost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                binding.rdonorcontribution.setText(addNumbers3());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.rpatientcontribution.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                binding.rdonorcontribution.setText(addNumbers3());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.repaircost1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                binding.rdonorcontribution1.setText(addNumbers2());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.rpatientcontribution1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                binding.rdonorcontribution1.setText(addNumbers2());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void callupdatelocaledata() {

        localRepo.updateHealthCareData(healthCareData);

    }

    private void callofflinedata1() {
        localRepo.getSelectedHealthWithData(CommonClass.datestring, CommonClass.benfeciary_ID).observe(this, new Observer<List<HealthCareData>>() {
            @Override
            public void onChanged(@Nullable List<HealthCareData> singleMember) {
                if (singleMember.size() > 0) {
                    binding.startdateofshg.setText(singleMember.get(0).getScreeningdate());

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;

                    idarray2.clear();

                    idarray2 = singleMember.get(0).getServiceName();
                    idarray5 = singleMember.get(0).getDeviceName();

                    String gettdisability = getHealthActivity(EditHealthActivity.this);
                    try {
                        JSONObject obj = new JSONObject(gettdisability);
                        if (obj.optString("status", "fail").equals("true")) {
                            JSONArray jsonArray = obj.optJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HealthActivityName.add(jsonObject.getString("service_name"));
                                HealthActivityId.add(jsonObject.getString("id"));

                                String name = jsonObject.getString("service_name");
                                String id = jsonObject.getString("id");

                                long lon_id = Long.valueOf(id);

                                VisitModelClass visitModelClass = new VisitModelClass(lon_id, name);
                                HealthActivityServices.add(visitModelClass);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < HealthActivityServices.size(); i++) {
                        KeyPairBoolData h = new KeyPairBoolData();
                        h.setId(HealthActivityServices.get(i).getId());
                        h.setName(HealthActivityServices.get(i).getName());
                        if (idarray2.contains(HealthActivityServices.get(i).getName())) {

                            h.setSelected(true);
                        }
                        //h.setSelected(i < 5);
                        listArray1.add(h);

                        Log.d("hdgbdjb", listArray1.toString());
                    }

                    multiSelectSpinnerWithSearch = findViewById(R.id.Services);
                    multiSelectSpinnerWithSearch.setSearchEnabled(true);
                    multiSelectSpinnerWithSearch.setHintText("Select Services");
                    multiSelectSpinnerWithSearch.setClearText("Close & Clear");
                    multiSelectSpinnerWithSearch.setSearchHint("Select your Services");
                    multiSelectSpinnerWithSearch.setEmptyTitle("Not Data Found!");
                    multiSelectSpinnerWithSearch.setShowSelectAllButton(true);

                    multiSelectSpinnerWithSearch.setItems(listArray1, items -> {

                        idarray3.clear();
                        idarray4.clear();

                        //The followings are selected items.
                        for (int i = 0; i < items.size(); i++) {
                            Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());

                            String userid = String.valueOf(items.get(i).getId());
                            String name = String.valueOf(items.get(i).getName());
                            idarray3.add(name);
                            idarray4.add(userid);

                            //Toast.makeText(EditHealthActivity.this, "sonuarr" + idarray3, Toast.LENGTH_SHORT).show();
                        }

                        Log.d("gcyhbghv", idarray3.toString());

                        boolean ans_Screening = idarray3.contains("Screening");
                        if (ans_Screening)
                            binding.linScreening.setVisibility(View.VISIBLE);
                        else
                            binding.linScreening.setVisibility(View.GONE);

                        boolean ans_Trial = idarray3.contains("Trial");
                        if (ans_Trial)
                            binding.linTrial.setVisibility(View.VISIBLE);
                        else
                            binding.linTrial.setVisibility(View.GONE);

                        boolean ans_GAITTraining = idarray3.contains("GAIT Training");
                        if (ans_GAITTraining)
                            binding.linGAITTraining.setVisibility(View.VISIBLE);
                        else
                            binding.linGAITTraining.setVisibility(View.GONE);

                        boolean ans_Assessment = idarray3.contains("Assessment");
                        if (ans_Assessment)
                            binding.linAssessment.setVisibility(View.VISIBLE);
                        else
                            binding.linAssessment.setVisibility(View.GONE);

                        boolean ans_MedicalReferral = idarray3.contains("Referral");
                        if (ans_MedicalReferral)
                            binding.linMedicalReferral.setVisibility(View.VISIBLE);
                        else
                            binding.linMedicalReferral.setVisibility(View.GONE);

                        boolean ans_TherapyServices = idarray3.contains("Therapy Service");
                        if (ans_TherapyServices)
                            binding.linTherapyServices.setVisibility(View.VISIBLE);
                        else
                            binding.linTherapyServices.setVisibility(View.GONE);

                        boolean ans_Fitment = idarray3.contains("Fitment");
                        if (ans_Fitment)
                            binding.linFitment.setVisibility(View.VISIBLE);
                        else
                            binding.linFitment.setVisibility(View.GONE);

                        boolean ans_CorrectiveSurgery = idarray3.contains("Corrective Surgery");
                        if (ans_CorrectiveSurgery)
                            binding.linCorrectiveSurgery.setVisibility(View.VISIBLE);
                        else
                            binding.linCorrectiveSurgery.setVisibility(View.GONE);

                        boolean ans_HomeModificcation = idarray3.contains("Home Modification");
                        if (ans_HomeModificcation)
                            binding.linHomeModificcation.setVisibility(View.VISIBLE);
                        else
                            binding.linHomeModificcation.setVisibility(View.GONE);

                        boolean ans_SpeechLanguage = idarray3.contains("Speech & Language");
                        if (ans_SpeechLanguage)
                            binding.linSpeechLanguage.setVisibility(View.VISIBLE);
                        else
                            binding.linSpeechLanguage.setVisibility(View.GONE);

                        boolean ans_Hearing = idarray3.contains("Hearing");
                        if (ans_Hearing)
                            binding.linHearing.setVisibility(View.VISIBLE);
                        else
                            binding.linHearing.setVisibility(View.GONE);

                        boolean ans_Hearing1 = idarray3.contains("Hearing");
                        if (ans_Hearing1)
                            binding.linHearingAid.setVisibility(View.VISIBLE);
                        else
                            binding.linHearingAid.setVisibility(View.GONE);

                        boolean ans_IHP = idarray3.contains("IHP");
                        if (ans_IHP)
                            binding.linIndividualHealthPlan.setVisibility(View.VISIBLE);
                        else
                            binding.linIndividualHealthPlan.setVisibility(View.GONE);

                        boolean ans_Followup = idarray3.contains("Followup");
                        if (ans_Followup)
                            binding.linFallowUp.setVisibility(View.VISIBLE);
                        else
                            binding.linFallowUp.setVisibility(View.GONE);

                        boolean ans_AARepair = idarray3.contains("A&A Repair");
                        if (ans_AARepair)
                            binding.linRepair1.setVisibility(View.VISIBLE);
                        else
                            binding.linRepair1.setVisibility(View.GONE);

                        boolean ans_CBRAARepair = idarray3.contains("CBR A&A Repair");
                        if (ans_CBRAARepair)
                            binding.linRepair.setVisibility(View.VISIBLE);
                        else
                            binding.linRepair.setVisibility(View.GONE);
                    });

                    binding.startdateofshg.setText(singleMember.get(0).getScreeningdate());
                    binding.dateofassiment.setText(singleMember.get(0).getAssessmentdate());
                    binding.whodidassesmenta.setText(singleMember.get(0).getAssessmentwho());
                    binding.wherewasitdonea.setText(singleMember.get(0).getAssessmentwhere());
                    // binding.referincal.setText(singleMember.get(0).getReferral());
                    //binding.refertowhichplace.setText(singleMember.get(0).getReferralplace());
                    //binding.presecription.setText(singleMember.get(0).getReferralprescription());

                    String referal = String.valueOf(singleMember.get(0).getReferral());
                    if (referal.equals("") || referal.equals("null")) {
                    } else if (referal.equalsIgnoreCase("yes") || referal.equalsIgnoreCase("true")) {
                        binding.referincal.setOn(true);
                        binding.refertowhichplace.setVisibility(View.VISIBLE);
                        binding.presecription.setVisibility(View.VISIBLE);
                    } else {
                        binding.referincal.setOn(false);
                        binding.refertowhichplace.setVisibility(View.GONE);
                        binding.presecription.setVisibility(View.GONE);
                    }
                    binding.homemodificationwhere.setText(singleMember.get(0).getReferralplace());
                    binding.homemodificationwhere.setText(singleMember.get(0).getReferralprescription());

                    binding.trailforwhat.setText(singleMember.get(0).getTrialwhat());
                    binding.traildate.setText(singleMember.get(0).getTrialdate());
                    binding.gaitfrequency.setText(singleMember.get(0).getGaitfrequency());
                    binding.howanydone.setText(singleMember.get(0).getGaithowmany());
                    binding.thrpynumberoftime.setText(singleMember.get(0).getTherapyfrequency());
                    binding.numberofsession.setText(singleMember.get(0).getTherapysessions());
                    binding.fitmentwho.setText(singleMember.get(0).getFitmentwho());
                    binding.fitmentwhere.setText(singleMember.get(0).getFitmentwhere());
                    binding.fitmentkind.setText(singleMember.get(0).getFitmentdevice());

                    String getDevices = getHealthDevices(EditHealthActivity.this);
                    try {
                        JSONObject obj = new JSONObject(getDevices);
                        if (obj.optString("status", "fail").equals("true")) {
                            JSONArray jsonArray = obj.optJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HealthDevicesName.add(jsonObject.getString("device_name"));
                                HealthDevicesID.add(jsonObject.getString("id"));

                                String name = jsonObject.getString("device_name");
                                String id = jsonObject.getString("id");

                                long lon_id = Long.valueOf(id);

                                VisitModelClass visitModelClass = new VisitModelClass(lon_id, name);
                                HealthDevices.add(visitModelClass);
                            }

                            int size = HealthDevicesName.size();
                            int size1 = HealthDevicesID.size();

                            Log.d("hbdsb", size + "     " + size1);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    final List<KeyPairBoolData> listArray2 = new ArrayList<>();
                    for (int i = 0; i < HealthDevices.size(); i++) {
                        KeyPairBoolData h = new KeyPairBoolData();
                        h.setId(HealthDevices.get(i).getId());
                        h.setName(HealthDevices.get(i).getName());

                        if (idarray5.contains(HealthDevices.get(i).getName())) {

                            h.setSelected(true);
                        }
                        //h.setSelected(i < 5);
                        listArray2.add(h);
                    }

                    MultiSpinnerSearch multiSelectSpinnerWithSearch1 = findViewById(R.id.aidsAppliances);
                    multiSelectSpinnerWithSearch1.setSearchEnabled(true);
                    multiSelectSpinnerWithSearch1.setHintText("Aids Appliances");
                    multiSelectSpinnerWithSearch1.setClearText("Close & Clear");
                    multiSelectSpinnerWithSearch1.setSearchHint("Select your Aids Appliances");
                    multiSelectSpinnerWithSearch1.setEmptyTitle("Not Data Found!");
                    multiSelectSpinnerWithSearch1.setShowSelectAllButton(true);
                    multiSelectSpinnerWithSearch1.setItems(listArray2, items -> {
                        //The followings are selected items.
                        for (int i = 0; i < items.size(); i++) {
                            Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());

                            String userid = String.valueOf(items.get(i).getId());
                            id_Array.add(userid);

                            String name = items.get(i).getName();
                            id_Array1.add(name);


                        }
                    });


                    binding.noofappliances.setText(singleMember.get(0).getTherapysessions());
                    binding.totalcost.setText(singleMember.get(0).getTotalCost());
                    binding.patientcontribution.setText(singleMember.get(0).getPatientContribution());
                    binding.donorcontribution.setText(singleMember.get(0).getDonorContribution());
                    binding.correctivesurgery.setText(singleMember.get(0).getSurgery());
                    binding.surgerywhere.setText(singleMember.get(0).getSurgerywhere());
                    binding.surgerywhat.setText(singleMember.get(0).getSurgerywherewhat());
                    //binding.homemodification.setText(singleMember.get(0).getHomerecommend());
                    // binding.homemodificationwhere.setText(singleMember.get(0).getHomerecommendwhat());

                    String financialservices = String.valueOf(singleMember.get(0).getHomerecommend());
                    if (financialservices.equals("") || financialservices.equals("null")) {
                    } else if (financialservices.equalsIgnoreCase("yes") || financialservices.equalsIgnoreCase("true")) {
                        binding.homemodification.setOn(true);
                        binding.homemodificationwhere.setVisibility(View.VISIBLE);
                    } else {
                        binding.homemodification.setOn(false);
                        binding.homemodificationwhere.setVisibility(View.GONE);
                    }
                    binding.homemodificationwhere.setText(singleMember.get(0).getHomerecommendwhat());

                    binding.developmentDD.setText(singleMember.get(0).getSpeechLangDev());
                    binding.OPMEDD.setText(singleMember.get(0).getOpmeDd());
                    binding.abnormal.setText(singleMember.get(0).getIfAbnormal());
                    binding.SpeechArticulationDD.setText(singleMember.get(0).getSpeechArticulation());
                    binding.misarticulation.setText(singleMember.get(0).getIfMisarticulation());
                    binding.FluencyDD.setText(singleMember.get(0).getFluency());
                    binding.QualityDD.setText(singleMember.get(0).getVoiceQuality());
                    binding.PitchDD.setText(singleMember.get(0).getVoicePitch());
                    binding.LoudnessDD.setText(singleMember.get(0).getVoiceLoudness());
                    binding.ModeofcommunicationDD.setText(singleMember.get(0).getModeCommunication());
                    binding.ComprehensionDD.setText(singleMember.get(0).getLanguageComprehension());
                    binding.ExpressionDD.setText(singleMember.get(0).getLanguageExpression());
                    binding.TestResults.setText(singleMember.get(0).getTestResult());
                    binding.RightDD.setText(singleMember.get(0).getRightOtoscopy());
                    binding.LeftDD.setText(singleMember.get(0).getLeftOtoscopy());
                    binding.audiometryRightDD.setText(singleMember.get(0).getPureToneAudioRight());
                    binding.audiometryLeftDD.setText(singleMember.get(0).getPureToneAudioLeft());
                    binding.SpeechAudtest.setText(singleMember.get(0).getSpeechAudioTest());
                    binding.EardischargeDD.setText(singleMember.get(0).getEarDischarge());
                    binding.Ling6Sounds.setText(singleMember.get(0).getLingSound());
                    binding.modelRightDD.setText(singleMember.get(0).getHearingModelRight());
                    binding.modelLeftDD.setText(singleMember.get(0).getHearingModelLeft());
                    binding.EarRightDD.setText(singleMember.get(0).getEarMouldRight());
                    binding.EarLeftDD.setText(singleMember.get(0).getEarMouldLeft());
                    binding.specialistremarks.setText(singleMember.get(0).getSpecialistsRemark());
                    binding.repaircost.setText(singleMember.get(0).getRepairCostCbr());
                    binding.rpatientcontribution.setText(singleMember.get(0).getPatientContributionCbr());
                    binding.rdonorcontribution.setText(singleMember.get(0).getDonorContributionCbr());
                    binding.repaircost1.setText(singleMember.get(0).getRepairCost());
                    binding.rpatientcontribution1.setText(singleMember.get(0).getPatientContributionRepair());
                    binding.rdonorcontribution1.setText(singleMember.get(0).getDonorContributionRepair());

                    String ihp = String.valueOf(singleMember.get(0).getIhp());
                    if (ihp.equals("") || ihp.equals("null")) {
                    } else if (ihp.equalsIgnoreCase("yes") || ihp.equalsIgnoreCase("true")) {
                        binding.ihp.setOn(true);
                    } else {
                        binding.ihp.setOn(false);
                    }

                    benificaryId = singleMember.get(0).getBenificiaryId();
                    id = singleMember.get(0).getId();

                    Log.d("gcyhbghv", idarray2.toString());

                    boolean ans_Screening = idarray2.contains("Screening");
                    if (ans_Screening)
                        binding.linScreening.setVisibility(View.VISIBLE);
                    else
                        binding.linScreening.setVisibility(View.GONE);

                    boolean ans_Trial = idarray2.contains("Trial");
                    if (ans_Trial)
                        binding.linTrial.setVisibility(View.VISIBLE);
                    else
                        binding.linTrial.setVisibility(View.GONE);

                    boolean ans_GAITTraining = idarray2.contains("GAIT Training");
                    if (ans_GAITTraining)
                        binding.linGAITTraining.setVisibility(View.VISIBLE);
                    else
                        binding.linGAITTraining.setVisibility(View.GONE);

                    boolean ans_Assessment = idarray2.contains("Assessment");
                    if (ans_Assessment)
                        binding.linAssessment.setVisibility(View.VISIBLE);
                    else
                        binding.linAssessment.setVisibility(View.GONE);

                    boolean ans_MedicalReferral = idarray2.contains("Referral");
                    if (ans_MedicalReferral)
                        binding.linMedicalReferral.setVisibility(View.VISIBLE);
                    else
                        binding.linMedicalReferral.setVisibility(View.GONE);

                    boolean ans_TherapyServices = idarray2.contains("Therapy Service");
                    if (ans_TherapyServices)
                        binding.linTherapyServices.setVisibility(View.VISIBLE);
                    else
                        binding.linTherapyServices.setVisibility(View.GONE);

                    boolean ans_Fitment = idarray2.contains("Fitment");
                    if (ans_Fitment)
                        binding.linFitment.setVisibility(View.VISIBLE);
                    else
                        binding.linFitment.setVisibility(View.GONE);

                    boolean ans_CorrectiveSurgery = idarray2.contains("Corrective Surgery");
                    if (ans_CorrectiveSurgery)
                        binding.linCorrectiveSurgery.setVisibility(View.VISIBLE);
                    else
                        binding.linCorrectiveSurgery.setVisibility(View.GONE);

                    boolean ans_HomeModificcation = idarray2.contains("Home Modification");
                    if (ans_HomeModificcation)
                        binding.linHomeModificcation.setVisibility(View.VISIBLE);
                    else
                        binding.linHomeModificcation.setVisibility(View.GONE);

                    boolean ans_SpeechLanguage = idarray2.contains("Speech & Language");
                    if (ans_SpeechLanguage)
                        binding.linSpeechLanguage.setVisibility(View.VISIBLE);
                    else
                        binding.linSpeechLanguage.setVisibility(View.GONE);

                    boolean ans_Hearing = idarray2.contains("Hearing");
                    if (ans_Hearing)
                        binding.linHearing.setVisibility(View.VISIBLE);
                    else
                        binding.linHearing.setVisibility(View.GONE);

                    boolean ans_Hearing1 = idarray2.contains("Hearing");
                    if (ans_Hearing1)
                        binding.linHearingAid.setVisibility(View.VISIBLE);
                    else
                        binding.linHearingAid.setVisibility(View.GONE);

                    boolean ans_IHP = idarray2.contains("IHP");
                    if (ans_IHP)
                        binding.linIndividualHealthPlan.setVisibility(View.VISIBLE);
                    else
                        binding.linIndividualHealthPlan.setVisibility(View.GONE);

                    boolean ans_Followup = idarray2.contains("Followup");
                    if (ans_Followup)
                        binding.linFallowUp.setVisibility(View.VISIBLE);
                    else
                        binding.linFallowUp.setVisibility(View.GONE);

                    boolean ans_AARepair = idarray2.contains("A&A Repair");
                    if (ans_AARepair)
                        binding.linRepair1.setVisibility(View.VISIBLE);
                    else
                        binding.linRepair1.setVisibility(View.GONE);

                    boolean ans_CBRAARepair = idarray2.contains("CBR A&A Repair");
                    if (ans_CBRAARepair)
                        binding.linRepair.setVisibility(View.VISIBLE);
                    else
                        binding.linRepair.setVisibility(View.GONE);


                }
            }
        });
    }

    private void callofflinedata() {
        localRepo.getSelectedHealthCareData((CommonClass.tempid)).observe(this, new Observer<List<HealthCareData>>() {
            @Override
            public void onChanged(@Nullable List<HealthCareData> singleMember) {
                if (singleMember.size() > 0) {
                    binding.startdateofshg.setText(singleMember.get(0).getScreeningdate());

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;

                    idarray2.clear();

                    idarray2 = singleMember.get(0).getServiceName();
                    idarray5 = singleMember.get(0).getDeviceName();

                    StringBuffer sb = new StringBuffer();

                    String gettdisability = getHealthActivity(EditHealthActivity.this);
                    try {
                        JSONObject obj = new JSONObject(gettdisability);
                        if (obj.optString("status", "fail").equals("true")) {
                            JSONArray jsonArray = obj.optJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HealthActivityName.add(jsonObject.getString("service_name"));
                                HealthActivityId.add(jsonObject.getString("id"));

                                String name = jsonObject.getString("service_name");
                                String id = jsonObject.getString("id");

                                long lon_id = Long.valueOf(id);

                                VisitModelClass visitModelClass = new VisitModelClass(lon_id, name);
                                HealthActivityServices.add(visitModelClass);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < HealthActivityServices.size(); i++) {
                        KeyPairBoolData h = new KeyPairBoolData();
                        h.setId(HealthActivityServices.get(i).getId());
                        h.setName(HealthActivityServices.get(i).getName());
                        if (idarray2.contains(HealthActivityServices.get(i).getName())) {

                            h.setSelected(true);
                        }
                        //h.setSelected(i < 5);
                        listArray1.add(h);

                        Log.d("hdgbdjb", listArray1.toString());
                    }

                    multiSelectSpinnerWithSearch = findViewById(R.id.Services);
                    multiSelectSpinnerWithSearch.setSearchEnabled(true);
                    multiSelectSpinnerWithSearch.setHintText("Select Services");
                    multiSelectSpinnerWithSearch.setClearText("Close & Clear");
                    multiSelectSpinnerWithSearch.setSearchHint("Select your Services");
                    multiSelectSpinnerWithSearch.setEmptyTitle("Not Data Found!");
                    multiSelectSpinnerWithSearch.setShowSelectAllButton(true);

                    multiSelectSpinnerWithSearch.setItems(listArray1, items -> {

                        idarray3.clear();
                        idarray4.clear();

                        //The followings are selected items.
                        for (int i = 0; i < items.size(); i++) {
                            Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());

                            String userid = String.valueOf(items.get(i).getId());
                            String name = String.valueOf(items.get(i).getName());
                            idarray3.add(name);
                            idarray4.add(userid);

                            //Toast.makeText(EditHealthActivity.this, "sonuarr" + idarray3, Toast.LENGTH_SHORT).show();
                        }

                        Log.d("gcyhbghv", idarray3.toString());

                        boolean ans_Screening = idarray3.contains("Screening");
                        if (ans_Screening)
                            binding.linScreening.setVisibility(View.VISIBLE);
                        else
                            binding.linScreening.setVisibility(View.GONE);

                        boolean ans_Trial = idarray3.contains("Trial");
                        if (ans_Trial)
                            binding.linTrial.setVisibility(View.VISIBLE);
                        else
                            binding.linTrial.setVisibility(View.GONE);

                        boolean ans_GAITTraining = idarray3.contains("GAIT Training");
                        if (ans_GAITTraining)
                            binding.linGAITTraining.setVisibility(View.VISIBLE);
                        else
                            binding.linGAITTraining.setVisibility(View.GONE);

                        boolean ans_Assessment = idarray3.contains("Assessment");
                        if (ans_Assessment)
                            binding.linAssessment.setVisibility(View.VISIBLE);
                        else
                            binding.linAssessment.setVisibility(View.GONE);

                        boolean ans_MedicalReferral = idarray3.contains("Referral");
                        if (ans_MedicalReferral)
                            binding.linMedicalReferral.setVisibility(View.VISIBLE);
                        else
                            binding.linMedicalReferral.setVisibility(View.GONE);

                        boolean ans_TherapyServices = idarray3.contains("Therapy Service");
                        if (ans_TherapyServices)
                            binding.linTherapyServices.setVisibility(View.VISIBLE);
                        else
                            binding.linTherapyServices.setVisibility(View.GONE);

                        boolean ans_Fitment = idarray3.contains("Fitment");
                        if (ans_Fitment)
                            binding.linFitment.setVisibility(View.VISIBLE);
                        else
                            binding.linFitment.setVisibility(View.GONE);

                        boolean ans_CorrectiveSurgery = idarray3.contains("Corrective Surgery");
                        if (ans_CorrectiveSurgery)
                            binding.linCorrectiveSurgery.setVisibility(View.VISIBLE);
                        else
                            binding.linCorrectiveSurgery.setVisibility(View.GONE);

                        boolean ans_HomeModificcation = idarray3.contains("Home Modification");
                        if (ans_HomeModificcation)
                            binding.linHomeModificcation.setVisibility(View.VISIBLE);
                        else
                            binding.linHomeModificcation.setVisibility(View.GONE);

                        boolean ans_SpeechLanguage = idarray3.contains("Speech & Language");
                        if (ans_SpeechLanguage)
                            binding.linSpeechLanguage.setVisibility(View.VISIBLE);
                        else
                            binding.linSpeechLanguage.setVisibility(View.GONE);

                        boolean ans_Hearing = idarray3.contains("Hearing");
                        if (ans_Hearing)
                            binding.linHearing.setVisibility(View.VISIBLE);
                        else
                            binding.linHearing.setVisibility(View.GONE);

                        boolean ans_Hearing1 = idarray3.contains("Hearing");
                        if (ans_Hearing1)
                            binding.linHearingAid.setVisibility(View.VISIBLE);
                        else
                            binding.linHearingAid.setVisibility(View.GONE);

                        boolean ans_IHP = idarray3.contains("IHP");
                        if (ans_IHP)
                            binding.linIndividualHealthPlan.setVisibility(View.VISIBLE);
                        else
                            binding.linIndividualHealthPlan.setVisibility(View.GONE);

                        boolean ans_Followup = idarray3.contains("Followup");
                        if (ans_Followup)
                            binding.linFallowUp.setVisibility(View.VISIBLE);
                        else
                            binding.linFallowUp.setVisibility(View.GONE);

                        boolean ans_AARepair = idarray3.contains("A&A Repair");
                        if (ans_AARepair)
                            binding.linRepair1.setVisibility(View.VISIBLE);
                        else
                            binding.linRepair1.setVisibility(View.GONE);

                        boolean ans_CBRAARepair = idarray3.contains("CBR A&A Repair");
                        if (ans_CBRAARepair)
                            binding.linRepair.setVisibility(View.VISIBLE);
                        else
                            binding.linRepair.setVisibility(View.GONE);
                    });

                    binding.startdateofshg.setText(singleMember.get(0).getScreeningdate());
                    binding.dateofassiment.setText(singleMember.get(0).getAssessmentdate());
                    binding.whodidassesmenta.setText(singleMember.get(0).getAssessmentwho());
                    binding.wherewasitdonea.setText(singleMember.get(0).getAssessmentwhere());
                    // binding.referincal.setText(singleMember.get(0).getReferral());
                    //binding.refertowhichplace.setText(singleMember.get(0).getReferralplace());
                    //binding.presecription.setText(singleMember.get(0).getReferralprescription());

                    String referal = String.valueOf(singleMember.get(0).getReferral());
                    if (referal.equals("") || referal.equals("null")) {
                    } else if (referal.equalsIgnoreCase("yes") || referal.equalsIgnoreCase("true")) {
                        binding.referincal.setOn(true);
                        binding.refertowhichplace.setVisibility(View.VISIBLE);
                        binding.presecription.setVisibility(View.VISIBLE);
                    } else {
                        binding.referincal.setOn(false);
                        binding.refertowhichplace.setVisibility(View.GONE);
                        binding.presecription.setVisibility(View.GONE);
                    }
                    binding.homemodificationwhere.setText(singleMember.get(0).getReferralplace());
                    binding.homemodificationwhere.setText(singleMember.get(0).getReferralprescription());

                    binding.trailforwhat.setText(singleMember.get(0).getTrialwhat());
                    binding.traildate.setText(singleMember.get(0).getTrialdate());
                    binding.gaitfrequency.setText(singleMember.get(0).getGaitfrequency());
                    binding.howanydone.setText(singleMember.get(0).getGaithowmany());
                    binding.thrpynumberoftime.setText(singleMember.get(0).getTherapyfrequency());
                    binding.numberofsession.setText(singleMember.get(0).getTherapysessions());
                    binding.fitmentwho.setText(singleMember.get(0).getFitmentwho());
                    binding.fitmentwhere.setText(singleMember.get(0).getFitmentwhere());
                    binding.fitmentkind.setText(singleMember.get(0).getFitmentdevice());

                    String getDevices = getHealthDevices(EditHealthActivity.this);
                    try {
                        JSONObject obj = new JSONObject(getDevices);
                        if (obj.optString("status", "fail").equals("true")) {
                            JSONArray jsonArray = obj.optJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                HealthDevicesName.add(jsonObject.getString("device_name"));
                                HealthDevicesID.add(jsonObject.getString("id"));

                                String name = jsonObject.getString("device_name");
                                String id = jsonObject.getString("id");

                                long lon_id = Long.valueOf(id);

                                VisitModelClass visitModelClass = new VisitModelClass(lon_id, name);
                                HealthDevices.add(visitModelClass);
                            }

                            int size = HealthDevicesName.size();
                            int size1 = HealthDevicesID.size();

                            Log.d("hbdsb", size + "     " + size1);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    final List<KeyPairBoolData> listArray2 = new ArrayList<>();
                    for (int i = 0; i < HealthDevices.size(); i++) {
                        KeyPairBoolData h = new KeyPairBoolData();
                        h.setId(HealthDevices.get(i).getId());
                        h.setName(HealthDevices.get(i).getName());

                        if (idarray5.contains(HealthDevices.get(i).getName())) {

                            h.setSelected(true);
                        }
                        //h.setSelected(i < 5);
                        listArray2.add(h);
                    }

                    MultiSpinnerSearch multiSelectSpinnerWithSearch1 = findViewById(R.id.aidsAppliances);
                    multiSelectSpinnerWithSearch1.setSearchEnabled(true);
                    multiSelectSpinnerWithSearch1.setHintText("Aids Appliances");
                    multiSelectSpinnerWithSearch1.setClearText("Close & Clear");
                    multiSelectSpinnerWithSearch1.setSearchHint("Select your Aids Appliances");
                    multiSelectSpinnerWithSearch1.setEmptyTitle("Not Data Found!");
                    multiSelectSpinnerWithSearch1.setShowSelectAllButton(true);
                    multiSelectSpinnerWithSearch1.setItems(listArray2, items -> {
                        //The followings are selected items.
                        for (int i = 0; i < items.size(); i++) {
                            Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());

                            String userid = String.valueOf(items.get(i).getId());
                            id_Array.add(userid);
                        }
                    });

                    binding.noofappliances.setText(singleMember.get(0).getTherapysessions());
                    binding.totalcost.setText(singleMember.get(0).getTotalCost());
                    binding.patientcontribution.setText(singleMember.get(0).getPatientContribution());
                    binding.donorcontribution.setText(singleMember.get(0).getDonorContribution());
                    binding.correctivesurgery.setText(singleMember.get(0).getSurgery());
                    binding.surgerywhere.setText(singleMember.get(0).getSurgerywhere());
                    binding.surgerywhat.setText(singleMember.get(0).getSurgerywherewhat());
                    //binding.homemodification.setText(singleMember.get(0).getHomerecommend());
                    // binding.homemodificationwhere.setText(singleMember.get(0).getHomerecommendwhat());

                    String financialservices = String.valueOf(singleMember.get(0).getHomerecommend());
                    if (financialservices.equals("") || financialservices.equals("null")) {
                    } else if (financialservices.equalsIgnoreCase("yes") || financialservices.equalsIgnoreCase("true")) {
                        binding.homemodification.setOn(true);
                        binding.homemodificationwhere.setVisibility(View.VISIBLE);
                    } else {
                        binding.homemodification.setOn(false);
                        binding.homemodificationwhere.setVisibility(View.GONE);
                    }
                    binding.homemodificationwhere.setText(singleMember.get(0).getHomerecommendwhat());

                    binding.developmentDD.setText(singleMember.get(0).getSpeechLangDev());
                    binding.OPMEDD.setText(singleMember.get(0).getOpmeDd());
                    binding.abnormal.setText(singleMember.get(0).getIfAbnormal());
                    binding.SpeechArticulationDD.setText(singleMember.get(0).getSpeechArticulation());
                    binding.misarticulation.setText(singleMember.get(0).getIfMisarticulation());
                    binding.FluencyDD.setText(singleMember.get(0).getFluency());
                    binding.QualityDD.setText(singleMember.get(0).getVoiceQuality());
                    binding.PitchDD.setText(singleMember.get(0).getVoicePitch());
                    binding.LoudnessDD.setText(singleMember.get(0).getVoiceLoudness());
                    binding.ModeofcommunicationDD.setText(singleMember.get(0).getModeCommunication());
                    binding.ComprehensionDD.setText(singleMember.get(0).getLanguageComprehension());
                    binding.ExpressionDD.setText(singleMember.get(0).getLanguageExpression());
                    binding.TestResults.setText(singleMember.get(0).getTestResult());
                    binding.RightDD.setText(singleMember.get(0).getRightOtoscopy());
                    binding.LeftDD.setText(singleMember.get(0).getLeftOtoscopy());
                    binding.audiometryRightDD.setText(singleMember.get(0).getPureToneAudioRight());
                    binding.audiometryLeftDD.setText(singleMember.get(0).getPureToneAudioLeft());
                    binding.SpeechAudtest.setText(singleMember.get(0).getSpeechAudioTest());
                    binding.EardischargeDD.setText(singleMember.get(0).getEarDischarge());
                    binding.Ling6Sounds.setText(singleMember.get(0).getLingSound());
                    binding.modelRightDD.setText(singleMember.get(0).getHearingModelRight());
                    binding.modelLeftDD.setText(singleMember.get(0).getHearingModelLeft());
                    binding.EarRightDD.setText(singleMember.get(0).getEarMouldRight());
                    binding.EarLeftDD.setText(singleMember.get(0).getEarMouldLeft());
                    binding.specialistremarks.setText(singleMember.get(0).getSpecialistsRemark());
                    binding.repaircost.setText(singleMember.get(0).getRepairCostCbr());
                    binding.rpatientcontribution.setText(singleMember.get(0).getPatientContributionCbr());
                    binding.rdonorcontribution.setText(singleMember.get(0).getDonorContributionCbr());
                    binding.repaircost1.setText(singleMember.get(0).getRepairCost());
                    binding.rpatientcontribution1.setText(singleMember.get(0).getPatientContributionRepair());
                    binding.rdonorcontribution1.setText(singleMember.get(0).getDonorContributionRepair());

                    String ihp = String.valueOf(singleMember.get(0).getIhp());
                    if (ihp.equals("") || ihp.equals("null")) {
                    } else if (ihp.equalsIgnoreCase("yes") || ihp.equalsIgnoreCase("true")) {
                        binding.ihp.setOn(true);
                    } else {
                        binding.ihp.setOn(false);
                    }

                    benificaryId = singleMember.get(0).getBenificiaryId();
                    id = singleMember.get(0).getId();

                    Log.d("gcyhbghv", idarray2.toString());

                    boolean ans_Screening = idarray2.contains("Screening");
                    if (ans_Screening)
                        binding.linScreening.setVisibility(View.VISIBLE);
                    else
                        binding.linScreening.setVisibility(View.GONE);

                    boolean ans_Trial = idarray2.contains("Trial");
                    if (ans_Trial)
                        binding.linTrial.setVisibility(View.VISIBLE);
                    else
                        binding.linTrial.setVisibility(View.GONE);

                    boolean ans_GAITTraining = idarray2.contains("GAIT Training");
                    if (ans_GAITTraining)
                        binding.linGAITTraining.setVisibility(View.VISIBLE);
                    else
                        binding.linGAITTraining.setVisibility(View.GONE);

                    boolean ans_Assessment = idarray2.contains("Assessment");
                    if (ans_Assessment)
                        binding.linAssessment.setVisibility(View.VISIBLE);
                    else
                        binding.linAssessment.setVisibility(View.GONE);

                    boolean ans_MedicalReferral = idarray2.contains("Referral");
                    if (ans_MedicalReferral)
                        binding.linMedicalReferral.setVisibility(View.VISIBLE);
                    else
                        binding.linMedicalReferral.setVisibility(View.GONE);

                    boolean ans_TherapyServices = idarray2.contains("Therapy Service");
                    if (ans_TherapyServices)
                        binding.linTherapyServices.setVisibility(View.VISIBLE);
                    else
                        binding.linTherapyServices.setVisibility(View.GONE);

                    boolean ans_Fitment = idarray2.contains("Fitment");
                    if (ans_Fitment)
                        binding.linFitment.setVisibility(View.VISIBLE);
                    else
                        binding.linFitment.setVisibility(View.GONE);

                    boolean ans_CorrectiveSurgery = idarray2.contains("Corrective Surgery");
                    if (ans_CorrectiveSurgery)
                        binding.linCorrectiveSurgery.setVisibility(View.VISIBLE);
                    else
                        binding.linCorrectiveSurgery.setVisibility(View.GONE);

                    boolean ans_HomeModificcation = idarray2.contains("Home Modification");
                    if (ans_HomeModificcation)
                        binding.linHomeModificcation.setVisibility(View.VISIBLE);
                    else
                        binding.linHomeModificcation.setVisibility(View.GONE);

                    boolean ans_SpeechLanguage = idarray2.contains("Speech & Language");
                    if (ans_SpeechLanguage)
                        binding.linSpeechLanguage.setVisibility(View.VISIBLE);
                    else
                        binding.linSpeechLanguage.setVisibility(View.GONE);

                    boolean ans_Hearing = idarray2.contains("Hearing");
                    if (ans_Hearing)
                        binding.linHearing.setVisibility(View.VISIBLE);
                    else
                        binding.linHearing.setVisibility(View.GONE);

                    boolean ans_Hearing1 = idarray2.contains("Hearing");
                    if (ans_Hearing1)
                        binding.linHearingAid.setVisibility(View.VISIBLE);
                    else
                        binding.linHearingAid.setVisibility(View.GONE);

                    boolean ans_IHP = idarray2.contains("IHP");
                    if (ans_IHP)
                        binding.linIndividualHealthPlan.setVisibility(View.VISIBLE);
                    else
                        binding.linIndividualHealthPlan.setVisibility(View.GONE);

                    boolean ans_Followup = idarray2.contains("Followup");
                    if (ans_Followup)
                        binding.linFallowUp.setVisibility(View.VISIBLE);
                    else
                        binding.linFallowUp.setVisibility(View.GONE);

                    boolean ans_AARepair = idarray2.contains("A&A Repair");
                    if (ans_AARepair)
                        binding.linRepair1.setVisibility(View.VISIBLE);
                    else
                        binding.linRepair1.setVisibility(View.GONE);

                    boolean ans_CBRAARepair = idarray2.contains("CBR A&A Repair");
                    if (ans_CBRAARepair)
                        binding.linRepair.setVisibility(View.VISIBLE);
                    else
                        binding.linRepair.setVisibility(View.GONE);

                }
            }
        });
    }

    public void openPdf() {
        Intent intentpdf = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intentpdf.setType("application/pdf");
        intentpdf.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intentpdf, "Select PDF"), 20);
    }

    public void launchGalleryIntentfordoc() {

//        /////////this is right for doc  ////////////////////////

        String[] mimeTypes =
                {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" // .doc & .docx
                };

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
            if (mimeTypes.length > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            }
        } else {
            String mimeTypesStr = "";
            for (String mimeType : mimeTypes) {
                mimeTypesStr += mimeType + "|";
            }
            intent.setType(mimeTypesStr.substring(0, mimeTypesStr.length() - 1));
        }
        startActivityForResult(Intent.createChooser(intent, "ChooseFile"), 30);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 20:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedFileURI = Uri.parse(data.getData().getPath());
                    Uri selectedFileURI1 = Uri.parse(data.getData().toString());
                    String extension;
                    ContentResolver contentResolver = EditHealthActivity.this.getContentResolver();
                    MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                    extension = mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(data.getData()));
                    // String extension = getfileExtension(TimeSlotSelectActivity.this, data.getData());

                    String encodedBase64 = null;
                    try {
                        InputStream in = getContentResolver().openInputStream(selectedFileURI1);
                        byte[] bytes = getBytes(in);
                        Log.d("data", "onActivityResult: bytes size=" + bytes.length);
                        Log.d("data", "onActivityResult: Base64string=" + Base64.encodeToString(bytes, Base64.DEFAULT));
                        String ansValue = Base64.encodeToString(bytes, Base64.DEFAULT);
                        String Document = Base64.encodeToString(bytes, Base64.DEFAULT);
                        FinalString = "data:application/" + extension + ";base64," + ansValue;
                        imagelist.add(FinalString);
                        if (getvalue.equals("1")) {
                            binding.imagesiew.setVisibility(View.VISIBLE);
                            binding.frame1.setVisibility(View.VISIBLE);
                            binding.imageview1.setImageDrawable(getResources().getDrawable(R.drawable.pdfimg));
                            getvalue = "2";
                        } else if (getvalue.equals("2")) {
                            binding.frame2.setVisibility(View.VISIBLE);
                            binding.imageview2.setImageDrawable(getResources().getDrawable(R.drawable.pdfimg));
                            getvalue = "3";
                        } else if (getvalue.equals("3")) {
                            binding.frame3.setVisibility(View.VISIBLE);
                            binding.imageview3.setImageDrawable(getResources().getDrawable(R.drawable.pdfimg));
                            getvalue = "4";
                        } else if (getvalue.equals("4")) {
                            binding.frame4.setVisibility(View.VISIBLE);
                            binding.imageview4.setImageDrawable(getResources().getDrawable(R.drawable.pdfimg));
                            getvalue = "5";
                        } else if (getvalue.equals("5")) {
                            binding.frame5.setVisibility(View.VISIBLE);
                            binding.imageview5.setImageDrawable(getResources().getDrawable(R.drawable.pdfimg));
                        } else {

                            Toast.makeText(this, "You have Add Already Five Document", Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        Log.d("error", "onActivityResult: " + e);
                    }
                }
                break;

            case 30:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedFileURI = Uri.parse(data.getData().getPath());
                    Uri selectedFileURI1 = Uri.parse(data.getData().toString());
//                    file = new File(selectedFileURI.getPath().toString());
                    String extension;
                    ContentResolver contentResolver = EditHealthActivity.this.getContentResolver();
                    MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                    extension = mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(data.getData()));
                    // String extension = getfileExtension(TimeSlotSelectActivity.this, data.getData());

                    String encodedBase64 = null;
                    try {
                        InputStream in = getContentResolver().openInputStream(selectedFileURI1);
                        byte[] bytes = getBytes(in);
                        Log.d("data", "onActivityResult: bytes size=" + bytes.length);
                        Log.d("data", "onActivityResult: Base64string=" + Base64.encodeToString(bytes, Base64.DEFAULT));
                        String ansValue = Base64.encodeToString(bytes, Base64.DEFAULT);
                        String Document = Base64.encodeToString(bytes, Base64.DEFAULT);
                        if (extension.equalsIgnoreCase("doc")) {
                            FinalString = "data:application/msword" + ";base64," + ansValue;
                            imagelist.add(FinalString);
                        }
                        if (getvalue.equals("1")) {
                            binding.imagesiew.setVisibility(View.VISIBLE);
                            binding.frame1.setVisibility(View.VISIBLE);
                            binding.imageview1.setImageDrawable(getResources().getDrawable(R.drawable.documentdetail));
                            getvalue = "2";
                        } else if (getvalue.equals("2")) {
                            binding.frame2.setVisibility(View.VISIBLE);
                            binding.imageview2.setImageDrawable(getResources().getDrawable(R.drawable.documentdetail));
                            getvalue = "3";
                        } else if (getvalue.equals("3")) {
                            binding.frame3.setVisibility(View.VISIBLE);
                            binding.imageview3.setImageDrawable(getResources().getDrawable(R.drawable.documentdetail));
                            getvalue = "4";
                        } else if (getvalue.equals("4")) {
                            binding.frame4.setVisibility(View.VISIBLE);
                            binding.imageview4.setImageDrawable(getResources().getDrawable(R.drawable.documentdetail));
                            getvalue = "5";
                        } else if (getvalue.equals("5")) {
                            binding.frame5.setVisibility(View.VISIBLE);
                            binding.imageview5.setImageDrawable(getResources().getDrawable(R.drawable.documentdetail));
                        } else {

                            Toast.makeText(this, "You have Add Already Five Document", Toast.LENGTH_SHORT).show();
                        }
//                        else if(extension.equalsIgnoreCase("docx")){
//
//                            FinalString = "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document" + ";base64," + ansValue;
//                        }
                        //textView.setText(selectedFileURI1 + "." +extension);


                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        Log.d("error", "onActivityResult: " + e);
                    }
                    break;


                }
                break;

            case 101:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        Uri path = data.getData();
                        String realPath = ImageFilePath.getPath(EditHealthActivity.this, data.getData());
                        //  Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(EditHealthActivity.this.getContentResolver(), path);

                        String imagePath = "data:image/jpeg;base64," + Utils.getEncoded64ImageStringFromBitmap(bitmap);
                        imagelist.add(imagePath);
                        if (getvalue.equals("1")) {
                            binding.frame1.setVisibility(View.VISIBLE);
                            binding.imageview1.setVisibility(View.VISIBLE);
                            binding.imageview1.setImageBitmap(bitmap);
                            getvalue = "2";
                        } else if (getvalue.equals("2")) {
                            binding.frame2.setVisibility(View.VISIBLE);
                            binding.imageview2.setImageBitmap(bitmap);
                            getvalue = "3";
                        } else if (getvalue.equals("3")) {
                            binding.frame3.setVisibility(View.VISIBLE);
                            binding.imageview3.setImageBitmap(bitmap);
                            getvalue = "3";
                        } else if (getvalue.equals("4")) {
                            binding.frame4.setVisibility(View.VISIBLE);
                            binding.imageview4.setImageBitmap(bitmap);
                            getvalue = "3";
                        } else if (getvalue.equals("5")) {
                            binding.frame5.setVisibility(View.VISIBLE);
                            binding.imageview5.setImageBitmap(bitmap);
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
        }

    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public void localbeneficaryDataCall1() {
        HealthCareData healthCareData = new HealthCareData();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date date = new Date();

        String randoNoStr = getRandomNumber();
        healthCareData.setId(id);

        healthCareData.setBeneficiaryId(benificaryId);
        healthCareData.setCreatedAt(formatter.format(date));
        healthCareData.setUpdatedAt(formatter.format(date));
        healthCareData.setUpdatedBy(userId);

        StringBuffer sb = new StringBuffer();

        if (idarray4.size() != 0) {

            for (String s : idarray4) {

                sb.append(s);
                sb.append(",");
            }

            String services_Id = sb.toString();

            // remove last character (,)
            services_Id = services_Id.substring(0, services_Id.length() - 1);

            Log.d("purposeofvisitdata", services_Id);

            healthCareData.setServiceDone(services_Id);

        } else {

            healthCareData.setServiceDone("");

        }

        StringBuffer sb1 = new StringBuffer();

        if (id_Array.size() != 0) {

            for (String s : id_Array) {

                sb1.append(s);
                sb1.append(",");
            }

            str_aidsAppliances = sb1.toString();

            // remove last character (,)
            str_aidsAppliances = str_aidsAppliances.substring(0, str_aidsAppliances.length() - 1);

            Log.d("purposeofvisitdata", str_aidsAppliances);


        } else {

            str_aidsAppliances = "";
        }

        // healthCareData.setServiceDone(idarray4.toString());
        healthCareData.setServiceName(idarray3);
        healthCareData.setDeviceName(id_Array1);
        healthCareData.setScreeningdate(binding.startdateofshg.getText().toString());
        healthCareData.setBenificiaryId(CommonClass.benfeciary_ID);
        healthCareData.setAssessmentdate(binding.dateofassiment.getText().toString());
        healthCareData.setAssessmentwhere(binding.wherewasitdonea.getText().toString());
        healthCareData.setAssessmentwho(binding.whodidassesmenta.getText().toString());
        healthCareData.setReferral(referincal);
        healthCareData.setReferralplace(binding.refertowhichplace.getText().toString());
        healthCareData.setReferralprescription(binding.presecription.getText().toString());
        healthCareData.setTrialwhat(binding.trailforwhat.getText().toString());
        healthCareData.setTrialdate(binding.traildate.getText().toString());
        healthCareData.setGaitfrequency(binding.gaitfrequency.getText().toString());
        healthCareData.setGaithowmany(binding.howanydone.getText().toString().trim());
        healthCareData.setTherapyfrequency(binding.thrpynumberoftime.getText().toString().trim());
        healthCareData.setTherapysessions(binding.numberofsession.getText().toString());
        healthCareData.setFitmentwho(binding.fitmentwho.getText().toString());
        healthCareData.setFitmentwhere(binding.fitmentwhere.getText().toString());
        healthCareData.setFitmentdevice(binding.fitmentkind.getText().toString());
        healthCareData.setAidAppliances(str_aidsAppliances);
        healthCareData.setNoofAppliances(binding.noofappliances.getText().toString());
        healthCareData.setTotalCost(binding.totalcost.getText().toString());
        healthCareData.setPatientContribution(binding.patientcontribution.getText().toString());
        healthCareData.setDonorContribution(binding.donorcontribution.getText().toString().trim());
        healthCareData.setRepairCost(binding.repaircost1.getText().toString().trim());
        healthCareData.setPatientContributionRepair(binding.rpatientcontribution1.getText().toString());
        healthCareData.setDonorContributionRepair(binding.rdonorcontribution1.getText().toString());
        healthCareData.setRepairCostCbr(binding.repaircost.getText().toString().trim());
        healthCareData.setPatientContributionCbr(binding.rpatientcontribution.getText().toString());
        healthCareData.setDonorContributionCbr(binding.rdonorcontribution.getText().toString());
        healthCareData.setFollowupfrequency(binding.fallowwhat.getText().toString());
        healthCareData.setFollowupsheet(fallowsheetupdated);
        healthCareData.setSurgery(correctivesurgery);
        healthCareData.setSurgerywhere(binding.surgerywhere.getText().toString().trim());
        healthCareData.setSurgerywherewhat(binding.surgerywhat.getText().toString().trim());
        healthCareData.setHomerecommend(homemodification);
        healthCareData.setHomerecommendwhat(binding.homemodificationwhere.getText().toString());
        healthCareData.setIhp(individualeducattionplan);
        healthCareData.setCreatedAt(formatter.format(date));
        healthCareData.setCreatedBy(userId);
        healthCareData.setSpeechLangDev(binding.developmentDD.getText().toString().trim());
        healthCareData.setOpmeDd(binding.OPMEDD.getText().toString().trim());
        healthCareData.setIfAbnormal(binding.abnormal.getText().toString().trim());
        healthCareData.setSpeechArticulation(binding.SpeechArticulationDD.getText().toString().trim());
        healthCareData.setIfMisarticulation(binding.misarticulation.getText().toString().trim());
        healthCareData.setFluency(binding.FluencyDD.getText().toString().trim());
        healthCareData.setVoiceQuality(binding.QualityDD.getText().toString().trim());
        healthCareData.setVoicePitch(binding.PitchDD.getText().toString().trim());
        healthCareData.setVoiceLoudness(binding.LoudnessDD.getText().toString().trim());
        healthCareData.setModeCommunication(binding.ModeofcommunicationDD.getText().toString().trim());
        healthCareData.setLanguageComprehension(binding.ComprehensionDD.getText().toString().trim());
        healthCareData.setLanguageExpression(binding.ExpressionDD.getText().toString().trim());
        healthCareData.setTestResult(binding.TestResults.getText().toString().trim());
        healthCareData.setRightOtoscopy(binding.RightDD.getText().toString().trim());
        healthCareData.setLeftOtoscopy(binding.LeftDD.getText().toString().trim());
        healthCareData.setPureToneAudioRight(binding.audiometryRightDD.getText().toString().trim());
        healthCareData.setPureToneAudioLeft(binding.audiometryLeftDD.getText().toString().trim());
        healthCareData.setSpeechAudioTest(binding.SpeechAudtest.getText().toString().trim());
        healthCareData.setEarDischarge(binding.EardischargeDD.getText().toString().trim());
        healthCareData.setEarDischarge(binding.EardischargeDD.getText().toString().trim());
        healthCareData.setLingSound(binding.Ling6Sounds.getText().toString().trim());
        healthCareData.setHearingModelLeft(binding.modelLeftDD.getText().toString().trim());
        healthCareData.setHearingModelRight(binding.modelRightDD.getText().toString().trim());
        healthCareData.setHearingModelRight(binding.modelRightDD.getText().toString().trim());
        healthCareData.setEarMouldRight(binding.EarRightDD.getText().toString().trim());
        healthCareData.setEarMouldLeft(binding.EarLeftDD.getText().toString().trim());
        healthCareData.setSpecialistsRemark(binding.specialistremarks.getText().toString().trim());
        healthCareData.setFlag("update");

        localRepo.updateHealthCareData(healthCareData);

        Toast.makeText(this, "Health updated in locally.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EditHealthActivity.this, BeneficaryDetailActivity.class);
        startActivity(intent);
    }

    public void localbeneficaryDataCall() {
        HealthCareData healthCareData = new HealthCareData();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date date = new Date();

        String randoNoStr = getRandomNumber();
        healthCareData.setId(CommonClass.tempid);

        healthCareData.setBeneficiaryId(CommonClass.benfeciary_ID);
        String bneid = CommonClass.benfeciary_ID;
        Toast.makeText(EditHealthActivity.this, bneid, Toast.LENGTH_SHORT).show();
        healthCareData.setCreatedAt(formatter.format(date));
        healthCareData.setUpdatedAt(formatter.format(date));
        healthCareData.setUpdatedBy(userId);

        StringBuffer sb = new StringBuffer();

        if (idarray4.size() != 0) {

            for (String s : idarray4) {

                sb.append(s);
                sb.append(",");
            }

            String services_Id = sb.toString();

            // remove last character (,)
            services_Id = services_Id.substring(0, services_Id.length() - 1);

            Log.d("purposeofvisitdata", services_Id);

            healthCareData.setServiceDone(services_Id);

        } else {

            healthCareData.setServiceDone("");

        }

        StringBuffer sb1 = new StringBuffer();

        if (id_Array.size() != 0) {

            for (String s : id_Array) {

                sb1.append(s);
                sb1.append(",");
            }

            str_aidsAppliances = sb1.toString();

            // remove last character (,)
            str_aidsAppliances = str_aidsAppliances.substring(0, str_aidsAppliances.length() - 1);

            Log.d("purposeofvisitdata", str_aidsAppliances);


        } else {

            str_aidsAppliances = "";
        }

        // healthCareData.setServiceDone(idarray4.toString());
        healthCareData.setServiceName(idarray3);
        healthCareData.setDeviceName(id_Array1);
        healthCareData.setScreeningdate(binding.startdateofshg.getText().toString());
        healthCareData.setBenificiaryId(CommonClass.benfeciary_ID);
        healthCareData.setAssessmentdate(binding.dateofassiment.getText().toString());
        healthCareData.setAssessmentwhere(binding.wherewasitdonea.getText().toString());
        healthCareData.setAssessmentwho(binding.whodidassesmenta.getText().toString());
        healthCareData.setReferral(referincal);
        healthCareData.setReferralplace(binding.refertowhichplace.getText().toString());
        healthCareData.setReferralprescription(binding.presecription.getText().toString());
        healthCareData.setTrialwhat(binding.trailforwhat.getText().toString());
        healthCareData.setTrialdate(binding.traildate.getText().toString());
        healthCareData.setGaitfrequency(binding.gaitfrequency.getText().toString());
        healthCareData.setGaithowmany(binding.howanydone.getText().toString().trim());
        healthCareData.setTherapyfrequency(binding.thrpynumberoftime.getText().toString().trim());
        healthCareData.setTherapysessions(binding.numberofsession.getText().toString());
        healthCareData.setFitmentwho(binding.fitmentwho.getText().toString());
        healthCareData.setFitmentwhere(binding.fitmentwhere.getText().toString());
        healthCareData.setFitmentdevice(binding.fitmentkind.getText().toString());
        healthCareData.setAidAppliances(str_aidsAppliances);
        healthCareData.setNoofAppliances(binding.noofappliances.getText().toString());
        healthCareData.setTotalCost(binding.totalcost.getText().toString());
        healthCareData.setPatientContribution(binding.patientcontribution.getText().toString());
        healthCareData.setDonorContribution(binding.donorcontribution.getText().toString().trim());
        healthCareData.setRepairCost(binding.repaircost1.getText().toString().trim());
        healthCareData.setPatientContributionRepair(binding.rpatientcontribution1.getText().toString());
        healthCareData.setDonorContributionRepair(binding.rdonorcontribution1.getText().toString());
        healthCareData.setRepairCostCbr(binding.repaircost.getText().toString().trim());
        healthCareData.setPatientContributionCbr(binding.rpatientcontribution.getText().toString());
        healthCareData.setDonorContributionCbr(binding.rdonorcontribution.getText().toString());
        healthCareData.setFollowupfrequency(binding.fallowwhat.getText().toString());
        healthCareData.setFollowupsheet(fallowsheetupdated);
        healthCareData.setSurgery(correctivesurgery);
        healthCareData.setSurgerywhere(binding.surgerywhere.getText().toString().trim());
        healthCareData.setSurgerywherewhat(binding.surgerywhat.getText().toString().trim());
        healthCareData.setHomerecommend(homemodification);
        healthCareData.setHomerecommendwhat(binding.homemodificationwhere.getText().toString());
        healthCareData.setIhp(individualeducattionplan);
        healthCareData.setCreatedAt(formatter.format(date));
        healthCareData.setCreatedBy(userId);
        healthCareData.setSpeechLangDev(binding.developmentDD.getText().toString().trim());
        healthCareData.setOpmeDd(binding.OPMEDD.getText().toString().trim());
        healthCareData.setIfAbnormal(binding.abnormal.getText().toString().trim());
        healthCareData.setSpeechArticulation(binding.SpeechArticulationDD.getText().toString().trim());
        healthCareData.setIfMisarticulation(binding.misarticulation.getText().toString().trim());
        healthCareData.setFluency(binding.FluencyDD.getText().toString().trim());
        healthCareData.setVoiceQuality(binding.QualityDD.getText().toString().trim());
        healthCareData.setVoicePitch(binding.PitchDD.getText().toString().trim());
        healthCareData.setVoiceLoudness(binding.LoudnessDD.getText().toString().trim());
        healthCareData.setModeCommunication(binding.ModeofcommunicationDD.getText().toString().trim());
        healthCareData.setLanguageComprehension(binding.ComprehensionDD.getText().toString().trim());
        healthCareData.setLanguageExpression(binding.ExpressionDD.getText().toString().trim());
        healthCareData.setTestResult(binding.TestResults.getText().toString().trim());
        healthCareData.setRightOtoscopy(binding.RightDD.getText().toString().trim());
        healthCareData.setLeftOtoscopy(binding.LeftDD.getText().toString().trim());
        healthCareData.setPureToneAudioRight(binding.audiometryRightDD.getText().toString().trim());
        healthCareData.setPureToneAudioLeft(binding.audiometryLeftDD.getText().toString().trim());
        healthCareData.setSpeechAudioTest(binding.SpeechAudtest.getText().toString().trim());
        healthCareData.setEarDischarge(binding.EardischargeDD.getText().toString().trim());
        healthCareData.setEarDischarge(binding.EardischargeDD.getText().toString().trim());
        healthCareData.setLingSound(binding.Ling6Sounds.getText().toString().trim());
        healthCareData.setHearingModelLeft(binding.modelLeftDD.getText().toString().trim());
        healthCareData.setHearingModelRight(binding.modelRightDD.getText().toString().trim());
        healthCareData.setHearingModelRight(binding.modelRightDD.getText().toString().trim());
        healthCareData.setEarMouldRight(binding.EarRightDD.getText().toString().trim());
        healthCareData.setEarMouldLeft(binding.EarLeftDD.getText().toString().trim());
        healthCareData.setSpecialistsRemark(binding.specialistremarks.getText().toString().trim());
        healthCareData.setFlag("update");

        localRepo.updateHealthCareData(healthCareData);

        Toast.makeText(this, "Health updated in locally.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EditHealthActivity.this, BeneficaryDetailActivity.class);
        startActivity(intent);

    }


    public static String getRandomNumber() {
        int max = 999;
        int min = 100;
        String result = "";
        Random random = new Random();
        result = String.valueOf(random.nextInt((max - min) + 1) + min);

        return result;
    }

    private String addNumbers1() {
        int number1;
        int number2;
        if(binding.totalcost.getText().toString() != "" && binding.totalcost.length() > 0) {
            number1 = Integer.parseInt(binding.totalcost.getText().toString());
        } else {
            number1 = 0;
        }
        if(binding.patientcontribution.getText().toString() != "" && binding.patientcontribution.length() > 0) {
            number2 = Integer.parseInt(binding.patientcontribution.getText().toString());
        } else {
            number2 = 0;
        }

        return Integer.toString(number1 - number2);
    }

    private String addNumbers2() {
        int number1;
        int number2;
        if(binding.repaircost1.getText().toString() != "" && binding.repaircost1.getText().length() > 0) {
            number1 = Integer.parseInt(binding.repaircost1.getText().toString());
        } else {
            number1 = 0;
        }
        if(binding.rpatientcontribution1.getText().toString() != "" && binding.rpatientcontribution1.getText().length() > 0) {
            number2 = Integer.parseInt(binding.rpatientcontribution1.getText().toString());
        } else {
            number2 = 0;
        }

        return Integer.toString(number1 - number2);
    }

    private String addNumbers3() {
        int number1;
        int number2;
        if(binding.repaircost.getText().toString() != "" && binding.repaircost.getText().length() > 0) {
            number1 = Integer.parseInt(binding.repaircost.getText().toString());
        } else {
            number1 = 0;
        }
        if(binding.rpatientcontribution.getText().toString() != "" && binding.rpatientcontribution.getText().length() > 0) {
            number2 = Integer.parseInt(binding.rpatientcontribution.getText().toString());
        } else {
            number2 = 0;
        }

        return Integer.toString(number1 - number2);
    }

}