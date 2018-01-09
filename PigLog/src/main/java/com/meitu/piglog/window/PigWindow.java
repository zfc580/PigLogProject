package com.meitu.piglog.window;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * PigWindow.java
 * Useage: PigWindow
 * Created by zfc<zfc@meitu.com> on 2018/1/3 - 11:04
 */
public class PigWindow {


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

    public PigWindow(Context context){
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
    public void addFloatWindow(){
        if(!isHaveFloatWindowPermission()){
            return;
        }
        if (mFloatView != null) {
            int screenWidth = mWindowManager.getDefaultDisplay().getWidth();
            int screenHeight = mWindowManager.getDefaultDisplay().getHeight();
            if (mFloatParams == null) {
                mFloatParams = new WindowManager.LayoutParams();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                    mFloatParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                } else {
                    mFloatParams.type = WindowManager.LayoutParams.TYPE_TOAST;
                }
                mFloatParams.format = PixelFormat.RGBA_8888;
                mFloatParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                mFloatParams.gravity = Gravity.START | Gravity.TOP;
                mFloatParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                mFloatParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                mFloatParams.x = screenWidth / 2;
                mFloatParams.y = screenHeight / 2;
                mFloatView.setPigWindowParams(mFloatParams);
            }
            try {
                Log.i("zhoufucai", "PigWindow addView. ");
                mWindowManager.addView(mFloatView, mFloatParams);
            }catch (IllegalStateException e){
                Log.i("zhoufucai", "PigWindow addView error. ");
                e.printStackTrace();
            }
        }
    }

    private boolean isHaveFloatWindowPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            boolean canDraw = Settings.canDrawOverlays(mContext);
            if(!canDraw && !mHadPermitted){
                mHadPermitted = true;
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                mContext.startActivity(intent);
            }
            return canDraw;
        } else {
            return true;
        }
    }

    public void removeFloatWindow(){
        if(mWindowManager != null){
            try {
                Log.i("zhoufucai", "PigWindow removeView. ");
                mWindowManager.removeView(mFloatView);
            }catch (IllegalArgumentException e){
                Log.i("zhoufucai", "PigWindow removeView error. ");
                e.printStackTrace();
            }
        }
    }

    public void setVisivity(boolean visivity){
        if(mFloatView != null){
            mFloatView.setVisibility(visivity ? View.VISIBLE : View.GONE);
        }
    }

    public void printFLoatLog(String msg){
        msg += "\n";
        if(mLogList.size() > 10){
            mLogList.remove(0);
            mLogList.add(msg);
        } else {
            mLogList.add(msg);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(String log: mLogList){
            stringBuilder.append(log);
        }
        String targetLogStr = stringBuilder.toString();
        if(targetLogStr.charAt(targetLogStr.length() - 1) == '\n'){
            targetLogStr = targetLogStr.substring(0, targetLogStr.length() - 1);
        }
        mFloatView.displayLogs(targetLogStr);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
