package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.DistData;

import java.util.List;

@Dao
public interface DistDao {
    @Insert
    void insertDist(DistData... model);
    @Update
    void updateDist(DistData model);

    @Delete
    void deleteDist(DistData model);

    @Query("DELETE FROM dist_table")
    void deleteDist();

    @Query("SELECT * FROM dist_table")
    LiveData<List<DistData>> getAllDistList();

    @Query("SELECT * FROM dist_table WHERE state_id =:idd")
    LiveData<List<DistData>> getDistRespectedToState(int idd);

    @Query("SELECT * FROM dist_table WHERE district_id =:idd")
    LiveData<List<DistData>> getDistDetalis(int idd);

}
