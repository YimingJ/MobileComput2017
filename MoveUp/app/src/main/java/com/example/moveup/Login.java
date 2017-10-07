package com.example.moveup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {
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
        buttonOk.setHeight(100);
        buttonOk.setWidth(350);
        buttonCancel.setHeight(100);
        buttonCancel.setWidth(350);
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
                if(IsCorrect()){
                Intent intent=new Intent(Login.this,MainInterface.class);
                startActivity(intent);}
            }

        });
    }

    public boolean IsCorrect(){
        //// TODO: 2017/10/3 1.database
        return true;
    }
}
