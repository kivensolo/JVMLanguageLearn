package com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/9/19 21:53
 * description:水果供应商的注解定义
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitProvider {
	public  int id() default -1;//供应商编号
	public  String name() default "佚名";//供应商名称
	public  String address() default "成都";//供应商地址
}
