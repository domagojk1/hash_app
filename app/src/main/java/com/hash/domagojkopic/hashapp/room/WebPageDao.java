package com.hash.domagojkopic.hashapp.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by domagojkopic on 09/09/2017.
 */

@Dao
public interface WebPageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(WebPage webPage);

    @Query("SELECT * FROM web_page")
    List<WebPage> getAll();

    @Query("SELECT * FROM web_page WHERE url = :url")
    WebPage getByUrl(String url);

    @Query("SELECT * FROM web_page WHERE url = :hash")
    WebPage getByHash(String hash);
}
