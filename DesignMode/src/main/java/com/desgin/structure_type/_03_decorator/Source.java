package com.desgin.structure_type._03_decorator;

/**
 * author: King.Z <br>
 * date:  2016/8/25 17:02 <br>
 * description: XXXXXXX <br>
 */
public class Source implements Sourceable{
    @Override
    public void method() {
        System.out.println("the original method!!");
    }
}
