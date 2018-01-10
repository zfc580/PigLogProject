package com.meitu.piglog;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.meitu.piglog.util.PLog;

/**
 * PigService.java
 * Useage: PigService
 * Created by zfc<zfc@meitu.com> on 2018/1/9 - 14:10
 */
public class PigService extends Service {

    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    private PigWindow mPigWindow;
    private boolean mHadAddWindow = false;
    private SimpleLifecycleCallback mActivityCallback = new SimpleLifecycleCallback(){
        @Override
        public void onActivityStarted(Activity activity) {
            super.onActivityStarted(activity);
            if(mPigWindow != null && !mHadAddWindow){
                /**
                 *  申请权限后，从设置页面返回可以接着显示悬浮窗；
                 */
                mPigWindow.addFloatWindow();
                mHadAddWindow = true;
            }
            if(mPigWindow != null){
                mPigWindow.setVisivity(true);
            }
        }
    };

    // ===========================================================
    // Constructor
    // ===========================================================

    // ===========================================================
    // Override Methods
    // ===========================================================
    @Override
    public void onCreate() {
        super.onCreate();
        PLog.i("zhoufucai", "PigService onCreate. ");
        mPigWindow = new PigWindow(this);
        mPigWindow.addFloatWindow();
        getApplication().registerActivityLifecycleCallbacks(mActivityCallback);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        PLog.i("zhoufucai", "PigService onBind. ");
        return new PigWindowBinder();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        PLog.i("zhoufucai", "PigService onTrimMemory level = " + level);
        if(level == TRIM_MEMORY_UI_HIDDEN && mPigWindow != null){
            mPigWindow.setVisivity(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPigWindow != null){
            mPigWindow.removeFloatWindow();
        }
        PLog.i("zhoufucai", "PigService onDestroy. ");
        getApplication().unregisterActivityLifecycleCallbacks(mActivityCallback);
    }

    // ===========================================================
    // Define Methods
    // ===========================================================
    protected void printlnLog(String message){
        if(mPigWindow != null){
            mPigWindow.printFLoatLog(message);
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    class PigWindowBinder extends Binder{
        PigService getService(){
            return PigService.this;
        }
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
