package com.designmode.tactics;

/**
 * 打折模式
 */
public class CashRebate extends CashSuper {
    private double moneyRebate = 1d;
    public CashRebate(String moneyRebate){
        this.moneyRebate = Double.parseDouble(moneyRebate);
    }
    public double acceptCash(double money) {
        return money * moneyRebate;
    }
}
