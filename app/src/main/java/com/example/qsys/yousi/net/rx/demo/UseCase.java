package com.example.qsys.yousi.net.rx.demo;

/**
 * Created by hanshaokai on 2017/10/11 18:03
 */

import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.extension.PagingReq;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 用于获取ApiService 的实例 然后提供了指定线程的基类方法
 *
 * @param <T>
 */
public abstract class UseCase<T> {
    //用于分页请求  分页模型
    protected PagingReq pagingReq = new PagingReq();


    protected T ApiClient() {
        return NetManager.getInstance().getRetrofit(Constant.BASE_URL).create(getType());
    }

    //subscribeOn(): 指定subscribe()订阅所发生的线程，即 call() 执行的线程。或者叫做事件产生的线程。
    //observeOn(): 指定Observer/Subscriber所运行在的线程，即onNext()执行的线程。或者叫做事件消费的线程。
    //指定观察者与被观察者线程
    protected <T> Observable.Transformer<T, T> normalSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> source) {
                //onTerminateDetach 防止解除订阅后 内存泄露
                return source.onTerminateDetach().subscribeOn(Schedulers.io())//指定subscribe()发生在IO线程
                        .observeOn(AndroidSchedulers.mainThread());// 指定Subscriber的回调发生在UI线程
                //   observeOn() 指定的是它之后的操作所在的线程。因此如果有多次切换线程的需求，
                // 只要在每个想要切换线程的位置调用一次 observeOn() 即可
                //当使用了多个 subscribeOn() 的时候，只有第一个 subscribeOn() 起作用。
                //默认情况下， doOnSubscribe() 执行在 subscribe() 发生的线程；而如果在 doOnSubscribe()
                // 之后有 subscribeOn() 的话，它将执行在离它最近的 subscribeOn() 所指定的线程。
            }
        };
    }

    private Class<T> getType() {
        Class<T> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        Type[] p = ((ParameterizedType) t).getActualTypeArguments();
        entityClass = (Class<T>) p[0];
        return entityClass;
    }
}
