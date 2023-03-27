package com.example.codeqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button escaneig = findViewById(R.id.butt);
        TextView text=findViewById(R.id.leido);
        resultado = getIntent().getStringExtra("leido");
        text.setText(resultado);

        escaneig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ScannerQR.class);
                startActivity(intent);
            }


        });
    }


}