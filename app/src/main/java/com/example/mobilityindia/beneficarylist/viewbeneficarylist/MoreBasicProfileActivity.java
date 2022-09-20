package com.example.mobilityindia.beneficarylist.viewbeneficarylist;

import static com.example.mobilityindia.constant.CommonClass.getHoboliMaster;
import static com.example.mobilityindia.constant.CommonClass.getStateMaster;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.mobilityindia.R;
import com.example.mobilityindia.beneficarylist.editbeneficarylist.EditMoreBasicProfileActivity;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityMoreBasicProfileBinding;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.model.BlockData;
import com.example.mobilityindia.sync.model.DistData;
import com.example.mobilityindia.sync.model.GPData;
import com.example.mobilityindia.sync.model.VillageData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MoreBasicProfileActivity extends AppCompatActivity {
    ActivityMoreBasicProfileBinding binding;
    LocalRepo localRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_more_basic_profile);
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
        binding.filtericon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MoreBasicProfileActivity.this, EditMoreBasicProfileActivity.class));
            }
        });
        localRepo = new LocalRepo(MoreBasicProfileActivity.this);
        callofflinelocaldatdata();
    }
    private void callofflinelocaldatdata()
    {
        localRepo.getSelectedBene(CommonClass.tempid).observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> singleMember) {
                System.out.println("Data Size: "+singleMember.size());
                if(singleMember.size() > 0)
                {

                    binding.village.setText(singleMember.get(0).getVillageId());

                    String villageiD = String.valueOf(singleMember.get(0).getVillageId());

                    if(villageiD.equals("")|| villageiD.equals("null")){

                    }else {
                        localRepo.getVillageDataDetail(Integer.valueOf(villageiD)).observe(MoreBasicProfileActivity.this, new Observer<List<VillageData>>() {
                            @Override
                            public void onChanged(List<VillageData> villageData)
                            {

                                binding.village.setText(villageData.get(0).getVillageName());
                                String gpid = villageData.get(0).getGpId();
                                String hobliid = villageData.get(0).getHobliId();
                                String blockId = villageData.get(0).getBlockId();
                                String districtId = villageData.get(0).getDistrictId();
                                String stateId = villageData.get(0).getStateId();

                                localRepo.getGpListDetails(Integer.valueOf(gpid)).observe(MoreBasicProfileActivity.this, new Observer<List<GPData>>() {
                                    @Override
                                    public void onChanged(List<GPData> gpData) {
                                        binding.gp.setText(gpData.get(0).getGpName());
                                    }
                                });

                                String getholi = getHoboliMaster(MoreBasicProfileActivity.this);
                                try {
                                    JSONObject obj = new JSONObject(getholi);
                                    if (obj.optString("status", "fail").equals("true")) {
                                        JSONArray jsonArray = obj.optJSONArray("data");
                                        for (int i = 0; i < jsonArray.length(); i++)
                                        {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            String hoboliid  = jsonObject.getString("hobli_id");
                                            binding.hobli.setText(jsonObject.getString("hobli_name"));
                                        }
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                localRepo.getBlockDetailData(Integer.valueOf(blockId)).observe(MoreBasicProfileActivity.this, new Observer<List<BlockData>>() {
                                    @Override
                                    public void onChanged(List<BlockData> blockData) {
                                        binding.block.setText(blockData.get(0).getBlockName());
                                    }
                                });

                                localRepo.getDistDetaild(Integer.valueOf(districtId)).observe(MoreBasicProfileActivity.this, new Observer<List<DistData>>() {
                                    @Override
                                    public void onChanged(List<DistData> distData) {
                                        binding.distrit.setText(distData.get(0).getDistrict_name());
                                    }
                                });

                                String getstate = getStateMaster(MoreBasicProfileActivity.this);

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



                            }
                        });
                    }

                    binding.school.setText(singleMember.get(0).getSchoolAnganwadiName());
                    binding.contact1.setText(singleMember.get(0).getContactNo());
                    binding.contact2.setText(singleMember.get(0).contactNoOther);
                    binding.email.setText(singleMember.get(0).emailId);
                    binding.rationcard.setText(singleMember.get(0).rationCardNo);
                    binding.sanitation.setText(singleMember.get(0).sanitationToilet);
                    binding.appliance.setText(singleMember.get(0).appliances);
                    binding.surgery.setText(singleMember.get(0).surgery);
                    binding.govtfacility.setText(singleMember.get(0).govtFacilities);

                    binding.familymemberadultm.setText(singleMember.get(0).familyMember);
                    binding.familymemberadultf.setText(singleMember.get(0).familyMemberAdults);

                    binding.fmlymemberchildrnm.setText(singleMember.get(0).familyMemberChildM);
                    binding.fmlymembrchilf.setText(singleMember.get(0).familyMemberChildF);
                    binding.childrnundergeducationm.setText(singleMember.get(0).childrenUndergoingEducationM);
                    binding.childrenundergoeeseducationf.setText(singleMember.get(0).childrenUndergoingEducationF);
                    binding.dropoutlessthen14m.setText(singleMember.get(0).dropoutsLess14M);
                    binding.dropoutlessthen14f.setText(singleMember.get(0).dropoutsLess14M);
                    binding.dropoutm.setText(singleMember.get(0).dropouts1418M);
                    binding.dropoutf.setText(singleMember.get(0).dropouts1418F);
                    binding.earingmemberflym.setText(singleMember.get(0).earningMembersFlyM);
                    binding.earingmemberflyf.setText(singleMember.get(0).earningMembersFlyF);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        callofflinelocaldatdata();
        super.onStart();
    }
}