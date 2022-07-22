package com.example.mobilityindia.syn1.view.allresponse.activityreport;

import com.example.mobilityindia.sync.model.ActivityReportAttendanceData;
import com.example.mobilityindia.sync.model.WorkPlanData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Datum_ActivityReport {

    @SerializedName("activity_report")
    @Expose
    private WorkPlanData activityReport;
    @SerializedName("attendances")
    @Expose
    private List<ActivityReportAttendanceData> attendances = null;

    public WorkPlanData getActivityReport() {
        return activityReport;
    }

    public void setActivityReport(WorkPlanData activityReport) {
        this.activityReport = activityReport;
    }

    public List<ActivityReportAttendanceData> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<ActivityReportAttendanceData> attendances) {
        this.attendances = attendances;
    }
}
