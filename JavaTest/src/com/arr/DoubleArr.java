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
            //此长度为行数 非列数
            System.out.println(aNumThree[0]);
        }
    }

    //写法2 不规则数组  只定义行，不定义列  动态分配列
    private static void definedWay_B() {
        float[][] numFive = new float[5][];
        numFive[0] = new float[5];
        numFive[1] = new float[6];
        numFive[2] = new float[7];
        numFive[3] = new float[8];
        numFive[4] = new float[9];
        numFive[0][4] = 0.5f;
        numFive[1][5] = 1.6f;
        numFive[2][6] = 2.7f;
        numFive[3][7] = 3.8f;
        numFive[4][8] = 4.9f;
        for (float[] aNumFive : numFive) {
            for (int j = 0; j < aNumFive.length; j++) {
                if (j == aNumFive.length - 1) {
                    System.out.println("Value:" + aNumFive[j]);
                }
            }
        }
    }
}
