package com.generic.suppress;

public class StackTest {
    public static void main(String[] args) {
        GenericStack<String> stack = new GenericStack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        while (!stack.isEmpty()){
            System.out.println(stack.pop().toUpperCase());
        }
    }
}
