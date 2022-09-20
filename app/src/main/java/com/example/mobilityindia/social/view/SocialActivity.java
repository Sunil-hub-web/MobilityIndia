package com.example.mobilityindia.social.view;

import static com.example.mobilityindia.constant.CommonClass.getSocalTranning;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.beneficarylist.VisitModelClass;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivitySocialBinding;
import com.example.mobilityindia.sync.model.SocialData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocialActivity extends AppCompatActivity {
    ActivitySocialBinding binding;
    LocalRepo localRepo;
    String partofanycultural = "No";
    String partofpwd = "No";
    String partoflocal = "No";
    String traningparticipate = "No",id = "",createdAt = "",Benificiaryid = "",benid = "";

    List<String> socalTraningWhat = new ArrayList<String>();
    List<String> socalTraningWhere = new ArrayList<String>();

    Map<String, String> purposeofvisit3 = new HashMap<>();
    Map<String, String> purposeofvisit2 = new HashMap<>();
    ArrayList<VisitModelClass> purposeofvisit1 = new ArrayList<>();
    String[] str_purposeofvisit;
    private static final String TAG = "MainActivity";
    AutoCompleteTextView whatkindoftraning;
    Button image_remove;
    View createView;
    EditText ed,editText;
    List<String> socalTranning;
    List<String> socalTranningId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_social);
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
        localRepo = new LocalRepo(SocialActivity.this);
        socalTranning = new ArrayList<>();
        socalTranningId = new ArrayList<>();
        binding.nextpage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SocialActivity.this, EditSocialActivity.class));
            }
        });

        benid = String.valueOf(CommonClass.benfeciary_ID);

        if(benid.equals("") || benid.equals("null")){

            callofflinedata1();

        }else{

            callofflinedata();
        }

       // callofflinedata();


    }

    /*@Override
    protected void onResume() {
        callofflinedata();
        super.onResume();
    }*/

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

                        binding.partofanyculture.setText(partofanycultural);
                        binding.Partofanycultural.setVisibility(View.VISIBLE);
                        binding.culturalsportseventsMention.setText(singleMember.get(0).getSocialsportsMention());

                    }else{

                        binding.partofanyculture.setText(partofanycultural);
                        binding.Partofanycultural.setVisibility(View.GONE);
                        binding.culturalsportseventsMention.setText(singleMember.get(0).getSocialsportsMention());
                    }

                    partoflocal = singleMember.get(0).getSocialgovt();
                    if (partoflocal.equals("") || partoflocal.equals("null")) {
                    } else if (partoflocal.equalsIgnoreCase("yes") || partoflocal.equalsIgnoreCase("true")) {
                        binding.partofanylocal.setText(partoflocal);
                        binding.SocialGovt.setVisibility(View.VISIBLE);
                        binding.Whatlocalgovt.setText(singleMember.get(0).getSocialgovtwhat());

                    } else {
                        binding.partofanylocal.setText(partoflocal);
                        binding.SocialGovt.setVisibility(View.GONE);
                        binding.Whatlocalgovt.setText("");
                    }

                    traningparticipate = singleMember.get(0).getSocialtraining();
                    if (partoflocal.equals("") || partoflocal.equals("null")) {
                    } else if (traningparticipate.equalsIgnoreCase("yes") || traningparticipate.equalsIgnoreCase("true")) {
                        binding.traningparticipate.setText(traningparticipate);
                        binding.tranarticipate.setVisibility(View.VISIBLE);
                    } else {
                        binding.traningparticipate.setText(traningparticipate);
                        binding.tranarticipate.setVisibility(View.GONE);

                    }

                    socalTraningWhat = singleMember.get(0).getSocialtrainingwhat();
                    socalTraningWhere = singleMember.get(0).getSocialtrainingwhere();

                    int size = socalTraningWhat.size();

                    Log.d("hdgkjbds", String.valueOf(size));

                    String castemasterdata = getSocalTranning(SocialActivity.this);
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

                            createView = getLayoutInflater().inflate(R.layout.row_add_cricket, null, false);

                            editText = createView.findViewById(R.id.edittext_trainning);
                            whatkindoftraning = createView.findViewById(R.id.whatkindoftraning);
                            image_remove = createView.findViewById(R.id.image_remove);
                            //image_remove.setVisibility(View.GONE);

                            String str_kindtraning = socalTraningWhat.get(i);
                            String strkindtraningId = purposeofvisit3.get(str_kindtraning);

                            editText.setText(socalTraningWhere.get(i));
                            whatkindoftraning.setText(strkindtraningId);

                            binding.tranarticipate.addView(createView);

                        }
                    }
                    else{

                        for (int i = 0; i < socalTraningWhat.size(); i++) {

                            createView = getLayoutInflater().inflate(R.layout.row_add_cricket, null, false);

                            editText = createView.findViewById(R.id.edittext_trainning);
                            whatkindoftraning = createView.findViewById(R.id.whatkindoftraning);
                            image_remove = createView.findViewById(R.id.image_remove);
                            //image_remove.setVisibility(View.GONE);

                            editText.setText(socalTraningWhere.get(i));
                            whatkindoftraning.setText(socalTraningWhat.get(i));

                            binding.tranarticipate.addView(createView);

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

                        binding.partofanyculture.setText(partofanycultural);
                        binding.Partofanycultural.setVisibility(View.VISIBLE);
                        binding.Whatlocalgovt.setText(singleMember.get(0).getSocialgovtwhat());

                    }else{

                        binding.partofanyculture.setText(partofanycultural);
                        binding.Partofanycultural.setVisibility(View.GONE);
                        binding.Whatlocalgovt.setText(singleMember.get(0).getSocialgovtwhat());
                    }

                    partoflocal = singleMember.get(0).getSocialgovt();
                    if (partoflocal.equals("") || partoflocal.equals("null")) {
                    } else if (partoflocal.equalsIgnoreCase("yes") || partoflocal.equalsIgnoreCase("true")) {
                        binding.partofanylocal.setText(partoflocal);
                        binding.SocialGovt.setVisibility(View.VISIBLE);
                        binding.Whatlocalgovt.setText(singleMember.get(0).getSocialgovtwhat());

                    } else {
                        binding.partofanylocal.setText(partoflocal);
                        binding.SocialGovt.setVisibility(View.GONE);
                        binding.Whatlocalgovt.setText("");
                    }

                    traningparticipate = singleMember.get(0).getSocialtraining();
                    if (partoflocal.equals("") || partoflocal.equals("null")) {
                    } else if (traningparticipate.equalsIgnoreCase("yes") || traningparticipate.equalsIgnoreCase("true")) {
                        binding.traningparticipate.setText(traningparticipate);
                        binding.tranarticipate.setVisibility(View.VISIBLE);
                    } else {
                        binding.traningparticipate.setText(traningparticipate);
                        binding.tranarticipate.setVisibility(View.GONE);

                    }

                    socalTraningWhat = singleMember.get(0).getSocialtrainingwhat();
                    socalTraningWhere = singleMember.get(0).getSocialtrainingwhere();

                    int size = socalTraningWhat.size();

                    Log.d("hdgkjbds", String.valueOf(size));

                    String castemasterdata = getSocalTranning(SocialActivity.this);
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

                            createView = getLayoutInflater().inflate(R.layout.row_add_cricket, null, false);

                            editText = createView.findViewById(R.id.edittext_trainning);
                            whatkindoftraning = createView.findViewById(R.id.whatkindoftraning);
                            image_remove = createView.findViewById(R.id.image_remove);
                            //image_remove.setVisibility(View.GONE);

                            String str_kindtraning = socalTraningWhat.get(i);
                            String strkindtraningId = purposeofvisit3.get(str_kindtraning);

                            editText.setText(socalTraningWhere.get(i));
                            whatkindoftraning.setText(strkindtraningId);

                            binding.tranarticipate.addView(createView);

                        }
                    }
                    else{

                        for (int i = 0; i < socalTraningWhat.size(); i++) {

                            createView = getLayoutInflater().inflate(R.layout.row_add_cricket, null, false);

                            editText = createView.findViewById(R.id.edittext_trainning);
                            whatkindoftraning = createView.findViewById(R.id.whatkindoftraning);
                            image_remove = createView.findViewById(R.id.image_remove);
                            //image_remove.setVisibility(View.GONE);

                            editText.setText(socalTraningWhere.get(i));
                            whatkindoftraning.setText(socalTraningWhat.get(i));

                            binding.tranarticipate.addView(createView);

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
}