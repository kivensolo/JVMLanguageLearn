package com.kingz.sort.switch_sort;

import java.util.Arrays;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/5/26 17:58 <br>
 */
public class QuickTest {
    private static int[] nums = {5, 3, 4, 2, 3, 12, 33, 4, 7, 11, 6};

    public static void main(String[] args) {
        _quickSort(nums, 0, nums.length - 1);
    }

    private static void _quickSort(int[] nums, int low, int hight) {
        int key = nums[low];
        while (low < hight) {
            while ((low < hight) && (nums[hight] >= key)) {
                hight--;
            }
            nums[low] = nums[hight];
            while ((low < hight) && (nums[low] <= key)) {
                low++;
            }
            //比新基准值大，则说明比此时的hight值大
            nums[hight] = nums[low];
            System.out.println("low=" + low + "     " + "hight=" + hight + "  " + Arrays.toString(nums));
        }
        nums[low] = key;
        System.out.println("二分一次：" + Arrays.toString(nums));
    }
}
