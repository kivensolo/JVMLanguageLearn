package com.desgin.create_type._02_factory_design.sample01.nomalfactory;

public class Test {
    public static void main(String[] args) {
        Creator creator = new ConcreteCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
        /*
        * 继续业务处理
        */
    }
}
