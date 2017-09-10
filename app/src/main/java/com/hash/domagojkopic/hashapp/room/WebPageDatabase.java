package com.hash.domagojkopic.hashapp.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by domagojkopic on 09/09/2017.
 */

@Database(entities = {WebPage.class}, version = 1, exportSchema = false)
public abstract class WebPageDatabase extends RoomDatabase {
    public static final String DB_NAME = "web_pages";
    public abstract WebPageDao webPageDao();
}
