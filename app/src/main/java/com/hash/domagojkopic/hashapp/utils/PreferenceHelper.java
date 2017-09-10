package com.hash.domagojkopic.hashapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by domagojkopic on 09/09/2017.
 */

public class PreferenceHelper {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME = "myPrefs";
    private Context context;

    public PreferenceHelper(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void putString(String key, String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value).apply();
    }

    public void putBoolean(String key, Boolean value) {
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public Boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void clear() {
        editor.clear().apply();
    }
}