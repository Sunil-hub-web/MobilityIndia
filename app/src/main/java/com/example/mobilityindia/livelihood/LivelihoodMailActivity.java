package com.example.mobilityindia.livelihood;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mobilityindia.R;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityLivelihoodMailBinding;
import com.example.mobilityindia.livelihood.adapterlivelihood.AdapterLivelihood;
import com.example.mobilityindia.sync.repository.LocalRepo;

import java.util.ArrayList;
import java.util.List;

public class LivelihoodMailActivity extends AppCompatActivity {
    ActivityLivelihoodMailBinding binding;
    LocalRepo localRepo;
    String benfeciaryID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livelihood_mail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_livelihood_mail);
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
        localRepo = new LocalRepo(LivelihoodMailActivity.this);

        benfeciaryID = String.valueOf(CommonClass.benfeciary_ID);

        if(benfeciaryID.equals("") || benfeciaryID.equals("null")){

            getdatehealthdate();

        }else{

            getdatehealthdate1();
        }

        binding.swiperefresLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if(benfeciaryID.equals("") || benfeciaryID.equals("null")){

                    getdatehealthdate();

                }else{

                    getdatehealthdate1();
                }

                binding.swiperefresLayout.setRefreshing(false);

            }
        });

        binding.btnaddbanificary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LivelihoodMailActivity.this, AddLivelihoodActivity.class));
            }
        });
    }

    private void getdatehealthdate()
    {

        localRepo.getSelectedlivehoodDataID(CommonClass.tempid).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> caselist) {
                System.out.println("SIZE>>>>>> "+caselist.size());

                if(caselist.size() > 0)
                {
                    binding.recycleview.setLayoutManager(new LinearLayoutManager(LivelihoodMailActivity.this, RecyclerView.VERTICAL, false));
                    AdapterLivelihood healthAdapter = new AdapterLivelihood(new ArrayList<>(caselist),LivelihoodMailActivity.this);
                    binding.recycleview.setAdapter(healthAdapter);
                    healthAdapter.notifyDataSetChanged();

                }else {

//                    binding.helplinecases.setVisibility(View.GONE);
//                    binding.textvisible.setVisibility(View.VISIBLE);

                    //Toast.makeText(LivelihoodMailActivity.this, "livehood Data is not there", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void getdatehealthdate1()
    {

        localRepo.getSelectedlivehoodData1(CommonClass.benfeciary_ID).observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> caselist) {
                System.out.println("SIZE>>>>>> "+caselist.size());

                if(caselist.size() > 0)
                {
                    binding.recycleview.setLayoutManager(new LinearLayoutManager(LivelihoodMailActivity.this, RecyclerView.VERTICAL, false));
                    AdapterLivelihood healthAdapter = new AdapterLivelihood(new ArrayList<>(caselist),LivelihoodMailActivity.this);
                    binding.recycleview.setAdapter(healthAdapter);
                    healthAdapter.notifyDataSetChanged();

                }else {

//                    binding.helplinecases.setVisibility(View.GONE);
//                    binding.textvisible.setVisibility(View.VISIBLE);

                   // Toast.makeText(LivelihoodMailActivity.this, "livehood Data is not there", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    protected void onResume() {
        getdatehealthdate();
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(benfeciaryID.equals("") || benfeciaryID.equals("null")){

            getdatehealthdate();

        }else{

            getdatehealthdate1();
        }
    }
}