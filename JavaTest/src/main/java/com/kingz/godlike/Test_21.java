package com.kingz.godlike;

public class Test_21 {

    public static void main(String[] args) {
        int sum = 0;
        int values = 1;
        for (int i = 1; i <= 20; i++) {
            values = i * values; //�׳�
            sum += values;
        }
        System.out.println("sum=" + sum);
    }
}

