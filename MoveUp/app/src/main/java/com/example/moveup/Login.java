package com.example.moveup;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

public class Login extends Activity {
    Button buttonCancel, buttonOk;
    EditText lEmail, lPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonCancel = (Button) findViewById(R.id.button5);
        buttonOk = (Button) findViewById(R.id.button6);
        lEmail = (EditText) findViewById(R.id.editText3);
        lPwd = (EditText) findViewById(R.id.editText6);
        buttonOk.setHeight(100);
        buttonOk.setWidth(350);
        buttonCancel.setHeight(100);
        buttonCancel.setWidth(350);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userEmail = lEmail.getText().toString().trim();
                final String userPsw = lPwd.getText().toString().trim();
                IsCorrect(userEmail, userPsw);
            }

        });
    }

    public void IsCorrect(final String userEmail, final String userPsw) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    List<User> result = MoveUpConstant.getInstance().getUserTable().where().field("email").eq(val(userEmail)).execute().get();
                    if (result.size() == 1 && result.get(0).getuPwd().equals(userPsw)) {
                        Intent intent = new Intent(Login.this, MainInterface.class);
                        intent.putExtra("userName",result.get(0).getuName());
                        startActivity(intent);
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Login.this, "Please input your correct email and password", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (Exception exception) {
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.createAndShowDialog(exception, "Error");
                }
                return null;
            }

        }.execute();
    }
}
