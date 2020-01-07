package jrh.library.common.http.converter;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 将特殊的参数通过{@link RequestBodyConverter}转换成RequestBody，
 * 需要进行转换的参数一定要加上@Body
 *
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface RequestConverter {

    Class<? extends RequestBodyConverter> value();

    String param() default "";
}
