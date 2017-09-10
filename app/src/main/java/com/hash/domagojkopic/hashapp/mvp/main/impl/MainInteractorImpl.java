package com.hash.domagojkopic.hashapp.mvp.main.impl;

import android.content.Context;

import com.hash.domagojkopic.hashapp.HashApp;
import com.hash.domagojkopic.hashapp.mvp.main.MainInteractor;
import com.hash.domagojkopic.hashapp.networking.NetworkClient;
import com.hash.domagojkopic.hashapp.room.WebPage;
import com.hash.domagojkopic.hashapp.room.WebPageDao;
import com.hash.domagojkopic.hashapp.utils.HashCalculator;
import com.hash.domagojkopic.hashapp.utils.PreferenceHelper;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.security.NoSuchAlgorithmException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by domagojkopic on 09/09/2017.
 */

public class MainInteractorImpl implements MainInteractor {

    private Context context;
    private PreferenceHelper preferenceHelper;

    public MainInteractorImpl(Context context) {
        this.context = context;
        preferenceHelper = new PreferenceHelper(context);
    }

    @Override
    public void fetchContent(String url, final FetchHashListener listener) {
        try {
            OkHttpClient client = NetworkClient.getInstance().getOkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    listener.onFetchHashError();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        try {
                            if (response.body() != null) {
                                byte[] hash = HashCalculator.calculateSHA1(response.body().toString());
                                listener.onFetchHashSuccess(hash);
                            }
                            else {
                                listener.onFetchHashError();
                            }
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                            listener.onFetchHashError();
                        }
                    }
                }
            });
        } catch (IllegalArgumentException ex) {
            listener.onFetchHashError();
        }
    }

    @Override
    public void saveToDatabase(final String url, final byte[] hash, final MainInteractor.DatabaseListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                WebPage webPage = new WebPage();
                webPage.setHash(HashCalculator.getHexString(hash));
                webPage.setUrl(url);

                WebPageDao dao = HashApp.getInstance().getWebPageDatabase().webPageDao();
                if (dao.insert(webPage) > 0) {
                    listener.onSuccess(webPage);
                } else {
                    listener.onFailed();
                }
            }
        }).start();
    }

    @Override
    public void saveToPreferences(String url, byte[] hash) {
        preferenceHelper.putString(url, HashCalculator.getHexString(hash));
    }

    @Override
    public boolean preferencesContains(String url) {
        String hash = preferenceHelper.getString(url);
        return !hash.equals("");
    }

    @Override
    public void databaseContains(final String url, final MainInteractor.DatabaseListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                WebPageDao dao = HashApp.getInstance().getWebPageDatabase().webPageDao();
                WebPage webPage = dao.getByUrl(url);
                if (webPage != null) {
                    listener.onSuccess(webPage);
                }
                else {
                    listener.onFailed();
                }
            }
        }).start();
    }
}
