package com.example.duod.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        buttonCancel=(Button)findViewById(R.id.button5);
        buttonOk=(Button)findViewById(R.id.button6);
        lEmail=(EditText)findViewById(R.id.editText3);
        lPwd=(EditText)findViewById(R.id.editText6);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,MainActivity.class);
                startActivity(intent);
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,MainInterface.class);
                startActivity(intent);
            }
        });
    }
}
