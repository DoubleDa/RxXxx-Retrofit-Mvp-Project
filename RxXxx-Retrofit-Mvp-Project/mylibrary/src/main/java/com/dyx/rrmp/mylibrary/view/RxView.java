package com.dyx.rrmp.mylibrary.view;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：抽象公用View接口
 * create person：dayongxin
 * create time：16/8/2 上午10:23
 * alter person：dayongxin
 * alter time：16/8/2 上午10:23
 * alter remark：
 */
public interface RxView {
    /**
     * 显示提示信息
     *
     * @param msg
     */
    void showTip(String msg);

    /**
     * 显示提示信息
     *
     * @param msgId
     */
    void showTip(int msgId);

    /**
     * 显示登录框
     */
    void showLoginLayout();

    /**
     * 提示网络无法访问
     */
    void showNetworkUnavailable();

    /**
     * 显示loding
     */
    void showLoding();

    /**
     * 隐藏loding
     */
    void dismissLoding();
}
