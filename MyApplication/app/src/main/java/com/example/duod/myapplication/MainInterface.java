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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.security.Policy;

public class MainInterface extends AppCompatActivity {
    TextView me,exercise;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
       linearLayout =(LinearLayout)findViewById(R.id.linearLayout);
        me=(TextView)findViewById(R.id.textView3);
        exercise=(TextView)findViewById(R.id.textView2);
//        welcome=(TextView)findViewById(R.id.textView4);
//        Intent intent=new Intent();
//        String username = getIntent().getStringExtra("time");
//        String username=intent.getStringExtra("time");
//        welcome.setText("Welcome Back"+username);

        //homeButton.setCompoundDrawables(null,null,findImage(MainInterface.this,R.drawable.homeButton, Parameters.IMG_SMALL),null,null);
    }
   /* public static Drawable findImage(Context context,int id, int side){
        Drawable drawable= ContextCompat.getDrawable(context, id);
        int px= ViewDragHelperHelper.dip(context,side);
        drawable.setBounds(0,0,px,px);
        return drawable;

    }
    public static int dip(Context context,float dip){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dip*scale+0.5f);
    }*/
}
