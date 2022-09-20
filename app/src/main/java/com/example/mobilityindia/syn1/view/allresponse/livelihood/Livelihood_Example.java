package com.example.mobilityindia.syn1.view.allresponse.livelihood;

import com.example.mobilityindia.sync.model.LivehoodData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Livelihood_Example {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("livelihooddata")
    @Expose
    private List<LivehoodData> livelihooddata = null;

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

    public List<LivehoodData> getLivelihooddata() {
        return livelihooddata;
    }

    public void setLivelihooddata(List<LivehoodData> livelihooddata) {
        this.livelihooddata = livelihooddata;
    }

}
