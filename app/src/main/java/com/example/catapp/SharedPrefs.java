package com.example.catapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

//Im leaving this here in case i decide to use usernames
public class SharedPrefs {

    public static void editPrefs(String key, String value, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrefs(String key, String value, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

}
