package com.jing.common.utils;

import android.os.Debug;

/**
 * Created by linux-sever-build5 on 11/18/16.
 */
public class LogUtils {

    private static final String TAG = "jingbin";

    private static boolean Debug = true;


    public static void logd(String tag, String msg){
        android.util.Log.d(TAG, tag + "---" +msg);
    }

    public static void logd(String tag, String msg, Throwable e){
        android.util.Log.d(TAG, tag + "---" +msg, e);
    }


    public static void loge(String tag, String msg){
        android.util.Log.e(TAG, tag + "---" +msg);
    }

    public static void loge(String tag, String msg, Throwable e){
        android.util.Log.e(TAG, tag + "---" +msg, e);
    }


}
