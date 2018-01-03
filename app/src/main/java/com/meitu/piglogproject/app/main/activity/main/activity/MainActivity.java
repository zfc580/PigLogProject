package com.meitu.piglogproject.app.main.activity.main.activity;

import com.meitu.piglogproject.R;
import com.meitu.piglogproject.app.main.activity.main.mvp.MainPresenter;
import com.meitu.piglogproject.cpmts.context.mvp.AbstractMvpActivity;
import com.meitu.piglogproject.app.main.activity.main.mvp.MainContract;
import android.os.Bundle;

/**
 * MainPresenter
 *
 * @author meitu at 2018/01/03.
 */

public class MainActivity extends AbstractMvpActivity<MainContract.Presenter> implements MainContract.View{
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Override Methods
    // ===========================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.initPig(this);
    }

    @Override
    protected MainContract.Presenter onLoadPresenter(Bundle savedInstanceState) {
        return new MainPresenter();
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.main_activity);
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
