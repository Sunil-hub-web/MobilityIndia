package com.example.mobilityindia.social.view;

import static com.example.mobilityindia.constant.CommonClass.getSocalTranning;
import static com.example.mobilityindia.utils.Utils.getRandomNumber;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.beneficarylist.VisitModelClass;
import com.example.mobilityindia.beneficarylist.view.BeneficaryDetailActivity;
import com.example.mobilityindia.beneficarylist.view.BeneficaryListActivity;
import com.example.mobilityindia.constant.AppUtils;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityAddSocialBinding;
import com.example.mobilityindia.livelihood.AddLivelihoodActivity;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.social.socialresponce.SocialResponse;
import com.example.mobilityindia.syn1.view.SyncActivityAll;
import com.example.mobilityindia.syn1.view.allresponse.social.DataDemo_Social;
import com.example.mobilityindia.syn1.view.allresponse.social.Social_Example;
import com.example.mobilityindia.sync.model.SocialData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSocialActivity extends AppCompatActivity {
    ActivityAddSocialBinding binding;
    String partofanycultural = "No";
    String partofpwd = "No";
    String partoflocal = "No";
    String traningparticipate = "No", purposeId = "",userId = "";
    LocalRepo localRepo;
    List<String> socalTranning;
    List<String> socalTranningId;
    String[] str_purposeofvisit;
    private static final String TAG = "MainActivity";
    ArrayList<VisitModelClass> purposeofvisit1 = new ArrayList<>();
    // ArrayList<VisitModelClass1> purposeofvisit2 = new ArrayList<>();
    Map<String, String> purposeofvisit2 = new HashMap<>();
    boolean[] selectDay;
    //ArrayList<String> idarray = new ArrayList<>();
    List<String> purposeofvisitID = new ArrayList<>();

    JSONArray socalTraningWhat = new JSONArray();
    JSONArray socalTraningWhere = new JSONArray();

    List<String> jsonArray_SocalTraningWhat = new ArrayList<>();
    List<String> jsonArray_SocalTraningWhere = new ArrayList<>();



    ArrayList<EditText> editTextLay = new ArrayList<>();
    ArrayList<String> idarray = new ArrayList<>();
    ArrayList<String> idarray1 = new ArrayList<>();

    EditText ed;
    List<EditText> allEds = new ArrayList<EditText>();

    ApiRequest apiRequest;


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
        socalTranning = new ArrayList<>();
        socalTranningId = new ArrayList<>();
        SessinoManager sessinoManager = new SessinoManager(AddSocialActivity.this);
        userId = sessinoManager.getUSERID();

        binding.partofanyculture.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    partofanycultural = "yes";
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
                    traningparticipate = "yes";
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
                    partoflocal = "yes";
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

        String castemasterdata = getSocalTranning(AddSocialActivity.this);
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
            public void onClick(View v) {

                if (AppUtils.isNetworkAvailable(AddSocialActivity.this)) {

                    if (traningparticipate.equals("yes")) {

                        if (checkifvalidandRead()) {

                            //Toast.makeText(AddSocialActivity.this, "Add Success", Toast.LENGTH_SHORT).show();

                            addSocialData();

                            //addSocialData1();

                        }
                    } else {

                        addSocialData();

                        //addSocialData1();
                    }


                } else {

                    if (traningparticipate.equals("yes")) {

                        if (checkifvalidandRead()) {

                            Toast.makeText(AddSocialActivity.this, "Add Success", Toast.LENGTH_SHORT).show();

                            //addSocialData();
                            localbeneficaryDataCall();

                        } else {

                            localbeneficaryDataCall();

                        }

                    } else {

                        localbeneficaryDataCall();
                    }


                }
            }
        });
    }


    public void localbeneficaryDataCall() {

        SocialData socialData = new SocialData();
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
        localRepo.insertSocialData(socialData);
        Toast.makeText(getApplicationContext(), "Internet is not Available Data save in your Local", Toast.LENGTH_SHORT).show();
        onBackPressed();

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


        }

        if (jsonArray_SocalTraningWhat.size() == 0) {

            result = false;

            Toast.makeText(this, "Add the Tranning!", Toast.LENGTH_SHORT).show();

        } else if (!result) {

            Toast.makeText(this, "Enter All the Details Correctly", Toast.LENGTH_SHORT).show();

        }

        return result;

    }

    public void addSocialData() {

        ProgressDialog pd = new ProgressDialog(AddSocialActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

        if(jsonArray_SocalTraningWhat.size() != 0){

            for (int m = 0; m < jsonArray_SocalTraningWhat.size(); m++) {
                socalTraningWhat.put(jsonArray_SocalTraningWhat.get(m));
            }

        }

        if(jsonArray_SocalTraningWhere.size() != 0){

            for (int n = 0; n < jsonArray_SocalTraningWhere.size(); n++) {
                socalTraningWhere.put(jsonArray_SocalTraningWhere.get(n));
            }

        }

        Log.d("hsadvu", String.valueOf(jsonArray_SocalTraningWhere));
        Log.d("hsadvu11", String.valueOf(jsonArray_SocalTraningWhat));

        Map<String, Object> mapData = new HashMap<>();

        mapData.put("benificiary_id", CommonClass.benfeciary_ID);
        mapData.put("user_id", userId);

        mapData.put("socialsports", partofanycultural);
        mapData.put("socialsports_mention", binding.culturalsportseventsMention.getText().toString());
        //mapData.put("spcialdpo",partofpwd);

        mapData.put("socialgovt", partoflocal);
        mapData.put("socialgovtwhat", binding.Whatlocalgovt.getText().toString());

        mapData.put("socialtraining", traningparticipate);
        mapData.put("socialtrainingwhat", jsonArray_SocalTraningWhat);
        mapData.put("socialtrainingwhere", jsonArray_SocalTraningWhere);

        Log.d("jhsguyg", mapData.toString());

        CommonClass.APP_TOKEN = CommonClass.getToken(AddSocialActivity.this);

        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.addSocialData(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<SocialResponse>() {
            @Override
            public void onResponse(Call<SocialResponse> call, Response<SocialResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());

                if (response.body() != null) {

                    if (response.body().isStatus()) {
                        Toast.makeText(AddSocialActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        //onBackPressed();

                        getAllSocialServicesList();

                    } else {
                        Toast.makeText(AddSocialActivity.this, "Social & Empowerment not Added ", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                } else {
                    Toast.makeText(AddSocialActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<SocialResponse> call, Throwable t) {
                pd.dismiss();
            }
        });
    }

    public void addSocialData1() {

        ProgressDialog pd = new ProgressDialog(AddSocialActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        SessinoManager sessinoManager = new SessinoManager(AddSocialActivity.this);
        String userId = sessinoManager.getUSERID();

        String url = "https://midev.zbapps.in/api/addSocial";

        if(jsonArray_SocalTraningWhat.size() != 0){

            for (int m = 0; m < jsonArray_SocalTraningWhat.size(); m++) {
                socalTraningWhat.put(jsonArray_SocalTraningWhat.get(m));
            }

        }

        if(jsonArray_SocalTraningWhere.size() != 0){

            for (int n = 0; n < jsonArray_SocalTraningWhere.size(); n++) {
                socalTraningWhere.put(jsonArray_SocalTraningWhere.get(n));
            }

        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("benificiary_id", CommonClass.benfeciary_ID);
            jsonObject.put("user_id", userId);
            jsonObject.put("socialsports", partofanycultural);
            jsonObject.put("socialsports_mention", binding.culturalsportseventsMention.getText().toString());
            jsonObject.put("socialgovt", partoflocal);
            jsonObject.put("socialgovtwhat", binding.Whatlocalgovt.getText().toString());
            jsonObject.put("socialtraining", traningparticipate);
            jsonObject.put("socialtrainingwhat", socalTraningWhat);
            jsonObject.put("socialtrainingwhere", socalTraningWhere);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("hsgug", jsonObject.toString());

        Log.d("hsadvu", String.valueOf(jsonArray_SocalTraningWhere));
        Log.d("hsadvu11", String.valueOf(jsonArray_SocalTraningWhat));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                pd.dismiss();

                try {
                    String status = response.getString("status");
                    String message = response.getString("message");

                    Toast.makeText(AddSocialActivity.this, message, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                pd.dismiss();
                Toast.makeText(AddSocialActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                String creds = String.format("%s:%s", "mobility", "admin@123");
                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.DEFAULT);

                params.put("Content-Type", "application/json");
                params.put("Authorization", auth);

                return params;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(AddSocialActivity.this);
        requestQueue.add(jsonObjectRequest);


    }

    public void getAllSocialServicesList(){

        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", userId);
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.listSocialService(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<Social_Example>() {
            @Override
            public void onResponse(Call<Social_Example> call, retrofit2.Response<Social_Example> response) {

                //    Log.d("TAG", "Eductionresponse" + response.body().getData().toString());
                //    Log.d("TAG", "onResponse response Eduction:" + response.body().toString());

                if (response.body() != null) {

                    SocialData socialData = new SocialData();
                    localRepo.deleteSocial(socialData);
                    localRepo.deleteSocial();

                    for(DataDemo_Social data : response.body().getData()){

                        localRepo.insertSocialData(data.getSocialdata());

                        Log.d("hdbgkbnkj",data.getSocialdata().toString());

                    }

                    Intent intent = new Intent(AddSocialActivity.this, BeneficaryDetailActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<Social_Example> call, Throwable t) {

                Toast.makeText(AddSocialActivity.this, "" + t, Toast.LENGTH_SHORT).show();

            }
        });

    }

}