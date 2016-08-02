package com.dyx.rrmp.mylibrary.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：Work Thread
 * create person：dayongxin
 * create time：16/8/2 上午11:02
 * alter person：dayongxin
 * alter time：16/8/2 上午11:02
 * alter remark：
 */
@Singleton
public class WorkThread implements ThreadExecutor {
    //Init Thread Pool Size
    private static final int INIT_POOL_SIZE = 3;
    //Max Thread Pool Size
    private static final int MAX_POOL_SIZE = 5;
    //Sets The Amount Of Time An Idle Thread Waits Before Terminating
    private static final int KEEP_ALIVE_TIME = 10;
    //Sets The Time Unit To Seconds
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    //Work Queue
    private final BlockingQueue<Runnable> workQueue;
    //Thread Pool Executor
    private final ThreadPoolExecutor threadPoolExecutor;
    //Thread Factory
    private final ThreadFactory threadFactory;

    @Inject
    public WorkThread() {
        this.workQueue = new LinkedBlockingQueue<>();
        this.threadFactory = new WorkThreadFactory();
        this.threadPoolExecutor = new ThreadPoolExecutor(INIT_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, this.workQueue, this.threadFactory);
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("Runnable to execute cannot be null");
        }
        this.threadPoolExecutor.execute(runnable);
    }

    private class WorkThreadFactory implements ThreadFactory {
        private static final String THREAD_NAME = "android_";
        private int counter = 0;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, THREAD_NAME + counter);
        }
    }
}
