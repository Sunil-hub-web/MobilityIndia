package com.example.mobilityindia.actionplan.model;

public class ActionPlanDataL {

    String id,plan,result,remarks;

    public ActionPlanDataL(String id, String plan, String result, String remarks) {
        this.id = id;
        this.plan = plan;
        this.result = result;
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
