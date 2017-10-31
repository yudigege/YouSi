package com.example.qsys.yousi.fragment.idea.readpression;

import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.SuccessResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.net.rx.manager.AbstractRxSubscriber;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;

/**
 * @author hanshaokai
 * @date 2017/10/31 14:00
 */


public class WriteReadPressionPresenterExtend extends AbstractWriteReadPressionPresenter {
    @Override
    public void start() {

    }


    @Override
    public void postData(String title, String content) {

        if (title.trim().equals("")) {
            getBindView().showMessage(((WriteReadPressionFragment) getBindView()).baseFragmentActivity.getResources().getString(R.string.write_title));
            return;
        }

        if (content.trim().equals("")) {
            getBindView().showMessage(((WriteReadPressionFragment) getBindView()).baseFragmentActivity.getResources().getString(R.string.write_content));
            return;
        }

        NetManager.getApiService().constructReport(title, content, 11, Constant.READPRESSION).compose(RxSchedulers.<SuccessResponse>io_main())
                .compose(getBindView().<SuccessResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<SuccessResponse>(getWeakRefView()) {
                    @Override
                    protected void on_Next(SuccessResponse successResponse) {
                        if (!getBindView().isActive()) {
                            return;
                        }
                        getBindView().showProgressView(false);
                        getBindView().showResponseData(successResponse);
                        if (successResponse.getCode() == Constant.SCUCESS_COED) {
                            ((WriteReadPressionFragment) getBindView()).clearEtData();
                        }
                    }
                });

    }

}
