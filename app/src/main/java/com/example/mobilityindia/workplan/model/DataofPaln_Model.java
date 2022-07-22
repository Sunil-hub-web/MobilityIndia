package com.example.mobilityindia.workplan.model;

public class DataofPaln_Model {

    String id,user_id,date_attendance,attendance,first_half,second_half,status;

    public DataofPaln_Model(String id, String user_id, String date_attendance, String attendance,
                            String first_half, String second_half, String status) {
        this.id = id;
        this.user_id = user_id;
        this.date_attendance = date_attendance;
        this.attendance = attendance;
        this.first_half = first_half;
        this.second_half = second_half;
        this.status = status;
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

    public String getDate_attendance() {
        return date_attendance;
    }

    public void setDate_attendance(String date_attendance) {
        this.date_attendance = date_attendance;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getFirst_half() {
        return first_half;
    }

    public void setFirst_half(String first_half) {
        this.first_half = first_half;
    }

    public String getSecond_half() {
        return second_half;
    }

    public void setSecond_half(String second_half) {
        this.second_half = second_half;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
