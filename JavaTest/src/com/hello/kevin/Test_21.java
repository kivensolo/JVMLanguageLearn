package com.hello.kevin;

public class Test_21 {

    /**
     * @param args��Ŀ����1+2!+3!+...+20!�ĺ͡� 1.����������˳���ֻ�ǰ��ۼӱ�����۳ˡ�
     * @Description��
     * @Return void
     */
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

