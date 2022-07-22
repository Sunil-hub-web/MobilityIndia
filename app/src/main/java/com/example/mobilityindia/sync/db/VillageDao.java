package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.VillageData;

import java.util.List;

@Dao
public interface VillageDao {
    @Insert
    void insertVillage(VillageData... model);
    @Update
    void updateVillage(VillageData model);

    @Delete
    void deleteVillage(VillageData model);

    @Query("DELETE FROM village_table")
    void deleteVillage();

    @Query("SELECT * FROM village_table")
    LiveData<List<VillageData>> getAllVillageList();

    @Query("SELECT * FROM village_table WHERE gp_id =:idd")
    LiveData<List<VillageData>> getVillageRespectedToGP(int idd);

    @Query("SELECT * FROM village_table WHERE id =:idd")
    LiveData<List<VillageData>> getVillageDetail(int idd);


}
