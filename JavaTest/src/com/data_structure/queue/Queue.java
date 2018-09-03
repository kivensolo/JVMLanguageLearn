package com.data_structure.queue;

/**
 * author: King.Z <br>
 * date:  2018/9/3 22:51 <br>
 * description: Simple Queue <br>
 *     java 源码为接口 而不是抽象类
 *     自我实现方式{@link KingZQueueArray}
 *
 *      队列中不允许插入为null的元素,否则会报空指针
 *
 *      满队列情况下：
 *          add()方法会报IllegalStateException异常,提示队列已满；
 *          offer()方法在此情况下不会报异常而返回false.
 *
 *      空队列情况下：
 *          remove()方法会抛出队列为空,没有匹配元素的的异常；
 *          poll()方法则会返回null值,不会报异常；
 *
 *          element()方法会抛出队列为空,没有匹配元素的的异常;
 *          peek()方法则会返回null值,不会报异常;
 *
 *      java集合探究的博客：
 *          https://blog.csdn.net/u011240877/article/category/6447444
 */
public interface Queue<E> {

    // 添加操作
    boolean add(E e);
    boolean offer(E e);

    // 删除操作
    E remove();
    E poll();

    //检索操作  返回值为队列容器头部元素,但是不会删除元素
    E element();
    E peek();

}
