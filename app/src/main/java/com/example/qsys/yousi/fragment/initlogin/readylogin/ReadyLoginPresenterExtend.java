package com.example.qsys.yousi.fragment.initlogin.readylogin;

import com.example.qsys.yousi.bean.SuccessResponse;
import com.example.qsys.yousi.bean.UserResponse;
import com.example.qsys.yousi.net.rx.manager.AbstractRxSubscriber;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;


/**
 * @author hanshaokai
 * @date 2017/10/9 17:39
 */
public class ReadyLoginPresenterExtend extends AbstractReadyLoginPresenter {
    //相应fragment 对应的的具体presenter

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
        //耗时过程中fragment有可能销毁 耗时完后更改视图要判断是否可用 开启子线程前不用判断
        NetManager.getApiService().toLogin(account, password).compose(RxSchedulers.<UserResponse>io_main())
                .compose(getBindView().<UserResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<UserResponse>(getWeakRefView()) {
                    @Override
                    protected void on_Next(UserResponse userResponse) {
                        getBindView().showResponseData(userResponse);
                    }
                });

        if (!getBindView().isActive()) {
            return;
        }

    }

    @Override
    public void toRegister(String account) {
        NetManager.getApiService().toRegister(account).compose(RxSchedulers.<SuccessResponse>io_main())
                .compose(getBindView().<SuccessResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<SuccessResponse>(getWeakRefView()) {
                    @Override
                    protected void on_Next(SuccessResponse successResponse) {
                        if (successResponse.getCode() != 1) {
                            getBindView().showMessage("注册失败");
                        } else {
                            getBindView().showMessage("注册成功");
                        }
                    }
                });
    }

    @Override
    public void start() {

    }




}

