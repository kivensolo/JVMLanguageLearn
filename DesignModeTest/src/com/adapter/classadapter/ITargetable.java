package com.adapter.classadapter;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/8/24 14:10 <br>
 * description: 增加一个与Srouce类同名的方法
 *    这样Targetable接口的实现类就具有了Source类的功能<br>
 */
public interface ITargetable {

    /* 与原类中的方法相同 */
    public void method1();

    /* 新类的方法 */
    public void method2();
}
