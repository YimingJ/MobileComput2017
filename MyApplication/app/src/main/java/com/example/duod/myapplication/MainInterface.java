package com.example.duod.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.Policy;

public class MainInterface extends AppCompatActivity {
    TextView me,exercise,loseweight;
    LinearLayout linearLayout;
    ImageView loseWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
        linearLayout =(LinearLayout)findViewById(R.id.linearLayout);
        me=(TextView)findViewById(R.id.textView3);
        exercise=(TextView)findViewById(R.id.textView2);
        loseweight=(TextView)findViewById(R.id.textView4);
        loseWeight=(ImageView)findViewById(R.id.imageView2);

        loseWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(MainInterface.this,LoseWeight.class);
                    startActivity(intent);}
        });
        loseweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainInterface.this,LoseWeight.class);
                startActivity(intent);}
        });
}}
