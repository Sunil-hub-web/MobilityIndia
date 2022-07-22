package com.example.mobilityindia.health.healthview;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.AppUtils;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityAddHealthBinding;
import com.example.mobilityindia.health.healthview.healthresponce.HealthResponse;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddHealthActivity extends AppCompatActivity {
    ActivityAddHealthBinding binding;
    DatePickerDialog datePicker;
    String referincal = "No";
    String fallowsheetupdated = "No";
    String correctivesurgery = "No";
    String homemodification = "No";
    String ihp = "No";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_health);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);

        binding.startdateofshg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(AddHealthActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.startdateofshg.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.dateofassiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(AddHealthActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.dateofassiment.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        binding.referincal.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    referincal = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    referincal = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.fallowsheetupdated.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    fallowsheetupdated = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    fallowsheetupdated = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.correctivesurgery.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    correctivesurgery = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    correctivesurgery = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.homemodification.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    homemodification = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on

                    binding.recommend.setVisibility(View.VISIBLE);
                    binding.recommend.setText("Recommended");
                } else {
                    homemodification = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off

                    binding.recommend.setVisibility(View.GONE);
                    binding.recommend.setText("Recommend");
                }
            }
        });

        binding.ihp.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    ihp = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    ihp = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(AppUtils.isNetworkAvailable(AddHealthActivity.this)) {
                    addlivelihoodApi();
                }else{
                    // localbeneficaryDataCall();
                }
            }
        });

    }

    private void addlivelihoodApi()
    {
        ProgressDialog pd = new ProgressDialog(AddHealthActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        Map<String, Object> mapData = new HashMap<>();

        mapData.put("screeningdate", binding.startdateofshg.getText().toString());

        mapData.put("assessmentdate", binding.dateofassiment.getText().toString());
        mapData.put("assessmentwho", binding.whodidassesmenta.getText().toString());
        mapData.put("assessmentwhere", binding.wherewasitdonea.getText().toString());


        mapData.put("referral", referincal);
        mapData.put("referralplace", binding.refertowhichplace.getText().toString());
        mapData.put("referralprescription", binding.presecription.getText().toString());

        mapData.put("trialwhat", binding.trailforwhat.getText().toString());
        mapData.put("trialdate", binding.traildate.getText().toString());


        mapData.put("socialsecurity", binding.trailforwhat.getText().toString());
        mapData.put("socialsecuritywhen",binding.trailforwhat.getText().toString());

        mapData.put("gaitfrequency", binding.gaitfrequency.getText().toString());
        mapData.put("gaithowmany", binding.howanydone.getText().toString());

        mapData.put("therapyfrequency", binding.thrpynumberoftime.getText().toString());
        mapData.put("therapysessions", binding.numberofsession.getText().toString());


        mapData.put("fitmentwho", binding.fitmentwho.getText().toString());
        mapData.put("fitmentwhere", binding.fitmentwhere.getText().toString());
        mapData.put("fitmentdevice", binding.fitmentkind.getText().toString());

        mapData.put("followupfrequency", binding.fallowwhat.getText().toString());
        mapData.put("followupsheet", fallowsheetupdated);


        mapData.put("surgery", correctivesurgery);
        mapData.put("surgerywhere", binding.surgerywhere.getText().toString());
        mapData.put("surgerywherewhat", binding.surgerywhat.getText().toString());

        mapData.put("homerecommend", homemodification);
        mapData.put("homerecommendwhat", binding.homemodificationwhere.getText().toString());
        mapData.put("ihp", ihp);


        CommonClass.APP_TOKEN = CommonClass.getToken(AddHealthActivity.this);
        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.addHealthData(CommonClass.APP_TOKEN,mapData).enqueue(new Callback<HealthResponse>() {
            @Override
            public void onResponse(Call<HealthResponse> call, Response<HealthResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    if(response.body().isStatus()){
                        Toast.makeText(AddHealthActivity.this,"Health  Added ",Toast.LENGTH_SHORT).show();
                        pd.dismiss();

                    }else{
                        Toast.makeText(AddHealthActivity.this,"Health not Added ",Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }


                }
            }
            @Override
            public void onFailure(Call<HealthResponse> call, Throwable t) {
                pd.dismiss();
            }
        });



    }
}