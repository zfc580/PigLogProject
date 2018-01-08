package com.meitu.piglogproject.app.main.application;

import android.app.Application;

import com.meitu.piglog.common.Pig;

/**
 * PigApplication.java
 * Useage: PigApplication
 * Created by zfc<zfc@meitu.com> on 2018/1/8 - 14:40
 */
public class PigApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Pig.createInstance(this);
    }
}
