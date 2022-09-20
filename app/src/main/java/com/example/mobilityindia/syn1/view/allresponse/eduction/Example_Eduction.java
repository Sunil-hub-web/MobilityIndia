package com.example.mobilityindia.syn1.view.allresponse.eduction;

import com.example.mobilityindia.sync.model.EducationData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example_Eduction {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("educationdata")
    @Expose
    private List<EducationData> educationdata = null;

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

    public List<EducationData> getEducationdata() {
        return educationdata;
    }

    public void setEducationdata(List<EducationData> educationdata) {
        this.educationdata = educationdata;
    }
}
