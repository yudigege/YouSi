package com.example.qsys.yousi.fragment.initlogin.readylogin;

import com.example.qsys.yousi.fragment.BasePresenter;

/**
 * @author hanshaokai
 * @date 2017/10/17 17:27
 */
public abstract class AbstractReadyLoginPresenter extends BasePresenter {


    /**
     * 执行登录操作
     *
     * @param count
     * @param password
     */
    abstract  void toLogin(String count, String password);

}
