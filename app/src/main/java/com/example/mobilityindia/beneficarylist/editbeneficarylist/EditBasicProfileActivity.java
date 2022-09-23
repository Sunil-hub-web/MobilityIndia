package com.example.mobilityindia.beneficarylist.editbeneficarylist;

import static com.example.mobilityindia.constant.CommonClass.getAnnualIncomeMaster;
import static com.example.mobilityindia.constant.CommonClass.getCasteMaster;
import static com.example.mobilityindia.constant.CommonClass.getEconomicMaster;
import static com.example.mobilityindia.constant.CommonClass.getEducationMaster;
import static com.example.mobilityindia.constant.CommonClass.getMaritialStatusMaster;
import static com.example.mobilityindia.constant.CommonClass.getOccupationMaster;
import static com.example.mobilityindia.constant.CommonClass.getPurposeVisitMaster;
import static com.example.mobilityindia.constant.CommonClass.getReligionMaster;
import static com.example.mobilityindia.constant.CommonClass.getTypeDisMaster;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.example.mobilityindia.R;
import com.example.mobilityindia.beneficarylist.VisitModelClass;
import com.example.mobilityindia.beneficarylist.view.BeneficaryDetailActivity;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEditBasicProfileBinding;
import com.example.mobilityindia.livelihood.EditLivelihoodAtivity;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class EditBasicProfileActivity extends AppCompatActivity {

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
    List<String> purposeofvisit;
    List<String> purposeofvisitID;
    LocalRepo localRepo;
    String disabilityID = "";
    String casteId = "",relativeId = "",economicId = "",annualId = "", maritalId = "",eduId = "",occupationId = "",disabilityId = "",
          subdisabilityId = "",purposeId = "",accountType = "",selectedItem = "";
    ArrayList<VisitModelClass> purposeofvisit1;
    String[] str_purposeofvisit;
    ArrayList<String> idarray = new ArrayList<>();
    ArrayList<String> idarray1 = new ArrayList<>();
    boolean[] selectDay;
    private ActivityEditBasicProfileBinding binding;

    List<String> visitename = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_basic_profile);
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
        religionname = new ArrayList<>();
        religionID =  new ArrayList<>();
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
        localRepo = new LocalRepo(EditBasicProfileActivity.this);



        final Calendar calendar = Calendar.getInstance();
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);

        binding.dojshg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(EditBasicProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                    //    binding.dojshg.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
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
                datePicker = new DatePickerDialog(EditBasicProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
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


       /* String castemasterdata = getCasteMaster(EditBasicProfileActivity.this);
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
        binding.caste.setAdapter(castemasterr);*/
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


      /*  String religionmaster = getReligionMaster(EditBasicProfileActivity.this);
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
        binding.religion.setAdapter(adapterenu);*/
        binding.religion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.religion.showDropDown();
            }
        });
        binding.religion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                relativeId = religionID.get(position);
                CommonClass.reigion = relativeId;

            }
        });


       /* String econimicstatus = getEconomicMaster(EditBasicProfileActivity.this);
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
        binding.ecconomicstatus.setAdapter(adaptereconimic);*/
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


       /* String getannualincomemaster = getAnnualIncomeMaster(EditBasicProfileActivity.this);
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
        binding.annualincome.setAdapter(adapterannual);*/
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


     /*   String meritalstatusmaster = getMaritialStatusMaster(EditBasicProfileActivity.this);
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
        binding.meritalstatus.setAdapter(adaptermerital);*/
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


       /* String getoccuptio = getOccupationMaster(EditBasicProfileActivity.this);
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
        binding.occuption.setAdapter(adapteroccuption);*/
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


       /* String geteducation = getEducationMaster(EditBasicProfileActivity.this);
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
        binding.edu.setAdapter(adapteredu);*/
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


       /* String gettdisability = getTypeDisMaster(EditBasicProfileActivity.this);
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
        binding.typeofdisability.setAdapter(adapterdisability);*/
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


        String getpurposeofvisit = getPurposeVisitMaster(EditBasicProfileActivity.this);
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

        multiSelectSpinnerWithSearch.setHintText("Select your Purpose of Visit");

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

                binding.purposeofvisit.setText("");
                Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());

                String userid = String.valueOf(items.get(i).getId());
                String name = String.valueOf(items.get(i).getName());
                idarray.add(userid);
                idarray1.add(name);
            }
        });
       /* ArrayAdapter<String> adapterpurposevisit = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, purposeofvisit);
        adapterpurposevisit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.purposeofvisit.setAdapter(adapterpurposevisit);
        //binding.purposeofvisit.setThreshold(1);
        binding.purposeofvisit.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        binding.purposeofvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.purposeofvisit.showDropDown();
*//*                AlertDialog.Builder builder = new AlertDialog.Builder(AddbasicprofilebenificiaryActivity.this);
                builder.setTitle("Select purposeofvisit");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(str_purposeofvisit, selectDay, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        //Check condition

                        if(isChecked){

                            //when check box selected
                            //add position in the list

                            purposeofvisit1.add(which);

                            //sort day list
                            Collections.sort(purposeofvisit1);

                        }else{

                            //checkbox unselected
                            //remove position from day list

                            purposeofvisit1.remove(which);
                        }

                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StringBuilder stringBuilder = new StringBuilder();

                        for(int j = 0;j<purposeofvisit1.size();j++){

                            stringBuilder.append(str_purposeofvisit[purposeofvisit1.get(j)]);

                            if(j != purposeofvisit1.size()-1){

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

                builder.show();*//*
            }
        });
        binding.purposeofvisit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                purposeId = purposeofvisitID.get(position);
                idarray.add(purposeId);


            }
        });
*/

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
                        Toast.makeText(EditBasicProfileActivity.this, "Value should be more then 100. ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        callofflinelocaldatdata();
        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.name.getText().toString().equals("") || binding.name.getText().toString() == null) {
                    Toast.makeText(EditBasicProfileActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();

                }else if (binding.parentname.getText().toString().equals("") || binding.parentname.getText().toString() == null) {
                    Toast.makeText(EditBasicProfileActivity.this, "Please Enter Gaurdian Name", Toast.LENGTH_SHORT).show();

                } else if (binding.dojshg.getText().toString().equals("") || binding.dojshg.getText().toString() == null) {
                    Toast.makeText(EditBasicProfileActivity.this, "Please Enter Date of Birth", Toast.LENGTH_SHORT).show();

                } else if (binding.gender.getText().toString().equals("") || binding.gender.getText().toString() == null) {
                    Toast.makeText(EditBasicProfileActivity.this, "Please Select Gender", Toast.LENGTH_SHORT).show();

                } else if (binding.typeofdisability.getText().toString().equals("") || binding.typeofdisability.getText().toString() == null) {

                    Toast.makeText(EditBasicProfileActivity.this, "Please Select Type of Disability", Toast.LENGTH_SHORT).show();

                } else if (binding.typeofbenificary.getText().toString().equals("") || binding.typeofbenificary.getText().toString() == null) {

                    Toast.makeText(EditBasicProfileActivity.this, "Please Select Type of Beneficary", Toast.LENGTH_SHORT).show();

                } else {
                    callupdatelocaledata();
                }
            }
        });

        binding.percentofdisability.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.percentofdisability.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval<=100){

                    }else{
                        binding.percentofdisability.setText("");
                        Toast.makeText(EditBasicProfileActivity.this, "Value should be more then 100. ", Toast.LENGTH_SHORT).show();
                    }


                }


                }
        });

    }

    private void callofflinelocaldatdata()
    {
        localRepo.getSelectedBene(CommonClass.tempid).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {
                if(singleMember.size() > 0) {

                    binding.DateOfRegistration.setText(singleMember.get(0).getRegistrationDate());
                    binding.name.setText(singleMember.get(0).getName());
                    binding.parentname.setText(singleMember.get(0).getParentName());
                    binding.relaship.setText(singleMember.get(0).getRelation());
                    binding.dojshg.setText(singleMember.get(0).getDob());
                    binding.age.setText(singleMember.get(0).getAge());
                    binding.gender.setText(singleMember.get(0).getGender());
                    String[] itemNames = getResources().getStringArray(R.array.gender);
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.gender.setAdapter(adapter2);

                    binding.edu.setText(singleMember.get(0).getEducationName());

                    String geteducation = getEducationMaster(EditBasicProfileActivity.this);
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


                    binding.caste.setText(singleMember.get(0).getCasteName());
                    String castemasterdata = getCasteMaster(EditBasicProfileActivity.this);
                    System.out.println("enumeroooooooooooooooo" + castemasterdata);
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



                    binding.religion.setText(singleMember.get(0).getReligionName());
                    String religionmaster = getReligionMaster(EditBasicProfileActivity.this);
                    System.out.println("enumeroooooooooooooooo" + religionmaster);
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
                    binding.religion.setAdapter(adapterenu);



                    binding.adhaarno.setText(singleMember.get(0).getAdhaarNo());


                    binding.ecconomicstatus.setText(singleMember.get(0).getEconomicName());
                    String econimicstatus = getEconomicMaster(EditBasicProfileActivity.this);
                    System.out.println("enumeroooooooooooooooo" + econimicstatus);
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


                    binding.annualincome.setText(singleMember.get(0).getAnnualName());
                    String getannualincomemaster = getAnnualIncomeMaster(EditBasicProfileActivity.this);
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


                    binding.meritalstatus.setText(singleMember.get(0).getMaritalName());
                    String meritalstatusmaster = getMaritialStatusMaster(EditBasicProfileActivity.this);
                    System.out.println("enumeroooooooooooooooo" + meritalstatusmaster);
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


                    binding.occuption.setText(singleMember.get(0).getOccupationName());
                    String getoccuptio = getOccupationMaster(EditBasicProfileActivity.this);
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

                    binding.typeofdisability.setText(singleMember.get(0).getDisabilityName());
                    String gettdisability = getTypeDisMaster(EditBasicProfileActivity.this);
                    System.out.println("enumeroooooooooooooooo" + gettdisability);
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



                    binding.typeofsubdisability.setText(singleMember.get(0).getTypeOfSubDisability());
                    binding.percentofdisability.setText(singleMember.get(0).getPercentageOfDisability());


                    binding.phpammount.setText(singleMember.get(0).getPhpAmount());
                    String[] phpamount = getResources().getStringArray(R.array.selectone);
                    ArrayAdapter<String> adapterphp = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, phpamount);
                    adapterphp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.phpammount.setAdapter(adapterphp);



                    binding.typeofbenificary.setText(singleMember.get(0).getTypeOfBeneficiary());
                    String[] typebene = getResources().getStringArray(R.array.benef);
                    ArrayAdapter<String> adapbene = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, typebene);
                    adapbene.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.typeofbenificary.setAdapter(adapbene);

                    binding.idcardno.setText(singleMember.get(0).getIdCardNo());

                    String visit = singleMember.get(0).getPurposeOfVisit();
                    List<String> myList = new ArrayList<String>(Arrays.asList(visit.split(",")));

                    visitename = singleMember.get(0).visitName;

                    StringBuffer sb = new StringBuffer();

                    if (visitename.size() != 0) {

                        for (String s : visitename) {

                            sb.append(s);
                            sb.append(",");
                        }

                        String visitname = sb.toString();

                        binding.purposeofvisit.setText(visitname);


                    }else{

                        binding.purposeofvisit.setText("");
                    }

                    binding.youhavebankaccount.setText(singleMember.get(0).getHaveBankAcc());
                    binding.nameofpwdcwd.setText(singleMember.get(0).getNameOfPwdCwd());
                    binding.VisitDetails.setText(singleMember.get(0).getPurposeVisitDetails());

                    if(singleMember.get(0).getHaveBankAcc().equals("No")){

                        binding.accnumber.setVisibility(View.GONE);
                        binding.accholdname.setVisibility(View.GONE);
                        binding.ifsc.setVisibility(View.GONE);
                        binding.typeacc.setVisibility(View.GONE);

                    }else{


                        binding.accnumber.setVisibility(View.VISIBLE);
                        binding.accholdname.setVisibility(View.VISIBLE);
                        binding.ifsc.setVisibility(View.VISIBLE);
                        binding.typeacc.setVisibility(View.VISIBLE);

                        binding.AccountNumber.setText(singleMember.get(0).getAccNum());
                        binding.HolderName.setText(singleMember.get(0).getAccHolderName());
                        binding.IFSCCode.setText(singleMember.get(0).getIfsc());
                        binding.Typeofaccount.setText(singleMember.get(0).getAccType());
                    }

                    String[] itemNamesbank = getResources().getStringArray(R.array.selectone1);
                    ArrayAdapter<String> bankaccountadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNamesbank);
                    bankaccountadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.youhavebankaccount.setAdapter(bankaccountadapter);

                    String[] itemNamesaccount = getResources().getStringArray(R.array.accounttype);
                    ArrayAdapter<String> accounttypeadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNamesaccount);
                    accounttypeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.Typeofaccount.setAdapter(accounttypeadapter);
                }
            }
        });
    }

    private void callupdatelocaledata(){
        localRepo.getSelectedBene(CommonClass.tempid).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {
                if (singleMember.size() > 0)
                {
                    singleMember.get(0).setTempId(CommonClass.tempid);
                    singleMember.get(0).setRegistrationDate(binding.DateOfRegistration.getText().toString());
                    singleMember.get(0).setName(binding.name.getText().toString());
                    singleMember.get(0).setParentName(binding.parentname.getText().toString());
                    singleMember.get(0).setRelation(binding.relaship.getText().toString());
                    singleMember.get(0).setDob(binding.dojshg.getText().toString());
                    singleMember.get(0).setAge(binding.age.getText().toString());
                    singleMember.get(0).setGender(binding.gender.getText().toString());

                    singleMember.get(0).setCasteName(binding.caste.getText().toString());
                    singleMember.get(0).setCaste(CommonClass.caste);

                    singleMember.get(0).setReligionName(binding.religion.getText().toString());
                    singleMember.get(0).setReligion(CommonClass.reigion);

                    singleMember.get(0).setAdhaarNo(binding.adhaarno.getText().toString());

                    singleMember.get(0).setAnnualName(binding.annualincome.getText().toString());
                    singleMember.get(0).setAnnualIncome(CommonClass.annualincome);

                    singleMember.get(0).setEconomicName(binding.ecconomicstatus.getText().toString());
                    singleMember.get(0).setEconomicStatus(CommonClass.ecconomicstatus);

                    singleMember.get(0).setMaritalName(binding.meritalstatus.getText().toString());
                    singleMember.get(0).setMaritalStatus(CommonClass.meritalstatus);

                    singleMember.get(0).setEducationName(binding.edu.getText().toString());
                    singleMember.get(0).setEducation(CommonClass.edu);

                    singleMember.get(0).setOccupationName(binding.occuption.getText().toString());
                    singleMember.get(0).setOccupation(CommonClass.occuption);

                    singleMember.get(0).setDisabilityName(binding.typeofdisability.getText().toString());
                    singleMember.get(0).setTypeOfDisability(CommonClass.typeofdisability);

                    singleMember.get(0).setTypeOfSubDisability(binding.typeofsubdisability.getText().toString());
                    singleMember.get(0).setTypeOfSubDisability(CommonClass.typeofsubdisability);

                    singleMember.get(0).setPercentageOfDisability(binding.percentofdisability.getText().toString());
                    singleMember.get(0).setIdCardNo(binding.idcardno.getText().toString());
                    singleMember.get(0).setPhpAmount(binding.phpammount.getText().toString());
                    singleMember.get(0).setTypeOfBeneficiary(binding.typeofbenificary.getText().toString());
                    StringBuffer sb = new StringBuffer();
                    singleMember.get(0).setPurposeVisitDetails(binding.VisitDetails.getText().toString().trim());
                    singleMember.get(0).setNameOfPwdCwd(binding.nameofpwdcwd.getText().toString().trim());
                    singleMember.get(0).setHaveBankAcc(binding.youhavebankaccount.getText().toString().trim());
                    singleMember.get(0).setAccHolderName(binding.HolderName.getText().toString().trim());
                    singleMember.get(0).setAccNum(binding.AccountNumber.getText().toString().trim());
                    singleMember.get(0).setIfsc(binding.IFSCCode.getText().toString().trim());
                    singleMember.get(0).setAccType(binding.Typeofaccount.getText().toString().trim());

                    singleMember.get(0).setFlag("update");

                    StringBuffer sb1 = new StringBuffer();

                    if (idarray.size() != 0) {

                        for (String s : idarray) {

                            sb1.append(s);
                            sb1.append(",");
                        }

                        String services_Id = sb1.toString();
                        // remove last character (,)
                        services_Id = services_Id.substring(0, services_Id.length() - 1);

                        singleMember.get(0).setPurposeOfVisit(services_Id);

                        for (String s1 : idarray1) {

                            sb1.append(s1);
                            sb1.append(",");
                        }

                        String services_name = sb1.toString();
                        // remove last character (,)
                        services_name = services_name.substring(0, services_name.length() - 1);
                        singleMember.get(0).setVisitName(idarray1);

                    } else {

                        singleMember.get(0).setPurposeOfVisit(binding.purposeofvisit.getText().toString());

                    }
                    localRepo.updateBene(singleMember.get(0));

                    onBackPressed();

                   /* Intent intent = new Intent(EditBasicProfileActivity.this, BeneficaryDetailActivity.class);
                    startActivity(intent);*/

                    Toast.makeText(EditBasicProfileActivity.this, " Basic profile updated in locally.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

//    public void  getSubDisability(String disabilityid){
//        ArrayList<String>distlist = new ArrayList<>();
//
//        localRepo.getSelectedSubDisability(Integer.parseInt(disabilityid)).observe(this, new Observer<List<SubDisabilityData>>() {
//            @Override
//            public void onChanged(@Nullable List<SubDisabilityData> vastiDataa) {
//                if(vastiDataa.size() >0)
//                {
//                    for(int i =0;i<vastiDataa.size();i++){
//                        distlist.add(vastiDataa.get(i).getSubDisabilityName());
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, distlist);
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    binding.typeodsubdisability.setAdapter(adapter);
//
//                }
//
//            }
//        });
//    }
}