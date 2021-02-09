package com.kingz.sort.switch_sort;

import java.util.Arrays;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/5/25 11:38 <br>
 * description: 交换排序------冒泡排序 <br>
 *  该算法是专门针对已部分排序的数据进行排序的一种排序算法。如果数据中只有一两个数据是乱序的，那这种就是最快的排序算法。
 * 如果所有数据都是乱序的，那这种算法就是最慢的。
 *
 * 冒出最大或者最小的，接下来的遍历次数都会减少
 */
public class BubbleSort {
    private static int[] nums = {1, 5, 4, 2, 3, 8, 9, 6, 5, 2, 13, 1, 23, 12, 33, 21};

    public static void main(String[] args) {
        System.out.println("Array values is：" + Arrays.toString(nums));
        bubbleSortSmall2Big(nums);
        System.out.println("bubbleSort values is：" + Arrays.toString(nums));
        bubbleSortBig2Small(nums);
        System.out.println("bubbleSort values is：" + Arrays.toString(nums));
    }

    /**
     * 从小到大
     *
     * @param arry
     */
    public static void bubbleSortSmall2Big(int[] arry) {
        int temp;
        //一共要比较 n - 1 次
        for (int i = 0; i < arry.length - 1; i++) {
            //每一次比较剩余的（冒出其中最大的）
            for (int j = 0; j < arry.length - 1 - i; j++) {
                //若前面的大于后面的，就换位置
                if(arry[j] > arry[j + 1]){
                    temp = arry[j+1];
                    arry[j+1] = arry[j];
                    arry[j] = temp;
                }
            }
        }
    }

    /**
     * 从大到小
     *
     * @param arry
     */
    public static void bubbleSortBig2Small(int[] arry) {
        int temp;
        //最多做 n - 1 趟排序
        for (int i = 0; i < arry.length - 1; i++) {
            for (int j = 0; j < arry.length - 1 -i; j++) {
                //把小的值交换到后面，如果要改变排序方向，改下这个判断即可
                if (arry[j] < arry[j + 1]) {
                    temp = arry[j];
                    arry[j] = arry[j + 1];
                    arry[j + 1] = temp;
                }
            }
             System.out.println("比较 +1：" + Arrays.toString(nums));
        }
    }
}
