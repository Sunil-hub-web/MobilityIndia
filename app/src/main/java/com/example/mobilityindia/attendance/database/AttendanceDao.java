package com.example.mobilityindia.attendance.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AttendanceDao {

    @Insert
    void insertAttendance(AttendanceClass... attendance);

    //Delete Query
    @Delete
    void deleteAttendance(AttendanceClass attendance);

    //Delete all query
    @Delete
    void reset(List<AttendanceClass> mainDataList);

    @Query("DELETE FROM attendance_table")
    void deleteAllAttendance();

    //Updat Query
    @Query("UPDATE attendance_table SET clockout_time = :time,clockout_lat = :lat, clockout_long = :lang, city = :city, flag = :flag,statues = :statues,statues_date = :staues_date WHERE date = :date")
    void updateAttendance(String time, String lat, String lang, String city,String flag,String statues,String staues_date, String date);

    //Get all data query
    @Query("SELECT * FROM attendance_table")
    //List<AttendanceClass> getAll();
    LiveData<List<AttendanceClass>> getAll();

    @Query("SELECT * FROM attendance_table WHERE user_id =:userId")
    LiveData<List<AttendanceClass>> getSelectedAttendanceList(String userId);

}
