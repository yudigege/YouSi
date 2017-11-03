package com.example.qsys.yousi.fragment.idea.readpression;

import com.example.qsys.yousi.fragment.BasePresenter;

/**
 * @author hanshaokai
 * @date 2017/10/31 13:59
 */


public abstract class AbstractWriteReadPressionPresenter extends BasePresenter {
    public abstract void postData(String bookName, String content);
}
