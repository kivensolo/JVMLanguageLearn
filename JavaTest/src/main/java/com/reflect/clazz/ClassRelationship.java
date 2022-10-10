package com.reflect.clazz;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * 测试代码，实现对类对象属性的类类型判断逻辑
 */
public class ClassRelationship {

    static class Cat {}
    static class Dog{}

    class Animal{
        List<Cat> cats;
        List<Dog>  dogs;
        Map<Integer, String> map ;
        int type;
    }


    public static void main(String[] args) {
        Class<Animal> animalClass = Animal.class;
        Field[] fields = animalClass.getDeclaredFields();
        for (Field f : fields) {
            Class<?> classType = f.getType();
            if(classType.isPrimitive()){ //是否为基本数据类型
                System.out.println("基本数据类型:" + classType.getName() + ",变量名:" + f.getName());
            }else{
                if(classType.isAssignableFrom(List.class)){ //判断是否为List的类型
                    System.out.println("发现List类型：" + f.getName());
                    //Tyoe是Java中所有类型的公共超级接口。这些类型包括原始类型、参数化类型、数组类型、类型变量和基本类型。
                    ParameterizedType pt = (ParameterizedType) f.getGenericType();    //得到泛型类型
                    Class lll = (Class)pt.getActualTypeArguments()[0];
                    System.out.println("\t name = " + lll.getName());
                }
            }
        }
    }
}