package com.example.qsys.yousi.net.rx.demo;

import java.util.List;

/**
 * Created by hanshaokai on 2017/10/11 17:59
 */

public interface IPaging<T> {
    List<T> getItems();

    int getPage();

    int getPageSize();

    long getTotal();

    int getPageCount();
}
