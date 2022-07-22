package com.example.mobilityindia.attendance.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "attendance_table")

public class AttendanceClass {

    @PrimaryKey(autoGenerate = true)
    @Expose
    @NonNull
    public int id;

    @ColumnInfo(name = "date_attendance")
    @SerializedName("date_attendance")
    @Expose
    public String date_attendance;

    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    @Expose
    public String user_id;

    @ColumnInfo(name = "clockin")
    @SerializedName("clockin")
    @Expose
    public String clockin;

    @ColumnInfo(name = "clockout")
    @SerializedName("clockout")
    @Expose
    public String clockout;

    @ColumnInfo(name = "clockin_lat")
    @SerializedName("clockin_lat")
    @Expose
    public Double clockin_lat;

    @ColumnInfo(name = "clockin_long")
    @SerializedName("clockin_long")
    @Expose
    public Double clockin_long;

    @ColumnInfo(name = "clockout_lat")
    @SerializedName("clockout_lat")
    @Expose
    public Double clockout_lat;

    @ColumnInfo(name = "clockout_long")
    @SerializedName("clockout_long")
    @Expose
    public Double clockout_long;

    @ColumnInfo(name = "city")
    @SerializedName("city")
    @Expose
    public String city;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getDate_attendance() {
        return date_attendance;
    }

    public void setDate_attendance(String date_attendance) {
        this.date_attendance = date_attendance;
    }

    public String getClockin() {
        return clockin;
    }

    public void setClockin(String clockin) {
        this.clockin = clockin;
    }

    public String getClockout() {
        return clockout;
    }

    public void setClockout(String clockout) {
        this.clockout = clockout;
    }

    public Double getClockin_lat() {
        return clockin_lat;
    }

    public void setClockin_lat(Double clockin_lat) {
        this.clockin_lat = clockin_lat;
    }

    public Double getClockin_long() {
        return clockin_long;
    }

    public void setClockin_long(Double clockin_long) {
        this.clockin_long = clockin_long;
    }

    public Double getClockout_lat() {
        return clockout_lat;
    }

    public void setClockout_lat(Double clockout_lat) {
        this.clockout_lat = clockout_lat;
    }

    public Double getClockout_long() {
        return clockout_long;
    }

    public void setClockout_long(Double clockout_long) {
        this.clockout_long = clockout_long;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "AttendanceClass{" +
                "id=" + id +
                ", date_attendance='" + date_attendance + '\'' +
                ", clockin='" + clockin + '\'' +
                ", clockout='" + clockout + '\'' +
                ", clockin_lat=" + clockin_lat +
                ", clockin_long=" + clockin_long +
                ", clockout_lat=" + clockout_lat +
                ", clockout_long=" + clockout_long +
                ", city='" + city + '\'' +
                '}';
    }
}
