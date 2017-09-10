package com.hash.domagojkopic.hashapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.hash.domagojkopic.hashapp.room.WebPageDatabase;

/**
 * Created by domagojkopic on 09/09/2017.
 */

public class HashApp extends Application {
    private static HashApp instance;
    private WebPageDatabase webPageDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static HashApp getInstance() {
        return instance;
    }

    public WebPageDatabase getWebPageDatabase() {
        if (webPageDatabase == null) {
            webPageDatabase = Room.databaseBuilder(this, WebPageDatabase.class, WebPageDatabase.DB_NAME).build();
        }
        return webPageDatabase;
    }
}
