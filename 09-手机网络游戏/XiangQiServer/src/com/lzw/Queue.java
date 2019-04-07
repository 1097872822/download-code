package com.lzw;

/**
 ���Ƚ��ȳ���(LIFO)�Ĵ洢�ṹ������Ԫ��ֻ�ܴӶ�β���룬�Ӷ���ȡ�����ڶ����У�
 ����Ԫ�ؿ�������������������Ԫ�صĴ��򲻻�ı䡣ÿ��������Ԫ�شӶ����б�ȡ����
 ���������Ԫ��������ǰ�ƶ�һλ�����ԣ��κ�ʱ��Ӷ����ж����Ķ��Ƕ��׵����ݡ�
 ������Щ�ص㣬�Զ��ж������������ֲ�����
 push(x) ����в���һ��ֵΪx��Ԫ�أ�
 pop() �Ӷ�����ȡ��һ��Ԫ�أ�
 front() �Ӷ����ж�һ��Ԫ�أ������б��ֲ��䣻
 empty() �ж϶����Ƿ�Ϊ�գ����򷵻��棻
 clear() ��ն��У�
 search(x) ���Ҿ���������Ԫ�ص�λ�ã��������ڣ�����-1
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
		/* ������Ϊ�գ�����EmptyQueueException�쳣 */
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
