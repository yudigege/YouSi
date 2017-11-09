package com.example.qsys.yousi.bean;

/**
 * Created by hanshaokai on 2017/10/12 18:16
 */

public abstract class BaseResponse {
    private int code;
    private String message;
    private Object errors;
    private int requstCode;
    /**
     * 返回的内容是否为空
     */
    private int resultsNotNull;

    public int getRequstCode() {
        return requstCode;
    }

    public void setRequstCode(int requstCode) {
        this.requstCode = requstCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public int getResultsNotNull() {
        return resultsNotNull;
    }

    public void setResultsNotNull(int resultsNotNull) {
        this.resultsNotNull = resultsNotNull;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                ", requstCode=" + requstCode +
                ", resultsNotNull=" + resultsNotNull +
                '}';
    }
}
