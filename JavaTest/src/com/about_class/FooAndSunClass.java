package com.about_class;
//内部类访问外部类同名变量
public class FooAndSunClass {

    private int fooInt = 0;
    public String fooStr = "foo";
    protected boolean isBoom = true;

    class InnerClass{

        public InnerClass(int fooInt) {
            FooAndSunClass.this.fooInt = fooInt;
        }
    }
}
