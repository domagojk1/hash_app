package com.hash.domagojkopic.hashapp.room;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class WebPageDao_Impl implements WebPageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfWebPage;

  public WebPageDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWebPage = new EntityInsertionAdapter<WebPage>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `web_page`(`id`,`url`,`hash`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WebPage value) {
        stmt.bindLong(1, value.getId());
        if (value.getUrl() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUrl());
        }
        if (value.getHash() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getHash());
        }
      }
    };
  }

  @Override
  public long insert(WebPage webPage) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfWebPage.insertAndReturnId(webPage);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<WebPage> getAll() {
    final String _sql = "SELECT * FROM web_page";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
      final int _cursorIndexOfHash = _cursor.getColumnIndexOrThrow("hash");
      final List<WebPage> _result = new ArrayList<WebPage>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final WebPage _item;
        _item = new WebPage();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpUrl;
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        _item.setUrl(_tmpUrl);
        final String _tmpHash;
        _tmpHash = _cursor.getString(_cursorIndexOfHash);
        _item.setHash(_tmpHash);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public WebPage getByUrl(String url) {
    final String _sql = "SELECT * FROM web_page WHERE url = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (url == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, url);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
      final int _cursorIndexOfHash = _cursor.getColumnIndexOrThrow("hash");
      final WebPage _result;
      if(_cursor.moveToFirst()) {
        _result = new WebPage();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpUrl;
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        _result.setUrl(_tmpUrl);
        final String _tmpHash;
        _tmpHash = _cursor.getString(_cursorIndexOfHash);
        _result.setHash(_tmpHash);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public WebPage getByHash(String hash) {
    final String _sql = "SELECT * FROM web_page WHERE url = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (hash == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hash);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfUrl = _cursor.getColumnIndexOrThrow("url");
      final int _cursorIndexOfHash = _cursor.getColumnIndexOrThrow("hash");
      final WebPage _result;
      if(_cursor.moveToFirst()) {
        _result = new WebPage();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _result.setId(_tmpId);
        final String _tmpUrl;
        _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
        _result.setUrl(_tmpUrl);
        final String _tmpHash;
        _tmpHash = _cursor.getString(_cursorIndexOfHash);
        _result.setHash(_tmpHash);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
