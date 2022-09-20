package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "block_table")
public class BlockData implements Serializable {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("block_id")
    @Expose
    @NonNull
    public String blockId;

    @ColumnInfo(name = "district_id")
    @SerializedName("district_id")
    @Expose
    public String districtId;

    @ColumnInfo(name = "state_id")
    @SerializedName("state_id")
    @Expose
    public String stateId;


    @ColumnInfo(name = "block_name")
    @SerializedName("block_name")
    @Expose
    public String blockName;

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    @Expose
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    @NonNull
    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(@NonNull String blockId) {
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

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
}
