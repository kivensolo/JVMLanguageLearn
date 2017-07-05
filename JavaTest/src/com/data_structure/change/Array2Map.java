package com.data_structure.change;

import java.util.HashMap;
import java.util.Map;

/**
 * author: King.Z
 * date:  2016/2/25 11:02
 * description:
 * 二维数组转为Map，需用到commons.lang包
 */
public class Array2Map {

    public static void main(String[] args) {
        String[][] countries = {{"United States", "New York"},
                                {"United Kingdom", "London"},
                                {"Netherland", "Amsterdam"},
                                {"Japan", "Tokyo"},
                                {"France", "Paris"}};

        //Map countryCapitals = ArrayUtils.toMap(countries);
        Map countryCapitals = toMap(countries);

        System.out.println("Capital of Japan is " + countryCapitals.get("Japan"));
        System.out.println("Capital of France is " + countryCapitals.get("France"));
    }

    public static Map toMap(Object[] array) {
        if (array == null) {
            return null;
        } else {
            //初始化容量大小  数组的1.5倍
            HashMap map = new HashMap((int) ((double) array.length * 1.5D));
            //循环遍历数组----->map
            for (int i = 0; i < array.length; ++i) {
                Object object = array[i];
                if (object instanceof Map.Entry) {
                    Map.Entry entry = (Map.Entry) object;
                    map.put(entry.getKey(), entry.getValue());
                } else {
                    if (!(object instanceof Object[])) {
                        throw new IllegalArgumentException("Array element " + i + ", \'" + object + "\', is neither of type Map.Entry nor an Array");
                    }
                    Object[] var5 = (Object[]) object;
                    if (var5.length < 2) {
                        throw new IllegalArgumentException("Array element " + i + ", \'" + object + "\', has a length less than 2");
                    }

                    map.put(var5[0], var5[1]);
                }
            }

            return map;
        }
    }
}
