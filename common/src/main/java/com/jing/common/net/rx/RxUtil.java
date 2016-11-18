package com.jing.common.net.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by linux-sever-build5 on 11/9/16.
 */
public class RxUtil {

    public static <T> Observable.Transformer<T, T> normalSchedulers(){
        return new Observable.Transformer<T, T>(){

            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
