package com.example.qsys.yousi.net.rx.manager;

import android.support.annotation.NonNull;

import com.example.qsys.yousi.net.rx.base.RequestHandler;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by hanshaokai on 2017/10/11 17:32
 */

public class NetInterceptor implements Interceptor {

    private RequestHandler handler;

    public NetInterceptor(RequestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        if (handler != null) {
            request = handler.onBeforeRequest(request, chain);
        }
        Response response = chain.proceed(request);
        if (handler != null) {
            Response tmp = handler.onAfterRequest(response, chain);
            if (tmp != null) {
                return tmp;
            }
        }
        return response;
    }










}
