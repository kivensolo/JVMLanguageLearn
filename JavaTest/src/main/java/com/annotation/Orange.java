package com.annotation;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/11/12 22:24
 * description:注解的使用
 */
public class Orange {
	@FruitName("Orange")
	private String appleName;

	@FruitColor(fruitColor = FruitColor.Color.RED)
	private String appleColor;

	@FruitProvider(id=10000,name = "kingz",address = "龙腾苑")
	private String appProvider;
}
