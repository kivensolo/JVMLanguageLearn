package com.data_structure.binary_tree;

/**
 * 构建二叉树的抽象工厂
 */
abstract class AbsBinaryCreatorFactory {
    /**
     * 前序遍历创建二叉树
     * 先创建根节点在创建左右子树
     * @return
     */
    abstract BinaryTreeNode preOrderCreateBTree();

    /**
     * 中序遍历创建二叉树
     * 先创建根节点在创建左右子树
     * @return
     */
    abstract BinaryTreeNode inOrderCreateBTree();

    /**
     * 后序遍历创建二叉树
     * 先创建根节点在创建左右子树
     * @return
     */
    abstract BinaryTreeNode postOrderCreateBTree();
}
