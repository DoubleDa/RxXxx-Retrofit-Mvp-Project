package com.dyx.rrmp.common.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import java.io.File;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/2 下午4:41
 * alter person：dayongxin
 * alter time：16/8/2 下午4:41
 * alter remark：
 */
public class MethodsCompat {


    @TargetApi(5)
    public static void overridePendingTransition(Activity activity, int enter_anim, int exit_anim) {
        activity.overridePendingTransition(enter_anim, exit_anim);
    }

    @TargetApi(7)
    public static Bitmap getThumbnail(ContentResolver cr, long origId, int kind, BitmapFactory.Options options) {
        return MediaStore.Images.Thumbnails.getThumbnail(cr, origId, kind, options);
    }

    @TargetApi(8)
    public static File getExternalCacheDir(Context context) {
        return context.getExternalCacheDir();
    }


}
