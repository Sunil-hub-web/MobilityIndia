package com.example.mobilityindia.changepin.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.changepin.view.changepinresponce.ChangepinresponceResponse;
import com.example.mobilityindia.changepin.view.changepinviewmodel.ChangepinViewModel;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityChangepiinBinding;
import com.example.mobilityindia.landingpage.view.LandingPageActivity;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangepiinActivity extends AppCompatActivity {
    String Currentpin = "",NewPin = "",RenterPin = "";
    private ChangepinViewModel changepinViewModel;
    private ActivityChangepiinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_changepiin);
        changepinViewModel = new ViewModelProvider(this).get(ChangepinViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setChangepinViewModel(changepinViewModel);
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

        EditText[] edit = {binding.etopc1, binding.etopc2, binding.etopc3, binding.etopc4, binding.etopc5, binding.etopc6};
        for (int i = 0; i < 6; i++) { //Its designed for 6 digit OTP
            final int iVal = i;

            edit[iVal].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (iVal == 5 && !edit[iVal].getText().toString().isEmpty()) {
                        edit[iVal].clearFocus(); //Clears focus when you have entered the last digit of the OTP.
                    } else if (!edit[iVal].getText().toString().isEmpty()) {
                        edit[iVal + 1].requestFocus(); //focuses on the next edittext after a digit is entered.
                    }
                }
            });
            edit[iVal].setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() != KeyEvent.ACTION_DOWN) {
                        return false; //Dont get confused by this, it is because onKeyListener is called twice and this condition is to avoid it.
                    }
                    if (keyCode == KeyEvent.KEYCODE_DEL &&
                            edit[iVal].getText().toString().isEmpty() && iVal != 0) {
                        //this condition is to handel the delete input by users.
                        edit[iVal - 1].setText("");//Deletes the digit of OTP
                        edit[iVal - 1].requestFocus();//and sets the focus on previous digit
                    }
                    return false;
                }
            });
        }

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


        EditText[] editr = {binding.etopr1, binding.etopr2, binding.etopr3, binding.etopr4, binding.etopr5, binding.etopr6};
        for (int i = 0; i < 6; i++) { //Its designed for 6 digit OTP
            final int iVal = i;

            editr[iVal].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (iVal == 5 && !editr[iVal].getText().toString().isEmpty()) {
                        editr[iVal].clearFocus(); //Clears focus when you have entered the last digit of the OTP.
                    } else if (!editr[iVal].getText().toString().isEmpty()) {
                        editr[iVal + 1].requestFocus(); //focuses on the next edittext after a digit is entered.
                    }
                }
            });
            editr[iVal].setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() != KeyEvent.ACTION_DOWN) {
                        return false; //Dont get confused by this, it is because onKeyListener is called twice and this condition is to avoid it.
                    }
                    if (keyCode == KeyEvent.KEYCODE_DEL &&
                            editr[iVal].getText().toString().isEmpty() && iVal != 0) {
                        //this condition is to handel the delete input by users.
                        editr[iVal - 1].setText("");//Deletes the digit of OTP
                        editr[iVal - 1].requestFocus();//and sets the focus on previous digit
                    }
                    return false;
                }
            });
        }

        binding.btnchangepin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Codec = binding.etopc1.getText().toString() + binding.etopc2.getText().toString() + binding.etopc3.getText().toString() + binding.etopc4.getText().toString()+ binding.etopc5.getText().toString()+ binding.etopc6.getText().toString() ;
                String Coden = binding.etopn1.getText().toString() + binding.etopn2.getText().toString() + binding.etopn3.getText().toString() + binding.etopn4.getText().toString()+ binding.etopn5.getText().toString()+ binding.etopn6.getText().toString() ;
                String Code = binding.etopr1.getText().toString() + binding.etopr2.getText().toString() + binding.etopr3.getText().toString() + binding.etopr4.getText().toString()+ binding.etopr5.getText().toString()+ binding.etopr6.getText().toString() ;

                if(Codec.length()!=6){

                    Toast.makeText(ChangepiinActivity.this,"Please Enter Current Pin", Toast.LENGTH_SHORT).show();

                } else if(Coden.length()!=6){

                    Toast.makeText(ChangepiinActivity.this,"Please Enter New Pin", Toast.LENGTH_SHORT).show();

                } else if(Code.length()!=6) {

                    Toast.makeText(ChangepiinActivity.this, "Please Enter Re-Enter New Pin ", Toast.LENGTH_SHORT).show();

                }else {

                    if(Coden.equalsIgnoreCase(Code)){

                        SessinoManager sessinoManager = new SessinoManager(ChangepiinActivity.this);
                        String userId = sessinoManager.getUSERID();

                        CommonClass.CURRENTPIN = Codec;
                        CommonClass.NEWPIN = Coden;
                        CommonClass.REENTERPIN = Code;

                        ProgressDialog progressDialog = new ProgressDialog(ChangepiinActivity.this);
                        progressDialog.setMessage("Please Wait....");
                        progressDialog.dismiss();
                        //binding.ProgressView.setValue(0);
                        Map<String, Object> mapData = new HashMap<>();
                        mapData.put("user_id", userId);
                        mapData.put("old_password", Codec);
                        mapData.put("password", Coden);
                        mapData.put("confirm_password", Coden);

                        Log.d("hsguyyfe",mapData.toString());

                        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
                        apiRequest.changemypin(CommonClass.APP_TOKEN,mapData).enqueue(new Callback<ChangepinresponceResponse>() {
                            @Override
                            public void onResponse(Call<ChangepinresponceResponse> call, Response<ChangepinresponceResponse> response) {

                                progressDialog.dismiss();
                                Log.d("TAG", "onResponse response:: " + response.body());
                                if (response.body().getStatus().equals("true")) {
                                   // userMutableLiveData.setValue(response.body());
                                    Log.d("TAG", "onResponse response:: " + response.body());
                                   // binding.ProgressView.setValue(10);

                                    Toast.makeText(ChangepiinActivity.this, "Pin Change Sucessfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ChangepiinActivity.this, LandingPageActivity.class).
                                            setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                }else{

                                    Toast.makeText(ChangepiinActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                            @Override
                            public void onFailure(Call<ChangepinresponceResponse> call, Throwable t) {

                                progressDialog.dismiss();
                                //userMutableLiveData.setValue(null);
                                //binding.ProgressView.setValue(10);

                                Toast.makeText(ChangepiinActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                            }
                        });
                        //changepinViewModel.onClick();

                    }else {
                        Toast.makeText(ChangepiinActivity.this, "New Pin and Re-enter Pin Should be Same ", Toast.LENGTH_SHORT).show();

                    }

                }


            }
        });

        /*changepinViewModel.getUser().observe(this, loginResponse -> {
            if (loginResponse != null) {
                if (loginResponse.isStatus().equalsIgnoreCase("true")) {
                    Toast.makeText(ChangepiinActivity.this, "Pin Change Sucessfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ChangepiinActivity.this, LandingPageActivity.class).
                            setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                }
            }
        });*/



    }
}