package com.meitu.piglogproject.cpmts.context.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.support.v4.app.Fragment;

/**
 * AbstractMvpFragment
 *
 * @author meitu at 2018/01/03.
 */

public abstract class AbstractMvpFragment<P extends IPresenter> extends Fragment implements IView {

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = onLoadPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (mPresenter != null) {
            mPresenter.subscribe();
        }
        initEventAndData();
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.unsubscribe();
        }
        super.onDestroyView();
    }

    // ===========================================================
    // Define Methods
    // ===========================================================
    protected abstract P onLoadPresenter();

    protected abstract void initViews(View view, Bundle savedInstanceState);

    protected abstract void initEventAndData();
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
