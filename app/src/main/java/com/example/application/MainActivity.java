package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bRegister2, bLogin2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bLogin2 = (Button) findViewById(R.id.bLogin2);
        bRegister2 = (Button) findViewById(R.id.bRegister2);

        bLogin2.setOnClickListener(this);
        bRegister2.setOnClickListener(this);
    }
    //tv linki po których kliknięciu przenosi użytkownika do odpowiednich klas
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bLogin2:

                startActivity(new Intent(this, login.class));
                break;
        }
        switch (v.getId()){
            case R.id.bRegister2:

                startActivity(new Intent(this, register.class));
                break;
        }
    }
}