package com.think.in.uptotype;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/6 13:57
 * description:
 */
public class CycleTest {
    public static void main(String[] args) {
        //Cycle cc = new Cycle();
        uniCycle ucc = new uniCycle();
        //Bicycle bcc = new Bicycle();
        //Tricycle tcc = new Tricycle();
        //ride(cc);
        ride(ucc);
        //ride(bcc);
        //ride(tcc);
    }
    public static void ride(Cycle cycle){
        cycle.ride(RideWay.Way_A);
    }
}
