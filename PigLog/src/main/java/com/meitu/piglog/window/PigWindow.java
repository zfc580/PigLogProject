package com.meitu.piglog.window;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.reflect.Method;

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
    private boolean mHadRequestPermission = false;

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
            if(!mHadRequestPermission){
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                mContext.startActivity(intent);
                mHadRequestPermission = true;
            }
            return;
        }

        int screenWidth = mWindowManager.getDefaultDisplay().getWidth();
        int screenHeight = mWindowManager.getDefaultDisplay().getHeight();
        if (mFloatView != null) {

            if (mFloatParams == null) {
                mFloatParams = new WindowManager.LayoutParams();
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    mFloatParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                } else {
                    mFloatParams.type = WindowManager.LayoutParams.TYPE_PHONE;
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
                mWindowManager.addView(mFloatView, mFloatParams);
            }catch (IllegalStateException e){
                Toast.makeText(mContext, "不可重复添加悬浮框",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void removeFloatWindow(){
        if(mWindowManager != null){
            try {
                mWindowManager.removeView(mFloatView);
            }catch (IllegalArgumentException e){
                Toast.makeText(mContext, "不可重复移除悬浮框",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void printFLoatLog(String msg){
        mFloatView.displayLogs(msg);
    }

    private boolean isHaveFloatWindowPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return Settings.canDrawOverlays(mContext);
        } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            return checkOps();
        } else {
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean checkOps() {
        try {
            Object object = mContext.getSystemService(Context.APP_OPS_SERVICE);
            if (object == null) {
                return false;
            }
            Class localClass = object.getClass();
            Class[] arrayOfClass = new Class[3];
            arrayOfClass[0] = Integer.TYPE;
            arrayOfClass[1] = Integer.TYPE;
            arrayOfClass[2] = String.class;
            Method method = localClass.getMethod("checkOp", arrayOfClass);
            if (method == null) {
                return false;
            }
            Object[] arrayOfObject1 = new Object[3];
            arrayOfObject1[0] = 24;
            arrayOfObject1[1] = Binder.getCallingUid();
            arrayOfObject1[2] = mContext.getPackageName();
            int m = (Integer) method.invoke(object, arrayOfObject1);
            return m == AppOpsManager.MODE_ALLOWED;
        } catch (Exception ignore) {
        }
        return false;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
