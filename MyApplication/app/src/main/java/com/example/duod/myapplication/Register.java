package com.example.duod.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    Button buttonCancel,buttonOk;
    EditText rEmail,rPwd,rUsername,rConfirmPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Log.d("MyApp","I am activity 2");
        buttonCancel=(Button)findViewById(R.id.button3);
        buttonOk=(Button)findViewById(R.id.button4);
        rEmail=(EditText)findViewById(R.id.editText);
        rPwd=(EditText)findViewById(R.id.editText2);
        rUsername=(EditText)findViewById(R.id.editText4);
        rConfirmPwd=(EditText)findViewById(R.id.editText5);

        buttonCancel.setOnClickListener(register_listener);
        buttonOk.setOnClickListener(register_listener);
    }

    View.OnClickListener register_listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button4:// ok button
                    register_check();
                    break;
                case R.id.button3:// cancel button
                    buttonCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(Register.this,MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    public void register_check(){
        if(isUsernameValid()){
            if(rPwd.equals(rConfirmPwd)==false){
                Toast.makeText(this,"Confirm Password is not same with Password", Toast.LENGTH_SHORT).show();
            }
            else{
                //todo database
                Intent intent_register_login=new Intent(Register.this,Login.class);
                startActivity(intent_register_login);
            }
        }
    }
    public boolean isUsernameValid(){
        if(rUsername.getText().toString().trim().equals("")){
            Toast.makeText(this,"Username can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(rPwd.getText().toString().trim().equals("")){
            Toast.makeText(this,"Password can't be empty",Toast.LENGTH_SHORT).show();//// TODO: 2017/10/3
            return false;
        }
        else if(rConfirmPwd.getText().toString().trim().equals("")){
            Toast.makeText(this,"ConfirmPassword can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}
