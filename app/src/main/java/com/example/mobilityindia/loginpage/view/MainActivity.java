package com.example.mobilityindia.loginpage.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityMainBinding;
import com.example.mobilityindia.forgetpin.view.ForgotPinActivity;
import com.example.mobilityindia.landingpage.view.LandingPageActivity;
import com.example.mobilityindia.loginpage.loginviewmodel.LoginViewModel;
import com.example.mobilityindia.syn1.view.SyncActivityAll;

public class MainActivity extends AppCompatActivity {
    SessinoManager sessinoManager;
    private ActivityMainBinding binding;
    private LoginViewModel loginViewModel;
    private Dialog dialog;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setLoginViewModel(loginViewModel);
        binding.pin.setInputType(InputType. TYPE_CLASS_TEXT | InputType. TYPE_TEXT_VARIATION_PASSWORD);

        sessinoManager = new SessinoManager(MainActivity.this);

        loginViewModel.getUser().observe(this, loginResponse -> {
            if (loginResponse != null) {
                System.out.println(loginResponse);
                if (loginResponse.isStatus().equalsIgnoreCase("true")) {
                    getSharedPreferences("userData", MODE_PRIVATE).edit().putString("token", loginResponse.getToken()).putBoolean("login", true).commit();
                    CommonClass.USERiD = loginResponse.getResult().getUserId() ;

                    sessinoManager.setUSERID(loginResponse.getResult().getUserId());
                    sessinoManager.setLogin();

                    startActivity(new Intent(MainActivity.this, SyncActivityAll.class));
                    finish();

                } else {
                    callErrorDialog(loginResponse.getMessage());
                }
            } else
                callErrorDialog(CommonClass.ERROR_MSG);
            {
//                if (!CommonClass.ERROR_MSG.equalsIgnoreCase("")) {
//                    callErrorDialog(CommonClass.ERROR_MSG);
//                   // Toast.makeText(this, CommonClass.ERROR_MSG, Toast.LENGTH_SHORT).show();
//                   // CommonClass.ERROR_MSG = "";
//                } else {
//                    callErrorDialog(CommonClass.ERROR_MSG);
//                 //   Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
//                }

            }
        });

        loginViewModel.getPageNavigate().observe(this, responseStr -> {
            if (responseStr.equalsIgnoreCase("Go to next Page")) {
                startActivity(new Intent(MainActivity.this, ForgotPinActivity.class));
            }
        });

    }
    public void callErrorDialog(String erroemessage) {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.erroedialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView okButton, textmessage;
        textmessage = dialog.findViewById(R.id.showtext);
        textmessage.setText("Invalid user name and password please try again ?");
        okButton = dialog.findViewById(R.id.savee);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                /*binding.email.setText("");
                binding.pin.setText("");*/
            }
        });
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(sessinoManager.isLogin()){

            startActivity(new Intent(MainActivity.this, LandingPageActivity.class));
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(sessinoManager.isLogin()) {

            startActivity(new Intent(MainActivity.this, SyncActivityAll.class));
            finish();
        }
    }

   /* @Override
    protected void onRestart() {
        super.onRestart();

        startActivity(new Intent(MainActivity.this, SyncActivityAll.class));
        finish();
    }*/
}