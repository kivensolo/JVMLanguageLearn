package com.data_structure.linked;

/**
 * author: King.Z <br>
 * date:  2016/11/25 22:34 <br>
 * description: 单链表的创建和遍历 <br>
 */
public class LinkedList {

    private Node headP; //单链表的头结点
    private Node p;


    /**
     * 节点入栈
     * @param node
     */
    public void enqueueNode(Node node){
        System.out.println("入栈");
        if(headP == null){
            System.out.println("头结点为空,新进数据为头结点");
            headP = new Node();
            headP = node;
            p = headP;
        }else{
            System.out.println("创建新节点，放在当前节点后面");
            node.preNode = p;
            p.nextNode = node;
            p = p.nextNode; //指针前进
        }
    }

    /**
     * 从目标节点开始遍历
     * @param node
     */
    public void foreach(Node node){
        if(node == null) {
            return;
        }
        p = node;
        while (p != null){
            System.out.println("-----> point:"+p.getData());
            p = p.nextNode;
        }
    }

    /**
     * 查找指定节点的前一个结点
     * @param node
     */
    public Node preNode(Node node){
       if(node == null){
            throw new NullPointerException("current Node is null.Please make sure the node is exit!");
        }
        if(node.preNode == null){
            System.out.println("当前为表头,已无前置节点。");
            return null;
        }else{
            return node.preNode;
        }

    }

    /**
     * 当前节点的下一个节点
     * @return 下一个节点
     */
    public Node nextNode(Node node){
        if(node == null){
            throw new NullPointerException("current Node is null.Please make sure the list has head!");
        }
        if(p.nextNode == null){
            System.out.println("At End！");
            return  null;
        }
        return p.nextNode;
    }

    public static void main(String[] args) {
        LinkedList  quene = new LinkedList();
        for (int i=0;i<4;i++){
            Node node = new Node();
            node.setnum(i);
            node.setData("我是----"+i);
            quene.enqueueNode(node);
        }
        quene.foreach(quene.headP);

        System.out.println("第二个节点下一个节点为："+quene.nextNode(quene.headP.nextNode).getData());

    }

}
