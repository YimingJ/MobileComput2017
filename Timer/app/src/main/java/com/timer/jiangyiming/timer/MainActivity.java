package com.timer.jiangyiming.timer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private static final String KEY = "timer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MessageReceiver receiver = new MessageReceiver(new Message());


        Intent intent = new Intent(this, TimerService.class);
        intent.putExtra("time", 100);
        intent.putExtra("receiver", receiver);
        startService(intent);
    }

    public class Message {
        public void displayMsg(int resultCode, Bundle resultData) {
            String message = resultData.getString("finish");
            Toast.makeText(MainActivity.this, resultCode + " " + message, Toast.LENGTH_SHORT).show();
        }
    }
}
