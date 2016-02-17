package com.hello.kevin;

public class Test_20 {

    /**
     * @param args
     * @Description�� ��Ŀ����һ�������У�2/1��3/2��5/3��8/5��13/8��21/13...���������е�ǰ20��֮�͡�
     * 1.�����������ץס�������ĸ�ı仯���ɡ�
     * @Return void
     */
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

