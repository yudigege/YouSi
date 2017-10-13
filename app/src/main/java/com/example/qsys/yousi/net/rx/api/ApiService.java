package com.example.qsys.yousi.net.rx.api;

import com.example.qsys.yousi.bean.UserResponse;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by hanshaokai on 2017/10/12 16:37
 */

public interface ApiService {
    @GET("users/{userId}")
    Observable<UserResponse> getUser(@Path("userId") int userId);

    @POST("users/login")
    Observable<UserResponse> toLogin(@Query("mobile") String mobile, @Query("mobile") String moblie);
}
