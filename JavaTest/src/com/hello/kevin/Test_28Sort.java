package com.hello.kevin;

import java.util.Arrays;

public class Test_28Sort {

    /**
     * @param args
     * @Description： 题目：对10个数进行排序。
     * 1.程序分析：用双支点快速排序方法—————DualPivotQuicksort
     * <p/>
     * <p/>
     * EX: 生成随机10个数排序，并输入1个数，插入重排序输出：
     * @Return void
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int arr[] = {1, 3, 12, 123, 432, 21, 5, 6, 7, 8};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }

}

