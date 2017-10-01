package com.timer.jiangyiming.timer;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by jiangyiming on 10/1/17.
 */

public class TimerService extends IntentService {

    public TimerService(){
        super("Timer Service");
        Log.d("lovelytimer","Timer service starts");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i <5;i++){
            Log.d("lovelytimer","i ="+i);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
