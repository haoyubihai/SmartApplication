package jrh.library.common.http.converter;

import okhttp3.RequestBody;

/**
 * desc:
 * Created by jiarh on 2018/8/9 16:07.
 */

public interface RequestBodyConverter<T> {
    RequestBody parse(T body);
}
