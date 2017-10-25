package com.example.qsys.yousi.net.rx.demo;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by hanshaokai on 2017/10/11 18:16
 */

public class GetCitiesCase extends AbstractUseCase<GetCitiesCase.Api> {

    interface Api {
        @GET("api/china/")
        /**
         * 得到数据
         */
        Observable<List<City>> getCitiesCase();
    }

    /**
     * compose 对自身对象处理
     * @return
     */
    public Observable<List<City>> getCities() {
        return apiClient().getCitiesCase()
                .compose(this.<List<City>>normalSchedulers());
    }

}
