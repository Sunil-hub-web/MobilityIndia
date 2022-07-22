package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
    public String benificiary_id;

    public String getBenificiary_id() {
        return benificiary_id;
    }

    public void setBenificiary_id(String benificiary_id) {
        this.benificiary_id = benificiary_id;
    }

    @ColumnInfo(name = "socialsports")
    @SerializedName("socialsports")
    @Expose
    public String socialsports;


    @ColumnInfo(name = "spcialdpo")
    @SerializedName("spcialdpo")
    @Expose
    public String spcialdpo;


    @ColumnInfo(name = "socialgovt")
    @SerializedName("socialgovt")
    @Expose
    public String socialgovt;

    @ColumnInfo(name = "socialgovtwhat")
    @SerializedName("socialgovtwhat")
    @Expose
    public String socialgovtwhat;

    @ColumnInfo(name = "socialtraining")
    @SerializedName("socialtraining")
    @Expose
    public String socialtraining;

    @ColumnInfo(name = "socialtrainingwhat")
    @SerializedName("socialtrainingwhat")
    @Expose
    public String socialtrainingwhat;

    @ColumnInfo(name = "socialtrainingwhere")
    @SerializedName("socialtrainingwhere")
    @Expose
    public String socialtrainingwhere;

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

    public String getSocialsports() {
        return socialsports;
    }

    public void setSocialsports(String socialsports) {
        this.socialsports = socialsports;
    }

    public String getSpcialdpo() {
        return spcialdpo;
    }

    public void setSpcialdpo(String spcialdpo) {
        this.spcialdpo = spcialdpo;
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

    public String getSocialtrainingwhat() {
        return socialtrainingwhat;
    }

    public void setSocialtrainingwhat(String socialtrainingwhat) {
        this.socialtrainingwhat = socialtrainingwhat;
    }

    public String getSocialtrainingwhere() {
        return socialtrainingwhere;
    }

    public void setSocialtrainingwhere(String socialtrainingwhere) {
        this.socialtrainingwhere = socialtrainingwhere;
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
