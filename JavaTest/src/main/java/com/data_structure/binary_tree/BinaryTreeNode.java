package com.data_structure.binary_tree;

/**
 * 二叉树储存节点
 * @param <T>
 */
public class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T> _LNode;
    BinaryTreeNode<T> _RNode;

    public BinaryTreeNode() {
        this.data = null;
        this._LNode = null;
        this._RNode = null;
    }

    public BinaryTreeNode(T data) {
        this.data = data;
        this._LNode = null;
        this._RNode = null;
    }

    public BinaryTreeNode(T data, BinaryTreeNode<T> _LNode, BinaryTreeNode<T> _RNode) {
        super();
        this.data = data;
        this._LNode = _LNode;
        this._RNode = _RNode;
    }

    /**
     * 是否是叶节点(度为0的节点)
     */
    public boolean isLeaf() {
        return this._LNode == null && this._RNode == null;
    }


    @Override
    public String toString() {
        return data + "";
    }
}
