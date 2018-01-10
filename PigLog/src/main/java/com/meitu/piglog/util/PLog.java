package com.meitu.piglog.util;

import android.util.Log;

/**
 * PLog.java
 * Useage: PLog
 * Created by zfc<zfc@meitu.com> on 2018/1/10 - 20:08
 */
public class PLog {

    private static boolean mIsPrintLog = false;

    public static void i(String tag, String log){
        if(mIsPrintLog){
            Log.i(tag, log);
        }
    }

    public static void w(String tag, String log){
        if(mIsPrintLog){
            Log.w(tag, log);
        }
    }

    public static void e(String tag, String log){
        if(mIsPrintLog){
            Log.e(tag, log);
        }
    }

}
