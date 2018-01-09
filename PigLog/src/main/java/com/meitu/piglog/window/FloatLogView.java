package com.meitu.piglog.window;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meitu.piglog.R;

import java.lang.reflect.Field;

/**
 * FloatLogView.java
 * Useage: FloatLogView
 * Created by zfc<zfc@meitu.com> on 2018/1/3 - 11:25
 */
public class FloatLogView extends RelativeLayout implements View.OnClickListener{

    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    //private RelativeLayout mLogPanelLayout;
    private TextView mDisplayTextView;
    private ImageView mDragView;
    private float mXInScreen;
    private float mYInScreen;
    private float mXInView;
    private float mYInView;
    private int mStatusBarHeight;
    private int mWindowWidth;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;

    // ===========================================================
    // Constructor
    // ===========================================================
    public FloatLogView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.pig_float_view, this);
//       mLogPanelLayout = (RelativeLayout)findViewById(R.id.pig_float_panel);
        mDragView = (ImageView) findViewById(R.id.tv_float_drag);
        mDisplayTextView = (TextView)findViewById(R.id.tv_float_display);
//        mCloseButton = (ImageView) findViewById(R.id.btn_float_close);
        mDragView.setOnClickListener(this);
        mDisplayTextView.setOnClickListener(this);

    }

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    public void onClick(View v) {
         if(v.getId() == R.id.tv_float_drag){
            if(mDisplayTextView.getVisibility() == VISIBLE){
                mDisplayTextView.setVisibility(GONE);
            } else {
                mDisplayTextView.setVisibility(VISIBLE);
            }
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Rect appRect = new Rect();
        getWindowVisibleDisplayFrame(appRect);
        mStatusBarHeight = appRect.top;
        mWindowWidth = appRect.right;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mXInView = event.getX();
                mYInView = event.getY();
                mXInScreen = event.getRawX();
                mYInScreen = event.getRawY() - mStatusBarHeight;

                break;
            case MotionEvent.ACTION_MOVE:
                mXInScreen = event.getRawX();
                mYInScreen = event.getRawY() - mStatusBarHeight;
                updateViewPosition(false);
                break;

            case MotionEvent.ACTION_UP:
                updateViewPosition(true);
                break;

        }
        return super.dispatchTouchEvent(event);
    }

    // ===========================================================
    // Define Methods
    // ===========================================================

    private void updateViewPosition(boolean isUp) {
        int targetX = (int) (mXInScreen - mXInView);
        int targetY = (int) (mYInScreen - mYInView);
        if(isUp){
            if(targetX <= mWindowWidth/2){
                targetX = 0;
            } else {
                targetX = (int) (mWindowWidth - mXInView);
            }
        }
        mParams.x = targetX;
        mParams.y = targetY;
        mWindowManager.updateViewLayout(this, mParams);
    }

    public void setPigWindowParams(WindowManager.LayoutParams params){
        mParams = params;
    }

    public void setPigWindowManager(WindowManager wm){
        mWindowManager = wm;
    }

    public void displayLogs(String log){

        mDisplayTextView.setText(log);
    }

    /**
     * 判断str1中包含str2的个数
     * @param str1
     * @param str2
     * @return counter
     */
    public static int countStr(String str1, String str2) {
        int counter = 0;
        if (str1.indexOf(str2) == -1) {
            return 0;
        } else if (str1.indexOf(str2) != -1) {
            counter++;
            countStr(str1.substring(str1.indexOf(str2) +
                    str2.length()), str2);
            return counter;
        }
        return 0;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
