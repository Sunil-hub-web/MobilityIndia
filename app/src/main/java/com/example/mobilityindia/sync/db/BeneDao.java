package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.BeneData;

import java.util.List;

@Dao
public interface BeneDao {
    @Insert
    void insertBene(BeneData... model);
    @Update
    void updateBene(BeneData model);

    @Delete
    void deleteBene(BeneData model);

    @Query("DELETE FROM beneficiary_table")
    void deleteBene();

    @Query("SELECT * FROM beneficiary_table")
    LiveData<List<BeneData>> getAllBeneList();

    @Query("SELECT * FROM beneficiary_table WHERE temp_id =:idd")
    LiveData<List<BeneData>> getSelectedBeneList(String idd);

/*    @Query("SELECT * FROM beneficiary_table WHERE temp_id =:idd")
    LiveData<List<BeneData>> getSelectedBeneList(String idd);*/

}
