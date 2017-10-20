package com.example.qsys.yousi.net.rx.api;

import com.example.qsys.yousi.bean.BookResponse;
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
    //得到某个登录人的信息
    @GET("users/{userId}")
    Observable<UserResponse> getUser(@Path("userId") int userId);
//根据密码账号登录
    @POST("users/login")
    Observable<UserResponse> toLogin(@Query("account") String account, @Query("password") String password);
    //得到所有的书籍数据
    @POST("books/getAllBook")
    Observable<BookResponse> getAllBooks();


}
