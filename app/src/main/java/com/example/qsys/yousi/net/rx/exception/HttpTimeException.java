package com.example.qsys.yousi.net.rx.exception;

/**
 * @author hanshaokai
 * @date 2017/12/14 11:43
 */


public class HttpTimeException extends RuntimeException {
    public static final int NO_DATA = 0x2;

    public HttpTimeException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    public HttpTimeException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 转换错误数据
     */

    public static String getApiExceptionMessage(int code) {
        String message = "";
        switch (code) {
            case NO_DATA:
                message = "无数据";
                break;
            default:
                message = "error";
                break;
        }
        return message;
    }
}
