package com.example.mobilityindia.beneficarylist.addbeneficiary;

import static com.example.mobilityindia.constant.CommonClass.getAnnualIncomeMaster;
import static com.example.mobilityindia.constant.CommonClass.getCasteMaster;
import static com.example.mobilityindia.constant.CommonClass.getEconomicMaster;
import static com.example.mobilityindia.constant.CommonClass.getEducationMaster;
import static com.example.mobilityindia.constant.CommonClass.getMaritialStatusMaster;
import static com.example.mobilityindia.constant.CommonClass.getOccupationMaster;
import static com.example.mobilityindia.constant.CommonClass.getPurposeVisitMaster;
import static com.example.mobilityindia.constant.CommonClass.getReligionMaster;
import static com.example.mobilityindia.constant.CommonClass.getTypeDisMaster;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.example.mobilityindia.R;
import com.example.mobilityindia.beneficarylist.VisitModelClass;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityAddbasicprofilebenificiaryBinding;
import com.example.mobilityindia.sync.repository.LocalRepo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AddbasicprofilebenificiaryActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatePickerDialog datePicker;
    List<String> religionname;
    List<String> religionID;
    List<String> castemaster;
    List<String> casteIDmaster;
    List<String> meritalstatus;
    List<String> meritalstatusID;
    List<String> economiclist;
    List<String> economicIDlist;
    List<String> edu;
    List<String> eduID;
    List<String> annualincome;
    List<String> annualincomeID;
    List<String> occuption;
    List<String> occuptionID;
    List<String> typedisabilityID;
    List<String> typedisability;
    List<String> typeofsubdisability;
    List<String> typeofsubdisabilityID;
    List<String> phpammount;
    List<String> typeofbeneficary;
    ArrayList<String> purposeofvisit;
    ArrayList<VisitModelClass> purposeofvisit1;
    String[] str_purposeofvisit;
    List<String> purposeofvisitID;
    ArrayList<String> idarray = new ArrayList<>();
    boolean[] selectDay;
    LocalRepo localRepo;
    String disabilityID = "", selectedItem = "", accountType = "";
    String casteId = "", relativeId = "", economicId = "", annualId = "", maritalId = "", eduId = "", occupationId = "", disabilityId = "",
            subdisabilityId = "", purposeId = "";
    private ActivityAddbasicprofilebenificiaryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addbasicprofilebenificiary);
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
        religionname = new ArrayList<>();
        religionID = new ArrayList<>();
        castemaster = new ArrayList<>();
        casteIDmaster = new ArrayList<>();
        meritalstatus = new ArrayList<>();
        meritalstatusID = new ArrayList<>();
        economiclist = new ArrayList<>();
        economicIDlist = new ArrayList<>();
        edu = new ArrayList<>();
        eduID = new ArrayList<>();
        annualincome = new ArrayList<>();
        annualincomeID = new ArrayList<>();
        typedisabilityID = new ArrayList<>();
        occuption = new ArrayList<>();
        occuptionID = new ArrayList<>();
        typedisability = new ArrayList<>();
        typeofsubdisability = new ArrayList<>();
        typeofsubdisabilityID = new ArrayList<>();
        phpammount = new ArrayList<>();
        typeofbeneficary = new ArrayList<>();
        purposeofvisit = new ArrayList<>();
        purposeofvisitID = new ArrayList<>();
        purposeofvisit1 = new ArrayList<>();
        localRepo = new LocalRepo(AddbasicprofilebenificiaryActivity.this);

        binding.nameofpwdcwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!binding.nameofpwdcwd.getText().toString().isEmpty()) {
                    //int tempval = Integer.parseInt(s.toString());
                    String text = binding.nameofpwdcwd.getText().toString().trim();
                    int count = text.length();
                    if (count <= 100) {
                    } else {
                        binding.nameofpwdcwd.setText("");
                        Toast.makeText(AddbasicprofilebenificiaryActivity.this, "Value should be more then 100. ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        String[] itemNamesbank = getResources().getStringArray(R.array.selectone1);
        ArrayAdapter<String> bankaccountadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNamesbank);
        bankaccountadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.youhavebankaccount.setAdapter(bankaccountadapter);

        String[] itemNamesaccount = getResources().getStringArray(R.array.accounttype);
        ArrayAdapter<String> accounttypeadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNamesaccount);
        accounttypeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.Typeofaccount.setAdapter(accounttypeadapter);

        binding.youhavebankaccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedItem = binding.youhavebankaccount.getAdapter().getItem(position).toString();

                if (selectedItem.equals("Yes")) {

                    binding.accholdname.setVisibility(View.VISIBLE);
                    binding.typeacc.setVisibility(View.VISIBLE);
                    binding.ifsc.setVisibility(View.VISIBLE);
                    binding.accnumber.setVisibility(View.VISIBLE);


                } else {

                    binding.accholdname.setVisibility(View.GONE);
                    binding.typeacc.setVisibility(View.GONE);
                    binding.ifsc.setVisibility(View.GONE);
                    binding.accnumber.setVisibility(View.GONE);

                }
            }
        });

        binding.Typeofaccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                accountType = binding.Typeofaccount.getAdapter().getItem(position).toString();
            }
        });

        binding.youhavebankaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                (binding.youhavebankaccount).showDropDown();
            }
        });

        binding.Typeofaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                (binding.Typeofaccount).showDropDown();
            }
        });

        binding.dojshg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(AddbasicprofilebenificiaryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        //  binding.dojshg.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                        binding.dojshg.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        int yearr = Calendar.getInstance().get(Calendar.YEAR);
                        int finalage = yearr - year;
                        String finalstringage = String.valueOf(finalage);
                        binding.age.setText(finalstringage);
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.DateOfRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(AddbasicprofilebenificiaryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        //  binding.dojshg.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                        binding.DateOfRegistration.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        /*int yearr = Calendar.getInstance().get(Calendar.YEAR);
                        int finalage = yearr - year;
                        String finalstringage = String.valueOf(finalage);
                        //binding.age.setText(finalstringage);*/
                    }
                }, year, month, day);

                datePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });

        binding.gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.gender).showDropDown();
            }
        });
        binding.gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.gender.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames = getResources().getStringArray(R.array.gender);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.gender.setAdapter(adapter2);


        String castemasterdata = getCasteMaster(AddbasicprofilebenificiaryActivity.this);
        Log.d("hsgjbsja", castemasterdata);

        try {
            JSONObject obj = new JSONObject(castemasterdata);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    castemaster.add(jsonObject.getString("caste_name"));
                    casteIDmaster.add(jsonObject.getString("id"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> castemasterr = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, castemaster);
        castemasterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.caste.setAdapter(castemasterr);
        binding.caste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.caste.showDropDown();
            }
        });
        binding.caste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                casteId = casteIDmaster.get(position);
                CommonClass.caste = casteId;
            }
        });


        String religionmaster = getReligionMaster(AddbasicprofilebenificiaryActivity.this);
        try {
            JSONObject obj = new JSONObject(religionmaster);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    religionname.add(jsonObject.getString("religion_name"));
                    religionID.add(jsonObject.getString("id"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapterenu = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, religionname);
        adapterenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.reigion.setAdapter(adapterenu);
        binding.reigion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.reigion.showDropDown();
            }
        });
        binding.reigion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                relativeId = religionID.get(position);
                CommonClass.reigion = relativeId;

            }
        });


        String econimicstatus = getEconomicMaster(AddbasicprofilebenificiaryActivity.this);
        try {
            JSONObject obj = new JSONObject(econimicstatus);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    economiclist.add(jsonObject.getString("economic_name"));
                    economicIDlist.add(jsonObject.getString("id"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adaptereconimic = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, economiclist);
        adaptereconimic.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ecconomicstatus.setAdapter(adaptereconimic);
        binding.ecconomicstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.ecconomicstatus.showDropDown();
            }
        });
        binding.ecconomicstatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                economicId = economicIDlist.get(position);
                CommonClass.ecconomicstatus = economicId;

            }
        });


        String getannualincomemaster = getAnnualIncomeMaster(AddbasicprofilebenificiaryActivity.this);
        try {
            JSONObject obj = new JSONObject(getannualincomemaster);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    annualincome.add(jsonObject.getString("annual_name"));
                    annualincomeID.add(jsonObject.getString("id"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapterannual = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, annualincome);
        adapterannual.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.annualincome.setAdapter(adapterannual);
        binding.annualincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.annualincome.showDropDown();
            }
        });
        binding.annualincome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                annualId = annualincomeID.get(position);
                CommonClass.annualincome = annualId;

            }
        });


        String meritalstatusmaster = getMaritialStatusMaster(AddbasicprofilebenificiaryActivity.this);
        try {
            JSONObject obj = new JSONObject(meritalstatusmaster);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    meritalstatus.add(jsonObject.getString("marital_name"));
                    meritalstatusID.add(jsonObject.getString("id"));

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adaptermerital = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, meritalstatus);
        adaptermerital.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.meritalstatus.setAdapter(adaptermerital);
        binding.meritalstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.meritalstatus.showDropDown();
            }
        });
        binding.meritalstatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                maritalId = meritalstatusID.get(position);
                CommonClass.meritalstatus = maritalId;

            }
        });


        String getoccuptio = getOccupationMaster(AddbasicprofilebenificiaryActivity.this);
        try {
            JSONObject obj = new JSONObject(getoccuptio);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    occuption.add(jsonObject.getString("occupation_name"));
                    occuptionID.add(jsonObject.getString("id"));

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapteroccuption = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, occuption);
        adapteroccuption.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.occuption.setAdapter(adapteroccuption);
        binding.occuption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.occuption.showDropDown();
            }
        });
        binding.occuption.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                occupationId = occuptionID.get(position);
                CommonClass.occuption = occupationId;

            }
        });


        String geteducation = getEducationMaster(AddbasicprofilebenificiaryActivity.this);
        try {
            JSONObject obj = new JSONObject(geteducation);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    edu.add(jsonObject.getString("education_name"));
                    eduID.add(jsonObject.getString("id"));


                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapteredu = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, edu);
        adapteredu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.edu.setAdapter(adapteredu);
        binding.edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edu.showDropDown();
            }
        });
        binding.edu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                eduId = eduID.get(position);
                CommonClass.edu = eduId;

            }
        });


        String gettdisability = getTypeDisMaster(AddbasicprofilebenificiaryActivity.this);
        try {
            JSONObject obj = new JSONObject(gettdisability);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    typedisability.add(jsonObject.getString("disability_name"));
                    typedisabilityID.add(jsonObject.getString("id"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapterdisability = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, typedisability);
        adapterdisability.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.typeofdisability.setAdapter(adapterdisability);
        binding.typeofdisability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.typeofdisability.showDropDown();
            }
        });
        binding.typeofdisability.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                disabilityID = typedisabilityID.get(position);
                //   getSubDisability(disabilityID);
                CommonClass.typeofdisability = disabilityID;
            }
        });


