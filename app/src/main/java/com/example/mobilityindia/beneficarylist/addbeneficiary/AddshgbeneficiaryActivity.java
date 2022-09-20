package com.example.mobilityindia.beneficarylist.addbeneficiary;

import static com.example.mobilityindia.utils.Utils.getRandomNumber;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.beneficarylist.beneficaryresponce.BeneficaryResponse;
import com.example.mobilityindia.beneficarylist.view.BeneficaryListActivity;
import com.example.mobilityindia.constant.AppUtils;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityAddshgbeneficiaryBinding;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.syn1.view.SyncActivityAll;
import com.example.mobilityindia.syn1.view.allresponse.benificiary.Example_Benificiary;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.whiteelephant.monthpicker.MonthPickerDialog;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddshgbeneficiaryActivity extends AppCompatActivity {
    DatePickerDialog datePicker;
    String finalrandomnumber = "";
    LocalRepo localRepo;
    String currentdate = "",userId = "";
    String weathershg = "No";
    int selected;
    private ActivityAddshgbeneficiaryBinding binding;
    SessinoManager sessinoManager;
    ApiRequest apiRequest;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addshgbeneficiary);
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
        localRepo = new LocalRepo(AddshgbeneficiaryActivity.this);
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        currentdate = dateObj.format(formatter);

        sessinoManager = new SessinoManager(AddshgbeneficiaryActivity.this);
        userId = sessinoManager.getUSERID();

        binding.switch4.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    weathershg = "Yes";
                    CommonClass.weathershgornot = weathershg;
                    binding.layoutshow.setVisibility(View.VISIBLE);
                    //switch  is on
                } else {
                    weathershg = "No";
                    CommonClass.weathershgornot = weathershg;
                    binding.layoutshow.setVisibility(View.GONE);
                    //switch is off
                }
            }
        });

        binding.startdateofcbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  datePicker = new DatePickerDialog(AddshgbeneficiaryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.startdateofshg.setText(year);//dayOfMonth + "-" + (month + 1) + "-" +
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();*/

                final Calendar today = Calendar.getInstance();

                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(AddshgbeneficiaryActivity.this,
                        new MonthPickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(int selectedMonth, int selectedYear) {
                                // on date set }

                                binding.startdateofcbo.setText(String.valueOf(selectedYear));

                                selected = selectedYear;
                                Log.d("hhyghcgh", String.valueOf(selected));
                            }
                        }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));

                builder.setActivatedMonth(Calendar.JULY)
                        .setMinYear(2001)
                        .setActivatedYear(today.get(Calendar.YEAR))
                        .setMaxYear(today.get(Calendar.YEAR))
                        .setTitle("Select Year")
                        .showYearOnly()
                        .build().show();


            }
        });

        binding.edocbo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* datePicker = new DatePickerDialog(AddshgbeneficiaryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        binding.edoshg.setText(year);//dayOfMonth + "-" + (month + 1) + "-" +
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();*/

                final Calendar today = Calendar.getInstance();

                MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(AddshgbeneficiaryActivity.this,
                        new MonthPickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(int selectedMonth, int selectedYear) {
                                // on date set }

                                binding.edocbo.setText(String.valueOf(selectedYear));
                            }
                        }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));

                builder.setActivatedMonth(Calendar.JULY)
                        .setMinYear(selected)
                        .setActivatedYear(today.get(Calendar.YEAR))
                        .setMaxYear(today.get(Calendar.YEAR))
                        .setTitle("Select Year")
                        .showYearOnly()
                        .build().show();
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callgeneraterandomnumber();
                CommonClass.weathershgornot = weathershg;
                CommonClass.shgname = binding.shgname.getText().toString();
                CommonClass.startdateofshg = binding.startdateofcbo.getText().toString();
                CommonClass.edoshg = binding.edocbo.getText().toString();

                if (AppUtils.isNetworkAvailable(AddshgbeneficiaryActivity.this)) {
                    addbeneficaryapiCall();
                    //localbeneficaryDataCall();
                } else {
                    localbeneficaryDataCall();
                }
            }
        });

        binding.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void localbeneficaryDataCall() {

       /* SessinoManager sessinoManager = new SessinoManager(AddshgbeneficiaryActivity.this);
        String userId = sessinoManager.getUSERID();*/

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String Datetime = sdf.format(c.getTime());

        BeneData beneData = new BeneData();
        String randoNoStr = getRandomNumber();
        beneData.setTempId(randoNoStr);
        beneData.setRegistrationDate(CommonClass.DateOfRegistration);
        beneData.setName(CommonClass.shgnameee);
        beneData.setParentName(CommonClass.parent);
        beneData.setRelation(CommonClass.startdateofshgg);
        beneData.setDob(CommonClass.dojshg);
        beneData.setGender(CommonClass.gender);
        beneData.setCaste(CommonClass.caste);
        beneData.setReligion(CommonClass.reigion);
        beneData.setAdhaarNo(CommonClass.adhaarno);
        beneData.setAnnualIncome(CommonClass.annualincome);
        beneData.setEconomicStatus(CommonClass.ecconomicstatus);
        beneData.setMaritalStatus(CommonClass.meritalstatus);
        beneData.setEducationName(CommonClass.edu);
        beneData.setOccupation(CommonClass.occuption);
        beneData.setTypeOfDisability(CommonClass.typeofdisability);
        beneData.setTypeOfSubDisability(CommonClass.typeofsubdisability);
        beneData.setPercentageOfDisability(CommonClass.percentofdisability);
        beneData.setIdCardNo(CommonClass.idcardno);
        beneData.setPhpAmount(CommonClass.phpammount);
        beneData.setTypeOfBeneficiary(CommonClass.typeofbenificary);
        beneData.setPurposeOfVisit(CommonClass.purposeofvisit);
        beneData.setPurposeVisitDetails(CommonClass.purposevisitdetails);
        beneData.setHaveBankAcc(CommonClass.havebankacc);
        beneData.setAccHolderName(CommonClass.accountHolderName);
        beneData.setAccNum(CommonClass.shgbankno);
        beneData.setIfsc(CommonClass.ifscCode);
        beneData.setAccType(CommonClass.accountType);
        beneData.setNameOfPwdCwd(CommonClass.nameofpwdcwd);
        beneData.setVillageId(CommonClass.villageID);
        beneData.setAddress(CommonClass.address);
        beneData.setSchoolAnganwadiName(CommonClass.schoolname);
        beneData.setContactNo(CommonClass.contactNo1);
        beneData.setContactNoOther(CommonClass.contactNo2);
        beneData.setEmailId(CommonClass.email);
        beneData.setRationCardNo(CommonClass.rationcard);
        beneData.setSanitationToilet(CommonClass.sanitation);
        beneData.setAppliances(CommonClass.appliance);
        beneData.setSurgery(CommonClass.surgery);
        beneData.setGovtFacilities(CommonClass.govtfacility);
        beneData.setGovtFacilityMention(CommonClass.govtfacility_mention);
        beneData.setFamilyMember(CommonClass.familymemberadultm);
        beneData.setFamilyMemberAdults(CommonClass.familymemberadultf);
        beneData.setFamilyMemberChildM(CommonClass.fmlymemberchildrnm);
        beneData.setFamilyMemberChildF(CommonClass.fmlymembrchilf);
        beneData.setChildrenUndergoingEducationM(CommonClass.childrnundergeducationm);
        beneData.setChildrenUndergoingEducationF(CommonClass.childrenundergoeeseducationf);
        beneData.setDropoutsLess14M(CommonClass.drpuotlessthen14m);
        beneData.setDropoutsLess14F(CommonClass.dropoutlessthen14f);
        beneData.setDropouts1418M(CommonClass.dropoutm);
        beneData.setDropouts1418F(CommonClass.dropoutf);
        beneData.setEarningMembersFlyM(CommonClass.earingmemberflym);
        beneData.setEarningMembersFlyF(CommonClass.earingmemberflyf);
        beneData.setWheatherCboMember(CommonClass.weathershgornot);
        beneData.setCboName(CommonClass.shgname);
        beneData.setStartYearOfCbo(CommonClass.startdateofshg);
        beneData.setYearJoinCbo(CommonClass.dojshg);
        beneData.setUserId(userId);
        beneData.setFlag("update");
        localRepo.insertBene(beneData);
        Toast.makeText(getApplicationContext(), "Internet is not Available Data save in your Local", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(AddshgbeneficiaryActivity.this, BeneficaryListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }


    public void callgeneraterandomnumber() {
        Random r = new Random();
        String randomNumber = String.format("%04d", Integer.valueOf(r.nextInt(1001)));
        finalrandomnumber = "TR001" + randomNumber;

    }

    public void addbeneficaryapiCall() {

        /*SessinoManager sessinoManager = new SessinoManager(AddshgbeneficiaryActivity.this);
        String userId = sessinoManager.getUSERID();*/
        ProgressDialog pd = new ProgressDialog(AddshgbeneficiaryActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        Map<String, Object> mapData = new  HashMap<>();
        mapData.put("temp_id", finalrandomnumber);
        mapData.put("registration_date", CommonClass.DateOfRegistration);
        mapData.put("name", CommonClass.shgnameee);
        mapData.put("parent_name", CommonClass.parent);
        mapData.put("relation", CommonClass.startdateofshgg);
        mapData.put("dob", CommonClass.dojshg);
        mapData.put("gender", CommonClass.gender);
        mapData.put("caste", CommonClass.caste);
        mapData.put("religion", CommonClass.reigion);
        mapData.put("adhaar_no", CommonClass.adhaarno);
        mapData.put("annual_income", CommonClass.annualincome);
        mapData.put("economic_status", CommonClass.ecconomicstatus);
        mapData.put("marital_status", CommonClass.meritalstatus);
        mapData.put("education", CommonClass.edu);
        mapData.put("occupation", CommonClass.occuption);
        mapData.put("type_of_disability", CommonClass.typeofdisability);
        mapData.put("type_of_sub_disability", CommonClass.typeofsubdisability);
        mapData.put("percentage_of_disability", CommonClass.percentofdisability);
        mapData.put("id_card_no", CommonClass.idcardno);
        mapData.put("php_amount", CommonClass.phpammount);
        mapData.put("type_of_beneficiary", CommonClass.typeofbenificary);
        mapData.put("purpose_of_visit", CommonClass.purposeofvisit);
        mapData.put("purpose_visit_details", CommonClass.purposevisitdetails);
        mapData.put("have_bank_acc", CommonClass.havebankacc);
        mapData.put("acc_num", CommonClass.bankname);
        mapData.put("acc_holder_name", CommonClass.accountHolderName);
        mapData.put("ifsc", CommonClass.ifscCode);
        mapData.put("acc_type", CommonClass.accountType);
        mapData.put("name_of_pwd_cwd", CommonClass.nameofpwdcwd);
        mapData.put("village_id", CommonClass.villageID);
        mapData.put("address", CommonClass.address);
        mapData.put("school_anganwadi_name", CommonClass.schoolname);
        mapData.put("contact_no", CommonClass.contactNo1);
        mapData.put("contact_no_other", CommonClass.contactNo2);
        mapData.put("email_id", CommonClass.email);
        mapData.put("ration_card_no", CommonClass.rationcard);
        mapData.put("sanitation_toilet", CommonClass.sanitation);
        mapData.put("appliances", CommonClass.appliance);
        mapData.put("surgery", CommonClass.surgery);
        mapData.put("govt_facilities", CommonClass.govtfacility);
        mapData.put("govt_facility_mention", CommonClass.govtfacility_mention);
        mapData.put("family_member", CommonClass.familymemberadultm);
        mapData.put("family_member_adults", CommonClass.familymemberadultf);
        mapData.put("family_member_child_m", CommonClass.fmlymemberchildrnm);
        mapData.put("family_member_child_f", CommonClass.fmlymembrchilf);
        mapData.put("children_undergoing_education_m", CommonClass.childrnundergeducationm);
        mapData.put("children_undergoing_education_f", CommonClass.childrenundergoeeseducationf);
        mapData.put("dropouts_less_14_m", CommonClass.drpuotlessthen14m);
        mapData.put("dropouts_less_14_f", CommonClass.dropoutlessthen14f);
        mapData.put("dropouts_14_18_m", CommonClass.dropoutm);
        mapData.put("dropouts_14_18_f", CommonClass.dropoutf);
        mapData.put("earning_members_fly_m", CommonClass.earingmemberflym);
        mapData.put("earning_members_fly_f", CommonClass.earingmemberflyf);
        mapData.put("wheather_cbo_member", weathershg);
        mapData.put("cbo_name", CommonClass.shgname);
        mapData.put("start_year_of_cbo", CommonClass.startdateofshg);
        mapData.put("year_join_cbo", CommonClass.edoshg);
        mapData.put("user_id", userId);

        System.out.println(mapData);

        Log.d("hsgubs",mapData.toString());

        CommonClass.APP_TOKEN = CommonClass.getToken(AddshgbeneficiaryActivity.this);

        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.addbeneficary(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<BeneficaryResponse>() {
            @Override
            public void onResponse(Call<BeneficaryResponse> call, Response<BeneficaryResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    Toast.makeText(AddshgbeneficiaryActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();

                    callBeneFiciaryData();
                }
            }

            @Override
            public void onFailure(Call<BeneficaryResponse> call, Throwable t) {
                pd.dismiss();
            }
        });

    }

    // Benificiary Data
    public void callBeneFiciaryData() {
        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", userId);
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.getBenificiaryData(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<Example_Benificiary>() {
            @Override
            public void onResponse(Call<Example_Benificiary> call, retrofit2.Response<Example_Benificiary> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                //isprogress.setValue(10);
                if (response.body() != null) {
                    //isprogress.setValue(10);
                    BeneData beneData = new BeneData();
                    localRepo.deleteBene(beneData);
                    localRepo.deleteBene();

                    for (BeneData data : response.body().getData()) {

                        localRepo.insertBene(data);
                    }
                    startActivity(new Intent(AddshgbeneficiaryActivity.this, BeneficaryListActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
            }

            @Override
            public void onFailure(Call<Example_Benificiary> call, Throwable t) {

                Toast.makeText(AddshgbeneficiaryActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                //isprogress.setValue(10);
            }
        });
    }


}