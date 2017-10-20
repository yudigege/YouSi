package com.example.qsys.yousi.fragment;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by hanshaokai on 2017/10/9 17:38
 */

public abstract class BasePresenter<T extends BaseView> {
    //定义弱引用
    WeakReference<BaseView> weakRefView = null;
    //初始的一些逻辑
    public abstract void start();
    //绑定view
    public void detacheView() {
        weakRefView.clear();
        weakRefView = null;
    }

    //持有fragment引用 执行回调
    public void setPresenterView(@NonNull BaseView tasksView) {
        //这里view不能为空 不在判断是否为空
        weakRefView = new WeakReference(checkNotNull(tasksView, "tasksView cannot be null!"));
    }

    public BaseView getBindView() {
        return weakRefView.get();

    }
}
