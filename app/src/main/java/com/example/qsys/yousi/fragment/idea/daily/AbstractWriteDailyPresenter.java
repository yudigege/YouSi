package com.example.qsys.yousi.fragment.idea.daily;

import com.example.qsys.yousi.fragment.BasePresenter;
import com.example.qsys.yousi.fragment.BaseView;

/**
 * @author hanshaokai
 * @date 2017/10/27 16:29
 */


public abstract class AbstractWriteDailyPresenter extends BasePresenter<BaseView> {
    public abstract void postData(String title, String content);
}
