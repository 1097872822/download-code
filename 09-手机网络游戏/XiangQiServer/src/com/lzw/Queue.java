package com.lzw;

/**
 “先进先出”(LIFO)的存储结构。数据元素只能从队尾进入，从队首取出。在队列中，
 数据元素可以任意增减，但数据元素的次序不会改变。每当有数据元素从队列中被取出，
 后面的数据元素依次向前移动一位。所以，任何时候从队列中读到的都是队首的数据。
 根据这些特点，对队列定义了以下六种操作：
 push(x) 向队列插入一个值为x的元素；
 pop() 从队列中取出一个元素；
 front() 从队列中读一个元素，但队列保持不变；
 empty() 判断队列是否为空，空则返回真；
 clear() 清空队列；
 search(x) 查找距队首最近的元素的位置，若不存在，返回-1
 */

public class Queue extends java.util.Vector {
	public class EmptyQueueException extends java.lang.RuntimeException {
		public EmptyQueueException() {
			super();
		}
	}
	public Queue() {
		super();
	}
	public synchronized void push(Object x) {
		super.addElement(x);
	}
	public synchronized Object pop() {
		/* 队列若为空，引发EmptyQueueException异常 */
		if (this.empty())
			throw new EmptyQueueException();
		Object x = super.elementAt(0);
		super.removeElementAt(0);
		return x;
	}
	public synchronized Object front() {
		if (this.empty())
			throw new EmptyQueueException();
		return super.elementAt(0);
	}
	public boolean empty() {
		return this.isEmpty();
	}
	public synchronized void clear() {
		super.removeAllElements();
	}
	public int search(Object x) {
		return super.indexOf(x);
	}
}
