package com.dyx.rrmp.common.app;

import android.graphics.Bitmap;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/2 下午4:45
 * alter person：dayongxin
 * alter time：16/8/2 下午4:45
 * alter remark：
 */
public class ApiClient {

    public static final String UTF_8 = "UTF-8";
    public static final String DESC = "descend";
    public static final String ASC = "ascend";

    private final static int TIMEOUT_CONNECTION = 8000;
    private final static int TIMEOUT_SOCKET = 8000;
    private final static int RETRY_TIME = 2;

    private static String appCookie;
    private static String appUserAgent;

    public static void cleanCookie() {
        appCookie = "";

    }


    public static Bitmap getNetBitmap(String url) {
        return null;
    }
}
