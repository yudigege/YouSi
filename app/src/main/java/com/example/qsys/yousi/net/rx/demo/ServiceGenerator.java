package com.example.qsys.yousi.net.rx.demo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hanshaokai on 2017/10/10 17:23
 */

public class ServiceGenerator {

    private static final String BASE_URL = "http://116.196.109.167:8080/education/";
    private static Retrofit retrofit;
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        //确保不多次添加拦截器
        if (!httpClient.interceptors().contains(logging)) {
            httpClient.addInterceptor(logging);
        }
        //设置OKHttpClient,如果不设置会提供一个默认的
        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }


}
