package com.desgin.create_type._02_factory_design.sample01.nomalfactory;

/**
 * author: King.Z <br>
 * date:  2017/1/25 14:28 <br>
 * description: 具体创建者 <br>
 */
public class ConcreteCreator extends Creator {
    public <T extends Product> T createProduct(Class<T> c) {
        Product product = null;
        try {
            product = (Product) Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            //异常处理
        }
        return (T) product;
    }
}
