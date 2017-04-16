package com.annotation.requestDemo;

import com.annotation.Orange;
import com.reflect.DumpMethods;

import java.lang.annotation.*;

/**
 * date:  2017/4/16 20:08 <br>
 * description: 模拟一个网络请求的注解 <br>
 *      1.自定义注解 {@link ReqUrl,ReqType,ReqParam}
 *      2.使用自定义注解 {@link ILoginApi,Orange}
 *      3.获取注解参数  {@link DumpMethods#findAnnotationMethods()}
 *      4.具体实现注解接口调用
 */
@Documented
@Target({ElementType.FIELD,
        ElementType.METHOD,
        ElementType.LOCAL_VARIABLE,
        ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ReqType {
    /**
     * @return the ReqType Type
     */
    ReqTypeEnum[] value();

    ReqTypeEnum reqType() default ReqTypeEnum.POST;
}
