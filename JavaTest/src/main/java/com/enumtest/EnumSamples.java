package com.enumtest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author King.Z
 * @version ${STUB}
 * @since 2019/7/18 21:29 <br>
 */
public class EnumSamples {
    public static void main(String[] args) {
        AccountState accountState = AccountState.valueOf("Normal"); //可以得到Noraml类型的枚举对象
    }

    public enum Operation{
        PLUS{double apply(double x,double y){return x + y;}},
        MINUS{double apply(double x,double y){return x - y;}},
        TIMES{double apply(double x,double y){return x * y;}},
        DIVIDE{double apply(double x,double y){return x / y;}};

        abstract double apply(double x, double y);
    }

    /**
     * Effect java 示例枚举
     */
    public enum AccountState {
        None(" "),
        Normal("正常"),
        Erasure("销户");

        private static final Map<String, AccountState> stringToEnum = new HashMap<>();

        static {
            for (AccountState act : values()) {
                stringToEnum.put(act.toString(), act);
            }
        }

        private final String type;

        AccountState(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }

        public static AccountState fromType(String type) {
            return stringToEnum.get(type);
        }
    }
}
