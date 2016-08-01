package com.dyx.rrmp.rx;

import com.dyx.rrmp.RegModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/1 下午11:07
 * alter person：dayongxin
 * alter time：16/8/1 下午11:07
 * alter remark：
 */
public interface MyService {
    @GET("oauth2/reg")
    Observable<RegModel> reg(
            @Query("client_id") String client_id,
            @Query("client_secret") String client_secret,
            @Query("email") String email,
            @Query("account") String account,
            @Query("password") String password
    );
}
