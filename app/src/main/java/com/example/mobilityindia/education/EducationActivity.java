package com.example.mobilityindia.education;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEducationBinding;
import com.example.mobilityindia.sync.model.EducationData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.ArrayList;
import java.util.List;

public class EducationActivity extends AppCompatActivity {
    ActivityEducationBinding binding;
    LocalRepo localRepo;
    String classaccessible1 = "", skilldevelopment = "", sittingmodification1 = "", accseetotlm1 = "", accesstotoilet1 = "",
            accesstolibrary1 = "", schoolenroll1 = "", acesstosportactivity1 = "", childpartialament1 = "", summercamp1 = "", benid = "",
            vocationcourse = "", individualeducattionplan1 = "", memberofCEC1 = "", curricularactivities1 = "", Childrengramsabha1 = "";

    List<String> userDoc = new ArrayList<>();

    String getValue = "1",docURL1 = "",docURL2 = "",docURL3 = "",docURL4 = "",docURL5 = "";
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        binding.nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EducationActivity.this, EditEducationActivity.class));
            }
        });

        benid = String.valueOf(CommonClass.benfeciary_ID);


       // callofflinedata1();

        if(benid.equals("") || benid.equals("null")){

            callofflinedata();

        }else{

            callofflinedata1();
        }

    }


    private void callofflinedata() {
        localRepo.getducationcreatedate1((CommonClass.tempid)).observe(this, new Observer<List<EducationData>>() {
            @Override
            public void onChanged(@Nullable List<EducationData> singleMember) {
                if (singleMember.size() > 0) {

                    schoolenroll1 = singleMember.get(0).getSchool();
                    if (schoolenroll1.equalsIgnoreCase("Yes") || schoolenroll1.equalsIgnoreCase("true")) {
                        binding.schoolenroll.setText(schoolenroll1);
                        binding.schoollayoutid.setVisibility(View.VISIBLE);
                    } else {
                        binding.schoolenroll.setText(schoolenroll1);
                        binding.schoollayoutid.setVisibility(View.GONE);
                    }

                    binding.enrollmentno.setText(singleMember.get(0).getEnrollmentno());
                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;
                    binding.attendingclassregularly.setText(singleMember.get(0).getAttendingclass());


                    classaccessible1 = String.valueOf(singleMember.get(0).getSchoolaccess());
                    if (classaccessible1.equals("") || classaccessible1.equals("null")) {
                    } else {
                        binding.classaccessible.setText(classaccessible1);
                    }


                    ///////swagitikaask
//                    skilldevelopment = singleMember.get(0).getCec();
//                    if(skilldevelopment.equalsIgnoreCase("Yes")||skilldevelopment.equalsIgnoreCase("true")){
//                        binding.skilldevelopment.setOn(true);
//                    }else{
//                        binding.skilldevelopment.setOn(false);
//                    }

                    sittingmodification1 = singleMember.get(0).getSitting();
                    binding.sittingmodification.setText(sittingmodification1);

                    accseetotlm1 = singleMember.get(0).getTlm();
                    binding.accseetotlm.setText(accseetotlm1);

                    accesstotoilet1 = String.valueOf(singleMember.get(0).getToilet());
                    if (accesstotoilet1.equals("") || accesstotoilet1.equals("null")) {
                    } else {
                        binding.accesstotoilet.setText(accesstotoilet1);
                    }

                    accesstolibrary1 = String.valueOf(singleMember.get(0).getLibrary());
                    if (accesstolibrary1.equals("") || accesstolibrary1.equals("null")) {
                    } else {
                        binding.accesstolibrary.setText(accesstolibrary1);
                    }


                    acesstosportactivity1 = String.valueOf(singleMember.get(0).getSports());
                    if (acesstosportactivity1.equals("") || acesstosportactivity1.equals("null")) {
                    } else {
                        binding.acesstosportactivity.setText(acesstosportactivity1);
                    }


                    binding.anyother.setText(singleMember.get(0).getSchoolother());

                    childpartialament1 = String.valueOf(singleMember.get(0).getParliament());
                    if (childpartialament1.equals("") || childpartialament1.equals("null")) {
                    } else {
                        binding.childpartialament.setText(childpartialament1);
                    }


                    summercamp1 = String.valueOf(singleMember.get(0).getSummercamp());
                    if (summercamp1.equals("") || summercamp1.equals("null")) {
                    } else {
                        binding.summercamp.setText(summercamp1);
                    }


                    individualeducattionplan1 = String.valueOf(singleMember.get(0).getIep());
                    if (individualeducattionplan1.equals("") || individualeducattionplan1.equals("null")) {
                    } else {
                        binding.individualeducattionplan.setText(individualeducattionplan1);
                    }


                    memberofCEC1 = String.valueOf(singleMember.get(0).getCec());
                    if (memberofCEC1.equals("") || memberofCEC1.equals("null")) {
                    } else {
                        binding.memberofCEC.setText(memberofCEC1);
                    }


                    curricularactivities1 = String.valueOf(singleMember.get(0).getCocurricular());
                    if (curricularactivities1.equals("") || curricularactivities1.equals("null")) {
                    } else {
                        binding.curricularactivities.setText(curricularactivities1);
                    }


                    Childrengramsabha1 = String.valueOf(singleMember.get(0).getGramsabha());
                    if (Childrengramsabha1.equals("") || Childrengramsabha1.equals("null")) {
                    } else {
                        binding.Childrengramsabha.setText(Childrengramsabha1);
                    }

                    individualeducattionplan1 = String.valueOf(singleMember.get(0).getIep());
                    if (individualeducattionplan1.equals("") || individualeducattionplan1.equals("null")) {
                    } else {

                    }
                    // binding.mentiondetailvocationcourse.setText(singleMember.get(0).getVocationaldetail());

                    binding.Activity1.setText(singleMember.get(0).getActivityOne());
                    binding.Activity2.setText(singleMember.get(0).getActivityTwo());
                    binding.Activity3.setText(singleMember.get(0).getActivityThree());
                    binding.Activity4.setText(singleMember.get(0).getActivityFour());
                    binding.Activity5.setText(singleMember.get(0).getActivityFive());

                    if (individualeducattionplan1.equals("Yes")) {

                        binding.ihpview.setVisibility(View.VISIBLE);
                        binding.imagesiew.setVisibility(View.VISIBLE);
                    }

                    userDoc = singleMember.get(0).getIepdoc();

                  /*  for (int i = 0; i < userDoc.size(); i++) {

                        if (getValue.equals("1")) {

                            docURL1 = userDoc.get(i);
                            getValue = "2";
                            binding.imageview1.setImageResource(R.drawable.pdfimg);

                        }else if(getValue.equals("2")){

                            docURL2 = userDoc.get(i);
                            getValue = "3";
                            binding.imageview2.setImageResource(R.drawable.pdfimg);

                        }else if(getValue.equals("3")){

                            docURL3 = userDoc.get(i);
                            getValue = "4";
                            binding.imageview3.setImageResource(R.drawable.pdfimg);

                        }else if(getValue.equals("4")){

                            docURL4 = userDoc.get(i);
                            getValue = "5";
                            binding.imageview4.setImageResource(R.drawable.pdfimg);

                        }else if(getValue.equals("5")){

                            docURL5 = userDoc.get(i);
                            binding.imageview5.setImageResource(R.drawable.pdfimg);

                        }else{

                            return;
                        }
                    }*/


                }
            }
        });
    }

    private void callofflinedata1() {
        localRepo.getSelectedEducationList((CommonClass.benfeciary_ID)).observe(this, new Observer<List<EducationData>>() {
            @Override
            public void onChanged(@Nullable List<EducationData> singleMember) {
                if (singleMember.size() > 0) {
                    schoolenroll1 = singleMember.get(0).getSchool();

                    if (schoolenroll1.equalsIgnoreCase("Yes") || schoolenroll1.equalsIgnoreCase("true")) {
                        binding.schoolenroll.setText(schoolenroll1);
                        binding.schoollayoutid.setVisibility(View.VISIBLE);
                    } else {
                        binding.schoolenroll.setText(schoolenroll1);
                        binding.schoollayoutid.setVisibility(View.GONE);
                    }

                    binding.enrollmentno.setText(singleMember.get(0).getEnrollmentno());
                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;
                    binding.attendingclassregularly.setText(singleMember.get(0).getAttendingclass());


                    classaccessible1 = String.valueOf(singleMember.get(0).getSchoolaccess());
                    if (classaccessible1.equals("") || classaccessible1.equals("null")) {
                    } else {
                        binding.classaccessible.setText(classaccessible1);
                    }


                    ///////swagitikaask
//                    skilldevelopment = singleMember.get(0).getCec();
//                    if(skilldevelopment.equalsIgnoreCase("Yes")||skilldevelopment.equalsIgnoreCase("true")){
//                        binding.skilldevelopment.setOn(true);
//                    }else{
//                        binding.skilldevelopment.setOn(false);
//                    }

                    sittingmodification1 = singleMember.get(0).getSitting();
                    binding.sittingmodification.setText(sittingmodification1);

                    accseetotlm1 = singleMember.get(0).getTlm();
                    binding.accseetotlm.setText(accseetotlm1);

                    accesstotoilet1 = String.valueOf(singleMember.get(0).getToilet());
                    if (accesstotoilet1.equals("") || accesstotoilet1.equals("null")) {
                    } else {
                        binding.accesstotoilet.setText(accesstotoilet1);
                    }

                    accesstolibrary1 = String.valueOf(singleMember.get(0).getLibrary());
                    if (accesstolibrary1.equals("") || accesstolibrary1.equals("null")) {
                    } else {
                        binding.accesstolibrary.setText(accesstolibrary1);
                    }


                    acesstosportactivity1 = String.valueOf(singleMember.get(0).getSports());
                    if (acesstosportactivity1.equals("") || acesstosportactivity1.equals("null")) {
                    } else {
                        binding.acesstosportactivity.setText(acesstosportactivity1);
                    }


                    binding.anyother.setText(singleMember.get(0).getSchoolother());

                    childpartialament1 = String.valueOf(singleMember.get(0).getParliament());
                    if (childpartialament1.equals("") || childpartialament1.equals("null")) {
                    } else {
                        binding.childpartialament.setText(childpartialament1);
                    }


                    summercamp1 = String.valueOf(singleMember.get(0).getSummercamp());
                    if (summercamp1.equals("") || summercamp1.equals("null")) {
                    } else {
                        binding.summercamp.setText(summercamp1);
                    }


                   /* individualeducattionplan1 = String.valueOf(singleMember.get(0).getIep());
                    if(individualeducattionplan1.equals("") || individualeducattionplan1.equals("null")){
                    }else{
                        binding.individualeducattionplan.setOn(individualeducattionplan1.equalsIgnoreCase("Yes") || individualeducattionplan1.equalsIgnoreCase("true"));
                    }*/


                    memberofCEC1 = String.valueOf(singleMember.get(0).getCec());
                    if (memberofCEC1.equals("") || memberofCEC1.equals("null")) {
                    } else {
                        binding.memberofCEC.setText(memberofCEC1);
                    }


                    curricularactivities1 = String.valueOf(singleMember.get(0).getCocurricular());
                    if (curricularactivities1.equals("") || curricularactivities1.equals("null")) {
                    } else {
                        binding.curricularactivities.setText(curricularactivities1);
                    }


                    Childrengramsabha1 = String.valueOf(singleMember.get(0).getGramsabha());
                    if (Childrengramsabha1.equals("") || Childrengramsabha1.equals("null")) {
                    } else {
                        binding.Childrengramsabha.setText(Childrengramsabha1);
                    }
/*

                    String[] itemNamesbank = getResources().getStringArray(R.array.selectone);
                    ArrayAdapter<String> bankaccountadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNamesbank);
                    bankaccountadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.individualeducattionplan.setAdapter(bankaccountadapter);
*/

                    individualeducattionplan1 = String.valueOf(singleMember.get(0).getIep());
                    if (individualeducattionplan1.equals("") || individualeducattionplan1.equals("null")) {
                    } else {
                        binding.individualeducattionplan.setText(individualeducattionplan1);
                    }
                    // binding.mentiondetailvocationcourse.setText(singleMember.get(0).getVocationaldetail());

                    binding.Activity1.setText(singleMember.get(0).getActivityOne());
                    binding.Activity2.setText(singleMember.get(0).getActivityTwo());
                    binding.Activity3.setText(singleMember.get(0).getActivityThree());
                    binding.Activity4.setText(singleMember.get(0).getActivityFour());
                    binding.Activity5.setText(singleMember.get(0).getActivityFive());

                    if (individualeducattionplan1.equals("Yes")) {

                        binding.ihpview.setVisibility(View.VISIBLE);
                        binding.imagesiew.setVisibility(View.VISIBLE);
                    }

                    userDoc = singleMember.get(0).getIepdoc();

                    /*for (int i = 0; i < userDoc.size(); i++) {

                        if (getValue.equals("1")) {

                            docURL1 = userDoc.get(i);
                            getValue = "2";
                            binding.imageview1.setImageResource(R.drawable.pdfimg);

                        }else if(getValue.equals("2")){

                            docURL2 = userDoc.get(i);
                            getValue = "3";
                            binding.imageview2.setImageResource(R.drawable.pdfimg);

                        }else if(getValue.equals("3")){

                            docURL3 = userDoc.get(i);
                            getValue = "4";
                            binding.imageview3.setImageResource(R.drawable.pdfimg);

                        }else if(getValue.equals("4")){

                            docURL4 = userDoc.get(i);
                            getValue = "5";
                            binding.imageview4.setImageResource(R.drawable.pdfimg);

                        }else if(getValue.equals("5")){

                            docURL5 = userDoc.get(i);
                            binding.imageview5.setImageResource(R.drawable.pdfimg);

                        }else{

                            return;
                        }
                    }*/
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(benid.equals("") || benid.equals("null")){

            callofflinedata1();

        }else{

            callofflinedata();
        }
    }
}