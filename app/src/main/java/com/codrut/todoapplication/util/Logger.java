package com.codrut.todoapplication.util;

import android.util.Log;

import com.codrut.todoapplication.BuildConfig;

public class Logger {

    private static final String TAG = "Todo Application";

    public static void printMessage(final String message){
        if(BuildConfig.DEBUG){
            Log.d(TAG, message);
        }
    }

    public static void printError(final Exception e){
        if(BuildConfig.DEBUG){
            Log.e(TAG, "An error has occured!", e);
        }
    }

}
