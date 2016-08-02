package com.dyx.rrmp.mylibrary.interactors;

import com.dyx.rrmp.mylibrary.executor.ThreadExecutor;
import com.dyx.rrmp.mylibrary.executor.UpdateExecutionThread;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：Abstract class for a Use Case
 * create person：dayongxin
 * create time：16/8/2 上午10:39
 * alter person：dayongxin
 * alter time：16/8/2 上午10:39
 * alter remark：
 */
public abstract class RxInteractor {
    //Declare Thread Executor
    private ThreadExecutor mThreadExecutor;
    //Declare Update Main Thread
    private UpdateExecutionThread mUpdateExecutionThread;
    //Declare Subscription
    private Subscription mSubscription = Subscriptions.empty();

    /**
     * No Parameter Constructor
     */
    public RxInteractor() {
    }

    /**
     * Have Parameter Constructor
     *
     * @param mThreadExecutor
     * @param mUpdateExecutionThread
     */
    protected RxInteractor(ThreadExecutor mThreadExecutor, UpdateExecutionThread mUpdateExecutionThread) {
        this.mThreadExecutor = mThreadExecutor;
        this.mUpdateExecutionThread = mUpdateExecutionThread;
    }

    /**
     * Init RxInteractor
     *
     * @param mThreadExecutor
     * @param mUpdateExecutionThread
     */
    protected void initRxInteractor(ThreadExecutor mThreadExecutor, UpdateExecutionThread mUpdateExecutionThread) {
        // FIXME 不是有构造方法了么 为什么还要用这个方法
        this.mThreadExecutor = mThreadExecutor;
        this.mUpdateExecutionThread = mUpdateExecutionThread;
    }

    /**
     * Executes the current use case
     *
     * @param observable
     * @param subscriber
     * @param tClass
     * @param <T>
     */
    protected <T> void execute(Observable observable, Subscriber subscriber, Class<T> tClass) {
        if (mThreadExecutor == null) {
            throw new RuntimeException("ThreadExecutor Uninited!");
        }
        this.mSubscription = filterObservable(observable, tClass).subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mUpdateExecutionThread.getScheduler())
                .subscribe(subscriber);
    }

    /**
     * Executes the current use case
     *
     * @param observable
     * @param subscriber
     * @param <T>
     */
    protected <T> void execute(Observable observable, Subscriber subscriber) {
        if (mThreadExecutor == null) {
            throw new RuntimeException("ThreadExecutor Uninited!");
        }
        this.mSubscription = filterObservable(observable).subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mUpdateExecutionThread.getScheduler())
                .subscribe(subscriber);
    }

    /**
     * @param observable
     * @return
     */
    protected abstract Observable filterObservable(Observable observable);

    /**
     * Filter Observable
     *
     * @param observable
     * @param tClass
     * @param <T>
     * @return
     */
    protected abstract <T> Observable filterObservable(Observable observable, Class<T> tClass);

    /**
     * 解除订阅
     */
    public void unSubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
