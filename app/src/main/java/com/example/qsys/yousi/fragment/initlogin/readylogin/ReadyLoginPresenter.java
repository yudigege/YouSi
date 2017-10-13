package com.example.qsys.yousi.fragment.initlogin.readylogin;

import android.support.annotation.NonNull;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.UserResponse;
import com.example.qsys.yousi.fragment.BaseView;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;
import com.example.qsys.yousi.net.rx.manager.RxSubscriber;

import java.lang.ref.WeakReference;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by hanshaokai on 2017/10/9 17:39
 */

public class ReadyLoginPresenter implements ReadyLoginContract.Presenter {
    //相应fragment 对应的的具体presenter
    //定义弱引用
    WeakReference<ReadyLoginContract.View> weakRefView = null;

    //初始操作
    @Override
    public void start() {

    }

    //登录网络请求
    @Override
    public void toLogin(String account, String password) {

        //防止内存泄露 绑定生命周期 RxFragment 继承 RxFragement 实现
        //内部类为订阅者 即接受源 Subscriber实现了Observer接口，比Observer多了一个最重要的方法unsubscribe( )，
        // 用来取消订阅，当你不再想接收数据了，可以调用unsubscribe( )方法停止接收，
        // Observer 在 subscribe() 过程中,最终也会被转换成 Subscriber 对象，一般情况下，建议使用Subscriber作为接收源；
        //订阅观察者（其实是观察者订阅被观察者）
        //compose 用于Obverseble 对自身处理
        //observeOn() 指定的是它之后的操作所在的线程。因此如果有多次切换线程的需求，只要在每个想要切换线程的位置调用一次 observeOn() 即可
       /* new GetCitiesCase().getCities()
                .compose(this.weakRefView.get().<List<City>>bindToLifecycle())
                .subscribe(new BaseSubscriber<List<City>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<City> cities) {

                    }
                });*/
        //耗时过程中fragment有可能销毁 耗时完后更改视图要判断是否可用  开启子线程前不用判断
        NetManager.getInstance().getApiService().toLogin(account, password).compose(RxSchedulers.<UserResponse>io_main())
                .compose(this.weakRefView.get().<UserResponse>bindToLifecycle())
                .subscribe(new RxSubscriber<UserResponse>() {
                    @Override
                    protected void _onNext(UserResponse userResponse) {
                        weakRefView.get().showProgressView(false);
                        weakRefView.get().showUserData(userResponse);
                    }

                    @Override
                    protected void _onError(String message) {
                        weakRefView.get().showProgressView(false);

                    }

                    @Override
                    protected void _onStart() {
                        weakRefView.get().showMessage(CustomApplication.getAppContext().getResources().getString(R.string.on_login));
                        weakRefView.get().showProgressView(true);
                    }

                });
        if (!weakRefView.get().isActive()) {
            return;
        }

    }

    @Override
    public void detacheView() {
        weakRefView.clear();
        weakRefView = null;
    }

    //持有fragment引用 执行回调
    @Override
    public void setPresenterView(@NonNull BaseView tasksView) {
        //这里view不能为空 不在判断是否为空
        weakRefView = new WeakReference(checkNotNull(tasksView, "tasksView cannot be null!"));
    }


}

