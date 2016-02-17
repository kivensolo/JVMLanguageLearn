package com.hello.kevin;

import java.util.Scanner;

/**
 * Copyright (c) 2015, �����Ӵ�ƿƼ��������ι�˾
 * All rights reserved.
 * author��zhi.wang
 * date��2015-9-24
 * description��
 */
/*
 ��Ŀ�����������������Ƕ������ɴ��⣺ѧϰ�ɼ�>=90�ֵ�ͬѧ��A��ʾ��
       60-89��֮�����B��ʾ��60�����µ���C��ʾ��
    1.���������(a>b)?a:b��������������Ļ������ӡ�����Ԫ�������*/
public class Test_4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input rank:");
        int inputNum = scanner.nextInt();//��ȡ�����ֵ
        System.out.println((inputNum <= 100) ? ((inputNum >= 90) ? "A" : (inputNum > 60 ? "B" : "C")) : "Not right");

    }

}

