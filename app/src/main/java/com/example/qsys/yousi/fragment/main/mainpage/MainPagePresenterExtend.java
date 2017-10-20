package com.example.qsys.yousi.fragment.main.mainpage;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;
import com.example.qsys.yousi.net.rx.manager.RxSubscriber;

/**
 * Created by hanshaokai on 2017/10/18 10:31
 */

public class MainPagePresenterExtend extends MainPagePresenter {
    @Override
    public void start() {

    }
    @Override
    void getBookData() {
        NetManager.getInstance().getApiService().getAllBooks().compose(RxSchedulers.<BaseResponse>io_main())
                .compose(getBindView().<BaseResponse>bindToLifecycle())
                .subscribe(new RxSubscriber<BaseResponse>() {
                    @Override
                    protected void _onNext(BaseResponse bookResponse) {
                        getBindView().showProgressView(false);
                        ((MainPageView) getBindView()).showResponseData(bookResponse);
                    }
                    @Override
                    protected void _onError(String message) {
                        getBindView().showMessage(message);
                        getBindView().showProgressView(false);
                    }

                    @Override
                    protected void _onStart() {
                        getBindView().showMessage(CustomApplication.getAppContext().getResources().getString(R.string.on_login));
                        getBindView().showProgressView(true);

                    }
                });


    }
}
