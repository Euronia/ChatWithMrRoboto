package com.android.excilys.chatwithmrroboto.dao;

import android.content.Context;
import android.net.ConnectivityManager;

import com.android.excilys.chatwithmrroboto.entity.User;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by excilys on 16/01/17.
 */

public class UserDao {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client;

    public boolean connect (String url, User user)
    {
        client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(user.getUsername(),user.getPassword()))
                .build();
        try {
            return isConnected(getConnect(url,user));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isConnected (String response) {
        return response.contains("\"status\":200");
    }

    String getConnect(String url, User user) throws IOException {
        String connectUrl = url + "2.0/connect/";
        Request request = new Request.Builder()
               .url(connectUrl)
                .build();
        System.out.println(request.toString());
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
