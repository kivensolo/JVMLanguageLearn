package com.reflect.clazz;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class GetClassObj {
    public static void main(String[] args) {
        String local = "E:\\GitHubProjects\\JVMLanguageLearn\\JavaTest\\build\\classes\\java\\main";
        String apkClassPath = "E:\\work\\Softterminal\\terminal-soft-git\\app\\build\\intermediates\\javac\\release\\classes";
        String injectLLineInfoParser = "com.starcor.terminal.helper.HNMultiBuildInjectLineInfo";
        String classFilePath = join(File.separator, new String[]{ local,
                injectLLineInfoParser.replace(".", File.separator) + ".class"
        });
        File clazzFile = new File(classFilePath);
        File clazzDir = clazzFile.getParentFile();
        URL url = null;
        try {
//            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            //应用他根本目录
//            String rootPath = contextClassLoader.getResource("").getPath();

            url = clazzDir.toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
            Class<?> parserClazz = classLoader.loadClass(injectLLineInfoParser);
            Constructor<?> constructor = parserClazz.getConstructor(String.class);
            Object instance = constructor.newInstance("1234568");
            System.out.println(instance.getClass().getMethod("getStbId").invoke(instance));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static String join(String sep, String[] container) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object s : container) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(sep);
            }
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
