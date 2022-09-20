package com.example.mobilityindia.attendance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilityindia.R;
import com.example.mobilityindia.attendance.Attendance;
import com.example.mobilityindia.attendance.database.AttendanceClass;
import com.example.mobilityindia.attendance.database.RoomDB;

import java.util.List;

public class ClickInOutAdapter extends RecyclerView.Adapter<ClickInOutAdapter.ViewHolder> {

    Context context;
    List<AttendanceClass> clockinout;
    RoomDB localdataBase;

    public ClickInOutAdapter(List<AttendanceClass> clockinout, Attendance attendance) {

        this.context = attendance;
        this.clockinout = clockinout;
    }

    @NonNull
    @Override
    public ClickInOutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clickinout_show,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClickInOutAdapter.ViewHolder holder, int position) {

        //Initialize database
        localdataBase = RoomDB.getInstance(context);

        AttendanceClass clock = clockinout.get(position);

        holder.text_Date.setText(clock.getDate());
        holder.clockoutTime.setText(clock.getClockoutTime());
        holder.checkinTime.setText(clock.getCheckinTime());

       /* holder.lin_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Initialize main data
                ClickInOut_Model d = clockinout.get(holder.getAdapterPosition());
                //Delete text from dataBase
               localdataBase.attendanceDao().delete(d);
                //Notifay when data is deleted

                int position = holder.getAdapterPosition();
                clockinout.remove(position);
                notifyItemChanged(position);
                notifyItemRangeChanged(position,clockinout.size());

            }
        });*/
    }

    @Override
    public int getItemCount() {

        return clockinout.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView text_Date,checkinTime,clockoutTime;
        LinearLayout lin_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            clockoutTime = itemView.findViewById(R.id.clockoutTime);
            checkinTime = itemView.findViewById(R.id.checkinTime);
            text_Date = itemView.findViewById(R.id.text_Date);
            lin_delete = itemView.findViewById(R.id.lin_delete);
        }
    }
}
