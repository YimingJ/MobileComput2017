package com.accelerometer.jiangyiming.accelerometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private TextView xText,yText,zText;
    private Sensor mySensor;
    private SensorManager sensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("tag", "log message");
        //create sensor manager
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        //accelerometer
        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sensor listener
        sensorManager.registerListener(this,mySensor,SensorManager.SENSOR_DELAY_NORMAL);

        //assign textviews
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);

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
}
