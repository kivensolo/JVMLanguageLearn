package com.data_structure.linked;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2016/9/4 16:10
 * description: 节点信息
 */
public class Node {

    public  Node preNode;
    public  Node nextNode;

    private String data;
    private int num;

    public Node getNextNode(){
        return nextNode;
    }

    public Node getPreNode(){
        return preNode;
    }

    public String getData() {
        return data;
    }

    public void setData(String name) {
        this.data = name;
    }

    public int getNum() {
        return num;
    }

    public void setnum(int num) {
        this.num = num;
    }

}
