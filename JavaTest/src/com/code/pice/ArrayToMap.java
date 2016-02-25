package com.code.pice;

import org.apache.commons.lang.ArrayUtils;

import java.util.Map;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/25 11:02
 * description: 数组转为Map，需用到commons.lang包
 */
public class ArrayToMap {

    public static void main(String[] args) {
        String[][] countries = {{"United States", "New York"}, {"United Kingdom", "London"},
                {"Netherland", "Amsterdam"}, {"Japan", "Tokyo"}, {"France", "Paris"}};

        Map countryCapitals = ArrayUtils.toMap(countries);

        System.out.println("Capital of Japan is " + countryCapitals.get("Japan"));
        System.out.println("Capital of France is " + countryCapitals.get("France"));
    }
}
