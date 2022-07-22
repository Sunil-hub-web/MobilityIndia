package com.example.mobilityindia.createpin.view.createpinviewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilityindia.changepin.view.changepinresponce.ChangepinresponceResponse;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.createpin.view.createpinresponce.ChangepinResponse;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePinViewModel extends ViewModel {

    public MutableLiveData<Integer> isprogress = new MutableLiveData<>();

    private ApiRequest apiRequest;


    private MutableLiveData<ChangepinResponse> userMutableLiveData;

    public MutableLiveData<ChangepinResponse> getUser() {
        isprogress.setValue(10);
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;

    }


    public void onClick() {
//        ProgressDialog pd = new ProgressDialog(CreatePinViewModel.this);
//        pd.setMessage("Loading...");
//        pd.setCancelable(false);
//        pd.show();
        isprogress.setValue(0);
        final MutableLiveData<ChangepinresponceResponse> data = new MutableLiveData<>();
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("password", CommonClass.NEWPIN);
        mapData.put("confirm_password", CommonClass.REENTERPIN);
        mapData.put("user_id", CommonClass.USERiD);
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.createpin(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<ChangepinResponse>() {
            @Override
            public void onResponse(Call<ChangepinResponse> call, Response<ChangepinResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    userMutableLiveData.setValue(response.body());
                    Log.d("TAG", "onResponse response:: " + response.body());
                    isprogress.setValue(10);

                }
            }

            @Override
            public void onFailure(Call<ChangepinResponse> call, Throwable t) {
                userMutableLiveData.setValue(null);
                isprogress.setValue(10);
            }
        });

    }
}