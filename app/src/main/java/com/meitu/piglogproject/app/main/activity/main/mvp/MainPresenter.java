package com.meitu.piglogproject.app.main.activity.main.mvp;

import android.content.Context;

import com.meitu.piglog.common.Pig;

/**
 * MainPresenter
 *
 * @author meitu at 2018/01/03.
 */

public class MainPresenter extends MainContract.Presenter {
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
    public void subscribe() {
        mModel = new MainModel();
    }

    @Override
    public void unsubscribe() {
        super.unsubscribe();
        mModel = null;
    }

    @Override
    public void initPig(Context context) {
        Pig.createInstance(context);
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
