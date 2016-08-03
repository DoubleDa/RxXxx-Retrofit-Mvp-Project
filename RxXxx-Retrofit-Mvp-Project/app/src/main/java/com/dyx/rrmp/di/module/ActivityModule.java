package com.dyx.rrmp.di.module;

import android.app.Activity;

import com.dyx.rrmp.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 上午10:32
 * alter person：dayongxin
 * alter time：16/8/3 上午10:32
 * alter remark：
 */
@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}
