package com.dyx.rrmp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * project name：RxXxx-Retrofit-Mvp-Project
 * class describe：
 * create person：dayongxin
 * create time：16/8/1 下午10:35
 * alter person：dayongxin
 * alter time：16/8/1 下午10:35
 * alter remark：
 */
public interface MyService {
    @GET("oauth2/reg")
    Call<RegModel> reg(@Query("client_id") String userClientId, @Query("client_secret") String userClientSecret, @Query("email") String userEmail, @Query("account") String userAccount, @Query("password") String userPassword);
}
