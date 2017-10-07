package com.example.moveup;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by RyanZhu on 10/7/17.
 */

public class GravitySensorListener implements SensorEventListener {
    private double lastAccX;
    private double lastAccY;
    private double lastAccZ;
    private long startTime;
    private int counter;
    private boolean timerOn;

    public boolean isTimerOn() {
        return timerOn;
    }

    public void incrementCounter() {
        counter++;
    }
    public int getCounter() {
        return counter;
    }

    private static GravitySensorListener listener;

    private GravitySensorListener() {
        this.timerOn = false;
        this.counter = 0;
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
            resetCounter();
            Log.d("sensorChange", ""+counter);
            Log.d("sensorChange", "change detected");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void resetCounter() {
        counter = 0;
    }
}
