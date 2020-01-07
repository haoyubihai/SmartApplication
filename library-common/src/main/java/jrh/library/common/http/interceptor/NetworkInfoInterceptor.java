package jrh.library.common.http.interceptor;

import java.io.EOFException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;
import timber.log.Timber;

import static okhttp3.internal.platform.Platform.INFO;

/**
 * desc:
 * Created by jiarh on 2018/8/9 14:24.
 */

public class NetworkInfoInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private boolean mShowHeader;

    public NetworkInfoInterceptor(){
        Timber.tag("networkInfo");
    }

    public NetworkInfoInterceptor header(boolean show) {
        this.mShowHeader = show;
        return this;
    }

    public interface Logger {
        void log(String message);

        Logger DEFAULT = new Logger() {
            @Override public void log(String message) {
                Platform.get().log(INFO, message, null);
            }
        };
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        StringBuilder builder = new StringBuilder();

        // append headers
        // 打印header，需要的时候可以打开注释，方便查看
        if (mShowHeader && request.headers() != null) {
            builder.append("headers: " + request.headers().toString());
        }

        // append method and url
        builder.append(request.method() + " url: " + request.url());

        // append body
        this.printBody(request, builder);

        Timber.i("START -> " + builder.toString());

        long begin = System.currentTimeMillis();
        Response response = chain.proceed(request);
        long duration = System.currentTimeMillis() - begin;

        // append response code and cost time
        builder.append("\nresponse code: " + response.code() + ", cost time: " + duration + "ms");
        ResponseBody responseBody = response.body();
        // append response body if success
        if (response.isSuccessful()) {
            if (responseBody.contentLength() != 0) {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE);
                Buffer buffer = source.buffer();
                if (isPlaintext(buffer)) {
                    builder.append("\nresult: " + buffer.clone().readString(Charset.forName("UTF-8")));
                }
            }
        } else {
            builder.append("\nmessage: " + response.message());
        }
        // print it
        Timber.i("END -----------------------> " + builder.toString());
        return response;
    }

    private String decode(String str) {
        try {
            str = str.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
            str = str.replaceAll("\\+", "%2B");
            return URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    private void printBody(final Request request, StringBuilder builder) {
        RequestBody requestBody = request.body();
        if (requestBody == null) {
            return;
        }
//        if (requestBody instanceof FormBody) {
//            printFormBody((FormBody) requestBody, builder);
//        }
//        else if (isJsonRequestBody(requestBody)) {
            printJsonRequestBody(requestBody, builder);
//        }
    }

    private boolean isJsonRequestBody(RequestBody requestBody) {
        MediaType type = requestBody.contentType();
        return false;
    }

    private void printJsonRequestBody(final RequestBody requestBody, StringBuilder builder) {
        try {
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            if (isPlaintext(buffer)) {
                builder.append("\nbody: ").append(buffer.readString(charset));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printFormBody(final FormBody body, StringBuilder builder) {
        try {
            if (body.size() <= 0) {
                return;
            }
            builder.append("\nbody: ");
            for (int i = 0; i < body.size(); i++) {
                if (i > 0) {
                    builder.append(", ");
                }
//                builder.append(body.encodedName(i) + "=" + decode(body.encodedValue(i)));
                builder.append(body.encodedName(i) + "=" + body.encodedValue(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small sample
     * of code points to detect unicode control characters commonly used in binary file signatures.
     */
    private boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }
}
