package com.data_structure.binary_tree;

public class BinaryCreatorFactory extends AbsBinaryCreatorFactory {
    /**
     *      A
     *     /  \
     *    B    C
     *   / \   /\
     *  D   E F  G
     *
     */
    private String[] tree = {"A",
            "B","D","","","E","","",
            "C","F","","","G"
    };
    private int index = 0;
    @Override
    BinaryTreeNode preOrderCreateBTree() {
        return createPreBTree();
    }

    @Override
    BinaryTreeNode inOrderCreateBTree() {
        return null;
    }

    @Override
    BinaryTreeNode postOrderCreateBTree() {
        return null;
    }

    /**
     * 前序遍历创建二叉树
     * @return 创建完毕的二叉树节点
     */
    private BinaryTreeNode createPreBTree(){
        if(index >= tree.length){
            return null;
        }
        String data = tree[index];
        index++;
        if(data.isEmpty()){
            return null;
        }else{
            BinaryTreeNode bt = new BinaryTreeNode<>(data);
            bt._LNode = createPreBTree();
            bt._RNode = createPreBTree();
            return bt;
        }
    }
}
