package com.desgin.structure_type._04_adapter.adapter;

/**
 * author: King.Z <br>
 * date:  2016/8/24 14:09 <br>
 * description: 适配器模式 <br>
 *   核心思想就是：有一个Source类，拥有一些方法，待适配，
 *   目标接口是Targetable，通过Adapter类，将Source的功能扩展到Targetable里
 */
public class Source {
    public void method1() {
        System.out.println("this is original method!");
    }
}

