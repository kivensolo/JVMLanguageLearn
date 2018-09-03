package com.data_structure.queue;

import java.util.NoSuchElementException;

/**
 * author: King.Z <br>
 * date:  2017/7/5 17:39 <br>
 * description: 一般队列 <br>
 *     队列（简称作队，Queue）也是一种特殊的线性表，队列的数据元素以及数据元素间的
 *     逻辑关系和线性表完全相同，其差别是线性表允许在任意位置插入和删除，
 *     而队列只允许在其一端进行插入操作在其另一端进行删除操作。
 */
public class KingZQueueArray {
    public static final int DEFAULT_INITIAL_CAPACITY = 10;
    Object[] mQueue; //对象数组，队列最多存储a.length-1个对象
    int front;  //队首下标
    int rear;   //队尾下标

    public KingZQueueArray() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public KingZQueueArray(int initialCapacity) {
        mQueue = new Object[initialCapacity];
        front = 0;
        rear = 0;
    }

    /**
     * 将一个对象追加到队列尾部
     *
     * @param obj 对象
     * @return 是否添加成功
     */
    public boolean add(Object obj) {
        if ((rear + 1) % mQueue.length == front) {
            //没有可用空间
            throw new IllegalStateException("Queue full");
        }
        mQueue[rear] = obj;
        rear = (rear + 1) % mQueue.length; //更新游标位置
        return true;
    }

    /**
     * 获取并移除此队列的头，如果此队列为空，则返回 null。
     */
    public Object poll() {
        if (rear == front) {
            System.out.println("pool() queue empty！");
            return null;
        }
        Object obj = mQueue[front];
        front = (front + 1) % mQueue.length;
        System.out.println("pool() +1");
        return obj;
    }
    /**
     * 获取并移除此队列的头，如果此队列为空，则抛异常。
     */
    public Object remove() {
        if (rear == front) {
            System.out.println("remove() queue empty！");
            throw new NoSuchElementException();
        }
        Object obj = mQueue[front];
        front = (front + 1) % mQueue.length;
        System.out.println("remove() +1");
        return obj;
    }

    /**
     * 获取但不移除此队列的头；如果此队列为空，则返回 null。
     * @return
     */
    public Object peek(){
           if (rear == front) {
            return null;
        }
        return mQueue[front];
    }


    public void clear(){
        System.out.println("clear() begin");
        while (poll() != null);
        System.out.println("clear() end");
    }

}
