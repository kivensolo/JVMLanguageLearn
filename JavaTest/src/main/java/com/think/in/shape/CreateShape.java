package com.think.in.shape;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/17 17:15
 * description:
 */
public class CreateShape {

    public static RandomShapeGenerator gen = new RandomShapeGenerator();
    public static void main(String[] args) {
        Shape[] s = new Shape[9];
        for (int i = 0; i < s.length; i++) {
              s[i] = gen.next();
        }
        for(Shape shp:s){
            shp.draw();
        }
    }
}
