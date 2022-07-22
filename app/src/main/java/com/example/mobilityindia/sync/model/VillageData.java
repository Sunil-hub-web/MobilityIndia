package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "village_table")
public class VillageData implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    @NonNull
    public String Id;

    @ColumnInfo(name = "gp_id")
    @SerializedName("gp_id")
    @Expose
    public String gpId;

    @ColumnInfo(name = "hobli_id")
    @SerializedName("hobli_id")
    @Expose
    public String hobliId;


    @ColumnInfo(name = "block_id")
    @SerializedName("block_id")
    @Expose
    public String blockId;


    @ColumnInfo(name = "district_id")
    @SerializedName("district_id")
    @Expose
    public String districtId;

    @ColumnInfo(name = "state_id")
    @SerializedName("state_id")
    @Expose
    public String stateId;


    @ColumnInfo(name = "village_name")
    @SerializedName("village_name")
    @Expose
    public String villageName;

    @NonNull
    public String getId() {
        return Id;
    }

    public void setId(@NonNull String id) {
        Id = id;
    }

    public String getGpId() {
        return gpId;
    }

    public void setGpId(String gpId) {
        this.gpId = gpId;
    }

    public String getHobliId() {
        return hobliId;
    }

    public void setHobliId(String hobliId) {
        this.hobliId = hobliId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
}
