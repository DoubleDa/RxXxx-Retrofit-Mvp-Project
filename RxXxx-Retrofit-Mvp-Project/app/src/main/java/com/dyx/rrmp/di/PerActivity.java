package com.dyx.rrmp.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：标注对象生命周期，设计跟随Activity的生命周期
 * create person：dayongxin
 * create time：16/8/3 上午10:25
 * alter person：dayongxin
 * alter time：16/8/3 上午10:25
 * alter remark：
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
