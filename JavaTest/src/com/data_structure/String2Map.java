package com.data_structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/5/24 17:22 <br>
 * description: XXXXXXX <br>
 */
public class String2Map {
    public static void main(String[] args) {
        Map<String, String> mMap = new HashMap<>();
        mMap.put("四川", "成都");
        mMap.put("新疆", "乌鲁木齐");
        mMap.put("湖南", "长沙");
        mMap.put("贵州", "贵阳");
        mMap.put("广州", "深圳");

        System.out.println("--1:当前Map数据：" + mMap.toString());//无序
        System.out.println("--2:Keys:" + mMap.keySet());

        Set<Map.Entry<String, String>> entrySet = mMap.entrySet();
        System.out.println("--3:循环输出省会+省城：");
        PrintAllItemOfMap(entrySet);

        System.out.println("--4:获取云南的省城：" + mMap.get("云南"));
        String firstKey = mMap.keySet().iterator().next();
        System.out.println("--5: First Key in Map：" + firstKey);
        mMap.remove(firstKey);
        System.out.println("--6:删除第一个，再次循环输出省会+省城：");
        PrintAllItemOfMap(entrySet);

        entrySet.clear();
        System.out.println("--7:Clear All , isEmpty:" + entrySet.isEmpty());

    }

    private static void PrintAllItemOfMap(Set<Map.Entry<String, String>> mEntrySet) {
        for (Map.Entry<String, String> item : mEntrySet) {
            System.out.println("    " + item.getKey() + "   " + item.getValue());
        }
    }
}
