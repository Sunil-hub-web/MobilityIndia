package com.example.mobilityindia.actionplan.model;

import java.util.ArrayList;

public class MonthPlanAction_Model {

    String id,user_id,month_year,status,name,user_type_id;
    ArrayList<Plans_ModelClass> palndetails;

    public MonthPlanAction_Model(String id, String user_id, String month_year, String status, String name,
                                 String user_type_id,ArrayList<Plans_ModelClass> palndetails) {
        this.id = id;
        this.user_id = user_id;
        this.month_year = month_year;
        this.status = status;
        this.name = name;
        this.user_type_id = user_type_id;
        this.palndetails = palndetails;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_type_id() {
        return user_type_id;
    }

    public void setUser_type_id(String user_type_id) {
        this.user_type_id = user_type_id;
    }

    public ArrayList<Plans_ModelClass> getPalndetails() {
        return palndetails;
    }

    public void setPalndetails(ArrayList<Plans_ModelClass> palndetails) {
        this.palndetails = palndetails;
    }

    @Override
    public String toString() {
        return "MonthPlanAction_Model{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", month_year='" + month_year + '\'' +
                ", status='" + status + '\'' +
                ", name='" + name + '\'' +
                ", user_type_id='" + user_type_id + '\'' +
                ", palndetails=" + palndetails +
                '}';
    }
}
