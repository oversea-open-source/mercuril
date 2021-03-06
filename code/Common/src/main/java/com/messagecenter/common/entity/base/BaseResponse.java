package com.messagecenter.common.entity.base;

import java.io.Serializable;

/**
 * Created by Jared on 16/12/7.
 */
public class BaseResponse<T> implements Serializable {
    private T data;
    private int code;
    private String message;

    public BaseResponse(){
        this.code = StatusCode.SUCCESS;
    }

    public BaseResponse(T data) {
        this();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
}
