package com.example.mobilityindia.beneficarylist.editbeneficarylist;

import static com.example.mobilityindia.constant.CommonClass.getHoboliMaster;
import static com.example.mobilityindia.constant.CommonClass.getStateMaster;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.beneficarylist.addbeneficiary.AddMoreprofilebeneficaryActivity;
import com.example.mobilityindia.beneficarylist.view.BeneficaryDetailActivity;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEditMoreBasicProfileBinding;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.model.BlockData;
import com.example.mobilityindia.sync.model.DistData;
import com.example.mobilityindia.sync.model.GPData;
import com.example.mobilityindia.sync.model.VillageData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EditMoreBasicProfileActivity extends AppCompatActivity {
    ActivityEditMoreBasicProfileBinding binding;
    List<String> statelist;
    List<String> stateIDlist;
    ArrayList<String>distIDlist;
    List<String> blockIdlist;
    List<String> gpIDlist;
    List<String> hobilIDlist;
    List<String> villageIDlist;
    LocalRepo localRepo;
    String stateid = "",districId = "",blockId = "",gpId = "",villageId = "",hobliId = "";
    String districId1 = "",districId2 = "",districId3 = "",districId4 = "",districId5 = "",districId6 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_more_basic_profile);
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
        statelist = new ArrayList<>();
        stateIDlist = new ArrayList<>();
        blockIdlist = new ArrayList<>();
        gpIDlist = new ArrayList<>();

        hobilIDlist = new ArrayList<>();
        distIDlist = new ArrayList<>();
        villageIDlist = new ArrayList<>();
        localRepo = new LocalRepo(EditMoreBasicProfileActivity.this);

        String[] itemNames = getResources().getStringArray(R.array.selectone);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.rationcard.setAdapter(adapter2);

        binding.sanitation.setAdapter(adapter2);
        binding.appliance.setAdapter(adapter2);
        binding.surgery.setAdapter(adapter2);
        binding.govtfacility.setAdapter(adapter2);

        binding.rationcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.rationcard).showDropDown();
            }
        });
        binding.rationcard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.rationcard.getAdapter().getItem(position).toString();
            }
        });
        binding.sanitation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.sanitation).showDropDown();
            }
        });
        binding.sanitation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.sanitation.getAdapter().getItem(position).toString();
            }
        });
        binding.appliance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.appliance).showDropDown();
            }
        });
        binding.appliance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.appliance.getAdapter().getItem(position).toString();
            }
        });
        binding.surgery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.surgery).showDropDown();
            }
        });
        binding.surgery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.surgery.getAdapter().getItem(position).toString();
            }
        });
        binding.govtfacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.govtfacility).showDropDown();
            }
        });
        binding.govtfacility.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.govtfacility.getAdapter().getItem(position).toString();
            }
        });

        String getstate = getStateMaster(EditMoreBasicProfileActivity.this);
        try {
            JSONObject obj = new JSONObject(getstate);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    statelist.add(jsonObject.getString("state_name"));
                    stateIDlist.add(jsonObject.getString("state_id"));

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, statelist);
        adapterlist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.state.setAdapter(adapterlist);

        binding.state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.state.showDropDown();
            }
        });
        binding.state.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                stateid = stateIDlist.get(position);
                getDistData(stateid);

                if (districId1.equals("")) {

                    districId1 = stateid;

                } else if (districId1.equals(stateid)) {
                } else {

                    binding.district.setText("");
                    binding.hobli.setText("");
                    binding.village.setText("");
                    binding.block.setText("");
                    binding.gp.setText("");
                    districId1 = stateid;
                }
            }
        });

       /* String getstate = getStateMaster(EditMoreBasicProfileActivity.this);
        try {
            JSONObject obj = new JSONObject(getstate);
            if (obj.optString("status", "fail").equals("true")) {
                JSONArray jsonArray = obj.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    statelist.add(jsonObject.getString("state_name"));
                    stateIDlist.add(jsonObject.getString("state_id"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, statelist);
        adapterlist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.state.setAdapter(adapterlist);*/

        binding.district.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.district.showDropDown();
            }
        });
        binding.district.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                districId = distIDlist.get(position);
                getBlockData(districId);

                if (districId2.equals("")) {

                    districId2 = districId;

                } else if (districId2.equals(districId)) {
                } else {

                    binding.hobli.setText("");
                    binding.village.setText("");
                    binding.block.setText("");
                    binding.gp.setText("");
                    districId2 = districId;
                }
            }
        });

        binding.block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.block.showDropDown();
                //blockId = blockIdlist.get(position);
            }
        });
        binding.block.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                blockId = blockIdlist.get(position);
                getHobliData(blockId);


                if (districId3.equals("")) {

                    districId3 = blockId;

                } else if (districId3.equals(blockId)) {
                } else {
                    // binding.district.setText("");
                    binding.hobli.setText("");
                    binding.village.setText("");
                    //binding.block.setText("");
                    binding.gp.setText("");
                    districId3 = blockId;
                }

            }
        });

        binding.hobli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.hobli.showDropDown();
            }
        });
        binding.hobli.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hobliId = hobilIDlist.get(position);
                getGpData(hobliId);

                if (districId4.equals("")) {

                    districId4 = hobliId;

                } else if (districId4.equals(hobliId)) {
                } else {
                    // binding.district.setText("");
                    binding.village.setText("");
                    //binding.block.setText("");
                    binding.gp.setText("");
                    districId4 = hobliId;
                }
            }
        });

        binding.gp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.gp.showDropDown();
            }
        });
        binding.gp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gpId = gpIDlist.get(position);
                getVillageData(gpId);


                if (districId5.equals("")) {

                    districId5 = gpId;

                } else if (districId5.equals(gpId)) {
                } else {
                    // binding.district.setText("");
                    binding.village.setText("");

                    districId5 = gpId;
                }
            }
        });

        binding.village.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.village.showDropDown();
            }
        });
        binding.village.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                villageId = villageIDlist.get(position);


                if (districId6.equals("")) {

                    districId6 = villageId;

                } else if (districId6.equals(villageId)) {
                } else {
                    // binding.district.setText("");
                    //binding.village.setText("");
                    //binding.block.setText("");
                    // binding.gp.setText("");

                    districId6 = villageId;
                }
            }
        });


        callofflinelocaldatdata();

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callupdatelocaledata();
            }
        });
        binding.familymemberadultm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.familymemberadultm.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval <15){

                    }else{
                        binding.familymemberadultm.setText("");
                        Toast.makeText(EditMoreBasicProfileActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        binding.familymemberadultf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.familymemberadultf.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval <15){

                    }else{
                        binding.familymemberadultf.setText("");
                        Toast.makeText(EditMoreBasicProfileActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        binding.fmlymemberchildrnm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.fmlymemberchildrnm.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval <15){

                    }else{
                        binding.fmlymemberchildrnm.setText("");
                        Toast.makeText(EditMoreBasicProfileActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });
        binding.fmlymembrchilf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.fmlymembrchilf.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval <15){

                    }else{
                        binding.fmlymembrchilf.setText("");
                        Toast.makeText(EditMoreBasicProfileActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        binding.childrnundergeducationm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.childrnundergeducationm.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval <15){

                    }else{
                        binding.childrnundergeducationm.setText("");
                        Toast.makeText(EditMoreBasicProfileActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        binding.childrenundergoeeseducationf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.childrenundergoeeseducationf.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval <15){

                    }else{
                        binding.childrenundergoeeseducationf.setText("");
                        Toast.makeText(EditMoreBasicProfileActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        binding.dropoutlessthen14f.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.dropoutlessthen14f.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval <15){

                    }else{
                        binding.dropoutlessthen14f.setText("");
                        Toast.makeText(EditMoreBasicProfileActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        binding.dropoutlessthen14m.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.dropoutlessthen14m.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval <15){

                    }else{
                        binding.dropoutlessthen14m.setText("");
                        Toast.makeText(EditMoreBasicProfileActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        binding.dropoutm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.dropoutm.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval <15){

                    }else{
                        binding.dropoutm.setText("");
                        Toast.makeText(EditMoreBasicProfileActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        binding.dropoutf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.dropoutf.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval <15){

                    }else{
                        binding.dropoutf.setText("");
                        Toast.makeText(EditMoreBasicProfileActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        binding.earingmemberflym.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.earingmemberflym.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval <15){

                    }else{
                        binding.earingmemberflym.setText("");
                        Toast.makeText(EditMoreBasicProfileActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        binding.earingmemberflyf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!binding.earingmemberflyf.getText().toString().isEmpty()){
                    int tempval = Integer.parseInt(s.toString());
                    if(tempval <15){

                    }else{
                        binding.earingmemberflyf.setText("");
                        Toast.makeText(EditMoreBasicProfileActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void callupdatelocaledata()
    {
        localRepo.getSelectedBene(CommonClass.tempid).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {
                if (binding.state.getText().toString().trim().equals("") || binding.state.getText().toString() == null) {
                    Toast.makeText(EditMoreBasicProfileActivity.this, "Please Enter state name", Toast.LENGTH_SHORT).show();

                } else if (binding.district.getText().toString().trim().equals("") || binding.district.getText().toString() == null) {
                    Toast.makeText(EditMoreBasicProfileActivity.this, "Please Enter district name", Toast.LENGTH_SHORT).show();

                } else if (binding.block.getText().toString().trim().equals("") || binding.block.getText().toString() == null) {
                    Toast.makeText(EditMoreBasicProfileActivity.this, "Please Enter block name", Toast.LENGTH_SHORT).show();

                } else if (binding.hobli.getText().toString().trim().equals("") || binding.hobli.getText().toString() == null) {
                    Toast.makeText(EditMoreBasicProfileActivity.this, "Please Enter hobli name", Toast.LENGTH_SHORT).show();

                } else if (binding.gp.getText().toString().trim().equals("") || binding.gp.getText().toString() == null) {
                    Toast.makeText(EditMoreBasicProfileActivity.this, "Please Enter gp name", Toast.LENGTH_SHORT).show();

                } else if (binding.village.getText().toString().trim().equals("") || binding.village.getText().toString() == null) {
                    Toast.makeText(EditMoreBasicProfileActivity.this, "Please Enter village name", Toast.LENGTH_SHORT).show();

                } else {

                    if(validateFields()){

                        if (singleMember.size() > 0)
                        {
                            singleMember.get(0).setTempId(CommonClass.tempid);
                            singleMember.get(0).setSchoolAnganwadiName(binding.school.getText().toString());
                            singleMember.get(0).setContactNo(binding.contact1.getText().toString());
                            singleMember.get(0).setContactNoOther(binding.contact2.getText().toString());
                            singleMember.get(0).setEmailId(binding.email.getText().toString());

                            singleMember.get(0).setRationCardNo(binding.rationcard.getText().toString());
                            singleMember.get(0).setSanitationToilet(binding.sanitation.getText().toString());
                            singleMember.get(0).setAppliances(binding.appliance.getText().toString());
                            singleMember.get(0).setSurgery(binding.surgery.getText().toString());
                            singleMember.get(0).setGovtFacilities(binding.govtfacility.getText().toString());

                            singleMember.get(0).setFamilyMember(binding.familymemberadultm.getText().toString());
                            singleMember.get(0).setFamilyMemberAdults(binding.familymemberadultf.getText().toString());
                            singleMember.get(0).setFamilyMemberChildM(binding.fmlymemberchildrnm.getText().toString());
                            singleMember.get(0).setFamilyMemberChildF(binding.fmlymembrchilf.getText().toString());
                            singleMember.get(0).setChildrenUndergoingEducationM(binding.childrnundergeducationm.getText().toString());
                            singleMember.get(0).setChildrenUndergoingEducationF(binding.childrenundergoeeseducationf.getText().toString());

                            singleMember.get(0).setDropoutsLess14M(binding.dropoutlessthen14m.getText().toString());
                            singleMember.get(0).setDropoutsLess14F(binding.dropoutlessthen14f.getText().toString());

                            singleMember.get(0).setDropouts1418M(binding.dropoutm.getText().toString());
                            singleMember.get(0).setDropouts1418F(binding.dropoutf.getText().toString());
                            singleMember.get(0).setEarningMembersFlyM(binding.earingmemberflym.getText().toString());
                            singleMember.get(0).setEarningMembersFlyF(binding.earingmemberflyf.getText().toString());

                            localRepo.updateBene(singleMember.get(0));
                            singleMember.get(0).setFlag("update");

                            Toast.makeText(EditMoreBasicProfileActivity.this, "More Basic profile updated in locally.", Toast.LENGTH_SHORT).show();

                            onBackPressed();

                        }

                    }else{

                        //saveData();
                    }

                       /* if(binding.email.getText().toString().trim().equals("") || binding.email.getText().toString() == null){

                            if(binding.contact1.getText().toString().trim().equals("") || binding.contact1.getText().toString() == null){

                                if(binding.contact2.getText().toString().trim().equals("") || binding.contact2.getText().toString() == null){

                                    saveData();

                                }

                            }

                        }else{


                        }*/

                }

            }
        });
    }

    Boolean validateFields() {

        if (binding.contact1.length() != 0) {
            if(binding.contact1.getText().toString().trim().length() != 10) {

                Toast.makeText(this, "Please Enter 10 Digit Mobile No", Toast.LENGTH_SHORT).show();

                return false;
            }
        }

        if (binding.contact2.length() != 0) {
            if(binding.contact2.getText().toString().trim().length() != 10){

                Toast.makeText(this, "Please Enter 10 Digit Mobile No", Toast.LENGTH_SHORT).show();

                return false;
            }
        }

        if (binding.email.length() != 0) {
            if(!isValidEmail(binding.email.getText().toString())){

                Toast.makeText(this, "Please Enter Valid EmailId", Toast.LENGTH_SHORT).show();

                return false;
            }
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void  getDistData(String stateid){
        ArrayList<String>distlist = new ArrayList<>();
        distIDlist = new ArrayList<>();
        localRepo.getDistWithStateId(Integer.parseInt(stateid)).observe(this, new Observer<List<DistData>>() {
            @Override
            public void onChanged(@Nullable List<DistData> vastiDataa) {
                if(vastiDataa.size() >0) {
                    for(int i =0;i<vastiDataa.size();i++){
                        distlist.add(vastiDataa.get(i).getDistrict_name());
                        distIDlist.add(vastiDataa.get(i).getDistrict_id());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, distlist);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.district.setAdapter(adapter);

                }

            }
        });
    }

    public void  getBlockData(String districtId){
        ArrayList<String>blocklist = new ArrayList<>();
        blockIdlist = new ArrayList<>();
        localRepo.getBlockWithDistId(Integer.parseInt(districtId)).observe(this, new Observer<List<BlockData>>() {
            @Override
            public void onChanged(@Nullable List<BlockData> vastiDataa) {
                if(vastiDataa.size() >0) {
                    for(int i =0;i<vastiDataa.size();i++){
                        blocklist.add(vastiDataa.get(i).getBlockName());
                        blockIdlist.add(vastiDataa.get(i).getBlockId());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, blocklist);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.block.setAdapter(adapter);
                }
            }
        });
    }

    public void  getHobliData(String districtId){
        ArrayList<String>hoblilist = new ArrayList<>();
        hobilIDlist = new ArrayList<>();
        localRepo.getBlockWithDistId(Integer.parseInt(districtId)).observe(this, new Observer<List<BlockData>>() {
            @Override
            public void onChanged(@Nullable List<BlockData> vastiDataa) {
                if(vastiDataa.size() >0) {
                    for(int i =0;i<vastiDataa.size();i++){
                        hoblilist.add(vastiDataa.get(i).getBlockName());
                        hobilIDlist.add(vastiDataa.get(i).getBlockId());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, hoblilist);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.hobli.setAdapter(adapter);
                }
            }
        });
    }

    public void  getGpData(String blockId){
        ArrayList<String>gplist = new ArrayList<>();
        gpIDlist = new ArrayList<>();
        localRepo.getGPWithBlockId(Integer.parseInt(blockId)).observe(this, new Observer<List<GPData>>() {
            @Override
            public void onChanged(@Nullable List<GPData> vastiDataa) {
                if(vastiDataa.size() >0) {
                    for(int i =0;i<vastiDataa.size();i++){
                        gplist.add(vastiDataa.get(i).getGpName());
                        gpIDlist.add(vastiDataa.get(i).getGpId());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, gplist);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.gp.setAdapter(adapter);
                }
            }
        });
    }

    public void  getVillageData(String gpId){
        ArrayList<String>villageId = new ArrayList<>();
        villageIDlist = new ArrayList<>();
        localRepo.getVillageWithGpId(Integer.parseInt(gpId)).observe(this, new Observer<List<VillageData>>() {
            @Override
            public void onChanged(@Nullable List<VillageData> vastiDataa) {
                if(vastiDataa.size() >0) {
                    for(int i =0;i<vastiDataa.size();i++){
                        villageId.add(vastiDataa.get(i).getVillageName());
                        villageIDlist.add(vastiDataa.get(i).getId());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, villageId);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.village.setAdapter(adapter);
                }
            }
        });
    }

    private void callofflinelocaldatdata()
    {
        localRepo.getSelectedBene(CommonClass.tempid).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {
                if(singleMember.size() > 0)
                {
                    binding.village.setText(singleMember.get(0).getVillageId());
                    String villageiD = String.valueOf(singleMember.get(0).getVillageId());
                    if(villageiD.equals("")||villageiD.equals("null")){
                    }else {
                        localRepo.getVillageDataDetail(Integer.valueOf(villageiD)).observe(EditMoreBasicProfileActivity.this, new Observer<List<VillageData>>() {
                            @Override
                            public void onChanged(List<VillageData> villageData)
                            {
                                binding.village.setText(villageData.get(0).getVillageName());
                                String gpid = villageData.get(0).getGpId();
                                String hobliid = villageData.get(0).getHobliId();
                                String blockId = villageData.get(0).getBlockId();
                                String districtId = villageData.get(0).getDistrictId();
                                String stateId = villageData.get(0).getStateId();

                                localRepo.getGpListDetails(Integer.valueOf(gpid)).observe(EditMoreBasicProfileActivity.this, new Observer<List<GPData>>() {
                                    @Override
                                    public void onChanged(List<GPData> gpData) {
                                        binding.gp.setText(gpData.get(0).getGpName());
                                    }
                                });

                                String getholi = getHoboliMaster(EditMoreBasicProfileActivity.this);
                                try {
                                    JSONObject obj = new JSONObject(getholi);
                                    if (obj.optString("status", "fail").equals("true")) {
                                        JSONArray jsonArray = obj.optJSONArray("data");
                                        for (int i = 0; i < jsonArray.length(); i++)
                                        {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            String hoboliid  = jsonObject.getString("hobli_id");
                                            if(hoboliid.equalsIgnoreCase(hobliid)){
                                                binding.hobli.setText(jsonObject.getString("hobli_name"));
                                            }

                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                               /* ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, hoblilist);
                                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                binding.hobli.setAdapter(adapter1);*/

                                localRepo.getBlockDetailData(Integer.valueOf(blockId)).observe(EditMoreBasicProfileActivity.this, new Observer<List<BlockData>>() {
                                    @Override
                                    public void onChanged(List<BlockData> blockData) {
                                        binding.block.setText(blockData.get(0).getBlockName());
                                    }
                                });

                                localRepo.getDistDetaild(Integer.valueOf(districtId)).observe(EditMoreBasicProfileActivity.this, new Observer<List<DistData>>() {
                                    @Override
                                    public void onChanged(List<DistData> distData) {
                                        binding.district.setText(distData.get(0).getDistrict_name());
                                    }
                                });
                                String getstate = getStateMaster(EditMoreBasicProfileActivity.this);

                                try {
                                    JSONObject obj = new JSONObject(getstate);
                                    if (obj.optString("status", "fail").equals("true")) {
                                        JSONArray jsonArray = obj.optJSONArray("data");
                                        for (int i = 0; i < jsonArray.length(); i++)
                                        {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            String stateid  = jsonObject.getString("state_id");
                                            if(stateid.equalsIgnoreCase(stateId)){
                                                binding.state.setText(jsonObject.getString("state_name"));
                                            }
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                ArrayAdapter<String> adapterlist = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, statelist);
                                adapterlist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                binding.state.setAdapter(adapterlist);
                            }
                        });

                    }

                    binding.school.setText(singleMember.get(0).getSchoolAnganwadiName());
                    binding.contact1.setText(singleMember.get(0).getContactNo());
                    binding.contact2.setText(singleMember.get(0).getContactNoOther());
                    binding.email.setText(singleMember.get(0).getEmailId());

                    binding.rationcard.setText(singleMember.get(0).getRationCardNo());
                    String[] itemNames = getResources().getStringArray(R.array.selectone);
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.rationcard.setAdapter(adapter2);

                    binding.sanitation.setText(singleMember.get(0).getSanitationToilet());
                    String[] itemNamess = getResources().getStringArray(R.array.selectone);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNamess);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.sanitation.setAdapter(adapter);

                    binding.appliance.setText(singleMember.get(0).getAppliances());
                    String[] itemName = getResources().getStringArray(R.array.selectone);
                    ArrayAdapter<String> adaptea = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemName);
                    adaptea.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.appliance.setAdapter(adaptea);


                    binding.surgery.setText(singleMember.get(0).getSurgery());
                    String[] itemNamesu = getResources().getStringArray(R.array.selectone);
                    ArrayAdapter<String> adaptes = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNamesu);
                    adaptes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.surgery.setAdapter(adaptes);

                    binding.govtfacility.setText(singleMember.get(0).getGovtFacilities());
                    String[] itemNamesug = getResources().getStringArray(R.array.selectone);
                    ArrayAdapter<String> adapteg = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNamesug);
                    adapteg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.govtfacility.setAdapter(adapteg);


                    binding.familymemberadultm.setText(singleMember.get(0).getFamilyMember());
                    binding.familymemberadultf.setText(singleMember.get(0).getFamilyMemberAdults());
                    binding.fmlymemberchildrnm.setText(singleMember.get(0).getFamilyMemberChildM());
                    binding.fmlymembrchilf.setText(singleMember.get(0).getFamilyMemberChildF());
                    binding.childrnundergeducationm.setText(singleMember.get(0).getChildrenUndergoingEducationM());
                    binding.childrenundergoeeseducationf.setText(singleMember.get(0).getChildrenUndergoingEducationF());
                    binding.dropoutlessthen14m.setText(singleMember.get(0).getDropoutsLess14M());
                    binding.dropoutlessthen14f.setText(singleMember.get(0).getDropoutsLess14F());
                    binding.dropoutm.setText(singleMember.get(0).getDropouts1418M());
                    binding.dropoutf.setText(singleMember.get(0).getDropouts1418F());
                    binding.earingmemberflym.setText(singleMember.get(0).getEarningMembersFlyM());
                    binding.earingmemberflyf.setText(singleMember.get(0).getEarningMembersFlyF());

                }
            }
        });
    }

}