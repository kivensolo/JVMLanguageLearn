package com.kingz.godlike;


import java.util.Scanner;

public class Test_5 {

    public static int m;
    public static int n;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Inpiut First num:");
        int a = in.nextInt();
        System.out.println("Inpiut Secend num:");
        int b = in.nextInt();
        if (b > a) {
            int x = a;
            a = b;
            b = x;
        }
        for (int k = 1; k <= b; k++) {
            if ((a % k) == 0 && (b % k) == 0) {
                m = k;
            }
        }
        n = a * b / m; //��󹫱��� = a * b / ���Լ��
        System.out.println("max:" + m + "; min:" + n);
    }

}

