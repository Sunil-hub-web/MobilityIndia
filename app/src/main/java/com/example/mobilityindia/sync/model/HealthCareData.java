package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "healthcare_table")
public class HealthCareData implements Serializable {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    @NonNull
    public String id;

    @ColumnInfo(name = "benificiary_id")
    @SerializedName("benificiary_id")
    @Expose
    public String benificiary_id;

    public String getBenificiary_id() {
        return benificiary_id;
    }

    public void setBenificiary_id(String benificiary_id) {
        this.benificiary_id = benificiary_id;
    }

    @ColumnInfo(name = "screeningdate")
    @SerializedName("screeningdate")
    @Expose
    public String screeningdate;

    @ColumnInfo(name = "assessmentdate")
    @SerializedName("assessmentdate")
    @Expose
    public String assessmentdate;

    @ColumnInfo(name = "assessmentwho")
    @SerializedName("assessmentwho")
    @Expose
    public String assessmentwho;

    @ColumnInfo(name = "assessmentwhere")
    @SerializedName("assessmentwhere")
    @Expose
    public String assessmentwhere;

    @ColumnInfo(name = "referral")
    @SerializedName("referral")
    @Expose
    public String referral;

    @ColumnInfo(name = "referralplace")
    @SerializedName("referralplace")
    @Expose
    public String referralplace;

    @ColumnInfo(name = "referralprescription")
    @SerializedName("referralprescription")
    @Expose
    public String referralprescription;

    @ColumnInfo(name = "trialwhat")
    @SerializedName("trialwhat")
    @Expose
    public String trialwhat;

    @ColumnInfo(name = "trialdate")
    @SerializedName("trialdate")
    @Expose
    public String trialdate;

    @ColumnInfo(name = "gaitfrequency")
    @SerializedName("gaitfrequency")
    @Expose
    public String gaitfrequency;

    @ColumnInfo(name = "gaithowmany")
    @SerializedName("gaithowmany")
    @Expose
    public String gaithowmany;

    @ColumnInfo(name = "therapyfrequency")
    @SerializedName("therapyfrequency")
    @Expose
    public String therapyfrequency;

    @ColumnInfo(name = "therapysessions")
    @SerializedName("therapysessions")
    @Expose
    public String therapysessions;

    @ColumnInfo(name = "fitmentwho")
    @SerializedName("fitmentwho")
    @Expose
    public String fitmentwho;

    @ColumnInfo(name = "fitmentwhere")
    @SerializedName("fitmentwhere")
    @Expose
    public String fitmentwhere;

    @ColumnInfo(name = "fitmentdevice")
    @SerializedName("fitmentdevice")
    @Expose
    public String fitmentdevice;

    @ColumnInfo(name = "followupfrequency")
    @SerializedName("followupfrequency")
    @Expose
    public String followupfrequency;

    @ColumnInfo(name = "followupsheet")
    @SerializedName("followupsheet")
    @Expose
    public String followupsheet;

    @ColumnInfo(name = "surgery")
    @SerializedName("surgery")
    @Expose
    public String surgery;

    @ColumnInfo(name = "surgerywhere")
    @SerializedName("surgerywhere")
    @Expose
    public String surgerywhere;

    @ColumnInfo(name = "surgerywherewhat")
    @SerializedName("surgerywherewhat")
    @Expose
    public String surgerywherewhat;

    @ColumnInfo(name = "homerecommend")
    @SerializedName("homerecommend")
    @Expose
    public String homerecommend;

    @ColumnInfo(name = "homerecommendwhat")
    @SerializedName("homerecommendwhat")
    @Expose
    public String homerecommendwhat;

    @ColumnInfo(name = "ihp")
    @SerializedName("ihp")
    @Expose
    public String ihp;

    @ColumnInfo(name = "ihp_doc")
    @SerializedName("ihp_doc")
    @Expose
    public String ihpDoc;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    @Expose
    public String createdAt;

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getScreeningdate() {
        return screeningdate;
    }

