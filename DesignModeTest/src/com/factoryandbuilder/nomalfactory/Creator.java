package com.factoryandbuilder.nomalfactory;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/1/25 14:27 <br>
 * description: 抽象工厂类 <br>
 */
public abstract class Creator {
    /*
    * 创建一个产品对象，其输入参数类型可以自行设置
    * 通常为String、Enum、Class等，当然也可以为空
    */
    public abstract <T extends Product> T createProduct(Class<T> c);
}
