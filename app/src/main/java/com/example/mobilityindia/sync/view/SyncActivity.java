package com.example.mobilityindia.sync.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivitySyncBinding;
import com.example.mobilityindia.landingpage.view.LandingPageActivity;
import com.example.mobilityindia.sync.model.ActionPlanData;
import com.example.mobilityindia.sync.model.ActivityReportAttendanceData;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.model.BlockData;
import com.example.mobilityindia.sync.model.DistData;
import com.example.mobilityindia.sync.model.EducationData;
import com.example.mobilityindia.sync.model.GPData;
import com.example.mobilityindia.sync.model.HealthCareData;
import com.example.mobilityindia.sync.model.LivehoodData;
import com.example.mobilityindia.sync.model.SocialData;
import com.example.mobilityindia.sync.model.SubDisabilityData;
import com.example.mobilityindia.sync.model.VillageData;
import com.example.mobilityindia.sync.model.WorkPlanData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.sync.viewmodel.SyncViewModel;
import com.example.mobilityindia.utils.Utils;

import java.util.List;

public class SyncActivity extends AppCompatActivity {
    private ActivitySyncBinding binding;
    SyncViewModel syncviewModel;
    LocalRepo localRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sync);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        CommonClass.APP_TOKEN  = CommonClass.getToken(this);
        localRepo = new LocalRepo(SyncActivity.this);
        syncviewModel= new ViewModelProvider(this).get(SyncViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setSyncviewmodel(syncviewModel);

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SyncActivity.this, LandingPageActivity.class));
            }
        });

        //On download master data btn called
        syncviewModel.getPageNavigate().observe(this, responseStr -> {
             if(responseStr.equalsIgnoreCase("masterdata")){
                if(Utils.isNetworkAvailable(this)){
                    syncviewModel.callStateMasterData();
                } else{
                    Toast.makeText(this, "Please connect your Internet", Toast.LENGTH_SHORT).show();
                }
            }else if(responseStr.equalsIgnoreCase("beneficiarydata")){
                 if(Utils.isNetworkAvailable(this)){
                     //syncviewModel.callBeneFiciaryData();
                 } else{
                     Toast.makeText(this, "Please connect your Internet", Toast.LENGTH_SHORT).show();
                 }
             }
             else if(responseStr.equalsIgnoreCase("actionplan")){
                 if(Utils.isNetworkAvailable(this)){
                     //syncviewModel.callActionPlanData();
                 } else{
                     Toast.makeText(this, "Please connect your Internet", Toast.LENGTH_SHORT).show();
                 }
             }


        });

        //benificiary response
        syncviewModel.getBeneficiaryData().observe(this,beneResponse -> {

            if (beneResponse != null) {
               // System.out.println(beneResponse.toString());
                if (beneResponse.getStatus().equalsIgnoreCase("true")) {

                    localRepo.getBeneList().observe(this, new Observer<List<BeneData>>() {
                        @Override
                        public void onChanged(@Nullable List<BeneData> beneDataa) {
                            if(beneDataa.size() <=0) {
                                for(int i=0;i<beneResponse.benelistList.size();i++){
                                    localRepo.insertBene(beneResponse.benelistList.get(i));
                                }
                            }
                        }
                    });

                } else {
                     Toast.makeText(this, beneResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });


        //ActionPlan
        syncviewModel.getActionPlanData().observe(this,actionPlanResponse -> {

            if (actionPlanResponse != null) {
                // System.out.println(beneResponse.toString());
                if (actionPlanResponse.getStatus().equalsIgnoreCase("true")) {

                    localRepo.getActionPlanList().observe(this, new Observer<List<ActionPlanData>>() {
                        @Override
                        public void onChanged(@Nullable List<ActionPlanData> actionPlanData) {
                            if(actionPlanData.size() <=0) {
                                for(int i=0;i<actionPlanResponse.getActionplanList().size();i++){
                                    localRepo.insertActionPlan(actionPlanResponse.getActionplanList().get(i));
                                }
                                Toast.makeText(SyncActivity.this, "Insert Success", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(this, actionPlanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
                //syncviewModel.callActivityPlanData();
            }

        });


        //Activity Report
        syncviewModel.getWorkPlanData().observe(this,workPlanResponse -> {

            if (workPlanResponse != null) {
                // System.out.println(beneResponse.toString());
                if (workPlanResponse.getStatus().equalsIgnoreCase("true")) {

                    localRepo.getWorkPlanList().observe(this, new Observer<List<WorkPlanData>>() {
                        @Override
                        public void onChanged(@Nullable List<WorkPlanData> activitynData) {
                            if(activitynData.size() <=0) {
                                for(int i=0;i<workPlanResponse.getWorkplanList().size();i++){
                                    localRepo.insertWorkPlan(workPlanResponse.getWorkplanList().get(i));
                                }
                                Toast.makeText(SyncActivity.this, "Insert Activity report Success", Toast.LENGTH_SHORT).show();

                            }
                            //
                            syncviewModel.callActivityReportData();
                        }
                    });

                } else {
                    Toast.makeText(this, workPlanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

        });

       //Arctivity Report Data
        syncviewModel.getActivityReportData().observe(this,activityReportResponse -> {

            if (activityReportResponse != null) {
                // System.out.println(beneResponse.toString());
                if (activityReportResponse.getStatus().equalsIgnoreCase("true")) {

                    localRepo.getActivityRepList().observe(this, new Observer<List<ActivityReportAttendanceData>>() {
                        @Override
                        public void onChanged(@Nullable List<ActivityReportAttendanceData> activitynData) {
                            if(activitynData.size() <=0) {
                                for(int i=0;i<activityReportResponse.getActivityRepAttList().size();i++){
                                    localRepo.insertActivityRep(activityReportResponse.getActivityRepAttList().get(i));
                                }
                                Toast.makeText(SyncActivity.this, "Insert Activity Report  Success", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                } else {
                    Toast.makeText(this, activityReportResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
                //syncviewModel.callServicetData();
            }

        });

        //ServiceData
        syncviewModel.getServiceData().observe(this,serviceResponse -> {

            if (serviceResponse != null) {
                // System.out.println(beneResponse.toString());
                if (serviceResponse.getStatus().equalsIgnoreCase("true")) {

                    localRepo.getSocialList().observe(this, new Observer<List<SocialData>>() {
                        @Override
                        public void onChanged(@Nullable List<SocialData> socialData) {

                                 if(socialData.size() <= 0) {
                                    for(int i=0;i<serviceResponse.getSocialdata().size();i++){
                                        localRepo.insertSocialData(serviceResponse.getSocialdata().get(i));
                                    }
                                    Toast.makeText(SyncActivity.this, "Insert Social Data  Success", Toast.LENGTH_SHORT).show();
                                }

                        }
                    });

                    //---LiveHood
                    localRepo.getLiveHoodList().observe(this, new Observer<List<LivehoodData>>() {
                        @Override
                        public void onChanged(@Nullable List<LivehoodData> livehoodData) {
                            if(livehoodData.size() <=0)
                            {
                                for(int i=0;i<serviceResponse.getLivelihooddata().size();i++){
                                    localRepo.insertLivehoodData(serviceResponse.getLivelihooddata().get(i));
                                }
                                Toast.makeText(SyncActivity.this, "Insert Livehood  Success", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    //---HealthCare
                    localRepo.getHealthCarenList().observe(this, new Observer<List<HealthCareData>>() {
                        @Override
                        public void onChanged(@Nullable List<HealthCareData> healthCareData) {
                            if(healthCareData.size() <=0) {
                                for(int i=0;i<serviceResponse.getHealthdata().size();i++){
                                    //localRepo.insertHealthCareData(serviceResponse.getHealthdata().get(i));
                                }
                                Toast.makeText(SyncActivity.this, "Insert HealthCare  Success", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    //---Education
                    localRepo.getEducationList().observe(this, new Observer<List<EducationData>>() {
                        @Override
                        public void onChanged(@Nullable List<EducationData> educationData) {
                            if(educationData.size() <=0) {
                                for(int i=0;i<serviceResponse.getEducationdata().size();i++){
                                    localRepo.insertEducationData(serviceResponse.getEducationdata().get(i));
                                }
                                Toast.makeText(SyncActivity.this, "Insert EducationData  Success", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(this, serviceResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        //-- State Master response
        syncviewModel.getStateMasterData().observe(this,  jsonResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 1 out of 16");
            getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("statemaster",jsonResponse.toString() ).commit();
            //syncviewModel.callDisticMasterData();
        });

        //-- Distic Master response
        syncviewModel.getDistMasterData().observe(this,  distResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 2 out of 16");
            //getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("disticmaster",jsonResponse.toString() ).commit();

            if(distResponse!=null){
                localRepo.getDistList().observe(this, new Observer<List<DistData>>() {
                    @Override
                    public void onChanged(@Nullable List<DistData> distDataa) {
                        if(distDataa.size() <=0) {
                            if(distResponse.getDistList().size()>0){
                                for (int i = 0;i<distResponse.getDistList().size();i++){
                                    localRepo.insertDist(distResponse.getDistList().get(i));
                                }
                            }
                            System.out.println("Dist local inserted");
                        }
                    }
                });
            }
            //syncviewModel.callBlockMasterData();
        });


        //-- Block Master response
        syncviewModel.getBlockMasterData().observe(this,  blockResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 3 out of 16");
            //getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("blockmaster",jsonResponse.toString() ).commit();

            if(blockResponse!=null){
                localRepo.getBlockList().observe(this, new Observer<List<BlockData>>() {
                    @Override
                    public void onChanged(@Nullable List<BlockData> blockDataa) {
                        if(blockDataa.size() <=0) {
                            if(blockResponse.getBlockList().size()>0){
                                for (int i = 0;i<blockResponse.getBlockList().size();i++){
                                    localRepo.insertBlock(blockResponse.getBlockList().get(i));
                                }
                            }
                            System.out.println("block local inserted");
                        }
                    }
                });
            }

            //syncviewModel.callGPMasterData();
        });

        //-- GP Master response
        syncviewModel.getGPMasterData().observe(this,  gpResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 4 out of 16");
            //getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("gpmaster",jsonResponse.toString() ).commit();

            if(gpResponse!=null){

                localRepo.getGpList().observe(this, new Observer<List<GPData>>() {
                    @Override
                    public void onChanged(@Nullable List<GPData> vastiDataa) {
                        if(vastiDataa.size() <=0) {
                            if(gpResponse.getGpList().size()>0){
                                for (int i = 0;i<gpResponse.getGpList().size();i++){
                                    localRepo.insertGP(gpResponse.getGpList().get(i));
                                }
                            }
                            System.out.println("GP local inserted");
                        }
                    }
                });
            }

            //syncviewModel.callVillageMasterData();
        });

        //-- Village Master response
        syncviewModel.getVillageMasterData().observe(this,  villageResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 5 out of 16");
            //getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("villagemaster",jsonResponse.toString() ).commit();
            if(villageResponse!=null){
                localRepo.getVillageList().observe(this, new Observer<List<VillageData>>() {
                    @Override
                    public void onChanged(@Nullable List<VillageData> villageDataa) {
                        if(villageDataa.size() <=0) {
                            if(villageResponse.getVillageList().size()>0){
                                for (int i = 0;i<villageResponse.getVillageList().size();i++){
                                    localRepo.insertVillage(villageResponse.getVillageList().get(i));
                                }
                            }
                            System.out.println("Village local inserted");
                        }
                    }
                });
            }

            syncviewModel.callCasteMasterData();
        });

        //-- Caste Master response
        syncviewModel.getCasteMasterData().observe(this,  jsonResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 6 out of 16");
            getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("castemaster",jsonResponse.toString() ).commit();
            syncviewModel.callReligionMasterData();
        });

        //-- Religion Master response
        syncviewModel.getReligionMasterData().observe(this,  jsonResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 7 out of 16");
            getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("religionmaster",jsonResponse.toString() ).commit();
            syncviewModel.callEconomicMasterData();
        });

        //-- Economic Master response
        syncviewModel.getEconomicMasterData().observe(this,  jsonResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 8 out of 16");
            getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("economicmaster",jsonResponse.toString() ).commit();
            syncviewModel.callAnnualIncomeMasterData();
        });

        //-- Annual income Master response
        syncviewModel.getAnualIncomeMasterData().observe(this,  jsonResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 9 out of 16");
            getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("annualincomemaster",jsonResponse.toString() ).commit();
            syncviewModel.callMaritialStatusMasterData();
        });

        //-- MaritialStatus Master response
        syncviewModel.getmeritalStatusMasterData().observe(this,  jsonResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 10 out of 16");
            getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("maritialStatusmaster",jsonResponse.toString() ).commit();
            syncviewModel.callEducationMasterData();
        });

        //-- Education Master response
        syncviewModel.getEducationMasterData().observe(this,  jsonResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 11 out of 16");
            getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("educationmaster",jsonResponse.toString() ).commit();
            syncviewModel.callOcupationMasterData();
        });


        //-- Occupation Master response
        syncviewModel.getOcupationMasterData().observe(this,  jsonResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 12 out of 16");
            getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("occupationmaster",jsonResponse.toString() ).commit();
            syncviewModel.callTypeDisabilityMasterData();
        });

        //-- Typedisability Master response
        syncviewModel.gettypeDisMasterData().observe(this,  jsonResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 13 out of 16");
            getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("typedismaster",jsonResponse.toString() ).commit();
            syncviewModel.callSubDisabilityMasterData();
        });

        //-- Subdisability Master response
        syncviewModel.getSubDsiabilityMasterData().observe(this,  subDisabilityResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 14 out of 16");
            if(subDisabilityResponse!=null){
                localRepo.getSubDisList().observe(this, new Observer<List<SubDisabilityData>>() {
                    @Override
                    public void onChanged(@Nullable List<SubDisabilityData> subdisDataa) {
                        if(subdisDataa.size() <=0) {
                            if(subDisabilityResponse.getSubDisabilityList().size()>0){
                                for (int i = 0;i<subDisabilityResponse.getSubDisabilityList().size();i++){
                                    localRepo.insertSubDisability(subDisabilityResponse.getSubDisabilityList().get(i));
                                }
                            }
                        }
                    }
                });
            }
           // getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("subdisabilemaster",jsonResponse.toString() ).commit();
            syncviewModel.callPurposeVisitDisabilityMasterData();
        });

        //-- Subdisability Master response
        syncviewModel.getProposeVisitMasterData().observe(this,  jsonResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 15 out of 16");
            getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("purposevisitmaster",jsonResponse.toString() ).commit();
            syncviewModel.callHoboliMasterData();
        });

        //-- Hoboli Master response
        syncviewModel.getHoboliMasterData().observe(this,  jsonResponse ->  {
            binding.loadingDataTxt.setVisibility(View.VISIBLE);
            binding.loadingDataTxt.setText("Download master data 16 out of 16");
            getSharedPreferences("masterData", MODE_PRIVATE).edit().putString("hobolimaster",jsonResponse.toString() ).commit();
            //syncviewModel.getProposeVisitMasterData();
        });
    }
}