package com.meitu.piglog.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.meitu.piglog.R;

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
    private Button mDragButton;
    private TextView mDisplayTextView;

    // ===========================================================
    // Constructor
    // ===========================================================
    public FloatLogView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.pig_float_view, null);
        mDragButton = (Button) findViewById(R.id.iv_pig_drag);
        mDisplayTextView = (TextView)findViewById(R.id.iv_pig_display);
    }

    // ===========================================================
    // Override Methods
    // ===========================================================

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
