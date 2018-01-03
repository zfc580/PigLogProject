package com.meitu.piglogproject.cpmts.context.mvp;

/**
 * IPresenter
 *
 * @author meitu at 2018/01/03.
 */

public interface IPresenter<T extends IView> {

    void attachView(T view);

    void subscribe();

    void unsubscribe();
}
