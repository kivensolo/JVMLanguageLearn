package com.clonepra;

import java.util.Date;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/12/29 11:06 <br>
 * description: 分析深度克隆和浅客隆 <br>
 */
public class CreateDeepIron {
    public static void main(String[] args) {
        DeepCloneIron ironMain = new DeepCloneIron(new SourceIronMain("002","2号",new Date()));

        DeepCloneIron ironMain_clone = null;
        try {
            ironMain_clone = (DeepCloneIron) ironMain.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("ironMain.getmIronMan() ==" + ironMain.getmIronMan());
        System.out.println("ironMain_clone.getmIronMan() ==" + ironMain_clone.getmIronMan());
        System.out.println("ironMain.getmIronMan() == ironMain_clone.getmIronMan() ??");
        System.out.println(ironMain.getmIronMan() == ironMain_clone.getmIronMan());
        System.out.println("ironMain.getmIronMan().equals(ironMain_clone.getmIronMan()) ??");
        System.out.println(ironMain.getmIronMan().equals(ironMain_clone.getmIronMan()));

    }
}
