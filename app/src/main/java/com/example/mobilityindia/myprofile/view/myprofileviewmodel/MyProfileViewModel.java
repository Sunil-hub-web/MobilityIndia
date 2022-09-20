package com.example.mobilityindia.myprofile.view.myprofileviewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.myprofile.view.myprofileresponce.MyProfileResponse;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileViewModel extends ViewModel {

    public MutableLiveData<String> Name = new MutableLiveData<>();
    public MutableLiveData<String> Phone = new MutableLiveData<>();
    public MutableLiveData<String> Email = new MutableLiveData<>();
    public MutableLiveData<String> Designation = new MutableLiveData<>();
    public MutableLiveData<String> District = new MutableLiveData<>();
    public MutableLiveData<String> Block = new MutableLiveData<>();
    public MutableLiveData<String> Panchayat = new MutableLiveData<>();
    public MutableLiveData<Integer> isprogress = new MutableLiveData<>();


    private ApiRequest apiRequest;

    private MutableLiveData<String> pageNavigate;

    private MutableLiveData<MyProfileResponse> userMutableLiveData;

    public MutableLiveData<MyProfileResponse> getUser() {
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


    public void onClick(String userId) {
            isprogress.setValue(0);
            final MutableLiveData<MyProfileResponse> data = new MutableLiveData<>();
            apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);

            apiRequest.myprofile(CommonClass.APP_TOKEN, userId).enqueue(new Callback<MyProfileResponse>() {
                @Override
                public void onResponse(Call<MyProfileResponse> call, Response<MyProfileResponse> response) {
                    Log.d("TAG", "onResponse response:: " + response.body());
                    if (response.body() != null) {
                        // userMutableLiveData.setValue(response.body());
                        isprogress.setValue(10);
                        Name.setValue(response.body().getUserDetails().getName());
                        Phone.setValue(response.body().getUserDetails().getPhone());
                        Email.setValue(response.body().getUserDetails().getEmail());
                        Designation.setValue(response.body().getUserDetails().getDesignation());
                        //District.setValue(response.body().getUserDetails().getD());
                    }
                }
                @Override
                public void onFailure(Call<MyProfileResponse> call, Throwable t) {
                    userMutableLiveData.setValue(null);
                    isprogress.setValue(10);
                }
            });

    }

}
