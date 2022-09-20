package com.example.mobilityindia.sync.model;

import com.example.mobilityindia.constant.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ServiceResponse extends BaseResponse implements Serializable {


    @SerializedName("livelihooddata")
    @Expose
    public ArrayList<LivehoodData> livelihooddata;

    @SerializedName("socialdata")
    @Expose
    public ArrayList<SocialData> socialdata;

    @SerializedName("educationdata")
    @Expose
    public ArrayList<EducationData> educationdata;

    @SerializedName("healthdata")
    @Expose
    public ArrayList<HealthCareData> healthdata;

    @Override
    public String getStatus() {
        return super.getStatus();
    }

    @Override
    public void setStatus(String status) {
        super.setStatus(status);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void setMessage(String message) {
        super.setMessage(message);
    }

    public ArrayList<LivehoodData> getLivelihooddata() {
        return livelihooddata;
    }

    public void setLivelihooddata(ArrayList<LivehoodData> livelihooddata) {
        this.livelihooddata = livelihooddata;
    }

    public ArrayList<SocialData> getSocialdata() {
        return socialdata;
    }

    public void setSocialdata(ArrayList<SocialData> socialdata) {
        this.socialdata = socialdata;
    }

    public ArrayList<EducationData> getEducationdata() {
        return educationdata;
    }

    public void setEducationdata(ArrayList<EducationData> educationdata) {
        this.educationdata = educationdata;
    }

    public ArrayList<HealthCareData> getHealthdata() {
        return healthdata;
    }

    public void setHealthdata(ArrayList<HealthCareData> healthdata) {
        this.healthdata = healthdata;
    }

}
