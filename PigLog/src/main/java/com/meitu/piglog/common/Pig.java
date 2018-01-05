package com.meitu.piglog.common;

import android.app.Activity;
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
        }
    }

    public static void destroyInstance(){
        if(mInstance != null){
            mInstance.destroy();
            mInstance = null;
        }
    }

    public static Pig shareInstance(){
        return mInstance;
    }

    private void destroy(){
        if(mApplication != null){
            releasePig();
            mApplication.unregisterActivityLifecycleCallbacks(mLifecycleCallback);
        }
    }

    public void initPig(){
        mPigWindow = new PigWindow(mContext);
        mPigWindow.addFloatWindow();
    }

    public void releasePig(){
        mPigWindow.removeFloatWindow();
        mPigWindow = null;
    }

    /**
     * 判断当前的Activity是否是主Activty，从主Activty退出就是整个应用退出了
     * @return 是否是主Activity
     */
    private boolean isLauncherActivity(){
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> packageInfos = mContext.getPackageManager().queryIntentActivities(intent, 0);
        for (int i = packageInfos.size()-1; i >= 0; i--) {
            String launcherActivityName = packageInfos.get(i).activityInfo.name;
            String packageName = packageInfos.get(i).activityInfo.packageName;
            Log.i("appappinfo", i + " -- launcherActivityName: " + launcherActivityName);
            Log.i("appappinfo", i + " -- packageName: " + packageName);
            if(packageName.equals(mContext.getPackageName())){
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
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityCreated activity = "+activity.getComponentName());
        }

        @Override
        public void onActivityStarted(Activity activity) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityStarted activity = "+activity.getComponentName());
        }

        @Override
        public void onActivityResumed(Activity activity) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityResumed activity = "+activity.getComponentName());
            mPigWindow.addFloatWindow();
        }

        @Override
        public void onActivityPaused(Activity activity) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityPaused activity = "+activity.getComponentName());
            mPigWindow.removeFloatWindow();
        }

        @Override
        public void onActivityStopped(Activity activity) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityStopped activity = "+activity.getComponentName());
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivitySaveInstanceState activity = "+activity.getComponentName());
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityDestroyed activity = "+activity.getComponentName());
            if(isLauncherActivity()){
                destroyInstance();
            }
        }
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
