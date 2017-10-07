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

//import com.example.zumoappname.*;

public class Me extends Activity {
    Button switchBtn, ok, logOut;
    EditText height, weight;
    EditText timeInterval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        final String username = getIntent().getStringExtra("userName");
        switchBtn = (Button) findViewById(R.id.button7);
        ok = (Button) findViewById(R.id.button8);
        logOut = (Button) findViewById(R.id.button9);
        timeInterval = (EditText) findViewById(R.id.timeInterval);
        height = (EditText) findViewById(R.id.editText7);
        weight = (EditText) findViewById(R.id.editText8);
        Intent intent = new Intent(this, TimerService.class);
//        startService(intent);
        changeTimerMode(intent);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    List<User> result = MoveUpConstant.getInstance().getUserTable().where().field("username").eq(val(username)).execute().get();
                    if (result.size() == 1) {
                        final String heightS = result.get(0).getuHeight();
                        final String weightS = result.get(0).getuWeight();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                height.setText(heightS);
                                weight.setText(weightS);
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


        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String heightS =height.getText().toString();
                String weightS = weight.getText().toString();
                int heightInt = Integer.parseInt(heightS);
                int weightInt = Integer.parseInt(weightS);
                if (heightInt == 0 || weightInt ==0){
                    Toast.makeText(Me.this, "Please input your correct height and weight", Toast.LENGTH_SHORT).show();
                }else {
                    String suggestion = handleData(heightInt, weightInt);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("suggest", suggestion);
                    intent.putExtras(bundle);
                    intent.setClass(Me.this, Suggestion.class);
                    startActivity(intent);
                }
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Me.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void changeTimerMode(final Intent intent) {
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchBtn.getText().equals("Turn Off")) {
                    switchBtn.setText("Turn On");
                    intent.putExtra("timerOn", true);
                    stopService(intent);
                } else {
                    int time;
                    switchBtn.setText("Turn Off");
                    String timeS = timeInterval.getText().toString();
                    if (timeS.trim().equals("")) {
                        time = 60 * 60 * 1000;
                    } else {
                        time = Integer.parseInt(timeS) * 60 * 1000;
                    }
                    intent.putExtra("Turn Off", false);
                    intent.putExtra("timeInterval", time);
                    startService(intent);
                }
            }
        });
    }

    public String handleData(int height, int weight) {
        double height1 = (double) (height) / 100;
        double BMI = ((double) weight) / (height1 * height1);
        String suggestion;
        if (BMI < 18.5) {
            suggestion = "Do level 1";
        } else if (BMI >= 18.5 && BMI < 24) {
            suggestion = "Do level 2";
        } else if (BMI >= 24 && BMI < 27) {
            suggestion = "Do level 3";
        } else {
            suggestion = "Do level 4";
        }

        return suggestion;
    }
}
