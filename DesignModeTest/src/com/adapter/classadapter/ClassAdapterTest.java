package com.adapter.classadapter;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/8/24 14:12 <br>
 * description: XXXXXXX <br>
 */
public class ClassAdapterTest {
    public static void main(String[] args) {
        ITargetable target = new Adapter();
        target.method1();
        target.method2();
    }
}
