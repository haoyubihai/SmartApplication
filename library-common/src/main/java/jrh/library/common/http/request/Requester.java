package jrh.library.common.http.request;


import io.reactivex.schedulers.Schedulers;
import jrh.library.common.http.HttpConstant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Requester {
    private static volatile Requester requester;
    private Retrofit mRetrofit;
    private Config mConfig;

    private Requester() {
    }

    public  static void init(Config config) {
        if (requester==null){
            synchronized (Requester.class){
                if (requester==null){
                    requester = new Requester();
                }
            }
        }

        requester.mConfig = config;
        requester.mRetrofit = buildRetrofit( config, false);
        HttpConstant.setSuccessCode(config.resultSuccessCode);
    }

    /**
     * @param sync true：同步请求；false：异步请求
     */
    public static Retrofit buildRetrofit(Config config, boolean sync) {
        Retrofit.Builder builder = new Retrofit.Builder()
                // 假如@GET、@POST等标签设置了url，并包含host，baseUrl将不起作用
                // 如果没有设置host，则url为baseUrl + 设置的url
                .baseUrl(config.baseUrl)
                .addConverterFactory(GsonConverterFactory.create());
        if (sync) {
            // 在当前线程执行网络请求，请求完成后不会进行线程切换
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        } else {
            // 在io线程执行网络请求，请求完成后切换到主线程
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));
        }
        if (config.httpClient != null) {
            builder.client(config.httpClient);
        }
        return builder.build();
    }

    public static Requester get() {


        return requester;
    }

    /**
     * 创建Service，io线程进行网络请求，请求完后切换到主线程
     */
    public static <T> T create(final Class<T> service) {
        return get().createService(service);
    }

    /**
     * 创建Service，io线程进行网络请求，请求完后切换到主线程
     */
    public <T> T createService(final Class<T> service) {
        return mRetrofit.create(service);
    }

    /**
     * 创建同步请求的Service
     */
    public <T> T createSync(final Class<T> service) {
        return buildRetrofit(mConfig, true).create(service);
    }

    public Config getmConfig() {
        return mConfig;
    }

    public static final class Config {
        private String baseUrl;
        private OkHttpClient httpClient;
        //请求结果 成功标识
        private String resultSuccessCode;

        public void setResultSuccessCode(String resultSuccessCode) {
            this.resultSuccessCode = resultSuccessCode;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public void setHttpClient(OkHttpClient httpClient) {
            this.httpClient = httpClient;
        }
    }
}
