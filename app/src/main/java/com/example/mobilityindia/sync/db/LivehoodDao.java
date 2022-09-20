package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.LivehoodData;

import java.util.List;

@Dao
public interface LivehoodDao {
    @Insert
    void insertLiveHood(LivehoodData... model);
    @Update
    void updateLivehood(LivehoodData model);

    @Delete
    void deleteLivehood(LivehoodData model);

    @Query("DELETE FROM livehood_table")
    void deleteLivehood();

    @Query("SELECT * FROM livehood_table")
    LiveData<List<LivehoodData>> getAllLiveHoodList();


    @Query("SELECT created_at FROM livehood_table WHERE id=:str")
    LiveData<List<String>> getSelectedLiveHood(String str);

    @Query("SELECT created_at FROM livehood_table WHERE  benificiary_id=:str")
    LiveData<List<String>> getSelectedLiveHood1(String str);


    @Query("SELECT * FROM livehood_table WHERE created_at =:str")
    LiveData<List<LivehoodData>> getSelectedLivelihoodWithDate(String str);

    @Query("SELECT * FROM livehood_table WHERE benificiary_id =:str")
    LiveData<List<LivehoodData>> getSelectedLivelihoodWithDate1(String str);

    @Query("SELECT * FROM livehood_table WHERE id =:str")
    LiveData<List<LivehoodData>> getSelectedLivelihoodWithDate2(String str);

}
