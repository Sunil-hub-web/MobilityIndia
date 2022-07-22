package com.example.mobilityindia.syn1.view.allresponse.distic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum_Distic {

    @SerializedName("district_id")
    @Expose
    private String districtId;
    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("district_name")
    @Expose
    private String districtName;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("updated_by")
    @Expose
    private String updatedBy;

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

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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