package com.example.qsys.yousi.fragment;

/**
 * Created by hanshaokai on 2017/10/9 17:51
 */

public interface BaseView {
    //void setPresenter(T presenter);
    void showMessage(String smg);

    //fragment是否可以更新视图
    Boolean isActive();
}