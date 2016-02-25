package com.designmode.tactics;

/**
 * 返现模式
 * 达到一定的条件，返现金
 */
public class CashReturn extends CashSuper {
    private double moneyCondition = 0.0d;
    private double moneyReturn = 0.0d;

    public CashReturn(String moneyCondition,String moneyReturn){
        this.moneyCondition = Double.parseDouble(moneyCondition);
        this.moneyReturn = Double.parseDouble(moneyReturn);
    }
    public double acceptCash(double money) {
        double result = money;
        if(money>=moneyCondition)
            //Math。floor 返回最大的（最接近正无穷大）double 值，该值小于等于参数，并等于某个整数。
            result = money - Math.floor(money/moneyCondition)*moneyReturn;
        return result;
    }
}