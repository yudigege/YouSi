package com.example.qsys.yousi.fragment.mine.recordtrack;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.net.rx.manager.AbstractRxSubscriber;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;

/**
 * @author hanshaokai
 * @date 2017/12/7 18:08
 */


public class RecordTrackShowPresenterExtend extends RecordTrackShowPresenter {
    @Override
    public void getRecordTrackData() {
        NetManager.getApiService().getRecordTrackData(CustomApplication.userEntity.getId()).compose(RxSchedulers.<BaseResponse>io_main())
                .compose(getBindView().<BaseResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<BaseResponse>(getWeakRefView()) {
                    @Override
                    public void on_Next(BaseResponse baseResponse) {
                        (getBindView()).showResponseData(baseResponse);
                    }
                });
    }

    @Override
    public void getTrackDayData() {
        NetManager.getApiService().getTrackDayData(CustomApplication.userEntity.getId()).compose(RxSchedulers.<BaseResponse>io_main())
                .compose(getBindView().<BaseResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<BaseResponse>(getWeakRefView()) {
                    @Override
                    public void on_Next(BaseResponse baseResponse) {
                        ((RecordTrackView)getBindView()).showDayTrackData(baseResponse);
                    }
                });
    }

    @Override
    public void start() {

    }
}
