package com.kingz.godlike;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2017/2/27 17:00 <br>
 * description:接口在工厂模式中的应用 P188练习18 <br>
 */
public class Test_188_18 {
    private static void cretaeCycle(CycleFactory cycleFactory){
        Cycle cycle = cycleFactory.createCycle();
        cycle.bikeRiding();
    }

    //public static void main(String[] args) {
    //    cretaeCycle(new UnicycycleFactory());
    //    cretaeCycle(new BicycleFactory());
    //    cretaeCycle(new TricycleFactory());
    //}

    private static void cretaeCycle2(Cycle cycle){
        cycle.bikeRiding();
    }
    public static void main(String[] args) {
        cretaeCycle2(new Unicycycle());
        cretaeCycle2(new Bicycle());
        cretaeCycle2(new Tricycle());
    }


}
interface Cycle{
    void bikeRiding();
}

interface CycleFactory{
    Cycle createCycle();
}

class Unicycycle implements  Cycle{
    @Override
    public void bikeRiding() {
        System.out.println("Unicycycle riding");
    }
}
class UnicycycleFactory implements  CycleFactory{
    @Override
    public Cycle createCycle() {
        return new Unicycycle();
    }
}
class Bicycle implements Cycle{
    @Override
    public void bikeRiding() {
        System.out.println("Bicycle riding");
    }
}
class BicycleFactory implements  CycleFactory{
    @Override
    public Cycle createCycle() {
        return new Bicycle();
    }
}

class Tricycle implements Cycle{
    @Override
    public void bikeRiding() {
        System.out.println("Tricycle riding");
    }
}
class TricycleFactory implements  CycleFactory{
    @Override
    public Cycle createCycle() {
        return new Tricycle();
    }
}

