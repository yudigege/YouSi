package com.example.qsys.yousi.fragment.main.bookshelf;

import com.example.qsys.yousi.bean.BaseResponse;
import com.example.qsys.yousi.bean.BookResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.net.rx.manager.AbstractRxSubscriber;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;

import java.util.List;


/**
 * @author hanshaokai
 * @date 2017/10/18 10:31
 */
public class BookShelfPresenterExtend extends AbstractBookShelfPresenter {
    @Override
    public void start() {

    }

    @Override
    void getBookData() {
        NetManager.getApiService().getAllBooks().compose(RxSchedulers.<BaseResponse>io_main())
                .compose(getBindView().<BaseResponse>bindToLifecycle())
                //开始和 错误回调统一再 AbstractRxSubscriber 中处理
                .subscribe(new AbstractRxSubscriber<BaseResponse>(getWeakRefView()) {
                    @Override
                    public void on_Next(BaseResponse bookResponse) {
                        getBindView().showProgressView(false);
                        (getBindView()).showResponseData(bookResponse);
                        List<BookResponse.ResultsBean> results = ((BookResponse) bookResponse).getResults();
                        if (results.size() == 0) {
                            getBindView().showEmptyViewByCode(Constant.NO_CONTENT);
                        }
                    }

                });


    }

}
