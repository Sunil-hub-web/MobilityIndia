package com.example.mobilityindia.health.healthview;

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
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityHealthMainBinding;
import com.example.mobilityindia.health.healthview.adapter.HealthAdapter;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.ArrayList;
import java.util.List;

public class HealthMainActivity extends AppCompatActivity {
    ActivityHealthMainBinding binding;
    LocalRepo localRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_health_main);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        binding.toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        localRepo = new LocalRepo(HealthMainActivity.this);
        getdatehealthdate();
        binding.btnaddbanificary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HealthMainActivity.this,AddHealthActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        getdatehealthdate();
        super.onResume();
    }

    private void getdatehealthdate()
    {
        localRepo.getHealthCareDateList(CommonClass.benfeciary_ID).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> caselist) {
                System.out.println("SIZE>>>>>> "+caselist.size());

                if(caselist.size() > 0)
                {
                    binding.recycleview.setLayoutManager(new LinearLayoutManager(HealthMainActivity.this, RecyclerView.VERTICAL, false));
                    HealthAdapter healthAdapter = new HealthAdapter(new ArrayList<>(caselist),HealthMainActivity.this);
                    binding.recycleview.setAdapter(healthAdapter);
                    healthAdapter.notifyDataSetChanged();
                }else {
//                    binding.helplinecases.setVisibility(View.GONE);
//                    binding.textvisible.setVisibility(View.VISIBLE);

                }
            }
        });

    }

}