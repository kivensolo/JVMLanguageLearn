package com.data_structure.queue;

import java.util.AbstractCollection;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

/**
 * author: King.Z
 * date:  2017/1/5 21:01
 * description: JDK提供的常规队列的使用
 *  	  抛出异常  |   返回特殊值 |
	| 插入   add(e) | offer(e) 	  |
	| 移除  remove()| poll() 	  |
	| 检查 element()| peek() 	  |
 */
public class NormalQuqueUse extends AbstractCollection{
	private static Queue<Object> mArrQueue = new ArrayDeque<Object>();
	private static Queue<Delayed> mDelayQueue = new DelayQueue<Delayed>();


	public static void main(String[] args) {

		BoundedCirclrQueue circlrQueue = new BoundedCirclrQueue();
		for (int i = 0; i < 9; i++) {
			if (!circlrQueue.offer(i)) {
				System.out.println("队列数据已满");
			}
		}
		//for(int i=0;i<20;i++){
		//	mArrQueue.add("num:"+i);
		//}
		//System.out.println(mArrQueue.toString());
	}

	public boolean addItem(Object o){
		return mArrQueue.add(o);
	}

	public boolean removeItem(Object o){
		return mArrQueue.remove(o);
	}
	@Override
	public Iterator iterator() {
		return null;
	}

	@Override
	public int size() {
		return mArrQueue.size();
	}
}
