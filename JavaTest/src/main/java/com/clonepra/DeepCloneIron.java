package com.clonepra;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/12/29 11:04 <br>
 * description:
 *     如果该可克隆对象中有额外的可克隆对象，如SourceIronMain，当外面调用此对象的克隆方法的时候，此对象被克隆了，但是其内部的
 *     可克隆对象并没有再次克隆，克隆的是DeepCloneIron包含的所有对象的【应用地址】，而是直接被引用了。所以在堆中SourceIronMain的对象还是只有一个，只是被多个对象指向了。
 *
 * 【深克隆】，就是非浅克隆。克隆除自身以外所有的对象，包括自身所包含的所有对象实例。
 * 至于深克隆的层次，由具体的需求决定，也有“N层克隆”一说。
 *
 * 但是，所有的基本（primitive）类型数据，无论是浅克隆还是深克隆，都会进行原值克隆。
 * 毕竟他们都不是对象，不是存储在堆中。注意：基本数据类型并不包括他们对应的包装类。
 */
public class DeepCloneIron implements Cloneable{
    private SourceIronMain mIronMan;

    public DeepCloneIron(SourceIronMain mIronMan) {
        this.mIronMan = mIronMan;
    }

    //@Override
    //protected Object clonepra() throws CloneNotSupportedException {
    //    return super.clonepra();
    //}
    @Override
    protected Object clone() throws CloneNotSupportedException {
        //深度克隆  克隆此对象包含的所有可克隆的子对象
        DeepCloneIron ironMan = (DeepCloneIron) super.clone();
        ironMan.mIronMan = (SourceIronMain) ironMan.getmIronMan().clone();
        return ironMan;
        //但是 这种方式：如果有N多个持有的对象，那就要写N多的方法，突然改变了类的结构，还要重新修改clone()方法。
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public SourceIronMain getmIronMan() {
        return mIronMan;
    }

    public void setmIronMan(SourceIronMain mIronMan) {
        this.mIronMan = mIronMan;
    }
}
