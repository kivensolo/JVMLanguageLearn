package com.reference;

/**
 * author: King.Z <br>
 * date:  2016/7/13 20:11 <br>
 * description: JVM的强软弱虚引用 <br>
 * 【强引用】
 *    垃圾回收器不会轻易回收它。只用当这个对象没有被使用(比如设置null)，垃圾回收器才会回收它。
 * 【软引用】
 *    GC后，若内存还是不足，就会把软引用的包裹的对象给清理掉。
 *      软引用可以用来实现内存敏感的高速缓存，当系统空间不足时缓存对象可被清理掉而不影响业务。
 * 【弱引用】
 *    如果触发GC，这时内存很充足，但是被弱引用的对象还是会被回收。
 *    所以说，被弱引用关联的对象只能生存到下一次垃圾收集发生之前。
 *    如果被包裹的数据是静态的，即便被弱引用，也不会被GC回收的。
 */
public class Reference {
    public static void main(String[] args) throws InterruptedException {
        Student student = new Student();
        student = null;
        System.gc();
    }

}
