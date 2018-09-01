package com.memoryleak;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: King.Z
 * date:  2016/11/12 21:40
 * description: ThreadLocal的学习
 */
public class ThreadId {
	// Atomic integer containing the next thread ID to be assigned
	private static final AtomicInteger nextId = new AtomicInteger(0);
	//包含每一个线程的ID的线程本地变量
	private static final ThreadLocal<Integer> threadId =	new ThreadLocal<Integer>() {
																@Override
																protected Integer initialValue() {
																	return nextId.getAndIncrement();
																}
															};

	// Returns the current thread's unique ID, assigning it if necessary
	public static int get() {
		return threadId.get();
	}
}
