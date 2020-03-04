package com.kingz.godlike;

import java.util.Scanner;

public class Test_3 {

    private int innum;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input num:");
        int inputNum = scanner.nextInt();//��ȡ�����ֵ
        int k = 2;
        System.out.println(inputNum + " = ");
        while (k <= inputNum) {
            if (k == inputNum) {
                System.out.print(k);
                break;
            } else if (k < inputNum) {
                if (inputNum % k == 0) {
                    System.out.print(k + "*");
                    inputNum = inputNum / k;
                } else {
                    k++;
                }
            }
        }
    }
}


