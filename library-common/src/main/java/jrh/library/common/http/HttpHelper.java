package jrh.library.common.http;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 * Created by jiarh on 2018/8/9 14:30.
 */

public class HttpHelper {

    public static final String KEY_ACCESS_TOKE = "accesstoken";


    public static Map<String,String> params = new HashMap<>();

    public static Map<String, String> getParams() {
        return params;
    }

    public static void setParams(Map<String, String> param) {
        params = param;
    }

    public void putParam(String key,String value){
        params.put(key,value);
    }

    public static void saveAccessToken(String token){
        params.put(KEY_ACCESS_TOKE,token);
    }
    public static  void reset(){
        params.clear();
    }
}
