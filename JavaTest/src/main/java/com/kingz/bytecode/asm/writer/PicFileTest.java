package com.kingz.bytecode.asm.writer;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.objectweb.asm.Opcodes.*;

public class PicFileTest {
    /**
     * 生成以下内部类接口：
     * package pkg;
     * public class Picture {
     *
     * }
     */
    public static void main(String[] args) {
        String imagePath = "E:\\GitHubProjects\\JVMLanguageLearn\\JavaTest\\preview.mp4";

        ClassWriter cw = new ClassWriter(0);
        //定义了类的标头
        //V1_7 参数是一个常数，与所有其他 ASM 常量一样， 在 ASM Opcodes 接口中定义。它指明了类的版本——Java 1.7
        cw.visit(V1_7, ACC_PUBLIC , "pkg/IncludeMp4", null, "java/lang/Object", null);

        //添加一个静态初始化方法。该方法将用于存储图片数据
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null);

        byte[] imageBytes;
        try {
            imageBytes = Files.readAllBytes(Paths.get(imagePath));
            mv.visitLdcInsn(imageBytes.length);
            mv.visitIntInsn(Opcodes.NEWARRAY, Opcodes.T_BYTE);
            mv.visitVarInsn(Opcodes.ASTORE, 0);  // 将字节数组存储到局部变量0
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < imageBytes.length; i++) {
            mv.visitVarInsn(Opcodes.ALOAD, 0);  // 加载字节数组
            mv.visitLdcInsn(i);
            mv.visitIntInsn(Opcodes.BIPUSH, imageBytes[i]);  // 加载字节数值
            mv.visitInsn(Opcodes.BASTORE);  // 存储字节数据到字节数组中
        }
        mv.visitInsn(Opcodes.RETURN);   //静态初始化方法中添加return指令。
//        结束静态初始化方法的访问。
        mv.visitMaxs(0, 0);  // 设置栈和局部变量的最大大小
        mv.visitEnd();

        byte[] classBytes = cw.toByteArray();

        //将生成的字节码写入文件
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("IncludeMp4.class");
            fos.write(classBytes);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
