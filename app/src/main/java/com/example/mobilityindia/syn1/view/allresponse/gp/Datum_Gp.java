package com.example.mobilityindia.syn1.view.allresponse.gp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum_Gp {

    @SerializedName("gp_id")
    @Expose
    private String gpId;
    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("block_id")
    @Expose
    private String blockId;
    @SerializedName("district_id")
    @Expose
    private String districtId;
    @SerializedName("hobli_id")
    @Expose
    private String hobliId;
    @SerializedName("gp_name")
    @Expose
    private String gpName;
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

    public String getGpId() {
        return gpId;
    }

    public void setGpId(String gpId) {
        this.gpId = gpId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
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

    public String getHobliId() {
        return hobliId;
    }

    public void setHobliId(String hobliId) {
        this.hobliId = hobliId;
    }

    public String getGpName() {
        return gpName;
    }

    public void setGpName(String gpName) {
        this.gpName = gpName;
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
