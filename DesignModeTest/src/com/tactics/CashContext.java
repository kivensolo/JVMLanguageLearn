package com.tactics;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/2/25 19:28
 * description:
 */
class CashContext {
    private CashSuper cs;
    public CashContext(CashSuper csuper){
        this.cs = csuper;
    }
    public double GetResult(double money){
        return cs.acceptCash(money);
    }
}
