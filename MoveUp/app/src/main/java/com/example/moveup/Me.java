package com.example.moveup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//import com.example.zumoappname.R;

public class Me extends AppCompatActivity {
    Button turnOff,Ok;
    EditText height,weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        turnOff=(Button)findViewById(R.id.button7);
        Ok=(Button)findViewById(R.id.button8);
        height=(EditText)findViewById(R.id.editText7);
        weight=(EditText)findViewById(R.id.editText8);

        int Height=Integer.parseInt(height.getText().toString());
        int Weight=Integer.parseInt(weight.getText().toString());
        String suggestion=handleData(Height,Weight);
        Ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Me.this,Suggestion.class);
                String suggest="Hi";
                intent.putExtra("suggestion",suggest);
                startActivity(intent);
            }
        });
    }
    public String  handleData(int height,int weight){
        double height1=(double)(height)/100;
        double BMI=((double) (weight*weight))/(height1*height1);
        if(BMI<18.5){}
        else if(BMI>=18.5&&BMI<24){}
        else if(BMI>=24&&BMI<27){}
        else{}
        String suggestion="hi";
       return suggestion;
    }
}
