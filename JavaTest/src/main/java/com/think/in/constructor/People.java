package com.think.in.constructor;

/**
 * author: King.Z <br>
 * date:  2017/5/12 11:32 <br>
 * description: new Child("mike")时，构造函数的调用顺序
 * 当创建子类对象时，最先调用的是父类的无参构造函数,如果默认无参构造函数是private的
 * 那么子类是无法创建的<br>
 */
class People {
    String name;
    public People() {
        System.out.print(1);
    }

    public People(String name) {
        System.out.print(2);
        this.name = name;
    }
}

class Child extends People {
    People father;

    public Child(String name) {
        System.out.print(3);
        this.name = name;
        father = new People(name + ":F");
    }

    public Child() {
        System.out.print(4);
    }

}
