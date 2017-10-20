package com.example.qsys.yousi.fragment;

import com.example.qsys.yousi.bean.BaseResponse;

import rx.Observable;

/**
 * Created by hanshaokai on 2017/10/9 17:51
 */

public interface BaseView {
    //void setPresenter(T presenter);
    //此接口在RxFragement 中已实现
    <T> Observable.Transformer<T, T> bindToLifecycle();
    void showResponseData(BaseResponse response);
    void showMessage(String smg);
    //展示是否显示动画
    void showProgressView(Boolean b);
    //fragment是否可以更新视图
    Boolean isActive();
}