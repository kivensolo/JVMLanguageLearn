package com.generic.wildcard;

import java.util.Iterator;

public class WildcardTest {
    public static void main(String[] args) {
        WildcardStack<Number> numberStack = new WildcardStack<>();
        Iterable<Number> integers = new Iterable<Number>() {
            @Override
            public Iterator<Number> iterator() {
                return null;
            }
        };
        numberStack.pushAll(integers);

        //Iterable<Integer> integers = new Iterable<Integer>() {
        //    @Override
        //    public Iterator<Integer> iterator() {
        //        return null;
        //    }
        //};
        //numberStack.pushAll(integers);
    }
}
