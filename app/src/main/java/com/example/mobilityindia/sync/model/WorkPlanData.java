package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "workplan_table")
public class WorkPlanData implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    @NonNull
    private String id;

    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    @Expose
    private String userId;

    @ColumnInfo(name = "month_year")
    @SerializedName("month_year")
    @Expose
    private String monthYear;

    @ColumnInfo(name = "no_village_visit")
    @SerializedName("no_village_visit")
    @Expose
    private String noVillageVisit;

    @ColumnInfo(name = "no_govt_visit")
    @SerializedName("no_govt_visit")
    @Expose
    private String noGovtVisit;

    @ColumnInfo(name = "meeting_attend_internal")
    @SerializedName("meeting_attend_internal")
    @Expose
    private String meetingAttendInternal;

    @ColumnInfo(name = "meeting_attend_external")
    @SerializedName("meeting_attend_external")
    @Expose
    private String meetingAttendExternal;

    @ColumnInfo(name = "no_of_training_attend")
    @SerializedName("no_of_training_attend")
    @Expose
    private String noOfTrainingAttend;

    @ColumnInfo(name = "no_of_training_facilited")
    @SerializedName("no_of_training_facilited")
    @Expose
    private String noOfTrainingFacilited;

    @ColumnInfo(name = "other_events")
    @SerializedName("other_events")
    @Expose
    private String otherEvents;

    @ColumnInfo(name = "status")
    @SerializedName("status")
    @Expose
    private String status;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @ColumnInfo(name = "submitted_at")
    @SerializedName("submitted_at")
    @Expose
    private String submittedAt;

    @ColumnInfo(name = "submitted_by")
    @SerializedName("submitted_by")
    @Expose
    private String submittedBy;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name = "user_type_id")
    @SerializedName("user_type_id")
    @Expose
    private String userTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public String getNoVillageVisit() {
        return noVillageVisit;
    }

    public void setNoVillageVisit(String noVillageVisit) {
        this.noVillageVisit = noVillageVisit;
    }

    public String getNoGovtVisit() {
        return noGovtVisit;
    }

    public void setNoGovtVisit(String noGovtVisit) {
        this.noGovtVisit = noGovtVisit;
    }

    public String getMeetingAttendInternal() {
        return meetingAttendInternal;
    }

    public void setMeetingAttendInternal(String meetingAttendInternal) {
        this.meetingAttendInternal = meetingAttendInternal;
    }

    public String getMeetingAttendExternal() {
        return meetingAttendExternal;
    }

    public void setMeetingAttendExternal(String meetingAttendExternal) {
        this.meetingAttendExternal = meetingAttendExternal;
    }

    public String getNoOfTrainingAttend() {
        return noOfTrainingAttend;
    }

    public void setNoOfTrainingAttend(String noOfTrainingAttend) {
        this.noOfTrainingAttend = noOfTrainingAttend;
    }

    public String getNoOfTrainingFacilited() {
        return noOfTrainingFacilited;
    }

    public void setNoOfTrainingFacilited(String noOfTrainingFacilited) {
        this.noOfTrainingFacilited = noOfTrainingFacilited;
    }

    public String getOtherEvents() {
        return otherEvents;
    }

    public void setOtherEvents(String otherEvents) {
        this.otherEvents = otherEvents;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(String submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }
}
