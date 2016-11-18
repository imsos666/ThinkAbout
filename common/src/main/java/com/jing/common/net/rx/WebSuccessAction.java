package com.jing.common.net.rx;

import com.jing.common.net.base.BaseResponse;
import com.jing.common.net.exception.ErrorResponseException;

import rx.exceptions.Exceptions;
import rx.functions.Action1;

/**
 * Created by linux-sever-build5 on 11/9/16.
 */
public abstract class WebSuccessAction<T extends BaseResponse> implements Action1<T>{
    @Override
    public void call(T response) {
        int code = response.code;
        if(code != 0) {
            try {
                throw new ErrorResponseException(response.msg);
            } catch (ErrorResponseException e) {
                Exceptions.propagate(e);
            }
        }
    }

    public abstract void onSuccess(T response);
}
