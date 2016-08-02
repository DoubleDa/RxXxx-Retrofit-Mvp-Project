package com.dyx.rrmp.common.app;

import android.content.Context;
import android.media.AudioManager;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/2 下午3:07
 * alter person：dayongxin
 * alter time：16/8/2 下午3:07
 * alter remark：
 */
public class AppConfig {


    private Context mContext;

    private static AppConfig instance;

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }


}
