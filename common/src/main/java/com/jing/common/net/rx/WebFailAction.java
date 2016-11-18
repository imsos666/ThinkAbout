package com.jing.common.net.rx;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/**
 * Created by linux-sever-build5 on 11/9/16.
 */
public class WebFailAction implements Action1<Throwable>{

    private Context mContext;

    public WebFailAction(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void call(Throwable throwable) {
        String errorMsg = "";
        if(throwable instanceof IOException)
            errorMsg = "Please check your network status";
        else if (throwable instanceof HttpException){
            HttpException httpException = (HttpException) throwable;
            errorMsg = httpException.response().message();
        }else{
            errorMsg = "unknown error";
        }
        Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
