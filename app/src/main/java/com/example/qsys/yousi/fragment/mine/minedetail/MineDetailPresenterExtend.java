package com.example.qsys.yousi.fragment.mine.minedetail;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.SuccessResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.net.rx.manager.AbstractRxSubscriber;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;

import java.util.Map;

/**
 * @author hanshaokai
 * @date 2017/10/31 18:06
 */


public class MineDetailPresenterExtend extends AbstractMineDetailPresenter {
    @Override
    public void start() {
    }

    @Override
    public void updateUserInfor(Map<String, String> map, final int editType) {

        NetManager.getApiService().updateUser(CustomApplication.userEntity.getId(), map).compose(RxSchedulers.<SuccessResponse>io_main())
                .compose(getBindView().<SuccessResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<SuccessResponse>(getWeakRefView()) {
                    @Override
                    protected void on_Next(SuccessResponse successResponse) {
                        if (!getBindView().isActive()) {
                            return;
                        }
                        getBindView().showResponseData(successResponse);
                        if (successResponse.getCode() != Constant.SCUCESS_COED) {
                            getBindView().showMessage(((MineDetailFragment) getBindView()).getString(R.string.edit_sucess));
                        } else {
                            ((MineDetailFragment) getBindView()).setUserInfor(editType);
                        }
                    }
                });

    }


}
