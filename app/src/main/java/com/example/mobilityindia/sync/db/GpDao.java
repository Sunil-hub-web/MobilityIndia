package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.GPData;

import java.util.List;

@Dao
public interface GpDao {
    @Insert
    void insertGP(GPData... model);
    @Update
    void updateGP(GPData model);

    @Delete
    void deleteGP(GPData model);

    @Query("DELETE FROM gp_table")
    void deleteGP();

    @Query("SELECT * FROM gp_table")
    LiveData<List<GPData>> getAllGPList();

    @Query("SELECT * FROM gp_table WHERE block_id =:idd")
    LiveData<List<GPData>> getGPRespectedToBlock(int idd);

    @Query("SELECT * FROM gp_table WHERE gpId =:idd")
    LiveData<List<GPData>> getGPListDetails(int idd);

}
