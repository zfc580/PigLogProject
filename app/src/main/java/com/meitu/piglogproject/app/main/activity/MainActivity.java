package com.meitu.piglogproject.app.main.activity;

import com.meitu.piglog.common.Pig;
import com.meitu.piglogproject.R;
import com.meitu.piglogproject.app.main.mvp.MainPresenter;
import com.meitu.piglogproject.cpmts.context.mvp.AbstractMvpActivity;
import com.meitu.piglogproject.app.main.mvp.MainContract;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

/**
 * MainPresenter
 *
 * @author zfc at 2018/01/03.
 */

public class MainActivity extends AbstractMvpActivity<MainContract.Presenter> implements MainContract.View{
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================
    private Button mShowBtn, mHidenBtn;
    private Handler mHandler = new Handler();

    // ===========================================================
    // Override Methods
    // ===========================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.initPig(MainActivity.this);
    }

    @Override
    protected MainContract.Presenter onLoadPresenter(Bundle savedInstanceState) {
        return new MainPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.main_activity);
        mShowBtn = (Button) findViewById(R.id.btn_pig_show);
        mHidenBtn = (Button)findViewById(R.id.btn_pig_hide);
        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pig.D("Pig", "分辨率：1280*720");
                Pig.D("Pig", "码率：5990400.0");
            }
        });
        mHidenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initEventAndData() {

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