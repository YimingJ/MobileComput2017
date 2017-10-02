package com.timer.jiangyiming.timer;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView xText,yText,zText;
    private Sensor mySensor;
    private SensorManager sensorManager;

    private Button timerOn;
    private static final String KEY = "timer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MessageReceiver receiver = new MessageReceiver(new Message());

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        //accelerometer
        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sensor listener
        sensorManager.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);

        //assign textviews
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);


        Intent intent = new Intent(this, TimerService.class);
        intent.putExtra("time", 10);
        intent.putExtra("receiver", receiver);
        startService(intent);
        changeTimerMode(intent);
    }
    public void changeTimerMode(final Intent intent){
        timerOn = (Button) findViewById(R.id.timerOn);
        timerOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerOn.getText().equals("timerOn")){
                    timerOn.setText("timerOff");
                    stopService(intent);
                }else{
                    timerOn.setText("timerOn");
                    startService(intent);
                }
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        xText.setText("X "+ sensorEvent.values[0]);
        yText.setText("Y "+ sensorEvent.values[1]);
        zText.setText("Z "+ sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public class Message {
        public void displayMsg(int resultCode, Bundle resultData) {
            String message = resultData.getString("finish");
            Toast.makeText(MainActivity.this, resultCode + " " + message, Toast.LENGTH_SHORT).show();
        }
    }
}
