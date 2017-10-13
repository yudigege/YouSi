package com.example.qsys.yousi.fragment.initlogin.readylogin;

import com.example.qsys.yousi.bean.UserResponse;
import com.example.qsys.yousi.fragment.BasePresenter;
import com.example.qsys.yousi.fragment.BaseView;

import rx.Observable;

/**
 * Created by hanshaokai on 2017/10/9 17:59
 */
//将view控制部分 逻辑操作部分 协议
public class ReadyLoginContract {

    //baseFragment 不在一个包内这里定义公共用法
    public interface View extends BaseView {
        //此接口在RxFragement 中已实现
        <T> Observable.Transformer<T, T> bindToLifecycle();
        //展示是否显示动画
        void showProgressView(Boolean b);
        void showUserData(UserResponse user);
    }

    public interface Presenter extends BasePresenter {
        //执行登录操作
        void toLogin(String count, String password);
    }

}
