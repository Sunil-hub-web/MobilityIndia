package com.example.mobilityindia.syn1.view.allresponse.actionplan;

import com.example.mobilityindia.sync.model.ActionPlanData;
import com.example.mobilityindia.sync.model.ActionPlanMonth;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum_ActionPlan {

    @SerializedName("monthly_status")
    @Expose
    private ActionPlanData monthlyStatus;
    @SerializedName("plans")
    @Expose
    private List<ActionPlanMonth> plans = null;

    public ActionPlanData getMonthlyStatus() {
        return monthlyStatus;
    }

    public void setMonthlyStatus(ActionPlanData monthlyStatus) {
        this.monthlyStatus = monthlyStatus;
    }

    public List<ActionPlanMonth> getPlans() {
        return plans;
    }

    public void setPlans(List<ActionPlanMonth> plans) {
        this.plans = plans;
    }
}
