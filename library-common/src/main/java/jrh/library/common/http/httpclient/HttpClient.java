package jrh.library.common.http.httpclient;

import java.util.concurrent.TimeUnit;

import jrh.library.common.http.interceptor.NetworkInfoInterceptor;
import okhttp3.OkHttpClient;

/**
 * desc:
 * Created by jiarh on 2018/8/9 14:10.
 */

public class HttpClient {

    private static HttpClient instance = null;
    private OkHttpClient okHttpClient;

    private HttpClient(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                ;
        okHttpClient = builder.build();

    }

    public static HttpClient getInstance(){
        if (instance==null){
            synchronized (HttpClient.class){
                if (instance==null){
                    instance = new HttpClient();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
