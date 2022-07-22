package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.SubDisabilityData;

import java.util.List;

@Dao
public interface SubDisDao {
    @Insert
    void insertSubDisability(SubDisabilityData... model);
    @Update
    void updateSubDisability(SubDisabilityData model);

    @Delete
    void deleteSubDisability(SubDisabilityData model);

    @Query("DELETE FROM subDisable_table")
    void deleteDist();

    @Query("SELECT * FROM subDisable_table")
    LiveData<List<SubDisabilityData>> getAllSubDisabilityList();

    @Query("SELECT * FROM subDisable_table WHERE disability_id =:idd")
    LiveData<List<SubDisabilityData>> getSubDisabilityWithID(int idd);

}
