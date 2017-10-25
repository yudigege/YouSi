package com.example.qsys.yousi.fragment.main.mainpage;

import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.net.rx.manager.AbstractRxSubscriber;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;

/**
 * Created by hanshaokai on 2017/10/18 10:31
 */

public class MainPagePresenterExtend extends AbstractMainPagePresenter {
    @Override
    public void start() {

    }
    @Override
    void getBookData() {
        NetManager.getApiService().getAllBooks().compose(RxSchedulers.<BaseResponse>io_main())
                .compose(getBindView().<BaseResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<BaseResponse>(getWeakRefView()) {
                    @Override
                    protected void on_Next(BaseResponse bookResponse) {
                        getBindView().showProgressView(false);
                        ((MainPageView) getBindView()).showResponseData(bookResponse);
                    }

                });


    }
}
