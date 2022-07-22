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
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEditBasicProfileBinding;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditBasicProfileActivity extends AppCompatActivity {
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
          subdisabilityId = "",purposeId = "";
    private ActivityEditBasicProfileBinding binding;

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



        binding.maritalstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.maritalstatus.showDropDown();
            }
        });
        binding.maritalstatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                maritalId = meritalstatusID.get(position);
                CommonClass.meritalstatus = maritalId;
            }
        });

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

        binding.maritalstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.maritalstatus.showDropDown();
            }
        });
        binding.maritalstatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                maritalId = casteIDmaster.get(position);
                CommonClass.meritalstatus = maritalId;
            }
        });

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
               // getSubDisability(disabilityID);
                CommonClass.typeofdisability = disabilityID;

            }
        });


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

        binding.perposeofvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.perposeofvisit.showDropDown();
            }
        });
        binding.perposeofvisit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                purposeId = purposeofvisitID.get(position);
                CommonClass.purposeofvisit = purposeId;
            }
        });

        callofflinelocaldatdata();
        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.name.getText().toString().equals("") || binding.name.getText().toString() == null) {
                    Toast.makeText(EditBasicProfileActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();

                }else if (binding.parent.getText().toString().equals("") || binding.parent.getText().toString() == null) {
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
        binding.persentofdisability.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.persentofdisability.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval<=100){

                    }else{
                        binding.persentofdisability.setText("");
                        Toast.makeText(EditBasicProfileActivity.this, "Value should be more then 100. ", Toast.LENGTH_SHORT).show();
                    }


                }


                }
        });

    }

    private void callofflinelocaldatdata()
    {
        localRepo.getSelectedBene(Integer.valueOf(CommonClass.benfeciary_ID)).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {
                if(singleMember.size() > 0) {
                    binding.name.setText(singleMember.get(0).name);
                    binding.parent.setText(singleMember.get(0).parentName);
                    binding.relaship.setText(singleMember.get(0).relation);
                    binding.dojshg.setText(singleMember.get(0).dob);
                    binding.age.setText(singleMember.get(0).age);
                    binding.gender.setText(singleMember.get(0).gender);
                    String[] itemNames = getResources().getStringArray(R.array.gender);
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.gender.setAdapter(adapter2);

                    binding.edu.setText(singleMember.get(0).educationName);

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


                    binding.caste.setText(singleMember.get(0).casteName);
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



                    binding.religion.setText(singleMember.get(0).religionName);
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



                    binding.adharno.setText(singleMember.get(0).adhaarNo);


                    binding.ecconomicstatus.setText(singleMember.get(0).economicName);
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


                    binding.annualincome.setText(singleMember.get(0).annualName);
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


                    binding.maritalstatus.setText(singleMember.get(0).maritalName);
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
                    binding.maritalstatus.setAdapter(adaptermerital);


                    binding.occuption.setText(singleMember.get(0).occupationName);
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



                    binding.typeofdisability.setText(singleMember.get(0).disabilityName);
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



                    binding.typeodsubdisability.setText(singleMember.get(0).subDisabilityName);


                    binding.phpammount.setText(singleMember.get(0).phpAmount);
                    String[] phpamount = getResources().getStringArray(R.array.selectone);
                    ArrayAdapter<String> adapterphp = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, phpamount);
                    adapterphp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.phpammount.setAdapter(adapterphp);



                    binding.typeofbenificary.setText(singleMember.get(0).typeOfBeneficiary);
                    String[] typebene = getResources().getStringArray(R.array.benef);
                    ArrayAdapter<String> adapbene = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, typebene);
                    adapbene.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.typeofbenificary.setAdapter(adapbene);

                    binding.idcardno.setText(singleMember.get(0).idCardNo);


                    binding.perposeofvisit.setText(singleMember.get(0).visitName);
                    String getpurposeofvisit = getPurposeVisitMaster(EditBasicProfileActivity.this);
                    try {
                        JSONObject obj = new JSONObject(getpurposeofvisit);
                        if (obj.optString("status", "fail").equals("true")) {
                            JSONArray jsonArray = obj.optJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                purposeofvisit.add(jsonObject.getString("visit_name"));
                                purposeofvisitID.add(jsonObject.getString("id"));

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ArrayAdapter<String> adapterpurposevisit = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, purposeofvisit);
                    adapterpurposevisit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.perposeofvisit.setAdapter(adapterpurposevisit);
                }
            }
        });
    }

    private void callupdatelocaledata(){
        localRepo.getSelectedBene(Integer.valueOf(CommonClass.benfeciary_ID)).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {
                if (singleMember.size() > 0)
                {
                    singleMember.get(0).setName(binding.name.getText().toString());
                    singleMember.get(0).setParentName(binding.parent.getText().toString());
                    singleMember.get(0).setRelation(binding.relaship.getText().toString());
                    singleMember.get(0).setDob(binding.dojshg.getText().toString());
                    singleMember.get(0).setAge(binding.age.getText().toString());
                    singleMember.get(0).setGender(binding.gender.getText().toString());

                    singleMember.get(0).setCasteName(binding.caste.getText().toString());
                    singleMember.get(0).setCaste(CommonClass.caste);

                    singleMember.get(0).setReligionName(binding.religion.getText().toString());
                    singleMember.get(0).setReligion(CommonClass.reigion);

                    singleMember.get(0).setAdhaarNo(binding.adharno.getText().toString());

                    singleMember.get(0).setAnnualName(binding.annualincome.getText().toString());
                    singleMember.get(0).setAnnualIncome(CommonClass.annualincome);

                    singleMember.get(0).setEconomicName(binding.ecconomicstatus.getText().toString());
                    singleMember.get(0).setEconomicStatus(CommonClass.ecconomicstatus);

                    singleMember.get(0).setMaritalName(binding.maritalstatus.getText().toString());
                    singleMember.get(0).setMaritalStatus(CommonClass.meritalstatus);

                    singleMember.get(0).setEducationName(binding.edu.getText().toString());
                    singleMember.get(0).setEducation(CommonClass.edu);

                    singleMember.get(0).setOccupationName(binding.occuption.getText().toString());
                    singleMember.get(0).setOccupation(CommonClass.occuption);

                    singleMember.get(0).setDisabilityName(binding.typeofdisability.getText().toString());
                    singleMember.get(0).setTypeOfDisability(CommonClass.typeofdisability);

                    singleMember.get(0).setSubDisabilityName(binding.typeodsubdisability.getText().toString());
                    singleMember.get(0).setTypeOfSubDisability(CommonClass.typeofsubdisability);

                    singleMember.get(0).setPercentageOfDisability(binding.persentofdisability.getText().toString());
                    singleMember.get(0).setIdCardNo(binding.idcardno.getText().toString());
                    singleMember.get(0).setPhpAmount(binding.phpammount.getText().toString());
                    singleMember.get(0).setTypeOfBeneficiary(binding.typeofbenificary.getText().toString());

                    singleMember.get(0).setVisitName(binding.perposeofvisit.getText().toString());
                    singleMember.get(0).setPurposeOfVisit(CommonClass.purposeofvisit);

                    localRepo.updateBene(singleMember.get(0));

                    onBackPressed();
                    Toast.makeText(EditBasicProfileActivity.this, " Basic Profile update Successfully", Toast.LENGTH_SHORT).show();

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