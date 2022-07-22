package com.example.mobilityindia.attendance.modelclass;

public class ClickInOut_Model {

    String id,user_id,date,checkin_time,clockout_time;

    public ClickInOut_Model(String id, String user_id, String date, String checkin_time, String clockout_time) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.checkin_time = checkin_time;
        this.clockout_time = clockout_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCheckin_time() {
        return checkin_time;
    }

    public void setCheckin_time(String checkin_time) {
        this.checkin_time = checkin_time;
    }

    public String getClockout_time() {
        return clockout_time;
    }

    public void setClockout_time(String clockout_time) {
        this.clockout_time = clockout_time;
    }
}
