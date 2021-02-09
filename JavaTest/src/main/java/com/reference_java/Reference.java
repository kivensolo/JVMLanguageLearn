package com.reference_java;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/7/13 20:11 <br>
 * description: 强引用测试 <br>
 *      被一个对象0被创建的时候，放在Heap中，当GC运行的时候, 如果发现没有任何引用指向o, o就会被回收以腾出内存空间.
 * 或者换句话说, 一个对象被回收, 必须满足两个条件: 1)没有任何引用指向它 2)GC被运行.
 */
public class Reference {
    public static void main(String[] args) {

        Object referenceObj = new Object();

        /**
         * 赋值创建强引用
         */
        Object strongReference = referenceObj;

        System.out.println("referenceObj = " + referenceObj + "\n"
                + "strongReference = " + strongReference);

        referenceObj = null;
        System.gc();
        /**
         * StrongReference 在 GC 后不会被回收
         */
   System.out.println("referenceObj置空  执行GC ----------- \n" +
           "referenceObj = " + referenceObj + "\n" +
           "strongReference = " + strongReference);

    }
}
