package com.hello.kevin;

public class Test_7 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int s;
        for (int i = 1; i <= 1000; i++) {
            s = 0;
            for (int j = 1; j < i; j++)
                if (i % j == 0)
                    s = s + j;
            if (s == i)
                System.out.print(i + ", ");
        }
        System.out.println();
    }

}

