package com.example.mobilityindia.health.healthview;

import android.app.DatePickerDialog;
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
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityHealthBinding;
import com.example.mobilityindia.sync.model.HealthCareData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HealthActivity extends AppCompatActivity {
    ActivityHealthBinding binding;
    DatePickerDialog datePicker;
    LocalRepo localRepo;
    List<String> idarray1 = new ArrayList<>();
    List<String> idarray2 = new ArrayList<>();

    String benid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_health);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        binding.nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthActivity.this,EditHealthActivity.class));
            }
        });
        localRepo = new LocalRepo(HealthActivity.this);
        callofflinedata();

        benid = String.valueOf(CommonClass.benfeciary_ID);

        if(benid.equals("") || benid.equals("null")){

            callofflinedata();

        }else{

            callofflinedata1();
        }
    }

    private void callofflinedata1()
    {
        localRepo.getSelectedHealthWithData(CommonClass.datestring,CommonClass.benfeciary_ID).observe(this, new Observer<List<HealthCareData>>() {
            @Override
            public void onChanged(@Nullable List<HealthCareData> singleMember) {
                if(singleMember.size() > 0)
                {
                    binding.startdateofshg.setText(singleMember.get(0).getScreeningdate());

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;

                    idarray1 = singleMember.get(0).getServiceName();
                    idarray2 = singleMember.get(0).getDeviceName();

                    StringBuffer sb = new StringBuffer();

                    if (idarray1.size() != 0) {

                        for (String s : idarray1) {

                            sb.append(s);
                            sb.append(",");
                        }

                        String services_Id = sb.toString();

                        // remove last character (,)
                        services_Id = services_Id.substring(0, services_Id.length() - 1);

                        binding.Services.setText(services_Id);
                    }

                    StringBuffer sb1 = new StringBuffer();

                    if (idarray2.size() != 0) {

                        for (String s : idarray2) {

                            sb1.append(s);
                            sb1.append(",");
                        }

                        String services_name = sb1.toString();

                        // remove last character (,)
                        services_name = services_name.substring(0, services_name.length() - 1);

                        binding.aidsAppliances.setText(services_name);

                    }

                    binding.startdateofshg.setText(singleMember.get(0).getScreeningdate());
                    binding.dateofassiment.setText(singleMember.get(0).getAssessmentdate());
                    binding.whodidassesmenta.setText(singleMember.get(0).getAssessmentwho());
                    binding.wherewasitdonea.setText(singleMember.get(0).getAssessmentwhere());
                    binding.referincal.setText(singleMember.get(0).getReferral());
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
                    //binding.aidsAppliances.setText(singleMember.get(0).getAidAppliances());
                    binding.noofappliances.setText(singleMember.get(0).getNoofAppliances());
                    binding.totalcost.setText(singleMember.get(0).getTotalCost());
                    binding.patientcontribution.setText(singleMember.get(0).getPatientContribution());
                    binding.donorcontribution.setText(singleMember.get(0).getDonorContribution());
                    binding.correctivesurgery.setText(singleMember.get(0).getSurgery());
                    binding.surgerywhere.setText(singleMember.get(0).getSurgerywhere());
                    binding.surgerywhat.setText(singleMember.get(0).getSurgerywherewhat());
                    binding.homemodification.setText(singleMember.get(0).getHomerecommend());
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
                    binding.ihp.setText(singleMember.get(0).getIhp());


                    Log.d("gcyhbghv",idarray1.toString());

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

                    boolean ans_MedicalReferral = idarray1.contains("Referral");
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


                }
            }
        });
    }

    private void callofflinedata()
    {
        localRepo.getSelectedHealthCareData((CommonClass.tempid)).observe(this, new Observer<List<HealthCareData>>() {
            @Override
            public void onChanged(@Nullable List<HealthCareData> singleMember) {
                if(singleMember.size() > 0)
                {
                    binding.startdateofshg.setText(singleMember.get(0).getScreeningdate());

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;

                    idarray1 = singleMember.get(0).getServiceName();
                    idarray2 = singleMember.get(0).getDeviceName();

                    StringBuffer sb = new StringBuffer();

                    if (idarray1.size() != 0) {

                        for (String s : idarray1) {

                            sb.append(s);
                            sb.append(",");
                        }

                        String services_Id = sb.toString();

                        // remove last character (,)
                        services_Id = services_Id.substring(0, services_Id.length() - 1);

                        binding.Services.setText(services_Id);
                    }

                    StringBuffer sb1 = new StringBuffer();

                    if (idarray2.size() != 0) {

                        for (String s : idarray2) {

                            sb1.append(s);
                            sb1.append(",");
                        }

                        String services_name = sb1.toString();

                        // remove last character (,)
                        services_name = services_name.substring(0, services_name.length() - 1);

                        binding.aidsAppliances.setText(services_name);

                    }

                    binding.startdateofshg.setText(singleMember.get(0).getScreeningdate());
                    binding.dateofassiment.setText(singleMember.get(0).getAssessmentdate());
                    binding.whodidassesmenta.setText(singleMember.get(0).getAssessmentwho());
                    binding.wherewasitdonea.setText(singleMember.get(0).getAssessmentwhere());
                    binding.referincal.setText(singleMember.get(0).getReferral());
                    binding.refertowhichplace.setText(singleMember.get(0).getReferralplace());
                    binding.presecription.setText(singleMember.get(0).getReferralprescription());
                    binding.trailforwhat.setText(singleMember.get(0).getTrialwhat());
                    binding.traildate.setText(singleMember.get(0).getTrialdate());
                    binding.gaitfrequency.setText(singleMember.get(0).getGaitfrequency());
                    binding.howanydone.setText(singleMember.get(0).getGaithowmany());
                    binding.thrpynumberoftime.setText(singleMember.get(0).getTherapyfrequency());
                    binding.numberofsession.setText(singleMember.get(0).getTherapysessions());
                    binding.fitmentwho.setText(singleMember.get(0).getTherapysessions());
                    binding.fitmentwhere.setText(singleMember.get(0).getTherapysessions());
                    binding.fitmentkind.setText(singleMember.get(0).getTherapysessions());
                   // binding.aidsAppliances.setText(singleMember.get(0).getTherapysessions());
                    binding.noofappliances.setText(singleMember.get(0).getTherapysessions());
                    binding.totalcost.setText(singleMember.get(0).getTotalCost());
                    binding.patientcontribution.setText(singleMember.get(0).getPatientContribution());
                    binding.donorcontribution.setText(singleMember.get(0).getDonorContribution());
                    binding.correctivesurgery.setText(singleMember.get(0).getSurgery());
                    binding.surgerywhere.setText(singleMember.get(0).getSurgerywhere());
                    binding.surgerywhat.setText(singleMember.get(0).getSurgerywherewhat());
                    binding.homemodification.setText(singleMember.get(0).getHomerecommend());
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
                    binding.ihp.setText(singleMember.get(0).getIhp());


                    Log.d("gcyhbghv",idarray1.toString());

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

                    boolean ans_MedicalReferral = idarray1.contains("Referral");
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

                }
            }
        });
    }


}