package com.timer.jiangyiming.timer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by jiangyiming on 10/1/17.
 */

@SuppressLint("ParcelCreator")
public class MessageReceiver extends ResultReceiver {

    private MainActivity.Message message;
    public MessageReceiver(MainActivity.Message message){
        super(new Handler());
        this.message = message;
    }
    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData){
        message.displayMsg(resultCode,resultData);
    }
}