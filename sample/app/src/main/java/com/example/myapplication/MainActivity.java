package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tech.vietinfo.vietinfomodule.VietinfoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGoModule = findViewById(R.id.btnGoModule);
        btnGoModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VietinfoActivity.class);
//        intent.putExtra("param", param)
//        intent.putExtra("screen", screen)
                startActivity(intent);
            }
        });

    }
}