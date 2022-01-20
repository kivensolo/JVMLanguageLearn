package com.data_structure.binary_tree;

import java.util.Stack;

/**
 * 二叉树工具类
 *
 * https://www.jianshu.com/p/0190985635eb
 * https://zhuanlan.zhihu.com/p/345665233/
 * 知识点掌握：
 * 1. 二叉树的遍历（前中后序，以及递归、非递归模式）
 */
public class BinaryUtils {
// <editor-fold defaultstate="collapsed" desc="树的度查找">
    /**
     * 最大深度获取(后序遍历法)
     * @param node 二叉树对象
     * @return 树深度
     */
    public static int maxDepth(BinaryTreeNode node) {
        if (node == null) return 0;
        int leftDepth = maxDepth(node._LNode);
        int rightDepth = maxDepth(node._RNode);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 最小深度查找
     * @param node
     * @return
     */
    public static int minDepth(BinaryTreeNode node) {
        if (node == null) return 0;
        return getMin(node);
    }

    private static int getMin(BinaryTreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root._LNode == null && root._RNode == null) {
            return 1;
        }
        int leftMinDepth = getMin(root._LNode);
        int lrightMinDepth = getMin(root._RNode);
        return Math.min(leftMinDepth, lrightMinDepth) + 1;
    }
// </editor-fold>


// <editor-fold defaultstate="collapsed" desc="遍历 ---- 递归法">
    /**
     * 递归法前序遍历二叉树
     */
    static void recursivePreOrderPrint(BinaryTreeNode root){
        if (root == null) {
            System.out.print("");
        } else {
            System.out.print(root.data);
            recursivePreOrderPrint(root._LNode);
            recursivePreOrderPrint(root._RNode);
        }
    }
    /**
     * 递归法中序遍历二叉树
     */
    static void recursiveInOrderPrint(BinaryTreeNode root){
        if (root == null) {
            System.out.print("");
        } else {
            recursivePreOrderPrint(root._LNode);
            System.out.print(root.data);
            recursivePreOrderPrint(root._RNode);
        }
    }
    /**
     * 递归法后序遍历二叉树
     */
    static void recursivePostOrderPrint(BinaryTreeNode root) {
        if (root == null) {
            System.out.print("");
        } else {
            recursivePreOrderPrint(root._LNode);
            recursivePreOrderPrint(root._RNode);
            System.out.print(root.data);
        }
    }
// </editor-fold>


// <editor-fold defaultstate="collapsed" desc="遍历 ---- 基于栈的非递归方式">

    /**
     * 非递归的前序遍历方式
     * @param root 树根节点
     */
    static void noRecursivePreOrderPrint(BinaryTreeNode root){
        if(root == null){
            System.err.println("TreeNode is Empty.");
            return;
        }

        Stack<BinaryTreeNode> stack = new Stack<>();
        //节点入栈
        stack.push(root);
        while (!stack.empty()){
            BinaryTreeNode node = stack.pop();
            if(node._RNode != null){ //先压右节点，因为取得时候先去左节点
                stack.push(node._RNode);
            }
            if(node._LNode != null){
                stack.push(node._LNode);
            }
            System.out.print(node.data);
        }
    }

    /**
     * 非递归的中序遍历方式
     * 先全部左节点，再父节点，再全部右节点
     * @param root 树根节点
     */
    static void noRecursiveInOrderPrint(BinaryTreeNode root){
        if(root == null){
            System.out.println("TreeNode is Empty.");
            return;
        }

        Stack<BinaryTreeNode> stack = new Stack<>();
        while (root != null || !stack.empty()){
            while (root != null){//压根节点及其左侧子节点
                stack.push(root);
                root = root._LNode;
            }
            if(!stack.isEmpty()){
                BinaryTreeNode dumpNode = stack.pop();
                //找出节点值
                System.out.print(dumpNode.data);
                //查找该节点右侧子节点，继续循环查右侧的左子节点
                root = dumpNode._RNode;
            }
        }
    }
    /**
     * 非递归的后序遍历方式
     * 后续遍历比较特殊，需要2个栈来存节点
     * @param root 树根节点
     */
    static void noRecursivePostOrderPrint(BinaryTreeNode root){
        if(root == null){
            System.out.println("TreeNode is Empty.");
            return;
        }

        //准备栈  准备栈的数据需要移动到dump栈，再获取值
        Stack<BinaryTreeNode> readySatck = new Stack<>();
        //输出栈
        Stack<BinaryTreeNode> dumpStack = new Stack<>();
        readySatck.push(root);
        while (!readySatck.isEmpty()){
            //准备栈中存在节点
            BinaryTreeNode popNode = readySatck.pop();
            dumpStack.push(popNode);


            if (popNode._LNode != null) {//这个时候要先入栈LNode,因为移至dumpStack时，就在栈顶了
                readySatck.push(popNode._LNode);
            }
            if (popNode._RNode != null) {
                readySatck.push(popNode._RNode);
            }
        }

        //DUMP栈不为空时，取出遍历后的数据
        while(!dumpStack.isEmpty()){
            BinaryTreeNode dumpNode = dumpStack.pop();
            System.out.print(dumpNode.data);
        }
    }

// </editor-fold>


// <editor-fold defaultstate="collapsed" desc="层序遍历 TODO">


// </editor-fold>

}
