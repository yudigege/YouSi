package com.example.qsys.yousi.net.rx.api;

import com.example.qsys.yousi.bean.BookResponse;
import com.example.qsys.yousi.bean.DaysResportResponse;
import com.example.qsys.yousi.bean.RecordTrackResponse;
import com.example.qsys.yousi.bean.SearchKeyWordsItemResponse;
import com.example.qsys.yousi.bean.SuccessResponse;
import com.example.qsys.yousi.bean.TrackDayResponse;
import com.example.qsys.yousi.bean.UserResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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
     * 上传头像图片 表单传输 后台按照表单接受
     *
     * @param
     * @param avatar
     * @param userIdBody
     */
    @POST("users/avatar")/*@Part("userId") RequestBody userId,*/
    @Multipart
    Observable<SuccessResponse> upLoadeAvatar(@Part  MultipartBody.Part avatar, @Part MultipartBody.Part userIdBody);
    //我的情况是混合传递，文件加文字类型 ("pic\"; filename=\"avatar.png\"") RequestBody file):pic是字段名，上传的文件名称为avatar.png，以RequestBody 形式上传

    /**
     * 得到所有的书籍数据
     * @return
     */
    @POST("books/getAllBook")
    Observable<BookResponse> getAllBooks();

    /**
     * 得到所有的文本名字
     *
     * @return
     */
    @POST("file")
    Observable<BookResponse> getAllFileName();
    /**
     * 得到登录人的所有日志和读后感
     * @param userId
     * @return
     */
    @GET("report/{userId}")
    Observable<DaysResportResponse> getAllDaysReport(@Path("userId") int userId);

    /**
     * 根据关键词搜索所属登录人的日志
     *
     * @param userId
     * @param keyWords
     * @return
     */
    @GET("report/{userId}/{keyWords}")
    Observable<DaysResportResponse> getAllDaysReport(@Path("userId") int userId, @Path("keyWords") String keyWords);

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
    Observable<SuccessResponse> constructReport(
            @Query("record_consume_start_time") Long record_consume_start_time
            ,@Query("record_consume_end_time") Long record_consume_end_time
            , @Query("book_name") String book_name, @Query("title") String title, @Query("content") String content
            , @Query("userId") int userId, @Query("type") int type);
    /**
     * 获得搜索频率在前十位的关键词
     */
    @GET("searchrecord/search")
    Observable<SearchKeyWordsItemResponse> getLimitToIndexSearchItem();

    /**
     * 得到轨迹总表数据
     */
    @GET("recordtrack/{userId}")
    Observable<RecordTrackResponse> getRecordTrackData(@Path("userId") int userId);

/**
 * 得到每天轨迹 前七天数据
 */
    /**
     * 得到轨迹总表数据
     */
    @GET("trackday/{userId}")
    Observable<TrackDayResponse> getTrackDayData(@Path("userId") int userId);

}
