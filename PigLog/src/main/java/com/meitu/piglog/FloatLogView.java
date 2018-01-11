package com.meitu.piglog;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * FloatLogView.java
 * Useage: FloatLogView
 * Created by zfc<zfc@meitu.com> on 2018/1/3 - 11:25
 */
public class FloatLogView extends RelativeLayout {

    // ===========================================================
    // Constants
    // ===========================================================


    // ===========================================================
    // Fields
    // ===========================================================
    private TextView mDisplayTextView;
    private float mXInScreen;
    private float mYInScreen;
    private float mXInView;
    private float mYInView;
    private float mStartX;
    private float mStartY;
    private int mWindowWidth;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;

    // ===========================================================
    // Constructor
    // ===========================================================
    FloatLogView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.pig_float_view, this);
        mDisplayTextView = (TextView)findViewById(R.id.tv_float_display);
    }

    // ===========================================================
    // Override Methods
    // ===========================================================
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Rect appRect = new Rect();
        getWindowVisibleDisplayFrame(appRect);
        int mStatusBarHeight = appRect.top;
        mWindowWidth = appRect.right;
        mXInScreen = event.getRawX();
        mYInScreen = event.getRawY() - mStatusBarHeight;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mXInView = event.getX();
                mYInView = event.getY();
                mStartX = mXInScreen;
                mStartY = mYInScreen;

                break;
            case MotionEvent.ACTION_MOVE:
                updateViewPosition(false);
                break;

            case MotionEvent.ACTION_UP:
                updateViewPosition(true);
                if(mXInScreen - mStartX < 2 && mYInScreen - mStartY < 2){
                    performClickAction();
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    // ===========================================================
    // Define Methods
    // ===========================================================
    private void performClickAction(){
        if(mDisplayTextView.getVisibility() == VISIBLE){
            mDisplayTextView.setVisibility(GONE);
        } else {
            mDisplayTextView.setVisibility(VISIBLE);
        }
    }

    private void updateViewPosition(boolean isUp) {
        int targetX = (int) (mXInScreen - mXInView);
        int targetY = (int) (mYInScreen - mYInView);
        if(isUp){
            if(targetX + getWidth()/2 <= mWindowWidth/2){
                targetX = 0;
            } else {
                targetX = (int) (mWindowWidth - mXInView);
            }
        }
        mParams.x = targetX;
        mParams.y = targetY;
        mWindowManager.updateViewLayout(this, mParams);
    }

    protected void setPigWindowParams(WindowManager.LayoutParams params){
        mParams = params;
    }

    protected void setPigWindowManager(WindowManager wm){
        mWindowManager = wm;
    }

    protected void displayLogs(final String log){
        mDisplayTextView.post(new Runnable() {
            @Override
            public void run() {
                mDisplayTextView.setText(log);
            }
        });
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
