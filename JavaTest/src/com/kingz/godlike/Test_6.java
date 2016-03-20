package com.kingz.godlike;

import java.util.Scanner;

/*
��Ŀ����s = a + aa + aaa + aaaa + aa...a��ֵ������a��һ�����֡�����2 + 22 + 222 + 2222 + 22222(��ʱ����5�������)������������м��̿��ơ�
1.����������ؼ��Ǽ����ÿһ���ֵ��*/
public class Test_6 {

    public static int total = 0;

    public static void main(String[] args) {
        int nownum;
        int lastnum;
        Scanner in = new Scanner(System.in);
        System.out.println("Inpiut a:");
        int a = in.nextInt();
        System.out.println("Inpiut ����:");
        int b = in.nextInt();
        lastnum = a;
        total = lastnum;
        if (b != 1) {
            for (int i = 1; i < b; i++) {
                lastnum = lastnum * 10 + a;
                total += lastnum;
            }
        }
        System.out.println("���Ϊ��" + total);
    }
}

