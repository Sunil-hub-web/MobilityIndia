package com.example.mobilityindia.livelihood;

import static com.example.mobilityindia.utils.Utils.getRandomNumber;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.AppUtils;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityAddLivelihoodBinding;
import com.example.mobilityindia.livelihood.livelihoodresponce.LivelihoodresponceResponse;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.sync.model.LivehoodData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLivelihoodActivity extends AppCompatActivity {
    ActivityAddLivelihoodBinding binding;
    DatePickerDialog datePicker;
    String skilldevelopment = "No",finencialservice = " No",socialsecurity = "No",vocationaltraning ="No",
    serviceother = "No";
    LocalRepo localRepo ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_livelihood);
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
        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        localRepo = new LocalRepo(AddLivelihoodActivity.this);


        String[] itemNames = getResources().getStringArray(R.array.FamielyLivelihood);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.familylivelihood.setAdapter(adapter2);

        binding.familylivelihood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.familylivelihood).showDropDown();

            }
        });

        binding.familylivelihood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.familylivelihood.getAdapter().getItem(position).toString();
            }
        });

        binding.skillwhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(AddLivelihoodActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.skillwhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.finencialwhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(AddLivelihoodActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.finencialwhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.socialwhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(AddLivelihoodActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.socialwhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.vocationwhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(AddLivelihoodActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.vocationwhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.servicewhen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(AddLivelihoodActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.servicewhen.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.switchskilldevelopment.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    skilldevelopment = "Yes";
                    binding.skillldaveloplayout.setVisibility(View.VISIBLE);
                    //switch  is on
                }else{
                    skilldevelopment = "No";
                    binding.skillldaveloplayout.setVisibility(View.GONE);
                    binding.skillwhen.setText("");
                    binding.skillwhat.setText("");
                    binding.skillwhere.setText("");
                    //switch is off
                }
            }
        });

        binding.financialservices.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    finencialservice = "Yes";
                    binding.financiallayout.setVisibility(View.VISIBLE);
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    finencialservice = "No";
                    binding.financiallayout.setVisibility(View.GONE);
                    binding.finencialwhen.setText("");
                    binding.finencialwhere.setText("");
                    binding.finencialwhat.setText("");
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.socialsecurity.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    socialsecurity = "Yes";
                    binding.sociallayout.setVisibility(View.VISIBLE);
                    //switch  is on
                }else
                    {
                    socialsecurity = "No";
                    binding.sociallayout.setVisibility(View.GONE);
                    binding.socialwhen.setText("");
                    binding.socialwhere.setText("");
                    binding.socialwhat.setText("");
                    //switch is off
                }
            }
        });

        binding.vocationtraning.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    vocationaltraning = "Yes";
                    binding.vocationlayout.setVisibility(View.VISIBLE);
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    vocationaltraning = "No";
                    binding.vocationlayout.setVisibility(View.GONE);
                    binding.vocationwhen.setText("");
                    binding.vocationwhere.setText("");
                    binding.vocationwhat.setText("");
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.serviceother.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    serviceother = "Yes";
                    binding.serotherlayut.setVisibility(View.VISIBLE);
                    //switch  is on
                }else{
                    serviceother = "No";
                    binding.serotherlayut.setVisibility(View.GONE);
                    binding.servicewhen.setText("");
                    binding.servicewhere.setText("");
                    binding.servicewhat.setText("");
                    //switch is off
                }
            }
        });


        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(AppUtils.isNetworkAvailable(AddLivelihoodActivity.this)) {
                    localbeneficaryDataCall();
                }else{
                    localbeneficaryDataCall();
                }
            }
        });
    }
    public void addlivelihoodApi()
    {
        ProgressDialog pd = new ProgressDialog(AddLivelihoodActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        Map<String, Object> mapData = new HashMap<>();

        mapData.put("benificiary_id", CommonClass.benfeciary_ID);
        mapData.put("user_id", CommonClass.USERiD);

        mapData.put("livelihood", finencialservice);
        mapData.put("livelihoodother", binding.familylivelihood.getText().toString());

        mapData.put("skilldev", skilldevelopment);
        mapData.put("skilldevwhen", binding.skillwhen.getText().toString());
        mapData.put("skilldevwhere", binding.skillwhere.getText().toString());
        mapData.put("skilldevwhat", binding.skillwhat.getText().toString());

        mapData.put("finservice", finencialservice);
        mapData.put("finservicewhen", binding.finencialwhen.getText().toString());
        mapData.put("finservicewhat", binding.finencialwhat.getText().toString());
        mapData.put("finservicewhere", binding.finencialwhere.getText().toString());

        mapData.put("socialsecurity", socialsecurity);
        mapData.put("socialsecuritywhen",binding.socialwhen.getText().toString());
        mapData.put("socialsecuritywhere", binding.socialwhere.getText().toString());
        mapData.put("socialsecuritywhat", binding.socialwhat.getText().toString());

        mapData.put("vocationaltraining", vocationaltraning);
        mapData.put("vocationaltraining_when", binding.vocationwhen.getText().toString());
        mapData.put("vocationaltraining_what", binding.vocationwhat.getText().toString());
        mapData.put("vocationaltraining_where", binding.vocationwhere.getText().toString());
        mapData.put("serviceother", serviceother);
        mapData.put("serviceotherwhen", binding.servicewhen.getText().toString());
        mapData.put("serviceotherwhere", binding.servicewhere.getText().toString());
        mapData.put("serviceotherwhat", binding.servicewhat.getText().toString());

        CommonClass.APP_TOKEN = CommonClass.getToken(AddLivelihoodActivity.this);
        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.addlivelihood(CommonClass.APP_TOKEN,mapData).enqueue(new Callback<LivelihoodresponceResponse>() {
            @Override
            public void onResponse(Call<LivelihoodresponceResponse> call, Response<LivelihoodresponceResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    if(response.body().isStatus()){
                        Toast.makeText(AddLivelihoodActivity.this,"Livelihood Added ",Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        onBackPressed();
                    }else{
                        Toast.makeText(AddLivelihoodActivity.this,"Livelihood not Added ",Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }
            @Override
            public void onFailure(Call<LivelihoodresponceResponse> call, Throwable t) {
                pd.dismiss();
            }
        });

    }


    public void localbeneficaryDataCall()
    {
        LivehoodData livelihood = new LivehoodData();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date date = new Date();

        livelihood.setBenificiary_id(CommonClass.benfeciary_ID);
        String randoNoStr = getRandomNumber();
        livelihood.setId(randoNoStr);
        livelihood.setCreatedAt(formatter.format(date));

        livelihood.setLivelihood(binding.familylivelihood.getText().toString());
        livelihood.setLivelihoodother(binding.otherspecify.getText().toString());

        livelihood.setSkilldev(skilldevelopment);
        livelihood.setSkilldevwhen(binding.skillwhen.getText().toString());
        livelihood.setSkilldevwhat(binding.skillwhat.getText().toString());
        livelihood.setSkilldevwhere(binding.skillwhere.getText().toString());

        livelihood.setFinservice(finencialservice);
        livelihood.setFinservicewhen(binding.finencialwhen.getText().toString());
        livelihood.setFinservicewhere(binding.finencialwhere.getText().toString());
        livelihood.setFinservicewhat(binding.finencialwhat.getText().toString());

        livelihood.setSocialsecurity(socialsecurity);
        livelihood.setSocialsecuritywhen(binding.socialwhen.getText().toString());
        livelihood.setSocialsecuritywhere(binding.socialwhere.getText().toString());
        livelihood.setSocialsecuritywhat(binding.socialwhat.getText().toString());

        livelihood.setVocationaltraining(vocationaltraning);
        livelihood.setVocationaltrainingWhen(binding.vocationwhen.getText().toString());
        livelihood.setVocationaltrainingWhere(binding.vocationwhere.getText().toString());
        livelihood.setVocationaltrainingWhat(binding.vocationwhat.getText().toString());

        livelihood.setServiceother(serviceother);
        livelihood.setVocationaltrainingWhen(binding.servicewhen.getText().toString());
        livelihood.setVocationaltrainingWhere(binding.servicewhere.getText().toString());
        livelihood.setVocationaltrainingWhat(binding.servicewhat.getText().toString());


        localRepo.insertLivehoodData(livelihood);
        Toast.makeText(getApplicationContext(), "Internet is not Available Data save in your Local", Toast.LENGTH_SHORT).show();
        onBackPressed();

    }
}