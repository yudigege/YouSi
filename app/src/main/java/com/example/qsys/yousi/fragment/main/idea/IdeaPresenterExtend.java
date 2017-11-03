package com.example.qsys.yousi.fragment.main.idea;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.DaysResportResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.net.rx.manager.AbstractRxSubscriber;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;

import java.util.List;


/**
 * @author hanshaokai
 * @date 2017/10/18 10:31
 */

public class IdeaPresenterExtend extends AbstractIdeaPresenter {
    @Override
    public void start() {

    }


    @Override
    void getDasyReportData() {
        NetManager.getApiService().getAllDaysReport(CustomApplication.userEntity.getId()).compose(RxSchedulers.<BaseResponse>io_main())
                .compose(getBindView().<BaseResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<BaseResponse>(getWeakRefView()) {
                    @Override
                    public void on_Next(BaseResponse dateReport) {
                        getBindView().showProgressView(false);
                        (getBindView()).showResponseData(dateReport);
                        List<DaysResportResponse.ResultsBean> results = ((DaysResportResponse) dateReport).getResults();
                        if (results.size() == 0) {
                            getBindView().showEmptyViewByCode(Constant.NO_CONTENT);
                        }
                    }
                });


    }




}
