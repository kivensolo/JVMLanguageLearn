package com.decorator;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
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
