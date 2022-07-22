package com.example.mobilityindia.attendance.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AttendanceDao {

    //Insert query
    @Insert
    void insertAttandance(AttendanceClass attendance);

    //Delete Query
    @Delete
    void deleteAttendance(AttendanceClass attendance);

    //Delete all query
    @Delete
    void reset(List<AttendanceClass> mainDataList);

    @Query("DELETE FROM attendance_table")
    void deleteAllAttendance();

    //Updat Query
    @Query("UPDATE attendance_table SET clockout = :time,clockout_lat = :lat, clockout_long = :lang, city = :city WHERE date_attendance = :date")
    void updateAttendance(String time, Double lat, Double lang, String city, String date);

    //Get all data query
    @Query("SELECT * FROM attendance_table")
    //List<AttendanceClass> getAll();
    LiveData<List<AttendanceClass>> getAll();

    @Query("SELECT * FROM attendance_table WHERE user_id =:userId")
    LiveData<List<AttendanceClass>> getSelectedAttendanceList(String userId);

}
