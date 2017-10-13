package com.example.qsys.yousi.net.rx.base;
import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
/**
 * Created by hanshaokai on 2017/10/11 17:50
 */

public interface NetProvider {
    Interceptor[] configInterceptors();

    void configHttps(OkHttpClient.Builder builder);

    CookieJar configCookie();

    RequestHandler configHandler();

    long configConnectTimeoutSecs();

    long configReadTimeoutSecs();

    long configWriteTimeoutSecs();

    boolean configLogEnable();
}
