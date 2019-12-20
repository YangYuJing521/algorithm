package com.yyj.circleQueue;
@SuppressWarnings("unchecked")


//double ended circle queue
public class DequeCircle <E>{
	private E[] elements;
	private int front;
	private int size;
	private int ELEMENT_CAPACITY = 10;
	
	
	public DequeCircle() {
		elements = (E[])new Object[ELEMENT_CAPACITY];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	//尾巴入
	public void enQueueRear(E element) {
		ensureCapacity(size+1);
		
		elements[realIndex(size)] = element;
		size++;
	}
	
	public E deQueueFront() {
		E deElement = elements[front];
		elements[front] = null;
		front = realIndex(1);
		size --;
		return deElement;
	}
	
	public E getFront() {
		return elements[front];
	}
	
	//从头入
	public void enQueueFront(E element) {
		ensureCapacity(size+1);
		front = realIndex(-1); //头往尾的反方向走一步
		elements[front] = element; //赋值给头
		size++;
	}
	
	public E deQueueRear() {
		E delElement = elements[realIndex(size-1)];
		elements[realIndex(size-1)] = null;
		size--;
		return delElement;
	}
	
	public E getRear() {
		return elements[realIndex(size-1)];
	}
	
	private void ensureCapacity(int comingSize) {
		int oldCapacity = elements.length;
		if (oldCapacity >= comingSize) return;
		
		int newCapacity = oldCapacity + (oldCapacity>>1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[realIndex(i)];
		}
		elements = newElements;
		front = 0;
	}
	
	private int realIndex(int index) {
		index += front;
		int length = elements.length;
		if (index < 0) {
			return length+index;  //如果从头进入，front左移的时候，为负数，则直接把front放到数组最右端
		}
		//>= !!!
		return index - (index >= length ? length : 0);
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
