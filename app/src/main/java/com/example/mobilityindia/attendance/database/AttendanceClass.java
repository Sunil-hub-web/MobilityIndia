package com.example.mobilityindia.attendance.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "attendance_table")

public class AttendanceClass {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    @NonNull
    private String id;

    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    @Expose
    private String userId;

    @ColumnInfo(name = "date")
    @SerializedName("date")
    @Expose
    private String date;

    @ColumnInfo(name = "checkin_time")
    @SerializedName("checkin_time")
    @Expose
    private String checkinTime;

    @ColumnInfo(name = "clockout_time")
    @SerializedName("clockout_time")
    @Expose
    private String clockoutTime;

    @ColumnInfo(name = "clockin_lat")
    @SerializedName("clockin_lat")
    @Expose
    private String clockinLat;

    @ColumnInfo(name = "clockin_long")
    @SerializedName("clockin_long")
    @Expose
    private String clockinLong;

    @ColumnInfo(name = "clockout_lat")
    @SerializedName("clockout_lat")
    @Expose
    private String clockoutLat;

    @ColumnInfo(name = "clockout_long")
    @SerializedName("clockout_long")
    @Expose
    private String clockoutLong;

    @ColumnInfo(name = "city")
    @SerializedName("city")
    @Expose
    private String city;

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    @Expose
    private String flag;

    @ColumnInfo(name = "statues")
    @SerializedName("statues")
    @Expose
    private String statues;

    @ColumnInfo(name = "statues_date")
    @SerializedName("statues_date")
    @Expose
    private String statues_date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getClockoutTime() {
        return clockoutTime;
    }

    public void setClockoutTime(String clockoutTime) {
        this.clockoutTime = clockoutTime;
    }

    public String getClockinLat() {
        return clockinLat;
    }

    public void setClockinLat(String clockinLat) {
        this.clockinLat = clockinLat;
    }

    public String getClockinLong() {
        return clockinLong;
    }

    public void setClockinLong(String clockinLong) {
        this.clockinLong = clockinLong;
    }

    public String getClockoutLat() {
        return clockoutLat;
    }

    public void setClockoutLat(String clockoutLat) {
        this.clockoutLat = clockoutLat;
    }

    public String getClockoutLong() {
        return clockoutLong;
    }

    public void setClockoutLong(String clockoutLong) {
        this.clockoutLong = clockoutLong;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatues() {
        return statues;
    }

    public void setStatues(String statues) {
        this.statues = statues;
    }

    public String getStatues_date() {
        return statues_date;
    }

    public void setStatues_date(String statues_date) {
        this.statues_date = statues_date;
    }
}
