package com.kingz.godlike;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/3/11 11:18
 * description: 将ID和学生对象通过键与值存储到map集合中
 */
public class PeopleMap {
    public static void main(String[] args) {
        TreeMap<Integer, People> mainMap = new TreeMap<>();
        mainMap.put(1001, new People("tiantian", "21", "girl"));
        mainMap.put(1002, new People("xiaohua", "22", "girl"));
        mainMap.put(1003, new People("wangsan", "15", "girl"));
        mainMap.put(1005, new People("kiven", "20", "boy"));
        Set<Map.Entry<Integer, People>> entrySets = mainMap.entrySet();

        TreeMap<Integer, People> subMap = new TreeMap<>();
        subMap.put(1000,new People("aaaaaaa","22","boy"));
        subMap.put(10012,new People("bbbbb","22","boy"));
        subMap.put(1008,new People("ccccc","22","boy"));
        mainMap.putAll(subMap);

        System.out.println("当前map中第一组数据：" + mainMap.firstEntry().toString());

        for(Map.Entry<Integer,People> single :entrySets){
            System.out.println("ID："+single.getKey() + "\t"+"Detail：" + single.getValue());
        }

        ArrayList<People> arrayList = new ArrayList<>();
        arrayList.add(mainMap.get(1000));
        arrayList.add(mainMap.get(10012));
        arrayList.add(mainMap.get(1008));
        arrayList.add(mainMap.get(1005));
        for(People list:arrayList){
            System.out.println("list中的数据：" + list.toString());
        }
    }
}
class People{
    String name;
    String age;
    String sex;

    public People(String name, String age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}