package com.jing.common.net.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by linux-sever-build5 on 11/8/16.
 */
public class BaseResponse {
    public static final int CODE_SUCCESS = 0;

    public String msg;
    public int code;

    @SerializedName("error_reponse")
    public ErrorResponse errorResponse;

    public static final class ErrorResponse{
        public String msg;
        public int code;
    }
}
