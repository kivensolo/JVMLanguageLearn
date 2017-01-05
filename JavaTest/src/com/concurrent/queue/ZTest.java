package com.concurrent.queue;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2017/1/5 22:29
 * description:
 */
public class ZTest {
	static int N = 100; //N以内的质数
	static int numCount;
	public static void main(String[] args) {
		System.out.println("小于" + N + "的质数有：");
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= (int) Math.sqrt(i); j++) {
				if ( (j != 1) && (i % j == 0)) {
					break;
				}
				if (j == (int) Math.sqrt(i)) {
					System.out.print(i + " ");
					if (++numCount == 10) {
						numCount = 0;
						System.out.println("");
					}
				}
			}
		}
	}

}
