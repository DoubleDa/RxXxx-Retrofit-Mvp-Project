package com.dyx.rrmp.mylibrary.interactors;

import com.dyx.rrmp.mylibrary.exception.NetworkUnavailableException;
import com.dyx.rrmp.mylibrary.exception.UserLoginInvalidException;
import com.dyx.rrmp.mylibrary.view.RxView;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：Common Subscriber
 * create person：dayongxin
 * create time：16/8/2 上午11:41
 * alter person：dayongxin
 * alter time：16/8/2 上午11:41
 * alter remark：
 */
public class CommonSubscriber<T> extends rx.Subscriber<T> {
    //Declare RxView
    private RxView mRxView;

    /**
     * Constructor Method
     *
     * @param rxView
     */

    private CommonSubscriber(RxView rxView) {
        this.mRxView = rxView;
    }

    @Override
    public void onCompleted() {
        mRxView.dismissLoding();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof UserLoginInvalidException) {
            mRxView.showLoginLayout();
        } else if (e instanceof NetworkUnavailableException) {
            mRxView.showNetworkUnavailable();
        } else {
            mRxView.showTip(e.getMessage());
        }
        mRxView.dismissLoding();
    }

    @Override
    public void onNext(T t) {

    }
}
