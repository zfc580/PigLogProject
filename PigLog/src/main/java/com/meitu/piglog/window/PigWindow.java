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
    private WindowManager.LayoutParams floatParams;
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

            if (floatParams == null) {
                floatParams = new WindowManager.LayoutParams();
                floatParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                floatParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                floatParams.gravity = Gravity.START | Gravity.TOP;
                floatParams.width = 500;
                floatParams.height = 500;
                floatParams.x = screenWidth;
                floatParams.y = screenHeight / 2;
                mFloatView.setPigWindowParams(floatParams);
            }
            try {
                mWindowManager.addView(mFloatView, floatParams);
            }catch (IllegalStateException e){
                Toast.makeText(mContext, "不可重复添加悬浮框",Toast.LENGTH_LONG).show();
            }

        }
    }

    public void removeFloatWindow(){

    }

    public void printLog(String msg){

    }


    // ===========================================================
    // Define Methods
    // ===========================================================


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


    // ===========================================================
    // Getter & Setter
    // ===========================================================


}