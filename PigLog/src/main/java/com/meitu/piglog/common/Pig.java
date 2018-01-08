package com.meitu.piglog.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
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
    private Application mApplication;
    private static Pig mInstance;
    private PigWindow mPigWindow;
    private PigActivityLifecycleCallbacks mLifecycleCallback;

    // ===========================================================
    // Constructor
    // ===========================================================
    public Pig(Context context){
        mContext = context.getApplicationContext();
        mApplication = (Application)mContext;
        mLifecycleCallback = new PigActivityLifecycleCallbacks();
        mApplication.registerActivityLifecycleCallbacks(mLifecycleCallback);
    }

    // ===========================================================
    // Override Methods
    // ===========================================================

    // ===========================================================
    // Define Methods
    // ===========================================================
    public static void createInstance(Context context){
        if(mInstance == null){
            mInstance = new Pig(context);
            mInstance.initPig();
        }
    }

    private static void destroyInstance(){
        if(mInstance != null){
            mInstance.destroy();
            mInstance = null;
        }
    }

    private void destroy(){
        if(mApplication != null){
            releasePig();
            mApplication.unregisterActivityLifecycleCallbacks(mLifecycleCallback);
        }
    }

    public void initPig(){
        mPigWindow = new PigWindow(mContext);
    }

    private void releasePig(){
        mPigWindow = null;
    }

    /**
     * APP是否处于前台唤醒状态
     *
     * @return true 前台, false 后台
     */
    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) mContext.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = mContext.getApplicationContext().getPackageName();
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            Log.i("appappinfo", " processName: " + appProcess.processName+", importance = "+appProcess.importance);
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    public void printLog(String msg){
        mPigWindow.printFLoatLog(msg);
    }

    public static void D(String tag, String msg){
        StringBuilder sb = new StringBuilder(tag);
        sb.append(":").append(msg);
        mInstance.printLog(sb.toString());
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    class PigActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks{

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityCreated activity = "
                    + activity.getComponentName().getShortClassName());

        }

        @Override
        public void onActivityStarted(Activity activity) {
            boolean isForeground = isAppOnForeground();
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityStarted activity = "
                    + activity.getComponentName().getShortClassName() + ", isForeground = " + isForeground);

            if(mPigWindow != null){
                mPigWindow.addFloatWindow();
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
            boolean isForeground = isAppOnForeground();
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityStopped activity = "
                    + activity.getComponentName().getShortClassName() + ", isForeground = " + isForeground);
            if(mPigWindow != null && !isForeground){
                mPigWindow.removeFloatWindow();
            }
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
