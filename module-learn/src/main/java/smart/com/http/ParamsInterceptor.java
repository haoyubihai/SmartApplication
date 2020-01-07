package smart.com.http;

import android.text.TextUtils;

import androidx.collection.ArrayMap;

import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jrh.library.common.http.HttpConstant;
import jrh.library.common.utils.MapUtil;
import jrh.library.common.utils.StringUtil;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.aly.phone.base.http.ParamConstantKt.HTTP_ACCESS_TOKEN;
import static jrh.library.common.http.HttpHelper.getParams;

/**
 * desc: 添加公用的参数
 * Created by jiarh on 2018/8/9 14:23.
 */

public class ParamsInterceptor implements Interceptor {
    private static final String KEY_ACCESS_TOKEN = "access_token";
    Map<String, String> queryParamsMap = new HashMap<>(); // 添加到 URL 末尾，Get Post 方法都使用
    private boolean isForm;

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request.Builder builder = request.newBuilder();


        builder.url(buildUrl(request));
        request = injectParamsIntoUrl(request,request.url().newBuilder(), builder, getParamsMap());
        RequestBody requestBody = buildRequestBody(request);
        request = buildHeader(builder);
        builder.method(request.method(), requestBody);
        request = builder.build();

        return chain.proceed(request);
    }

    private Map<String, String> getParamsMap() {
        String access_token = Hawk.get(KEY_ACCESS_TOKEN);
        if (StringUtil.isNotBlank(access_token)){
            queryParamsMap.put(HTTP_ACCESS_TOKEN,access_token);
        }
        return queryParamsMap;
    }


    private Request buildHeader(Request.Builder builder) {

        return builder.build();
    }


    public void addQueryParam(String key, String value) {
        queryParamsMap.put(key, value);
    }

    private RequestBody buildRequestBody(Request request) {
        RequestBody body = request.body();
        String method = request.method();
        if (!method.equals("POST")) {
            return body;
        }

        if (body instanceof FormBody) {
            FormBody formbody = (FormBody) body;
            String access_token = Hawk.get(KEY_ACCESS_TOKEN);
            if (StringUtil.isEmpty(access_token)) return body;
            FormBody.Builder builder = new FormBody.Builder();
            if (formbody.size() == 1) {
                boolean isAccessToken = formbody.encodedName(0).equals(HTTP_ACCESS_TOKEN);
                if (isAccessToken) {
                    if (StringUtil.isEmpty(formbody.encodedValue(0))) {
                        builder.add(HTTP_ACCESS_TOKEN, access_token);
                    }
                    return builder.build();
                } else {
                    for (int i = 0; i < formbody.size(); i++) {
                        builder.add(formbody.encodedName(i), formbody.encodedValue(i));
                    }
                    builder.add(HTTP_ACCESS_TOKEN, access_token);
                    return builder.build();
                }
            } else {
                for (int i = 0; i < formbody.size(); i++) {
                    if (!formbody.encodedValue(i).isEmpty())
                        builder.add(formbody.encodedName(i), formbody.encodedValue(i));
                }
                builder.add(HTTP_ACCESS_TOKEN, access_token);
                return builder.build();

            }

        }

        return body;
    }

    private Request injectParamsIntoUrl(Request request,HttpUrl.Builder httpUrlBuilder, Request.Builder requestBuilder, Map<String, String> paramsMap) {


        RequestBody body = request.body();
        if (body == null) {
            return request;
        }
        MediaType mediaType = body.contentType();
        if (mediaType == null) {
            return request;
        }
        if (TextUtils.equals(mediaType.subtype(), "x-www-form-urlencoded")) {
            return request;
        }

        if (paramsMap.size() > 0) {
            Iterator iterator = paramsMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                httpUrlBuilder.addQueryParameter((String) entry.getKey(), (String) entry.getValue());
            }
            requestBuilder.url(httpUrlBuilder.build());
            return requestBuilder.build();
        }
        return null;
    }

    private RequestBody buildJsonRequestBody(Map<String, String> params) {
        JSONObject jsonObject = new JSONObject();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                try {
                    jsonObject.put(entry.getKey(), StringUtil.decode(entry.getValue()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return RequestBody.create(MediaType.parse(HttpConstant.HEADER_APPLICATION_JSON), jsonObject.toString().getBytes());
    }

    private Map<String, String> body2map(Request request, boolean encode) {
        Map<String, String> params = new ArrayMap<>();

        RequestBody requestBody = request.body();
        String subtype = requestBody.contentType().subtype();
        if (subtype.equals(HttpConstant.FORMAT_JSON)) {

        } else {
            FormBody body = (FormBody) request.body();

            for (int i = 0; i < body.size(); i++) {
                if (encode) {
                    params.put(body.encodedName(i), body.encodedValue(i));
                } else {
                    params.put(body.encodedName(i), body.value(i));
                }
            }

            Map<String, String> commonParams = getParams();
            if (MapUtil.isNotEmpty(commonParams)) {
                for (Map.Entry<String, String> entry : commonParams.entrySet()) {
                    params.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return params;
    }


    private HttpUrl buildUrl(Request request) {
        HttpUrl url = request.url();
        HttpUrl.Builder builder = url.newBuilder();
        return builder.build();
    }
}
