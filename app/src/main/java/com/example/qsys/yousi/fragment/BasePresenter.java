package com.example.qsys.yousi.fragment;

import android.support.annotation.NonNull;

/**
 * Created by hanshaokai on 2017/10/9 17:38
 */

public interface BasePresenter<T extends BaseView> {
    //初始的一些逻辑
    void start();

    //解除view的引用
    void detacheView();

    //绑定view
    void setPresenterView(@NonNull T view);
}
