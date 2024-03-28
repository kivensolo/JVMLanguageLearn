package com.kingz.bytecode.asm.writer;

import com.kingz.bytecode.asm.visitor.ClassInfoPrinter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.objectweb.asm.Opcodes.*;

public class WriterTest {
    /**
     * 生成以下内部类接口：
     * package pkg;
     * public interface Comparable extends Mesurable {
     *  int LESS = -1;
     *  int EQUAL = 0;
     *  int GREATER = 1;
     *  int compareTo(Object o);
     * }
     */
    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(0);
        //定义了类的标头
        //V1_7 参数是一个常数，与所有其他 ASM 常量一样， 在 ASM Opcodes 接口中定义。它指明了类的版本——Java 1.7
        cw.visit(V1_7,
                ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "pkg/Comparable",
                null,
                "java/lang/Object",
                new String[] { "pkg/Mesurable" }
        );
        //public final static
        int access = ACC_PUBLIC + ACC_FINAL + ACC_STATIC;
        cw.visitField(access, "LESS", "I", null, new Integer(-1))
                .visitEnd();
        cw.visitField(access, "EQUAL", "I", null, new Integer(0))
                .visitEnd();
        cw.visitField(access, "GREATER", "I",null, new Integer(1))
                .visitEnd();
        cw.visitMethod(
                ACC_PUBLIC + ACC_ABSTRACT,
                        "compareTo","(Ljava/lang/Object;)I", null, null)
                .visitEnd();
        cw.visitEnd();
        byte[] bytes = cw.toByteArray();
        File file = new File("compare.class");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
