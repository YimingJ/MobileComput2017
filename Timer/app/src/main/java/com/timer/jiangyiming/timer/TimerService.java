package com.timer.jiangyiming.timer;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by jiangyiming on 10/1/17.
 */

public class TimerService extends IntentService {
    private static final int UniqueID = 1124;

    public TimerService() {
        super("Timer Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("lovelytimer", "Timer service starts");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            int time = 5;
            for (int i = 0; i < time; i++) {
                Log.d("lovelytimer", "i (intent is null)= " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String id = "myChannel";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mChannel = new NotificationChannel(id, "1234", importance);
                mChannel.setLightColor(Color.GREEN);
            }
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                nm.createNotificationChannel(mChannel);
            }

            Notification notification = null;
            Intent intent1 = new Intent(this, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notification = new Notification.Builder(this,id)
                        .setContentTitle("Hey~Hey~Hey")
                        .setContentText("Time for exsercise now!!")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setWhen(System.currentTimeMillis())
                        .build();
            }
            nm.notify(1,notification);

            return;
        }

        ResultReceiver receiver = intent.getParcelableExtra("receiver");
        int time = intent.getIntExtra("time", 0);
        for (int i = 0; i < time; i++) {
            Log.d("lovelytimer", "i (intent is not null) =" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Bundle bundle = new Bundle();
        bundle.putString("finish", "counting finished");
        receiver.send(1234, bundle);
    }
}
