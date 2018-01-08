package com.meitu.piglogproject.app.main.mvp;

import android.content.Context;

import com.meitu.piglogproject.cpmts.context.mvp.AbstractPresenter;
import com.meitu.piglogproject.cpmts.context.mvp.IModel;
import com.meitu.piglogproject.cpmts.context.mvp.IView;

/**
 * MainContract
 *
 * @author meitu at 2018/01/03.
 */

public class MainContract {

    public interface View extends IView {

    }

    public static abstract class Presenter extends AbstractPresenter<View, Model>{
        public abstract void initPig(Context context);
    }

    public interface Model extends IModel {
        String getMessage();
    }
}
