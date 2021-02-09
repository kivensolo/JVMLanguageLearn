package com.annotation;

import java.lang.reflect.Field;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/9/19 22:00
 * description:注解处理器
 */
public class FruitInfoUtil {
	public static void getFruitInfo(Class<?> clazz) {
		String strFruitName = " 水果名称：";
		String strFruitColor = " 水果颜色：";
		String strFruitProvicer;

		//返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段。
		Field[] fields = clazz.getDeclaredFields();
		//Field 提供有关类或接口的单个字段的信息，
		//以及对它的动态访问权限。反射的字段可能是一个类（静态）字段或实例字段

		for (Field field : fields) {
//			field.getAnnotation(Class<T> annotationClass)//  如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null。
//			field.getAnnotations() 返回此元素上存在的所有注释。
//			field.getDeclaredAnnotations() 返回直接存在于此元素上的所有注释。
			//如果指定类型的注释存在于此元素上，则返回 true，否则返回 false
			if (field.isAnnotationPresent(FruitName.class)) {
				FruitName fruitName = field.getAnnotation(FruitName.class);//如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null
				strFruitName += fruitName.value();
				System.out.println(strFruitName);
			} else if (field.isAnnotationPresent(FruitColor.class)) {
				FruitColor fruitColor = field.getAnnotation(FruitColor.class);
				strFruitColor = strFruitColor + fruitColor.fruitColor().toString();
				System.out.println(strFruitColor);
			} else if (field.isAnnotationPresent(FruitProvider.class)) {
				FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
				strFruitProvicer = " 供应商编号：" + fruitProvider.id() + " 供应商名称：" + fruitProvider.name() + " 供应商地址：" + fruitProvider.address();
				System.out.println(strFruitProvicer);
			}
		}
	}
}
