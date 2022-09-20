package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "dist_table")
public class DistData implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("district_id")
    @Expose
    @NonNull
    public String district_id;

    @ColumnInfo(name = "state_id")
    @SerializedName("state_id")
    @Expose
    public String state_id;

    @ColumnInfo(name = "district_name")
    @SerializedName("district_name")
    @Expose
    public String district_name;

    @NonNull
    public String getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(@NonNull String district_id) {
        this.district_id = district_id;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }
}
