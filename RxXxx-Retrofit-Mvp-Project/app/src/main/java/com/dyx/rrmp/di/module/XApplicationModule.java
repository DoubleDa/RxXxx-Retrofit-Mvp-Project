package com.dyx.rrmp.di.module;

import android.app.Application;
import android.content.Context;

import com.dyx.rrmp.mylibrary.executor.MainThread;
import com.dyx.rrmp.mylibrary.executor.ThreadExecutor;
import com.dyx.rrmp.mylibrary.executor.UpdateExecutionThread;
import com.dyx.rrmp.mylibrary.executor.WorkThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 上午10:35
 * alter person：dayongxin
 * alter time：16/8/3 上午10:35
 * alter remark：
 */
@Module
public class XApplicationModule {
    private Application context;

    public XApplicationModule(Application context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(WorkThread workThread) {
        return workThread;
    }

    @Provides
    @Singleton
    UpdateExecutionThread provideUpdateExecutionThread(MainThread mainThread) {
        return mainThread;
    }
}
