package com.example.moveup;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.squareup.okhttp.OkHttpClient;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangyiming on 10/7/17.
 */

public class MoveUpConstant {
    private static MoveUpConstant constant;

    public static void setConstant(MoveUpConstant constant) {
        MoveUpConstant.constant = constant;
    }

    public static MoveUpConstant getInstance() {
        return constant;
    }
    /**
     * Mobile Service Client reference
     */
    private final MobileServiceClient mClient;
    /**
     * Mobile Service Table used to access data
     */
    private final MobileServiceTable<User> mUserTable;

    public MoveUpConstant(MainActivity activity) throws MalformedURLException {
        // Mobile Service URL and key
        mClient = new MobileServiceClient("https://moveup.azurewebsites.net", activity);

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

        // Get the Mobile Service Table instance to use
        mUserTable = mClient.getTable(User.class);
    }

    public MobileServiceClient getmClient() {
        return mClient;
    }

    public MobileServiceTable<User> getUserTable() {
        return mUserTable;
    }

}
