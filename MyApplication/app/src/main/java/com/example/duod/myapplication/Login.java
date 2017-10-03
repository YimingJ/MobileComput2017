package com.example.duod.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.sql.BatchUpdateException;

public class Login extends AppCompatActivity {
    Button buttonCancel,buttonOk;
    EditText lEmail,lPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonCancel=(Button)findViewById(R.id.button3);
    }
}
