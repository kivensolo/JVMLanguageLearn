package com.think.in.uptotype;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/6 13:52
 * description:
 */
public class Cycle{
    public void ride(RideWay rideWay){
        System.out.println("Cycle.ride()  ------>" + rideWay);
    }
}
enum RideWay{
    Way_A,Way_B,Way_C
}
class uniCycle extends Cycle{
    @Override
    public void ride(RideWay rideWay) {
        System.out.println("uniCycle.ride()  ------>" + rideWay);
        super.ride(rideWay);
    }
}

class Bicycle extends Cycle{
    @Override
    public void ride(RideWay rideWay) {
        super.ride(rideWay);
        System.out.println("Bicycle.ride()  ------>" + rideWay);
    }
}

class Tricycle extends Cycle{
    @Override
    public void ride(RideWay rideWay) {
        super.ride(rideWay);

    }
}
