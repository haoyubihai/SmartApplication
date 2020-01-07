package jrh.library.common.http.converter;

import okhttp3.ResponseBody;

/**
 * desc:
 * Created by jiarh on 2018/8/9 16:09.
 */

public interface ResponseBodyConverter <T>{
    T parse(ResponseBody responseBody);
}
