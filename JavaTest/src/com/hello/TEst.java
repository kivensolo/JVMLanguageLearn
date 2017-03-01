package com.hello;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/6/29 18:09 <br>
 * description: XXXXXXX <br>
 */
class Useful {
    public void a() {System.out.println("A...");}
    public void b() {System.out.println("B...");}
}
class MoreUseful extends Useful {
    public void a() {System.out.println("A...");}
    public void b() {System.out.println("B...");}
    public void c() {System.out.println("C...");}
}

public class TEst {
    public static void main(String[] args) {
        Useful[] x = {new Useful(),new MoreUseful()};
        x[0].a();
        x[1].b();
        ((MoreUseful)x[1]).c();
        ((MoreUseful)x[0]).c();
    }
}
