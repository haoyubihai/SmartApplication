package jrh.library.common.http.converter;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * desc:
 * Created by jiarh on 2018/8/9 16:08.
 */

@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface ResponseConverter {
    Class<? extends ResponseBodyConverter> value();

    String param() default "";
}
