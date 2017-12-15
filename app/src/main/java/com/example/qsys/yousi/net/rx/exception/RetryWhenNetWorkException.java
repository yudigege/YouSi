package com.example.qsys.yousi.net.rx.exception;


import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * @author hanshaokai
 * @date 2017/12/13 15:32
 */


public class RetryWhenNetWorkException implements Func1<Observable<? extends Throwable>, Observable<?>> {
    //retry 次数
    private int count = 3;
    //延迟
    private long delay = 3000;
    //叠加延迟
    private long increaseDelay = 3000;

    public RetryWhenNetWorkException() {
    }

    public RetryWhenNetWorkException(int count, long delay, long increaseDelay) {
        this.count = count;
        this.delay = delay;
        this.increaseDelay = increaseDelay;
    }

    @Override
    public Observable<?> call(Observable<? extends Throwable> observable) {
        return observable.zipWith(Observable.range(1, count + 1), new Func2<Throwable, Integer, Wrapper>() {
            @Override
            public Wrapper call(Throwable throwable, Integer integer) {
                return new Wrapper(throwable, integer);
            }
        }).flatMap(new Func1<Wrapper, Observable<?>>() {
            @Override
            public Observable<?> call(Wrapper wrapper) {
                if ((wrapper.throwable instanceof ConnectException
                        || wrapper.throwable instanceof SocketTimeoutException
                        || wrapper.throwable instanceof TimeoutException
                ) && wrapper.index < count + 1) {
//如果超出重试次数也抛出错误 否则默认进入 onCompleted
                    return Observable.timer(delay + (wrapper.index - 1) * increaseDelay, TimeUnit.MICROSECONDS);
                }
                return Observable.error(wrapper.throwable);
            }
        });
    }

    private class Wrapper {
        private int index;
        private Throwable throwable;

        public Wrapper(Throwable throwable, int index) {
            this.index = index;
            this.throwable = throwable;
        }
    }
}
