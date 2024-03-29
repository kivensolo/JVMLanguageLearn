[TOC]

# 术语
- **结点**：包含一个数据元素及若干指向子树分支的信息。
- **结点的度**：一个节点子树（即分支）个数。
- **叶结点**：也称为终端节点，指度为0的节点。
- **树的深度（高度）**：树中结点层次的最大值。
- **同构**：将节点适当重命名，即可得两颗完全相同的树
- **孩子节点**：一个节点的直接后继节点。
- **双亲节点**：一个节点的直接前驱节点。
- **有序树**：如果树中各棵子树的次序是有先后次序，则称该树为有序树。 
- **无序树**：如果树中各棵子树的次序没有先后次序，则称该树为无序树。

# 概念
## 二叉树
①节点的度不可以超过2

②节点的孩子节点次序不可颠倒

## 满二叉树
每层得节点数都是满的，即 `2^(n-1)`

## 完全二叉树
        1
       / \
      2   3
     /\   /\
    4  5 6  7
节点1~n分别对应于满二叉树的节点1~n
### 性质
- 若节点序号为i（i>1），则其双亲节点序号为i/2。（这里是整除）
- 若节点序号为i（i>=1），则其左子节点序号为2i。
- 若节点序号为i （i>=1），则其右子节点序号为2i+1。

# 遍历
二叉树遍历方式分为三种
前序遍历（根左右）： 访问根结点，再访问左子树、再访问右子树。
中序遍历（左根右）： 先访问左子树，再访问根结点、再访问右子树。
后续遍历（左右根）： 先访问左子树，再访问右子树，再访问根结点。
例如一个这个样子的二叉树，按三种遍历方法分别遍历，输出的结果分别是:

        A
       / \
      B   C
     /\   /\
    D  E F  G
   
前序遍历： ABDECFG

中序遍历： DBEAFCG

后续遍历： DEBFGCA

注：以上**前序**、**中序**、**后序**每一种遍历方式都有`递归`和`非递归`两种实现方法

前序遍历就是深度优先遍历（DFS）

层次遍历就是广度优先遍历（BFS）


[参考资料1](https://blog.csdn.net/ping1632743560/article/details/52902240)
[二叉树各种遍历](https://zhuanlan.zhihu.com/p/345665233)