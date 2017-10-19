package com.generic.rawtype;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RawType_1 {

    private static Set union(Set s1, Set s2){
        Set set = new HashSet(s1);
        set.addAll(s2);
        return set;
    }

    public static void main(String[] args) {
        Set<String> guys = new HashSet<>(Arrays.asList("张三","李四","Tom"));
        //Set<String> locations = new HashSet<>(Arrays.asList("四川","湖南","广西"));
        Set<Integer> locations = new HashSet<>();
        locations.add(21);
        locations.add(18);
        locations.add(22);
        Set union = union(guys, locations);
        System.out.println(union);
        Iterator iterator = union.iterator();
        while (iterator.hasNext()){
        }
    }
}
