package com.example.mobilityindia.education;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.app.Activity;
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
import com.example.mobilityindia.constant.CommonClass;
import com.example.mobilityindia.databinding.ActivityEditEducationBinding;
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
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class EditEducationActivity extends AppCompatActivity {
    private static final int PROFILE_IMAGE_REQ_CODE = 101;
    ActivityEditEducationBinding binding;
    LocalRepo localRepo;
    String classaccessible = "" ,skilldevelopment = "",sittingmodification = "",accseetotlm ="",accesstotoilet ="",
            accesstolibrary ="",schoolenroll = "",acesstosportactivity ="",childpartialament ="",summercamp ="",vocationcourse ="",individualeducattionplan ="",
            memberofCEC = "", activity1 = "", activity2 = "", activity3 = "", activity4 = "", activity5 = "",curricularactivities,
            createdat = "",benificiaryId = "",userId = "",id = "";
    EducationData educationData;
    String FinalString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_education);
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
        localRepo = new LocalRepo(EditEducationActivity.this);
        educationData = new EducationData();
        callofflinedata();
        String date = CommonClass.datestring;
        Log.d("hsvxva",date);


        binding.schoolenroll.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    schoolenroll = "Yes";
                    binding.schoollayoutid.setVisibility(View.VISIBLE);
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    schoolenroll = "No";
                    binding.schoollayoutid.setVisibility(View.GONE);
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.classaccessible.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    classaccessible = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    classaccessible = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.Childrengramsabha.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    skilldevelopment = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    skilldevelopment = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });

        binding.sittingmodification.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    sittingmodification = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
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
                if(isOn){
                    accseetotlm = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
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
                if(isOn){
                    accesstotoilet = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
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
                if(isOn){
                    accesstolibrary = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
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
                if(isOn){
                    acesstosportactivity = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
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
                if(isOn){
                    childpartialament = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
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
                if(isOn){
                    summercamp = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
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

       /* binding.vocationcourse.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    vocationcourse = "Yes";
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    vocationcourse = "No";
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
                }
            }
        });
*/
        binding.individualeducattionplan.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here
                if(isOn){
                    individualeducattionplan = "Yes";
                  //  binding.ihpview.setVisibility(View.VISIBLE);
                    // CommonClass.weathershgornot =weathershg;
                    //switch  is on
                }else{
                    individualeducattionplan = "No";
              //      binding.ihpview.setVisibility(View.GONE);
                    //  CommonClass.weathershgornot =weathershg;
                    //switch is off
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

        binding.buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                educationData.setActivityOne(activity1);
                educationData.setActivityTwo(activity2);
                educationData.setActivityThree(activity3);
                educationData.setActivityFour(activity4);
                educationData.setActivityFive(activity5);
                educationData.setIep(individualeducattionplan);
                //callupdatelocaledata();
                educationData.setCreatedAt(createdat);
                educationData.setBenificiaryId(benificiaryId);
                educationData.setUser_id(userId);
                educationData.setId(id);

                localRepo.updateEducationData(educationData);


                Toast.makeText(EditEducationActivity.this, "Data Update Local", Toast.LENGTH_SHORT).show();
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
    }

    public void callupdatelocaledata( )
    {
        localRepo.updateEducationData(educationData);
        onBackPressed();
    }

    private void callofflinedata()
    {
        localRepo.getducationcreatedate(CommonClass.datestring).observe(this, new Observer<List<EducationData>>() {
            @Override
            public void onChanged(@Nullable List<EducationData> singleMember) {
                if(singleMember.size() != 0)
                {

                    educationData = singleMember.get(0);

                    schoolenroll = singleMember.get(0).getSchool();
                    if(schoolenroll.equalsIgnoreCase("yes")||schoolenroll.equalsIgnoreCase("true")){
                        binding.schoolenroll.setOn(true);
                        binding.schoollayoutid.setVisibility(View.VISIBLE);
                    }else{
                        binding.schoolenroll.setOn(false);
                        binding.schoollayoutid.setVisibility(View.GONE);
                    }

                    binding.enrollmentno.setText(singleMember.get(0).getEnrollmentno());
                    String healthId = singleMember.get(0).getId();
                    CommonClass.healthId = healthId;

                    binding.attendingclassregularly.setText(singleMember.get(0).getAttendingclass());
                   /* String[] itemNames = getResources().getStringArray(R.array.selectonehome);
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemNames);
                    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.attendingclassregularly.setAdapter(adapter2);*/




                    classaccessible = singleMember.get(0).getCec();
                   // binding.classaccessible.setOn(classaccessible.equalsIgnoreCase("yes") || classaccessible.equalsIgnoreCase("true"));

                    ///////swagitikaask
//                    skilldevelopment = singleMember.get(0).getCec();
//                    if(skilldevelopment.equalsIgnoreCase("yes")||skilldevelopment.equalsIgnoreCase("true")){
//                        binding.skilldevelopment.setOn(true);
//                    }else{
//                        binding.skilldevelopment.setOn(false);
//                    }

                    sittingmodification = singleMember.get(0).getSitting();
                    //binding.sittingmodification.setOn(sittingmodification.equalsIgnoreCase("yes") || sittingmodification.equalsIgnoreCase("true"));

                    accseetotlm = singleMember.get(0).getTlm();
                    //binding.accseetotlm.setOn(accseetotlm.equalsIgnoreCase("yes") || accseetotlm.equalsIgnoreCase("true"));

                    accesstotoilet = singleMember.get(0).getToilet();
                    //binding.accesstotoilet.setOn(accesstotoilet.equalsIgnoreCase("yes") || accesstotoilet.equalsIgnoreCase("true"));

                    accesstolibrary = singleMember.get(0).getLibrary();
                    //binding.accesstolibrary.setOn(accesstolibrary.equalsIgnoreCase("yes") || accesstolibrary.equalsIgnoreCase("true"));

                    acesstosportactivity = singleMember.get(0).getSports();
                    //binding.acesstosportactivity.setOn(acesstosportactivity.equalsIgnoreCase("yes") || acesstosportactivity.equalsIgnoreCase("true"));

                    // binding.anyother.setText(singleMember.get(0).get());


                    childpartialament = singleMember.get(0).getParliament();
                    //binding.childpartialament.setOn(childpartialament.equalsIgnoreCase("yes") || childpartialament.equalsIgnoreCase("true"));

                    summercamp = singleMember.get(0).getSummercamp();
                    //binding.summercamp.setOn(summercamp.equalsIgnoreCase("yes") || summercamp.equalsIgnoreCase("true"));

                   // vocationcourse = singleMember.get(0).getVocational();
                  /*  if(vocationcourse.equalsIgnoreCase("yes")||vocationcourse.equalsIgnoreCase("true")){
                        binding.vocationcourse.setOn(true);
                    }else{
                        binding.vocationcourse.setOn(false);
                    }*/

                    individualeducattionplan = singleMember.get(0).getIep();
                    //binding.individualeducattionplan.setOn(individualeducattionplan.equalsIgnoreCase("yes") || individualeducattionplan.equalsIgnoreCase("true"));

                   // binding.mentiondetailvocationcourse.setText(singleMember.get(0).getVocationaldetail());

                    createdat = singleMember.get(0).getCreatedAt();
                    benificiaryId = singleMember.get(0).getBenificiaryId();
                    userId = singleMember.get(0).getUser_id();
                    id = singleMember.get(0).getUser_id();

                }
            }
        });
    }

    private void capturePhoto(){
        ImagePicker.with(this)
                .saveDir(EditEducationActivity.this.getExternalFilesDir(Environment.DIRECTORY_DCIM))
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

    public void openPdf(){
        Intent intentpdf = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intentpdf.setType("application/pdf");
        intentpdf.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intentpdf, "Select PDF"), 20);
    }

    public void launchGalleryIntentfordoc() {

//        /////////this is right for doc  ////////////////////////

        String[] mimeTypes =
                {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document" // .doc & .docx
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
            intent.setType(mimeTypesStr.substring(0,mimeTypesStr.length() - 1));
        }
        startActivityForResult(Intent.createChooser(intent,"ChooseFile"), 30);


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
                    ContentResolver contentResolver = EditEducationActivity.this.getContentResolver();
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
                    ContentResolver contentResolver = EditEducationActivity.this.getContentResolver();
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
                        if(extension.equalsIgnoreCase("doc"))
                        {
                            FinalString = "data:application/msword" + ";base64," + ansValue;
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
                        String realPath = ImageFilePath.getPath(EditEducationActivity.this, data.getData());
                        //  Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(EditEducationActivity.this.getContentResolver(), path);

                        String imagePath = "data:image/jpeg;base64," + Utils.getEncoded64ImageStringFromBitmap(bitmap);

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
}