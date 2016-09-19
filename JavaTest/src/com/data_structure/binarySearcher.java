package com.data_structure;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/9/4 16:50
 * description:二分查找法，适用于有序排列的数据，升序或者降序。
 *  首先，假设表中元素是按升序排列，将表中间位置记录的关键字与查找关键字比较，如果两者相等，则查找成功
 *
 */
public class binarySearcher {

    public static void main(String[] args) {
	int[] data  ={1,2,3,4,5,6,7,8,9,12,13,14,15,22,23,24,25};
	System.out.println("对应位置："+ binarySearche(data,24));
    }

    /**
     * 查找等于value的值位于数组哪里
     * @param arr
     * @param value
     * @return
     */
    public static int binarySearche(int[] arr,int value){
	int low = 0;
	int height = arr.length -1;
	while (low <= height){
	    int mid = low/2 + height/2; //初始中点位置
	    System.out.println("查找+1			"+arr[mid]);
	    if(arr[mid] == value){
		return mid;
	    }
	    if(arr[mid] > value){
		    height = mid - 1;
	    }else{
		low = mid + 1;
	    }
	}
	return -1;
    }
}
