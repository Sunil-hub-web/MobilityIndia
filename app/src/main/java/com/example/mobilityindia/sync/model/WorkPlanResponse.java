package com.example.mobilityindia.sync.model;

import com.example.mobilityindia.constant.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkPlanResponse extends BaseResponse implements Serializable {

    @SerializedName("data")
    @Expose
    public ArrayList<WorkPlanData>workplanList;

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

    public ArrayList<WorkPlanData> getWorkplanList() {
        return workplanList;
    }

    public void setWorkplanList(ArrayList<WorkPlanData> workplanList) {
        this.workplanList = workplanList;
    }
}
