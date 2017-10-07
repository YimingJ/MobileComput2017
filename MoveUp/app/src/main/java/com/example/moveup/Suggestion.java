package com.example.moveup;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

//import com.example.zumoappname.*;

public class Suggestion extends Activity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);
        textView = (TextView) findViewById(R.id.textView13);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String string = bundle.getString("suggest");
        textView.setText(string);
    }

}
