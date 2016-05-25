package com.kingz.sort;

import java.util.Arrays;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/5/25 11:39 <br>
 * description: 选择排序，就是选择最小的，然后置换，循环再找到最小的，再置换... <br>
 */
public class SelectSort {
    private static int[] nums = {1, 5, 4, 2, 3, 8, 9, 6, 5, 2, 13, 1, 23, 12, 33, 21};

    public static void main(String[] args) {
        System.out.println("Array values is：" + Arrays.toString(nums));
        selectSort(nums);
        System.out.println("selectSort values is：" + Arrays.toString(nums));
    }

    //选择排序，就是选择最小的，然后置换，循环再找到最小的，再置换....
    public static void selectSort(int[] arr){
        int small,temp;
        for (int i=0; i<arr.length; i++){
                small = i;
                //找出最小的,放到"第一位"
                 for (int j=i+1; j<arr.length; j++){
                        if (arr[small]>arr[j]){
                                small = j;
                        }
                }
                //置换位置
                if (i != small){
                        temp = arr[small];
                        arr[small] = arr[i];
                        arr[i] = temp;
                }
        }
    }
}
