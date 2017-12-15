package com.example.qsys.yousi.fragment.idea.search;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.net.rx.manager.AbstractRxSubscriber;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;

/**
 * @author hanshaokai
 * @date 2017/11/15 10:50
 */


public class SearchDailyPresenterExtend extends AbstractIdSearchDailyPresenter {
    @Override
    public void start() {


    }

    @Override
    public void getSearchItemMostFrequently() {

        NetManager.getApiService().getLimitToIndexSearchItem()
                .compose(RxSchedulers.<BaseResponse>io_main())
                .compose(getBindView().<BaseResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<BaseResponse>(getWeakRefView()) {
                    @Override
                    public void on_Next(BaseResponse searchItem) {
                        ((SearchDailyView)getBindView()).showLimitToIndexSearchItem(searchItem);
                    }
                });
    }

    @Override
    public void getContainKeyWordsDaily(String keyWords) {
        NetManager.getApiService().getAllDaysReport(CustomApplication.userEntity.getId(), keyWords).compose(RxSchedulers.<BaseResponse>io_main())
                .compose(getBindView().<BaseResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<BaseResponse>(getWeakRefView()) {
                    @Override
                    public void on_Next(BaseResponse dateReport) {
                        (getBindView()).showResponseData(dateReport);
                    }
                });
    }


}
