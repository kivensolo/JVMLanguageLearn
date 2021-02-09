package com.kingz.sort.switch_sort;

import java.util.Arrays;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/5/25 11:28 <br>
 * description: 交换排序------快速排序 <br>
 * ------|通过一趟排序，首先任意选取一个数据（通常选用数组的第一个数）作为关键数据，然后将所有比它小的数都放到它前面，所有比它大的数都放到它后面，这个过程称为一趟快速排序。
 * 具体做法：
 * ------|使用两个指针，low/high,初值分别设置为序列的开头，和序列的尾，设置pivotkey(关键)为第一个记录，然后从高向
 * 前搜索小于pivotkey的记录和pivotkey所在位置进行交换，然后从low开始向后搜索第一个大于pivotkey的记录和此时pivotkey
 * 所在位置进行交换，重复直到low=high为止。
 */
public class QuickSort {
    private static int[] nums = {5, 3, 4, 2, 3, 12, 33, 4, 7, 11, 6};

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
            _quickSort(arry, low, mid - 1);  //对低字表进行递归排序
            _quickSort(arry, mid + 1, high); //对高字表进行递归排序
        }
    }

    public static int getMiddle(int[] arry, int low, int high) {
        int key = arry[low];
        while (low < high) {
            while ((low < high) && (arry[high] >= key)) {
                high--;
            }
            arry[low] = arry[high];//高轴指针处发现有比基准值小的值，将所指值赋值给基准值。

            while ((low < high) && (arry[low] <= key)) {
                low++;
            }
            arry[high] = arry[low];//将比新基准值还大的值替换到最高轴处
        }
        arry[low] = key;//将原基准值放入第一次比较后的中轴指针处
        return low;
    }


}
