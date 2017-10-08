package com.example.moveup;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import java.util.List;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

//import com.example.zumoappname.*;

public class Suggestion extends Activity {
    TextView textView,yourBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        textView = (TextView) findViewById(R.id.textView13);
        yourBMI = (TextView) findViewById(R.id.yourBMI);
        Intent intent = getIntent();
        final String username = intent.getStringExtra("userName");

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
                                if (!heightS.equals("0") && !weightS.equals("0")) {
                                    Double BMI = Util.calculateBMI(Integer.parseInt(heightS), Integer.parseInt(weightS));
                                    String BMIS = "BMI: "+String.format("%.2f", BMI);
                                    yourBMI.setText(BMIS);
                                }else {
                                    String BMIS = "BMI: Please set your height and weight:)";
                                    yourBMI.setText(BMIS);
                                }
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


        Bundle bundle = intent.getExtras();
        String string = bundle.getString("suggest");
        textView.setText(string);
    }

}
