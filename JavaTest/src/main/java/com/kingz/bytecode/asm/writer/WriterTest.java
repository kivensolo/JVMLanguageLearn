package com.kingz.bytecode.asm.visitor;

import org.objectweb.asm.ClassReader;

import java.io.IOException;

public class ClassInfoTest {
    public static void main(String[] args) {
        ClassInfoPrinter cp = new ClassInfoPrinter();
        try {
            ClassReader cr = new ClassReader("java.lang.Runnable");
            cr.accept(cp, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
