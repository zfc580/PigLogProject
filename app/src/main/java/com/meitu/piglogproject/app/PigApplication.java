package com.meitu.piglogproject.app;

import android.app.Application;
import android.content.Context;

import com.meitu.piglog.common.Pig;

/**
 * PigApplication.java
 * Useage: PigApplication
 * Created by zfc<zfc@meitu.com> on 2018/1/8 - 14:40
 */
public class PigApplication extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        Pig.init(this);
    }

    public static Context getPigApplication(){
        return mContext;
    }
}
