package com.data_structure;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/11/25 22:34 <br>
 * description: 链表的操作集合 <br>
 * 1: 单链表的创建和遍历
 * <p/>
 * 2: 求单链表中节点的个数
 * <p/>
 * 3、查找单链表中的倒数第k个结点
 * <p/>
 * 4、查找单链表中的中间结点  与3同思路
 * <p/>
 * 5、合并两个有序的单链表，合并之后的链表依然有序【出现频率高】
 *
 * 		链表1：
			 1->2->3->4
		链表2：
			 2->3->4->5
		合并后：
			1->2->2->3->3->4->4->5
 * <p/>
 * 6、单链表的反转【出现频率最高】
 * 		例如链表：
 			1->2->3->4
		反转之后：
 			4->2->2->1

 * <p/>
 * 7、从尾到头打印单链表
 * <p/>
 * 8、判断单链表是否有环
 * <p/>
 * 9、取出有环链表中，环的长度
 * <p/>
 * 10、单链表中，取出环的起始点。本题需利用上面的第8题和第9题。
 * <p/>
 * 11、判断两个单链表相交的第一个交点
 */
public class LinkedList {

	private Node headP;
	private Node p;

	/**
	 * 节点入栈
	 *
	 * @param node 外部传入的节点数据
	 */
	public void enqueueNode(Node node) {
		if (headP == null) {
			System.out.println("头结点为空,新进数据为头结点");
			headP = new Node();
			headP = node;
			p = headP;
		} else {
//            System.out.println("---创建新节点，放在当前节点后面");
			node.preNode = p;
			p.nextNode = node;
			p = p.nextNode; //指针前进
		}
	}

	/**
	 * 从目标节点开始遍历
	 *
	 * @param node
	 */
	public void foreach(Node node) {
		if (node == null) {
			return;
		}
		p = node;
		while (p != null) {
			System.out.println("-----> point:" + p.getName());
			p = p.nextNode;
		}
	}

	/**
	 * 获取指定节点后面的长度
	 *
	 * @param node node参数必须为当前链表的接点值
	 * @return
	 */
	public int getLength(Node node) {
		if (node == null) {
			return 0;
		}
		int length = 1;
		while (node.nextNode != null) {
			length++;
			node = node.nextNode;
		}
		return length;
	}


	/**
	 * 获取正序index位的接点
	 * @param index
	 * @return
	 */
//    public Node getNodePos(int index){
//        if(index < 0 || index > getLength(headP)){
//            throw  new IllegalArgumentException("index is illegal.");
//        }
//        while (int )
//
//
//    }

	/**
	 * 获取逆序的第index个接点
	 * 法一：获取总长度  然后   总长度 - index
	 * 法二：如下代码思路
	 *
	 * @param index
	 * @return
	 */
	public Node getReverseNode(int index) {
		if (index <= 0 || index > getLength(headP)) {
			throw new IllegalArgumentException("index is illegal.");
		}
		Node first = headP;
		Node second = headP;
		//让second结点往后挪index个位置
		for (int i = 0; i < index; i++) {
			second = second.nextNode;
		}
		//让first和second结点整体向后移动，直到second结点为Null
		while (second != null) {
			first = first.nextNode;
			second = second.nextNode;
		}
		return first;
	}

	/**
	 * 返回以给定头结点为起点 到链尾的中间节点
	 * @param head
	 * @return
	 */
	public Node findMidNode(Node head) {
		if (head == null) {
			return null;
		}
		Node first = head;
		Node second = head;
		//每次移动时，让second结点移动两位，first结点移动一位
		while (second != null && second.nextNode != null) {
			first = first.nextNode;
			second = second.nextNode.nextNode;
		}
		//直到second结点移动到null时，此时first指针指向的位置就是中间结点的位置
		return first;
		// 当n为偶数时，得到的中间结点是第n/2 + 1个结点。
		// 比如链表有6个节点时，得到的是第4个节点。

	}


	/**
	 * 查找指定节点的前一个结点
	 *
	 * @param node
	 */
	public Node preNode(Node node) {
		if (node == null) {
			throw new IllegalArgumentException("current Node is null.Please make sure the node is exit!");
		}
		if (node.preNode == null) {
			System.out.println("当前为表头,已无前置节点。");
			return null;
		} else {
			return node.preNode;
		}

	}

	/**
	 * 当前节点的下一个节点
	 *
	 * @return 下一个节点
	 */
	public Node nextNode(Node node) {
		if (node == null) {
			throw new IllegalArgumentException("Current Node is null.Please make sure the list has head!");
		}
		if (node.nextNode == null) {
			System.out.println("At End！");
			return null;
		}
		return node.nextNode;
	}

	/**
	 * 合并两个单链表【面试出现的频率很高】
	 * @param head1 链表1的头结点
	 * @param head2 链表2的头结点
	 * @return
	 */
	public Node mergeLinkList(Node head1, Node head2) {
		if(head1 == null && head2 == null){
			return null;
		}
		if(head1 == null){
			return head2;
		}
		if(head2 == null){
			return head1;
		}
		//构建新链表
		Node nh; //新链表的头结点
		Node p;  //current结点指向新链表
		// 一开始，让current结点指向head1和head2中较小的数据，得到head结点
		if(head1.getNum() < head2.getNum()){
			nh = head1;
			p = nh;
			head1 = head1.nextNode;
		}else{
			nh = head2;
			p = nh;
			head2 = head2.nextNode;
		}

		//循环遍历两个单链表  处理公共长度部分
		while (head1 != null && head2 != null){
			if(head1.getNum() < head2.getNum()){
				p = head1.nextNode;
				p = p.nextNode;
				head1 = head1.nextNode;
			}else{
				p = head2.nextNode;
				p = p.nextNode;
				head2 = head2.nextNode;
			}
		}

		//合并剩余元素
		if(head1 != null){
			p.nextNode = head1;
		}
		if(head2 != null){
			p.nextNode = head2;
		}
		return nh;
	}


	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		LinkedList linkedList1 = new LinkedList();
		LinkedList linkedList2 = new LinkedList();
		for (int i = 1; i <= 20; i++) {
			Node node = new Node();
			node.setNum(i);
			node.setName("我是----" + i);
			linkedList.enqueueNode(node);
		}
		for (int i = 15; i <= 25; i++) {
			Node node = new Node();
			node.setNum(i);
			node.setName("I是----" + i);
			linkedList1.enqueueNode(node);
		}
//		linkedList.foreach(linkedList.headP);
//		System.out.println("第二个节点下一个节点为：" + linkedList.nextNode(linkedList.headP.nextNode).getName());
		//从头获取链表长度
		int totalLength = linkedList.getLength(linkedList.headP);
		System.out.println("当前链表长度：" + totalLength);

		System.out.println("倒数第5个接点：" + linkedList.getReverseNode(3).getName());

		linkedList2.headP = linkedList2.mergeLinkList(linkedList1.headP,linkedList.headP);
		// 从head节点开始遍历输出
		linkedList2.foreach(linkedList2.headP);

	}

}
