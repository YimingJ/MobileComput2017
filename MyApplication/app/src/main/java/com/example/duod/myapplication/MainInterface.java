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

import java.security.Policy;

public class MainInterface extends AppCompatActivity {
    Button homeButton,exceButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
        homeButton=(Button)findViewById(R.id.button7);
        exceButton=(Button)findViewById(R.id.button8);
        exceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainInterface.this,Exercise.class);
                startActivity(intent);
            }
        });
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
