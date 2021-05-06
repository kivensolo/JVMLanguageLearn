package com.data_structure.queue;

import java.util.Arrays;

/**
 * 循环队列:
 * 为了能够充分地使用数组中的存储空间，克服”假溢出”现象，可以把数组的前端和后端连接起来，
 * 形成一个环形的表，即把存储队列元素的表从逻辑上看成一个环，成为循环队列(circular queue)。
 * - [初始化]: 头(mFront)尾指针初始化都为0
 * - 在【队尾插入新元素】和【删除队头元素】时，两个指针都按顺时针方向进1。当他们进到maxSize-1时，
 *   并不表示表的终结，只要有需要，利用%运算可以前进到数组的0号位置。
 *   (1)队空条件：如果循环队列读取元素的速度快于存入元素的速度，队头指针很快追上了队尾指针，
 *              一旦front == rear时，队列就变成空队列。
 *   (2)队满条件：如果队列存入元素的速度快于读取元素的速度，队尾指针很快就追上了队头指针，
 *              一旦队列满就不能再加入新元素了。
 *    为了区别于队空条件，用(mRear +１) ％ maxSize == front来判断是否队已满，就是说，让rear指到front的前一位置就认为队已满。
 *    也就是说循环队列中，最多只能存放maxSize - 1个元素。
 *
 */
@SuppressWarnings("unchecked")
public class CircularQueue<T> {
    private Object[] queue;
    //front is out, rear is in.
    private int mFront, mRear;

    public CircularQueue(int capacity) {
        if(capacity <= 0){
            throw new IllegalArgumentException("Capacity can't less 0");
        }
        queue = new Object[capacity];
        mFront = mRear = 0;
    }

    public interface IValueComparator<T>{
        /**
         * @param v1 the object compare.
         * @param v2 the object to be compared.
         * @return A negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         */
        int compare(T v1, T v2);
    }

    public boolean isEmpty(){
        return mFront == mRear;
    }

    /**
     * Add new data into circular queue.
     * @param obj data
     * @return
     *      true:offer success
     *      false: offer failed when queue is full.
     */
    public boolean offer(T obj){
        int rear = mRear;
        int front = mFront;
        int nextRear = (rear + 1) % queue.length;
        if(nextRear == front){
            // Queue is full. Add failed
            return false;
        }
        queue[rear]= obj;
        mRear = nextRear;
        return true;
    }

    public boolean offer(T obj, IValueComparator<T> cmp){
        int rear = mRear;
        int front = mFront;
        int nextRear = (rear + 1) % queue.length;
        if(nextRear == front){
            // Queue is full. Add failed
            return false;
        }
        //通过数据对比进行数据移位，保证大的在末尾
        while (front != rear){
            T srcObj = (T)queue[front];
            int result = cmp.compare(srcObj, obj);
            if(result > 0){ // o > obj
                queue[front] = obj;
                obj = srcObj;
            }
            ++front;
            front %= queue.length;
        }
        queue[rear]= obj;
        mRear = nextRear;
        return true;
    }

    /**
     * Peek new data from front index.
     * @return data
     */
    public T peek(){
        if(mFront == mRear){
            return null;
        }
        return (T) queue[mFront];
    }

    public T peek(int offset){
        if(mFront == mRear){
            return null;
        }
        if(offset >= size()){
            return null;
        }
        int pointPos = (mFront + offset) % queue.length;
        return (T) queue[pointPos];
    }

    public T pull(){
        int front = mFront;
        if(front == mRear){
            return null; //Queue is empty
        }
        Object o = queue[front];
        queue[front] = null;
        mFront = (front + 1) % queue.length;
        return (T) o;
    }

    /**
     * 根据偏移量pull数据
     * @param offset 偏移量
     * @return 对应偏移量位置的数据
     */
    public T pull(int offset){
        int front = mFront;
        if(front == mRear){
            return null; //Queue is empty
        }
        int pointPos = (mFront + offset) % queue.length;
        Object target = queue[pointPos];

        //数据回填
        while (pointPos != front){
            int prePos = pointPos - 1;
            if(prePos < 0){
                prePos += queue.length;
            }
            queue[pointPos] = queue[prePos];
            pointPos = prePos;
        }
        queue[pointPos] = null;
        mFront = (front + 1) % queue.length;
        return (T) target;
    }

    /**
     * Get the size of data.
     * @return Size of data
     */
    public int size(){
        return (mRear - mFront + queue.length) % queue.length;
    }

    public int capacity(){
        return queue.length - 1;
    }

    /**
     * Clear queue data.
     */
    public void clear(){
        int front = mFront;
        mFront = mRear;
        while (front != mRear){
            queue[front] = null;
            front = (front + 1) % queue.length;
        }
    }


    public String dumpInfo() {
        return "Circular={" +
                "Capacity=" + capacity() +
                ", dataCount=" + size() +
                ", mFront=" + mFront +
                ", mRear=" + mRear +
                '}';
    }

    public String dumpData() {
        return Arrays.toString(queue);
    }

}
