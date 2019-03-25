package com.about_class;
//内部类访问外部类同名变量

class FooAndSunClass {

    private int fooInt = 0;
    public String fooStr = "foo";
    protected boolean isBoom = true;

    class InnerClass{

        public InnerClass(int fooInt) {
            FooAndSunClass.this.fooInt = fooInt;
        }
    }

    public void print(){
        System.out.println("I'm Foo");
    }
}
class SubClass extends FooAndSunClass{
    @Override
    public void print() {
        //super.print();
        System.out.println("I'm Sub");
    }
}
class TestClient{
    public static void main(String[] args) {
        SubClass sun = new SubClass();
        sun.print();
    }
}
