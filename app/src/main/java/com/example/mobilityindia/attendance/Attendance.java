package com.example.mobilityindia.attendance;

import static com.example.mobilityindia.utils.Utils.getRandomNumber;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.attendance.adapter.ClickInOutAdapter;
import com.example.mobilityindia.attendance.database.AttendanceClass;
import com.example.mobilityindia.attendance.database.RoomDB;
import com.example.mobilityindia.attendance.modelclass.ClickInOut_Model;
import com.example.mobilityindia.databinding.ActivityAttendanceBinding;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.workplan.MyJsonArrayRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Attendance extends AppCompatActivity {

    ActivityAttendanceBinding binding;
    ArrayList<ClickInOut_Model> clockinout;
    List<AttendanceClass> clockinout1;
    ClickInOutAdapter clickInOutAdapter;
    LinearLayoutManager linearLayoutManager;
    SessinoManager sessinoManager;

    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;
    Double latitude, longitude;
    GoogleMap mMap;
    String address_txt = "", userId, dateTime = "", btn_Text = "", todayDate = "", clockin_statues = "",
            clockout_statues = "", statuesDate = "";
    public static final int REQUEST_CODE_PERMISSIONS = 101;

    RoomDB localdataBase;
    JSONArray jsonArray;

    LocalRepo localRepo;

    private GpsTracker gpsTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_attendance);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_attendance);
        setContentView(binding.getRoot());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(Attendance.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(Attendance.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(Attendance.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(Attendance.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));

        binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Initialize database
        localdataBase = RoomDB.getInstance(this);
        localRepo = new LocalRepo(Attendance.this);

        //localRepo.deleteAllAttendance();


        sessinoManager = new SessinoManager(Attendance.this);
        userId = sessinoManager.getUSERID();

        /*dateTime = sessinoManager.getDATETIME();
        Log.d("hdgjhbsah", sessinoManager.getDATETIME());
        Log.d("hdksd", dateTime);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdate.format(c.getTime());

        todayDate = sessinoManager.getTodayDate();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        todayDate = sh.getString("todayDate", null);
        todayDate = String.valueOf(todayDate);*/

       /* if(todayDate.equals("") || todayDate.equals("null")){

                if (dateTime.equals("DEFAULT")) {

                    binding.clockInBtn.setEnabled(true);

                    binding.clockInBtn.setText("Clock In");
                    binding.clockInBtn.setEnabled(true);
                    binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.dimbluecolor));

                } else {
                    //binding.ViewDate.setText(sessinoManager.getDATETIME());
                    binding.clockInBtn.setText("Clock Out");
                    binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.Red));
                }

        }else{

             if(todayDate.equals(date)){

                binding.clockInBtn.setEnabled(false);
                binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.graycolor));

            }

        }*/


        localRepo.getSelectedAttendanceList(userId).observe(Attendance.this, attendanceClasses -> {

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdate.format(c.getTime());

            if (attendanceClasses.isEmpty()) {

                Toast.makeText(Attendance.this, "No Data Is Avilable", Toast.LENGTH_SHORT).show();

            } else {


                clockinout1 = new ArrayList<>();
                clockinout1.clear();

                for (int i = attendanceClasses.size() - 1; i >= 0; i--) {

                    String statues = String.valueOf(attendanceClasses.get(i).getStatues());
                    String statues_date = String.valueOf(attendanceClasses.get(i).getDate());
                    String clickin = String.valueOf(attendanceClasses.get(i).getCheckinTime());
                    String clockout = String.valueOf(attendanceClasses.get(i).getClockoutTime());

                    if (statues_date.equals(date)) {

                        if (clickin.equals("") || clickin.equals("null")) {

                            clockin_statues = "clockin";

                            binding.clockInBtn.setText("Clock In");
                            binding.clockInBtn.setEnabled(true);
                            binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.dimbluecolor));

                        } else if (clockout.equals("") || clockout.equals("null")) {

                            clockout_statues = "clockout";
                            clockin_statues = "";

                            binding.clockInBtn.setText("Clock Out");
                            binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.Red));

                        } else if (statues_date.equals(date) || statues_date.equals(date)) {

                            statuesDate = "todaydate";
                            clockout_statues = "";

                            binding.clockInBtn.setEnabled(false);
                            binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.graycolor));

                        } else {

                            statuesDate = "";

                            binding.clockInBtn.setText("Clock In");
                            binding.clockInBtn.setEnabled(true);
                            binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.dimbluecolor));

                        }

                    } else {

                        if (clockin_statues.equals("clockin")) {

                            binding.clockInBtn.setText("Clock In");
                            binding.clockInBtn.setEnabled(true);
                            binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.dimbluecolor));


                        } else if (clockout_statues.equals("clockout")) {

                            binding.clockInBtn.setText("Clock Out");
                            binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.Red));

                        } else if (statuesDate.equals("todaydate")) {

                            binding.clockInBtn.setEnabled(false);
                            binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.graycolor));

                        } else {

                            binding.clockInBtn.setText("Clock In");
                            binding.clockInBtn.setEnabled(true);
                            binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.dimbluecolor));
                        }

                    }


                   /* if(statues.equals("") || statues.equals("null")){

                        binding.clockInBtn.setText("Clock In");
                        binding.clockInBtn.setEnabled(true);
                        binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.dimbluecolor));

                    }
                    else if(statues.equals("Clock Out")){

                        binding.clockInBtn.setText("Clock Out");
                        binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.Red));

                    }else{
                        if(statues.equals("Clock In")){

                            if(statues_date.equals(date)){

                                binding.clockInBtn.setEnabled(false);
                                binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.graycolor));
                            }
                        }

                    }*/

                    clockinout1.add(attendanceClasses.get(i));
                }

                linearLayoutManager = new LinearLayoutManager(Attendance.this, LinearLayoutManager.VERTICAL, false);
                clickInOutAdapter = new ClickInOutAdapter(clockinout1, Attendance.this);
                binding.showClickinDetails.setLayoutManager(linearLayoutManager);
                binding.showClickinDetails.setHasFixedSize(true);
                binding.showClickinDetails.setAdapter(clickInOutAdapter);

            }
        });


        //Store database value in dataList
        /*clockinout1 = localdataBase.attendanceDao().getAll();

        Log.d("kkkkh",clockinout1.toString());

        if(clockinout1.size() != 0){

            linearLayoutManager = new LinearLayoutManager(Attendance.this, LinearLayoutManager.VERTICAL, false);
            clickInOutAdapter = new ClickInOutAdapter(clockinout1, Attendance.this);
            binding.showClickinDetails.setLayoutManager(linearLayoutManager);
            binding.showClickinDetails.setHasFixedSize(true);
            binding.showClickinDetails.setAdapter(clickInOutAdapter);
        }*/

        binding.clockInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa");
                String Datetime = sdf.format(c.getTime());

                SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdate.format(c.getTime());

                SimpleDateFormat stime = new SimpleDateFormat("hh:mm:ss aa");
                String time = stime.format(c.getTime());

                if (binding.clockInBtn.getText().toString().trim().equals("Clock In")) {

                    gpsTracker = new GpsTracker(Attendance.this);

                    if (gpsTracker.canGetLocation()) {

                        double latitude = gpsTracker.getLatitude();
                        double longitude = gpsTracker.getLongitude();
                        //binding.ViewDate.setText(date + "  " + time);

                        String str_latitude = String.valueOf(latitude);
                        String str_longitude = String.valueOf(longitude);

                        String randoNoStr = getRandomNumber();
                        AttendanceClass attendanceClass = new AttendanceClass();
                        attendanceClass.setId(randoNoStr);
                        attendanceClass.setUserId(userId);
                        attendanceClass.setDate(date);
                        attendanceClass.setCheckinTime(time);
                        attendanceClass.setClockinLat(str_latitude);
                        attendanceClass.setClockinLong(str_longitude);
                        attendanceClass.setCity("");
                        attendanceClass.setFlag("update");
                        attendanceClass.setStatues("Clock Out");
                        attendanceClass.setStatues_date("");

                        //localdataBase.attendanceDao().insert(attendanceClass);

                        localRepo.deleteAttendance(attendanceClass);
                        localRepo.insertAttandance(attendanceClass);

                        Log.d("jdjbkjndski", date + time + latitude + longitude + address_txt);

                        Toast.makeText(Attendance.this, "Data Save Success", Toast.LENGTH_SHORT).show();

                        binding.clockInBtn.setText("Clock Out");
                        binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.redcolor));

                        dateTime = date + "  " + time;
                        sessinoManager.setDATETIME(dateTime);

                    } else {
                        gpsTracker.showSettingsAlert();
                    }
                } else {

                    // double latitude = gpsTracker.getLatitude();
                    // double longitude = gpsTracker.getLongitude();

                    String str_latitude = String.valueOf(latitude);
                    String str_longitude = String.valueOf(longitude);

                    AttendanceClass attendanceClass = new AttendanceClass();
                    //binding.ViewDate.setText(date + "  " + time);
                    //localdataBase.attendanceDao().update(time, latitude, longitude, address_txt, date);
                    localRepo.deleteAttendance(attendanceClass);
                    localRepo.updateAttendance(time, "", "", address_txt, "update", "Clock In", date, date);


                    Toast.makeText(Attendance.this, "Data Save Success", Toast.LENGTH_SHORT).show();

                    Log.d("jdjbkjndski", date + time + latitude + longitude + address_txt);

                    sessinoManager.setDATETIME("DEFAULT");
                    dateTime = "DEFAULT";

                    binding.clockInBtn.setText("Submited");
                    binding.clockInBtn.setBackgroundTintList(Attendance.this.getResources().getColorStateList(R.color.graycolor));
                    // binding.clockInBtn.setVisibility(View.GONE);

                    binding.clockInBtn.setEnabled(false);

                    todayDate = date;

                    sessinoManager.setTodayDate(todayDate);

                    Log.d("hgffrd", todayDate);

                    //uploaddata();

                }

            }
        });
    }

    private void getLocation(String ClockIn) {

        if (ActivityCompat.checkSelfPermission(Attendance.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Attendance.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Attendance.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        } else {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {

                    //initialize location
                    Location location = task.getResult();

                    if (location != null) {

                        try {
                            //initialize geocoder
                            Geocoder geocoder = new Geocoder(Attendance.this, Locale.getDefault());

                            //initialize AddressList
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                            //set Latitude On Text View
                            latitude = addresses.get(0).getLatitude();

                            //set Longitude On Text View
                            longitude = addresses.get(0).getLongitude();

                            //set address On Text View
                            address_txt = addresses.get(0).getLocality() + "," + addresses.get(0).getSubLocality();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
        }

    }

    private void requestLocationPermission() {

        boolean foreground = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if (foreground) {
            boolean background = ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED;

            if (background) {
                handleLocationUpdates();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, REQUEST_CODE_PERMISSIONS);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION}, REQUEST_CODE_PERMISSIONS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {

            boolean foreground = false, background = false;

            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equalsIgnoreCase(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    //foreground permission allowed
                    if (grantResults[i] >= 0) {
                        foreground = true;
                        //Toast.makeText(getApplicationContext(), "Foreground location permission allowed", Toast.LENGTH_SHORT).show();
                        continue;
                    } else {
                        //Toast.makeText(getApplicationContext(), "Location Permission denied", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                if (permissions[i].equalsIgnoreCase(Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                    if (grantResults[i] >= 0) {
                        foreground = true;
                        background = true;
                        //Toast.makeText(getApplicationContext(), "Background location location permission allowed", Toast.LENGTH_SHORT).show();
                    } else {
                        //Toast.makeText(getApplicationContext(), "Background location location permission denied", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            if (foreground) {
                if (background) {
                    handleLocationUpdates();
                } else {
                    handleForegroundLocationUpdates();
                }
            }
        }
    }

    private void handleLocationUpdates() {
        //foreground and background
        //Toast.makeText(getApplicationContext(), "Start Foreground and Background Location Updates", Toast.LENGTH_SHORT).show();
    }

    private void handleForegroundLocationUpdates() {
        //handleForeground Location Updates
        //Toast.makeText(getApplicationContext(), "Start foreground location updates", Toast.LENGTH_SHORT).show();
    }

    public void locationPermission() {

        final androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);

        builder.setMessage("Enable Your GPS Location").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final androidx.appcompat.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void clockInOut(String userId) {

        ProgressDialog progressDialog = new ProgressDialog(Attendance.this);
        progressDialog.setMessage("Lodaing Details...");
        progressDialog.show();

        String url = "https://midev.zbapps.in/api/clockInList";

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("user_id", userId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {
                    String status = response.getString("status");
                    String message = response.getString("message");
                    String data = response.getString("data");

                    clockinout1 = new ArrayList<>();
                    clockinout1.clear();

                    if (status.equals("true")) {


                        JSONArray jsonArray_data = new JSONArray(data);

                        if (jsonArray_data.length() != 0) {

                            for (int i = 0; i < jsonArray_data.length(); i++) {

                                JSONObject jsonObject1_data = jsonArray_data.getJSONObject(i);

                                String id = jsonObject1_data.getString("id");
                                String user_id = jsonObject1_data.getString("user_id");
                                String date = jsonObject1_data.getString("date");
                                String checkin_time = jsonObject1_data.getString("checkin_time");
                                String clockout_time = jsonObject1_data.getString("clockout_time");

                              /*  ClickInOut_Model clickInOut_model = new ClickInOut_Model(
                                        id, user_id, date, checkin_time, clockout_time
                                );*/

                                AttendanceClass attendanceClass = new AttendanceClass();


                                clockinout1.add(attendanceClass);/*  attendanceClass.setDate_attendance(date);
                                attendanceClass.setClockin(checkin_time);
                                attendanceClass.setClockout(clockout_time);
                                attendanceClass.setClockin_lat(dclockin_lat);
                                attendanceClass.setClockin_long(dclockin_long);
                                attendanceClass.setClockout_lat(dclockout_lat);
                                attendanceClass.setClockout_long(dclockout_long);
                                attendanceClass.setCity(city);*/
                            }

                            linearLayoutManager = new LinearLayoutManager(Attendance.this, LinearLayoutManager.VERTICAL, false);
                            clickInOutAdapter = new ClickInOutAdapter(clockinout1, Attendance.this);
                            binding.showClickinDetails.setLayoutManager(linearLayoutManager);
                            binding.showClickinDetails.setHasFixedSize(true);
                            binding.showClickinDetails.setAdapter(clickInOutAdapter);

                        } else {

                            Toast.makeText(Attendance.this, "No Data Is Avilable In The List", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(Attendance.this, message, Toast.LENGTH_SHORT).show();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
                Toast.makeText(Attendance.this, "api Server error", Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                String creds = String.format("%s:%s", "mobility", "admin@123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);

                return params;
            }

        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(Attendance.this);
        requestQueue.add(jsonObjectRequest);
    }

    public void storeDataInDataBase(JSONArray jsonArray) {

        ProgressDialog progressDialog = new ProgressDialog(Attendance.this);
        progressDialog.setMessage("Lodaing Details...");
        progressDialog.show();

        String url = "https://midev.zbapps.in/api/uploadClockin";

        MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(Request.Method.POST, url, jsonArray, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressDialog.dismiss();

                try {

                    String status = response.getString("status");
                    String message = response.getString("message");

                    if (status.equals("true")) {

                        Toast.makeText(Attendance.this, message, Toast.LENGTH_SHORT).show();

                        clockInOut(userId);

                    } else {

                        Toast.makeText(Attendance.this, message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();

                Toast.makeText(Attendance.this, "" + error, Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                String creds = String.format("%s:%s", "mobility", "admin@123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);

                return params;
            }
        };
        myJsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(Attendance.this);
        requestQueue.add(myJsonArrayRequest);

    }

    public void uploaddata() {

        localRepo.getAttendance().observe(Attendance.this, new Observer<List<AttendanceClass>>() {
            @Override
            public void onChanged(List<AttendanceClass> attendanceClasses) {

                for (int i = 0; i < attendanceClasses.size(); i++) {

                   /* Double clockinlang = attendanceClasses.get(i).getClockin_long();
                    Double getClockinlat = attendanceClasses.get(i).getClockin_lat();
                    Double clockout_long = attendanceClasses.get(i).getClockout_long();
                    Double clockout_lat = attendanceClasses.get(i).getClockout_lat();
                    String clocktime = attendanceClasses.get(i).getClockin();
                    String clockouttime = attendanceClasses.get(i).getClockout();
                    String dateattendance = attendanceClasses.get(i).getDate_attendance();
                    String city = attendanceClasses.get(i).getCity();*/


                    JSONObject jsonObject = new JSONObject();

                    /* jsonObject.put("user_id", userId);
                     jsonObject.put("date", dateattendance);
                     jsonObject.put("checkin_time", clocktime);
                     jsonObject.put("clockout_time", clockouttime);
                     jsonObject.put("clockin_lat", getClockinlat);
                     jsonObject.put("clockin_long", clockinlang);
                     jsonObject.put("clockout_lat", clockout_lat);
                     jsonObject.put("clockout_long", clockout_long);
                     jsonObject.put("city", city);*/

                    jsonArray = new JSONArray();
                    jsonArray.put(jsonObject);


                }
                Log.d("gjusgjhbs", jsonArray.toString());

                //storeDataInDataBase(jsonArray);

                linearLayoutManager = new LinearLayoutManager(Attendance.this, LinearLayoutManager.VERTICAL, false);
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);
                clickInOutAdapter = new ClickInOutAdapter(clockinout1, Attendance.this);
                binding.showClickinDetails.setLayoutManager(linearLayoutManager);
                binding.showClickinDetails.setHasFixedSize(true);
                binding.showClickinDetails.setAdapter(clickInOutAdapter);

            }
        });
    }

    public static String getRandomNumber() {
        int max = 999;
        int min = 100;
        String result = "";
        Random random = new Random();
        result = String.valueOf(random.nextInt((max - min) + 1) + min);

        return result;
    }

    Boolean dataStore() {

        if (clockin_statues.equals("clockin")) {

            return false;
        }

        if (clockout_statues.equals("clockout")) {

            return false;
        }
        if (statuesDate.equals("date")) {

        }

        return true;
    }
}