package com.meitu.piglog.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
    private RelativeLayout mLogPanelLayout;
    private TextView mDragView;
    private TextView mDisplayTextView;
    private Button mCloseButton;

    private float xInScreen;
    private float yInScreen;
    private float xDownInScreen;
    private float yDownInScreen;
    private float xInView;
    private float yInView;
    private int statusBarHeight;
    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;

    // ===========================================================
    // Constructor
    // ===========================================================
    public FloatLogView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.pig_float_view, this);
        mLogPanelLayout = (RelativeLayout)findViewById(R.id.pig_float_panel);
        mDragView = (TextView) findViewById(R.id.tv_float_drag);
        mDisplayTextView = (TextView)findViewById(R.id.tv_float_display);
        mCloseButton = (Button) findViewById(R.id.btn_float_close);
        mDragView.setOnClickListener(this);
        mCloseButton.setOnClickListener(this);

    }

    // ===========================================================
    // Override Methods
    // ===========================================================


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_float_close){
            mLogPanelLayout.setVisibility(GONE);
            mDragView.setVisibility(VISIBLE);

        } else if(v.getId() == R.id.tv_float_drag){
            mLogPanelLayout.setVisibility(VISIBLE);
            mDragView.setVisibility(GONE);
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                xInView = event.getX();
                yInView = event.getY();
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY() - getStatusBarHeight();
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();

                break;
            case MotionEvent.ACTION_MOVE:
                xInScreen = event.getRawX();
                yInScreen = event.getRawY() - getStatusBarHeight();
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP:
                break;

        }
        return super.dispatchTouchEvent(event);
    }

    // ===========================================================
    // Define Methods
    // ===========================================================
    private int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

    private void updateViewPosition() {
        mParams.x = (int) (xInScreen - xInView);
        mParams.y = (int) (yInScreen - yInView);
        mWindowManager.updateViewLayout(this, mParams);
    }

    public void setPigWindowParams(WindowManager.LayoutParams params){
        mParams = params;
    }

    public void setPigWindowManager(WindowManager wm){
        mWindowManager = wm;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================


    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
