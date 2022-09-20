package com.example.mobilityindia.forgetpin.view.forgetpinviewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.forgetpin.view.forgetpinresponce.ForgetPinResponse;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPinViewModel extends ViewModel {
    public MutableLiveData<String> Email = new MutableLiveData<>();
    public MutableLiveData<Integer> isprogress = new MutableLiveData<>();


    private ApiRequest apiRequest;

    private MutableLiveData<String> pageNavigate;

    private MutableLiveData<ForgetPinResponse> userMutableLiveData;

    public MutableLiveData<ForgetPinResponse> getUser() {
        isprogress.setValue(10);
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        System.out.println("Hi");
        return userMutableLiveData;

    }

    public void onClick() {
        if (Email.getValue() == null) {

            CommonClass.ERROR_MSG = "Email should not empty";
            userMutableLiveData.setValue(null);
        } else if (!Utils.isEmailValid(Email.getValue())) {
            CommonClass.ERROR_MSG = "Email id is not proper format ";
            userMutableLiveData.setValue(null);
        } else {

            isprogress.setValue(0);
            Map<String, Object> mapData = new HashMap<>();
            mapData.put("email", Email.getValue());
            final MutableLiveData<ForgetPinResponse> data = new MutableLiveData<>();
            apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
            apiRequest.forgetpin(mapData).enqueue(new Callback<ForgetPinResponse>() {
                @Override
                public void onResponse(Call<ForgetPinResponse> call, Response<ForgetPinResponse> response) {
                    Log.d("TAG", "onResponse response:: " + response.body());
                    if (response.body() != null) {
                        userMutableLiveData.setValue(response.body());
                        isprogress.setValue(10);

                    }else{

                        CommonClass.ERROR_MSG = "Email id is not register";
                        userMutableLiveData.setValue(null);
                    }
                }

                @Override
                public void onFailure(Call<ForgetPinResponse> call, Throwable t) {
                    userMutableLiveData.setValue(null);
                    isprogress.setValue(10);

                }
            });
        }
    }
}
