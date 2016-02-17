package com.think.in;

import java.util.Formatter;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/1/14 10:40
 * description:
 */
public class FormatTest {
    private double total = 0;
    private Formatter f = new Formatter(System.out);

    public void printTitle(int width){
        //"%-15s"
         String str = "%-"+width+"s" + " %" + width + "s" + " %" + width +"s\n";
        f.format(str,"item","count","price");
        f.format(str,"----","-----","-----");
    }

    public void printDetail(String itemName ,String num,double price,int width){
        String str = "%-"+width+"s" + " %" + width + "s" + " %" + width +".2f\n";
        f.format(str,itemName,num,price);
        total+=Integer.valueOf(num)*price;
    }

    private void printTotal(int width){
         String str_a = "%-"+width+"s" + " %" + width + "s" + " %" + width +".2f\n";
         String str_b = "%-"+width+"s" + " %" + width + "s" + " %" + width +"s\n";
        f.format(str_a,"Tax","",total*0.06);
        f.format(str_b,"","","-----");
        f.format(str_a,"Total","",total*1.06);
    }

    public static void main(String[] args) {
        FormatTest ft  = new FormatTest();
        ft.printTitle(10);
        ft.printDetail("HappyCar","2",5.31,10);
        ft.printDetail("Apple","12",4.31,10);
        ft.printDetail("Banana","5",3.2,10);
        ft.printTotal(10);
    }
}
