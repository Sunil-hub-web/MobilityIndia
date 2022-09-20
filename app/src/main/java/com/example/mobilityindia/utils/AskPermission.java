package com.example.mobilityindia.utils;

import static com.karumi.dexter.Dexter.withActivity;

import android.Manifest;
import android.app.Activity;

import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class AskPermission {

    PermissionListener listener;
    Activity activity;

    public AskPermission(PermissionListener listener, Activity activity) {
        this.listener = listener;
        this.activity = activity;
    }


    public void storragePermission()
    {
        withActivity(activity)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {

                            listener.storagePermissionGrant();


                        }

                        // check for permanent denial of any permission
                        else if (report.isAnyPermissionPermanentlyDenied()) {
                            listener.permissionDenied();

                        }


                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(error -> {
                    listener.onError(error.name());
                })
                .onSameThread()
                .check();

    }

    public interface PermissionListener
    {
        void storagePermissionGrant();
        void permissionDenied();
        void onError(String error);
    }
}
