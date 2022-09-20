package com.example.mobilityindia.actionplan.model;

import java.io.Serializable;

public class Plans_ModelClass implements Serializable {

    String id,user_id,month_year,date,plan,result,remarks;

    public Plans_ModelClass(String id, String user_id, String month_year, String date, String plan,
                            String result, String remarks) {
        this.id = id;
        this.user_id = user_id;
        this.month_year = month_year;
        this.date = date;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMonth_year() {
        return month_year;
    }

    public void setMonth_year(String month_year) {
        this.month_year = month_year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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


    @Override
    public String toString() {
        return "Plans_ModelClass{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", month_year='" + month_year + '\'' +
                ", date='" + date + '\'' +
                ", plan='" + plan + '\'' +
                ", result='" + result + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
