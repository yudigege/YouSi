package com.example.qsys.yousi.fragment.initlogin.readylogin;

import com.example.qsys.yousi.fragment.BasePresenter;

/**
 * Created by hanshaokai on 2017/10/17 17:27
 */

public abstract class ReadyLoginPresenter extends BasePresenter {


    //执行登录操作
    abstract  void toLogin(String count, String password);

}
