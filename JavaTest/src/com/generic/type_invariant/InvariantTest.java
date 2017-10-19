package com.generic.type_invariant;

import org.omg.CORBA.Object;

import java.util.ArrayList;
import java.util.List;

public class InvariantTest {
    public static void main(String[] args) {
        InvariantTest b = new InvariantTest();
        b.getT(new ArrayList<Number>());
    }
    public void getT(List<Number> t){}


    //List<Object>  与 List<String> 的关系
    //List<String> 不是List<Object>的子类，
    //可以讲任何对象放进一个List<Object>，但只能将字符串放进List<String>
}
