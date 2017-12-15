package com.example.qsys.yousi.net.rx.subscribers;

import com.example.qsys.yousi.common.util.LogUtils;
import com.example.qsys.yousi.db.GreenDaoManager;
import com.example.qsys.yousi.net.rx.download.DownInfo;
import com.example.qsys.yousi.net.rx.download.DownState;
import com.example.qsys.yousi.net.rx.listener.DownLoadProgressListener;
import com.example.qsys.yousi.net.rx.listener.HttpDownOnNextListener;
import com.example.qsys.yousi.net.rx.manager.NetManager;

import java.lang.ref.SoftReference;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 断点下载处理类Subscriber
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http 请求结束时关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 *
 * @author hanshaokai
 * @date 2017/12/14 9:59
 */


public class ProgressDownSubscriber<T> extends Subscriber<T> implements DownLoadProgressListener {

    //弱引用结果回调
    private SoftReference<HttpDownOnNextListener> mSubscriberOnNextListener;
    //下载数据
    private DownInfo downInfo;

    public ProgressDownSubscriber(DownInfo downInfo) {
        this.mSubscriberOnNextListener = new SoftReference<>(downInfo.getListener());
        this.downInfo = downInfo;
    }

    public void setDownInfo(DownInfo downInfo) {
        this.mSubscriberOnNextListener = new SoftReference<HttpDownOnNextListener>(downInfo.getListener());
        this.downInfo = downInfo;
    }

    /**
     * 订阅开始时调用 显示ProgressDialog
     */
    @Override
    public void onStart() {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onStart();
        }
        downInfo.setState(DownState.START);
    }


    /**
     * @param read
     * @param count
     * @param done
     */
    @Override
    public void update(long read, long count, final boolean done) {
        if(downInfo.getCountLength()>count){
            read=downInfo.getCountLength()-count+read;
        }else{
            downInfo.setCountLength(count);
        }
        downInfo.setReadLength(read);
        if (mSubscriberOnNextListener.get() != null) {
            /*接受进度消息造成UI阻塞如果不需要显示进度可去掉实现逻辑减少压力*/
            rx.Observable.just(read).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            /*如果暂停或停止状态延迟，不需要继续发送回调，影响显示*/
                            if (downInfo.getState() == DownState.PAUSE
                                    || downInfo.getState() == DownState.STOP) {
                                return;
                            }
                            LogUtils.d("获取数据" + aLong);
                            downInfo.setState(DownState.DOWN);
                            mSubscriberOnNextListener.get().updateProgress(aLong, downInfo.getCountLength());
                        }
                    });
        }
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onComplete();
        }
        NetManager.getInstance().remove(downInfo);
        downInfo.setState(DownState.ERROR);
        GreenDaoManager.getInstance().update(downInfo);
    }

    /**
     * 将onNext 方法中的返回结果交给activity 或Fragment 自己处理
     *
     * @param t 创建Subscriber 时的泛型类型
     */

    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onNext(t);
        }
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */

    @Override
    public void onError(Throwable e) {
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onError(e);
        }
        NetManager.getInstance().remove(downInfo);
        downInfo.setState(DownState.ERROR);
        GreenDaoManager.getInstance().update(downInfo);
    }
}
