package com.arr;

/**
 * author: King.Z <br>
 * date:  2017/4/16 18:49 <br>
 * description: 二维数组 <br>
 */
public class DoubleArr {
    public static void main(String[] args) {
        definedWay_A();
        definedWay_B();
    }

    //写法1  规则数组  定义一个五行五列的空间大小
    private static void definedWay_A() {
        float[][] numThree = new float[5][5];
        numThree[0][0] = 1.1f;
        numThree[1][0] = 1.2f;
        numThree[2][0] = 1.3f;
        numThree[3][0] = 1.4f;
        numThree[4][0] = 1.5f;
        for (float[] aNumThree : numThree) {
            for (float anANumThree : aNumThree) {
                System.out.print(String.format("%-5f",anANumThree));
            }
            System.out.println("");
        }
        System.out.println("");
    }

    //写法2 不规则数组  只定义行，不定义列。动态分配列  锯齿数组
    private static void definedWay_B() {
        int[][] numFive = new int[5][];
        numFive[0] = new int[]{3, 4, 5, 6, 7};
        numFive[1] = new int[]{1, 2, 3};
        numFive[2] = new int[]{7, 8, 9, 10};
        numFive[3] = new int[]{11, 12};
        numFive[4] = new int[]{13, 11, 14, 23};
        for (int[] aNumFive : numFive) {
            for (int value : aNumFive) {
                System.out.print(String.format("%-5d",value));
            }
            System.out.println("");
        }
    }
}
