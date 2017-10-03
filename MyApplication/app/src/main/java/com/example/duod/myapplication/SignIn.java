package com.example.duod.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class SignIn extends AppCompatActivity {
    Button button3,button4;
    EditText editText,editText2,editText4,editText5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        Log.d("MyApp","I am activity 2");
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        editText4=(EditText)findViewById(R.id.editText4);
        editText5=(EditText)findViewById(R.id.editText5);
    }
}
