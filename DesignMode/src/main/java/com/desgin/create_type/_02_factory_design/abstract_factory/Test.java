package com.desgin.create_type._02_factory_design.abstract_factory;

public class Test {
    public static void main(String[] args) {
        PizzaFactory factory = new PizzaFactory();
        factory.createChinesePizza().prepare();
        factory.createHotPizza().prepare();
    }
}
