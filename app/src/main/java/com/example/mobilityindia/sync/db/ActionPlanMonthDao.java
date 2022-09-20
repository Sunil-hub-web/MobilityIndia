package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.ActionPlanMonth;

import java.util.List;

@Dao
public interface ActionPlanMonthDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertActionPlanMonth(ActionPlanMonth... model);

    @Update
    void updateActionPlanMonth(ActionPlanMonth model);

    @Delete
    void deleteActionPlanMonth(ActionPlanMonth model);

    @Query("DELETE FROM actionplnmonth_table")
    void deleteBeneMonth();

    @Query("SELECT * FROM actionplnmonth_table")
    LiveData<List<ActionPlanMonth>> getAllActionPlanMonthList();

    @Query("SELECT * FROM actionplnmonth_table GROUP BY month_year")
    LiveData<List<ActionPlanMonth>> getFilterActionPlanMonthList();

    @Query("SELECT * FROM actionplnmonth_table WHERE month_year =:str")
    LiveData<List<ActionPlanMonth>> getSelectedActionPlanMonthList(String str);

    @Query("SELECT * FROM actionplnmonth_table WHERE month_year =:str")
    LiveData<List<ActionPlanMonth>> getSelectedActionPlanMonthList1(String str);
}
