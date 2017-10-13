package com.example.qsys.yousi.net.rx.demo;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by hanshaokai on 2017/10/11 18:16
 */

public class GetCitiesCase extends UseCase<GetCitiesCase.Api> {
    interface Api {
        @GET("api/china/")
        Observable<List<City>> getCitiesCase();
    }

    //ApiClient 得到上一级的
    //compose 对自身对象处理
    public Observable<List<City>> getCities() {
        return ApiClient().getCitiesCase()
                .compose(this.<List<City>>normalSchedulers());
    }

}
