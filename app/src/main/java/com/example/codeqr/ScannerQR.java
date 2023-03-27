package com.example.codeqr;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class ScannerQR extends AppCompatActivity {
    private CodeScanner Scaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        if (ContextCompat.checkSelfPermission(
                getBaseContext(), Manifest.permission.CAMERA
        ) ==
                PackageManager.PERMISSION_GRANTED) {


        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(
                    Manifest.permission.CAMERA

            );
        }

        CodeScannerView scannerView = findViewById(R.id.scan);
        Scaner = new CodeScanner(this, scannerView);
        Scaner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ScannerQR.this, result.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ScannerQR.this,MainActivity.class);
                        intent.putExtra("leido",result.getText());
                        startActivity(intent);
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scaner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Scaner.startPreview();
    }

    @Override
    protected void onPause() {
        Scaner.releaseResources();
        super.onPause();
    }
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });
}