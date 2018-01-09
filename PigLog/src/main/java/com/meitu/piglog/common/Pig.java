package com.meitu.piglog.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.meitu.piglog.window.PigWindow;

import java.util.List;

/**
 * Pig.java
 * Useage: Pig
 * Created by zfc<zfc@meitu.com> on 2018/1/3 - 14:26
 */
public class Pig {


    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    private Context mContext;
    private static Pig mInstance;
    private PigService mService;


    // ===========================================================
    // Constructor
    // ===========================================================
    public Pig(Context context){
        mContext = context.getApplicationContext();
        Intent intent = new Intent(mContext, PigService.class);
        mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    // ===========================================================
    // Override Methods
    // ===========================================================

    // ===========================================================
    // Define Methods
    // ===========================================================
    public static void init(Context context){
        if(mInstance == null){
            mInstance = new Pig(context);
        }
    }

    public static void unInit(){
        if(mInstance != null){
            mInstance.destroy();
            mInstance = null;
        }
    }

    private void destroy(){
        mContext.unbindService(mServiceConnection);
    }


    public void printLog(String msg){
        mService.printlnLog(msg);
    }

    public static void D(String tag, String msg){
        StringBuilder sb = new StringBuilder(tag);
        sb.append(":").append(msg);
        mInstance.printLog(sb.toString());
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ((PigService.PigWindowBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }
    };

    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
