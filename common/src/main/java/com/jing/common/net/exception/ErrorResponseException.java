package com.jing.common.net.exception;

/**
 * Created by linux-sever-build5 on 11/8/16.
 */
public class ErrorResponseException extends Throwable {

    public ErrorResponseException() {
    }

    public ErrorResponseException(String detailMessage) {
        super(detailMessage);
    }
}
