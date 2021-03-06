package com.example.qsys.yousi.fragment.main.bookshelf;

import com.example.qsys.yousi.fragment.BasePresenter;

/**
 * Created by 韩少凯 on 2017/10/17 15:20
 */

public abstract class AbstractBookShelfPresenter extends BasePresenter {
    /**
     * 得到图书数据
     */
    abstract void getBookData();

    public abstract void toLoadBook(String bookname);

    /**
     * 断点可见进度下载
     *
     * @param bookname
     * @param postion
     */
    public abstract void toLoadCouldContinueBook(String bookname, int postion);
}
