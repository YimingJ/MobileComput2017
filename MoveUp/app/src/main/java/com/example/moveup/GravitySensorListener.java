package com.example.moveup;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.SystemClock;
import android.util.Log;


public class GravitySensorListener implements SensorEventListener {
    private double lastAccX;
    private double lastAccY;
    private double lastAccZ;
    private long startTime;
    private boolean timerOn;

    public boolean isTimerOn() {
        return timerOn;
    }


    private static GravitySensorListener listener;

    private GravitySensorListener() {
        this.timerOn = false;
    }

    public static GravitySensorListener getInstance() {
        if (listener == null) {
            listener = new GravitySensorListener();
        }
        return listener;
    }

    public void setTimerOn(boolean timerOn) {
        this.timerOn = timerOn;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        double curAccX = sensorEvent.values[0];
        double curAccY = sensorEvent.values[1];
        double curAccZ = sensorEvent.values[2];
        if (((curAccX - lastAccX) > 1 || (curAccY - lastAccY > 1) || (curAccZ - lastAccZ) > 1) && timerOn) {
            Log.d("sensorState", "X " + curAccX);
            Log.d("sensorState", "Y " + curAccY);
            Log.d("sensorState", "Z " + curAccZ);
            startTime = SystemClock.uptimeMillis();
            lastAccX = curAccX;
            lastAccY = curAccY;
            lastAccZ = curAccZ;
            Log.d("sensorChange", "change detected");
        }
    }

    public long getStartTime() {
        return startTime;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
