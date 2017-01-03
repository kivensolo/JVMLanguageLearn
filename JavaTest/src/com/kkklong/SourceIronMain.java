package com.kkklong;

import java.util.Date;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/12/29 10:31 <br>
 * description: 实现了 Cloneable 接口，
 * 以指示 Object.clone() 方法可以合法地对该类实例进行按字段复制。 <br>
 *
 *     要让一个对象进行克隆，其实就是两个步骤：
    1. 让该类实现java.lang.Cloneable接口；
    2. 重写（override）Object类的clone()方法。
    这种方式是浅克隆（shallow clone）
 */
public class SourceIronMain implements Cloneable{
    private String id;
	private String name;
	private Date createDate;

    public SourceIronMain(String id, String name, Date createDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
