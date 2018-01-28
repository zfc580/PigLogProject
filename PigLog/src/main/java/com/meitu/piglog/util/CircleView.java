package com.meitu.piglog.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.meitu.piglog.R;

/**
 * CircleView.java
 * Useage: CircleView
 * Created by zfc<zfc@meitu.com> on 2018/1/28 - 16:40
 */
public class CircleView extends View {

    private int mCircleColor;
    private int mCircleRadius;
    private Paint mCirclePaint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleStyle);
            mCircleRadius = a.getDimensionPixelSize(R.styleable.CircleStyle_circleRadius, 0);
            mCircleColor = a.getColor(R.styleable.CircleStyle_circleColor, 0);
            a.recycle();
        }
        mCirclePaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float halfWidth = getMeasuredWidth() / 2;
        float halfHeight = getMeasuredHeight() / 2;
        mCirclePaint.setColor(mCircleColor);
        canvas.drawCircle(halfWidth, halfHeight, mCircleRadius, mCirclePaint);
    }
}
