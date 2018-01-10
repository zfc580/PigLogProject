package com.meitu.piglog;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.meitu.piglog.util.PLog;

/**
 * SimpleLifecycleCallback.java
 * Useage: SimpleLifecycleCallback
 * Created by zfc<zfc@meitu.com> on 2018/1/10 - 11:04
 */
public class SimpleLifecycleCallback implements Application.ActivityLifecycleCallbacks {
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
       PLog.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityCreated activity = "
                + activity.getComponentName().getShortClassName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
       PLog.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityStarted activity = "
                + activity.getComponentName().getShortClassName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
       PLog.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityResumed activity = "
                + activity.getComponentName().getShortClassName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
       PLog.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityPaused activity = "
                + activity.getComponentName().getShortClassName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
       PLog.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityStopped activity = "
                + activity.getComponentName().getShortClassName());

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
       PLog.i("zhoufucai", "PigActivityLifecycleCallbacks onActivitySaveInstanceState activity = "
                + activity.getComponentName().getShortClassName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
       PLog.i("zhoufucai", "PigActivityLifecycleCallbacks onActivityDestroyed activity = "
                + activity.getComponentName().getShortClassName());

    }
}
