package com.example.mobilityindia.workplan.model;

public class MonthPlanWork_Model {

    String id,user_id,month_year,status,name,user_type_id,noOfVillageVisit,noOfGovtVisit,meetingInternal,
            meetingExternal,noOfTrainingAttended,noOfTrainingFacilated,noOfOtherEvent;

    public MonthPlanWork_Model(String id, String user_id, String month_year, String status, String name,
                               String user_type_id, String noOfVillageVisit, String noOfGovtVisit,
                               String meetingInternal, String meetingExternal, String noOfTrainingAttended,
                               String noOfTrainingFacilated, String noOfOtherEvent) {
        this.id = id;
        this.user_id = user_id;
        this.month_year = month_year;
        this.status = status;
        this.name = name;
        this.user_type_id = user_type_id;
        this.noOfVillageVisit = noOfVillageVisit;
        this.noOfGovtVisit = noOfGovtVisit;
        this.meetingInternal = meetingInternal;
        this.meetingExternal = meetingExternal;
        this.noOfTrainingAttended = noOfTrainingAttended;
        this.noOfTrainingFacilated = noOfTrainingFacilated;
        this.noOfOtherEvent = noOfOtherEvent;
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

    public String getNoOfVillageVisit() {
        return noOfVillageVisit;
    }

    public void setNoOfVillageVisit(String noOfVillageVisit) {
        this.noOfVillageVisit = noOfVillageVisit;
    }

    public String getNoOfGovtVisit() {
        return noOfGovtVisit;
    }

    public void setNoOfGovtVisit(String noOfGovtVisit) {
        this.noOfGovtVisit = noOfGovtVisit;
    }

    public String getMeetingInternal() {
        return meetingInternal;
    }

    public void setMeetingInternal(String meetingInternal) {
        this.meetingInternal = meetingInternal;
    }

    public String getMeetingExternal() {
        return meetingExternal;
    }

    public void setMeetingExternal(String meetingExternal) {
        this.meetingExternal = meetingExternal;
    }

    public String getNoOfTrainingAttended() {
        return noOfTrainingAttended;
    }

    public void setNoOfTrainingAttended(String noOfTrainingAttended) {
        this.noOfTrainingAttended = noOfTrainingAttended;
    }

    public String getNoOfTrainingFacilated() {
        return noOfTrainingFacilated;
    }

    public void setNoOfTrainingFacilated(String noOfTrainingFacilated) {
        this.noOfTrainingFacilated = noOfTrainingFacilated;
    }

    public String getNoOfOtherEvent() {
        return noOfOtherEvent;
    }

    public void setNoOfOtherEvent(String noOfOtherEvent) {
        this.noOfOtherEvent = noOfOtherEvent;
    }
}
