package com.android.excilys.chatwithmrroboto;

import android.app.Application;

/**
 * Created by excilys on 16/01/17.
 */

public class ChatApplication extends Application {

    private String url;

    @Override
    public void onCreate() {
        super.onCreate();

        url= "http://10.0.1.35:9000/";
    }

    public String getUrl() {
        return url;
    }
}