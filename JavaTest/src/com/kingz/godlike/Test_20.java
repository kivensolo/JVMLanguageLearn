package com.kingz.godlike;

public class Test_20 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        float top = 2;
        float buttom = 1;
        float nexttop = 2;
        float sum = 0;
        for (int i = 1; i <= 20; i++) {
            top = nexttop;
            System.out.println(top + "/" + buttom);
            sum += top / buttom;
            nexttop = top + buttom;
            buttom = top;
        }
        System.out.println(sum);
    }
}

