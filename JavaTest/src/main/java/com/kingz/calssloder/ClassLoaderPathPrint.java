package com.kingz.calssloder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * author: King.Z <br>
 * date:  2018/1/2 22:11 <br>
 * description: 打印JAVA的三种classloader的加载路径 <br>
 * BootstrapClassLoder : 纯C++实现的类加载其，没有对应的java类，主要加载jre/lib目录下的核心库
 *              /jre/lib/resources.jar
                /jre/lib/rt.jar
                /jre/lib/sunrsasign.jar
                /jre/lib/jsse.jar
                /jre/lib/jce.jar
                /jre/lib/charsets.jar
                /jre/lib/jfr.jar
                /jre/classes
 * ExtClassLoader： sun.misc.Luncher$ExtClssLoader 主要加载/jre/lib/ext/目录下的扩展包
 *              /jre/lib/ext/access-bridge-64.jar
 *              /jre/lib/ext/cldrdata.jar
 *              /jre/lib/ext/dnsns.jar
 *              /jre/lib/ext/jaccess.jar
 *              /jre/lib/ext/jfxrt.jar
 *              /jre/lib/ext/localedata.jar
 *              /jre/lib/ext/nashorn.jar
 *              /jre/lib/ext/sunec.jar
 *              /jre/lib/ext/sunjce_provider.jar
 *              /jre/lib/ext/sunmscapi.jar
 *              /jre/lib/ext/sunpkcs11.jar
 *              /jre/lib/ext/zipfs.jar
 * AppClassLoader： sun.misc.Luncher$AppClssLoader 主要加载CLASSPATH路径下的包
 */
public class ClassLoaderPathPrint {
    public static void main(String[] args) {
        //Step one: get ClassLoaderPathPrint's classloder
        Class clazz = ClassLoaderPathPrint.class;
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.println("classLoader's name:" + classLoader.toString());

        //Step two: print AppClssLoader's load path.   AppClassLoader extends URLClassLoader
        URL[] urLs = ((URLClassLoader) classLoader).getURLs();
        print(urLs);
        System.out.println("");

        //Step three: by getParent(). obtain classLoader's parent.
        ClassLoader parentLoader = classLoader.getParent();
        System.out.println("AppClassLoader's parent name:" + parentLoader.toString());
        ClassLoader loader = parentLoader.getParent();
        System.out.println("extParentLoder's name:" + loader);
        System.out.println("");

        //Step four:printf BootstrapClassLoder load path.
        try {
            Class<?> launcherClass = Class.forName("sun.misc.Launcher");
            Method methodClassPath = launcherClass.getDeclaredMethod("getBootstrapClassPath", (Class<?>) null);
            if (methodClassPath != null) {
                methodClassPath.setAccessible(true);
                Object mObj = methodClassPath.invoke(null, (Class<?>) null);
                if(mObj != null){
                    Method methodGetUrls = mObj.getClass().getDeclaredMethod("getURLs", (Class<?>) null);
                    if(methodGetUrls != null){
                        methodGetUrls.setAccessible(true);
                        URL[] murlBoot = (URL[])methodGetUrls.invoke(mObj, (Class<?>) null);
                        print(murlBoot);
                    }
                }
            }
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void print(URL[] urls) {
        for (URL url : urls) {
            System.out.println(url);
        }
    }
}
