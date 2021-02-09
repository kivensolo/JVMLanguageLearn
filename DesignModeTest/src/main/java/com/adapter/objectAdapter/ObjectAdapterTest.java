package com.adapter.objectAdapter;

import com.adapter.classadapter.ITargetable;
import com.adapter.classadapter.Source;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/8/24 14:12 <br>
 * description: XXXXXXX <br>
 */
public class ObjectAdapterTest {
    public static void main(String[] args) {
        Source source = new Source();
        ITargetable target = new Wrapper(source);
        target.method1();
        target.method2();
    }
}
