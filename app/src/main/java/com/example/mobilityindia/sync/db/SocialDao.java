package com.example.mobilityindia.sync.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.room.Update;

import com.example.mobilityindia.syn1.view.allresponse.Converters;
import com.example.mobilityindia.sync.model.SocialData;

import java.util.List;

@Dao
public interface SocialDao {
    @Insert
    void insertSocial(SocialData... model);
    @Update
    void updateSocial(SocialData model);

    @Delete
    void deleteSocial(SocialData model);

    @Query("DELETE FROM social_table")
    void deleteSocial();

    @Query("SELECT * FROM social_table")
    LiveData<List<SocialData>> getAllSocialList();

    @Query("SELECT * FROM social_table WHERE id =:str")
    LiveData<List<SocialData>> getSelectedSociallist(String str);

    @Query("SELECT created_at FROM social_table WHERE id =:str")
    LiveData<List<String>> getSelectedCreatd(String str);

    @Query("SELECT created_at FROM social_table WHERE benificiary_id =:str")
    LiveData<List<String>> getSelectedCreatd1(String str);


    @Query("SELECT * FROM social_table WHERE created_at =:str")
    LiveData<List<SocialData>> getSelectedEducationWithDate(String str);

    @Query("SELECT * FROM social_table WHERE created_at =:var1 AND benificiary_id = :var2")
    LiveData<List<SocialData>> getSelectedEducationWithData(String var1,String var2);

}
