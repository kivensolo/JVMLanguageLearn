package com.reflect.demo;

import com.annotation.requestDemo.ReqParam;
import com.annotation.requestDemo.ReqType;
import com.annotation.requestDemo.ReqTypeEnum;

import javax.annotation.Nullable;

/**
 * 反射目标测试类
 */
public class ICBCBank {
    static {
        //
    }
    //----基础数据类型
    private int year = 0;
    public String desc = "工商银行";
    protected int createDate;

    //----final字段
    private final String country = "China";
    private final boolean isVisa = false;

    //----普通字段
    protected String accoutnName = "admin";
    public int accountMoney = 0;

    protected void saveMoney(int money){
        accountMoney = money;
    }
    protected  int takeMoneky(){
        return accountMoney;
    }

    @ReqType(ReqTypeEnum.POST)
    private void credit(@Nullable String type){}
    private void moneyManagement(@Nullable @ReqParam String money,@ReqParam String time){}

    static class BankInspector {
        String name = "银监会";
    }

    @Override
    public String toString() {
        return "BankAble{" +
                //"year=" + year +
                "desc='" + desc + '\'' +
                ", country='" + country + '\'' +
                ", isVisa='" + isVisa + '\'' +
                ", createDate=" + createDate +
                ", accoutnName='" + accoutnName + '\'' +
                ", accountMoney=" + accountMoney +
                '}';
    }
}
