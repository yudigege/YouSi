package com.example.qsys.yousi.net.rx.exception;

import java.io.IOException;

/**
 * Created by hanshaokai on 2017/10/11 18:02
 */

public class ApiException extends IOException {
    public ApiException(String message) {
        super(message);
    }
}
