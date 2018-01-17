package com.meitu.piglogproject.app.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.meitu.piglog.Pig;
import com.meitu.piglog.util.PLog;
import com.meitu.piglogproject.R;
import com.meitu.piglogproject.app.main.mvp.MainContract;
import com.meitu.piglogproject.app.main.mvp.MainPresenter;
import com.meitu.piglogproject.app.second.activity.SecondActivity;
import com.meitu.piglogproject.cpmts.context.mvp.AbstractMvpActivity;

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
    // Override Methods
    // ===========================================================RP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       PLog.i("zhoufucai", "MainActivity onCreate. ");
        Pig.init(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
       PLog.i("zhoufucai", "MainActivity onStart. ");
    }

    @Override
    protected void onResume() {
        super.onResume();
       PLog.i("zhoufucai", "MainActivity onResume. ");
    }

    @Override
    protected void onPause() {
        super.onPause();
       PLog.i("zhoufucai", "MainActivity onPause. ");
    }

    @Override
    protected void onStop() {
        super.onStop();
       PLog.i("zhoufucai", "MainActivity onStop. ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       PLog.i("zhoufucai", "MainActivity onDestroy. ");
        Pig.unInit();
    }

    @Override
    protected MainContract.Presenter onLoadPresenter(Bundle savedInstanceState) {
        return new MainPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.main_activity);
        Button mShowBtn = (Button) findViewById(R.id.btn_pig_show);
        Button mHidenBtn = (Button) findViewById(R.id.btn_pig_hide);
        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pig.D("分辨率：1280*720\nHello World. \nThinking In Java.");
            }
        });
        mHidenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
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
