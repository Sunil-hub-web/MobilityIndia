package com.example.mobilityindia.forgetpin.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityForgotPinBinding;
import com.example.mobilityindia.forgetpin.view.forgetpinviewmodel.ForgetPinViewModel;
import com.example.mobilityindia.otpverification.view.OtpVerificationActivity;
import com.example.mobilityindia.retrofit.ApiRequest;

public class ForgotPinActivity extends AppCompatActivity {
    private ActivityForgotPinBinding binding;
    private ForgetPinViewModel forgetPinViewModel;
    private ApiRequest apiRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_pin);
        setContentView(binding.getRoot());
        forgetPinViewModel = new ViewModelProvider(this).get(ForgetPinViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setForgetPinViewModel(forgetPinViewModel);
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


        forgetPinViewModel.getUser().observe(this,  forgetpassword ->  {

            if(forgetpassword!=null){

                if(forgetpassword.isStatus()) {
                    getSharedPreferences("userData", MODE_PRIVATE).edit().putString("token", forgetpassword.getToken()).putBoolean("login", false).commit();
                    CommonClass.USERiD = forgetpassword.getResult().getUserId();
                    CommonClass.APP_TOKEN = forgetpassword.getToken();
                    //Intent intent = new Intent(ForgotPinActivity.this, CreatepinActivity.class);
                    Intent intent = new Intent(ForgotPinActivity.this, OtpVerificationActivity.class);
                    intent.putExtra("emailid", binding.email.getText().toString().trim());
                    intent.putExtra("userid", forgetpassword.getResult().getUserId());
                    startActivity(intent);

                    finish();
                }else{
                    Toast.makeText(ForgotPinActivity.this, forgetpassword.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            else {
                if(!CommonClass.ERROR_MSG.equalsIgnoreCase("")){
                    Toast.makeText(this, CommonClass.ERROR_MSG, Toast.LENGTH_SHORT).show();
                    CommonClass.ERROR_MSG = "OTP mismatched!";;
                } else {
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                }

            }

        });

        binding.btnotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                forgetPinViewModel.onClick();
            }
        });

    }
}