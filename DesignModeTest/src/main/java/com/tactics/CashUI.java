package com.tactics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * description:策略模式更加侧重的是算法的变化，而简单工厂模式则侧重的是具体对象的变化，
 * 把握这两点的不同就可以进行区分了。另外这两个方法一般情况下是可以结合使用的，
 * 将策略的返回放入到简单工厂类中。
 */
public class CashUI {
    public static void main(String[] args) {
        try {
            CashContext cashContext= null;
            System.out.println("1.无折扣");
            System.out.println("2.促销，满300返100");
            System.out.println("3.VIP，打8折");
            System.out.println("请选择:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int  iselect;
            iselect= Integer.parseInt(br.readLine());
            switch(iselect)
            {
                case 1://正常收费
                    cashContext = new CashContext(new CashNormal());
                    break;
                case 2://满300返100
                    cashContext = new CashContext(new CashReturn("300","100"));
                    break;
                case 3://打8折
                    cashContext = new CashContext(new CashRebate("0.8"));
                    break;
            }
            double totalPrices = 0;
            System.out.println("请输入总钱数:");
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
            double nowPrice = Double.parseDouble(br2.readLine());
            if (cashContext != null) {
                totalPrices = cashContext.GetResult(nowPrice);
            }
            System.out.println("需付款："+totalPrices+"元");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
