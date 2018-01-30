package com.example.amiotj.tp0;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class UserStorage {

    private static final String USER_NAME  = "user_name";
    private static final String USER_EMAIL = "user_email";

    public static void saveUserInfo(Context context, String name, String email) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(USER_NAME, name);
        editor.putString(USER_EMAIL, email);
        editor.apply();
    }

    public static User getUserInfo(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new User(sharedPreferences.getString(USER_NAME, null), sharedPreferences.getString(USER_EMAIL, null));
    }

    public static boolean isUserLoggedIn(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(USER_NAME, null) != null && sharedPreferences.getString(USER_EMAIL, null) != null;
    }
}
