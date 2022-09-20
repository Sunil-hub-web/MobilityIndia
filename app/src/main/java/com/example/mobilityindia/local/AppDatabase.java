package com.example.mobilityindia.local;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mobilityindia.attendance.database.AttendanceClass;
import com.example.mobilityindia.attendance.database.AttendanceDao;
import com.example.mobilityindia.syn1.view.allresponse.Converters;
import com.example.mobilityindia.sync.db.ActionPlanDao;
import com.example.mobilityindia.sync.db.ActionPlanMonthDao;
import com.example.mobilityindia.sync.db.ActivityReportDao;
import com.example.mobilityindia.sync.db.BeneDao;
import com.example.mobilityindia.sync.db.BlockDao;
import com.example.mobilityindia.sync.db.DistDao;
import com.example.mobilityindia.sync.db.EducationDao;
import com.example.mobilityindia.sync.db.GpDao;
import com.example.mobilityindia.sync.db.HealthcareDao;
import com.example.mobilityindia.sync.db.LivehoodDao;
import com.example.mobilityindia.sync.db.SocialDao;
import com.example.mobilityindia.sync.db.SubDisDao;
import com.example.mobilityindia.sync.db.VillageDao;
import com.example.mobilityindia.sync.db.WorkPlanDao;
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

@Database(entities = {DistData.class,
        BeneData.class,
        SubDisabilityData.class,
        BlockData.class,
        GPData.class,
        VillageData.class,
        ActionPlanData.class,
        WorkPlanData.class,
        ActivityReportAttendanceData.class,
        SocialData.class,
        LivehoodData.class,
        EducationData.class,
        HealthCareData.class,
        AttendanceClass.class,
        ActionPlanMonth.class}, version = 2, exportSchema = false)

@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    //dao intialize
    public abstract DistDao distDao();
    public abstract BlockDao blockDao();
    public abstract GpDao gpDao();
    public abstract VillageDao villageDao();
    public abstract BeneDao beneDao();

    public abstract SubDisDao subDisDao();

    public abstract ActionPlanDao actionPlanDao();

    public abstract WorkPlanDao workPlanDao();

    public abstract ActivityReportDao activityReportDao();

    public abstract LivehoodDao livehoodDao();

    public abstract HealthcareDao healthcareDao();

    public abstract EducationDao educationDao();

    public abstract SocialDao socialDao();

    public abstract AttendanceDao attendanceDao();

    public abstract ActionPlanMonthDao actionPlanMonthDao();


}