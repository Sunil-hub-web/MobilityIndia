package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.WorkPlanData;

import java.util.List;

@Dao
public interface WorkPlanDao {
    @Insert
    void insertWorkPlan(WorkPlanData... model);

    @Update
    void updateWorkPlan(WorkPlanData model);

    @Delete
    void deleteWorkPlan(WorkPlanData model);

    @Query("DELETE FROM workplan_table")
    void deleteWorkplan();

    @Query("SELECT * FROM workplan_table")
    LiveData<List<WorkPlanData>> getAllWorkPlanList();

    @Query("SELECT * FROM workplan_table GROUP BY month_year")
    LiveData<List<WorkPlanData>> getFilterWorkPlanList();

    @Query("SELECT * FROM workplan_table WHERE month_year =:str GROUP BY month_year")
    LiveData<List<WorkPlanData>> getSelectedWorkPlanList(String str);

}
