package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.mobilityindia.syn1.view.allresponse.Converters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@TypeConverters(Converters.class)
@Entity(tableName = "social_table")
public class SocialData implements Serializable {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    @NonNull
    public String id;

    @ColumnInfo(name = "benificiary_id")
    @SerializedName("benificiary_id")
    @Expose
    private String benificiaryId;

    @ColumnInfo(name = "socialsports")
    @SerializedName("socialsports")
    @Expose
    private String socialsports;

    @ColumnInfo(name = "socialsports_mention")
    @SerializedName("socialsports_mention")
    @Expose
    private String socialsportsMention;

    @ColumnInfo(name = "socialgovt")
    @SerializedName("socialgovt")
    @Expose
    private String socialgovt;

    @ColumnInfo(name = "socialgovtwhat")
    @SerializedName("socialgovtwhat")
    @Expose
    private String socialgovtwhat;

    @ColumnInfo(name = "socialtraining")
    @SerializedName("socialtraining")
    @Expose
    private String socialtraining;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @ColumnInfo(name = "created_by")
    @SerializedName("created_by")
    @Expose
    private String createdBy;

    @ColumnInfo(name = "updated_by")
    @SerializedName("updated_by")
    @Expose
    private String updatedBy;

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    @Expose
    private String flag;


    @ColumnInfo(name = "socialtrainingwhat")
    @SerializedName("socialtrainingwhat")
    @Expose
    private List<String> socialtrainingwhat;


    @ColumnInfo(name = "socialtrainingwhere")
    @SerializedName("socialtrainingwhere")
    @Expose
    private List<String> socialtrainingwhere;


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

    public String getBenificiaryId() {
        return benificiaryId;
    }

    public void setBenificiaryId(String benificiaryId) {
        this.benificiaryId = benificiaryId;
    }

    public String getSocialsports() {
        return socialsports;
    }

    public void setSocialsports(String socialsports) {
        this.socialsports = socialsports;
    }

    public String getSocialsportsMention() {
        return socialsportsMention;
    }

    public void setSocialsportsMention(String socialsportsMention) {
        this.socialsportsMention = socialsportsMention;
    }

    public String getSocialgovt() {
        return socialgovt;
    }

    public void setSocialgovt(String socialgovt) {
        this.socialgovt = socialgovt;
    }

    public String getSocialgovtwhat() {
        return socialgovtwhat;
    }

    public void setSocialgovtwhat(String socialgovtwhat) {
        this.socialgovtwhat = socialgovtwhat;
    }

    public String getSocialtraining() {
        return socialtraining;
    }

    public void setSocialtraining(String socialtraining) {
        this.socialtraining = socialtraining;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<String> getSocialtrainingwhat() {
        return socialtrainingwhat;
    }

    public void setSocialtrainingwhat(List<String> socialtrainingwhat) {
        this.socialtrainingwhat = socialtrainingwhat;
    }

    public List<String> getSocialtrainingwhere() {
        return socialtrainingwhere;
    }

    public void setSocialtrainingwhere(List<String> socialtrainingwhere) {
        this.socialtrainingwhere = socialtrainingwhere;
    }

    @Override
    public String toString() {
        return "SocialData{" +
                "id='" + id + '\'' +
                ", benificiaryId='" + benificiaryId + '\'' +
                ", socialsports='" + socialsports + '\'' +
                ", socialsportsMention='" + socialsportsMention + '\'' +
                ", socialgovt='" + socialgovt + '\'' +
                ", socialgovtwhat='" + socialgovtwhat + '\'' +
                ", socialtraining='" + socialtraining + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", flag='" + flag + '\'' +
                ", socialtrainingwhat=" + socialtrainingwhat +
                ", socialtrainingwhere=" + socialtrainingwhere +
                '}';
    }
}
