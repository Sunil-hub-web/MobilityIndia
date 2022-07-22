package com.example.mobilityindia.attendance.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities = {AttendanceClass.class}, version = 2, exportSchema = false)

public abstract class RoomDB extends RoomDatabase {

    //Define DataBase Name
    private static final String DATABASE_NAME = "database";
    //Create database instance
    private static RoomDB database;

    public synchronized static RoomDB getInstance(Context context){

        if(database == null){

            database = Room.databaseBuilder(context.getApplicationContext(),RoomDB.class,DATABASE_NAME).allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        }
        return database;

    }

    public abstract AttendanceDao attendanceDao();

    public LiveData<List<AttendanceClass>> getAttendance() {
        return database.attendanceDao().getAll();
    }

}
