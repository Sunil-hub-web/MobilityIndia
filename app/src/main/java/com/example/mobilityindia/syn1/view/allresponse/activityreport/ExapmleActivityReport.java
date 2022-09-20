package com.example.mobilityindia.syn1.view.allresponse.activityreport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExapmleActivityReport {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum_ActivityReport> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum_ActivityReport> getData() {
        return data;
    }

    public void setData(List<Datum_ActivityReport> data) {
        this.data = data;
    }
}
