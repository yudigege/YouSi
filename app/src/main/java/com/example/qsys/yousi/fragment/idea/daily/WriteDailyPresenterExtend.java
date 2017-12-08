package com.example.qsys.yousi.fragment.idea.daily;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.SuccessResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.net.rx.manager.AbstractRxSubscriber;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;

/**
 * @author hanshaokai
 * @date 2017/10/27 16:30
 */


public class WriteDailyPresenterExtend extends AbstractWriteDailyPresenter {
    @Override
    public void start() {

    }


    @Override
    public void postData(Long write_start_time,String title, String content) {

        if (title.trim().equals("")) {
            getBindView().showMessage(((WriteDailyFragment) getBindView()).baseFragmentActivity.getResources().getString(R.string.write_title));
            return;
        }

        if (content.trim().equals("")) {
            getBindView().showMessage(((WriteDailyFragment) getBindView()).baseFragmentActivity.getResources().getString(R.string.write_content));
            return;
        }
        long write_end_time = System.currentTimeMillis();
        NetManager.getApiService().constructReport(write_start_time,write_end_time,null,title, content, CustomApplication.userEntity.getId(), Constant.DAYLIE).compose(RxSchedulers.<SuccessResponse>io_main())
                .compose(getBindView().<SuccessResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<SuccessResponse>(getWeakRefView()) {
                    @Override
                    protected void on_Next(SuccessResponse successResponse) {
                        if (!getBindView().isActive()) {
                            return;
                        }
                        getBindView().showResponseData(successResponse);
                        if (successResponse.getCode() == Constant.SCUCESS_COED) {
                            ((WriteDailyFragment) getBindView()).clearEtData();
                        }
                    }
                });
    }
}
