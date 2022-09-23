package com.example.mobilityindia.education;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mobilityindia.R;
import com.example.mobilityindia.beneficarylist.view.BeneficaryDetailActivity;
import com.example.mobilityindia.beneficarylist.view.BeneficaryListActivity;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEditEducationBinding;
import com.example.mobilityindia.sync.model.EducationData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.utils.ImageFilePath;
import com.example.mobilityindia.utils.Utils;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.constant.ImageProvider;
import com.github.dhaval2404.imagepicker.listener.DismissListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class EditEducationActivity extends AppCompatActivity {
    private static final int PROFILE_IMAGE_REQ_CODE = 101;
    ActivityEditEducationBinding binding;
    LocalRepo localRepo;
    String classaccessible = "" ,skilldevelopment = "",sittingmodification = "",accseetotlm ="",accesstotoilet ="",benid = "",
            accesstolibrary ="",schoolenroll = "",acesstosportactivity ="",childpartialament ="",summercamp ="",vocationcourse ="",individualeducattionplan ="",
            memberofCEC = "",curricularactivities = "",Childrengramsabha = "",createdat = "",benificiaryId = "",userId = "",id = "";
    EducationData educationData;
    String FinalString;
    ArrayList<String> imagelist;
    String getvalue = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_education);
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
        localRepo = new LocalRepo(EditEducationActivity.this);
        educationData = new EducationData();
        benid = String.valueOf(CommonClass.benfeciary_ID);
        if(benid.equals("") || benid.equals("null")){

            callofflinedata1();

        }else{

            callofflinedata();
        }
        String date = CommonClass.datestring;
        Log.d("hsvxva",date);


        binding.schoolenroll.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    schoolenroll = "Yes";
                    binding.schoollayoutid.setVisibility(View.VISIBLE);
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    schoolenroll = "No";
                    binding.schoollayoutid.setVisibility(View.GONE);
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.attendingclassregularly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.attendingclassregularly).showDropDown();
            }
        });
        binding.attendingclassregularly.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.attendingclassregularly.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames = getResources().getStringArray(R.array.selectonehome);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.attendingclassregularly.setAdapter(adapter2);

        binding.classaccessible.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    classaccessible = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on

                       /* binding.Sittingmodification.setVisibility(View.VISIBLE);
                        binding.AccesstoTLM.setVisibility(View.VISIBLE);
                        binding.AccesstoToilet.setVisibility(View.VISIBLE);
                        binding.AccesstoLibrary.setVisibility(View.VISIBLE);
                        binding.AccesstoSportActivity.setVisibility(View.VISIBLE);
                        binding.cocurricularactivities.setVisibility(View.VISIBLE);
                        binding.childmemberCEC.setVisibility(View.VISIBLE);*/

                } else {
                    classaccessible = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off

                        /*binding.Sittingmodification.setVisibility(View.GONE);
                        binding.AccesstoTLM.setVisibility(View.GONE);
                        binding.AccesstoToilet.setVisibility(View.GONE);
                        binding.AccesstoLibrary.setVisibility(View.GONE);
                        binding.AccesstoSportActivity.setVisibility(View.GONE);
                        binding.cocurricularactivities.setVisibility(View.GONE);
                        binding.childmemberCEC.setVisibility(View.GONE);*/
                }
            }
        });

        binding.curricularactivities.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    curricularactivities = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    curricularactivities = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.Childrengramsabha.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    skilldevelopment = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    skilldevelopment = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.memberofCEC.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    memberofCEC = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    memberofCEC = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        String[] itemName_sitting = getResources().getStringArray(R.array.selectone);
        ArrayAdapter<String> adapter_sitting = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemName_sitting);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.sittingmodification.setAdapter(adapter_sitting);

        binding.sittingmodification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.sittingmodification).showDropDown();
            }
        });
        binding.sittingmodification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sittingmodification = binding.sittingmodification.getAdapter().getItem(position).toString();
            }
        });

        String[] itemName_AccesstoTLM = getResources().getStringArray(R.array.AccesstoTLM);
        ArrayAdapter<String> adapter_AccesstoTLM = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemName_AccesstoTLM);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.accseetotlm.setAdapter(adapter_AccesstoTLM);

        binding.accseetotlm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.accseetotlm).showDropDown();
            }
        });
        binding.accseetotlm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                accseetotlm = binding.accseetotlm.getAdapter().getItem(position).toString();
            }
        });
        binding.accesstotoilet.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    accesstotoilet = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    accesstotoilet = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.accesstolibrary.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    accesstolibrary = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    accesstolibrary = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.acesstosportactivity.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    acesstosportactivity = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    acesstosportactivity = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.childpartialament.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    childpartialament = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    childpartialament = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.summercamp.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    summercamp = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    summercamp = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        String[] itemNamesbank = getResources().getStringArray(R.array.selectone);
        ArrayAdapter<String> bankaccountadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNamesbank);
        bankaccountadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.individualeducattionplan.setAdapter(bankaccountadapter);

        binding.individualeducattionplan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                individualeducattionplan = binding.individualeducattionplan.getAdapter().getItem(position).toString();

                if (individualeducattionplan.equals("Yes")) {

                    binding.ihpview.setVisibility(View.VISIBLE);
                    binding.imagesiew.setVisibility(View.VISIBLE);


                } else if(individualeducattionplan.equals("NA")) {

                    binding.ihpview.setVisibility(View.GONE);
                    binding.imagesiew.setVisibility(View.GONE);

                }else{

                    binding.ihpview.setVisibility(View.GONE);
                    binding.imagesiew.setVisibility(View.GONE);
                }
            }
        });

        binding.individualeducattionplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                (binding.individualeducattionplan).showDropDown();
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(benid.equals("") || benid.equals("null")){

                    updatedata1();

                }else{

                    updatedata();
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
                capturePhoto();

            }
        });
    }

    public void callupdatelocaledata( )
    {
        localRepo.updateEducationData(educationData);
        onBackPressed();
    }

    private void callofflinedata()
    {
        localRepo.getSelectedEducationList((CommonClass.benfeciary_ID)).observe(this, new Observer<List<EducationData>>() {
            @Override
            public void onChanged(@Nullable List<EducationData> singleMember) {
                if(singleMember.size() > 0)
                {
                    schoolenroll = singleMember.get(0).getSchool();

                    if(schoolenroll.equalsIgnoreCase("Yes")||schoolenroll.equalsIgnoreCase("true")){
                        binding.schoolenroll.setOn(true);
                        binding.schoollayoutid.setVisibility(View.VISIBLE);
                    }else{
                        binding.schoolenroll.setOn(false);
                        binding.schoollayoutid.setVisibility(View.GONE);
                    }

                    binding.enrollmentno.setText(singleMember.get(0).getEnrollmentno());
                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;



                    String attendingclassregularly = singleMember.get(0).getAttendingclass();
                    if(attendingclassregularly.equals("") || attendingclassregularly.equals("null")){
                    }else{
                        binding.attendingclassregularly.setText(attendingclassregularly);
                    }

                    String[] itemNames = getResources().getStringArray(R.array.selectonehome);
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.attendingclassregularly.setAdapter(adapter2);


                    classaccessible = String.valueOf(singleMember.get(0).getCec());
                    if(classaccessible.equals("") || classaccessible.equals("null")){
                    }else{
                        binding.classaccessible.setOn(classaccessible.equalsIgnoreCase("Yes") || classaccessible.equalsIgnoreCase("true"));
                    }


                    ///////swagitikaask
//                    skilldevelopment = singleMember.get(0).getCec();
//                    if(skilldevelopment.equalsIgnoreCase("Yes")||skilldevelopment.equalsIgnoreCase("true")){
//                        binding.skilldevelopment.setOn(true);
//                    }else{
//                        binding.skilldevelopment.setOn(false);
//                    }

                    sittingmodification = singleMember.get(0).getSitting();
                    if(sittingmodification.equals("") || sittingmodification.equals("null")){
                    }else{
                        binding.sittingmodification.setText(sittingmodification);
                    }

                    String[] itemName_sitting = getResources().getStringArray(R.array.selectone);
                    ArrayAdapter<String> adapter_sitting = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemName_sitting);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.sittingmodification.setAdapter(adapter_sitting);

                    accseetotlm = singleMember.get(0).getTlm();
                    if(accseetotlm.equals("") || accseetotlm.equals("null")){
                    }else{
                        binding.accseetotlm.setText(accseetotlm);
                    }

                    String[] itemName_AccesstoTLM = getResources().getStringArray(R.array.AccesstoTLM);
                    ArrayAdapter<String> adapter_AccesstoTLM = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemName_AccesstoTLM);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.accseetotlm.setAdapter(adapter_AccesstoTLM);

                    accesstotoilet = String.valueOf(singleMember.get(0).getToilet());
                    if(accesstotoilet.equals("") || accesstotoilet.equals("null")){
                    }else{
                        binding.accesstotoilet.setOn(accesstotoilet.equalsIgnoreCase("Yes") || accesstotoilet.equalsIgnoreCase("true"));
                    }

                    accesstolibrary = String.valueOf(singleMember.get(0).getLibrary());
                    if(accesstolibrary.equals("") || accesstolibrary.equals("null")){
                    }else{
                        binding.accesstolibrary.setOn(accesstolibrary.equalsIgnoreCase("Yes") || accesstolibrary.equalsIgnoreCase("true"));
                    }


                    acesstosportactivity = String.valueOf(singleMember.get(0).getSports());
                    if(acesstosportactivity.equals("") || acesstosportactivity.equals("null")){
                    }else{
                        binding.acesstosportactivity.setOn(acesstosportactivity.equalsIgnoreCase("Yes") || acesstosportactivity.equalsIgnoreCase("true"));
                    }


                    binding.anyother.setText(singleMember.get(0).getSchoolother());

                    childpartialament = String.valueOf(singleMember.get(0).getParliament());
                    if(childpartialament.equals("") || childpartialament.equals("null")){
                    }else{
                        binding.childpartialament.setOn(childpartialament.equalsIgnoreCase("Yes") || childpartialament.equalsIgnoreCase("true"));
                    }


                    summercamp = String.valueOf(singleMember.get(0).getSummercamp());
                    if(summercamp.equals("") || summercamp.equals("null")){
                    }else{
                        binding.summercamp.setOn(summercamp.equalsIgnoreCase("Yes") || summercamp.equalsIgnoreCase("true"));
                    }


                   /* individualeducattionplan = String.valueOf(singleMember.get(0).getIep());
                    if(individualeducattionplan.equals("") || individualeducattionplan.equals("null")){
                    }else{
                        binding.individualeducattionplan.setOn(individualeducattionplan.equalsIgnoreCase("Yes") || individualeducattionplan.equalsIgnoreCase("true"));
                    }*/


                    memberofCEC = String.valueOf(singleMember.get(0).getCec());
                    if(memberofCEC.equals("") || memberofCEC.equals("null")){
                    }else{
                        binding.memberofCEC.setOn(memberofCEC.equalsIgnoreCase("Yes") || memberofCEC.equalsIgnoreCase("true"));
                    }


                    curricularactivities = String.valueOf(singleMember.get(0).getCocurricular());
                    if(curricularactivities.equals("") || curricularactivities.equals("null")){
                    }else{
                        binding.curricularactivities.setOn(curricularactivities.equalsIgnoreCase("Yes") || curricularactivities.equalsIgnoreCase("true"));
                    }


                    skilldevelopment = String.valueOf(singleMember.get(0).getGramsabha());
                    if(skilldevelopment.equals("") || skilldevelopment.equals("null")){
                    }else{
                        binding.Childrengramsabha.setOn(skilldevelopment.equalsIgnoreCase("Yes") || skilldevelopment.equalsIgnoreCase("true"));
                    }

                    individualeducattionplan = String.valueOf(singleMember.get(0).getIep());
                    if(individualeducattionplan.equals("") || individualeducattionplan.equals("null")){
                    }else{
                        binding.individualeducattionplan.setText(individualeducattionplan);
                    }

                    String[] itemNamesbank = getResources().getStringArray(R.array.selectone);
                    ArrayAdapter<String> bankaccountadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNamesbank);
                    bankaccountadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.individualeducattionplan.setAdapter(bankaccountadapter);
                    // binding.mentiondetailvocationcourse.setText(singleMember.get(0).getVocationaldetail());

                    binding.Activity1.setText(singleMember.get(0).getActivityOne());
                    binding.Activity2.setText(singleMember.get(0).getActivityTwo());
                    binding.Activity3.setText(singleMember.get(0).getActivityThree());
                    binding.Activity4.setText(singleMember.get(0).getActivityFour());
                    binding.Activity5.setText(singleMember.get(0).getActivityFive());

                    benificiaryId = singleMember.get(0).getBenificiaryId();
                    id = singleMember.get(0).getId();
                    createdat = singleMember.get(0).getCreatedAt();

                    if(individualeducattionplan.equals("yes")){

                        binding.ihpview.setVisibility(View.VISIBLE);
                        binding.imagesiew.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void callofflinedata1()
    {
        localRepo.getducationcreatedate1((CommonClass.tempid)).observe(this, new Observer<List<EducationData>>() {
            @Override
            public void onChanged(@Nullable List<EducationData> singleMember) {
                if(singleMember.size() > 0)
                {
                    schoolenroll = singleMember.get(0).getSchool();

                    if(schoolenroll.equalsIgnoreCase("Yes")||schoolenroll.equalsIgnoreCase("true")){
                        binding.schoolenroll.setOn(true);
                        binding.schoollayoutid.setVisibility(View.VISIBLE);
                    }else{
                        binding.schoolenroll.setOn(false);
                        binding.schoollayoutid.setVisibility(View.GONE);
                    }

                    binding.enrollmentno.setText(singleMember.get(0).getEnrollmentno());
                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;



                    String attendingclassregularly = singleMember.get(0).getAttendingclass();
                    if(attendingclassregularly.equals("") || attendingclassregularly.equals("null")){
                    }else{
                        binding.attendingclassregularly.setText(attendingclassregularly);
                    }

                    String[] itemNames = getResources().getStringArray(R.array.selectonehome);
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.attendingclassregularly.setAdapter(adapter2);


                    classaccessible = String.valueOf(singleMember.get(0).getCec());
                    if(classaccessible.equals("") || classaccessible.equals("null")){
                    }else{
                        binding.classaccessible.setOn(classaccessible.equalsIgnoreCase("Yes") || classaccessible.equalsIgnoreCase("true"));
                    }


                    ///////swagitikaask
//                    skilldevelopment = singleMember.get(0).getCec();
//                    if(skilldevelopment.equalsIgnoreCase("Yes")||skilldevelopment.equalsIgnoreCase("true")){
//                        binding.skilldevelopment.setOn(true);
//                    }else{
//                        binding.skilldevelopment.setOn(false);
//                    }

                    sittingmodification = singleMember.get(0).getSitting();
                    if(sittingmodification.equals("") || sittingmodification.equals("null")){
                    }else{
                        binding.sittingmodification.setText(sittingmodification);
                    }

                    String[] itemName_sitting = getResources().getStringArray(R.array.selectone);
                    ArrayAdapter<String> adapter_sitting = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemName_sitting);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.sittingmodification.setAdapter(adapter_sitting);

                    accseetotlm = singleMember.get(0).getTlm();
                    if(accseetotlm.equals("") || accseetotlm.equals("null")){
                    }else{
                        binding.accseetotlm.setText(accseetotlm);
                    }

                    String[] itemName_AccesstoTLM = getResources().getStringArray(R.array.AccesstoTLM);
                    ArrayAdapter<String> adapter_AccesstoTLM = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemName_AccesstoTLM);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.accseetotlm.setAdapter(adapter_AccesstoTLM);

                    accesstotoilet = String.valueOf(singleMember.get(0).getToilet());
                    if(accesstotoilet.equals("") || accesstotoilet.equals("null")){
                    }else{
                        binding.accesstotoilet.setOn(accesstotoilet.equalsIgnoreCase("Yes") || accesstotoilet.equalsIgnoreCase("true"));
                    }

                    accesstolibrary = String.valueOf(singleMember.get(0).getLibrary());
                    if(accesstolibrary.equals("") || accesstolibrary.equals("null")){
                    }else{
                        binding.accesstolibrary.setOn(accesstolibrary.equalsIgnoreCase("Yes") || accesstolibrary.equalsIgnoreCase("true"));
                    }


                    acesstosportactivity = String.valueOf(singleMember.get(0).getSports());
                    if(acesstosportactivity.equals("") || acesstosportactivity.equals("null")){
                    }else{
                        binding.acesstosportactivity.setOn(acesstosportactivity.equalsIgnoreCase("Yes") || acesstosportactivity.equalsIgnoreCase("true"));
                    }


                    binding.anyother.setText(singleMember.get(0).getSchoolother());

                    childpartialament = String.valueOf(singleMember.get(0).getParliament());
                    if(childpartialament.equals("") || childpartialament.equals("null")){
                    }else{
                        binding.childpartialament.setOn(childpartialament.equalsIgnoreCase("Yes") || childpartialament.equalsIgnoreCase("true"));
                    }


                    summercamp = String.valueOf(singleMember.get(0).getSummercamp());
                    if(summercamp.equals("") || summercamp.equals("null")){
                    }else{
                        binding.summercamp.setOn(summercamp.equalsIgnoreCase("Yes") || summercamp.equalsIgnoreCase("true"));
                    }


                   /* individualeducattionplan = String.valueOf(singleMember.get(0).getIep());
                    if(individualeducattionplan.equals("") || individualeducattionplan.equals("null")){
                    }else{
                        binding.individualeducattionplan.setOn(individualeducattionplan.equalsIgnoreCase("Yes") || individualeducattionplan.equalsIgnoreCase("true"));
                    }*/


                    memberofCEC = String.valueOf(singleMember.get(0).getCec());
                    if(memberofCEC.equals("") || memberofCEC.equals("null")){
                    }else{
                        binding.memberofCEC.setOn(memberofCEC.equalsIgnoreCase("Yes") || memberofCEC.equalsIgnoreCase("true"));
                    }


                    curricularactivities = String.valueOf(singleMember.get(0).getCocurricular());
                    if(curricularactivities.equals("") || curricularactivities.equals("null")){
                    }else{
                        binding.curricularactivities.setOn(curricularactivities.equalsIgnoreCase("Yes") || curricularactivities.equalsIgnoreCase("true"));
                    }


                    skilldevelopment = String.valueOf(singleMember.get(0).getGramsabha());
                    if(skilldevelopment.equals("") || skilldevelopment.equals("null")){
                    }else{
                        binding.Childrengramsabha.setOn(skilldevelopment.equalsIgnoreCase("Yes") || skilldevelopment.equalsIgnoreCase("true"));
                    }

                    individualeducattionplan = String.valueOf(singleMember.get(0).getIep());
                    if(individualeducattionplan.equals("") || individualeducattionplan.equals("null")){
                    }else{
                        binding.individualeducattionplan.setText(individualeducattionplan);
                    }

                    String[] itemNamesbank = getResources().getStringArray(R.array.selectone);
                    ArrayAdapter<String> bankaccountadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNamesbank);
                    bankaccountadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.individualeducattionplan.setAdapter(bankaccountadapter);
                    // binding.mentiondetailvocationcourse.setText(singleMember.get(0).getVocationaldetail());

                    binding.Activity1.setText(singleMember.get(0).getActivityOne());
                    binding.Activity2.setText(singleMember.get(0).getActivityTwo());
                    binding.Activity3.setText(singleMember.get(0).getActivityThree());
                    binding.Activity4.setText(singleMember.get(0).getActivityFour());
                    binding.Activity5.setText(singleMember.get(0).getActivityFive());

                    benificiaryId = singleMember.get(0).getBenificiaryId();
                    id = singleMember.get(0).getId();
                    createdat = singleMember.get(0).getCreatedAt();

                    if(individualeducattionplan.equals("yes")){

                        binding.ihpview.setVisibility(View.VISIBLE);
                        binding.imagesiew.setVisibility(View.VISIBLE);
                    }

                }
            }
        });
    }

    private void capturePhoto() {
        ImagePicker.with(this)
                .saveDir(EditEducationActivity.this.getExternalFilesDir(Environment.DIRECTORY_DCIM))
                .cropSquare()
                .setImageProviderInterceptor(new Function1<ImageProvider, Unit>() {
                    @Override
                    public Unit invoke(ImageProvider imageProvider) {
                        Log.d("ImagePicker", "Selected ImageProvider: " + imageProvider.toString());
                        return null;
                    }
                }).setDismissListener(new DismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.d("ImagePicker", "Dialog Dismiss");
                    }
                })
                // Image resolution will be less than 512 x 512
                .maxResultSize(512, 512)
                .start(PROFILE_IMAGE_REQ_CODE);
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
                    ContentResolver contentResolver = EditEducationActivity.this.getContentResolver();
                    MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                    extension = mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(data.getData()));
                    // String extension = getfileExtension(TimeSlotSelectActivity.this, data.getData());

                    String encodedBase64 = null;
                    try {

                        binding.imagesiew.setVisibility(View.VISIBLE);

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
                        }else{

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
                    ContentResolver contentResolver = EditEducationActivity.this.getContentResolver();
                    MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                    extension = mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(data.getData()));
                    // String extension = getfileExtension(TimeSlotSelectActivity.this, data.getData());

                    String encodedBase64 = null;
                    try {

                        binding.imagesiew.setVisibility(View.VISIBLE);

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
                        }else{

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

                        binding.imagesiew.setVisibility(View.VISIBLE);

                        Uri path = data.getData();
                        String realPath = ImageFilePath.getPath(EditEducationActivity.this, data.getData());
                        //  Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(EditEducationActivity.this.getContentResolver(), path);

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

    public void updatedata(){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        String Datetime = formatter.format(c.getTime());

        educationData.setId(id);
        educationData.setBenificiaryId(benificiaryId);
        educationData.setUser_id(userId);
        educationData.setCreatedAt(Datetime);
        educationData.setCreatedBy(benificiaryId);
        //educationData.setCreatedAt(formatter.format(date));

        educationData.setSchool(schoolenroll);
        educationData.setEnrollmentno(binding.enrollmentno.getText().toString());
        educationData.setAttendingclass(binding.attendingclassregularly.getText().toString());
        educationData.setSchoolaccess(classaccessible);
        //   educationData.s(skilldevelopment);
        educationData.setSitting(sittingmodification);
        educationData.setTlm(accseetotlm);
        educationData.setToilet(accesstotoilet);
        educationData.setLibrary(accesstolibrary);
        educationData.setSports(acesstosportactivity);
        educationData.setCocurricular(curricularactivities);
        educationData.setSchoolother(binding.anyother.getText().toString());
        educationData.setCec(memberofCEC);
        // educationData.set(acesstosportactivity);
        educationData.setParliament(childpartialament);
        educationData.setGramsabha(skilldevelopment);
        educationData.setSummercamp(summercamp);
        educationData.setActivityOne(binding.Activity1.getText().toString());
        educationData.setActivityTwo(binding.Activity2.getText().toString());
        educationData.setActivityThree(binding.Activity3.getText().toString());
        educationData.setActivityFour(binding.Activity4.getText().toString());
        educationData.setActivityFive(binding.Activity5.getText().toString());
        educationData.setIep(individualeducattionplan);
        educationData.setFlag("update");
        //educationData.setIepdoc(individualeducattionplan);
        //educationData.setIepdoc(imagelist);

        localRepo.updateEducationData(educationData);


        Toast.makeText(EditEducationActivity.this, "Education updated in locally", Toast.LENGTH_SHORT).show();

        //onBackPressed();

        Intent intent = new Intent(EditEducationActivity.this, BeneficaryDetailActivity.class);
        startActivity(intent);
    }
    public void updatedata1(){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        String Datetime = formatter.format(c.getTime());

        educationData.setId(CommonClass.tempid);
        //educationData.setBenificiaryId(benificiaryId);
        educationData.setUser_id(userId);
        educationData.setCreatedAt(Datetime);
        educationData.setCreatedBy(benificiaryId);
        //educationData.setCreatedAt(formatter.format(date));

        educationData.setSchool(schoolenroll);
        educationData.setEnrollmentno(binding.enrollmentno.getText().toString());
        educationData.setAttendingclass(binding.attendingclassregularly.getText().toString());
        educationData.setSchoolaccess(classaccessible);
        //   educationData.s(skilldevelopment);
        educationData.setSitting(sittingmodification);
        educationData.setTlm(accseetotlm);
        educationData.setToilet(accesstotoilet);
        educationData.setLibrary(accesstolibrary);
        educationData.setSports(acesstosportactivity);
        educationData.setCocurricular(curricularactivities);
        educationData.setSchoolother(binding.anyother.getText().toString());
        educationData.setCec(memberofCEC);
        // educationData.set(acesstosportactivity);
        educationData.setParliament(childpartialament);
        educationData.setGramsabha(skilldevelopment);
        educationData.setSummercamp(summercamp);
        educationData.setActivityOne(binding.Activity1.getText().toString());
        educationData.setActivityTwo(binding.Activity2.getText().toString());
        educationData.setActivityThree(binding.Activity3.getText().toString());
        educationData.setActivityFour(binding.Activity4.getText().toString());
        educationData.setActivityFive(binding.Activity5.getText().toString());
        educationData.setIep(individualeducattionplan);
        educationData.setFlag("update");
        //educationData.setIepdoc(individualeducattionplan);
        //educationData.setIepdoc(imagelist);


        localRepo.updateEducationData(educationData);


        Toast.makeText(EditEducationActivity.this, "Education updated in locally", Toast.LENGTH_SHORT).show();

        //onBackPressed();

        Intent intent = new Intent(EditEducationActivity.this, BeneficaryDetailActivity.class);
        startActivity(intent);
    }
}