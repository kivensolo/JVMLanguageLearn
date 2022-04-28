package com.reflect.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 测试代码，实现对类对象属性的类类型判断逻辑
 */
public class ClassRelationship {

    class Animal{
        List<Cat> cats;
        List<Dog>  dogs;
        //    List l ;
        Map<Integer, String> map ;
        int type;
    }

    class Cat {}
    class Dog{}

    public static void main(String[] args) {
        Class tc = Animal.class;
        Field[] fields = tc.getDeclaredFields();
        for (Field f : fields) {
            Class fc = f.getType();
            if(fc.isPrimitive()){ //是否为基本数据类型
                System.out.println("基本数据类型： " + f.getName() + "  变量名:" + fc.getName());
            }else{
                if(fc.isAssignableFrom(List.class)){ //判断是否为List的类型
                    System.out.println("发现List类型：" + f.getName());
                    Type gt = f.getGenericType();    //得到泛型类型
                    ParameterizedType pt = (ParameterizedType)gt;
                    Class lll = (Class)pt.getActualTypeArguments()[0];
                    System.out.println("\t\t" + lll.getName());
                }
            }
        }
    }
}
