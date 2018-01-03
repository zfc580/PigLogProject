package com.meitu.piglogproject.cpmts.context.mvp;

/**
 * AbstractPresenter
 *
 * @author meitu at 2018/01/03.
 */

public abstract class AbstractPresenter<T extends IView, M extends IModel> implements IPresenter<T> {

    // ===========================================================
    // Constants
    // ===========================================================
    protected static final String TAG = "BasePresenter";

    // ===========================================================
    // Fields
    // ===========================================================
    protected T mView;
    protected M mModel;

    // ===========================================================
    // Override Methods
    // ===========================================================
    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void unsubscribe() {
        mView = null;
    }

    // ===========================================================
    // Define Methods
    // ===========================================================
    public boolean isViewAttached() {
        return mView != null;
    }

    public T getView() {
        return mView;
    }

    public M getModel() {
        return mModel;
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

}
