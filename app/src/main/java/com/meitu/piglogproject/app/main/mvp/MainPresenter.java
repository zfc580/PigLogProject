package com.meitu.piglogproject.app.main.mvp;

import android.content.Context;

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
        //Pig.init(context);
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
