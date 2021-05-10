package com.desgin.structure_type._03_decorator;

/**
 * author: King.Z <br>
 * date:  2016/8/25 17:20 <br>
 * description: <br>
 */
public class DecoratorTest {
    public static void main(String[] args) {
        Sourceable source = new Source();
        Sourceable obj = new Decorator(source);
        //这一段代码错了吧，应该是 Decorator obj = new Decorator(source); ，你这种应该叫做代理模式  好像有理
        obj.method();
    }
}
