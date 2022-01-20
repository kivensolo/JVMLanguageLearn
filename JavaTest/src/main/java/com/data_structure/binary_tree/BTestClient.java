package com.data_structure.binary_tree;

public class BTestClient {
    public static void main(String[] args) {
        BinaryCreatorFactory binaryCreatorFactory = new BinaryCreatorFactory();
        BinaryTreeNode bTree = binaryCreatorFactory.preOrderCreateBTree();

        System.out.println("==============递归方式遍历二叉树===============");
        System.out.print("前序遍历:");
        BinaryUtils.recursivePreOrderPrint(bTree);
        System.out.print("\n中序遍历:");
        BinaryUtils.recursiveInOrderPrint(bTree);
        System.out.print("\n后序遍历:");
        BinaryUtils.recursivePostOrderPrint(bTree);

        System.out.print("\n==============非递归方式遍历二叉树===============");
        System.out.print("\n前序遍历:");
        BinaryUtils.noRecursivePreOrderPrint(bTree);
        System.out.print("\n中序遍历:");
        BinaryUtils.noRecursiveInOrderPrint(bTree);
        System.out.print("\n后序遍历:");
        BinaryUtils.noRecursivePostOrderPrint(bTree);

    }
}
