package com.desgin.base;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * author: King.Z <br>
 * date:  2017/1/24 17:07 <br>
 * description: 设计模式之里氏替换原则 <br>
 * 里氏替换原则也要求制定一个契约，就是父类或接口，这种设计方法也叫做
 * Design by Contract（契约设计）
 * 里氏替换原则为良好的继承定义了一个规范，其中包含的意思如下:
 * '1.子类必须完全实现父类的方法
 * '2.子类可以有自己的实现逻辑
 * '3.覆盖或实现父类的方法时[输入参数可以被放大]
 * '4.覆写或实现父类的方法时[输出结果可以被缩小]
 *
 * 采用里氏替换原则的目的就是增强程序的健壮性，版本升级时也可以保持非常好的兼容
    性。即使增加子类，原有的子类还可以继续运行。
 */
public class GoodExtends {
    public static void main(String[] args) {
        SonEnlarg f = new SonEnlarg();
        HashMap map = new HashMap();
        f.doSomething(map);
    }
}
class Father {
    public Collection doSomething(HashMap map) {
        System.out.println("111");
        return map.values();
    }
}

class SonEnlarg extends Father {
    public Collection doSomething(Map map) {
        System.out.println("222");
        return map.values();
    }
}


