package com.hash.domagojkopic.hashapp.mvp.main;

import android.arch.persistence.room.Database;

import com.hash.domagojkopic.hashapp.room.WebPage;

/**
 * Created by domagojkopic on 09/09/2017.
 */

public interface MainInteractor {

    interface DatabaseListener {
        void onSuccess(WebPage webPage);
        void onFailed();
    }

    interface FetchHashListener {
        void onFetchHashSuccess(byte[] hash);
        void onFetchHashError();
    }

    void fetchContent(String url, FetchHashListener listener);
    void saveToDatabase(String url, byte[] hash, DatabaseListener listener);
    void saveToPreferences(String url, byte[] hash);
    boolean preferencesContains(String url);
    void databaseContains(String url, DatabaseListener listener);
}
