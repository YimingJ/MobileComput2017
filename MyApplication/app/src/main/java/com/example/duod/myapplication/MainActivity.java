package com.example.duod.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button,button2;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        textView=(TextView)findViewById(R.id.textView);
        button.setHeight(100);
        button.setWidth(350);
        button2.setHeight(100);
        button2.setWidth(350);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(MainActivity.this,Register.class);
            startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });

    }
}
