package com.example.qsys.yousi.fragment.main.idea;

import com.example.qsys.yousi.fragment.BasePresenter;

/**
 * Created by hanshaokai on 2017/10/17 15:20
 */

public abstract class AbstractIdeaPresenter extends BasePresenter {
    /**
     * 得到日志和读后感
     */
    abstract void getDasyReportData(int page,int pageSiz);


    public abstract void getDasyReportMoreData(int page, int pageSize);
}
