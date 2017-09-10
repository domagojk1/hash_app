package com.hash.domagojkopic.hashapp.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class WebPageDatabase_Impl extends WebPageDatabase {
  private volatile WebPageDao _webPageDao;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate() {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `web_page` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `url` TEXT, `hash` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"fe61001e6a45e1910d818cf343b21867\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `web_page`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsWebPage = new HashMap<String, TableInfo.Column>(3);
        _columnsWebPage.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsWebPage.put("url", new TableInfo.Column("url", "TEXT", false, 0));
        _columnsWebPage.put("hash", new TableInfo.Column("hash", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWebPage = new HashSet<TableInfo.ForeignKey>(0);
        final TableInfo _infoWebPage = new TableInfo("web_page", _columnsWebPage, _foreignKeysWebPage);
        final TableInfo _existingWebPage = TableInfo.read(_db, "web_page");
        if (! _infoWebPage.equals(_existingWebPage)) {
          throw new IllegalStateException("Migration didn't properly handle web_page(com.hash.domagojkopic.hashapp.room.WebPage).\n"
                  + " Expected:\n" + _infoWebPage + "\n"
                  + " Found:\n" + _existingWebPage);
        }
      }
    }, "fe61001e6a45e1910d818cf343b21867");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .version(1)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "web_page");
  }

  @Override
  public WebPageDao webPageDao() {
    if (_webPageDao != null) {
      return _webPageDao;
    } else {
      synchronized(this) {
        if(_webPageDao == null) {
          _webPageDao = new WebPageDao_Impl(this);
        }
        return _webPageDao;
      }
    }
  }
}
