package com.example.catapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

//I was gonna use shared preferences to hold a username and then use the api to hold favourites
//problem is the api can only favourite images, and you can't use the api to get information
//(like the breed of the cat in the image), unless you're the one who uploaded the image
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
