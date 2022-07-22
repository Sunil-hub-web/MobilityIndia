package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "subDisable_table")
public class SubDisabilityData implements Serializable {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    @NonNull
    public String id;
    @ColumnInfo(name = "sub_disability_name")
    @SerializedName("sub_disability_name")
    @Expose
    public String subDisabilityName;

    @ColumnInfo(name = "disability_id")
    @SerializedName("disability_id")
    @Expose
    public String disabilityId;

    @ColumnInfo(name = "status")
    @SerializedName("status")
    @Expose
    public String status;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    @Expose
    public String createdAt;

    @ColumnInfo(name = "created_by")
    @SerializedName("created_by")
    @Expose
    public String createdBy;

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    @ColumnInfo(name = "updated_by")
    @SerializedName("updated_by")
    @Expose
    public String updatedBy;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getSubDisabilityName() {
        return subDisabilityName;
    }

    public void setSubDisabilityName(String subDisabilityName) {
        this.subDisabilityName = subDisabilityName;
    }

    public String getDisabilityId() {
        return disabilityId;
    }

    public void setDisabilityId(String disabilityId) {
        this.disabilityId = disabilityId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
