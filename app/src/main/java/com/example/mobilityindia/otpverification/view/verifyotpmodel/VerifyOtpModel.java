package com.example.mobilityindia.otpverification.view.verifyotpmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.otpverification.view.otpverifyresponce.VerifyotpResponse;
import com.example.mobilityindia.otpverification.view.resentotpresponce.ResentotpResponse;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpModel extends ViewModel {

    public MutableLiveData<String> etopn1 = new MutableLiveData<>();
    public MutableLiveData<String> etotp2 = new MutableLiveData<>();
    public MutableLiveData<String> etotp3 = new MutableLiveData<>();
    public MutableLiveData<String> etotp4 = new MutableLiveData<>();
    public MutableLiveData<String> etotp5 = new MutableLiveData<>();
    public MutableLiveData<String> etotp6 = new MutableLiveData<>();
    public MutableLiveData<Integer> isprogress = new MutableLiveData<>();

    private ApiRequest apiRequest;

    private MutableLiveData<String> pageNavigate;
    private MutableLiveData<String> resendOTP;
    private MutableLiveData<String> ErrorMSG;

    private MutableLiveData<VerifyotpResponse> userMutableLiveData;


    public MutableLiveData<String> getResentOTP() {
        if (resendOTP == null) {
            resendOTP = new MutableLiveData<>();
        }
        return resendOTP;
    }
    public MutableLiveData<String> getErrorMsg() {
        if (ErrorMSG == null) {
            ErrorMSG = new MutableLiveData<>();
        }
        return ErrorMSG;
    }


    public MutableLiveData<VerifyotpResponse> getUser() {
        isprogress.setValue(10);
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }


    public void onClick(String otpStr, String userId) {

        if (otpStr.equalsIgnoreCase("null") || otpStr.isEmpty()) {
            ErrorMSG.setValue("OTP field should not blank .");
        } else {

            isprogress.setValue(0);
            Map<String, Object> mapData = new HashMap<>();
            mapData.put("intUserId", userId);
            mapData.put("vchOTP", otpStr);
            final MutableLiveData<VerifyotpResponse> data = new MutableLiveData<>();
            apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
            apiRequest.verifyotp( CommonClass.APP_TOKEN,mapData).enqueue(new Callback<VerifyotpResponse>() {
                @Override
                public void onResponse(Call<VerifyotpResponse> call, Response<VerifyotpResponse> response) {
                    Log.d("TAG", "onResponse response:: " + response.body());
                    if (response.body().getMessage() != null) {
                        userMutableLiveData.setValue(response.body());
                        isprogress.setValue(10);

                    }
                }
                @Override
                public void onFailure(Call<VerifyotpResponse> call, Throwable t) {
                    userMutableLiveData.setValue(null);
                    isprogress.setValue(10);
                }
            });

        }
    }




    public void onResendOTP(String emailStr) {
        isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", CommonClass.USERiD);
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.resendotp(CommonClass.APP_TOKEN,mapData).enqueue(new Callback<ResentotpResponse>() {
            @Override
            public void onResponse(Call<ResentotpResponse> call, Response<ResentotpResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                //     userMutableLiveData.setValue(response.body());
                    isprogress.setValue(10);
                    resendOTP.setValue("Success");
                }
            }

            @Override
            public void onFailure(Call<ResentotpResponse> call, Throwable t) {
                userMutableLiveData.setValue(null);
                isprogress.setValue(10);
                resendOTP.setValue("Fail");
            }
        });
    }


}
