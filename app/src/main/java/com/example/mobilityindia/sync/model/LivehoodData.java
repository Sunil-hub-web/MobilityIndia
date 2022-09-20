package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "livehood_table")
public class LivehoodData implements Serializable {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    @NonNull
    public String id;

    @ColumnInfo(name = "livelihood")
    @SerializedName("livelihood")
    @Expose
    public String livelihood;

    @ColumnInfo(name = "livelihoodName")
    @SerializedName("livelihoodName")
    @Expose
    public String livelihoodName;

    @ColumnInfo(name = "livelihoodother")
    @SerializedName("livelihoodother")
    @Expose
    public String livelihoodother;

    @ColumnInfo(name = "skilldev")
    @SerializedName("skilldev")
    @Expose
    public String skilldev;

    @ColumnInfo(name = "skilldevwhen")
    @SerializedName("skilldevwhen")
    @Expose
    public String skilldevwhen;


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


    @ColumnInfo(name = "skilldevwhere")
    @SerializedName("skilldevwhere")
    @Expose
    public String skilldevwhere;

    @ColumnInfo(name = "skilldevwhat")
    @SerializedName("skilldevwhat")
    @Expose
    public String skilldevwhat;

    @ColumnInfo(name = "finservice")
    @SerializedName("finservice")
    @Expose
    public String finservice;

    @ColumnInfo(name = "finservicewhen")
    @SerializedName("finservicewhen")
    @Expose
    public String finservicewhen;

    @ColumnInfo(name = "finservicewhat")
    @SerializedName("finservicewhat")
    @Expose
    public String finservicewhat;

    @ColumnInfo(name = "finservicewhere")
    @SerializedName("finservicewhere")
    @Expose
    public String finservicewhere;

    @ColumnInfo(name = "socialsecurity")
    @SerializedName("socialsecurity")
    @Expose
    public String socialsecurity;

    @ColumnInfo(name = "socialsecuritywhen")
    @SerializedName("socialsecuritywhen")
    @Expose
    public String socialsecuritywhen;

    @ColumnInfo(name = "socialsecuritywhere")
    @SerializedName("socialsecuritywhere")
    @Expose
    public String socialsecuritywhere;

    @ColumnInfo(name = "socialsecuritywhat")
    @SerializedName("socialsecuritywhat")
    @Expose
    public String socialsecuritywhat;

    @ColumnInfo(name = "vocationaltraining")
    @SerializedName("vocationaltraining")
    @Expose
    public String vocationaltraining;

    @ColumnInfo(name = "vocationaltraining_when")
    @SerializedName("vocationaltraining_when")
    @Expose
    public String vocationaltrainingWhen;

    @ColumnInfo(name = "vocationaltraining_what")
    @SerializedName("vocationaltraining_what")
    @Expose
    public String vocationaltrainingWhat;

    @ColumnInfo(name = "vocationaltraining_where")
    @SerializedName("vocationaltraining_where")
    @Expose
    public String vocationaltrainingWhere;

    @ColumnInfo(name = "serviceother")
    @SerializedName("serviceother")
    @Expose
    public String serviceother;

    @ColumnInfo(name = "serviceotherwhen")
    @SerializedName("serviceotherwhen")
    @Expose
    public String serviceotherwhen;

    @ColumnInfo(name = "serviceotherwhere")
    @SerializedName("serviceotherwhere")
    @Expose
    public String serviceotherwhere;

    @ColumnInfo(name = "serviceotherwhat")
    @SerializedName("serviceotherwhat")
    @Expose
    public String serviceotherwhat;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    @Expose
    public String createdAt;

    @ColumnInfo(name = "updated_at")
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;

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

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getLivelihood() {
        return livelihood;
    }

    public void setLivelihood(String livelihood) {
        this.livelihood = livelihood;
    }

    public String getLivelihoodother() {
        return livelihoodother;
    }

    public void setLivelihoodother(String livelihoodother) {
        this.livelihoodother = livelihoodother;
    }

    public String getSkilldev() {
        return skilldev;
    }

    public void setSkilldev(String skilldev) {
        this.skilldev = skilldev;
    }

    public String getSkilldevwhen() {
        return skilldevwhen;
    }

    public void setSkilldevwhen(String skilldevwhen) {
        this.skilldevwhen = skilldevwhen;
    }

    public String getSkilldevwhere() {
        return skilldevwhere;
    }

    public void setSkilldevwhere(String skilldevwhere) {
        this.skilldevwhere = skilldevwhere;
    }

    public String getSkilldevwhat() {
        return skilldevwhat;
    }

    public void setSkilldevwhat(String skilldevwhat) {
        this.skilldevwhat = skilldevwhat;
    }

    public String getFinservice() {
        return finservice;
    }

    public void setFinservice(String finservice) {
        this.finservice = finservice;
    }

