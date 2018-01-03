package com.meitu.piglog.common;

import android.content.Context;

import com.meitu.piglog.window.PigWindow;

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
    private PigWindow mPigWindow;

    // ===========================================================
    // Constructor
    // ===========================================================
    public Pig(Context context){
        mContext = context.getApplicationContext();
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

    public static Pig shareInstance(){
        return mInstance;
    }

    public void initPig(){
        mPigWindow = new PigWindow(mContext);
        mPigWindow.addFloatWindow();
    }

    public void releasePig(){
        mPigWindow.removeFloatWindow();
        mPigWindow = null;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
