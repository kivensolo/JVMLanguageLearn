package com.annotation;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/9/19 22:18
 * description:
 */
public class FruitTest {
	public static void main(String[] args) {
		FruitInfoUtil.getFruitInfo(Apple.class);
		FruitInfoUtil.getFruitInfo(Orange.class);
	}
}
