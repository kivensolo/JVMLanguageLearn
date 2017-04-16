package com.annotation.requestDemo;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * date:  2017/4/16 20:40 <br>
 * description: 请求参数 <br>
 */
@Documented
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface ReqParam {
    String value() default "";
}

