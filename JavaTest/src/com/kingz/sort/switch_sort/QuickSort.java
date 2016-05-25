package com.kingz.sort.switch_sort;

import java.util.Arrays;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/5/25 11:28 <br>
 * description: 交换排序------快速排序 <br>
 * ------|通过一趟排序，将排序记录分割成独立的两部分，其中一部分记录的关键字均比另一部分记录的关键字小，则可以分别对这两部分
 * 记录的数据进行排序，以达到整个序列有序。
 * 具体做法：
 * ------|使用两个指针，low/high,初值分别设置为序列的开头，和序列的尾，设置pivotkey(枢纽关键)为第一个记录，然后从高向
 * 前搜索小于pivotkey的记录和pivotkey所在位置进行交换，然后从low开始向后搜索第一个大于pivotkey的记录和此时pivotkey
 * 所在位置进行交换，重复直到low=high为止。
 */
public class QuickSort {
    private static int[] nums = {10, 5, 4, 2, 3, 8, 9, 6, 5, 2, 13, 1, 23, 12, 33, 21};

    public static void main(String[] args) {
        System.out.println("Array values is：" + Arrays.toString(nums));
        quick();
        System.out.println("Sort values is：" + Arrays.toString(nums));
    }

    public static void quick() {
        if (nums.length > 0) {
            _quickSort(nums, 0, nums.length - 1);
        }
    }

    public static void _quickSort(int[] arry, int low, int high) {
        if (low < high) {
            int mid = getMiddle(arry, low, high);
            _quickSort(arry,low,mid - 1);  //对低字表进行递归排序
            _quickSort(arry,mid + 1,high); //对高字表进行递归排序
        }
    }

    public static int getMiddle(int[] arry, int low, int high) {
        int temp = arry[low];//将第一个作为中轴
        while (low < high) {
            while ((low < high) && (arry[high] >= temp)) {
                high--;
            }
            arry[low] = arry[high];//比中轴小的记录移动到低端

            while ((low < high) && (arry[low] <= temp)) {
                low++;
            }
            arry[high] = arry[low];//比中轴大的记录移动到高端
        }
        arry[low] = temp;//中轴记录
        return low; //返回中洲的位置
    }


}
