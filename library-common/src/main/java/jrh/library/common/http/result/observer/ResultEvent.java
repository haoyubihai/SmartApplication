package jrh.library.common.http.result.observer;

/**
 * desc:
 * Created by jiarh on 2018/8/10 11:14.
 */

public interface ResultEvent<T> {
    /**
     * 请求之前
     */
    void onPreRequest();

    /**
     * 请求结束
     */
    void onEndRequest();

    /**
     * 成功
     * @param result
     */
    void onSuccess(T result);

    /**
     * 失败，  业务失败  ,其它error
     * @param code
     * @param msg
     */
    void onFailure(String code,String msg);

}
