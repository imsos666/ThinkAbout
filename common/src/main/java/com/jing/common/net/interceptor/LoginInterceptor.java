package com.jing.common.net.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by linux-sever-build5 on 11/8/16.
 */
public class LoginInterceptor implements Interceptor{

    public static final String ACCESS_TOKEN = "access_token";
    public static final String USER_AGENT = "User-Agent";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                //.header(USER_AGENT, AppUtils.getUserAgent() + " app_name/" + AppUtils.getVersionName())
                //.header(ACCESS_TOKEN, AppUtils.getToken())
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
