package com.example.mobilityindia.sync.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.mobilityindia.attendance.database.AttendanceClass;
import com.example.mobilityindia.local.AppDatabase;
import com.example.mobilityindia.sync.model.ActionPlanData;
import com.example.mobilityindia.sync.model.ActionPlanMonth;
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

import java.util.List;


public class LocalRepo {

    private final String DB_NAME = "db_mobilityindia";

    private final AppDatabase appDatabase;

    public LocalRepo(Context context) {
        // appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();

        appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }


    //Distic

    public void insertDist(DistData distData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.distDao().insertDist(distData);
                return null;
            }
        }.execute();
    }

    public void updateDist(final DistData distData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.distDao().updateDist(distData);
                return null;
            }
        }.execute();
    }

    public LiveData<List<DistData>> getDistList() {
        return appDatabase.distDao().getAllDistList();
    }

    public LiveData<List<DistData>> getDistWithStateId(int id) {
        return appDatabase.distDao().getDistRespectedToState(id);
    }

    public LiveData<List<DistData>> getDistDetaild(int id) {
        return appDatabase.distDao().getDistDetalis(id);
    }

    public void deleteDist() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.distDao().deleteDist();
                return null;
            }
        }.execute();
    }

    //-----------------Block
    public void insertBlock(BlockData blockData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.blockDao().insertBlock(blockData);
                return null;
            }
        }.execute();
    }

    public void deleteBlock() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.blockDao().deleteBlock();
                return null;
            }
        }.execute();
    }

    public void updateBlock(final BlockData blockData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.blockDao().updateBlock(blockData);
                return null;
            }
        }.execute();
    }

    public LiveData<List<BlockData>> getBlockList() {
        return appDatabase.blockDao().getAllBlockList();
    }

    public LiveData<List<BlockData>> getBlockWithDistId(int id) {
        return appDatabase.blockDao().getBlockRespectedToDist(id);
    }

    public LiveData<List<BlockData>> getBlockDetailData(int id) {
        return appDatabase.blockDao().getBlockDetail(id);
    }

    //Attendance=================

    public void insertAttandance(AttendanceClass actionPlanData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.attendanceDao().insertAttandance(actionPlanData);
                return null;
            }
        }.execute();
    }

    public void updateAttendance(String time, Double latitude, Double longitude, String address_txt, String date) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.attendanceDao().updateAttendance(time, latitude, longitude, address_txt, date);
                return null;
            }
        }.execute();
    }

    public void deleteAttendance(AttendanceClass actionPlanData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.attendanceDao().deleteAttendance(actionPlanData);
                return null;
            }
        }.execute();
    }

    public void deleteAllAttendance() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.attendanceDao().deleteAllAttendance();
                return null;
            }
        }.execute();
    }

    public LiveData<List<AttendanceClass>> getAttendance() {
        return appDatabase.attendanceDao().getAll();
    }

    public LiveData<List<AttendanceClass>> getSelectedAttendanceList(String userid) {
        return appDatabase.attendanceDao().getSelectedAttendanceList(userid);
    }


    //------------------GP
    public void insertGP(GPData gpData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.gpDao().insertGP(gpData);
                return null;
            }
        }.execute();
    }

    public void deleteGP() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.gpDao().deleteGP();
                return null;
            }
        }.execute();
    }

    public void updateGP(final GPData gpData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.gpDao().updateGP(gpData);
                return null;
            }
        }.execute();
    }

    public LiveData<List<GPData>> getGpList() {
        return appDatabase.gpDao().getAllGPList();
    }

    public LiveData<List<GPData>> getGPWithBlockId(int id) {
        return appDatabase.gpDao().getGPRespectedToBlock(id);
    }
    public LiveData<List<GPData>> getGpListDetails(int id) {
        return appDatabase.gpDao().getGPListDetails(id);
    }

    //-----Village

    //------------------GP
    public void insertVillage(VillageData villageData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.villageDao().insertVillage(villageData);
                return null;
            }
        }.execute();
    }

    public void deleteVillage() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.villageDao().deleteVillage();
                return null;
            }
        }.execute();
    }

    public void updateVillage(final VillageData villageData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.villageDao().updateVillage(villageData);
                return null;
            }
        }.execute();
    }

    public LiveData<List<VillageData>> getVillageList() {
        return appDatabase.villageDao().getAllVillageList();
    }

    public LiveData<List<VillageData>> getVillageWithGpId(int id) {
        return appDatabase.villageDao().getVillageRespectedToGP(id);
    }
    public LiveData<List<VillageData>> getVillageDataDetail(int id) {
        return appDatabase.villageDao().getVillageDetail(id);
    }


    //--------------BeneFiciary
    public void insertBene(BeneData beneData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.beneDao().insertBene(beneData);
                return null;
            }
        }.execute();
    }

    public void deleteBene(BeneData beneData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.beneDao().deleteBene(beneData);
                return null;
            }
        }.execute();
    }

    public void deleteBene() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.beneDao().deleteBene();
                return null;
            }
        }.execute();
    }

    public void updateBene(BeneData beneData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.beneDao().updateBene(beneData);
                return null;
            }
        }.execute();
    }

    public LiveData<List<BeneData>> getBeneList() {
        return appDatabase.beneDao().getAllBeneList();
    }

    public LiveData<List<BeneData>> getSelectedBene(int id) {
        return appDatabase.beneDao().getSelectedBeneList(id);
    }

    //--- Sub Disability
    public void insertSubDisability(SubDisabilityData subdisData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.subDisDao().insertSubDisability(subdisData);
                return null;
            }
        }.execute();
    }

    public void updateSubDisability(SubDisabilityData subDisData){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.subDisDao().updateSubDisability(subDisData);
                return null;
            }
        }.execute();
    }

    public LiveData<List<SubDisabilityData>> getSubDisList() {
        return appDatabase.subDisDao().getAllSubDisabilityList();
    }

    public LiveData<List<SubDisabilityData>> getSelectedSubDisability(int id) {
        return appDatabase.subDisDao().getSubDisabilityWithID(id);
    }

    //-----------ActionPlan

    public void insertActionPlan(ActionPlanData actionPlanData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.actionPlanDao().insertActionPlan(actionPlanData);
                return null;
            }
        }.execute();
    }

    public void updateActionPlan(ActionPlanData actionPlanData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.actionPlanDao().updateActionPlan(actionPlanData);
                return null;
            }
        }.execute();
    }

    public void deleteActionPlan(ActionPlanData actionPlanData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.actionPlanDao().deleteActionPlan(actionPlanData);
                return null;
            }
        }.execute();
    }

    public void deleteBene1() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.actionPlanDao().deleteBene();
                return null;
            }
        }.execute();
    }

    public LiveData<List<ActionPlanData>> getActionPlanList() {
        return appDatabase.actionPlanDao().getAllActionPlanList();
    }

    public LiveData<List<ActionPlanData>> getFilterActionPlanList() {
        return appDatabase.actionPlanDao().getFilterActionPlanList();
    }

    public LiveData<List<ActionPlanData>> getSelectedActionPlan(String date) {
        return appDatabase.actionPlanDao().getSelectedActionPlanList(date);
    }

    //........................ActionPlanMonth

    public void insertActionPlanMonth(ActionPlanMonth actionPlanData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.actionPlanMonthDao().insertActionPlanMonth(actionPlanData);
                return null;
            }
        }.execute();
    }

    public void updateActionPlanMonth(ActionPlanMonth actionPlanData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.actionPlanMonthDao().updateActionPlanMonth(actionPlanData);
                return null;
            }
        }.execute();
    }

    public void deleteActionPlanMonth(ActionPlanMonth actionPlanData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.actionPlanMonthDao().deleteActionPlanMonth(actionPlanData);
                return null;
            }
        }.execute();
    }

    public void deleteBeneMonth() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.actionPlanMonthDao().deleteBeneMonth();
                return null;
            }
        }.execute();
    }

    public LiveData<List<ActionPlanMonth>> getAllActionPlanMonthList() {
        return appDatabase.actionPlanMonthDao().getAllActionPlanMonthList();
    }

    public LiveData<List<ActionPlanMonth>> getFilterActionPlanMonthList() {
        return appDatabase.actionPlanMonthDao().getFilterActionPlanMonthList();
    }

    public LiveData<List<ActionPlanMonth>> getSelectedActionPlanMonthList(String date) {
        return appDatabase.actionPlanMonthDao().getSelectedActionPlanMonthList(date);
    }

    //-----------WorkPlan

    public void insertWorkPlan(WorkPlanData workPlanData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.workPlanDao().insertWorkPlan(workPlanData);
                return null;
            }
        }.execute();
    }

    public void updateWorkPlan(WorkPlanData workPlanData){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.workPlanDao().updateWorkPlan(workPlanData);
                return null;
            }
        }.execute();
    }

    public void DeleteWorkPlan(WorkPlanData workPlanData) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.workPlanDao().deleteWorkPlan(workPlanData);
                return null;
            }
        }.execute();
    }

    public void deleteWorkplan() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.workPlanDao().deleteWorkplan();
                return null;
            }
        }.execute();
    }

    public LiveData<List<WorkPlanData>> getWorkPlanList() {
        return appDatabase.workPlanDao().getAllWorkPlanList();
    }

    public LiveData<List<WorkPlanData>> getFilterWorkPlanList() {
        return appDatabase.workPlanDao().getFilterWorkPlanList();
    }

    public LiveData<List<WorkPlanData>> getSelectedWorkPlan(String date) {
        return appDatabase.workPlanDao().getSelectedWorkPlanList(date);
    }

    //-----------ActivityyrMon

    public void insertActivityRep(ActivityReportAttendanceData data) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.activityReportDao().insertActivityRep(data);
                return null;
            }
        }.execute();
    }

    public void updateActivityRep(ActivityReportAttendanceData data) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.activityReportDao().updateActivityRep(data);
                return null;
            }
        }.execute();
    }

    public void deleteActivityRep(ActivityReportAttendanceData data) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.activityReportDao().deleteActivityRep(data);
                return null;
            }
        }.execute();
    }

    public void deleteBeneatten() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.activityReportDao().deleteBene();
                return null;
            }
        }.execute();
    }

    public LiveData<List<ActivityReportAttendanceData>> getActivityRepList() {
        return appDatabase.activityReportDao().getAllActivityReportList();
    }
