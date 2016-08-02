package com.dyx.rrmp.common.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/2 下午2:23
 * alter person：dayongxin
 * alter time：16/8/2 下午2:23
 * alter remark：
 */
public class CloseUtils {
    /**
     * Close Closeable Object
     *
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
