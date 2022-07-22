package com.example.mobilityindia.education;

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
import com.example.mobilityindia.databinding.ActivityEducationMainBinding;
import com.example.mobilityindia.education.educationadapter.EducationAdapter;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.ArrayList;
import java.util.List;

public class EducationMainActivity extends AppCompatActivity {
    ActivityEducationMainBinding binding;
    LocalRepo localRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_education_main);
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

        localRepo = new LocalRepo(EducationMainActivity.this);
        binding.btnaddbanificary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EducationMainActivity.this, AddEducationActivity.class));
            }
        });
        getdatehealthdate();

    }

    @Override
    protected void onResume() {
        getdatehealthdate();
        super.onResume();
    }

    private void getdatehealthdate()
    {
        localRepo.getEducationcreatedList(CommonClass.benfeciary_ID).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> caselist) {
                System.out.println("SIZE>>>>>> "+caselist.size());

                if(caselist.size() > 0)
                {
                    binding.recycleview.setLayoutManager(new LinearLayoutManager(EducationMainActivity.this, RecyclerView.VERTICAL, false));
                    EducationAdapter healthAdapter = new EducationAdapter(new ArrayList<>(caselist),EducationMainActivity.this);
                    binding.recycleview.setAdapter(healthAdapter);
                    healthAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}