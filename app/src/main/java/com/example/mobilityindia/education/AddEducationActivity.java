package com.example.mobilityindia.education;

import static com.example.mobilityindia.utils.Utils.getRandomNumber;

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

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mobilityindia.R;
import com.example.mobilityindia.SessinoManager;
import com.example.mobilityindia.beneficarylist.view.BeneficaryDetailActivity;
import com.example.mobilityindia.beneficarylist.view.BeneficaryListActivity;
import com.example.mobilityindia.constant.AppUtils;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityAddEducationBinding;
import com.example.mobilityindia.education.educationresponce.EducationResponse;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
import com.example.mobilityindia.syn1.view.SyncActivityAll;
import com.example.mobilityindia.syn1.view.allresponse.eduction.Example_Eduction;
import com.example.mobilityindia.sync.model.EducationData;
import com.example.mobilityindia.sync.repository.LocalRepo;
import com.example.mobilityindia.utils.ImageFilePath;
import com.example.mobilityindia.utils.Utils;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.constant.ImageProvider;
import com.github.dhaval2404.imagepicker.listener.DismissListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEducationActivity extends AppCompatActivity {
    private static final int PROFILE_IMAGE_REQ_CODE = 101;
    ActivityAddEducationBinding binding;
    String classaccessible = "No", skilldevelopment = "NO", sittingmodification = "", accseetotlm = "", accesstotoilet = "No",
            accesstolibrary = "No", schoolenroll = "", acesstosportactivity = "No", childpartialament = "No", summercamp = "No", vocationcourse = "",
            individualeducattionplan = "", curricularactivities = "No", memberofCEC = "No", activity1 = "", activity2 = "", activity3 = "", activity4 = "", activity5 = "";
    EducationData educationData;
    String FinalString, userId;
    ArrayList<String> imagelist;
    String getvalue = "1";
    LocalRepo localRepo;
    ApiRequest apiRequest;
    SessinoManager sessinoManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_education);
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        imagelist = new ArrayList<>();
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
        localRepo = new LocalRepo(AddEducationActivity.this);

        sessinoManager = new SessinoManager(AddEducationActivity.this);
        userId = sessinoManager.getUSERID();

        binding.schoolenroll.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    schoolenroll = "Yes";
                    binding.schoollayoutid.setVisibility(View.VISIBLE);
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    schoolenroll = "No";
                    binding.schoollayoutid.setVisibility(View.GONE);
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.attendingclassregularly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.attendingclassregularly).showDropDown();
            }
        });
        binding.attendingclassregularly.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genderStr = binding.attendingclassregularly.getAdapter().getItem(position).toString();
            }
        });

        String[] itemNames = getResources().getStringArray(R.array.selectonehome);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.attendingclassregularly.setAdapter(adapter2);

        binding.classaccessible.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    classaccessible = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on

                       /* binding.Sittingmodification.setVisibility(View.VISIBLE);
                        binding.AccesstoTLM.setVisibility(View.VISIBLE);
                        binding.AccesstoToilet.setVisibility(View.VISIBLE);
                        binding.AccesstoLibrary.setVisibility(View.VISIBLE);
                        binding.AccesstoSportActivity.setVisibility(View.VISIBLE);
                        binding.cocurricularactivities.setVisibility(View.VISIBLE);
                        binding.childmemberCEC.setVisibility(View.VISIBLE);*/

                } else {
                    classaccessible = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off

                        /*binding.Sittingmodification.setVisibility(View.GONE);
                        binding.AccesstoTLM.setVisibility(View.GONE);
                        binding.AccesstoToilet.setVisibility(View.GONE);
                        binding.AccesstoLibrary.setVisibility(View.GONE);
                        binding.AccesstoSportActivity.setVisibility(View.GONE);
                        binding.cocurricularactivities.setVisibility(View.GONE);
                        binding.childmemberCEC.setVisibility(View.GONE);*/
                }
            }
        });

        binding.curricularactivities.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    curricularactivities = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    curricularactivities = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.Childrengramsabha.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    skilldevelopment = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    skilldevelopment = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.memberofCEC.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    memberofCEC = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    memberofCEC = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        String[] itemName_sitting = getResources().getStringArray(R.array.selectone);
        ArrayAdapter<String> adapter_sitting = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemName_sitting);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.sittingmodification.setAdapter(adapter_sitting);

        binding.sittingmodification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.sittingmodification).showDropDown();
            }
        });
        binding.sittingmodification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sittingmodification = binding.sittingmodification.getAdapter().getItem(position).toString();
            }
        });

        String[] itemName_AccesstoTLM = getResources().getStringArray(R.array.AccesstoTLM);
        ArrayAdapter<String> adapter_AccesstoTLM = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemName_AccesstoTLM);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.accseetotlm.setAdapter(adapter_AccesstoTLM);

        binding.accseetotlm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (binding.accseetotlm).showDropDown();
            }
        });
        binding.accseetotlm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                accseetotlm = binding.accseetotlm.getAdapter().getItem(position).toString();
            }
        });
        binding.accesstotoilet.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    accesstotoilet = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    accesstotoilet = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.accesstolibrary.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    accesstolibrary = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    accesstolibrary = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.acesstosportactivity.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    acesstosportactivity = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    acesstosportactivity = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.childpartialament.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    childpartialament = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    childpartialament = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.summercamp.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    summercamp = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    summercamp = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        String[] itemNamesbank = getResources().getStringArray(R.array.selectone);
        ArrayAdapter<String> bankaccountadapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNamesbank);
        bankaccountadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.individualeducattionplan.setAdapter(bankaccountadapter);

        binding.individualeducattionplan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                individualeducattionplan = binding.individualeducattionplan.getAdapter().getItem(position).toString();

                if (individualeducattionplan.equals("Yes")) {

                    binding.ihpview.setVisibility(View.VISIBLE);
                    binding.imagesiew.setVisibility(View.VISIBLE);


                } else if(individualeducattionplan.equals("NA")) {

                    binding.ihpview.setVisibility(View.GONE);
                    binding.imagesiew.setVisibility(View.GONE);

                }else{

                    binding.ihpview.setVisibility(View.GONE);
                    binding.imagesiew.setVisibility(View.GONE);
                }
            }
        });

        binding.individualeducattionplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                (binding.individualeducattionplan).showDropDown();
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AppUtils.isNetworkAvailable(AddEducationActivity.this)) {

                    addlivelihoodapi();
                    //localbeneficaryDataCall();

                } else {

                    localbeneficaryDataCall();
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

    }

    private void addlivelihoodapi() {

        ProgressDialog pd = new ProgressDialog(AddEducationActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();

       /* SessinoManager sessinoManager = new SessinoManager(AddEducationActivity.this);
        String userId = sessinoManager.getUSERID();*/
        Map<String, Object> mapData = new HashMap<>();

        mapData.put("school", schoolenroll);
        mapData.put("enrollmentno", binding.enrollmentno.getText().toString());
        mapData.put("attendingclass", binding.attendingclassregularly.getText().toString());
        mapData.put("schoolaccess", classaccessible);
        //  mapData.put("sitting", skilldevelopment);
        mapData.put("sitting", sittingmodification);
        mapData.put("tlm", accseetotlm);

        mapData.put("toilet", accesstotoilet);
        mapData.put("library", accesstolibrary);


        mapData.put("sports", acesstosportactivity);
        mapData.put("cocurricular", curricularactivities);
        mapData.put("schoolother", binding.anyother.getText().toString());
        mapData.put("cec", memberofCEC);

        mapData.put("parliament", childpartialament);
        mapData.put("gramsabha", skilldevelopment);
        mapData.put("summercamp", summercamp);
        mapData.put("activity_one", binding.Activity1.getText().toString());
        mapData.put("activity_two", binding.Activity2.getText().toString());
        mapData.put("activity_three", binding.Activity3.getText().toString());
        mapData.put("activity_four", binding.Activity4.getText().toString());
        mapData.put("activity_five", binding.Activity5.getText().toString());
        mapData.put("iep", individualeducattionplan);

        mapData.put("benificiary_id", CommonClass.benfeciary_ID);
        mapData.put("user_id", userId);
        //mapData.put("iepdoc", "");
        // baad me open ker dena
        mapData.put("iepdoc", imagelist);

        Log.d("gftchg",mapData.toString());

        CommonClass.APP_TOKEN = CommonClass.getToken(AddEducationActivity.this);
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.addEducationData(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<EducationResponse>() {
            @Override
            public void onResponse(Call<EducationResponse> call, Response<EducationResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    if (response.body().isStatus()) {
                        Toast.makeText(AddEducationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        getEductionDetails();
                        pd.dismiss();

                    } else {
                        Toast.makeText(AddEducationActivity.this, "Education not Added ", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }


                }
            }

            @Override
            public void onFailure(Call<EducationResponse> call, Throwable t) {
                pd.dismiss();
            }
        });


    }

    private void capturePhoto() {
        ImagePicker.with(this)
                .saveDir(AddEducationActivity.this.getExternalFilesDir(Environment.DIRECTORY_DCIM))
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
                    ContentResolver contentResolver = AddEducationActivity.this.getContentResolver();
                    MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                    extension = mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(data.getData()));
                    // String extension = getfileExtension(TimeSlotSelectActivity.this, data.getData());

                    String encodedBase64 = null;
                    try {

                        binding.imagesiew.setVisibility(View.VISIBLE);

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
                            getvalue = "6";
                        } else if (getvalue.equals("6")) {
                            Toast.makeText(this, "You are not allowing to enter more than five", Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(this, "You are not allowing to enter more than five", Toast.LENGTH_SHORT).show();
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
                    ContentResolver contentResolver = AddEducationActivity.this.getContentResolver();
                    MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
                    extension = mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(data.getData()));
                    // String extension = getfileExtension(TimeSlotSelectActivity.this, data.getData());

                    String encodedBase64 = null;
                    try {

                        binding.imagesiew.setVisibility(View.VISIBLE);

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
                            getvalue = "6";
                        } else if (getvalue.equals("6")) {
                            Toast.makeText(this, "You upload Only Five Document", Toast.LENGTH_SHORT).show();
                        } else {

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

                        binding.imagesiew.setVisibility(View.VISIBLE);

                        Uri path = data.getData();
                        String realPath = ImageFilePath.getPath(AddEducationActivity.this, data.getData());
                        //  Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(AddEducationActivity.this.getContentResolver(), path);

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
                            getvalue = "4";
                        } else if (getvalue.equals("4")) {
                            binding.frame4.setVisibility(View.VISIBLE);
                            binding.imageview4.setImageBitmap(bitmap);
                            getvalue = "5";
                        } else if (getvalue.equals("5")) {
                            binding.frame5.setVisibility(View.VISIBLE);
                            binding.imageview5.setImageBitmap(bitmap);
                            getvalue = "6";
                        } else if (getvalue.equals("6")) {
                            Toast.makeText(this, "You upload Only Five Document", Toast.LENGTH_SHORT).show();
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

    public void localbeneficaryDataCall() {

        EducationData educationData = new EducationData();

        Date date = new Date();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        String Datetime = formatter.format(c.getTime());

        String randoNoStr = getRandomNumber();
        //educationData.setId(randoNoStr);

        SessinoManager sessinoManager = new SessinoManager(AddEducationActivity.this);
        String userId = sessinoManager.getUSERID();

        educationData.setId(CommonClass.tempid);
        educationData.setBenificiaryId(CommonClass.benfeciary_ID);
        educationData.setUser_id(userId);
        educationData.setCreatedAt(Datetime);
        educationData.setCreatedBy(CommonClass.benfeciary_ID);
        //educationData.setCreatedAt(formatter.format(date));

        educationData.setSchool(schoolenroll);
        educationData.setEnrollmentno(binding.enrollmentno.getText().toString());
        educationData.setAttendingclass(binding.attendingclassregularly.getText().toString());
        educationData.setSchoolaccess(classaccessible);
        //   educationData.s(skilldevelopment);
        educationData.setSitting(sittingmodification);
        educationData.setTlm(accseetotlm);
        educationData.setToilet(accesstotoilet);
        educationData.setLibrary(accesstolibrary);
        educationData.setSports(acesstosportactivity);
        educationData.setCocurricular(curricularactivities);
        educationData.setSchoolother(binding.anyother.getText().toString());
        educationData.setCec(memberofCEC);
        // educationData.set(acesstosportactivity);
        educationData.setParliament(childpartialament);
        educationData.setGramsabha(skilldevelopment);
        educationData.setSummercamp(summercamp);
        educationData.setActivityOne(binding.Activity1.getText().toString());
        educationData.setActivityTwo(binding.Activity2.getText().toString());
        educationData.setActivityThree(binding.Activity3.getText().toString());
        educationData.setActivityFour(binding.Activity4.getText().toString());
        educationData.setActivityFive(binding.Activity5.getText().toString());
        educationData.setIep(individualeducattionplan);
        //educationData.setIepdoc(individualeducattionplan);
        //educationData.setIepdoc(imagelist);

        educationData.setFlag("update");

        localRepo.insertEducationData(educationData);

        Toast.makeText(getApplicationContext(), "Internet is not Available Data save in your Local", Toast.LENGTH_SHORT).show();
        //onBackPressed();
        Intent intent = new Intent(AddEducationActivity.this, BeneficaryDetailActivity.class);
        startActivity(intent);

    }

    private void getEductionDetails() {

        //isprogress.setValue(0);
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("user_id", userId);
        //final MutableLiveData<CaseResponse> data = new MutableLiveData<>();
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.listEducationService(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<Example_Eduction>() {
            @Override
            public void onResponse(Call<Example_Eduction> call, retrofit2.Response<Example_Eduction> response) {

                Log.d("TAG", "Eductionresponse" + response.body().getEducationdata().toString());
                Log.d("TAG", "onResponse response Eduction:" + response.body().toString());


                if (response.body() != null) {

                    EducationData educationData = new EducationData();
                    localRepo.deleteEducation(educationData);
                    localRepo.deleteEducation();

                    for (EducationData data : response.body().getEducationdata()) {

                        localRepo.insertEducationData(data);

                        Log.d("sunilEduction", data.toString());

                        //onBackPressed();

                        Intent intent = new Intent(AddEducationActivity.this, BeneficaryDetailActivity.class);
                        startActivity(intent);

                    }
                }

            }

            @Override
            public void onFailure(Call<Example_Eduction> call, Throwable t) {

                Toast.makeText(AddEducationActivity.this, "" + t, Toast.LENGTH_SHORT).show();

            }
        });

    }

}