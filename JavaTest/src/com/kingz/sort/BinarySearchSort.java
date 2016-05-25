package com.kingz.sort;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/5/25 11:35 <br>
 * description: 二分法  二叉查找树 <br>
 */
public class BinarySearchSort {
    private static int mid;
    public static void main(String[] args) {

    }

    public static int binarySearch(int[] a, int value) {
        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            mid = (low + high) / 2; //**
            if (a[mid] == value)
                return mid;
            else if (a[mid] > value)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
        //mid = (low + high) / 2; 有问题，当low + high大于int范围时就会溢出的。Sun的jdk里面的二分查找源码原先也有同样的问题。
        //解决的方法是mid = low/2 + high/2。这样用2先除一下，就不会溢出了。
    }
}
