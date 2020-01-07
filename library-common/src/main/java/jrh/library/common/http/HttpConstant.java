package jrh.library.common.http;

import jrh.library.common.utils.StringUtil;

/**
 * desc:
 * Created by jiarh on 2018/8/10 11:46.
 */

public class HttpConstant {

    /**
     * 没网 ，网络错误
     */
    public static final String NET_ERROR = "net_error";

    public static String ERROR_CODE = "errcode";
    public static String SUCCESS_CODE = "1";
    /**
     * token 失效
     */
    public static int TOKEN_LOSS = -11;
    /**
     * 服务器错误
     **/
    public static int SERVER_ERROE = -1000;
    /**
     * 非法参数
     */
    public static int PARAMS_ERROE = -1;

    public static final String HEADER_APPLICATION_JSON = "application/json";
    public static final String HEADER_FORM = "application/x-www-form-urlencoded";
    public static final String FORMAT_FORM = "form";
    public static final String FORMAT_JSON = "json";
    public static final String PARAMS_TOKEN = "token";

    public static String getSuccessCode() {
        return SUCCESS_CODE;
    }

    public static void setSuccessCode(String successCode) {
        if (StringUtil.isAllNotBlank(successCode))
            SUCCESS_CODE = successCode;
    }

    public static boolean msgInterceptor(String errorCode) {

        return errorCode.equals(TOKEN_LOSS + "")
                || errorCode.equals(SERVER_ERROE + "")
                || errorCode.equals(PARAMS_ERROE+"");
    }
}
