package com.kkklong;

import java.util.Date;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/12/29 10:33 <br>
 * description: 钢铁侠生产类  克隆<br>
 */
public class CreateIronMan {
    public static void main(String[] args) {
        clonIronMan();
    }

    public static void clonIronMan() {
        SourceIronMain sourceIronMain = new SourceIronMain("001", "白色钢铁侠", new Date());
        SourceIronMain ironMain_1 = sourceIronMain;
        SourceIronMain ironMain_2 = null;
        try {
            ironMain_2 = (SourceIronMain) sourceIronMain.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("sourceIronMain=" + sourceIronMain + "\nironMain_1=" + ironMain_1 + "\nironMain_2=" + ironMain_2);

        System.out.println("ironMain_1 == sourceIronMain 地址？？");
        System.out.println(ironMain_1 == sourceIronMain);
        System.out.println("ironMain_1.equals(sourceIronMain) 内容？？");
        System.out.println(ironMain_1.equals(sourceIronMain));

        System.out.println("ironMain_2 == sourceIronMain 地址？？");
        System.out.println(ironMain_2 == sourceIronMain);
        System.out.println("ironMain_2.equals(sourceIronMain) 内容？？");
        System.out.println(ironMain_1.equals(sourceIronMain));


    }
}
