package com.example.labeling.ui.login;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.labeling.reqAndRes.LoginResponse;

public class LoginHelper {
    public final static String SharedPrefName = "login";
    public final static String TokenKey = "token";
    public final static String TokenTypeKey = "type";
    public final static String EmailKey = "email";

    public static void saveLoginData(Context context, LoginResponse loginResponse, String email){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TokenKey, loginResponse.getAccessToken());
        editor.putString(TokenTypeKey, loginResponse.getTokenType());
        editor.putString(EmailKey, email);
        editor.commit();
    }

    public static String getEmail(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefName,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(EmailKey, null);
    }

    public static String getAccessToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefName,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(TokenKey, null);
    }

    public static String getTokenType(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPrefName,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(TokenTypeKey, null);
    }
}
