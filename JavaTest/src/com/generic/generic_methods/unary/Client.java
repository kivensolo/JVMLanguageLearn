package com.generic.generic_methods.unary;

public class Client {

    private static UnaryFunction<Object> IDENTITY_FUNCTION = new UnaryFunction<Object>() {
        @Override
        public Object apply(Object o) {
            return o;
        }
    };

    @SuppressWarnings("unchecked")
    private static <T> UnaryFunction<T> identifyFunction() {
        return (UnaryFunction<T>) IDENTITY_FUNCTION;
    }
}
