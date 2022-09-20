package com.example.mobilityindia.workplan.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.sync.model.ActivityReportAttendanceData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.workplan.MonthWorkPlanActivity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class WorkMonthPalnAdapter extends RecyclerView.Adapter<WorkMonthPalnAdapter.ViewHolder> {

    Context context;
    List<ActivityReportAttendanceData> datepaln;
    TextView first_half, second_half_txt, Firsthalf, Secondhalf;
    LocalRepo localRepo;
    ActivityReportAttendanceData date_plan;
    ArrayList<String> arrayList;
    AutoCompleteTextView attandance_edt;
    MaterialButton saveBtn, cancelBtn;
    String monthyear;

    SessinoManager sessinoManager;

    public WorkMonthPalnAdapter(List<ActivityReportAttendanceData> dateofplan, MonthWorkPlanActivity monthWorkPlanActivity, ArrayList<String> arrayList, String monthyear) {

        this.context = monthWorkPlanActivity;
        this.datepaln = dateofplan;
        this.arrayList = arrayList;
        this.monthyear = monthyear;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_row_layout, parent, false);

        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        sessinoManager = new SessinoManager(context);

        localRepo = new LocalRepo(context);

        date_plan = datepaln.get(position);
        holder.row_txt.setText(date_plan.getDateAttendance());

        //String attendance = date_plan.getId();

        Log.d("hsgjhabsh", arrayList.toString());

        //int size = arrayList.size();

        String attendance = date_plan.getAttendance();
        attendance = String.valueOf(attendance);

        if (attendance.equals("")) {

            holder.row_txt.setBackgroundTintList(context.getResources().getColorStateList(R.color.purple_500));

        } else if (attendance.equals("null")) {

            holder.row_txt.setBackgroundTintList(context.getResources().getColorStateList(R.color.purple_500));

        } else {

            holder.row_txt.setBackgroundTintList(context.getResources().getColorStateList(R.color.Green));
        }

       /* if (attendance.equals("null")) {

            holder.row_txt.setBackgroundTintList(context.getResources().getColorStateList(R.color.purple_500));

        } else {

            holder.row_txt.setBackgroundTintList(context.getResources().getColorStateList(R.color.Green));
        }*/


    }

    private void OpenDialouge(String id, String userId, String dateAttendance, String status, String monthYear, String createdAt,
                              String updatedAt, String submitedAt, String submitedBy, String name, String usertypeid) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.attendance_dialoug);
        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        attandance_edt = dialog.findViewById(R.id.attandance_edt);
        first_half = dialog.findViewById(R.id.first_half);
        second_half_txt = dialog.findViewById(R.id.second_half_txt);
        saveBtn = dialog.findViewById(R.id.saveBtn);
        cancelBtn = dialog.findViewById(R.id.cancelBtn);
        Firsthalf = dialog.findViewById(R.id.Firsthalf);
        Secondhalf = dialog.findViewById(R.id.Secondhalf);

        // saveBtn.setFocusable(false);

        //saveBtn.setVisibility(View.VISIBLE);

        String[] genderName = {"present", "HDL", "CL", "SL", "AL", "holiday"};

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderName);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        attandance_edt.setAdapter(adapter2);

        String monthStatues = sessinoManager.getMONTHSTATUS();

        attandance_edt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = attandance_edt.getAdapter().getItem(position).toString();
                ;

                if (item.equalsIgnoreCase("present")) {

                    first_half.setVisibility(View.VISIBLE);
                    second_half_txt.setVisibility(View.VISIBLE);
                    Secondhalf.setVisibility(View.VISIBLE);
                    Firsthalf.setVisibility(View.VISIBLE);

                } else if (item.equalsIgnoreCase("HDL")) {

                    first_half.setVisibility(View.VISIBLE);
                    second_half_txt.setVisibility(View.VISIBLE);
                    Secondhalf.setVisibility(View.VISIBLE);
                    Firsthalf.setVisibility(View.VISIBLE);

                } else if (item.equalsIgnoreCase("CL")) {

                    first_half.setVisibility(View.GONE);
                    second_half_txt.setVisibility(View.GONE);
                    Secondhalf.setVisibility(View.GONE);
                    Firsthalf.setVisibility(View.GONE);
                    //saveBtn.setVisibility(View.GONE);

                } else if (item.equalsIgnoreCase("SL")) {

                    first_half.setVisibility(View.GONE);
                    second_half_txt.setVisibility(View.GONE);
                    Secondhalf.setVisibility(View.GONE);
                    Firsthalf.setVisibility(View.GONE);
                    //saveBtn.setVisibility(View.GONE);

                } else if (item.equalsIgnoreCase("holiday")) {

                    first_half.setVisibility(View.GONE);
                    second_half_txt.setVisibility(View.GONE);
                    Secondhalf.setVisibility(View.GONE);
                    Firsthalf.setVisibility(View.GONE);
                    //saveBtn.setVisibility(View.GONE);

                } else if (item.equalsIgnoreCase("AL")) {

                    first_half.setVisibility(View.GONE);
                    second_half_txt.setVisibility(View.GONE);
                    Secondhalf.setVisibility(View.GONE);
                    Firsthalf.setVisibility(View.GONE);
                    //saveBtn.setVisibility(View.GONE);

                }
            }
        });

        //showCalender1();

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String attendance = attandance_edt.getText().toString();

                if (attendance.equals("present")) {

                    if (first_half.getText().toString().equalsIgnoreCase("")) {
                        first_half.setError("Please enter your First half attendance");
                    } else if (second_half_txt.getText().toString().equalsIgnoreCase("")) {
                        second_half_txt.setError("Please enter your second half attendance");
                    } else if (attandance_edt.getText().toString().equals("Attendance")) {
                        Toast.makeText(context, "Please select Attendance", Toast.LENGTH_SHORT).show();
                    } else if (attandance_edt.getText().toString().equals("")) {
                        Toast.makeText(context, "Please select Attendance", Toast.LENGTH_SHORT).show();
                    } else {

                        Log.d("gcgcg", attendance);

                        String firstHalf = first_half.getText().toString();
                        String secondHalf = second_half_txt.getText().toString();

                        ActivityReportAttendanceData attedanceData = new ActivityReportAttendanceData();

                        attedanceData.setId(id);
                        attedanceData.setUserId(userId);
                        attedanceData.setDateAttendance(dateAttendance);
                        attedanceData.setAttendance(attendance);
                        attedanceData.setFirstHalf(firstHalf);
                        attedanceData.setSecondHalf(secondHalf);
                        attedanceData.setMonthYear(monthYear);
                        attedanceData.setCreatedAt(createdAt);
                        attedanceData.setUpdatedAt(updatedAt);
                        attedanceData.setSubmittedAt(submitedAt);
                        attedanceData.setSubmittedBy(submitedBy);
                        attedanceData.setName(name);
                        attedanceData.setStatus(status);
                        attedanceData.setUserTypeId(usertypeid);
                        attedanceData.setFlag("update");

                        //localRepo.deleteActivityRep(attedanceData);
                        localRepo.updateActivityRep(attedanceData);

                        Toast.makeText(context, "Data Saved in Locally", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                        Intent intent = new Intent(context, MonthWorkPlanActivity.class);
                        ((Activity) context).finish();
                        ((Activity) context).overridePendingTransition(0, 0);
                        context.startActivity(intent);
                        ((Activity) context).overridePendingTransition(0, 0);

                    }
                } else if (attendance.equals("HDL")) {

                    if (first_half.getText().toString().equalsIgnoreCase("")) {
                        first_half.setError("Please enter your First half attendance");
                    } else if (attandance_edt.getText().toString().equals("")) {
                        Toast.makeText(context, "Please select Attendance", Toast.LENGTH_SHORT).show();
                    } else {

                        Log.d("gcgcg", attendance);

                        String firstHalf = first_half.getText().toString();
                        String secondHalf = second_half_txt.getText().toString();

                        ActivityReportAttendanceData attedanceData = new ActivityReportAttendanceData();

                        attedanceData.setId(id);
                        attedanceData.setUserId(userId);
                        attedanceData.setDateAttendance(dateAttendance);
                        attedanceData.setAttendance(attendance);
                        attedanceData.setFirstHalf(firstHalf);
                        attedanceData.setSecondHalf(secondHalf);
                        attedanceData.setMonthYear(monthYear);
                        attedanceData.setCreatedAt(createdAt);
                        attedanceData.setUpdatedAt(updatedAt);
                        attedanceData.setSubmittedAt(submitedAt);
                        attedanceData.setSubmittedBy(submitedBy);
                        attedanceData.setName(name);
                        attedanceData.setStatus(status);
                        attedanceData.setUserTypeId(usertypeid);
                        attedanceData.setFlag("update");

                        //localRepo.deleteActivityRep(attedanceData);
                        localRepo.updateActivityRep(attedanceData);

                        Toast.makeText(context, "Data Saved in Locally", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                        Intent intent = new Intent(context, MonthWorkPlanActivity.class);
                        ((Activity) context).finish();
                        ((Activity) context).overridePendingTransition(0, 0);
                        context.startActivity(intent);
                        ((Activity) context).overridePendingTransition(0, 0);

                    }
                } else {

                    if (attandance_edt.getText().toString().equals("Attendance")) {
                        Toast.makeText(context, "Please select Attendance", Toast.LENGTH_SHORT).show();
                    } else if (attandance_edt.getText().toString().equals("")) {
                        Toast.makeText(context, "Please select Attendance", Toast.LENGTH_SHORT).show();
                    } else {

                        ActivityReportAttendanceData attedanceData = new ActivityReportAttendanceData();

                        attedanceData.setId(id);
                        attedanceData.setUserId(userId);
                        attedanceData.setDateAttendance(dateAttendance);
                        attedanceData.setAttendance(attendance);
                        attedanceData.setFirstHalf("");
                        attedanceData.setSecondHalf("");
                        attedanceData.setMonthYear(monthYear);
                        attedanceData.setCreatedAt(createdAt);
                        attedanceData.setUpdatedAt(updatedAt);
                        attedanceData.setSubmittedAt(submitedAt);
                        attedanceData.setSubmittedBy(submitedBy);
                        attedanceData.setName(name);
                        attedanceData.setStatus(status);
                        attedanceData.setUserTypeId(usertypeid);
                        attedanceData.setFlag("update");

                        //localRepo.deleteActivityRep(attedanceData);
                        localRepo.updateActivityRep(attedanceData);

                        Toast.makeText(context, "Data Saved in Locally", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                        Intent intent = new Intent(context, MonthWorkPlanActivity.class);
                        ((Activity) context).finish();
                        ((Activity) context).overridePendingTransition(0, 0);
                        context.startActivity(intent);
                        ((Activity) context).overridePendingTransition(0, 0);

                    }
                }


                //saveBtn.setFocusable(false);
            }
        });

        attandance_edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String statues = sessinoManager.getMONTHSTATUS();

                if (statues.equalsIgnoreCase("1")) {
                } else if (statues.equalsIgnoreCase("2")) {
                } else {

                    attandance_edt.showDropDown();
                }

            }
        });

        attandance_edt.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                attandance_edt.showDropDown();

               /* ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderName);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                attandance_edt.setAdapter(adapter2);*/

                return false;

            }
        });

        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public int getItemCount() {
        return datepaln.size();
    }

    private int selectSpinnerValue(List<ActivityReportAttendanceData> ListSpinner, String myString) {
        int index = 0;
        for (int i = 0; i < ListSpinner.size(); i++) {
            if (ListSpinner.get(i).getAttendance().equals(myString)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView row_txt;
        private final LinearLayout rowitem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.row_txt = itemView.findViewById(R.id.row_txt);
            this.rowitem = itemView.findViewById(R.id.rowitem);
            row_txt.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            String statues = sessinoManager.getMONTHSTATUS();

            int position = this.getAdapterPosition();
            ActivityReportAttendanceData date_plan = datepaln.get(position);

            String id = date_plan.getId();
            String userId = date_plan.getUserId();
            String dateAttendance = date_plan.getDateAttendance();
            String status = date_plan.getStatus();
            String monthYear = date_plan.getMonthYear();
            String createdAt = date_plan.getCreatedAt();
            String updatedAt = date_plan.getUpdatedAt();
            String submitedAt = date_plan.getSubmittedAt();
            String submitedBy = date_plan.getSubmittedBy();
            String name = date_plan.getName();
            String usertypeid = date_plan.getUserTypeId();

            String subcategory_Name = date_plan.getAttendance();
            subcategory_Name = String.valueOf(subcategory_Name);

            if (statues.equalsIgnoreCase("1")) {

                //row_txt.setFocusable(false);

                OpenDialouge(id, userId, dateAttendance, status, monthYear, createdAt, updatedAt, submitedAt, submitedBy, name, usertypeid);

                first_half.setText(date_plan.getFirstHalf());
                second_half_txt.setText(date_plan.getSecondHalf());


                first_half.setEnabled(false);
                second_half_txt.setEnabled(false);

                if (subcategory_Name.equals("")) {
                } else if (subcategory_Name.equals("null")) {
                } else {
                    if (subcategory_Name.equals("present")) {

                        attandance_edt.setText(subcategory_Name);
                        first_half.setVisibility(View.VISIBLE);
                        second_half_txt.setVisibility(View.VISIBLE);
                        Secondhalf.setVisibility(View.VISIBLE);
                        Firsthalf.setVisibility(View.VISIBLE);

                        String[] genderName = {"present", "HDL", "CL", "SL", "AL", "holiday"};

                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderName);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        attandance_edt.setAdapter(adapter2);

                    } else if (subcategory_Name.equals("HDL")) {

                        attandance_edt.setText(subcategory_Name);
                        first_half.setVisibility(View.VISIBLE);
                        second_half_txt.setVisibility(View.VISIBLE);
                        Secondhalf.setVisibility(View.VISIBLE);
                        Firsthalf.setVisibility(View.VISIBLE);
                        //HDL

                        String[] genderName = {"present", "HDL", "CL", "SL", "AL", "holiday"};

                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderName);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        attandance_edt.setAdapter(adapter2);

                    } else {

                        attandance_edt.setText(subcategory_Name);
                        first_half.setVisibility(View.GONE);
                        second_half_txt.setVisibility(View.GONE);
                        Secondhalf.setVisibility(View.GONE);
                        Firsthalf.setVisibility(View.GONE);

                        String[] genderName = {"present", "HDL", "CL", "SL", "AL", "holiday"};

                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderName);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        attandance_edt.setAdapter(adapter2);
                    }
                }

                //saveBtn.setFocusable(true);
                saveBtn.setVisibility(View.GONE);

                //attandance_edt.setClickable(false);
                attandance_edt.setEnabled(false);
                attandance_edt.setClickable(false);

            } else if (statues.equalsIgnoreCase("2")) {

                //row_txt.setFocusable(false);

                OpenDialouge(id, userId, dateAttendance, status, monthYear, createdAt, updatedAt, submitedAt, submitedBy, name, usertypeid);

                first_half.setText(date_plan.getFirstHalf());
                second_half_txt.setText(date_plan.getSecondHalf());

                first_half.setEnabled(false);
                second_half_txt.setEnabled(false);

                if (subcategory_Name.equals("")) {
                } else if (subcategory_Name.equals("null")) {
                } else {
                    if (subcategory_Name.equals("present")) {

                        attandance_edt.setText(subcategory_Name);
                        first_half.setVisibility(View.VISIBLE);
                        second_half_txt.setVisibility(View.VISIBLE);
                        Secondhalf.setVisibility(View.VISIBLE);
                        Firsthalf.setVisibility(View.VISIBLE);

                        String[] genderName = {"present", "HDL", "CL", "SL", "AL", "holiday"};

                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderName);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        attandance_edt.setAdapter(adapter2);
                        //HDL

                    } else if (subcategory_Name.equals("HDL")) {

                        attandance_edt.setText(subcategory_Name);
                        first_half.setVisibility(View.VISIBLE);
                        second_half_txt.setVisibility(View.VISIBLE);
                        Secondhalf.setVisibility(View.VISIBLE);
                        Firsthalf.setVisibility(View.VISIBLE);
                        //HDL

                        String[] genderName = {"present", "HDL", "CL", "SL", "AL", "holiday"};

                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderName);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        attandance_edt.setAdapter(adapter2);

                    } else {

                        attandance_edt.setText(subcategory_Name);
                        first_half.setVisibility(View.GONE);
                        second_half_txt.setVisibility(View.GONE);
                        Secondhalf.setVisibility(View.GONE);
                        Firsthalf.setVisibility(View.GONE);

                        String[] genderName = {"present", "HDL", "CL", "SL", "AL", "holiday"};

                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderName);
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        attandance_edt.setAdapter(adapter2);

                    }

                }

                //saveBtn.setFocusable(true);
                saveBtn.setVisibility(View.GONE);

                //attandance_edt.setClickable(false);
                attandance_edt.setEnabled(false);
                attandance_edt.setClickable(false);

            } else {

                if (subcategory_Name.equalsIgnoreCase("null")) {

                    OpenDialouge(id, userId, dateAttendance, status, monthYear, createdAt, updatedAt, submitedAt, submitedBy, name, usertypeid);

                    String[] genderName = {"present", "HDL", "CL", "SL", "AL", "holiday"};

                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderName);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    attandance_edt.setAdapter(adapter2);

                } else {

                    OpenDialouge(id, userId, dateAttendance, status, monthYear, createdAt, updatedAt, submitedAt, submitedBy, name, usertypeid);

                    first_half.setText(date_plan.getFirstHalf());
                    second_half_txt.setText(date_plan.getSecondHalf());

                    if (subcategory_Name.equals("")) {
                    } else {
                        if (subcategory_Name.equals("present")) {

                            attandance_edt.setText(subcategory_Name);

                            first_half.setVisibility(View.VISIBLE);
                            second_half_txt.setVisibility(View.VISIBLE);
                            Secondhalf.setVisibility(View.VISIBLE);
                            Firsthalf.setVisibility(View.VISIBLE);

                            String[] genderName = {"present", "HDL", "CL", "SL", "AL", "holiday"};

                            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderName);
                            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            attandance_edt.setAdapter(adapter2);

                        } else if (subcategory_Name.equals("HDL")) {

                            attandance_edt.setText(subcategory_Name);
                            first_half.setVisibility(View.VISIBLE);
                            second_half_txt.setVisibility(View.VISIBLE);
                            Secondhalf.setVisibility(View.VISIBLE);
                            Firsthalf.setVisibility(View.VISIBLE);

                            String[] genderName = {"present", "HDL", "CL", "SL", "AL", "holiday"};

                            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderName);
                            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            attandance_edt.setAdapter(adapter2);
                            //HDL

                        } else {

                            attandance_edt.setText(subcategory_Name);
                            first_half.setVisibility(View.GONE);
                            second_half_txt.setVisibility(View.GONE);
                            Secondhalf.setVisibility(View.GONE);
                            Firsthalf.setVisibility(View.GONE);

                            String[] genderName = {"present", "HDL", "CL", "SL", "AL", "holiday"};

                            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, genderName);
                            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            attandance_edt.setAdapter(adapter2);
                        }
                    }

                    //saveBtn.setFocusable(true);
                    saveBtn.setVisibility(View.VISIBLE);
                }
            }

        }
    }
}
