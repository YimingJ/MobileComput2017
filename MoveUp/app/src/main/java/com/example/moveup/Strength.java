package com.example.moveup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Strength extends Activity {
    TextView title,level1,level2,level3,level4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength);
        title=(TextView)findViewById(R.id.textView8);
        level1=(TextView)findViewById(R.id.textView9);
        level2=(TextView)findViewById(R.id.textView10);
        level3=(TextView)findViewById(R.id.textView11);
        level4=(TextView)findViewById(R.id.textView12);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://youtu.be/mY-_xYd7nXU?list=PL5lPziO_t_ViN5Mu1b17pTIGHfHgXf_Bi");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://youtu.be/hfe6aBM-UvY?list=PL5lPziO_t_ViN5Mu1b17pTIGHfHgXf_Bi");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://youtu.be/ZkWUzn1sPEQ?list=PL5lPziO_t_ViN5Mu1b17pTIGHfHgXf_Bi");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });

        level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://youtu.be/5uVaKjtJHN8?list=PL5lPziO_t_ViN5Mu1b17pTIGHfHgXf_Bi");
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });


    }
}
