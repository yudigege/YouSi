package com.example.qsys.yousi.net.rx.api;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;

/**
 * @author hanshaokai
 * @date 2017/12/13 16:01
 */


public interface HttpDownService {
    /**
     * 我把地址写到get后面  不能用@Path得用get  改成post可用但是上传的a.txt 后台收到的是a 失去了扩展名 @URL 全地址 不全 都可用 但不能加{}
     *fileName 注意与后台字段一致
     * @param start
     * @param url
     * @return
     */
    /*断点续传下载接口*/
    @Streaming/*大文件需要加入这个判断，防止下载过程中写入到内存中*/
    @POST("file/down")
    Observable<ResponseBody> downLoadFile(@Header("RANGE") String start, @Query("fileName") String bookName);
}
