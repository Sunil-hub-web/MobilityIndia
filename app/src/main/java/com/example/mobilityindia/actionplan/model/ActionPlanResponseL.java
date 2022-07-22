package com.example.mobilityindia.actionplan.model;

import com.example.mobilityindia.constant.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ActionPlanResponseL extends BaseResponse {

    @SerializedName("data")
    @Expose
    public ArrayList<ActionPlanDataL> ActPlanData;

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

    public ArrayList<ActionPlanDataL> getActPlanData() {
        return ActPlanData;
    }

    public void setActPlanData(ArrayList<ActionPlanDataL> actPlanData) {
        ActPlanData = actPlanData;
    }
}
