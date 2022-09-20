package com.example.mobilityindia.health.healthview;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.constant.AppUtils;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityAddHealth2Binding;
import com.example.mobilityindia.health.healthresponce.HealthResponse;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.sync.model.HealthCareData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.utils.ImageFilePath;
import com.example.mobilityindia.utils.Utils;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.constant.ImageProvider;
import com.github.dhaval2404.imagepicker.listener.DismissListener;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddHealthActivity2 extends AppCompatActivity {

    ActivityAddHealth2Binding binding;
    List<String> eardischargeDD;
    List<String> earRightDD;
    List<String> earLeftDD;
    String fallowsheetupdated = "",ihp = "",userId = "",benificiary_id = "",individualeducattionplan = "",FinalString = "",
    service_done = "",screeningdate = "",assessmentdate = "",assessmentwho = "",assessmentwhere = "",referral = "",referralplace = "",
            referralprescription = "",trialwhat = "",trialdate = "",socialsecurity = "",socialsecuritywhen = "",gaitfrequency = "",
            gaithowmany = "",therapyfrequency = "",therapysessions = "",fitmentwho = "",fitmentwhere = "",fitmentdevice = "",
            surgery = "",surgerywhere = "",surgerywherewhat = "",homerecommend = "",homerecommendwhat = "";
    SessinoManager sessinoManager;
    String getvalue = "1";
    List<String> imagelist;
    private static final int PROFILE_IMAGE_REQ_CODE = 101;
    LocalRepo localRepo ;
    ArrayList<String> filelist = new ArrayList<>();
    JSONArray jsonArray = new JSONArray();
    ArrayList<String> numbersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Assign variable
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_health2);
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

        sessinoManager = new SessinoManager(this);

        eardischargeDD = new ArrayList<>();
        earRightDD = new ArrayList<>();
        earLeftDD = new ArrayList<>();
        imagelist = new ArrayList<>();

        localRepo = new LocalRepo(AddHealthActivity2.this);

        //jsonArray = (JSONArray) getIntent().getSerializableExtra("service_done");
        numbersList = (ArrayList<String>) getIntent().getSerializableExtra("service_done");
        Log.d("hftrc",numbersList.toString());
        screeningdate = getIntent().getStringExtra("screeningdate");
        assessmentdate = getIntent().getStringExtra("assessmentdate");
        assessmentwho = getIntent().getStringExtra("assessmentwho");
        assessmentwhere = getIntent().getStringExtra("assessmentwhere");
        referral = getIntent().getStringExtra("referral");
        referralplace = getIntent().getStringExtra("referralplace");
        referralprescription = getIntent().getStringExtra("referralprescription");
        trialwhat = getIntent().getStringExtra("trialwhat");
        trialdate = getIntent().getStringExtra("trialdate");
        socialsecurity = getIntent().getStringExtra("socialsecurity");
        socialsecuritywhen = getIntent().getStringExtra("socialsecuritywhen");
        gaitfrequency = getIntent().getStringExtra("gaitfrequency");
        gaithowmany = getIntent().getStringExtra("gaithowmany");
        therapyfrequency = getIntent().getStringExtra("therapyfrequency");
        therapysessions = getIntent().getStringExtra("therapysessions");
        fitmentwho = getIntent().getStringExtra("fitmentwho");
        fitmentwhere = getIntent().getStringExtra("fitmentwhere");
        fitmentdevice = getIntent().getStringExtra("fitmentdevice");
        surgery = getIntent().getStringExtra("surgery");
        surgerywhere = getIntent().getStringExtra("surgerywhere");
        surgerywherewhat = getIntent().getStringExtra("surgerywherewhat");
        homerecommend = getIntent().getStringExtra("homerecommend");
        homerecommendwhat = getIntent().getStringExtra("homerecommendwhat");

        String[] itemNames = getResources().getStringArray(R.array.selectone1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.EardischargeDD.setAdapter(adapter2);

        binding.EarRightDD.setAdapter(adapter2);
        binding.EarLeftDD.setAdapter(adapter2);

        binding.EardischargeDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.EardischargeDD).showDropDown();
            }
        });
        binding.EardischargeDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.EardischargeDD.getAdapter().getItem(position).toString();
            }
        });

        binding.EarRightDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.EarRightDD).showDropDown();
            }
        });
        binding.EarRightDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.EarRightDD.getAdapter().getItem(position).toString();
            }
        });

        binding.EarLeftDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.EarLeftDD).showDropDown();
            }
        });
        binding.EarLeftDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.EarLeftDD.getAdapter().getItem(position).toString();
            }
        });

        binding.fallowsheetupdated.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    fallowsheetupdated = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    fallowsheetupdated = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        String[] itemNames_howOften = getResources().getStringArray(R.array.Howoften);
        ArrayAdapter<String> adapter_HowOften = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_howOften);
        adapter_HowOften.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.fallowwhat.setAdapter(adapter_HowOften);

        binding.fallowwhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.fallowwhat).showDropDown();
            }
        });
        binding.fallowwhat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.fallowwhat.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_developmentDD = getResources().getStringArray(R.array.speechandlanguage);
        ArrayAdapter<String> adapter_developmentDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_developmentDD);
        adapter_developmentDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.developmentDD.setAdapter(adapter_developmentDD);

        binding.developmentDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.developmentDD).showDropDown();
            }
        });
        binding.developmentDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.developmentDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_OPMEDD = getResources().getStringArray(R.array.OPMEDD);
        ArrayAdapter<String> adapter_OPMEDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_OPMEDD);
        adapter_OPMEDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.OPMEDD.setAdapter(adapter_OPMEDD);

        binding.OPMEDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.OPMEDD).showDropDown();
            }
        });
        binding.OPMEDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.OPMEDD.getAdapter().getItem(position).toString();

                if(genderStr.equals("Abnormal")){

                    binding.tlAbnormal.setVisibility(View.VISIBLE);
                }
            }
        });

        String[] itemNames_ArticulationDD = getResources().getStringArray(R.array.articulationDD);
        ArrayAdapter<String> adapter_ArticulationDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_ArticulationDD);
        adapter_ArticulationDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.SpeechArticulationDD.setAdapter(adapter_ArticulationDD);

        binding.SpeechArticulationDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.SpeechArticulationDD).showDropDown();
            }
        });
        binding.SpeechArticulationDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.SpeechArticulationDD.getAdapter().getItem(position).toString();

                if(genderStr.equals("Mis-articulation")){
                    
                    binding.tlMisarticulation.setVisibility(View.VISIBLE);
                }
            }
        });

        String[] itemNames_FluencyDD = getResources().getStringArray(R.array.fluencyDD);
        ArrayAdapter<String> adapter_FluencyDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_FluencyDD);
        adapter_FluencyDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.FluencyDD.setAdapter(adapter_FluencyDD);

        binding.FluencyDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.FluencyDD).showDropDown();
            }
        });
        binding.FluencyDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.FluencyDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_QualityDD = getResources().getStringArray(R.array.qualityDD);
        ArrayAdapter<String> adapter_QualityDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_QualityDD);
        adapter_QualityDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.QualityDD.setAdapter(adapter_QualityDD);

        binding.QualityDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.QualityDD).showDropDown();
            }
        });
        binding.QualityDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.QualityDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_PitchDD = getResources().getStringArray(R.array.pitchDD);
        ArrayAdapter<String> adapter_PitchDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_PitchDD);
        adapter_PitchDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.PitchDD.setAdapter(adapter_PitchDD);

        binding.PitchDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.PitchDD).showDropDown();
            }
        });
        binding.PitchDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.PitchDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_LoudnessDD = getResources().getStringArray(R.array.loudnessDD);
        ArrayAdapter<String> adapter_LoudnessDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_LoudnessDD);
        adapter_LoudnessDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.LoudnessDD.setAdapter(adapter_LoudnessDD);

        binding.LoudnessDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.LoudnessDD).showDropDown();
            }
        });
        binding.LoudnessDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.LoudnessDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_communicationDD = getResources().getStringArray(R.array.modeofcommunicationDD);
        ArrayAdapter<String> adapter_communicationDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_communicationDD);
        adapter_communicationDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ModeofcommunicationDD.setAdapter(adapter_communicationDD);

        binding.ModeofcommunicationDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.ModeofcommunicationDD).showDropDown();
            }
        });
        binding.ModeofcommunicationDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.ModeofcommunicationDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_ComprehensionDD = getResources().getStringArray(R.array.comprehensionDD);
        ArrayAdapter<String> adapter_ComprehensionDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_ComprehensionDD);
        adapter_ComprehensionDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ComprehensionDD.setAdapter(adapter_ComprehensionDD);

        binding.ComprehensionDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.ComprehensionDD).showDropDown();
            }
        });
        binding.ComprehensionDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.ComprehensionDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_ExpressionDD = getResources().getStringArray(R.array.comprehensionDD);
        ArrayAdapter<String> adapter_ExpressionDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_ExpressionDD);
        adapter_ExpressionDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ExpressionDD.setAdapter(adapter_ExpressionDD);

        binding.ExpressionDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.ExpressionDD).showDropDown();
            }
        });
        binding.ExpressionDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.ExpressionDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_RightDD = getResources().getStringArray(R.array.otoscopyRightLeftDD);
        ArrayAdapter<String> adapter_RightDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_RightDD);
        adapter_RightDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.RightDD.setAdapter(adapter_RightDD);

        binding.RightDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.RightDD).showDropDown();
            }
        });
        binding.RightDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.RightDD.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames_LeftDD = getResources().getStringArray(R.array.otoscopyRightLeftDD);
        ArrayAdapter<String> adapter_LeftDD = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames_LeftDD);
        adapter_LeftDD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.LeftDD.setAdapter(adapter_LeftDD);

        binding.LeftDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.LeftDD).showDropDown();
            }
        });
        binding.LeftDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.LeftDD.getAdapter().getItem(position).toString();
            }
        });

        binding.ihp.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    individualeducattionplan = "Yes";
                    binding.ihpview.setVisibility(View.VISIBLE);
                    binding.imagesiew.setVisibility(View.VISIBLE);
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    individualeducattionplan = "No";
                    binding.ihpview.setVisibility(View.GONE);
                    binding.imagesiew.setVisibility(View.GONE);
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPdf();
            }
        });

        binding.document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGalleryIntentfordoc();
            }
        });

        binding.cameraaview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturePhoto();
            }
        });

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imageview1.setImageBitmap(null);
                getvalue = "1";
                binding.frame1.setVisibility(View.GONE);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imageview2.setImageBitmap(null);
                getvalue = "2";
                binding.frame2.setVisibility(View.GONE);
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imageview3.setImageBitmap(null);
                getvalue = "3";
                binding.frame3.setVisibility(View.GONE);
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imageview4.setImageBitmap(null);
                getvalue = "4";
                binding.frame4.setVisibility(View.GONE);
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imageview5.setImageBitmap(null);
                getvalue = "5";
                binding.frame5.setVisibility(View.GONE);
            }
        });
        binding.ihpview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imagesiew.setVisibility(View.VISIBLE);
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  if(AppUtils.isNetworkAvailable(AddHealthActivity2.this)) {
                    addlivelihoodapi();
                }else{
                     localbeneficaryDataCall();
                }
            }
        });

    }

    private void capturePhoto() {
        ImagePicker.with(this)
                .saveDir(AddHealthActivity2.this.getExternalFilesDir(Environment.DIRECTORY_DCIM))
                .cropSquare()
                .setImageProviderInterceptor(new Function1<ImageProvider, Unit>() {
                    @Override
                    public Unit invoke(ImageProvider imageProvider) {
                        Log.d("ImagePicker", "Selected ImageProvider: " + imageProvider.toString());
                        return null;
                    }
                }).setDismissListener(new DismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.d("ImagePicker", "Dialog Dismiss");
                    }
                })
                // Image resolution will be less than 512 x 512
                .maxResultSize(512, 512)
                .start(PROFILE_IMAGE_REQ_CODE);
    }

    public void openPdf() {
        Intent intentpdf = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intentpdf.setType("application/pdf");
        intentpdf.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intentpdf, "Select PDF"), 20);
    }

    public void launchGalleryIntentfordoc() {

//        /////////this is right for doc  ////////////////////////

        String[] mimeTypes =
                {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" // .doc & .docx
                };

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
            if (mimeTypes.length > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            }
        } else {
            String mimeTypesStr = "";
            for (String mimeType : mimeTypes) {
                mimeTypesStr += mimeType + "|";
            }
            intent.setType(mimeTypesStr.substring(0, mimeTypesStr.length() - 1));
        }
        startActivityForResult(Intent.createChooser(intent, "ChooseFile"), 30);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 20:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedFileURI = Uri.parse(data.getData().getPath());
                    Uri selectedFileURI1 = Uri.parse(data.getData().toString());
                    String extension;
                    ContentResolver contentResolver = AddHealthActivity2.this.getContentResolver();
                    MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                    extension = mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(data.getData()));
                    // String extension = getfileExtension(TimeSlotSelectActivity.this, data.getData());

                    String encodedBase64 = null;
                    try {
                        InputStream in = getContentResolver().openInputStream(selectedFileURI1);
                        byte[] bytes = getBytes(in);
                        Log.d("data", "onActivityResult: bytes size=" + bytes.length);
                        Log.d("data", "onActivityResult: Base64string=" + Base64.encodeToString(bytes, Base64.DEFAULT));
                        String ansValue = Base64.encodeToString(bytes, Base64.DEFAULT);
                        String Document = Base64.encodeToString(bytes, Base64.DEFAULT);
                        FinalString = "data:application/" + extension + ";base64," + ansValue;
                        imagelist.add(FinalString);
                        if (getvalue.equals("1")) {
                            binding.imagesiew.setVisibility(View.VISIBLE);
                            binding.frame1.setVisibility(View.VISIBLE);
                            binding.imageview1.setImageDrawable(getResources().getDrawable(R.drawable.pdfimg));
                            getvalue = "2";
                        } else if (getvalue.equals("2")) {
                            binding.frame2.setVisibility(View.VISIBLE);
                            binding.imageview2.setImageDrawable(getResources().getDrawable(R.drawable.pdfimg));
                            getvalue = "3";
                        } else if (getvalue.equals("3")) {
                            binding.frame3.setVisibility(View.VISIBLE);
                            binding.imageview3.setImageDrawable(getResources().getDrawable(R.drawable.pdfimg));
                            getvalue = "4";
                        } else if (getvalue.equals("4")) {
                            binding.frame4.setVisibility(View.VISIBLE);
                            binding.imageview4.setImageDrawable(getResources().getDrawable(R.drawable.pdfimg));
                            getvalue = "5";
                        } else if (getvalue.equals("5")) {
                            binding.frame5.setVisibility(View.VISIBLE);
                            binding.imageview5.setImageDrawable(getResources().getDrawable(R.drawable.pdfimg));
                        }else{

                            Toast.makeText(this, "You have Add Already Five Document", Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        Log.d("error", "onActivityResult: " + e);
                    }
                }
                break;

            case 30:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedFileURI = Uri.parse(data.getData().getPath());
                    Uri selectedFileURI1 = Uri.parse(data.getData().toString());
//                    file = new File(selectedFileURI.getPath().toString());
                    String extension;
                    ContentResolver contentResolver = AddHealthActivity2.this.getContentResolver();
                    MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                    extension = mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(data.getData()));
                    // String extension = getfileExtension(TimeSlotSelectActivity.this, data.getData());

                    String encodedBase64 = null;
                    try {
                        InputStream in = getContentResolver().openInputStream(selectedFileURI1);
                        byte[] bytes = getBytes(in);
                        Log.d("data", "onActivityResult: bytes size=" + bytes.length);
                        Log.d("data", "onActivityResult: Base64string=" + Base64.encodeToString(bytes, Base64.DEFAULT));
                        String ansValue = Base64.encodeToString(bytes, Base64.DEFAULT);
                        String Document = Base64.encodeToString(bytes, Base64.DEFAULT);
                        if (extension.equalsIgnoreCase("doc")) {
                            FinalString = "data:application/msword" + ";base64," + ansValue;
                            imagelist.add(FinalString);
                        }
                        if (getvalue.equals("1")) {
                            binding.imagesiew.setVisibility(View.VISIBLE);
                            binding.frame1.setVisibility(View.VISIBLE);
                            binding.imageview1.setImageDrawable(getResources().getDrawable(R.drawable.documentdetail));
                            getvalue = "2";
                        } else if (getvalue.equals("2")) {
                            binding.frame2.setVisibility(View.VISIBLE);
                            binding.imageview2.setImageDrawable(getResources().getDrawable(R.drawable.documentdetail));
                            getvalue = "3";
                        } else if (getvalue.equals("3")) {
                            binding.frame3.setVisibility(View.VISIBLE);
                            binding.imageview3.setImageDrawable(getResources().getDrawable(R.drawable.documentdetail));
                            getvalue = "4";
                        } else if (getvalue.equals("4")) {
                            binding.frame4.setVisibility(View.VISIBLE);
                            binding.imageview4.setImageDrawable(getResources().getDrawable(R.drawable.documentdetail));
                            getvalue = "5";
                        } else if (getvalue.equals("5")) {
                            binding.frame5.setVisibility(View.VISIBLE);
                            binding.imageview5.setImageDrawable(getResources().getDrawable(R.drawable.documentdetail));
                        }else{

                            Toast.makeText(this, "You have Add Already Five Document", Toast.LENGTH_SHORT).show();
                        }
//                        else if(extension.equalsIgnoreCase("docx")){
//
//                            FinalString = "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document" + ";base64," + ansValue;
//                        }
                        //textView.setText(selectedFileURI1 + "." +extension);


                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                        Log.d("error", "onActivityResult: " + e);
                    }
                    break;


                }
                break;

            case 101:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        Uri path = data.getData();
                        String realPath = ImageFilePath.getPath(AddHealthActivity2.this, data.getData());
                        //  Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(AddHealthActivity2.this.getContentResolver(), path);

                        String imagePath = "data:image/jpeg;base64," + Utils.getEncoded64ImageStringFromBitmap(bitmap);
                        imagelist.add(imagePath);
                        if (getvalue.equals("1")) {
                            binding.frame1.setVisibility(View.VISIBLE);
                            binding.imageview1.setVisibility(View.VISIBLE);
                            binding.imageview1.setImageBitmap(bitmap);
                            getvalue = "2";
                        } else if (getvalue.equals("2")) {
                            binding.frame2.setVisibility(View.VISIBLE);
                            binding.imageview2.setImageBitmap(bitmap);
                            getvalue = "3";
                        } else if (getvalue.equals("3")) {
                            binding.frame3.setVisibility(View.VISIBLE);
                            binding.imageview3.setImageBitmap(bitmap);
                            getvalue = "3";
                        } else if (getvalue.equals("4")) {
                            binding.frame4.setVisibility(View.VISIBLE);
                            binding.imageview4.setImageBitmap(bitmap);
                            getvalue = "3";
                        } else if (getvalue.equals("5")) {
                            binding.frame5.setVisibility(View.VISIBLE);
                            binding.imageview5.setImageBitmap(bitmap);
                        }


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;

        }

    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public void localbeneficaryDataCall()
    {
        HealthCareData healthCareData = new HealthCareData();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date date = new Date();

        String randoNoStr = getRandomNumber();
        healthCareData.setId(randoNoStr);

        healthCareData.setBeneficiaryId(CommonClass.benfeciary_ID);
        String bneid = CommonClass.benfeciary_ID;
        Toast.makeText(AddHealthActivity2.this, bneid, Toast.LENGTH_SHORT).show();
        healthCareData.setCreatedAt(formatter.format(date));

        healthCareData.setServiceDone(numbersList.toString());
        healthCareData.setScreeningdate(screeningdate);
        healthCareData.setBenificiaryId(CommonClass.benfeciary_ID);
        healthCareData.setAssessmentdate(assessmentdate);
        healthCareData.setAssessmentwhere(assessmentwhere);
        healthCareData.setAssessmentwho(assessmentwho);

        healthCareData.setReferral(referral);
        healthCareData.setReferralplace(referralplace);
        healthCareData.setReferralprescription(referralprescription);

        healthCareData.setTrialwhat(trialwhat);
        healthCareData.setTrialdate(trialdate);

        healthCareData.setGaitfrequency(gaitfrequency);
        healthCareData.setGaithowmany(gaithowmany);

        healthCareData.setTherapyfrequency(therapyfrequency);
        healthCareData.setTherapysessions(therapysessions);
        healthCareData.setFitmentwho(fitmentwho);
        healthCareData.setFitmentwhere(fitmentwhere);
        healthCareData.setFitmentdevice(fitmentdevice);
        healthCareData.setFollowupfrequency(binding.fallowwhat.getText().toString());
        healthCareData.setFollowupsheet(fallowsheetupdated);
        healthCareData.setSurgery(surgery);
        healthCareData.setSurgerywhere(surgerywhere);
        healthCareData.setSurgerywherewhat(surgerywherewhat);
        healthCareData.setHomerecommend(homerecommend);
        healthCareData.setHomerecommendwhat(homerecommendwhat);
        healthCareData.setIhp(ihp);
        healthCareData.setCreatedAt(formatter.format(date));
        healthCareData.setCreatedBy(userId);
        healthCareData.setSpeechLangDev(binding.developmentDD.getText().toString().trim());
        healthCareData.setOpmeDd(binding.OPMEDD.getText().toString().trim());
        healthCareData.setIfAbnormal(binding.abnormal.getText().toString().trim());
        healthCareData.setSpeechArticulation(binding.SpeechArticulationDD.getText().toString().trim());
        healthCareData.setIfMisarticulation(binding.misarticulation.getText().toString().trim());
        healthCareData.setFluency(binding.FluencyDD.getText().toString().trim());
        healthCareData.setVoiceQuality(binding.QualityDD.getText().toString().trim());
        healthCareData.setVoicePitch(binding.PitchDD.getText().toString().trim());
        healthCareData.setVoiceLoudness(binding.LoudnessDD.getText().toString().trim());
        healthCareData.setModeCommunication(binding.ModeofcommunicationDD.getText().toString().trim());
        healthCareData.setLanguageComprehension(binding.ComprehensionDD.getText().toString().trim());
        healthCareData.setLanguageExpression(binding.ExpressionDD.getText().toString().trim());
        healthCareData.setTestResult(binding.TestResults.getText().toString().trim());
        healthCareData.setRightOtoscopy(binding.RightDD.getText().toString().trim());
        healthCareData.setLeftOtoscopy(binding.LeftDD.getText().toString().trim());
        healthCareData.setPureToneAudioRight(binding.audiometryRightDD.getText().toString().trim());
        healthCareData.setPureToneAudioLeft(binding.audiometryLeftDD.getText().toString().trim());
        healthCareData.setSpeechAudioTest(binding.SpeechAudtest.getText().toString().trim());
        healthCareData.setEarDischarge(binding.EardischargeDD.getText().toString().trim());
        healthCareData.setEarDischarge(binding.EardischargeDD.getText().toString().trim());
        healthCareData.setLingSound(binding.Ling6Sounds.getText().toString().trim());
        healthCareData.setHearingModelLeft(binding.modelLeftDD.getText().toString().trim());
        healthCareData.setHearingModelRight(binding.modelRightDD.getText().toString().trim());
        healthCareData.setHearingModelRight(binding.modelRightDD.getText().toString().trim());
        healthCareData.setEarMouldRight(binding.EarRightDD.getText().toString().trim());
        healthCareData.setEarMouldLeft(binding.EarLeftDD.getText().toString().trim());
        healthCareData.setSpecialistsRemark(binding.specialistremarks.getText().toString().trim());

        localRepo.insertHealthCareData(healthCareData);

        Toast.makeText(getApplicationContext(), "Internet is not Available Data save in your Local", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    public static String  getRandomNumber() {
        int max  = 999;
        int min  = 100;
        String result = "";
        Random random =  new Random();
        result = String.valueOf(random.nextInt((max - min) + 1)+min);

        return result;
    }

    private void addlivelihoodapi()
    {
        ProgressDialog pd = new ProgressDialog(AddHealthActivity2.this);
        pd.setMessage("Loading...");
        //pd.setCancelable(false);
        pd.show();
        Map<String, Object> mapData = new HashMap<>();

        userId = sessinoManager.getUSERID();
        benificiary_id = CommonClass.benfeciary_ID;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date date = new Date();

        mapData.put("user_id",userId);
        mapData.put("benificiary_id",benificiary_id);
        mapData.put("service_done",numbersList);
        mapData.put("screeningdate",screeningdate);
        mapData.put("assessmentdate",assessmentdate);
        mapData.put("assessmentwho",assessmentwho);
        mapData.put("assessmentwhere",assessmentwhere);
        mapData.put("referral",referral);
        mapData.put("referralplace",referralplace);
        mapData.put("referralprescription",referralprescription);
        mapData.put("trialwhat",trialwhat);
        mapData.put("trialdate",trialdate);
        mapData.put("gaitfrequency",gaitfrequency);
        mapData.put("gaithowmany",gaithowmany);
        mapData.put("therapyfrequency",therapyfrequency);
        mapData.put("therapysessions",therapysessions);
        mapData.put("fitmentwho",fitmentwho);
        mapData.put("fitmentwhere",fitmentwhere);
        mapData.put("fitmentdevice",fitmentdevice);
        mapData.put("followupfrequency",binding.fallowwhat.getText().toString());
        mapData.put("followupsheet",fallowsheetupdated);
        mapData.put("surgery",surgery);
        mapData.put("surgerywhere",surgerywhere);
        mapData.put("surgerywherewhat",surgerywherewhat);
        mapData.put("homerecommend",homerecommend);
        mapData.put("homerecommendwhat",homerecommendwhat);
        mapData.put("ihp",ihp);
        mapData.put("ihp_doc",imagelist);
        mapData.put("speech_lang_dev",binding.developmentDD.getText().toString().trim());
        mapData.put("opme_dd",binding.OPMEDD.getText().toString().trim());
        mapData.put("if_abnormal",binding.abnormal.getText().toString().trim());
        mapData.put("speech_articulation",binding.SpeechArticulationDD.getText().toString().trim());
        mapData.put("if_misarticulation",binding.misarticulation.getText().toString().trim());
        mapData.put("fluency",binding.FluencyDD.getText().toString().trim());
        mapData.put("voice_quality",binding.QualityDD.getText().toString().trim());
        mapData.put("voice_pitch",binding.PitchDD.getText().toString().trim());
        mapData.put("voice_loudness",binding.LoudnessDD.getText().toString().trim());
        mapData.put("mode_communication",binding.ModeofcommunicationDD.getText().toString().trim());
        mapData.put("language_comprehension",binding.ComprehensionDD.getText().toString().trim());
        mapData.put("language_expression",binding.ExpressionDD.getText().toString().trim());
        mapData.put("test_result",binding.TestResults.getText().toString().trim());
        mapData.put("right_otoscopy",binding.RightDD.getText().toString().trim());
        mapData.put("left_otoscopy",binding.LeftDD.getText().toString().trim());
        mapData.put("pure_tone_audio_right",binding.audiometryRightDD.getText().toString());
        mapData.put("pure_tone_audio_left",binding.audiometryLeftDD.getText().toString().trim());
        mapData.put("speech_audio_test",binding.SpeechAudtest.getText().toString().trim());
        mapData.put("ear_discharge",binding.EardischargeDD.getText().toString().trim());
        mapData.put("ear_discharge",binding.EardischargeDD.getText().toString().trim());
        mapData.put("ling_sound",binding.Ling6Sounds.getText().toString().trim());
        mapData.put("hearing_model_right",binding.modelRightDD.getText().toString());
        mapData.put("hearing_model_left",binding.modelLeftDD.getText().toString().trim());
        mapData.put("ear_mould_right",binding.EarRightDD.getText().toString().trim());
        mapData.put("ear_mould_left",binding.EarLeftDD.getText().toString().trim());
        mapData.put("specialists_remark",binding.specialistremarks.getText().toString());

        Log.d("hsbuygfjwb",mapData.toString());

        CommonClass.APP_TOKEN = CommonClass.getToken(AddHealthActivity2.this);
        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.addHealthData(CommonClass.APP_TOKEN,mapData).enqueue(new Callback<HealthResponse>() {
            @Override
            public void onResponse(Call<HealthResponse> call, Response<HealthResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    if(response.body().isStatus()){
                        Toast.makeText(AddHealthActivity2.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        onBackPressed();

                    }else{
                        Toast.makeText(AddHealthActivity2.this,"Health not Added ",Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }
                }
            }
            @Override
            public void onFailure(Call<HealthResponse> call, Throwable t) {

                Toast.makeText(AddHealthActivity2.this, ""+t, Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }


}