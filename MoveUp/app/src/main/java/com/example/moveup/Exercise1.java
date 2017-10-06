package com.example.moveup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Exercise1 extends Activity {
    Button button;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1);
        button=(Button)findViewById(R.id.button9);
        imageButton=(ImageButton)findViewById(R.id.imageButton2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://youtu.be/m7kpIBGEdkI");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }

}
