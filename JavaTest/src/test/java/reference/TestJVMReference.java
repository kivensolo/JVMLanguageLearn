package reference;

import org.junit.Test;

import java.lang.ref.*;

import static org.junit.Assert.assertEquals;

/**
 * JVM的强软弱虚引用测试
 * 【强引用】
 *    垃圾回收器不会轻易回收它,哪怕OOM。只用当这个对象没有被使用(比如设置null)，垃圾回收器才会回收它。
 *
 * 【软引用】
 *    GC后，若内存还是不足，就会把软引用的包裹的对象给清理掉。
 *    软引用可以用来实现内存敏感的高速缓存，当系统空间不足时缓存对象可被清理掉而不影响业务。
 *
 * 【弱引用】
 *    如果触发GC，这时内存很充足，但是被弱引用的对象还是会被回收。
 *    所以说，被弱引用关联的对象只能生存到下一次垃圾收集发生之前。
 *    如果被包裹的数据是静态的，即便被弱引用，也不会被GC回收的。
 *
 * 【虚引用】
 *    - 无法通过虚引用来获取对一个对象的真实引用。因为PhantomReference.get() 始终都是null !!!
 *    - 虚引用必须与ReferenceQueue一起使用，当GC准备回收一个对象，
 *      如果发现它还有虚引用，就会在回收之前，
 *      把这个虚引用加入到与之关联的ReferenceQueue中。
 *  为一个对象设置虚引用关联的唯一目的就是能在这个对象被垃圾收集器回收时收到一个系统通知。
 *  还有会用虚引用管理堆外内存。
 */
public class TestJVMReference {
    @Test
    public void testStrongReference(){
        Student student = new Student();
        student = null;
        System.gc();
    }


    @Test
    public void testSoftReference_v1(){
        Object heapObj = new Object();
        SoftReference<Object> softReference = new SoftReference<>(heapObj);
        Object o = softReference.get();
        System.out.println("弱引用对象:" + o);
    }

    @Test
    public void testSoftReference_v2(){
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[100 * 1024 * 1024]);
        System.out.println("gc前 弱引用对象:" + softReference.get());
        System.gc(); //gc后，观察弱引用有没有被回收
        System.out.println("gc后(内存足够) 弱引用对象:" + softReference.get());

        //分配新对象, 触发gc
        byte[] bytes = new byte[300 * 1024 * 1024];
        System.out.println("内存不够 弱引用对象:" + softReference.get());
    }

    /**
     * 弱引用测试
     * @throws InterruptedException
     */
    @Test
    public void testWeakReference() throws InterruptedException {
        WeakReference<String> wr = new WeakReference<>(new String("HelloWorld"));
        WeakReference<String> staticWR = new WeakReference<>("HelloWorld");

        System.out.println("befor gc: wr=" + wr.get()
                + " ,static=" + staticWR.get());
        System.gc();
        Thread.sleep(200);
        //weakReference在GC后就会被回收，所以
        System.out.println(
            "after gc: wr=" + wr.get() + " ,static=" + staticWR.get()
        );
        assertEquals(null, wr.get());
    }


    @Test
    public void testPhantomReference() throws InterruptedException {
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        Reference<?> elementOfQueue = queue.poll();
        System.out.println("befor gc: elementOfQueue="+elementOfQueue);
        PhantomReference<Student> reference= new PhantomReference<>(new Student(),queue);
        System.out.println("befor gc: Try get from reference = " + reference.get());
        System.gc();
        Thread.sleep(300);
        new Thread(() -> {
            while (true) {
                Reference<?> poll = queue.poll();
                if (poll != null) {
                    System.out.println("虚引用被回收了：" + poll);
                }
            }
        }).start();
        Thread.sleep(15 * 1000);
    }
}
