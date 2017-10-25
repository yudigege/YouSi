package com.example.qsys.yousi.net.rx.demo;

import java.util.List;

/**
 * Created by hanshaokai on 2017/10/11 17:59
 */

public interface IPaging<T> {
    /**
     * 得到条目
     * @return
     */
    List<T> getItems();

    /**
     * 得到页
     * @return
     */
    int getPage();

    /**
     * 得到页数
     * @return
     */
    int getPageSize();

    /**
     * 得到总数
     * @return
     */
    long getTotal();

    /**
     * 得到页总数
     * @return
     */
    int getPageCount();
}
