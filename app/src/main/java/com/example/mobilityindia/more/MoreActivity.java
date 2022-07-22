package com.example.mobilityindia.more;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.databinding.ActivityMoreBinding;
import com.example.mobilityindia.myprofile.view.MyProfileActivity;
import com.example.mobilityindia.syn1.view.SyncActivityAll;
import com.google.android.material.button.MaterialButton;

public class MoreActivity extends AppCompatActivity {
    Dialog dialog;
    SessinoManager sessinoManager;
    private ActivityMoreBinding binding;

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

        sessinoManager = new SessinoManager(MoreActivity.this);

        binding.myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MoreActivity.this, MyProfileActivity.class));

            }
        });

        binding.linLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openDialog_Logout();
            }
        });

        binding.linSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MoreActivity.this, SyncActivityAll.class));
            }
        });

    }

    public void openDialog_Logout(){

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
}