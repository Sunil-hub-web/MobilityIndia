package com.example.mobilityindia.more;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.attendance.database.AttendanceClass;
import com.example.mobilityindia.changepin.view.ChangepiinActivity;
import com.example.mobilityindia.databinding.ActivityMoreBinding;
import com.example.mobilityindia.myprofile.view.MyProfileActivity;
import com.example.mobilityindia.syn1.view.SyncActivityAll;
import com.example.mobilityindia.sync.model.ActionPlanMonth;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.model.EducationData;
import com.example.mobilityindia.sync.model.LivehoodData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.usermanual.UserManual;
import com.example.mobilityindia.workplan.MyJsonArrayRequest;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoreActivity extends AppCompatActivity {
    Dialog dialog;
    SessinoManager sessinoManager;
    private ActivityMoreBinding binding;
    LocalRepo localRepo;
    String userId = "", str_value = "store", str_value2 = "store", str_value3 = "store", str_value4 = "store", str_value5 = "store";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_more);
        setContentView(binding.getRoot());
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

        localRepo = new LocalRepo(MoreActivity.this);
        sessinoManager = new SessinoManager(MoreActivity.this);
        userId = sessinoManager.getUSERID();

        getAttendanceReportLocalDataBase();
        storeActionPlan();
        getBeneficuaryLocal();
        getAllEductionDeatils();
        getAllLivelihoodService();

        binding.myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MoreActivity.this, MyProfileActivity.class));

            }
        });

        binding.linLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean value = showData();
                Log.d("jdbxknsdkj", String.valueOf(value));

                if(showData()){

                  openDialog_Logout();

                }else{

                    openDialog_Logout1();
                }
            }
        });

        binding.linSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MoreActivity.this, SyncActivityAll.class));
            }
        });

        binding.UserManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(MoreActivity.this, UserManual.class));

                String urlString = "https://midev.zbapps.in/appmanual.pdf";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.android.chrome");
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    // Chrome browser presumably not installed so allow user to choose instead
                    intent.setPackage(null);
                    startActivity(intent);
                }

            }
        });

        binding.changePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MoreActivity.this, ChangepiinActivity.class));
            }
        });

    }

    public void openDialog_Logout() {

        dialog = new Dialog(MoreActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logout_logout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        MaterialButton cancelBtn = dialog.findViewById(R.id.cancelBtn);
        MaterialButton saveBtn = dialog.findViewById(R.id.saveBtn);
        TextView showtext = dialog.findViewById(R.id.showtext);

        showtext.setText("Do you want to logout from the app ?");
        saveBtn.setText("yes");


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sessinoManager.logoutUser();


            }
        });

        dialog.show();
    }

    public void openDialog_Logout1() {

        dialog = new Dialog(MoreActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logout_logout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        MaterialButton cancelBtn = dialog.findViewById(R.id.cancelBtn);
        MaterialButton saveBtn = dialog.findViewById(R.id.saveBtn);
        TextView showtext = dialog.findViewById(R.id.showtext);

        showtext.setText("Please use sync option to upload the data and then logout");
        saveBtn.setText("sync page");


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MoreActivity.this, SyncActivityAll.class);
                startActivity(intent);

                dialog.dismiss();


            }
        });

        dialog.show();
    }

    public void getAttendanceReportLocalDataBase() {

        localRepo.getSelectedAttendanceList(userId).observe(MoreActivity.this, new Observer<List<AttendanceClass>>() {
            @Override
            public void onChanged(List<AttendanceClass> attendanceClasses) {

                //localRepo.deleteAllAttendance();

                if (attendanceClasses.size() == 0) {

                } else {

                    String value = "";

                    for (int i = 0; i < attendanceClasses.size(); i++) {

                        String flag = String.valueOf(attendanceClasses.get(i).getFlag());
                        String flag1 = (flag.equals("null")) ? value : flag;

                        if (flag1.equals("update")) {

                            str_value = "update";

                        }
                    }

                    if (str_value.equals("store")) {
                    } else {
                        str_value = "update";
                    }
                }

            }
        });
    }

    public void getAllEductionDeatils() {

        localRepo.getEducationList().observe(MoreActivity.this, new Observer<List<EducationData>>() {
            @Override
            public void onChanged(List<EducationData> eductionDemos) {

                //localRepo.deleteAllAttendance();

                if (eductionDemos.size() == 0) {

                    Toast.makeText(MoreActivity.this, "No Data Avilable in Data Base", Toast.LENGTH_SHORT).show();


                } else {

                    String value = "";

                    for (int i = 0; i < eductionDemos.size(); i++) {

                        String flag = String.valueOf(eductionDemos.get(i).getFlag());
                        String flag1 = (flag.equals("null")) ? value : flag;

                        if (flag1.equals("update")) {

                            str_value2 = "update";

                        }

                    }

                    if (str_value2.equals("store")) {
                    } else {
                        str_value2 = "update";
                    }
                }

            }
        });
    }

    public void getBeneficuaryLocal() {

        localRepo.getBeneList().observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> beneDataa) {

                String value = "";

                if (beneDataa.size() != 0) {

                    for (int i = 0; i < beneDataa.size(); i++) {

                        String flag = String.valueOf(beneDataa.get(i).getFlag());
                        String flag1 = (flag.equals("null")) ? value : flag;

                        if (flag1.equals("update")) {

                            str_value3 = "update";
                        }
                    }

                    if (str_value3.equals("store")) {
                    } else {
                        str_value3 = "update";
                    }

                } else {

                    //Toast.makeText(MoreActivity.this, "Data Sync SuccessFully", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void storeActionPlan() {

        localRepo.getAllActionPlanMonthList().observe(MoreActivity.this, new Observer<List<ActionPlanMonth>>() {
            @Override
            public void onChanged(List<ActionPlanMonth> actionPlanMonths) {

                String value = "";
                if (actionPlanMonths.size() != 0) {

                    for (int i = 0; i < actionPlanMonths.size(); i++) {

                        String flag = String.valueOf(actionPlanMonths.get(i).getFlag());
                        String flag1 = (flag.equals("null")) ? value : flag;

                        if (flag1.equals("update")) {

                            str_value4 = "update";
                        }

                    }

                    if (str_value4.equals("store")) {
                    } else {
                        str_value4 = "update";
                    }

                } else {

                    //Toast.makeText(SyncActivityAll.this, "Data sync Successful", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void getAllLivelihoodService() {

        localRepo.getLiveHoodList().observe(MoreActivity.this, new Observer<List<LivehoodData>>() {
            @Override
            public void onChanged(List<LivehoodData> livehoodData) {

                //localRepo.deleteAllAttendance();

                if (livehoodData.size() == 0) {

                    // Toast.makeText(SyncActivityAll.this, "Data sync Successful", Toast.LENGTH_SHORT).show();

                    //binding.btnProceed.setVisibility(View.VISIBLE);

                } else {

                    String value = "";

                    for (int i = 0; i < livehoodData.size(); i++) {

                        String flag = String.valueOf(livehoodData.get(i).getFlag());
                        String flag1 = (flag.equals("null")) ? value : flag;

                        if (flag1.equals("update")) {

                            str_value5 = "update";
                        }

                    }

                    if (str_value5.equals("store")) {
                    } else {
                        str_value5 = "update";
                    }

                }

            }
        });
    }

    Boolean showData() {

        if (str_value.equals("update")) {

            return false;
        }

        if (str_value2.equals("update")) {

            return false;
        }

        if (str_value3.equals("update")) {

            return false;
        }

        if (str_value4.equals("update")) {

            return false;
        }

        if (str_value5.equals("update")) {

            return false;
        }

        return true;
    }

}