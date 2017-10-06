package com.example.moveup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainInterface extends Activity {
    TextView me,exercise,suggestion,loseweight,stretch,strength,yoga;
    LinearLayout linearLayout;
    ImageView loseWeight,Stretch,Strength,Yoga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
        linearLayout =(LinearLayout)findViewById(R.id.linearLayout);
        me=(TextView)findViewById(R.id.textView2);
        exercise=(TextView)findViewById(R.id.textView3);
        suggestion=(TextView)findViewById(R.id.textView15) ;
        loseweight=(TextView)findViewById(R.id.textView4);
        stretch=(TextView)findViewById(R.id.textView7);
        strength=(TextView)findViewById(R.id.textView5);
        yoga=(TextView)findViewById(R.id.textView6);
        loseWeight=(ImageView)findViewById(R.id.imageView2);
        Stretch=(ImageView)findViewById(R.id.imageView4);
        Strength=(ImageView)findViewById(R.id.imageView);
        Yoga=(ImageView)findViewById(R.id.imageView3);

        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainInterface.this,MainInterface.class);
                startActivity(intent);}
        });
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

        stretch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainInterface.this,Stretch.class);
                startActivity(intent);}
        });
        Stretch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainInterface.this,Stretch.class);
                startActivity(intent);}
        });

        strength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainInterface.this,Strength.class);
                startActivity(intent);}
        });
        Strength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainInterface.this,Strength.class);
                startActivity(intent);}
        });

        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainInterface.this,Yoga.class);
                startActivity(intent);}
        });
        Yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainInterface.this,Yoga.class);
                startActivity(intent);}
        });
}}
