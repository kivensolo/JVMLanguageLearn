package com.desgin.create_type._02_factory_design.products;

import com.desgin.create_type._02_factory_design.abstract_factory.CommonPizza;

public class HotPizza extends CommonPizza {
    @Override
    public void prepare() {
        System.out.println("prepare HotPizza");
    }

    @Override
    public void bake() {

    }

    @Override
    public void cut() {

    }

    @Override
    public void box() {

    }
}
