package com.kingz.godlike;

import java.util.Scanner;

/**
 * Copyright (c) 2015, �����Ӵ�ƿƼ��������ι�˾
 * All rights reserved.
 * author��zhi.wang
 * date��2015-9-24
 * description��
 */
/* ��Ŀ����һ���������ֽ������������磺����90,��ӡ��90=2*3*3*5��
 Ȼ������������ɣ�
 (1)����������ǡ����n����˵���ֽ��������Ĺ����Ѿ���������ӡ�����ɡ�
 (2)���n <> k����n�ܱ�k��������Ӧ��ӡ��k��ֵ��
 ����n����k����,��Ϊ�µ���������n,�ظ�ִ�е�һ����
 (3)���n���ܱ�k����������k+1��Ϊk��ֵ,�ظ�ִ�е�һ����
     �����������n���зֽ���������Ӧ���ҵ�һ����С������k
 */
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


