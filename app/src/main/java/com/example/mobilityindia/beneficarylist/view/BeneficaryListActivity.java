package com.example.mobilityindia.beneficarylist.view;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilityindia.R;
import com.example.mobilityindia.beneficarylist.adapter.BeneficaryListAdapter;
import com.example.mobilityindia.beneficarylist.addbeneficiary.AddbasicprofilebenificiaryActivity;
import com.example.mobilityindia.databinding.ActivityBeneficaryListBinding;
import com.example.mobilityindia.landingpage.view.LandingPageActivity;
import com.example.mobilityindia.syn1.view.SyncActivityAll;
import com.example.mobilityindia.sync.model.BeneData;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.ArrayList;
import java.util.List;

public class BeneficaryListActivity extends AppCompatActivity {
    ActivityBeneficaryListBinding binding;
    LocalRepo localRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_beneficary_list);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();

                startActivity(new Intent(BeneficaryListActivity.this, LandingPageActivity.class));
            }
        });

        localRepo = new LocalRepo(BeneficaryListActivity.this);

        callofflinebeneficarylistdata();

        binding.btnaddbanificary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(BeneficaryListActivity.this, AddbasicprofilebenificiaryActivity.class));
            }
        });

    }
    @Override
    protected void onStart() {
        callofflinebeneficarylistdata();
        super.onStart();
    }

    @Override
    protected void onResume() {
        callofflinebeneficarylistdata();
        super.onResume();
    }

    private void callofflinebeneficarylistdata() {

        localRepo.getBeneList().observe(this, new Observer<List<BeneData>>() {
            @Override
            public void onChanged(@Nullable List<BeneData> notes) {

                if(notes.size() > 0)
                {
                    binding.benefyid.setLayoutManager(new LinearLayoutManager(BeneficaryListActivity.this, RecyclerView.VERTICAL, false));
                    BeneficaryListAdapter viewMemberAdapter = new BeneficaryListAdapter( new ArrayList<>(notes),BeneficaryListActivity.this);
                    binding.benefyid.setAdapter(viewMemberAdapter);
                }
            }
        });
    }
}