package com.dyx.rrmp.di.component;

import com.dyx.rrmp.di.module.CacheModule;
import com.dyx.rrmp.di.module.XApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/3 上午10:34
 * alter person：dayongxin
 * alter time：16/8/3 上午10:34
 * alter remark：
 */
@Singleton
@Component(modules = {XApplicationModule.class, CacheModule.class})
public class XApplicationComponent {

}
