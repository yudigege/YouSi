package com.example.qsys.yousi.fragment.main.idea;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.net.rx.manager.AbstractRxSubscriber;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;


/**
 * @author hanshaokai
 * @date 2017/10/18 10:31
 */

public class IdeaPresenterExtend extends AbstractIdeaPresenter {
    @Override
    public void start() {

    }


    @Override
    void getDasyReportData(int page, int pageSize) {
        NetManager.getApiService().getReportByPage(CustomApplication.userEntity.getId(), page, pageSize).compose(RxSchedulers.<BaseResponse>io_main())
                .compose(getBindView().<BaseResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<BaseResponse>(getWeakRefView()) {
                    @Override
                    public void on_Next(BaseResponse dateReport) {
                        (getBindView()).showResponseData(dateReport);
                    }
                });


    }

    @Override
    public void getDasyReportMoreData(int page, int pageSize) {

        NetManager.getApiService().getReportByPage(CustomApplication.userEntity.getId(), page, pageSize).compose(RxSchedulers.<BaseResponse>io_main())
                .compose(getBindView().<BaseResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<BaseResponse>(getWeakRefView()) {
                    @Override
                    public void on_Next(BaseResponse dateReport) {
                        ((IdeaView) getBindView()).showResponseMoreData(dateReport);
                    }
                });


    }


}
