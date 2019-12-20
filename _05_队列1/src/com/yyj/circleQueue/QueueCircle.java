package com.yyj.circleQueue;
@SuppressWarnings("unchecked")

public class QueueCircle<E> {
	private E[] elements;
	private int size;  
	private int front; //头的位置
	
	private int DEFAULT_CAPACITY = 10;
	public QueueCircle() {
		elements = (E[])new Object[DEFAULT_CAPACITY];
	}
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void enQueue(E element) {
		ensureCapacity(size+1);
		//元素的真实位置 
//		elements[(size+front)%elements.length] = element;
		elements[RealIndex(size)] = element;
		size++;
	}
	
	public E deQueue() {
		E deletE = elements[front];
		elements[front] = null;
		//删除后前进一步， 再%列表长度得到真实位置
//		front = (1+front)%elements.length;  //删除后front向前移动后的真实位置
		front = RealIndex(1);
		size--;
		return deletE;
	}
	
	public E peek() {
		return elements[front];
	}
	
	//扩容
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity >= capacity) return;
		
		//扩容为原来1.5倍  位运算记得括号
		int newCapacity = oldCapacity + (oldCapacity>>1);
		E[] newElements = (E[])new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[RealIndex(i)];
		}
		elements = newElements;
		//头重置
		front = 0;
	}
	
	//处理返回真实索引
	private int RealIndex(int index) {
//		index += front;
//		return index%elements.length;
		//模运算、 * 、/、浮点计算 运算效率低下， 替换如下
		index += front;
		int lenth = elements.length;
		//条件 index>0 lenth>0 n<2*lenth
		return index - (index >= lenth? lenth : 0);
	}
	
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("capcacity=").append(elements.length)
		.append(" size=").append(size)
		.append(" front=").append(front)
		.append(", [");
		for (int i = 0; i < elements.length; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(elements[i]);
		}
		string.append("]");
		return string.toString();
	}

	
	
}