/*
    public LiveData<List<WorkPlanData>> getFilterWorkPlanList() {
        return appDatabase.workPlanDao().getFilterWorkPlanList();
    }*/

    public LiveData<List<ActivityReportAttendanceData>> getSelectedActivityYrMon(String str) {
        return appDatabase.activityReportDao().getSelectedActivityReportList(str);
    }

    public LiveData<List<ActivityReportAttendanceData>> getSelectedActivityReportListMon(String str) {
        return appDatabase.activityReportDao().getSelectedActivityReportListMon(str);
    }


    //-----------ServiceData

    public void insertLivehoodData(LivehoodData data) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.livehoodDao().insertLiveHood(data);
                return null;
            }
        }.execute();
    }

    public void insertSocialData(SocialData data) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.socialDao().insertSocial(data);
                return null;
            }
        }.execute();
    }

    public void insertHealthCareData(HealthCareData data) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.healthcareDao().insertHealthCare(data);
                return null;
            }
        }.execute();
    }
    public void insertEducationData(EducationData data) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.educationDao().insertEducation(data);
                return null;
            }
        }.execute();
    }

    public void updateSocialData(SocialData data){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.socialDao().updateSocial(data);
                return null;
            }
        }.execute();
    }

    public void updateLivehoodData(LivehoodData data){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.livehoodDao().updateLivehood(data);
                return null;
            }
        }.execute();
    }

    public void updateHealthCareData(HealthCareData data){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.healthcareDao().updateHealthCare(data);
                return null;
            }
        }.execute();
    }

    public void updateEducationData(EducationData data){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.educationDao().updateEducation(data);
                return null;
            }
        }.execute();
    }

    public LiveData<List<SocialData>> getSocialList() {
        return appDatabase.socialDao().getAllSocialList();
    }

    public LiveData<List<LivehoodData>> getLiveHoodList() {
        return appDatabase.livehoodDao().getAllLiveHoodList();
    }

    public LiveData<List<EducationData>> getEducationList() {
        return appDatabase.educationDao().getAllEducationList();
    }
    public LiveData<List<HealthCareData>> getHealthCarenList() {
        return appDatabase.healthcareDao().getHealthCareList();
    }

    public LiveData<List<HealthCareData>> getHealthCarenListDate(String dateStr) {
        return appDatabase.healthcareDao().getSelectedHealthCareWithDate(dateStr);
    }

    public LiveData<List<String>> getHealthCareDateList(String id) {
        return appDatabase.healthcareDao().getSelectedDate(id);
    }

    /////////////////////////////////

    public LiveData<List<String>> getEducationcreatedList(String dateStr) {
        return appDatabase.educationDao().getSelectedCreatd(dateStr);
    }

    public LiveData<List<EducationData>> getducationcreatedate(String dateStr) {
        return appDatabase.educationDao().getSelectedEducationWithDate(dateStr);
    }


    ///////////////////SocialData//////////////
    public LiveData<List<SocialData>> getSelectedsocialData(String str) {
        return appDatabase.socialDao().getSelectedSociallist(str);
    }


    public LiveData<List<String>> getSocialCreatedList(String dateStr) {
        return appDatabase.socialDao().getSelectedCreatd(dateStr);
    }

    public LiveData<List<SocialData>> getSocialionCreatedate(String dateStr) {
        return appDatabase.socialDao().getSelectedEducationWithDate(dateStr);
    }

///////////////////////////livelihood data/////////////////

    public LiveData<List<String>> getSelectedlivehoodData(String id) {
        return appDatabase.livehoodDao().getSelectedLiveHood(id);
    }

    public LiveData<List<LivehoodData>> getSelectedLivelihoodDate(String str) {
        return appDatabase.livehoodDao().getSelectedLivelihoodWithDate(str);
    }

    public LiveData<List<HealthCareData>> getSelectedHealthCareData(String str) {
        return appDatabase.healthcareDao().getSelectedHealthCare(str);
    }

    public void deleteHealthCareData(String str) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.healthcareDao().deleteHealthCare(str);
                return null;
            }
        }.execute();
    }
}
