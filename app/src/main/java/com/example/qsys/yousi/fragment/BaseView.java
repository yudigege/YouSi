package com.example.qsys.yousi.fragment;

import com.example.qsys.yousi.bean.BaseResponse;

import rx.Observable;

/**
 * Created by hanshaokai on 2017/10/9 17:51
 */

public interface BaseView {
    //void setPresenter(T presenter);

    /**
     * 此接口在RxFragement 中已实现
     * @param <T>
     * @return
     */
    <T> Observable.Transformer<T, T> bindToLifecycle();

    /**
     * 回调数据
     * @param response
     */
    void showResponseData(BaseResponse response);

    /**
     * 展示信息
     * @param smg
     */
    void showMessage(String smg);

    /**
     * 展示码
     * @param code
     */
    void showEmptyViewByCode(int code);

    /**
     * 展示是否显示动画
     * @param b
     */
    void showProgressView(Boolean b);

    /**
     * fragment是否可以更新视图
     * @return
     */
    Boolean isActive();



}