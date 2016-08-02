package com.dyx.rrmp.mylibrary.executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：Main Thread
 * create person：dayongxin
 * create time：16/8/2 上午10:58
 * alter person：dayongxin
 * alter time：16/8/2 上午10:58
 * alter remark：
 */
@Singleton
public class MainThread implements UpdateExecutionThread {

    @Inject
    public MainThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
