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
import com.example.mobilityindia.constant.AppUtils;
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityAddEducationBinding;
import com.example.mobilityindia.education.educationresponce.EducationResponse;
import com.example.mobilityindia.retrofit.ApiRequest;
import com.example.mobilityindia.retrofit.RetrofitRequest;
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
    String classaccessible = "", skilldevelopment = "", sittingmodification = "", accseetotlm = "", accesstotoilet = "",
            accesstolibrary = "", schoolenroll = "", acesstosportactivity = "", childpartialament = "", summercamp = "", vocationcourse = "",
            individualeducattionplan = "", curricularactivities = "", memberofCEC = "", activity1 = "", activity2 = "", activity3 = "", activity4 = "", activity5 = "";
    EducationData educationData;
    String FinalString;
    List<String> imagelist;
    LocalRepo localRepo;
    String getvalue = "1";


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

        binding.sittingmodification.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    sittingmodification = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    sittingmodification = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.accseetotlm.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    accseetotlm = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    accseetotlm = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
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

        binding.Activity1.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    activity1 = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    activity1 = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });
        binding.Activity2.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    activity2 = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    activity2 = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });
        binding.Activity3.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    activity3 = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    activity3 = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });
        binding.Activity4.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    activity4 = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    activity4 = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });
        binding.Activity5.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    activity5 = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    activity5 = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

       /* binding.vocationcourse.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    vocationcourse = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on

                    binding.vocationCourses.setVisibility(View.VISIBLE);
                } else {
                    vocationcourse = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off

                    binding.vocationCourses.setVisibility(View.GONE);
                }
            }
        });
*/
        binding.individualeducattionplan.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if (isOn) {
                    individualeducattionplan = "Yes";
                    binding.ihpview.setVisibility(View.VISIBLE);
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                } else {
                    individualeducattionplan = "No";
                    binding.ihpview.setVisibility(View.GONE);
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AppUtils.isNetworkAvailable(AddEducationActivity.this)) {
                    addlivelihoodApi();

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

    private void addlivelihoodApi() {
        ProgressDialog pd = new ProgressDialog(AddEducationActivity.this);
        pd.setMessage("Loading...");
        pd.setCancelable(false);
        pd.show();
        Map<String, Object> mapData = new HashMap<>();

        mapData.put("school", schoolenroll);

        mapData.put("benificiary_id", CommonClass.benfeciary_ID);
        mapData.put("user_id", CommonClass.USERiD);

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
        mapData.put("activity_one", activity1);
        mapData.put("activity_two", activity2);
        mapData.put("activity_three", activity3);
        mapData.put("activity_four", activity4);
        mapData.put("activity_five", activity5);

        mapData.put("iep", individualeducattionplan);
        mapData.put("iepdoc", "");
        // baad me open ker dena
        mapData.put("iepdoc", imagelist);

        CommonClass.APP_TOKEN = CommonClass.getToken(AddEducationActivity.this);
        ApiRequest apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
        apiRequest.addEducationData(CommonClass.APP_TOKEN, mapData).enqueue(new Callback<EducationResponse>() {
            @Override
            public void onResponse(Call<EducationResponse> call, Response<EducationResponse> response) {
                Log.d("TAG", "onResponse response:: " + response.body());
                if (response.body() != null) {
                    if (response.body().isStatus()) {
                        Toast.makeText(AddEducationActivity.this, "Education  Added ", Toast.LENGTH_SHORT).show();
                        onBackPressed();
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

    public void localbeneficaryDataCall() {
        EducationData educationData = new EducationData();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        Date date = new Date();

        String randoNoStr = getRandomNumber();
        educationData.setId(randoNoStr);

        educationData.setBenificiary_id(CommonClass.benfeciary_ID);
        educationData.setCreatedAt(formatter.format(date));

        educationData.setSchool(schoolenroll);
        educationData.setEnrollmentno(binding.enrollmentno.getText().toString());
        educationData.setAttendingclass(binding.attendingclassregularly.getText().toString());
        educationData.setCec(classaccessible);
        //   educationData.s(skilldevelopment);
        educationData.setSitting(sittingmodification);
        educationData.setTlm(accseetotlm);
        educationData.setToilet(accesstotoilet);
        educationData.setLibrary(accesstolibrary);
        educationData.setSports(acesstosportactivity);
        // educationData.set(acesstosportactivity);
        educationData.setParliament(childpartialament);
        educationData.setSummercamp(summercamp);
        educationData.setVocational(vocationcourse);
        educationData.setVocationaldetail(binding.mentiondetailvocationcourse.getText().toString());
        educationData.setIep(individualeducattionplan);

        localRepo.insertEducationData(educationData);
        Toast.makeText(getApplicationContext(), "Internet is not Available Data save in your Local", Toast.LENGTH_SHORT).show();
        onBackPressed();

    }

}