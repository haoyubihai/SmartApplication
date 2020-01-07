package jrh.library.common.http.interceptor;

import androidx.collection.ArrayMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import jrh.library.common.http.HttpConstant;
import jrh.library.common.http.HttpHelper;
import jrh.library.common.utils.MapUtil;
import jrh.library.common.utils.StringUtil;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * desc: 添加公用的参数
 * Created by jiarh on 2018/8/9 14:23.
 */

public class ParamsInterceptor implements Interceptor {

    private boolean isForm;
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request.Builder builder = request.newBuilder();


        builder.url(buildUrl(request));

        RequestBody requestBody = buildRequestBody(request);
        request = buildHeader(builder);
        builder.method(request.method(), requestBody);
        request = builder.build();

        return chain.proceed(request);
    }


    private Request buildHeader(Request.Builder builder) {

//        builder.removeHeader("Content-type");
//        Map<String, String> commonParams = HttpHelper.getParams();
//        String access_token = commonParams.get(HttpHelper.KEY_ACCESS_TOKE);
//        if (isForm){
//            builder.addHeader("Content-type", HttpConstant.HEADER_FORM);
//
//        }else {
//           builder.addHeader("Content-type", HttpConstant.HEADER_APPLICATION_JSON);
//
//        }
//        if (StringUtil.isNotBlank(access_token)){
//            builder.addHeader(HttpHelper.KEY_ACCESS_TOKE,access_token);
//        }

        return  builder.build();
    }

    private RequestBody buildRequestBody(Request request) {
        RequestBody body = request.body();
        if ( body instanceof FormBody) {

        }

//        String method = request.method();
//        if (!method.equals("POST")) {
//            return body;
//        }
//        Map<String, String> params = body2map(request, true);
//        if ( body instanceof FormBody) {
//            FormBody.Builder builder = new FormBody.Builder();
//            if (!params.isEmpty()) {
//                //暂时从参数中传值判断是否是Form表单请求
//                if (params.keySet().contains(HttpConstant.PARAMS_TOKEN)){
//                   params.remove(HttpConstant.PARAMS_TOKEN);
//                }
//                if (params.keySet().contains(HttpConstant.FORMAT_FORM)) {
//                    //移除标志位
//                    params.remove(HttpConstant.FORMAT_FORM);
//                    isForm = true;
//                    for (Map.Entry<String, String> entry : params.entrySet()) {
//                        builder.addEncoded(entry.getKey(), entry.getValue());
//                    }
//                    return builder.build();
//                } else {
//                    isForm = false;
//                    return buildJsonRequestBody(params);
//                }
//            }else {
//                JSONObject jsonObject = new JSONObject();
//                return RequestBody.create(MediaType.parse(HttpConstant.HEADER_APPLICATION_JSON), jsonObject.toString().getBytes());
//            }
//        }
        return body;
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
        if (subtype.equals(HttpConstant.FORMAT_JSON)){

        }else {
            FormBody body = (FormBody) request.body();

            for (int i = 0; i < body.size(); i++) {
                if (encode) {
                    params.put(body.encodedName(i), body.encodedValue(i));
                } else {
                    params.put(body.encodedName(i), body.value(i));
                }
            }

            Map<String, String> commonParams = HttpHelper.getParams();
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
