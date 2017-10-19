package com.generic.wildcard;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

/**
 * PECS
 * @param <E>
 */
public class WildcardStack<E> {
    private E[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    @SuppressWarnings("unchecked")
    WildcardStack() {
        this.elements = (E[])new Object[DEFAULT_INITIAL_CAPACITY];
    }

    void push(E e){
        ensureCapacity();
        elements[size++] = e;
    }

    void pushAll(Iterable<E> obj){
        for(E o:obj){
            push(o);
        }
    }

    E pop(){
        if(size == 0){
            throw new EmptyStackException();
        }
        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    public void popAll(Collection<? super E> dst){
        while (!isEmpty()){
            dst.add(pop());
        }
    }

    private void ensureCapacity() {
        if(elements.length == size){
            elements = Arrays.copyOf(elements,2 * size + 1);
        }
    }

    private boolean isEmpty(){
        return size == 0;
    }
}
