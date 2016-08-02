package com.dyx.rrmp.common.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：Activity Manager Class
 * create person：dayongxin
 * create time：16/8/2 下午1:27
 * alter person：dayongxin
 * alter time：16/8/2 下午1:27
 * alter remark：
 */
public class AppManager {
    //Declare Storage Activity's Stack
    private static Stack<Activity> activityStack;
    //Declare ActivityManager Object
    private static AppManager instance;

    /**
     * Constructor
     */
    private AppManager() {
    }

    /**
     * Get ActivityManager Instance
     *
     * @return
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * Add Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * Get Current Activity
     *
     * @return
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * Finish Activity
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * Finish Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * Finish Activity
     *
     * @param cla
     */
    public void finishActivity(Class<?> cla) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cla)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * Finish All Activity
     */
    public void finishAllActivity() {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * App Exit
     *
     * @param context
     */
    public void AppExit(Context context) {
        finishAllActivity();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        manager.restartPackage(context.getPackageName());
        System.exit(0);
    }
}
