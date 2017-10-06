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

    private MainActivity.ToSendMessage toSendMessage;
    public MessageReceiver(MainActivity.ToSendMessage toSendMessage){
        super(new Handler());
        this.toSendMessage = toSendMessage;
    }
    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData){
        toSendMessage.displayMsg(resultCode,resultData);
    }
}
