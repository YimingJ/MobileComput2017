package com.example.moveup;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.*;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

//import com.example.zumoappname.*;

public class Me extends Activity {
    Button switchBtn, setHeight, logOut;
    EditText height, weight;
    EditText timeInterval;
    TextView showUsername, showBMI;
    String username;

    private Handler handler;
    private HandlerThread handlerThread;
    private GravitySensorListener gravitySensorListener;
    private Sensor mySensor;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntent();
        gravitySensorListener = GravitySensorListener.getInstance();
        handlerThread = new HandlerThread("timerServiceHandlerThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //accelerometer
        mySensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sensor listener
        sensorManager.registerListener(gravitySensorListener, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
        setContentView(R.layout.activity_me);
        username = getIntent().getStringExtra("userName");
        showUsername = (TextView) findViewById(R.id.showUsername);
        String showUser = "Hello, " +username;
        showUsername.setText(showUser);
        showBMI = (TextView) findViewById(R.id.showBMI);
        switchBtn = (Button) findViewById(R.id.button7);
        setHeight = (Button) findViewById(R.id.button8);
        logOut = (Button) findViewById(R.id.button9);
        timeInterval = (EditText) findViewById(R.id.timeInterval);
        height = (EditText) findViewById(R.id.editText7);
        weight = (EditText) findViewById(R.id.editText8);
        changeTimerMode();

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    List<User> result = MoveUpConstant.getInstance().getUserTable().where().field("username").eq(val(username)).execute().get();
                    if (result.size() == 1) {
                        final String heightS = result.get(0).getuHeight();
                        final String weightS = result.get(0).getuWeight();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                height.setText(heightS);
                                weight.setText(weightS);
                                if (!heightS.equals("0") && !weightS.equals("0")) {
                                    Double BMI = calculateBMI(Integer.parseInt(heightS), Integer.parseInt(weightS));
                                    String BMIS = "BMI: "+String.format("%.2f", BMI);
                                    showBMI.setText(BMIS);
                                }

                            }
                        });
                    }
                } catch (Exception exception) {
                    MainActivity mainActivity = new MainActivity();
                    mainActivity.createAndShowDialog(exception, "Error");
                }
                return null;
            }

        }.execute();


        setHeight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String heightS = height.getText().toString();
                final String weightS = weight.getText().toString();
                int heightInt = Integer.parseInt(heightS);
                int weightInt = Integer.parseInt(weightS);

                if (heightInt == 0 || weightInt == 0) {
                    Toast.makeText(Me.this, "Please input your correct height and weight", Toast.LENGTH_SHORT).show();
                } else {
                    new AsyncTask<Void, Void, Void>() {

                        @Override
                        protected Void doInBackground(Void... params) {
                            try {
                                List<User> result = MoveUpConstant.getInstance().getUserTable().where().field("username").eq(val(username)).execute().get();
                                User user = result.get(0);
                                user.setuWeight(weightS);
                                user.setuHeight(heightS);
                                MoveUpConstant.getInstance().getUserTable().update(user).get();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            height.setText(heightS);
                                            weight.setText(weightS);
                                            if (!heightS.equals("0") && !weightS.equals("0")) {
                                                Double BMI = calculateBMI(Integer.parseInt(heightS), Integer.parseInt(weightS));
                                                String BMIS = "BMI: "+String.format("%.2f", BMI);
                                                showBMI.setText(BMIS);
                                            }

                                        }
                                    });
                            } catch (Exception exception) {
                                MainActivity mainActivity = new MainActivity();
                                mainActivity.createAndShowDialog(exception, "Error");
                            }
                            return null;
                        }

                    }.execute();

                    String suggestion = handleData(heightInt, weightInt);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("suggest", suggestion);
                    intent.putExtras(bundle);
                    intent.setClass(Me.this, Suggestion.class);
                    startActivity(intent);
                }
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Me.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void changeTimerMode() {
        switchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchBtn.getText().equals("Switch Off")) {
                    switchBtn.setText("Switch On");
                    stopMonitoring();
                } else {
                    switchBtn.setText("Switch Off");
                    String timeS = timeInterval.getText().toString();
                    if (timeS.trim().equals("")) {
                        interval = 60 * 60 * 1000;
                    } else {
                        interval = Integer.parseInt(timeS) * 60 * 1000;
                    }
                    startMonitoring();
                }
            }
        });
    }

    private int interval;

    private void startMonitoring() {
        gravitySensorListener.setTimerOn(true);
        postJob(interval);
    }
    int counter = 1;
    public void postJob(int delay) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long curMil = SystemClock.uptimeMillis();
                boolean gtThreshold = curMil - gravitySensorListener.getStartTime() > interval;
                if (gtThreshold && gravitySensorListener.isTimerOn()) {
                    sendNotification(counter, username);
                    counter++;
                    if (counter > intentFor.size()) {
                        counter = 1;
                    }
                }
                if (!gtThreshold) {
                    counter = 1;
                }
                if(gravitySensorListener.isTimerOn()){
                    int nextDelay = Math.min(
                            (int)(SystemClock.uptimeMillis() - gravitySensorListener.getStartTime())
                            , interval);
                    Log.d("postDelay", String.valueOf(nextDelay));
                    postJob(nextDelay);
                }
            }
        }, delay);
    }

    private void stopMonitoring() {
        gravitySensorListener.setTimerOn(false);
    }

    public Double calculateBMI(int height, int weight) {
        double height1 = (double) (height) / 100;
        return ((double) weight) / (height1 * height1);
    }

    public String handleData(int height, int weight) {
        Double BMI = calculateBMI(height, weight);
        String suggestion;
        if (BMI < 18.5) {
            suggestion = "Do level 1";
        } else if (BMI >= 18.5 && BMI < 24) {
            suggestion = "Do level 2";
        } else if (BMI >= 24 && BMI < 27) {
            suggestion = "Do level 3";
        } else {
            suggestion = "Do level 4";
        }

        return suggestion;
    }

    public void sendNotification(int counter, String userName) {
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
        Intent intent1 = intentFor.get(counter);
//        Intent intent1 = new Intent(this,Stretch.class);
        intent1.putExtra("userName",userName);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
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
    }
    private HashMap<Integer,Intent> intentFor;
    public void initIntent() {
        intentFor = new HashMap<>();
        Intent intent1 = new Intent(this, Stretch.class);
        Intent intent2 = new Intent(this,Strength.class);
        Intent intent3 = new Intent(this,Yoga.class);
        Intent intent4 = new Intent(this,LoseWeight.class);
        intentFor.put(1,intent1);
        intentFor.put(2,intent2);
        intentFor.put(3,intent3);
        intentFor.put(4,intent4);
    }
}
