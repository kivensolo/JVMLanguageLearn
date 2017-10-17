package com.generic.suppress;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Stack<Object>
 * Stack<int[]>
 * Stack<List<String>>
 * Stack<xxxxx>
 * 不能创建基本类型的：Stack<int>  Stack<double>
 * 原因：泛型系统根本的局限性   措施：使用包装类
 *
 */
public class Stack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    public Stack() {
        this.elements = (E[])new Object[DEFAULT_INITIAL_CAPACITY];
    }


    public void push(E e){
        ensureCapacity();
        elements[size++] = e;
    }

    public E pop(){
        if(size == 0){
            throw new EmptyStackException();
        }
        //@SuppressWarnings("unchecked")
        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if(elements.length == size){
            elements = Arrays.copyOf(elements,2 * size + 1);
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

}
