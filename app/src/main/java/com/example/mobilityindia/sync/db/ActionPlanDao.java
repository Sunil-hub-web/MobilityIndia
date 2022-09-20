package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.ActionPlanData;

import java.util.List;

@Dao
public interface ActionPlanDao {

    @Insert
    void insertActionPlan(ActionPlanData... model);
    @Update
    void updateActionPlan(ActionPlanData model);

    @Delete
    void deleteActionPlan(ActionPlanData model);

    @Query("DELETE FROM actionpln_table")
    void deleteBene();

    @Query("SELECT * FROM actionpln_table")
    LiveData<List<ActionPlanData>> getAllActionPlanList();

    @Query("SELECT * FROM actionpln_table GROUP BY month_year")
    LiveData<List<ActionPlanData>> getFilterActionPlanList();

    @Query("SELECT * FROM actionpln_table WHERE month_year =:str")
    LiveData<List<ActionPlanData>> getSelectedActionPlanList(String str);

}
