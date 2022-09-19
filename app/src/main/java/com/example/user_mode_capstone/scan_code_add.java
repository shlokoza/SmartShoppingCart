package com.example.user_mode_capstone;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class scan_code_add extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView ScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView  = new ZXingScannerView(this);
        setContentView(ScannerView);
    }





    @Override
    protected void onPause() {
        super.onPause();
        ScannerView.stopCamera();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String []{Manifest.permission.CAMERA},
                        1);
                return;
            }
        }
        ScannerView.setResultHandler(this);
        ScannerView.startCamera(0);

    }

    @Override
    public void handleResult(Result result) {
        AddItem.txbarcode.setText(result.getText());


        onBackPressed();
    }
}