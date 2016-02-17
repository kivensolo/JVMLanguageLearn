package com.hello.kevin;

/**
 * Copyright (c) 2015, �����Ӵ�ƿƼ��������ι�˾
 * All rights reserved.
 * author��zhi.wang
 * date��2015-10-12
 * description��������Σ�
 * *
 * ***
 * *****
 * *******
 * *****
 * ***
 * *
 */
public class Test_19 {

    /**
     * @param args
     * @Description��
     * @Return void
     */
    public static void main(String[] args) {
        /*�ҵ��㷨*/
//        for (int i = 0; i <= 3; i++) {
//            for (int j = 0; j <= i+3 ; j++) {
//                if(j >= 3 - i && j <=  3 + i ){
//                    System.out.print("*");
//                }else{
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }
//        for (int i = 3; i > 0; i--) {
//            for (int j = 0; j <= i+3 ; j++) {
//                if(j > 3 - i && j <  3 + i ){
//                    System.out.print("*");
//                }else{
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }

        /*�����㷨*/

        for (int i = 1; i <= 4; i++) {
            //���Ǵ���ո�
            for (int k = 1; k <= 4 - i; k++)
                System.out.print(" ");
            //Ȼ������ѭ�����*
            for (int j = 1; j <= 2 * i - 1; j++)
                System.out.print("*");
            System.out.println();
        }
        for (int i = 3; i >= 1; i--) {
            for (int k = 1; k <= 4 - i; k++)
                System.out.print(" ");
            for (int j = 1; j <= 2 * i - 1; j++)
                System.out.print("*");
            System.out.println();
        }
    }

}

