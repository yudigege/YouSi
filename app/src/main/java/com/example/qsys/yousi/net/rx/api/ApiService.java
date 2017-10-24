package com.example.qsys.yousi.net.rx.api;

import com.example.qsys.yousi.bean.BookResponse;
import com.example.qsys.yousi.bean.DaysResportResponse;
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
    /**
     * 得到某个登录人的信息
     *
     * @param userId
     * @return
     */
    @GET("users/{userId}")
    Observable<UserResponse> getUser(@Path("userId") int userId);

    /**
     * 根据密码账号登录
     * @param account
     * @param password
     * @return
     */
    @POST("users/login")
    Observable<UserResponse> toLogin(@Query("account") String account, @Query("password") String password);

    /**
     * 得到所有的书籍数据
     * @return
     */
    @POST("books/getAllBook")
    Observable<BookResponse> getAllBooks();

    /**
     * 得到登录人的所有日志和读后感
     */
    @GET("report/{userId}")
    Observable<DaysResportResponse> getAllDaysReport(@Path("userId") int userId);


}
