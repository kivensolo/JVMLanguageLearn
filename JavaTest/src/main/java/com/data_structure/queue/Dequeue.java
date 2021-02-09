package com.data_structure.queue;

/**
 * author: King.Z <br>
 * date:  2018/9/3 22:49 <br>
 * description: Realization of Dequeue <br>
 *     Deque是一种具有队列和栈的性质的数据结构.
 *     双端队列中的元素可以从两端弹出,其限定插入和删除操作在表的两端进行.
 *     当队列用的时候：
 *              添加元素到尾巴，删除时从头部删除。
 *     当作栈使用的时候：
 *              添加到头部，删除从尾部删除。
 *     Deque的容量有两种模式，一种是固定长度，另一种是容量无限。
 *
 *     不涉及到并发的情况下，有两个实现类，可根据其自身的特性进行选择：
 *          LinkedList 大小可变的链表双端队列，允许元素为 null
 *          ArrayDeque 大下可变的数组双端队列，不允许 null
 *          细节分析：https://github.com/CarpenterLee/JCFInternals/blob/master/markdown/4-Stack%20and%20Queue.md
 */
public class Dequeue {
}
