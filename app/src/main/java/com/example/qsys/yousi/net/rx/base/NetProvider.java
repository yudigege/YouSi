package com.example.qsys.yousi.net.rx.base;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
/**
 * Created by hanshaokai on 2017/10/11 17:50
 */

public interface NetProvider {
    /**
     * 过滤器参数
     * @return
     */
    Interceptor[] configInterceptors();

    /**
     * 连接参数
     * @param builder
     */
    void configHttps(OkHttpClient.Builder builder);

    /**
     * cookie 参数
     * @return
     */
    CookieJar configCookie();

    /**
     * handler参数
     * @return
     */
    RequestHandler configHandler();

    /**
     * 链接时间
     * @return
     */
    long configConnectTimeoutSecs();

    /**
     * 读取时间
     * @return
     */
    long configReadTimeoutSecs();

    /**
     * 写入时间
     * @return
     */
    long configWriteTimeoutSecs();

    /**
     * 是否log 配置
     * @return
     */
    boolean configLogEnable();
}
