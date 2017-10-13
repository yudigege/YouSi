package com.example.qsys.yousi.net.rx.base;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
 * Created by hanshaokai on 2017/10/11 17:45
 */

public interface RequestHandler {
    Request onBeforeRequest(Request request, Interceptor.Chain chain);

    Response onAfterRequest(Response response, Interceptor.Chain chain) throws IOException;
}
