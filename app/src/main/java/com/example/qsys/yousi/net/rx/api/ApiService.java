package com.example.qsys.yousi.net.rx.api;

import com.example.qsys.yousi.bean.BookResponse;
import com.example.qsys.yousi.bean.DaysResportResponse;
import com.example.qsys.yousi.bean.SuccessResponse;
import com.example.qsys.yousi.bean.UserResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
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
     * 通过账号注册
     *
     * @param account
     * @return
     */
    @POST("users/register")
    Observable<SuccessResponse> toRegister(@Query("mobile")String account);

    /**
     * 更新user
     *
     * @param map
     * @return
     */
    @PUT("users/{userId}")
    Observable<SuccessResponse> updateUser(@Path("userId") int userId, @QueryMap Map<String, String> map);
    /**
     * 得到所有的书籍数据
     * @return
     */
    @POST("books/getAllBook")
    Observable<BookResponse> getAllBooks();

    /**
     * 得到登录人的所有日志和读后感
     * @param userId
     * @return
     */
    @GET("report/{userId}")
    Observable<DaysResportResponse> getAllDaysReport(@Path("userId") int userId);

    /**
     * 分页请求
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GET("report/{userId}/{page}/{pageSize}")
    Observable<DaysResportResponse>  getReportByPage(@Path("userId") int userId,@Path("page") int page,@Path("pageSize") int pageSize);

    /**
     * 上传日志 或读后感
     *
     * @param title
     * @param content
     * @param userId
     * @return
     */
    @POST("report/construct")
    Observable<SuccessResponse> constructReport(@Query("book_name") String book_name, @Query("title") String title, @Query("content") String content, @Query("userId") int userId, @Query("type") int type);


}
