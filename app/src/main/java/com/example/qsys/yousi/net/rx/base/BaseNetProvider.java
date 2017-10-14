package com.example.qsys.yousi.net.rx.base;


import com.example.qsys.yousi.BuildConfig;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.net.rx.exception.ApiException;

import java.io.IOException;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by hanshaokai on 2017/10/11 17:54
 */

public class BaseNetProvider implements NetProvider {

    private static final long CONNECT_TIME_OUT = 60;
    private static final long READ_TIME_OUT = 180;
    private static final long WRITE_TIME_OUT = 30;


    @Override
    public Interceptor[] configInterceptors() {
        return null;
    }

    @Override
    public void configHttps(OkHttpClient.Builder builder) {

    }

    @Override
    public CookieJar configCookie() {
        return null;
    }

    @Override
    public RequestHandler configHandler() {

        return new HeaderHandler();
    }

    @Override
    public long configConnectTimeoutSecs() {
        return CONNECT_TIME_OUT;
    }

    @Override
    public long configReadTimeoutSecs() {
        return READ_TIME_OUT;
    }

    @Override
    public long configWriteTimeoutSecs() {
        return WRITE_TIME_OUT;
    }

    @Override
    public boolean configLogEnable() {
        return BuildConfig.DEBUG;
    }


    private class HeaderHandler implements RequestHandler {

        @Override
        public Request onBeforeRequest(Request request, Interceptor.Chain chain) {
            return chain.request().newBuilder()
                    .addHeader("X-Auth-Token", Constant.accessToken)
                    .addHeader("Authorization", "")
                    .build();
        }

        @Override
        public Response onAfterRequest(Response response, Interceptor.Chain chain)
                throws IOException {
            ApiException e = null;
            if (401 == response.code()) {
                throw new ApiException("登录已过期,请重新登录!");
            } else if (403 == response.code()) {
                throw new ApiException("禁止访问!");
            } else if (404 == response.code()) {
                throw new ApiException("链接错误");
            } else if (503 == response.code()) {
                throw new ApiException("服务器升级中!");
            } else if (500 == response.code()) {
                throw new ApiException("服务器内部错误!");
            }
            return response;
        }
    }
}
