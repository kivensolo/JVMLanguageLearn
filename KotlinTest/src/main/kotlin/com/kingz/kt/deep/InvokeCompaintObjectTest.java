package com.kingz.kt.deep;

/**
 * author: King.Z <br>
 * date:  2020/3/4 17:43 <br>
 * description: Java层调用Kotlin层伴随对象时，如果不加JVM注解，
 * 那么访问的"静态变量"会是Companion的内部对象，访问时必须加上Companion，而不能像java那样直接调用。<br>
 */
public class InvokeCompaintObjectTest {
    public static void main(String[] args) {
        System.out.println("compainleVal="+ AnnotationTest.compainleVal_1);
        System.out.println("compainleVal="+ AnnotationTest.compainleVal_2);
        System.out.println("compainleVar="+ AnnotationTest.compainleVar_1);
        System.out.println("compainleVar="+ AnnotationTest.Companion.getCompainleVar_2());
    }
}
