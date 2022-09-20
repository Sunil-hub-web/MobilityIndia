package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "activity_report_attend_table")
public class ActivityReportAttendanceData implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    @NonNull
    private String id;

    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    @Expose
    private String userId;

    @ColumnInfo(name = "date_attendance")
    @SerializedName("date_attendance")
    @Expose
    private String dateAttendance;

    @ColumnInfo(name = "attendance")
    @SerializedName("attendance")
    @Expose
    private String attendance;

    @ColumnInfo(name = "first_half")
    @SerializedName("first_half")
    @Expose
    private String firstHalf;

    @ColumnInfo(name = "second_half")
    @SerializedName("second_half")
    @Expose
    private String secondHalf;

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

    @ColumnInfo(name = "is_apporoved")
    @SerializedName("is_apporoved")
    @Expose
    private String isApporoved;

    @ColumnInfo(name = "month_year")
    @SerializedName("month_year")
    @Expose
    private String monthYear;

    @ColumnInfo(name = "submitted_at")
    @SerializedName("submitted_at")
    @Expose
    private String submittedAt;

    @ColumnInfo(name = "submitted_by")
    @SerializedName("submitted_by")
    @Expose
    private String submittedBy;

    @ColumnInfo(name = "approved_at")
    @SerializedName("approved_at")
    @Expose
    private String approvedAt;

    @ColumnInfo(name = "approved_by")
    @SerializedName("approved_by")
    @Expose
    private String approvedBy;

    @ColumnInfo(name = "reworked_at")
    @SerializedName("reworked_at")
    @Expose
    private String reworkedAt;

    @ColumnInfo(name = "reworked_by")
    @SerializedName("reworked_by")
    @Expose
    private String reworkedBy;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo(name = "user_type_id")
    @SerializedName("user_type_id")
    @Expose
    private String userTypeId;

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    @Expose
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


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

    public String getDateAttendance() {
        return dateAttendance;
    }

    public void setDateAttendance(String dateAttendance) {
        this.dateAttendance = dateAttendance;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getFirstHalf() {
        return firstHalf;
    }

    public void setFirstHalf(String firstHalf) {
        this.firstHalf = firstHalf;
    }

    public String getSecondHalf() {
        return secondHalf;
    }

    public void setSecondHalf(String secondHalf) {
        this.secondHalf = secondHalf;
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

    public String getIsApporoved() {
        return isApporoved;
    }

    public void setIsApporoved(String isApporoved) {
        this.isApporoved = isApporoved;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
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

    public String getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(String approvedAt) {
        this.approvedAt = approvedAt;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getReworkedAt() {
        return reworkedAt;
    }

    public void setReworkedAt(String reworkedAt) {
        this.reworkedAt = reworkedAt;
    }

    public String getReworkedBy() {
        return reworkedBy;
    }

    public void setReworkedBy(String reworkedBy) {
        this.reworkedBy = reworkedBy;
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
