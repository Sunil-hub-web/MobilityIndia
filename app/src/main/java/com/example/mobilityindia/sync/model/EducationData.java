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
import java.util.List;

@TypeConverters(Converters.class)
@Entity(tableName = "education_table")
public class EducationData implements Serializable {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    @NonNull
    public String id;

    @ColumnInfo(name = "benificiary_id")
    @SerializedName("benificiary_id")
    @Expose
    private String benificiaryId;

    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    @Expose
    private String user_id;

    @ColumnInfo(name = "school")
    @SerializedName("school")
    @Expose
    private String school;

    @ColumnInfo(name = "enrollmentno")
    @SerializedName("enrollmentno")
    @Expose
    private String enrollmentno;

    @ColumnInfo(name = "attendingclass")
    @SerializedName("attendingclass")
    @Expose
    private String attendingclass;

    @ColumnInfo(name = "schoolaccess")
    @SerializedName("schoolaccess")
    @Expose
    private String schoolaccess;

    @ColumnInfo(name = "sitting")
    @SerializedName("sitting")
    @Expose
    private String sitting;

    @ColumnInfo(name = "tlm")
    @SerializedName("tlm")
    @Expose
    private String tlm;

    @ColumnInfo(name = "toilet")
    @SerializedName("toilet")
    @Expose
    private String toilet;

    @ColumnInfo(name = "library")
    @SerializedName("library")
    @Expose
    private String library;

    @ColumnInfo(name = "sports")
    @SerializedName("sports")
    @Expose
    private String sports;

    @ColumnInfo(name = "cocurricular")
    @SerializedName("cocurricular")
    @Expose
    private String cocurricular;

    @ColumnInfo(name = "schoolother")
    @SerializedName("schoolother")
    @Expose
    private String schoolother;

    @ColumnInfo(name = "cec")
    @SerializedName("cec")
    @Expose
    private String cec;

    @ColumnInfo(name = "parliament")
    @SerializedName("parliament")
    @Expose
    private String parliament;

    @ColumnInfo(name = "gramsabha")
    @SerializedName("gramsabha")
    @Expose
    private String gramsabha;

    @ColumnInfo(name = "summercamp")
    @SerializedName("summercamp")
    @Expose
    private String summercamp;

    @ColumnInfo(name = "activity_one")
    @SerializedName("activity_one")
    @Expose
    private String activityOne;

    @ColumnInfo(name = "activity_two")
    @SerializedName("activity_two")
    @Expose
    private String activityTwo;

    @ColumnInfo(name = "activity_three")
    @SerializedName("activity_three")
    @Expose
    private String activityThree;

    @ColumnInfo(name = "activity_four")
    @SerializedName("activity_four")
    @Expose
    private String activityFour;

    @ColumnInfo(name = "activity_five")
    @SerializedName("activity_five")
    @Expose
    private String activityFive;

    @ColumnInfo(name = "iep")
    @SerializedName("iep")
    @Expose
    private String iep;

    @ColumnInfo(name = "iepdoc")
    @SerializedName("iepdoc")
    @Expose
    private List<String> iepdoc;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @ColumnInfo(name = "created_by")
    @SerializedName("created_by")
    @Expose
    private String createdBy;

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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBenificiaryId() {
        return benificiaryId;
    }

    public void setBenificiaryId(String benificiaryId) {
        this.benificiaryId = benificiaryId;
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

    public String getActivityOne() {
        return activityOne;
    }

    public void setActivityOne(String activityOne) {
        this.activityOne = activityOne;
    }

    public String getActivityTwo() {
        return activityTwo;
    }

    public void setActivityTwo(String activityTwo) {
        this.activityTwo = activityTwo;
    }

    public String getActivityThree() {
        return activityThree;
    }

    public void setActivityThree(String activityThree) {
        this.activityThree = activityThree;
    }

    public String getActivityFour() {
        return activityFour;
    }

    public void setActivityFour(String activityFour) {
        this.activityFour = activityFour;
    }

    public String getActivityFive() {
        return activityFive;
    }

    public void setActivityFive(String activityFive) {
        this.activityFive = activityFive;
    }

    public String getIep() {
        return iep;
    }

    public void setIep(String iep) {
        this.iep = iep;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<String> getIepdoc() {
        return iepdoc;
    }

    public void setIepdoc(List<String> iepdoc) {
        this.iepdoc = iepdoc;
    }

    @Override
    public String toString() {
        return "EducationData{" +
                "id='" + id + '\'' +
                ", benificiaryId='" + benificiaryId + '\'' +
                ", user_id='" + user_id + '\'' +
                ", school='" + school + '\'' +
                ", enrollmentno='" + enrollmentno + '\'' +
                ", attendingclass='" + attendingclass + '\'' +
                ", schoolaccess='" + schoolaccess + '\'' +
                ", sitting='" + sitting + '\'' +
                ", tlm='" + tlm + '\'' +
                ", toilet='" + toilet + '\'' +
                ", library='" + library + '\'' +
                ", sports='" + sports + '\'' +
                ", cocurricular='" + cocurricular + '\'' +
                ", schoolother='" + schoolother + '\'' +
                ", cec='" + cec + '\'' +
                ", parliament='" + parliament + '\'' +
                ", gramsabha='" + gramsabha + '\'' +
                ", summercamp='" + summercamp + '\'' +
                ", activityOne='" + activityOne + '\'' +
                ", activityTwo='" + activityTwo + '\'' +
                ", activityThree='" + activityThree + '\'' +
                ", activityFour='" + activityFour + '\'' +
                ", activityFive='" + activityFive + '\'' +
                ", iep='" + iep + '\'' +
                ", iepdoc='" + iepdoc + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
