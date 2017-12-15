package com.example.qsys.yousi.net.rx.listener;

/**
 * @author hanshaokai
 * @date 2017/12/13 16:09
 */


public interface DownLoadProgressListener {
    /**
     * 下载进度
     */
    void update(long read, long count, boolean done);
}
