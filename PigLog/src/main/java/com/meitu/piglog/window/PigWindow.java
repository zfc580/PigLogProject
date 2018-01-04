package com.meitu.piglog.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

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
    public void addFloatWindow(){

        int screenWidth = mWindowManager.getDefaultDisplay().getWidth();
        int screenHeight = mWindowManager.getDefaultDisplay().getHeight();
        if (mFloatView != null) {

            if (mFloatParams == null) {
                mFloatParams = new WindowManager.LayoutParams();
                mFloatParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                mFloatParams.format = PixelFormat.RGBA_8888;
                mFloatParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                mFloatParams.gravity = Gravity.START | Gravity.TOP;
                mFloatParams.width = WindowManager.LayoutParams.WRAP_CONTENT;;
                mFloatParams.height = WindowManager.LayoutParams.WRAP_CONTENT;;
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
            mWindowManager.removeView(mFloatView);
        }
    }


    // ===========================================================
    // Define Methods
    // ===========================================================
    public void printFLoatLog(String msg){
        mFloatView.displayLogs(msg);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


    // ===========================================================
    // Getter & Setter
    // ===========================================================


}
