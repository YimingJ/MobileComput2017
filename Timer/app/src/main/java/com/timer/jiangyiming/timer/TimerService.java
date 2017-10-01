package com.timer.jiangyiming.timer;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by jiangyiming on 10/1/17.
 */

public class TimerService extends IntentService {
    NotificationCompat.Builder notification;
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
            notification = new NotificationCompat.Builder(this);
            notification.setAutoCancel(true);
            notification.setSmallIcon(R.mipmap.ic_launcher);
            notification.setTicker("Ticker");
            notification.setWhen(System.currentTimeMillis());
            notification.setContentTitle("Notification~Hey");
            notification.setContentText("timer is done...");

            Intent intent1 = new Intent(this,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pendingIntent);

            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.notify(UniqueID,notification.build());

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
