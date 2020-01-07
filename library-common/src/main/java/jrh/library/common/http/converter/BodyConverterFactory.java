package jrh.library.common.http.converter;


import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.lang.annotation.Annotation;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class BodyConverterFactory extends Converter.Factory {

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        for (Annotation annotation : methodAnnotations) {
            if (annotation instanceof RequestConverter) {
                return new RequestBodyConverterFactory<>((RequestConverter) annotation);
            }
        }
        return null;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        for (Annotation annotation : annotations) {
            if (annotation instanceof ResponseConverter) {
                return new ResponseBodyConverterFactory<>((ResponseConverter) annotation);
            }
        }
        return null;
    }

    final class RequestBodyConverterFactory<T> implements Converter<T, RequestBody> {
        private RequestConverter requestConverter;

        RequestBodyConverterFactory(RequestConverter requestConverter) {
            this.requestConverter = requestConverter;
        }

        @SuppressWarnings("unchecked")
        @Override
        public RequestBody convert(T value) throws IOException {
            try {
                Class clazz = requestConverter.value();
                String param = requestConverter.param();
                RequestBodyConverter converter;
                if (!"".equals(param.trim())) {
                    Constructor constructor = clazz.getConstructor(String.class);
                    converter = (RequestBodyConverter) constructor.newInstance(param);
                } else {
                    Constructor constructor = clazz.getConstructor();
                    converter = (RequestBodyConverter) constructor.newInstance();
                }
                return converter.parse(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    final class ResponseBodyConverterFactory<T> implements Converter<ResponseBody, T> {
        private ResponseConverter responseConverter;

        ResponseBodyConverterFactory(ResponseConverter annotation) {
            this.responseConverter = annotation;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T convert(ResponseBody value) throws IOException {
            try {
                Class clazz = responseConverter.value();
                String param = responseConverter.param();
                ResponseBodyConverter converter;
                if (!"".equals(param.trim())) {
                    Constructor constructor = clazz.getConstructor(String.class);
                    converter = (ResponseBodyConverter) constructor.newInstance(param);
                } else {
                    Constructor constructor = clazz.getConstructor();
                    converter = (ResponseBodyConverter) constructor.newInstance();
                }
                return (T) converter.parse(value);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                value.close();
            }
            return null;
        }
    }
}
