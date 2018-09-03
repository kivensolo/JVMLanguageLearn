package com.data_structure.queue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * author: King.Z <br>
 * date:  2017/7/5 17:38 <br>
 * description: 有界的循环队列 <br>
 */
public class BoundedCirclrQueue<E> extends AbstractQueue<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 8;
    Object[] queue;
    int head;
    int rear;

    public BoundedCirclrQueue() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public BoundedCirclrQueue(int initialCapacity) {
        queue = new Object[initialCapacity];
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        //TODO size不对
        return (rear - head) & (queue.length - 1);
    }

    @Override
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        if (queue[rear] != null && head == rear) {
            //队列已满
            return false;
        }
        queue[rear] = e;
        rear = (rear + 1) & (queue.length - 1);
        System.out.println("BoundedCirclrQueue offer  rear++ ----> " + rear);
        return true;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    public E removeLast() {
        E x = pollLast();
        if (x == null) {
            throw new NoSuchElementException();
        }
        return x;
    }

    public E pollFirst() {
        int h = head;
        @SuppressWarnings("unchecked")
        E result = (E) queue[h];
        // Element is null if deque empty
        if (result == null) {
            return null;
        }
        queue[h] = null;     // 必须清空当前槽
        head = (h + 1) & (queue.length - 1);
        System.out.println("BoundedCirclrQueue pollFirst  head = " + head);
        return result;
    }

    public E pollLast() {
        //当前rear游标位置
        int t = (rear - 1) & (queue.length - 1);
        @SuppressWarnings("unchecked")
        E result = (E) queue[t];
        if (result == null) {
            return null;
        }
        queue[t] = null;
        rear = t;
        return result;
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @SuppressWarnings("unchecked")
    public E peekFirst() {
        // elements[head] is null if deque empty
        return (E) queue[head];
    }

    @SuppressWarnings("unchecked")
    public E peekLast() {
        return (E) queue[(rear - 1) & (queue.length - 1)];
    }

    @Override
    public boolean isEmpty() {
        return head == rear;
    }
}
