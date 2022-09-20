package com.example.mobilityindia.changepin.view.changepinviewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilityindia.changepin.view.changepinresponce.ChangepinresponceResponse;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangepinViewModel extends ViewModel {

    public MutableLiveData<Integer> isprogress = new MutableLiveData<>();

    private ApiRequest apiRequest;


    private MutableLiveData<ChangepinresponceResponse> userMutableLiveData;

    public MutableLiveData<ChangepinresponceResponse> getUser() {
//        isprogress.setValue(10);
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        System.out.println("Hi");
        return userMutableLiveData;

    }



    public void onClick() {
        isprogress.setValue(0);
        final MutableLiveData<ChangepinresponceResponse> data = new MutableLiveData<>();
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("old_password", CommonClass.CURRENTPIN);
        mapData.put("password", CommonClass.NEWPIN);
        mapData.put("confirm_password", CommonClass.REENTERPIN);
        mapData.put("user_id", CommonClass.USERiD);
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.changemypin(CommonClass.APP_TOKEN,mapData).enqueue(new Callback<ChangepinresponceResponse>() {
            @Override
            public void onResponse(Call<ChangepinresponceResponse> call, Response<ChangepinresponceResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    userMutableLiveData.setValue(response.body());
                    Log.d("TAG", "onResponse response:: " + response.body());
                    isprogress.setValue(10);
                }
            }
            @Override
            public void onFailure(Call<ChangepinresponceResponse> call, Throwable t) {
                userMutableLiveData.setValue(null);
                isprogress.setValue(10);
            }
        });

    }
}
