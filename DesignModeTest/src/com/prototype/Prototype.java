package com.prototype;

import java.io.*;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/8/24 11:28 <br>
 * description: 原型模式（Prototype） <br>
 *     原型模式虽然是创建型的模式，但是与工厂模式没有关系，
 *     从名字即可看出，该模式的思想就是将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象
 */
public class Prototype implements Cloneable,Serializable {

    private static final long serialVersionUID = 1L;
    private String string;

    private SerializableObject obj;

    /**
     * 浅复制
     * @return
     * @throws CloneNotSupportedException
     */
    //此处clone方法可以改成任意的名称，因为Cloneable接口是个空接口
    public Object clone() throws CloneNotSupportedException {
        //此处的重点是super.clonepra()这句话，super.clonepra()调用的是Object的clone()方法 native的
        Prototype proto = (Prototype) super.clone();
        return proto;
        ///--------> 扩展
//        对象深、浅复制的概念：
//          浅复制：将一个对象复制后，基本数据类型的变量都会重新创建，而引用类型，指向的还是原对象所指向的。
//          深复制：将一个对象复制后，不论是基本数据类型还有引用类型，都是重新创建的。简单来说，就是深复制进行了完全彻底的复制，而浅复制不彻底。
    }

    /** 深复制 */
    public Object deepClone() throws IOException, ClassNotFoundException {
        //要实现深复制，需要采用流的形式读入当前对象的二进制输入，再写出二进制数据对应的对象。

        /* 写入当前对象的二进制流 */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        /* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public SerializableObject getObj() {
        return obj;
    }

    public void setObj(SerializableObject obj) {
        this.obj = obj;
    }

}
class SerializableObject implements Serializable {
    private static final long serialVersionUID = 1L;
}