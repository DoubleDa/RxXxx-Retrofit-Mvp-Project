package com.dyx.rrmp.mylibrary.presenter;

import com.dyx.rrmp.mylibrary.view.RxView;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：抽象的Presenter层
 * create person：dayongxin
 * create time：16/8/2 上午10:35
 * alter person：dayongxin
 * alter time：16/8/2 上午10:35
 * alter remark：
 */
public interface RxPresenter<V extends RxView> {
    /**
     * 绑定view
     *
     * @param view
     */
    void attachView(V view);

    /**
     * 解绑view
     *
     * @param isHoldInstance
     */
    void dettachView(boolean isHoldInstance);
}
