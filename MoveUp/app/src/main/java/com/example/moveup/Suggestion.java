package com.example.moveup;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

//import com.example.zumoappname.*;

public class Suggestion extends Activity {
    TextView txtSuggestion,yourBMI;
    Double BMI;
    Button btnGoToExercise;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        initSuggestion();
        txtSuggestion = (TextView) findViewById(R.id.txtSuggestion);
        yourBMI = (TextView) findViewById(R.id.yourBMI);
        btnGoToExercise = findViewById(R.id.btnGoToExercise);
        btnGoToExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int catagory = Util.getResult(BMI);
                if (catagory==1){
                    Intent intent = new Intent(Suggestion.this,Stretch.class);
                    intent.putExtra("userName",username);
                    startActivity(intent);
                }else if (catagory==2){
                    Intent intent = new Intent(Suggestion.this,Strength.class);
                    intent.putExtra("userName",username);
                    startActivity(intent);
                }else if (catagory==3){
                    Intent intent = new Intent(Suggestion.this,Yoga.class);
                    intent.putExtra("userName",username);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(Suggestion.this,LoseWeight.class);
                    intent.putExtra("userName",username);
                    startActivity(intent);
                }
            }
        });
        Intent intent = getIntent();
        username = intent.getStringExtra("userName");

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
                                    BMI = Util.calculateBMI(Integer.parseInt(heightS), Integer.parseInt(weightS));
                                    String BMIS = "BMI: "+String.format("%.2f", BMI);
                                    yourBMI.setText(BMIS);
                                    btnGoToExercise.setEnabled(true);
                                    int catagory = Util.getResult(BMI);
                                    String suggest = suggestions.get(catagory);
                                    txtSuggestion.setText(suggest);
                                }else {
                                    String BMIS = "BMI: Please set your height and weight:)";
                                    yourBMI.setText(BMIS);
                                    txtSuggestion.setText("No suggestion");
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

    }

    private HashMap<Integer,String> suggestions;
    private void initSuggestion() {
        suggestions = new HashMap<>();
        suggestions.put(1,Util.category1);
        suggestions.put(2,Util.category2);
        suggestions.put(3,Util.category3);
        suggestions.put(4,Util.category4);
        suggestions.put(5,Util.category5);
        suggestions.put(6,Util.category6);
    }

}
