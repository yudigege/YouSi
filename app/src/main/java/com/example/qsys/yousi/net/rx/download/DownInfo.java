package com.example.qsys.yousi.net.rx.download;

import com.example.qsys.yousi.net.rx.api.HttpDownService;
import com.example.qsys.yousi.net.rx.listener.HttpDownOnNextListener;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 下载请求数据基础类
 *
 * @author hanshaokai
 * @date 2017/12/13 15:55
 */

@Entity
public class DownInfo {
    @Id
    private long id;
    /**
     * 存储位置
     */
    private String savePath;
    /**
     * 文件总长度
     */
    private long countLength;
    /**
     * 下载长度
     */
    private long readLength;
    /**
     * 下载唯一的HttpServiece
     */
    @Transient
    private HttpDownService service;
    /*回调监听*/
    @Transient
    private HttpDownOnNextListener listener;
    /*超时设置*/
    private int connectonTime = 6;
    /*state状态数据库保存*/
    private int stateInte;
    /*url*/
    private String url;
    @Generated(hash = 656702907)
    public DownInfo(long id, String savePath, long countLength, long readLength,
            int connectonTime, int stateInte, String url) {
        this.id = id;
        this.savePath = savePath;
        this.countLength = countLength;
        this.readLength = readLength;
        this.connectonTime = connectonTime;
        this.stateInte = stateInte;
        this.url = url;
    }
    @Generated(hash = 928324469)
    public DownInfo() {
    }

    public DownInfo(String url,HttpDownOnNextListener listener) {
        setUrl(url);
        setListener(listener);
    }

    public DownInfo(String url) {
        setUrl(url);
    }

    public void setState(DownState state) {
        setStateInte(state.getState());
    }
    public DownState getState() {

        switch (getStateInte()) {
            case 0:
                return DownState.START;

            case 1:
                return DownState.DOWN;
            case 2:
                return DownState.PAUSE;
            case 3:
                return DownState.STOP;
            case 4:
                return DownState.ERROR;
            case 5:
            default:
                return DownState.FINISH;


        }


    }

    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSavePath() {
        return this.savePath;
    }
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
    public long getCountLength() {
        return this.countLength;
    }
    public void setCountLength(long countLength) {
        this.countLength = countLength;
    }
    public long getReadLength() {
        return this.readLength;
    }
    public void setReadLength(long readLength) {
        this.readLength = readLength;
    }
    public int getConnectonTime() {
        return this.connectonTime;
    }
    public void setConnectonTime(int connectonTime) {
        this.connectonTime = connectonTime;
    }
    public int getStateInte() {
        return this.stateInte;
    }
    public void setStateInte(int stateInte) {
        this.stateInte = stateInte;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public HttpDownService getService() {
        return service;
    }

    public void setService(HttpDownService service) {
        this.service = service;
    }

    public HttpDownOnNextListener getListener() {
        return listener;
    }

    public void setListener(HttpDownOnNextListener listener) {
        this.listener = listener;
    }

    @Override
    public String toString() {
        return "DownInfo{" +
                "id=" + id +
                ", savePath='" + savePath + '\'' +
                ", countLength=" + countLength +
                ", readLength=" + readLength +
                ", service=" + service +
                ", listener=" + listener +
                ", connectonTime=" + connectonTime +
                ", stateInte=" + stateInte +
                ", url='" + url + '\'' +
                '}';
    }
}
