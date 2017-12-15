package com.example.qsys.yousi.net.rx.download;

import com.example.qsys.yousi.common.util.LogUtils;
import com.example.qsys.yousi.net.rx.listener.DownLoadProgressListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 成功回调处理
 *
 * @author hanshaokai
 * @date 2017/12/13 16:07
 */


public class DownLoadInterceptor implements Interceptor {
    private DownLoadProgressListener loadProgressListener;

    public DownLoadInterceptor(DownLoadProgressListener loadProgressListener) {
        this.loadProgressListener = loadProgressListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Response originalResponse = chain.proceed(chain.request());
        LogUtils.d("intercept"+originalResponse.message());
        return originalResponse.newBuilder()
                .body(new DownLoadResponseBody(originalResponse.body(), loadProgressListener)).build();
    }
}
