package com.example.moveup;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by jiangyiming on 10/1/17.
 */

public class TimerService extends IntentService implements SensorEventListener {
    //    private static final long timeInterval = 1000 * 60 * 120;
    private static long timeInterval = 10000;
    private long startTime;
    private boolean stopFlag;
    private static int counter;
    private boolean timerOn;
    private String userName;

    private double lastAccX;
    private double lastAccY;
    private double lastAccZ;
    private Sensor mySensor;
    private SensorManager sensorManager;

    public TimerService() {
        super("Timer Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startTime = SystemClock.uptimeMillis();
        counter = 0;
        lastAccX = 0;
        lastAccY = 0;
        lastAccZ = 0;
        stopFlag = false;
        timerOn = true;
        Log.d("lovelytimer", "Timer service starts");

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //accelerometer
        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sensor listener
        sensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("sensorState", "sensor starts");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d("lovelytimer", "onStartCommand");
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //accelerometer
        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sensor listener
        sensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("sensorState", "sensor onStartCommand");
        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (!stopFlag) {
            long currentTime = SystemClock.uptimeMillis();
            long interval = currentTime - startTime;
            timeInterval = intent.getIntExtra("timeInterval", 10000);
            userName = intent.getStringExtra("userName");
            if (interval > timeInterval) {
                Log.d("lovelytimer", "" + interval);
                sendNotification();
                startTime = SystemClock.uptimeMillis();
            }
            if (intent == null) {
                timerOn = true;
            } else {
                timerOn = intent.getBooleanExtra("timerOn", true);
            }
        }
//        ResultReceiver receiver = intent.getParcelableExtra("receiver");
//        int time = intent.getIntExtra("time", 0);
//        for (int i = 0; i < time; i++) {
//            Log.d("lovelytimer", "i (intent is not null) =" + i);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        Bundle bundle = new Bundle();
//        bundle.putString("finish", "counting finished");
//        receiver.send(1234, bundle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopFlag = true;
        sensorManager.unregisterListener(this);
        Log.d("lovelytimer", "service closed..");
    }

    public void sendNotification() {
        String id = "myChannel";
        int importance = NotificationManager.IMPORTANCE_LOW;
        NotificationChannel myChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            myChannel = new NotificationChannel(id, "1234", importance);
            myChannel.setLightColor(Color.GREEN);
        }
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(myChannel);
        }

        Notification notification = null;
        Intent intent1 = new Intent(this, Me.class);
        intent1.putExtra("userName",userName);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            long totalTime = counter * (timeInterval / 1000 * 60);
            notification = new Notification.Builder(this, id)
                    .setContentTitle("Let's move up")
                    .setContentText("Don't be a couch potato!!")
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setShowWhen(true)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .build();
        } else {
            notification = new Notification.Builder(this)
                    .setContentTitle("Move up")
                    .setContentText("Don't be a couch potato!")
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setShowWhen(true)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .build();
        }

        nm.notify(1, notification);
        Log.d("send", "notification sent...");
        counter++;
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
//            stopService(new Intent(this,TimerService.class));
//            startService(new Intent(this,TimerService.class));
            startTime = SystemClock.uptimeMillis();
            lastAccX = curAccX;
            lastAccY = curAccY;
            lastAccZ = curAccZ;
            Log.d("sensorChange", "change detected");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
