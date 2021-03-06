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
    private int mLineNum = 10;
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

    /**
     *  将LOG打印在悬浮窗
     * @param msg 需要打印悬浮窗的字符串
     */
    public static void D(String msg){
        if(mInstance != null){
            mInstance.printLog(msg);
        }
    }

    static int getLineNum() {
        if(mInstance == null){
            return 0;
        }
        return mInstance.mLineNum;
    }

    public static void setLineNum(int num){
        if(mInstance != null){
            mInstance.mLineNum = num;
        }
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
