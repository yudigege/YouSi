package com.example.qsys.yousi.fragment.mine.minedetail;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;
import com.example.qsys.yousi.bean.SuccessResponse;
import com.example.qsys.yousi.common.Constant;
import com.example.qsys.yousi.net.rx.manager.AbstractRxSubscriber;
import com.example.qsys.yousi.net.rx.manager.NetManager;
import com.example.qsys.yousi.net.rx.manager.RxSchedulers;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
                            getBindView().showMessage(((MineDetailFragment) getBindView()).getString(R.string.edit_failed));
                        } else {
                            ((MineDetailFragment) getBindView()).setUserInfor(editType);
                            getBindView().showMessage(((MineDetailFragment) getBindView()).getString(R.string.edit_sucess));
                        }
                    }
                });
    }

    @Override
    public void upLoadeAvatar(String avatorUrl) {
        File file = new File(avatorUrl);
        //RequestBody userID = RequestBody.create(MediaType.parse("text/plain"), CustomApplication.userEntity.getId() + "");
        MultipartBody.Part userIdBody = MultipartBody.Part.createFormData("userId", CustomApplication.userEntity.getId()+"");
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part avatorFile = MultipartBody.Part.createFormData("avatar", file.getName(), requestBody);
        NetManager.getApiService().upLoadeAvatar(avatorFile,userIdBody).compose(RxSchedulers.<SuccessResponse>io_main())
                .compose(getBindView().<SuccessResponse>bindToLifecycle())
                .subscribe(new AbstractRxSubscriber<SuccessResponse>(getWeakRefView()) {
                    @Override
                    protected void on_Next(SuccessResponse successResponse) {
                        if (!getBindView().isActive()) {
                            return;
                        }
                        getBindView().showResponseData(successResponse);
                        if (successResponse.getCode() != Constant.SCUCESS_COED) {
                            getBindView().showMessage(((MineDetailFragment) getBindView()).getString(R.string.edit_avatar_failed)+successResponse.getErrors().toString());
                        } else {
                            getBindView().showMessage(((MineDetailFragment) getBindView()).getString(R.string.edit_avatar_sucess));
                        }
                    }
                });


    }


}
