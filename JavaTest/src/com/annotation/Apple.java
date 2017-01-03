package com.annotation;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/9/19 21:55
 * description: 注解的使用
 */
public class Apple {
	@FruitName(value = "Apple")
	private String appleName;

	@FruitColor(fruitColor = FruitColor.Color.RED)
	private String appleColor;

	@FruitProvider(id=10086,name = "kingz",address = "中和")
	private String appProvider;

	public String getAppleName() {
		return appleName;
	}

	public void setAppleName(String appleName) {
		this.appleName = appleName;
	}

	public String getAppleColor() {
		return appleColor;
	}

	public void setAppleColor(String appleColor) {
		this.appleColor = appleColor;
	}

	public String getAppProvider() {
		return appProvider;
	}

	public void setAppProvider(String appProvider) {
		this.appProvider = appProvider;
	}

	public void displayName(){
		System.out.println("水果名字是：苹果");
	}
}
