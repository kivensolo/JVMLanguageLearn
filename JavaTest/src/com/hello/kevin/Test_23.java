package com.hello.kevin;

public class Test_23 {

    /**
     * @param args
     * @Description： 题目：有5个人坐在一起，问第五个人多少岁？
     * 他说比第4个人大2岁。问第4个人岁数，他说比第3个人大2岁。问
     * 第三个人，又说比第2人大两岁。问第2个人，说比第一个人大两岁。
     * 最后问第一个人，他说是10岁。请问第五个人多大？
     * 1.程序分析：利用递归的方法，递归分为回推和递推两个阶段。
     * 要想知道第五个人岁数，需知道第四人的岁数，依次类推，推到
     * 第一人（10岁），再往回推。
     * @Return void
     */
    public static void main(String[] args) {
        int n = 10;
        for (int i = 0; i < 4; i++) {
            n = n + 2;
        }
        System.out.println("第五个人" + n + "岁");
    }

}

