package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.ActivityReportAttendanceData;

import java.util.List;

@Dao
public interface ActivityReportDao {
    @Insert
    void insertActivityRep(ActivityReportAttendanceData... model);
    @Update
    void updateActivityRep(ActivityReportAttendanceData model);

    @Delete
    void deleteActivityRep(ActivityReportAttendanceData model);

    @Query("DELETE FROM activity_report_attend_table")
    void deleteBene();

    @Query("SELECT * FROM activity_report_attend_table")
    LiveData<List<ActivityReportAttendanceData>> getAllActivityReportList();

/*    @Query("SELECT * FROM activity_yrmon_table GROUP BY month_year")
    LiveData<List<ActivityYrMonData>> getFilterActivityYrMnList();*/

    @Query("SELECT * FROM activity_report_attend_table WHERE user_id =:str")
    LiveData<List<ActivityReportAttendanceData>> getSelectedActivityReportList(String str);

    @Query("SELECT * FROM activity_report_attend_table WHERE month_year =:month")
    LiveData<List<ActivityReportAttendanceData>> getSelectedActivityReportListMon(String month);

}
