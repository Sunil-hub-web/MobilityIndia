package com.example.mobilityindia.syn1.view.allresponse.health;

import com.example.mobilityindia.sync.model.HealthCareData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Health_Example {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("healthdata")
    @Expose
    private List<HealthCareData> healthdata = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HealthCareData> getHealthdata() {
        return healthdata;
    }

    public void setHealthdata(List<HealthCareData> healthdata) {
        this.healthdata = healthdata;
    }
}
