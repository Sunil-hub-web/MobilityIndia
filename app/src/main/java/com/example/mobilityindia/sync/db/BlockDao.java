package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mobilityindia.sync.model.BlockData;

import java.util.List;

@Dao
public interface BlockDao {
    @Insert
    void insertBlock(BlockData... model);
    @Update
    void updateBlock(BlockData model);

    @Delete
    void deleteBlock(BlockData model);

    @Query("DELETE FROM block_table")
    void deleteBlock();

    @Query("SELECT * FROM block_table")
    LiveData<List<BlockData>> getAllBlockList();

    @Query("SELECT * FROM block_table WHERE district_id =:idd")
    LiveData<List<BlockData>> getBlockRespectedToDist(int idd);

    @Query("SELECT * FROM block_table WHERE blockId =:idd")
    LiveData<List<BlockData>> getBlockDetail(int idd);

}
