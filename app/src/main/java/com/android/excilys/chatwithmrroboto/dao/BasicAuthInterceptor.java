package com.android.excilys.chatwithmrroboto.dao;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by excilys on 16/01/17.
 */

public class BasicAuthInterceptor implements Interceptor {

    private String credentials;

    public BasicAuthInterceptor(String user, String password) {
        this.credentials = Credentials.basic(user, password);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request autheticatedRequest = request.newBuilder().header("Authorization", credentials).build();
        return chain.proceed(autheticatedRequest);
    }
}
