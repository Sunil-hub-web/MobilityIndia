package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.EducationData;

import java.util.List;

@Dao
public interface EducationDao {
    @Insert
    void insertEducation(EducationData... model);
    @Update
    void updateEducation(EducationData model);

    @Delete
    void deleteEducation(EducationData model);

    @Query("DELETE FROM education_table")
    void deleteEducation();

    @Query("SELECT * FROM education_table")
    LiveData<List<EducationData>> getAllEducationList();

    @Query("SELECT * FROM education_table WHERE benificiary_id =:str")
    LiveData<List<EducationData>> getSelectedEducationList(String str);

    @Query("SELECT created_at FROM education_table WHERE id =:str")
    LiveData<List<String>> getSelectedCreatd(String str);

    @Query("SELECT created_at FROM education_table WHERE benificiary_id =:str")
    LiveData<List<String>> getSelectedCreatd1(String str);

    @Query("SELECT * FROM education_table WHERE created_at =:str")
    LiveData<List<EducationData>> getSelectedEducationWithDate(String str);

    @Query("SELECT * FROM education_table WHERE id =:str")
    LiveData<List<EducationData>> getSelectedEducationWithDate1(String str);

}