//        binding.typeofsubdisability.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                binding.typeofsubdisability.showDropDown();
//            }
//        });
//        binding.typeofsubdisability.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                subdisabilityId = typedisabilityID.get(position);
//                CommonClass.typeofsubdisability = subdisabilityId;
//
//            }
//        });
//

        binding.phpammount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.phpammount.showDropDown();
            }
        });
        binding.phpammount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                SlumID = slumId.get(position);
//                CommonClass.SlumID = SlumID;
            }
        });
        String[] phpamount = getResources().getStringArray(R.array.selectone);
        ArrayAdapter<String> adapterphp = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, phpamount);
        adapterphp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.phpammount.setAdapter(adapterphp);


        binding.typeofbenificary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.typeofbenificary.showDropDown();
            }
        });
        binding.typeofbenificary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                SlumID = slumId.get(position);
//                CommonClass.SlumID = SlumID;
            }
        });
        String[] typebene = getResources().getStringArray(R.array.benef);
        ArrayAdapter<String> adapbene = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, typebene);
        adapbene.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.typeofbenificary.setAdapter(adapbene);


        String getpurposeofvisit = getPurposeVisitMaster(AddbasicprofilebenificiaryActivity.this);
        try {
            JSONObject obj = new JSONObject(getpurposeofvisit);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    purposeofvisit.add(jsonObject.getString("visit_name"));
                    purposeofvisitID.add(jsonObject.getString("id"));

                    String name = jsonObject.getString("visit_name");
                    String id = jsonObject.getString("id");

                    long lon_id = Long.valueOf(id);

                    VisitModelClass visitModelClass = new VisitModelClass(lon_id,name);
                    purposeofvisit1.add(visitModelClass);

                    str_purposeofvisit = Arrays.copyOf(
                            purposeofvisit.toArray(), purposeofvisit.size(), String[].class);
                }
                selectDay = new boolean[purposeofvisit.size()];
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final List<KeyPairBoolData> listArray1 = new ArrayList<>();
        for (int i = 0; i < purposeofvisit1.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();

            h.setId(purposeofvisit1.get(i).getId());
            h.setName(purposeofvisit1.get(i).getName());
            //h.setSelected(i < 5);
            listArray1.add(h);
        }

        MultiSpinnerSearch multiSelectSpinnerWithSearch = findViewById(R.id.multipleItemSelectionSpinner);

        // Pass true If you want searchView above the list. Otherwise false. default = true.
        multiSelectSpinnerWithSearch.setSearchEnabled(true);

        multiSelectSpinnerWithSearch.setHintText("Purpose of Visit");

        //A text that will display in clear text button
        multiSelectSpinnerWithSearch.setClearText("Close & Clear");

        // A text that will display in search hint.
        multiSelectSpinnerWithSearch.setSearchHint("Select your Purpose of Visit");

        // Set text that will display when search result not found...
        multiSelectSpinnerWithSearch.setEmptyTitle("Not Data Found!");

        // If you will set the limit, this button will not display automatically.
        multiSelectSpinnerWithSearch.setShowSelectAllButton(true);

        // Removed second parameter, position. Its not required now..
        // If you want to pass preselected items, you can do it while making listArray,
        // Pass true in setSelected of any item that you want to preselect
        multiSelectSpinnerWithSearch.setItems(listArray1, items -> {
            //The followings are selected items.
            for (int i = 0; i < items.size(); i++) {
                Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());

                String userid = String.valueOf(items.get(i).getId());
                idarray.add(userid);
            }
        });


        /*ArrayAdapter<String> adapterpurposevisit = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, purposeofvisit);
        adapterpurposevisit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.purposeofvisit.setAdapter(adapterpurposevisit);
        binding.purposeofvisit.setThreshold(1);
        //binding.purposeofvisit.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        binding.purposeofvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // binding.purposeofvisit.showDropDown();

                AlertDialog.Builder builder = new AlertDialog.Builder(AddbasicprofilebenificiaryActivity.this);
                builder.setTitle("Select purposeofvisit");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(str_purposeofvisit, selectDay, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        //Check condition
                        try {

                            if (isChecked) {

                                //when check box selected
                                //add position in the list

                                purposeofvisit1.add(which);

                                purposeId = purposeofvisitID.get(which);
                                idarray.add(purposeId);

                                Log.d("hsgub", idarray.toString());

                                //sort day list
                                Collections.sort(purposeofvisit1);


                            } else {

                                //checkbox unselected
                                //remove position from day list

                                purposeofvisit1.remove(which);
                                idarray.remove(which);

                                Log.d("hsgub", idarray.toString());
                            }

                        } catch (Exception e) {

                            e.printStackTrace();
                        }

                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j = 0; j < purposeofvisit1.size(); j++) {

                            stringBuilder.append(str_purposeofvisit[purposeofvisit1.get(j)]);

                            if (j != purposeofvisit1.size() - 1) {

                                stringBuilder.append(", ");
                            }
                        }
                        binding.purposeofvisit.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });

                builder.show();
            }
        });
        *//*binding.purposeofvisit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                purposeId = purposeofvisitID.get(position);
                idarray.add(purposeId);


            }
        });*/


        binding.percentofdisability.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!binding.percentofdisability.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval <= 100) {
                    } else {
                        binding.percentofdisability.setText("");
                        //Toast.makeText(AddbasicprofilebenificiaryActivity.this, "Value should be more then 100. ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(binding.shgnamee.getText().toString().trim()) || binding.shgnamee.getText().toString() == null) {
                    Toast.makeText(AddbasicprofilebenificiaryActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();

                } else if (binding.parentname.getText().toString().trim().equals("") || binding.parentname.getText().toString() == null) {
                    Toast.makeText(AddbasicprofilebenificiaryActivity.this, "Please Enter Gaurdian Name", Toast.LENGTH_SHORT).show();

                } else if (binding.dojshg.getText().toString().trim().equals("") || binding.dojshg.getText().toString() == null) {
                    Toast.makeText(AddbasicprofilebenificiaryActivity.this, "Please Enter Date of Birth", Toast.LENGTH_SHORT).show();

                } else if (binding.gender.getText().toString().trim().equals("") || binding.gender.getText().toString() == null) {
                    Toast.makeText(AddbasicprofilebenificiaryActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();

                } else if (binding.typeofdisability.getText().toString().trim().equals("") || binding.typeofdisability.getText().toString() == null) {

                    Toast.makeText(AddbasicprofilebenificiaryActivity.this, "Please Select Type of Disability", Toast.LENGTH_SHORT).show();

                } else if (binding.typeofbenificary.getText().toString().trim().equals("") || binding.typeofbenificary.getText().toString() == null) {

                    Toast.makeText(AddbasicprofilebenificiaryActivity.this, "Please Select Type of Beneficary", Toast.LENGTH_SHORT).show();

                } else if (binding.DateOfRegistration.getText().toString().trim().equals("") || binding.DateOfRegistration.getText().toString() == null) {

                    Toast.makeText(AddbasicprofilebenificiaryActivity.this, "Please Enter Date Of Registration", Toast.LENGTH_SHORT).show();

                } else if (binding.youhavebankaccount.getText().toString().trim().equals("") || binding.youhavebankaccount.getText().toString() == null) {

                    Toast.makeText(AddbasicprofilebenificiaryActivity.this, "Please Select your Bank Account", Toast.LENGTH_SHORT).show();

                } else {

                    if (!TextUtils.isEmpty(binding.adhaarno.getText())) {

                        if (binding.adhaarno.getText().toString().trim().length() != 12) {

                            Toast.makeText(AddbasicprofilebenificiaryActivity.this, "Please Enter 12 aadhar Digit Number ", Toast.LENGTH_SHORT).show();

                        } else {
                            CommonClass.shgnameee = binding.shgnamee.getText().toString();
                            CommonClass.parent = binding.parentname.getText().toString();
                            CommonClass.startdateofshgg = binding.startdateofshg.getText().toString();
                            CommonClass.dojshg = binding.dojshg.getText().toString();
                            CommonClass.age = binding.age.getText().toString();
                            CommonClass.gender = binding.gender.getText().toString();
                            CommonClass.caste = casteId;
                            CommonClass.reigion = relativeId;
                            CommonClass.adhaarno = binding.adhaarno.getText().toString();

                            CommonClass.ecconomicstatus = economicId;
                            CommonClass.annualincome = annualId;
                            CommonClass.meritalstatus = maritalId;
                            CommonClass.edu = eduId;
                            CommonClass.occuption = occupationId;

                            CommonClass.typeofdisability = disabilityID;
                            subdisabilityId = binding.typeofsubdisability.getText().toString().trim();
                            CommonClass.typeofsubdisability = subdisabilityId;
                            CommonClass.percentofdisability = binding.percentofdisability.getText().toString();
                            CommonClass.idcardno = binding.idcardno.getText().toString();
                            CommonClass.phpammount = binding.phpammount.getText().toString();
                            CommonClass.typeofbenificary = binding.typeofbenificary.getText().toString();
                            CommonClass.DateOfRegistration = binding.DateOfRegistration.getText().toString();

                            //CommonClass.purposeofvisit = binding.purposeofvisit.getText().toString();

                            Log.d("hdhbkb", CommonClass.purposeofvisit);

                            StringBuffer sb = new StringBuffer();

                            if (idarray.size() != 0) {

                                for (String s : idarray) {

                                    sb.append(s);
                                    sb.append(",");
                                }

                                String services_Id = sb.toString();

                                // remove last character (,)
                                services_Id = services_Id.substring(0, services_Id.length() - 1);

                                CommonClass.purposeofvisit = services_Id;

                                Log.d("purposeofvisitdata", services_Id);

                            } else {

                                CommonClass.purposeofvisit = "";

                            }
                            //CommonClass.purposeofvisit = purposeId;
                            CommonClass.purposevisitdetails = binding.VisitDetails.getText().toString().trim();
                            CommonClass.nameofpwdcwd = binding.nameofpwdcwd.getText().toString().trim();
                            //CommonClass.nameofpwdcwd = binding.nameofpwdcwd.getText().toString();

                            if (selectedItem.equals("Yes")) {

                                CommonClass.bankname = binding.AccountNumber.getText().toString();
                                CommonClass.accountHolderName = binding.HolderName.getText().toString();
                                CommonClass.ifscCode = binding.IFSCCode.getText().toString();
                                CommonClass.shgbankno = binding.AccountNumber.getText().toString();
                                CommonClass.accountType = accountType;
                                CommonClass.havebankacc = selectedItem;

                                //startActivity(new Intent(AddbasicprofilebenificiaryActivity.this, AddMoreprofilebeneficaryActivity.class));

                            } else {

                                CommonClass.bankname = "";
                                CommonClass.accountHolderName = "";
                                CommonClass.ifscCode = "";
                                CommonClass.accountType = "";
                                CommonClass.havebankacc = selectedItem;

                                startActivity(new Intent(AddbasicprofilebenificiaryActivity.this, AddMoreprofilebeneficaryActivity.class));
                            }

                            startActivity(new Intent(AddbasicprofilebenificiaryActivity.this, AddMoreprofilebeneficaryActivity.class));

                        }

                    } else {

                        CommonClass.shgnameee = binding.shgnamee.getText().toString();
                        CommonClass.parent = binding.parentname.getText().toString();
                        CommonClass.startdateofshgg = binding.startdateofshg.getText().toString();
                        CommonClass.dojshg = binding.dojshg.getText().toString();
                        CommonClass.age = binding.age.getText().toString();
                        CommonClass.gender = binding.gender.getText().toString();
                        CommonClass.caste = casteId;
                        CommonClass.reigion = relativeId;
                        CommonClass.adhaarno = binding.adhaarno.getText().toString();

                        CommonClass.ecconomicstatus = economicId;
                        CommonClass.annualincome = annualId;
                        CommonClass.meritalstatus = maritalId;
                        CommonClass.edu = eduId;
                        CommonClass.occuption = occupationId;

                        CommonClass.typeofdisability = disabilityID;
                        subdisabilityId = binding.typeofsubdisability.getText().toString().trim();
                        CommonClass.typeofsubdisability = subdisabilityId;
                        CommonClass.percentofdisability = binding.percentofdisability.getText().toString();
                        CommonClass.idcardno = binding.idcardno.getText().toString();
                        CommonClass.phpammount = binding.phpammount.getText().toString();
                        CommonClass.typeofbenificary = binding.typeofbenificary.getText().toString();
                        CommonClass.DateOfRegistration = binding.DateOfRegistration.getText().toString();
                        //CommonClass.purposeofvisit = binding.purposeofvisit.getText().toString();

                        Log.d("hvgvgcg", idarray.toString());

                        StringBuffer sb = new StringBuffer();

                        if (idarray.size() != 0) {

                            for (String s : idarray) {

                                sb.append(s);
                                sb.append(",");
                            }

                            String services_Id = sb.toString();

                            // remove last character (,)
                            services_Id = services_Id.substring(0, services_Id.length() - 1);

                            CommonClass.purposeofvisit = services_Id;

                            Log.d("purposeofvisitdata", services_Id);

                        } else {

                            CommonClass.purposeofvisit = "";

                        }
                        //CommonClass.purposeofvisit = purposeId;
                        CommonClass.purposevisitdetails = binding.VisitDetails.getText().toString().trim();
                        CommonClass.nameofpwdcwd = binding.nameofpwdcwd.getText().toString().trim();
                        //CommonClass.nameofpwdcwd = binding.nameofpwdcwd.getText().toString();

                        if (selectedItem.equals("Yes")) {

                            CommonClass.bankname = binding.AccountNumber.getText().toString();
                            CommonClass.accountHolderName = binding.HolderName.getText().toString();
                            CommonClass.ifscCode = binding.IFSCCode.getText().toString();
                            CommonClass.shgbankno = binding.AccountNumber.getText().toString();
                            CommonClass.accountType = accountType;
                            CommonClass.havebankacc = selectedItem;

                            //startActivity(new Intent(AddbasicprofilebenificiaryActivity.this, AddMoreprofilebeneficaryActivity.class));

                        } else {

                            CommonClass.bankname = "";
                            CommonClass.accountHolderName = "";
                            CommonClass.ifscCode = "";
                            CommonClass.accountType = "";
                            CommonClass.havebankacc = selectedItem;

                            startActivity(new Intent(AddbasicprofilebenificiaryActivity.this, AddMoreprofilebeneficaryActivity.class));
                        }

                        startActivity(new Intent(AddbasicprofilebenificiaryActivity.this, AddMoreprofilebeneficaryActivity.class));

                    }

                    //Toast.makeText(AddbasicprofilebenificiaryActivity.this, "go next", Toast.LENGTH_SHORT).show();
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

//    public void getSubDisability(String disabilityid) {
//        ArrayList<String> distlist = new ArrayList<>();
//
//        localRepo.getSelectedSubDisability(Integer.parseInt(disabilityid)).observe(this, new Observer<List<SubDisabilityData>>() {
//            @Override
//            public void onChanged(@Nullable List<SubDisabilityData> vastiDataa) {
//                if (vastiDataa.size() > 0) {
//                    for (int i = 0; i < vastiDataa.size(); i++) {
//                        distlist.add(vastiDataa.get(i).getSubDisabilityName());
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, distlist);
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    binding.typeofsubdisability.setAdapter(adapter);
//                }
//            }
//        });
//    }

}