package com.meitu.piglogproject.app.main.mvp;

import com.meitu.piglogproject.cpmts.context.mvp.AbstractPresenter;
import com.meitu.piglogproject.cpmts.context.mvp.IModel;
import com.meitu.piglogproject.cpmts.context.mvp.IView;

/**
 * SecondContract
 *
 * @author zfc at 2018/01/08.
 */

public class SecondContract {

    public interface View extends IView {

    }

    public static abstract class Presenter extends AbstractPresenter<View, Model>{

    }

    public interface Model extends IModel {

    }
}
