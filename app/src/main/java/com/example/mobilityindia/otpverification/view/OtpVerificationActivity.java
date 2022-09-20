package com.example.mobilityindia.otpverification.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobilityindia.R;
import com.example.mobilityindia.createpin.view.CreatepinActivity;
import com.example.mobilityindia.databinding.ActivityOtpVerificationBinding;
import com.example.mobilityindia.otpverification.view.verifyotpmodel.VerifyOtpModel;

import java.util.Locale;

public class OtpVerificationActivity extends AppCompatActivity {
    String emailStr = "";
    String otpStr = "";
    private ActivityOtpVerificationBinding binding;
    private long totalTimeCountInMilliseconds;
    private CountDownTimer countDownTimer;
    private VerifyOtpModel verifyOtpModel;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp_verification);
        verifyOtpModel = new ViewModelProvider(this).get(VerifyOtpModel.class);
        binding.setLifecycleOwner(this);
        binding.setVerifyOtpModel(verifyOtpModel);

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
        emailStr = getIntent().getStringExtra("emailid");
        userid = getIntent().getStringExtra("userid");
        setTimer();
        startTimer();

        EditText[] editn = {binding.etopn1, binding.etopn2, binding.etopn3, binding.etopn4, binding.etopn5, binding.etopn6};
        for (int i = 0; i < 6; i++) { //Its designed for 6 digit OTP
            final int iVal = i;

            editn[iVal].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (iVal == 5 && !editn[iVal].getText().toString().isEmpty()) {
                        editn[iVal].clearFocus(); //Clears focus when you have entered the last digit of the OTP.
                    } else if (!editn[iVal].getText().toString().isEmpty()) {
                        editn[iVal + 1].requestFocus(); //focuses on the next edittext after a digit is entered.
                    }
                }
            });
            editn[iVal].setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() != KeyEvent.ACTION_DOWN) {
                        return false; //Dont get confused by this, it is because onKeyListener is called twice and this condition is to avoid it.
                    }
                    if (keyCode == KeyEvent.KEYCODE_DEL &&
                            editn[iVal].getText().toString().isEmpty() && iVal != 0) {
                        //this condition is to handel the delete input by users.
                        editn[iVal - 1].setText("");//Deletes the digit of OTP
                        editn[iVal - 1].requestFocus();//and sets the focus on previous digit
                    }
                    return false;
                }
            });

        }

        binding.resendTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.timerTxt.setVisibility(View.VISIBLE);
                binding.Didntreceiveotp.setVisibility(View.VISIBLE);
                startTimer();
                verifyOtpModel.onResendOTP(emailStr);
            }
        });

        binding.btnverify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(binding.etopn1.getText().toString().equals("") || binding.etopn1.getText().toString() == null){
                    Toast.makeText(OtpVerificationActivity.this, "Please fill the OTP", Toast.LENGTH_SHORT).show();
                }else if(binding.etopn2.getText().toString().equals("") || binding.etopn2.getText().toString() == null){
                    Toast.makeText(OtpVerificationActivity.this, "Please fill the OTP", Toast.LENGTH_SHORT).show();
                }else if(binding.etopn3.getText().toString().equals("") || binding.etopn3.getText().toString() == null){
                    Toast.makeText(OtpVerificationActivity.this, "Please fill the OTP", Toast.LENGTH_SHORT).show();
                }else if(binding.etopn4.getText().toString().equals("") || binding.etopn4.getText().toString() == null){
                    Toast.makeText(OtpVerificationActivity.this, "Please fill the OTP", Toast.LENGTH_SHORT).show();
                }else if(binding.etopn5.getText().toString().equals("") || binding.etopn5.getText().toString() == null){
                    Toast.makeText(OtpVerificationActivity.this, "Please fill the OTP", Toast.LENGTH_SHORT).show();
                }else if(binding.etopn6.getText().toString().equals("") || binding.etopn6.getText().toString() == null){
                    Toast.makeText(OtpVerificationActivity.this, "Please fill the OTP", Toast.LENGTH_SHORT).show();
                }else{

                    otpStr = binding.etopn1.getText().toString() + binding.etopn2.getText().toString() + binding.etopn3.getText().toString() + binding.etopn4.getText().toString()+ binding.etopn5.getText().toString()+ binding.etopn6.getText().toString() ;
                    startTimer();
                    verifyOtpModel.onClick(otpStr, userid);
                }

            }
        });

        verifyOtpModel.getUser().observe(this,  verifyotpresponce ->  {
            //System.out.println("Data :   "+verifyotpresponce.getMessage());
            if(verifyotpresponce.isStatus()){
                stopTimer();
                Toast.makeText(OtpVerificationActivity.this, verifyotpresponce.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OtpVerificationActivity.this, CreatepinActivity.class));
                finish();
            }else{
                //stopTimer();

                Toast.makeText(this, "OTP mismatched!", Toast.LENGTH_SHORT).show();
            }
        });

        verifyOtpModel.getResentOTP().observe(this, responseStr -> {
            System.out.println("Data :   "+responseStr);
            if(responseStr.equalsIgnoreCase("Success"))
            {
                binding.timerTxt.setText("");
                binding.timerTxt.setVisibility(View.VISIBLE);
                binding.resendTxt.setVisibility(View.GONE);
             //   stopTimer();

            }
        });

        verifyOtpModel.getErrorMsg().observe(this, responseStr -> {
            if(responseStr.equalsIgnoreCase("OTP field should not blank .")) {
                Toast.makeText(OtpVerificationActivity.this, responseStr, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setTimer() {
        int time = 1;
        totalTimeCountInMilliseconds = 60 * time * 1000;

    }

    private void startTimer() {
        countDownTimer = new OtpCounter(totalTimeCountInMilliseconds, 500).start();
    }

    public void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public class OtpCounter extends CountDownTimer {

        OtpCounter(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long leftTimeInMilliseconds) {
            long seconds = leftTimeInMilliseconds / 1000;
            binding.timerTxt.setText(String.format(Locale.getDefault(), "%02d", seconds / 60) + ":" + String.format(Locale.getDefault(), "%02d", seconds % 60));
        }

        @Override
        public void onFinish() {

            binding.timerTxt.setVisibility(View.INVISIBLE);
            binding.resendTxt.setVisibility(View.VISIBLE);
            binding.Didntreceiveotp.setVisibility(View.VISIBLE);
        }
    }

}