package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "gp_table")
public class GPData implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("gp_id")
    @Expose
    @NonNull
    public String gpId;


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


    @ColumnInfo(name = "gp_name")
    @SerializedName("gp_name")
    @Expose
    public String gpName;

    @NonNull
    public String getGpId() {
        return gpId;
    }

    public void setGpId(@NonNull String gpId) {
        this.gpId = gpId;
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

    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
    }
}
