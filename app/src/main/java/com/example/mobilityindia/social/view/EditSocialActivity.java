package com.example.mobilityindia.social.view;

import static com.example.mobilityindia.constant.CommonClass.getSocalTranning;
import static com.example.mobilityindia.utils.Utils.getRandomNumber;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.beneficarylist.VisitModelClass;
import com.example.mobilityindia.beneficarylist.view.BeneficaryDetailActivity;
import com.example.mobilityindia.beneficarylist.view.BeneficaryListActivity;
import com.example.mobilityindia.constant.AppUtils;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEditSocialBinding;
import com.example.mobilityindia.livelihood.AddLivelihoodActivity;
import com.example.mobilityindia.livelihood.EditLivelihoodAtivity;
import com.example.mobilityindia.sync.model.SocialData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.google.android.material.button.MaterialButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditSocialActivity extends AppCompatActivity {
    ActivityEditSocialBinding binding;
    LocalRepo localRepo;
    String partofanycultural = "No";
    String partofpwd = "No";
    String partoflocal = "No";
    String traningparticipate = "No";
    SocialData socialData;
    String createdAt = "", Benificiaryid = "", id = "",benid = "";

    ArrayList<String> jsonArray_SocalTraningWhat = new ArrayList<>();
    ArrayList<String> jsonArray_SocalTraningWhere = new ArrayList<>();

    ArrayList<EditText> editTextLay = new ArrayList<>();
    ArrayList<String> idarray = new ArrayList<>();

    EditText ed,editText;
    List<EditText> allEds = new ArrayList<EditText>();
    List<String> socalTranning;
    List<String> socalTranningId;

    List<String> socalTraningWhat = new ArrayList<String>();
    List<String> socalTraningWhere = new ArrayList<String>();
    Map<String, String> purposeofvisit2 = new HashMap<>();
    Map<String, String> purposeofvisit3 = new HashMap<>();
    String[] str_purposeofvisit;
    private static final String TAG = "MainActivity";
    ArrayList<VisitModelClass> purposeofvisit1 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_social);
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
        localRepo = new LocalRepo(EditSocialActivity.this);
        socialData = new SocialData();
        socalTranning = new ArrayList<>();
        socalTranningId = new ArrayList<>();

        benid = String.valueOf(CommonClass.benfeciary_ID);

        if(benid.equals("") || benid.equals("null")){

            callofflinedata1();

        }else{

            callofflinedata();
        }

        binding.partofanyculture.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    partofanycultural = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                    binding.Partofanycultural.setVisibility(View.VISIBLE);
                } else {
                    partofanycultural = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                    binding.Partofanycultural.setVisibility(View.GONE);
                }
            }
        });

        binding.traningparticipate.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    traningparticipate = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                    binding.tranarticipate.setVisibility(View.VISIBLE);
                } else {
                    traningparticipate = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off

                    binding.tranarticipate.setVisibility(View.GONE);
                }
            }
        });

        binding.partofanylocal.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    partoflocal = "Yes";
                    binding.SocialGovt.setVisibility(View.VISIBLE);
                    //switch  is on
                } else {
                    partoflocal = "No";
                    binding.SocialGovt.setVisibility(View.GONE);
                    binding.Whatlocalgovt.setText("");

                    //switch is off
                }
            }
        });

        String castemasterdata = getSocalTranning(EditSocialActivity.this);
        Log.d("hsgjbsja", castemasterdata);

        try {
            JSONObject obj = new JSONObject(castemasterdata);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    socalTranning.add(jsonObject.getString("training_name"));
                    socalTranningId.add(jsonObject.getString("id"));

                    String name = jsonObject.getString("training_name");
                    String id = jsonObject.getString("id");

                    purposeofvisit2.put(name, id);
                    purposeofvisit3.put(id, name);

                    long lon_id = Long.valueOf(id);

                    VisitModelClass visitModelClass = new VisitModelClass(lon_id, name);
                    purposeofvisit1.add(visitModelClass);

                    str_purposeofvisit = Arrays.copyOf(
                            socalTranning.toArray(), socalTranning.size(), String[].class);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addView();

            }
        });

      binding.buttonnext.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              if (traningparticipate.equals("yes")) {

                  if (checkifvalidandRead()) {

                      if (Benificiaryid.equals("") || Benificiaryid.equals("null")) {

                          storeData();

                          Toast.makeText(EditSocialActivity.this, "Add Success", Toast.LENGTH_SHORT).show();

                      } else {

                          Toast.makeText(EditSocialActivity.this, "Add Success", Toast.LENGTH_SHORT).show();

                          storeData1();
                      }

                  }
              }
              else {

                  if (Benificiaryid.equals("") || Benificiaryid.equals("null")) {

                      storeData();

                  } else {

                      storeData1();
                  }
              }
          }
      });

    }

    private void callofflinedata()
    {
        localRepo.getSelectedEducationWithData(CommonClass.datestring,CommonClass.benfeciary_ID).observe(this, new Observer<List<SocialData>>() {
            @Override
            public void onChanged(@Nullable List<SocialData> singleMember) {
                if(singleMember.size() > 0)
                {

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;


                    partofanycultural = String.valueOf(singleMember.get(0).getSocialsports());
                    if (partofanycultural.equals("") || partofanycultural.equals("null")) {
                    } else if(partofanycultural.equalsIgnoreCase("yes") || partofanycultural.equalsIgnoreCase("true")) {

                        binding.partofanyculture.setOn(true);
                        binding.Partofanycultural.setVisibility(View.VISIBLE);
                        binding.culturalsportseventsMention.setText(singleMember.get(0).getSocialsportsMention());

                    }else{

                        binding.partofanyculture.setOn(false);
                        binding.Partofanycultural.setVisibility(View.GONE);
                        binding.culturalsportseventsMention.setText(singleMember.get(0).getSocialsportsMention());
                    }

                    partoflocal = singleMember.get(0).getSocialgovt();
                    if (partoflocal.equals("") || partoflocal.equals("null")) {
                    } else if (partoflocal.equalsIgnoreCase("yes") || partoflocal.equalsIgnoreCase("true")) {
                        binding.partofanylocal.setOn(true);
                        binding.SocialGovt.setVisibility(View.VISIBLE);
                        binding.Whatlocalgovt.setText(singleMember.get(0).getSocialgovtwhat());

                    } else {
                        binding.partofanylocal.setOn(false);
                        binding.SocialGovt.setVisibility(View.GONE);
                        binding.Whatlocalgovt.setText("");
                    }

                    traningparticipate = singleMember.get(0).getSocialtraining();
                    if (partoflocal.equals("") || partoflocal.equals("null")) {
                    } else if (traningparticipate.equalsIgnoreCase("yes") || traningparticipate.equalsIgnoreCase("true")) {
                        binding.traningparticipate.setOn(true);
                        binding.tranarticipate.setVisibility(View.VISIBLE);
                    } else {
                        binding.traningparticipate.setOn(false);
                        binding.tranarticipate.setVisibility(View.GONE);

                    }

                    socalTraningWhat = singleMember.get(0).getSocialtrainingwhat();
                    socalTraningWhere = singleMember.get(0).getSocialtrainingwhere();

                    int size = socalTraningWhat.size();

                    Log.d("hdgkjbds", String.valueOf(size));

                    String castemasterdata = getSocalTranning(EditSocialActivity.this);
                    Log.d("hsgjbsja", castemasterdata);

                    try {
                        JSONObject obj = new JSONObject(castemasterdata);
                        if (obj.optString("status", "fail").equals("true")) {
                            JSONArray jsonArray = obj.optJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                socalTranning.add(jsonObject.getString("training_name"));
                                socalTranningId.add(jsonObject.getString("id"));

                                String name = jsonObject.getString("training_name");
                                String id = jsonObject.getString("id");

                                purposeofvisit2.put(name, id);
                                purposeofvisit3.put(id, name);

                                long lon_id = Long.valueOf(id);

                                VisitModelClass visitModelClass = new VisitModelClass(lon_id, name);
                                purposeofvisit1.add(visitModelClass);

                                str_purposeofvisit = Arrays.copyOf(
                                        socalTranning.toArray(), socalTranning.size(), String[].class);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String flag = String.valueOf(singleMember.get(0).getFlag());

                    if(flag.equals("update")){

                        for (int i = 0; i < socalTraningWhat.size(); i++) {

                            View createView = getLayoutInflater().inflate(R.layout.row_add_cricket, null, false);

                            editText = createView.findViewById(R.id.edittext_trainning);
                            AutoCompleteTextView whatkindoftraning = createView.findViewById(R.id.whatkindoftraning);
                            MaterialButton image_remove = createView.findViewById(R.id.image_remove);
                            //image_remove.setVisibility(View.GONE);

                            String str_kindtraning = socalTraningWhat.get(i);
                            String strkindtraningId = purposeofvisit3.get(str_kindtraning);

                            editText.setText(socalTraningWhere.get(i));
                            whatkindoftraning.setText(strkindtraningId);

                            image_remove.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    removeView(createView);
                                }
                            });

                            binding.layoutList.addView(createView);

                        }
                    }
                    else{

                        for (int i = 0; i < socalTraningWhat.size(); i++) {

                            View createView = getLayoutInflater().inflate(R.layout.row_add_cricket, null, false);

                            editText = createView.findViewById(R.id.edittext_trainning);
                            AutoCompleteTextView whatkindoftraning = createView.findViewById(R.id.whatkindoftraning);
                            MaterialButton image_remove = createView.findViewById(R.id.image_remove);
                            //image_remove.setVisibility(View.GONE);

                            editText.setText(socalTraningWhere.get(i));
                            whatkindoftraning.setText(socalTraningWhat.get(i));

                            image_remove.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    removeView(createView);
                                }
                            });

                            binding.layoutList.addView(createView);

                        }
                    }

                  /*  image_remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            removeView(createView);
                        }
                    });*/

                    id = singleMember.get(0).getId();
                    createdAt = singleMember.get(0).getCreatedAt();
                    Benificiaryid = singleMember.get(0).getBenificiaryId();


                }
            }
        });
    }

    private void callofflinedata1()
    {
        localRepo.getSelectedsocialData(CommonClass.tempid).observe(this, new Observer<List<SocialData>>() {
            @Override
            public void onChanged(@Nullable List<SocialData> singleMember) {
                if(singleMember.size() > 0)
                {

                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;


                    partofanycultural = String.valueOf(singleMember.get(0).getSocialsports());
                    if (partofanycultural.equals("") || partofanycultural.equals("null")) {
                    } else if(partofanycultural.equalsIgnoreCase("yes") || partofanycultural.equalsIgnoreCase("true")) {

                        binding.partofanyculture.setOn(true);
                        binding.Partofanycultural.setVisibility(View.VISIBLE);
                        binding.culturalsportseventsMention.setText(singleMember.get(0).getSocialsportsMention());

                    }else{

                        binding.partofanyculture.setOn(false);
                        binding.Partofanycultural.setVisibility(View.GONE);
                        binding.culturalsportseventsMention.setText(singleMember.get(0).getSocialsportsMention());
                    }

                    partoflocal = singleMember.get(0).getSocialgovt();
                    if (partoflocal.equals("") || partoflocal.equals("null")) {
                    } else if (partoflocal.equalsIgnoreCase("yes") || partoflocal.equalsIgnoreCase("true")) {
                        binding.partofanylocal.setOn(true);
                        binding.SocialGovt.setVisibility(View.VISIBLE);
                        binding.Whatlocalgovt.setText(singleMember.get(0).getSocialgovtwhat());

                    } else {
                        binding.partofanylocal.setOn(false);
                        binding.SocialGovt.setVisibility(View.GONE);
                        binding.Whatlocalgovt.setText(singleMember.get(0).getSocialgovtwhat());
                    }

                    traningparticipate = singleMember.get(0).getSocialtraining();
                    if (partoflocal.equals("") || partoflocal.equals("null")) {
                    } else if (traningparticipate.equalsIgnoreCase("yes") || traningparticipate.equalsIgnoreCase("true")) {
                        binding.traningparticipate.setOn(true);
                        binding.tranarticipate.setVisibility(View.VISIBLE);
                    } else {
                        binding.traningparticipate.setOn(false);
                        binding.tranarticipate.setVisibility(View.GONE);

                    }

                    socalTraningWhat = singleMember.get(0).getSocialtrainingwhat();
                    socalTraningWhere = singleMember.get(0).getSocialtrainingwhere();

                    int size = socalTraningWhat.size();

                    Log.d("hdgkjbds", String.valueOf(size));

                    String castemasterdata = getSocalTranning(EditSocialActivity.this);
                    Log.d("hsgjbsja", castemasterdata);

                    try {
                        JSONObject obj = new JSONObject(castemasterdata);
                        if (obj.optString("status", "fail").equals("true")) {
                            JSONArray jsonArray = obj.optJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                socalTranning.add(jsonObject.getString("training_name"));
                                socalTranningId.add(jsonObject.getString("id"));

                                String name = jsonObject.getString("training_name");
                                String id = jsonObject.getString("id");

                                purposeofvisit2.put(name, id);
                                purposeofvisit3.put(id, name);

                                long lon_id = Long.valueOf(id);

                                VisitModelClass visitModelClass = new VisitModelClass(lon_id, name);
                                purposeofvisit1.add(visitModelClass);

                                str_purposeofvisit = Arrays.copyOf(
                                        socalTranning.toArray(), socalTranning.size(), String[].class);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String flag = String.valueOf(singleMember.get(0).getFlag());

                    if(flag.equals("update")){

                        for (int i = 0; i < socalTraningWhat.size(); i++) {

                            View createView = getLayoutInflater().inflate(R.layout.row_add_cricket, null, false);

                            EditText editText = createView.findViewById(R.id.edittext_trainning);
                            AutoCompleteTextView whatkindoftraning = createView.findViewById(R.id.whatkindoftraning);
                            MaterialButton image_remove = createView.findViewById(R.id.image_remove);
                            //image_remove.setVisibility(View.GONE);

                            String str_kindtraning = socalTraningWhat.get(i);
                            String strkindtraningId = purposeofvisit3.get(str_kindtraning);

                            editText.setText(socalTraningWhere.get(i));
                            whatkindoftraning.setText(strkindtraningId);

                            image_remove.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    removeView(createView);
                                }
                            });

                            binding.layoutList.addView(createView);

                        }
                    }
                    else{

                        for (int i = 0; i < socalTraningWhat.size(); i++) {

                            View createView = getLayoutInflater().inflate(R.layout.row_add_cricket, null, false);

                            EditText editText = createView.findViewById(R.id.edittext_trainning);
                            AutoCompleteTextView whatkindoftraning = createView.findViewById(R.id.whatkindoftraning);
                            MaterialButton image_remove = createView.findViewById(R.id.image_remove);
                            //image_remove.setVisibility(View.GONE);

                            editText.setText(socalTraningWhere.get(i));
                            whatkindoftraning.setText(socalTraningWhat.get(i));

                            image_remove.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    removeView(createView);
                                }
                            });

                            binding.layoutList.addView(createView);

                        }
                    }

                  /*  image_remove.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            removeView(createView);
                        }
                    });*/

                    id = singleMember.get(0).getId();
                    createdAt = singleMember.get(0).getCreatedAt();
                    Benificiaryid = singleMember.get(0).getBenificiaryId();


                }
            }
        });
    }

    public void callupdatelocaledata() {

        localRepo.updateSocialData(socialData);
        //onBackPressed();

        Toast.makeText(this, "Data update Locally", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(EditSocialActivity.this, BeneficaryDetailActivity.class);
        startActivity(intent);


    }

    public void storeData() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date date = new Date();

        socialData.setId(CommonClass.tempid);
        socialData.setBenificiaryId(CommonClass.benfeciary_ID);
        socialData.setCreatedAt(formatter.format(date));
        socialData.setSocialsports(partofanycultural);
        // socialData.setSpcialdpo(partofpwd);
        socialData.setSocialsportsMention(binding.culturalsportseventsMention.getText().toString().trim());
        String randoNoStr = getRandomNumber();
        //socialData.setId(randoNoStr);

        socialData.setSocialgovt(partoflocal);
        socialData.setSocialgovtwhat(binding.Whatlocalgovt.getText().toString());

        socialData.setSocialtraining(traningparticipate);
        socialData.setSocialtrainingwhat(jsonArray_SocalTraningWhat);
        socialData.setSocialtrainingwhere(jsonArray_SocalTraningWhere);
        socialData.setFlag("update");
        callupdatelocaledata();
    }

    public void storeData1() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date date = new Date();

        Log.d("hsadvuss", String.valueOf(jsonArray_SocalTraningWhere));
        Log.d("hsadvu11ss", String.valueOf(jsonArray_SocalTraningWhat));


        socialData.setId(id);
        socialData.setBenificiaryId(CommonClass.benfeciary_ID);
        socialData.setCreatedAt(formatter.format(date));
        socialData.setSocialsports(partofanycultural);
        // socialData.setSpcialdpo(partofpwd);
        socialData.setSocialsportsMention(binding.culturalsportseventsMention.getText().toString().trim());
        String randoNoStr = getRandomNumber();
        //socialData.setId(randoNoStr);

        socialData.setSocialgovt(partoflocal);
        socialData.setSocialgovtwhat(binding.Whatlocalgovt.getText().toString());

        socialData.setSocialtraining(traningparticipate);
        socialData.setSocialtrainingwhat(jsonArray_SocalTraningWhat);
        socialData.setSocialtrainingwhere(jsonArray_SocalTraningWhere);
        socialData.setFlag("update");
        callupdatelocaledata();
    }

    public void addView() {

        View createView = getLayoutInflater().inflate(R.layout.row_add_cricket, null, false);

        EditText editText = createView.findViewById(R.id.edittext_trainning);
        AutoCompleteTextView whatkindoftraning = createView.findViewById(R.id.whatkindoftraning);
        Button image_remove = createView.findViewById(R.id.image_remove);

        ArrayAdapter<String> castemasterr = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, socalTranning);
        castemasterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        whatkindoftraning.setAdapter(castemasterr);

        image_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeView(createView);
            }
        });

        whatkindoftraning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                (whatkindoftraning).showDropDown();
            }
        });


        binding.layoutList.addView(createView);
    }

    public void removeView(View view) {

        binding.layoutList.removeView(view);
    }

    public Boolean checkifvalidandRead() {

        jsonArray_SocalTraningWhat.clear();
        jsonArray_SocalTraningWhere.clear();

        String traingWhere, strkindtraningId, str_kindtraning;

        boolean result = true;

        for (int i = 0; i < binding.layoutList.getChildCount(); i++) {

            View createView = binding.layoutList.getChildAt(i);

            EditText editText = createView.findViewById(R.id.edittext_trainning);
            AutoCompleteTextView whatkindoftraning = createView.findViewById(R.id.whatkindoftraning);

            if (!editText.getText().toString().trim().equals("")) {

                traingWhere = editText.getText().toString().trim();

            } else {

                result = false;
                break;
            }

            if (!whatkindoftraning.getText().toString().trim().equals("")) {

                str_kindtraning = whatkindoftraning.getText().toString().trim();
                strkindtraningId = purposeofvisit2.get(str_kindtraning);

            } else {

                result = false;
                break;
            }

            jsonArray_SocalTraningWhere.add(traingWhere);
            jsonArray_SocalTraningWhat.add(strkindtraningId);

            Log.d("hujgjh",jsonArray_SocalTraningWhere.toString());
            Log.d("hujgjhdd",jsonArray_SocalTraningWhat.toString());


        }

        if (jsonArray_SocalTraningWhat.size() == 0) {

            result = false;

            Toast.makeText(this, "Add the Tranning!", Toast.LENGTH_SHORT).show();

        } else if (!result) {

            Toast.makeText(this, "Enter All the Details Correctly", Toast.LENGTH_SHORT).show();

        }

        return result;

    }

    public void addview1(int i){

        View createView = getLayoutInflater().inflate(R.layout.row_add_cricket, null, false);
        EditText editText = createView.findViewById(R.id.edittext_trainning);
        AutoCompleteTextView whatkindoftraning = createView.findViewById(R.id.whatkindoftraning);
        MaterialButton image_remove = createView.findViewById(R.id.image_remove);
        //image_remove.setVisibility(View.GONE);

        String str_kindtraning = socalTraningWhat.get(i);
        String strkindtraningId = purposeofvisit3.get(str_kindtraning);

        editText.setText(socalTraningWhere.get(i));
        whatkindoftraning.setText(strkindtraningId);

        image_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeView(createView);
            }
        });

        binding.layoutList.addView(createView);
    }

}