package com.example.mobilityindia.loginpage.loginviewmodel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.loginpage.loginresponce.LoginDataResponse;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> Password = new MutableLiveData<>();
    public MutableLiveData<Integer> isprogress = new MutableLiveData<>();

    private ApiRequest apiRequest;


    private MutableLiveData<LoginDataResponse> userMutableLiveData;

    private MutableLiveData<String> pageNavigate;

    public MutableLiveData<LoginDataResponse> getUser() {
        isprogress.setValue(10);

        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        System.out.println("Hi");
        return userMutableLiveData;

    }

    public MutableLiveData<String> getPageNavigate() {
        if (pageNavigate == null) {
            pageNavigate = new MutableLiveData<>();
        }
        return pageNavigate;

    }


    public void onClick(View view) {
        CommonClass.ERROR_MSG = "";
        if(!Utils.isNetworkAvailable(view.getContext())){
            CommonClass.ERROR_MSG = "Check your Internet connection.";
            userMutableLiveData.setValue(null);
        }
        else if(EmailAddress.getValue()==null){
            CommonClass.ERROR_MSG = "Please Enter Email Address";
            //   Toast.makeText(view.getContext(), "Email Address Should not Empty", Toast.LENGTH_SHORT).show();
            userMutableLiveData.setValue(null);

        }/*else if(!Patterns.EMAIL_ADDRESS.matcher(EmailAddress.getValue().trim()).matches()){
            CommonClass.ERROR_MSG = "Please Enter Valid Email Address";
            userMutableLiveData.setValue(null);


        }*/else if(Password.getValue()==null||Password.getValue().equals("")){
            CommonClass.ERROR_MSG = "Please Enter PIN";
            userMutableLiveData.setValue(null);

        }else {

            isprogress.setValue(0);

            Map<String, Object> mapData = new HashMap<>();
            mapData.put("email", EmailAddress.getValue());
            mapData.put("password", Password.getValue());
            System.out.println(mapData);
            final MutableLiveData<LoginDataResponse> data = new MutableLiveData<>();
            apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
            apiRequest.login(mapData).enqueue(new Callback<LoginDataResponse>() {
                @Override
                public void onResponse(Call<LoginDataResponse> call, Response<LoginDataResponse> response) {
                    Log.d("TAG", "onResponse response:: " + response.body());
                    isprogress.setValue(10);

                    if (response.body() != null) {
                        userMutableLiveData.setValue(response.body());

                    } else {
                        userMutableLiveData.setValue(null);
                    }
                }
                @Override
                public void onFailure(Call<LoginDataResponse> call, Throwable t) {
                    userMutableLiveData.setValue(null);
                    isprogress.setValue(10);
                }
            });
        }
    }

    public void onForgotPwdClick(){
        pageNavigate.setValue("Go to next Page");

    }

}
