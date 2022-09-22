package com.example.mobilityindia.beneficarylist.addbeneficiary;

import static com.example.mobilityindia.constant.CommonClass.email;
import static com.example.mobilityindia.constant.CommonClass.getHoboliMaster;
import static com.example.mobilityindia.constant.CommonClass.getStateMaster;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
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
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityAddMoreprofilebeneficaryBinding;
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

public class AddMoreprofilebeneficaryActivity extends AppCompatActivity {
    List<String> statelist;
    List<String> stateIDlist;
    ArrayList<String> distIDlist;
    List<String> blockIdlist;
    List<String> gpIDlist;
    List<String> hobilIDlist;
    List<String> villageIDlist;
    LocalRepo localRepo;
    String stateid = "", districId = "", blockId = "", gpId = "", villageId = "", hobliId = "",districId1 = "",districId2 = "",districId3 = "",districId4 = "",districId5 = "",districId6 = "";
    private ActivityAddMoreprofilebeneficaryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_moreprofilebeneficary);
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
        localRepo = new LocalRepo(AddMoreprofilebeneficaryActivity.this);

        String[] itemNames = getResources().getStringArray(R.array.selectone1);
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

        String getstate = getStateMaster(AddMoreprofilebeneficaryActivity.this);
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

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.state.getText().toString().trim().equals("") || binding.state.getText().toString() == null) {
                    Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Please Enter state name", Toast.LENGTH_SHORT).show();

                } else if (binding.district.getText().toString().trim().equals("") || binding.district.getText().toString() == null) {
                    Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Please Enter district name", Toast.LENGTH_SHORT).show();

                } else if (binding.block.getText().toString().trim().equals("") || binding.block.getText().toString() == null) {
                    Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Please Enter block name", Toast.LENGTH_SHORT).show();

                } else if (binding.hobli.getText().toString().trim().equals("") || binding.hobli.getText().toString() == null) {
                    Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Please Enter hobli name", Toast.LENGTH_SHORT).show();

                } else if (binding.gp.getText().toString().trim().equals("") || binding.gp.getText().toString() == null) {
                    Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Please Enter gp name", Toast.LENGTH_SHORT).show();

                } else if (binding.village.getText().toString().trim().equals("") || binding.village.getText().toString() == null) {
                    Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Please Enter village name", Toast.LENGTH_SHORT).show();

                } else {

                    if (validateFields()) {

                        saveData();

                    } else {

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
        binding.viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

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
                if (!binding.familymemberadultm.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval < 15) {

                    } else {
                        binding.familymemberadultm.setText("");
                        Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
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
                if (!binding.familymemberadultf.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval < 15) {

                    } else {
                        binding.familymemberadultf.setText("");
                        Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
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
                if (!binding.fmlymemberchildrnm.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval < 15) {

                    } else {
                        binding.fmlymemberchildrnm.setText("");
                        Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
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
                if (!binding.fmlymembrchilf.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval < 15) {

                    } else {
                        binding.fmlymembrchilf.setText("");
                        Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
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
                if (!binding.childrnundergeducationm.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval < 15) {

                    } else {
                        binding.childrnundergeducationm.setText("");
                        Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
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
                if (!binding.childrenundergoeeseducationf.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval < 15) {

                    } else {
                        binding.childrenundergoeeseducationf.setText("");
                        Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
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
                if (!binding.dropoutlessthen14f.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval < 15) {

                    } else {
                        binding.dropoutlessthen14f.setText("");
                        Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

        binding.drpuotlessthen14m.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!binding.drpuotlessthen14m.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval < 15) {

                    } else {
                        binding.drpuotlessthen14m.setText("");
                        Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
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
                if (!binding.dropoutm.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval < 15) {

                    } else {
                        binding.dropoutm.setText("");
                        Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
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
                if (!binding.dropoutf.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval < 15) {

                    } else {
                        binding.dropoutf.setText("");
                        Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
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
                if (!binding.earingmemberflym.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval < 15) {

                    } else {
                        binding.earingmemberflym.setText("");
                        Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
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
                if (!binding.earingmemberflyf.getText().toString().isEmpty()) {
                    int tempval = Integer.parseInt(s.toString());
                    if (tempval < 15) {

                    } else {
                        binding.earingmemberflyf.setText("");
                        Toast.makeText(AddMoreprofilebeneficaryActivity.this, "Value should be less than 15. ", Toast.LENGTH_SHORT).show();
                    }


                }


            }
        });

    }

    public void getDistData(String stateid) {
        ArrayList<String> distlist = new ArrayList<>();
        distIDlist = new ArrayList<>();
        localRepo.getDistWithStateId(Integer.parseInt(stateid)).observe(this, new Observer<List<DistData>>() {
            @Override
            public void onChanged(@Nullable List<DistData> vastiDataa) {
                if (vastiDataa.size() > 0) {
                    for (int i = 0; i < vastiDataa.size(); i++) {
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

    public void getBlockData(String districtId) {
        ArrayList<String> blocklist = new ArrayList<>();
        blockIdlist = new ArrayList<>();
        localRepo.getBlockWithDistId(Integer.parseInt(districtId)).observe(this, new Observer<List<BlockData>>() {
            @Override
            public void onChanged(@Nullable List<BlockData> vastiDataa) {
                if (vastiDataa.size() > 0) {
                    for (int i = 0; i < vastiDataa.size(); i++) {
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

    public void getHobliData(String blockid) {
       /* ArrayList<String>hoblilist = new ArrayList<>();
        hobilIDlist = new ArrayList<>();*/

        String getHobli = getHoboliMaster(AddMoreprofilebeneficaryActivity.this);
        ArrayList<String> hoblilist = new ArrayList<>();
        hobilIDlist = new ArrayList<>();
        try {
            JSONObject obj1 = new JSONObject(getHobli);
            if (obj1.optString("status", "fail").equals("true")) {
                JSONArray jsonArray1 = obj1.optJSONArray("data");
                for (int i = 0; i < jsonArray1.length(); i++) {
                    JSONObject jsonObject = jsonArray1.getJSONObject(i);
                    String blockid1 = jsonObject.getString("block_id");

                    if (blockid1.equals(blockid)) {

                        hoblilist.add(jsonObject.getString("hobli_name"));
                        hobilIDlist.add(jsonObject.getString("block_id"));
                    }
                }
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, hoblilist);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.hobli.setAdapter(adapter1);

                Log.d("hsvuvj", hoblilist.toString());

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String> adapterlist1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, hoblilist);
        adapterlist1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.hobli.setAdapter(adapterlist1);

       /* localRepo.getBlockWithDistId(Integer.parseInt(districtId)).observe(this, new Observer<List<BlockData>>() {
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
        });*/
    }

    public void getGpData(String blockId) {
        ArrayList<String> gplist = new ArrayList<>();
        gpIDlist = new ArrayList<>();
        localRepo.getGPWithBlockId(Integer.parseInt(blockId)).observe(this, new Observer<List<GPData>>() {
            @Override
            public void onChanged(@Nullable List<GPData> vastiDataa) {
                if (vastiDataa.size() > 0) {
                    for (int i = 0; i < vastiDataa.size(); i++) {
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

    public void getVillageData(String gpId) {
        ArrayList<String> villageId = new ArrayList<>();
        villageIDlist = new ArrayList<>();
        localRepo.getVillageWithGpId(Integer.parseInt(gpId)).observe(this, new Observer<List<VillageData>>() {
            @Override
            public void onChanged(@Nullable List<VillageData> vastiDataa) {
                if (vastiDataa.size() > 0) {
                    for (int i = 0; i < vastiDataa.size(); i++) {
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

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public void saveData() {

        CommonClass.villageID = villageId;
        CommonClass.address = binding.address.getText().toString();
        CommonClass.schoolname = binding.school.getText().toString();
        CommonClass.contactNo1 = binding.contact1.getText().toString();
        CommonClass.contactNo2 = binding.contact2.getText().toString();
        CommonClass.email = binding.email.getText().toString();

        CommonClass.rationcard = binding.rationcard.getText().toString();
        CommonClass.sanitation = binding.sanitation.getText().toString();
        CommonClass.appliance = binding.appliance.getText().toString();
        CommonClass.surgery = binding.surgery.getText().toString();
        CommonClass.govtfacility = binding.govtfacility.getText().toString();
        CommonClass.govtfacility_mention = binding.GovtFacilitymention.getText().toString();


        CommonClass.familymemberadultm = binding.familymemberadultm.getText().toString();
        CommonClass.familymemberadultf = binding.familymemberadultf.getText().toString();
        CommonClass.fmlymemberchildrnm = binding.fmlymemberchildrnm.getText().toString();
        CommonClass.fmlymembrchilf = binding.fmlymembrchilf.getText().toString();
        CommonClass.childrnundergeducationm = binding.childrnundergeducationm.getText().toString();
        CommonClass.childrenundergoeeseducationf = binding.childrenundergoeeseducationf.getText().toString();
        CommonClass.drpuotlessthen14m = binding.drpuotlessthen14m.getText().toString();
        CommonClass.dropoutlessthen14f = binding.dropoutlessthen14f.getText().toString();
        CommonClass.dropoutm = binding.dropoutm.getText().toString();
        CommonClass.dropoutf = binding.dropoutf.getText().toString();
        CommonClass.earingmemberflym = binding.earingmemberflym.getText().toString();
        CommonClass.earingmemberflyf = binding.earingmemberflyf.getText().toString();

        startActivity(new Intent(AddMoreprofilebeneficaryActivity.this, AddshgbeneficiaryActivity.class));

    }

    Boolean validateFields() {

        if (binding.contact1.length() != 0) {
            if (binding.contact1.getText().toString().trim().length() != 10) {

                Toast.makeText(this, "Please Enter 10 Digit Mobile No", Toast.LENGTH_SHORT).show();

                return false;
            }
        }

        if (binding.contact2.length() != 0) {
            if (binding.contact2.getText().toString().trim().length() != 10) {

                Toast.makeText(this, "Please Enter 10 Digit Mobile No", Toast.LENGTH_SHORT).show();

                return false;
            }
        }

        if (binding.email.length() != 0) {
            if (!isValidEmail(binding.email.getText().toString())) {

                Toast.makeText(this, "Please Enter Valid EmailId", Toast.LENGTH_SHORT).show();

                return false;
            }
        }

        return true;
    }
}