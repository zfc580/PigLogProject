package com.meitu.piglog.common;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.meitu.piglog.window.PigWindow;

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
    private Application mApplication;
    private PigWindow mPigWindow;
    private PigActivityLifecycleCallbacks mLifecycleCallback;
    private PigComponentCallback mComponentCallback;
    private boolean hadAddWindow = false;

    // ===========================================================
    // Constructor
    // ===========================================================

    // ===========================================================
    // Override Methods
    // ===========================================================
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("zhoufucai", "PigService onCreate. ");
        mApplication = (Application) getApplicationContext();
        mPigWindow = new PigWindow(this);

        mPigWindow.addFloatWindow();
        mLifecycleCallback = new PigActivityLifecycleCallbacks();
        mApplication.registerActivityLifecycleCallbacks(mLifecycleCallback);
        mComponentCallback = new PigComponentCallback();
        mApplication.registerComponentCallbacks(mComponentCallback);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("zhoufucai", "PigService onBind. ");
        return new PigWindowBinder();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPigWindow.removeFloatWindow();
        Log.i("zhoufucai", "PigService onDestroy. ");
        mApplication.unregisterActivityLifecycleCallbacks(mLifecycleCallback);
        mApplication.unregisterComponentCallbacks(mComponentCallback);
    }

    // ===========================================================
    // Define Methods
    // ===========================================================
    public void printlnLog(String message){
        mPigWindow.printFLoatLog(message);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    class PigWindowBinder extends Binder{
        public PigService getService(){
            return PigService.this;
        }
    }

    class PigComponentCallback implements ComponentCallbacks2{

        @Override
        public void onTrimMemory(int level) {
            Log.i("zhoufucai", "ComponentCallbacks2 onTrimMemory level = " + level);
            if(level == TRIM_MEMORY_UI_HIDDEN && mPigWindow != null){
                mPigWindow.setVisivity(false);
            }
        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            Log.i("zhoufucai", "ComponentCallbacks2 onConfigurationChanged newConfig = " + newConfig);
        }

        @Override
        public void onLowMemory() {
            Log.i("zhoufucai", "ComponentCallbacks2 onLowMemory ");
        }
    }

    class PigActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks{

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityCreated activity = "
                    + activity.getComponentName().getShortClassName());
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityStarted activity = "
                    + activity.getComponentName().getShortClassName());
            if(mPigWindow != null && !hadAddWindow){
                /**
                 *  申请权限后，从设置页面返回可以接着显示悬浮窗；
                 */
                mPigWindow.addFloatWindow();
                hadAddWindow = true;
            }
            if(mPigWindow != null){
                mPigWindow.setVisivity(true);
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityResumed activity = "
                    + activity.getComponentName().getShortClassName());
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityPaused activity = "
                    + activity.getComponentName().getShortClassName());
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityStopped activity = "
                    + activity.getComponentName().getShortClassName());

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivitySaveInstanceState activity = "
                    + activity.getComponentName().getShortClassName());
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityDestroyed activity = "
                    + activity.getComponentName().getShortClassName());

        }
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
