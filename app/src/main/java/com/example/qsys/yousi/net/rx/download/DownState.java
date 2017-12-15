package com.example.qsys.yousi.net.rx.download;

/**
 * @author hanshaokai
 * @date 2017/12/13 16:02
 */


public enum DownState {
    START(0),
    DOWN(1),
    PAUSE(2),
    STOP(3),
    ERROR(4),
    FINISH(5);
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    DownState(int state) {
        this.state = state;
    }
}
