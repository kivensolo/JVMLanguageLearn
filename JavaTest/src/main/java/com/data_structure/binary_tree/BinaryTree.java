package com.data_structure.binary_tree;

public class BinaryTree<T> {
    private BinaryTreeNode<T> root;

    public BinaryTree() {
        this.root = new BinaryTreeNode<>();
    }

    public BinaryTreeNode<T> getRoot() {
        return this.root;
    }

    private boolean isEmpty(){
        return this.root == null;
    }
}
