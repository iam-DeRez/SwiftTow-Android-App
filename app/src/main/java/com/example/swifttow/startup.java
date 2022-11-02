package com.example.swifttow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class startup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }

    public void signup(View view) {
        Intent move = new Intent(this,signup.class);
        startActivity(move);

    }

    public void login(View view) {
        Intent move = new Intent(this,login.class);
        startActivity(move);
    }
}