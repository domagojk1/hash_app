package com.hash.domagojkopic.hashapp.networking;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by domagojkopic on 09/09/2017.
 */

public class NetworkClient {
    private static NetworkClient instance;
    private OkHttpClient okHttpClient;

    private NetworkClient() {
    }

    public static synchronized NetworkClient getInstance() {
        if (instance == null) {
            instance = new NetworkClient();
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient();
        }
        return okHttpClient;
    }
}
