package com.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * 通过反射实例化泛型类
 */
public class GenRFTest {
    public static void main(String[] args) {
        new Transformer<RelectTarget>().load(RelectTarget.class);
    }

    static class Transformer<T>{
        String className = "";
        public T load(Class<T> clazz){
            String className = clazz.getName();
            return transform(className);
        }

        public T transform(String className){
            ClassLoader classLoader = getClass().getClassLoader();
            T instance = null;
            try {
                Class<?> target = classLoader.loadClass(className);
                instance = (T) target.newInstance();
                Field[] fields = target.getDeclaredFields();
                for (Field field : fields) {
                    Type genericType = field.getGenericType();
                    System.out.print("genericType="+genericType);
                    field.setAccessible(true);
                    String name = field.getName();
                    Class<?> fieldType = field.getType();
                    if (fieldType.isPrimitive()) {
                        switch (genericType.toString()){
                            case "boolean":
                                System.out.print("布尔");
                                try {
                                    field.set(instance,Boolean.parseBoolean("false"));
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "char":
                                System.out.print("字符");
                                break;
                            case "byte":
                                System.out.print("字节");
                                break;
                            case "short":
                                System.out.print("short");
                                break;
                            case "int":
                                try {
                                    field.set(instance, Integer.parseInt("222"));
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                System.out.print("数字");
                                break;
                            case "float":
                                System.out.print("浮点数字");
                                break;
                            case "long":
                                System.out.print("long数字");
                                break;
                            case "double":
                                System.out.print("double数字");
                                break;

                        }
                    }else{
                        if("class java.lang.String".equals(genericType.toString())){ //TODO kotlin String
                            field.set(instance, "被修改的字符串");
                            System.out.print("字符串");
                        }else if("class java.lang.Map".equals(genericType.toString())){
                            System.out.print("Map");
                        }else{
                            //对象类型
                            String cName = field.getType().getName();
                            //先检查XulDataNode中是否有相关数据，再决定是否实例化
                            Object subBean = transform(cName);
                            field.set(instance, subBean);
                        }
                    }
                    System.out.println("field = "+name);

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return instance;
        }
    }
}
