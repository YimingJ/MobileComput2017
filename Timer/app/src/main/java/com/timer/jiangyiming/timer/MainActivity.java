package com.timer.jiangyiming.timer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

//    private TextView xText,yText,zText;

    private MobileServiceClient mClient;

    private MobileServiceTable<Message> mMessageTable;
    private Button timerOn;
//    private static final String KEY = "timer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



            // Create the Mobile Service Client instance, using the provided

            // Mobile Service URL and key
        try {
            mClient = new MobileServiceClient(
                    "https://moveup.azurewebsites.net",
                    this);
            // Extend timeout from default of 10s to 20s
            mClient.setAndroidHttpClientFactory(new OkHttpClientFactory() {
                @Override
                public OkHttpClient createOkHttpClient() {
                    OkHttpClient client = new OkHttpClient();
                    client.setReadTimeout(20, TimeUnit.SECONDS);
                    client.setWriteTimeout(20, TimeUnit.SECONDS);
                    return client;
                }
            });

            mMessageTable = mClient.getTable(Message.class);
            Message msg = new Message();
            msg.setText("fromTimer");
            mMessageTable.insert(msg).get(5, TimeUnit.SECONDS);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

//        MessageReceiver receiver = new MessageReceiver(new ToSendMessage());

        //assign textviews
//        xText = (TextView)findViewById(R.id.xText);
//        yText = (TextView)findViewById(R.id.yText);
//        zText = (TextView)findViewById(R.id.zText);
        timerOn = (Button) findViewById(R.id.timerOn);
        Intent intent = new Intent(this, TimerService.class);
//        intent.putExtra("time", 10);
//        intent.putExtra("receiver", receiver);
        startService(intent);
        changeTimerMode(intent);
    }
    public void changeTimerMode (final Intent intent){
        timerOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerOn.getText().equals("timerOn")){
                    timerOn.setText("timerOff");
                    intent.putExtra("timerOn",false);
                    stopService(intent);

                }else{
                    timerOn.setText("timerOn");
                    intent.putExtra("timerOn",true);
                    startService(intent);
                }
            }
        });
    }

    public class ToSendMessage {
        public void displayMsg(int resultCode, Bundle resultData) {
            String message = resultData.getString("finish");
            Toast.makeText(MainActivity.this, resultCode + " " + message, Toast.LENGTH_SHORT).show();
        }
    }
}
