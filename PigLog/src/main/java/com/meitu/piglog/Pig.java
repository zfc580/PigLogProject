package com.meitu.piglog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

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
    private static int mLineNum = 10;
    private static Pig mInstance;
    private Context mContext;
    private PigService mService;


    // ===========================================================
    // Constructor
    // ===========================================================
    private Pig(Context context){
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
        if(mContext != null){
            mContext.unbindService(mServiceConnection);
        }
    }

    private void printLog(String msg){
        if(mService != null){
            mService.printlnLog(msg);
        }
    }

    public static void D(String tag, String msg){
        tag += ":";
        tag += msg;
        mInstance.printLog(tag);
    }

    protected static int getLineNum() {
        return mLineNum;
    }

    public static void setLineNum(int num){
        mLineNum = num;
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
