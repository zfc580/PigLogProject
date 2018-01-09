package com.meitu.piglogproject.app.second.activity;

import com.meitu.piglogproject.R;
import com.meitu.piglogproject.cpmts.context.mvp.AbstractMvpActivity;
import com.meitu.piglogproject.app.second.mvp.SecondContract;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

/**
 * SecondPresenter
 *
 * @author zfc at 2018/01/08.
 */

public class SecondActivity extends AbstractMvpActivity<SecondContract.Presenter> implements SecondContract.View{
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================
    private Button mPrintButton;

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    protected SecondContract.Presenter onLoadPresenter(Bundle savedInstanceState) {
        return null;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.second_activity);
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("zhoufucai", "SecondActivity onCreate. ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("zhoufucai", "SecondActivity onStart. ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("zhoufucai", "SecondActivity onResume. ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("zhoufucai", "SecondActivity onStop. ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("zhoufucai", "SecondActivity onDestroy. ");
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
