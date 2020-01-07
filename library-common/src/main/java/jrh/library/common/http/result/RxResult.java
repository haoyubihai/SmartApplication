package jrh.library.common.http.result;

import jrh.library.common.http.HttpConstant;

/**
 * desc:
 * Created by jiarh on 2018/8/10 10:48.
 */

public class RxResult<T> {
    private String errcode;
    private String errmsg;

    private T data;


    public boolean isOk() {
        return errcode.equals(HttpConstant.getSuccessCode());
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
