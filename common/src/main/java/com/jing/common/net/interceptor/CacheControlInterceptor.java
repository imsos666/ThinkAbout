package com.jing.common.net.interceptor;

import android.content.Context;
import android.util.Log;

import com.jing.common.utils.NetWorkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by linux-sever-build5 on 11/8/16.
 */
public class CacheControlInterceptor implements Interceptor{

    private Context context;

    public CacheControlInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
        cacheBuilder.maxAge(0, TimeUnit.SECONDS);
        cacheBuilder.maxStale(365, TimeUnit.DAYS);
        CacheControl cacheControl = cacheBuilder.build();
        Request request = chain.request();
        Log.d("jingbin", "request : " + request);
        if(!NetWorkUtil.isNetWorkAvailable(context)){
            request = request.newBuilder().cacheControl(cacheControl)
                    .build();
            Log.d("jingbin", "no network!");
        }
        Response orignalResponse = chain.proceed(request);
        if(NetWorkUtil.isNetWorkAvailable(context)){
            int maxAge = 0;
            return orignalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public ,max-age=" + maxAge)
                    .build();
        }else{
            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
            return orignalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
    }
}
