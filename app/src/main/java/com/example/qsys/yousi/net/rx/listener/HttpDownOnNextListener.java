package com.example.qsys.yousi.net.rx.listener;

/**
 * 下载过程中的回调处理
 *
 * @author hanshaokai
 * @date 2017/12/13 16:26
 */


public abstract class HttpDownOnNextListener<T>{
    /**
     * 成功回调方法
     *
     * @param t
     */

    public abstract void onNext(T t);

    public abstract void onStart();

    public abstract void onComplete();

    /**
     * 下载进度
     */
    public abstract void updateProgress(long readLength, long countLength);

    public void onError(Throwable e) {

    }

    public void onPuase() {

    }

    public void onStop() {
    }
}
