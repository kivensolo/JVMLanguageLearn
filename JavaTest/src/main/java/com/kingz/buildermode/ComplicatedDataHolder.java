package com.kingz.buildermode;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/6/3 11:35 <br>
 * description: 如果你有一个相当复杂的对象想要去为其构建一个结构，可以考虑使用 Builder 模式。
 * 你可以在对象中创建一个能帮助你构建出这个对象的子类。它使用了可变语句，
 * 但是一旦你调用了build，它就会提供给你一个不可变的对象。
 * 想象一下我们要有一个更加复杂的 DataHolder。针对它的构建器看起来可能像是下面这样 <br>
 */
public class ComplicatedDataHolder {
    public final String data = "";
    public final int num = 0;

    public ComplicatedDataHolder(String data, int num) {

    }

    public static class Builder {
        private String data;
        private int num;

        public Builder data(String data) {
            this.data = data;
            return this;
        }

        public Builder num(int num) {
            this.num = num;
            return this;
        }

        public ComplicatedDataHolder build() {
            return new ComplicatedDataHolder(data, num); // etc
        }
    }
    //然后这样使用它：
    //final ComplicatedDataHolder cdh = new ComplicatedDataHolder.Builder()
    //        .data("set this")
    //        .num(523)
    //        .build();
}
