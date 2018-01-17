package com.meitu.piglog;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.meitu.piglog.util.PLog;

import java.util.LinkedList;
import java.util.List;

/**
 * PigWindow.java
 * Useage: PigWindow
 * Created by zfc<zfc@meitu.com> on 2018/1/3 - 11:04
 */
class PigWindow {


    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    private Context mContext;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mFloatParams;
    private FloatLogView mFloatView;
    private List<String> mLogList = new LinkedList<>();
    private boolean mHadPermitted = false;

    // ===========================================================
    // Constructor
    // ===========================================================

    PigWindow(Context context){
        mContext = context;
        mFloatView = new FloatLogView(mContext);
        mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mFloatView.setPigWindowManager(mWindowManager);
    }

    // ===========================================================
    // Override Methods
    // ===========================================================


    // ===========================================================
    // Define Methods
    // ===========================================================
    void addFloatWindow(){

        if (mFloatView != null) {
            if (mFloatParams == null) {
                mFloatParams = new WindowManager.LayoutParams();
                mFloatParams.format = PixelFormat.RGBA_8888;
                mFloatParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                mFloatParams.gravity = Gravity.START | Gravity.TOP;
                mFloatParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                mFloatParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                mFloatView.setPigWindowParams(mFloatParams);
            }
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                    if(!isHaveFloatWindowPermission()){
                        return;
                    }
                    mFloatParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                } else {
                    mFloatParams.type = WindowManager.LayoutParams.TYPE_TOAST;
                }
                PLog.i("zhoufucai", "PigWindow addView. ");
                mWindowManager.addView(mFloatView, mFloatParams);
            }catch (Exception e){
                PLog.i("zhoufucai", "PigWindow addView error. e = " + e.getMessage());
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean isHaveFloatWindowPermission(){
        boolean canDraw = Settings.canDrawOverlays(mContext);
        if(!canDraw && !mHadPermitted){
            mHadPermitted = true;
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            mContext.startActivity(intent);
        }
        return canDraw;
    }

    void removeFloatWindow(){
        if(mWindowManager != null){
            try {
                PLog.i("zhoufucai", "PigWindow removeView. ");
                mWindowManager.removeView(mFloatView);
            }catch (IllegalArgumentException e){
                PLog.i("zhoufucai", "PigWindow removeView error. e = " + e.getMessage());
            }
        }
    }

    void setVisivity(boolean visivity){
        if(mFloatView != null){
            mFloatView.setVisibility(visivity ? View.VISIBLE : View.GONE);
        }
    }

    void printFLoatLog(String msg){
        if(msg.contains("\n")){
            String[] logs = msg.split("\n");
            for(String log : logs){
                storeList(log);
            }
        } else {
            storeList(msg);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(String log: mLogList){
            stringBuilder.append(log).append("\n");
        }
        String targetLogStr = stringBuilder.toString();
        if(targetLogStr.charAt(targetLogStr.length() - 1) == '\n'){
            targetLogStr = targetLogStr.substring(0, targetLogStr.length() - 1);
        }
        mFloatView.displayLogs(targetLogStr);
    }

    private void storeList(String log){
        if(mLogList.size() > Pig.getLineNum()){
            mLogList.remove(0);
            mLogList.add(log);
        } else {
            mLogList.add(log);
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