    public void setScreeningdate(String screeningdate) {
        this.screeningdate = screeningdate;
    }

    public String getAssessmentdate() {
        return assessmentdate;
    }

    public void setAssessmentdate(String assessmentdate) {
        this.assessmentdate = assessmentdate;
    }

    public String getAssessmentwho() {
        return assessmentwho;
    }

    public void setAssessmentwho(String assessmentwho) {
        this.assessmentwho = assessmentwho;
    }

    public String getAssessmentwhere() {
        return assessmentwhere;
    }

    public void setAssessmentwhere(String assessmentwhere) {
        this.assessmentwhere = assessmentwhere;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public String getReferralplace() {
        return referralplace;
    }

    public void setReferralplace(String referralplace) {
        this.referralplace = referralplace;
    }

    public String getReferralprescription() {
        return referralprescription;
    }

    public void setReferralprescription(String referralprescription) {
        this.referralprescription = referralprescription;
    }

    public String getTrialwhat() {
        return trialwhat;
    }

    public void setTrialwhat(String trialwhat) {
        this.trialwhat = trialwhat;
    }

    public String getTrialdate() {
        return trialdate;
    }

    public void setTrialdate(String trialdate) {
        this.trialdate = trialdate;
    }

    public String getGaitfrequency() {
        return gaitfrequency;
    }

    public void setGaitfrequency(String gaitfrequency) {
        this.gaitfrequency = gaitfrequency;
    }

    public String getGaithowmany() {
        return gaithowmany;
    }

    public void setGaithowmany(String gaithowmany) {
        this.gaithowmany = gaithowmany;
    }

    public String getTherapyfrequency() {
        return therapyfrequency;
    }

    public void setTherapyfrequency(String therapyfrequency) {
        this.therapyfrequency = therapyfrequency;
    }

    public String getTherapysessions() {
        return therapysessions;
    }

    public void setTherapysessions(String therapysessions) {
        this.therapysessions = therapysessions;
    }

    public String getFitmentwho() {
        return fitmentwho;
    }

    public void setFitmentwho(String fitmentwho) {
        this.fitmentwho = fitmentwho;
    }

    public String getFitmentwhere() {
        return fitmentwhere;
    }

    public void setFitmentwhere(String fitmentwhere) {
        this.fitmentwhere = fitmentwhere;
    }

    public String getFitmentdevice() {
        return fitmentdevice;
    }

    public void setFitmentdevice(String fitmentdevice) {
        this.fitmentdevice = fitmentdevice;
    }

    public String getFollowupfrequency() {
        return followupfrequency;
    }

    public void setFollowupfrequency(String followupfrequency) {
        this.followupfrequency = followupfrequency;
    }

    public String getFollowupsheet() {
        return followupsheet;
    }

    public void setFollowupsheet(String followupsheet) {
        this.followupsheet = followupsheet;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }

    public String getSurgerywhere() {
        return surgerywhere;
    }

    public void setSurgerywhere(String surgerywhere) {
        this.surgerywhere = surgerywhere;
    }

    public String getSurgerywherewhat() {
        return surgerywherewhat;
    }

    public void setSurgerywherewhat(String surgerywherewhat) {
        this.surgerywherewhat = surgerywherewhat;
    }

    public String getHomerecommend() {
        return homerecommend;
    }

    public void setHomerecommend(String homerecommend) {
        this.homerecommend = homerecommend;
    }

    public String getHomerecommendwhat() {
        return homerecommendwhat;
    }

    public void setHomerecommendwhat(String homerecommendwhat) {
        this.homerecommendwhat = homerecommendwhat;
    }

    public String getIhp() {
        return ihp;
    }

    public void setIhp(String ihp) {
        this.ihp = ihp;
    }

    public String getIhpDoc() {
        return ihpDoc;
    }

    public void setIhpDoc(String ihpDoc) {
        this.ihpDoc = ihpDoc;
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
}

