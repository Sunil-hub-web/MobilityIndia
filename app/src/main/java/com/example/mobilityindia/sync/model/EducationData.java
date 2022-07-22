package com.example.mobilityindia.sync.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "education_table")
public class EducationData implements Serializable {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    @NonNull
    public String id;


    @ColumnInfo(name = "school")
    @SerializedName("school")
    @Expose
    public String school;

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

    @ColumnInfo(name = "enrollmentno")
    @SerializedName("enrollmentno")
    @Expose
    public String enrollmentno;

    @ColumnInfo(name = "attendingclass")
    @SerializedName("attendingclass")
    @Expose
    public String attendingclass;

    @ColumnInfo(name = "schoolaccess")
    @SerializedName("schoolaccess")
    @Expose
    public String schoolaccess;

    @ColumnInfo(name = "sitting")
    @SerializedName("sitting")
    @Expose
    public String sitting;

    @ColumnInfo(name = "tlm")
    @SerializedName("tlm")
    @Expose
    public String tlm;

    @ColumnInfo(name = "toilet")
    @SerializedName("toilet")
    @Expose
    public String toilet;

    @ColumnInfo(name = "library")
    @SerializedName("library")
    @Expose
    public String library;

    @ColumnInfo(name = "sports")
    @SerializedName("sports")
    @Expose
    public String sports;

    @ColumnInfo(name = "cocurricular")
    @SerializedName("cocurricular")
    @Expose
    public String cocurricular;

    @ColumnInfo(name = "schoolother")
    @SerializedName("schoolother")
    @Expose
    public String schoolother;


    @ColumnInfo(name = "cec")
    @SerializedName("cec")
    @Expose
    public String cec;

    @ColumnInfo(name = "parliament")
    @SerializedName("parliament")
    @Expose
    public String parliament;

    @ColumnInfo(name = "gramsabha")
    @SerializedName("gramsabha")
    @Expose
    public String gramsabha;

    @ColumnInfo(name = "summercamp")
    @SerializedName("summercamp")
    @Expose
    public String summercamp;

    @ColumnInfo(name = "vocational")
    @SerializedName("vocational")
    @Expose
    public String vocational;

    @ColumnInfo(name = "vocationaldetail")
    @SerializedName("vocationaldetail")
    @Expose
    public String vocationaldetail;

    @ColumnInfo(name = "iep")
    @SerializedName("iep")
    @Expose
    public String iep;

    @ColumnInfo(name = "iepdoc")
    @SerializedName("iepdoc")
    @Expose
    public String iepdoc;

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEnrollmentno() {
        return enrollmentno;
    }

    public void setEnrollmentno(String enrollmentno) {
        this.enrollmentno = enrollmentno;
    }

    public String getAttendingclass() {
        return attendingclass;
    }

    public void setAttendingclass(String attendingclass) {
        this.attendingclass = attendingclass;
    }

    public String getSchoolaccess() {
        return schoolaccess;
    }

    public void setSchoolaccess(String schoolaccess) {
        this.schoolaccess = schoolaccess;
    }

    public String getSitting() {
        return sitting;
    }

    public void setSitting(String sitting) {
        this.sitting = sitting;
    }

    public String getTlm() {
        return tlm;
    }

    public void setTlm(String tlm) {
        this.tlm = tlm;
    }

    public String getToilet() {
        return toilet;
    }

    public void setToilet(String toilet) {
        this.toilet = toilet;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public String getCocurricular() {
        return cocurricular;
    }

    public void setCocurricular(String cocurricular) {
        this.cocurricular = cocurricular;
    }

    public String getSchoolother() {
        return schoolother;
    }

    public void setSchoolother(String schoolother) {
        this.schoolother = schoolother;
    }

    public String getCec() {
        return cec;
    }

    public void setCec(String cec) {
        this.cec = cec;
    }

    public String getParliament() {
        return parliament;
    }

    public void setParliament(String parliament) {
        this.parliament = parliament;
    }

    public String getGramsabha() {
        return gramsabha;
    }

    public void setGramsabha(String gramsabha) {
        this.gramsabha = gramsabha;
    }

    public String getSummercamp() {
        return summercamp;
    }

    public void setSummercamp(String summercamp) {
        this.summercamp = summercamp;
    }

    public String getVocational() {
        return vocational;
    }

    public void setVocational(String vocational) {
        this.vocational = vocational;
    }

    public String getVocationaldetail() {
        return vocationaldetail;
    }

    public void setVocationaldetail(String vocationaldetail) {
        this.vocationaldetail = vocationaldetail;
    }

    public String getIep() {
        return iep;
    }

    public void setIep(String iep) {
        this.iep = iep;
    }

    public String getIepdoc() {
        return iepdoc;
    }

    public void setIepdoc(String iepdoc) {
        this.iepdoc = iepdoc;
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
