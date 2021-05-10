package com.desgin.structure_type._04_adapter.objectAdapter;

import com.desgin.structure_type._04_adapter.adapter.ITargetable;
import com.desgin.structure_type._04_adapter.adapter.Source;

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
        ITargetable target = new SourceWrapper(source);
        target.method1();
        target.method2();
    }
}
