package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.HealthCareData;
import com.example.mobilityindia.sync.model.SocialData;

import java.util.List;

@Dao
public interface HealthcareDao {
    @Insert
    void insertHealthCare(HealthCareData... model);
    @Update
    void updateHealthCare(HealthCareData model);

    @Delete
    void deleteHealthCare(HealthCareData model);

    @Query("DELETE FROM healthcare_table WHERE id =:str")
    void deleteHealthCare(String str);

    @Query("DELETE FROM healthcare_table")
    void deleteHealth();

    @Query("SELECT * FROM healthcare_table")
    LiveData<List<HealthCareData>> getHealthCareList();


    @Query("SELECT * FROM healthcare_table WHERE id =:str")
    LiveData<List<HealthCareData>> getSelectedHealthCare(String str);

    @Query("SELECT created_at FROM healthcare_table WHERE benificiary_id =:str")
    LiveData<List<String>> getSelectedDate(String str);

    @Query("SELECT created_at FROM healthcare_table WHERE id =:str")
    LiveData<List<String>> getSelectedDate1(String str);

    @Query("SELECT * FROM healthcare_table WHERE created_at =:str")
    LiveData<List<HealthCareData>> getSelectedHealthCareWithDate(String str);

    @Query("SELECT * FROM healthcare_table WHERE created_at =:var1 AND benificiary_id = :var2")
    LiveData<List<HealthCareData>> getSelectedHealthWithData(String var1, String var2);

}
