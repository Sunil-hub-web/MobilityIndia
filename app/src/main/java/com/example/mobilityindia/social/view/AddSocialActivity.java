package com.example.mobilityindia.social.view;

import static com.example.mobilityindia.utils.Utils.getRandomNumber;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.AppUtils;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityAddSocialBinding;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.social.socialresponce.SocialResponse;
import com.example.mobilityindia.sync.model.SocialData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSocialActivity extends AppCompatActivity {
    ActivityAddSocialBinding binding;
    String partofanycultural = "No";
    String partofpwd = "No";
    String partoflocal = "No";
    String traningparticipate = "No";
    LocalRepo localRepo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_social);
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
        localRepo = new LocalRepo(AddSocialActivity.this);


        binding.partofanyculture.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    partofanycultural = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    partofanycultural = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.partofpwd.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    partofpwd = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    partofpwd = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.partofanylocal.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    partoflocal = "Yes";
                    binding.tlName.setVisibility(View.VISIBLE);
                    //switch  is on
                }else{
                    partoflocal = "No";
                    binding.tlName.setVisibility(View.GONE);
                    binding.localgovtwhat.setText("");

                    //switch is off
                }
            }
        });

        binding.traningparticipate.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    traningparticipate = "Yes";
                    binding.tranarticipate.setVisibility(View.VISIBLE);
                    //switch  is on
                }else{
                    traningparticipate = "No";
                    binding.tranarticipate.setVisibility(View.GONE);
                    binding.whatkindoftraning.setText("");
                    binding.traningwhere.setText("");
                    //switch is off
                }
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(AppUtils.isNetworkAvailable(AddSocialActivity.this)) {
                    addSocialData();
                   // localbeneficaryDataCall();

                }else{
                     localbeneficaryDataCall();
                }
            }
        });
    }

    public void addSocialData(){
        ProgressDialog pd = new ProgressDialog(AddSocialActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        Map<String, Object> mapData = new HashMap<>();

        mapData.put("benificiary_id", CommonClass.benfeciary_ID);
        mapData.put("user_id",CommonClass.USERiD);

        mapData.put("socialsports", partofanycultural);
        mapData.put("spcialdpo",partofpwd);

        mapData.put("socialgovt", partoflocal);
        mapData.put("socialgovtwhat", binding.localgovtwhat.getText().toString());

        mapData.put("socialtraining", traningparticipate);
        mapData.put("socialtrainingwhat", binding.whatkindoftraning.getText().toString());
        mapData.put("socialtrainingwhere", binding.traningwhere.getText().toString());


        CommonClass.APP_TOKEN = CommonClass.getToken(AddSocialActivity.this);
        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.addSocialData(CommonClass.APP_TOKEN,mapData).enqueue(new Callback<SocialResponse>() {
            @Override
            public void onResponse(Call<SocialResponse> call, Response<SocialResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    if(response.body().isStatus()){
                        Toast.makeText(AddSocialActivity.this,"Social & Empowerment  Added ",Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        onBackPressed();

                    }else{
                        Toast.makeText(AddSocialActivity.this,"Social & Empowerment not Added ",Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }


                }
            }
            @Override
            public void onFailure(Call<SocialResponse> call, Throwable t) {
                pd.dismiss();
            }
        });

    }

    public void localbeneficaryDataCall()
    {
        SocialData socialData = new SocialData();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date date = new Date();


        socialData.setBenificiary_id(CommonClass.benfeciary_ID);
        socialData.setCreatedAt(formatter.format(date));
        socialData.setSocialsports(partofanycultural);
        socialData.setSpcialdpo(partofpwd);
        String randoNoStr = getRandomNumber();
        socialData.setId(randoNoStr);

        socialData.setSocialgovt(partoflocal);
        socialData.setSocialgovtwhat(binding.localgovtwhat.getText().toString());

        socialData.setSocialtraining(traningparticipate);
        socialData.setSocialtrainingwhat(binding.whatkindoftraning.getText().toString());
        socialData.setSocialtrainingwhere(binding.traningwhere.getText().toString());
        localRepo.insertSocialData(socialData);
        Toast.makeText(getApplicationContext(), "Internet is not Available Data save in your Local", Toast.LENGTH_SHORT).show();
        onBackPressed();

    }


}