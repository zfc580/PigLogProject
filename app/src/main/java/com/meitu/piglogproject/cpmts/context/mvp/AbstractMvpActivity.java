package com.meitu.piglogproject.cpmts.context.mvp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * AbstractMvpActivity
 *
 * @author meitu at 2018/01/03.
 */

public abstract class AbstractMvpActivity<P extends IPresenter> extends FragmentActivity implements IView {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================
    protected P mPresenter;

    // ===========================================================
    // Override Methods
    // ===========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews(savedInstanceState);
        mPresenter = onLoadPresenter(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
            mPresenter.subscribe();
        }
        initEventAndData();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
        super.onDestroy();
    }

    // ===========================================================
    // Define Methods
    // ===========================================================
    protected abstract P onLoadPresenter(Bundle savedInstanceState);

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initEventAndData();
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