    public String getFinservicewhen() {
        return finservicewhen;
    }

    public void setFinservicewhen(String finservicewhen) {
        this.finservicewhen = finservicewhen;
    }

    public String getFinservicewhat() {
        return finservicewhat;
    }

    public void setFinservicewhat(String finservicewhat) {
        this.finservicewhat = finservicewhat;
    }

    public String getFinservicewhere() {
        return finservicewhere;
    }

    public void setFinservicewhere(String finservicewhere) {
        this.finservicewhere = finservicewhere;
    }

    public String getSocialsecurity() {
        return socialsecurity;
    }

    public void setSocialsecurity(String socialsecurity) {
        this.socialsecurity = socialsecurity;
    }

    public String getSocialsecuritywhen() {
        return socialsecuritywhen;
    }

    public void setSocialsecuritywhen(String socialsecuritywhen) {
        this.socialsecuritywhen = socialsecuritywhen;
    }

    public String getSocialsecuritywhere() {
        return socialsecuritywhere;
    }

    public void setSocialsecuritywhere(String socialsecuritywhere) {
        this.socialsecuritywhere = socialsecuritywhere;
    }

    public String getSocialsecuritywhat() {
        return socialsecuritywhat;
    }

    public void setSocialsecuritywhat(String socialsecuritywhat) {
        this.socialsecuritywhat = socialsecuritywhat;
    }

    public String getVocationaltraining() {
        return vocationaltraining;
    }

    public void setVocationaltraining(String vocationaltraining) {
        this.vocationaltraining = vocationaltraining;
    }

    public String getVocationaltrainingWhen() {
        return vocationaltrainingWhen;
    }

    public void setVocationaltrainingWhen(String vocationaltrainingWhen) {
        this.vocationaltrainingWhen = vocationaltrainingWhen;
    }

    public String getVocationaltrainingWhat() {
        return vocationaltrainingWhat;
    }

    public void setVocationaltrainingWhat(String vocationaltrainingWhat) {
        this.vocationaltrainingWhat = vocationaltrainingWhat;
    }

    public String getVocationaltrainingWhere() {
        return vocationaltrainingWhere;
    }

    public void setVocationaltrainingWhere(String vocationaltrainingWhere) {
        this.vocationaltrainingWhere = vocationaltrainingWhere;
    }

    public String getServiceother() {
        return serviceother;
    }

    public void setServiceother(String serviceother) {
        this.serviceother = serviceother;
    }

    public String getServiceotherwhen() {
        return serviceotherwhen;
    }

    public void setServiceotherwhen(String serviceotherwhen) {
        this.serviceotherwhen = serviceotherwhen;
    }

    public String getServiceotherwhere() {
        return serviceotherwhere;
    }

    public void setServiceotherwhere(String serviceotherwhere) {
        this.serviceotherwhere = serviceotherwhere;
    }

    public String getServiceotherwhat() {
        return serviceotherwhat;
    }

    public void setServiceotherwhat(String serviceotherwhat) {
        this.serviceotherwhat = serviceotherwhat;
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

    public String getLivelihoodName() {
        return livelihoodName;
    }

    public void setLivelihoodName(String livelihoodName) {
        this.livelihoodName = livelihoodName;
    }

    @Override
    public String toString() {
        return "LivehoodData{" +
                "id='" + id + '\'' +
                ", livelihood='" + livelihood + '\'' +
                ", livelihoodother='" + livelihoodother + '\'' +
                ", skilldev='" + skilldev + '\'' +
                ", skilldevwhen='" + skilldevwhen + '\'' +
                ", benificiary_id='" + benificiary_id + '\'' +
                ", skilldevwhere='" + skilldevwhere + '\'' +
                ", skilldevwhat='" + skilldevwhat + '\'' +
                ", finservice='" + finservice + '\'' +
                ", finservicewhen='" + finservicewhen + '\'' +
                ", finservicewhat='" + finservicewhat + '\'' +
                ", finservicewhere='" + finservicewhere + '\'' +
                ", socialsecurity='" + socialsecurity + '\'' +
                ", socialsecuritywhen='" + socialsecuritywhen + '\'' +
                ", socialsecuritywhere='" + socialsecuritywhere + '\'' +
                ", socialsecuritywhat='" + socialsecuritywhat + '\'' +
                ", vocationaltraining='" + vocationaltraining + '\'' +
                ", vocationaltrainingWhen='" + vocationaltrainingWhen + '\'' +
                ", vocationaltrainingWhat='" + vocationaltrainingWhat + '\'' +
                ", vocationaltrainingWhere='" + vocationaltrainingWhere + '\'' +
                ", serviceother='" + serviceother + '\'' +
                ", serviceotherwhen='" + serviceotherwhen + '\'' +
                ", serviceotherwhere='" + serviceotherwhere + '\'' +
                ", serviceotherwhat='" + serviceotherwhat + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
