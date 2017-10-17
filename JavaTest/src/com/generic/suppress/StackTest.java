package com.generic.suppress;

public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        while (!stack.isEmpty()){
            System.out.println(stack.pop().toUpperCase());
        }
    }
}
