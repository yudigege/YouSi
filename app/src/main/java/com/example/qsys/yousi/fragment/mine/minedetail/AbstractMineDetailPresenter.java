package com.example.qsys.yousi.fragment.mine.minedetail;

import com.example.qsys.yousi.fragment.BasePresenter;

import java.util.Map;

/**
 * @author hanshaokai
 * @date 2017/10/31 18:05
 */


public abstract class AbstractMineDetailPresenter extends BasePresenter {
    public abstract void updateUserInfor(Map<String, String> map,int editType);

    public abstract void upLoadeAvatar(String avatorUrl);
}

