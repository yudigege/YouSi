package com.example.qsys.yousi.fragment.main.bookshelf;

import com.example.qsys.yousi.fragment.BaseView;

/**
 * Created by hanshaokai on 2017/10/18 10:28
 */

public interface BookShelfView extends BaseView {

    /**
     * 回调进度 和位置
     * @param readLength
     * @param countLength
     * @param postion
     */
    void showLoadePercent(long readLength, long countLength,int postion);
}
