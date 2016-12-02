package com.data_structure;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/9/4 16:10
 * description: 节点信息
 *   //双向链表接点
 */
public class Node {

    public  Node preNode;
    public  Node nextNode;

    private String name;
    private int num;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
