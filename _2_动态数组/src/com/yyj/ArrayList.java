package com.yyj;

//import javax.lang.model.util.Elements;
@SuppressWarnings("unchecked")

public class ArrayList<E> {
	/**
	 * 元素数量
	 */
	private int size;
	/**
	 * 所有元素
	 */
	private E[] elements;
	
	private static final int DEFAULT_CAPACITY = 10; 
	private static final int ELEMENT_NOT_FOUND = -1;
	
	public ArrayList(int capacity) {
		capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
		elements = (E[]) new Object[capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * 元素数量
	 * @return
	 * */
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean contains(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}
	
	public void add(E element) {
		add(size, element);
	}
	
	public E get(int index) {
		rangeCheck(index);
		return elements[index];
	}
	
	public E set(int index, E element) {
		rangeCheck(index);
		E oldElementE = elements[index];
		elements[index] = element;
		return oldElementE;
	}
	
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		ensureCapacity(size+1); //即将到来的长度
		
		//先挪到后边
		for (int i = size; i > index; i--) {
			elements[i] = elements[i-1];
		}
		//再添加
		elements[index] = element;
		size++;
	}
	
	public E remove(int index) {
		rangeCheck(index);
		
		E oldElementE = elements[index];
		for (int i = index+1; i < size; i++) { //往前挪
			elements[i-1] = elements[i];
		}
		elements[--size] = null; //最后一个设为null， 同时size -1
		trim();
		return oldElementE;
	}
	
	public void clear() {
		//如果存放对象，就把指针指向null， 释放堆中对象(数组存放的是地址，在栈中)
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;   //如果只设计一个整数的动态数组其实这一句就够了，外部接口不关心内部实现
	}
	
	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) return i;
			} 
		}else {
			for (int i = 0; i < size; i++) {
				if (element.equals(elements[i])) return i;				
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	
	///私有方法
	
	/**
	 * 扩容
	 * */
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity>=capacity) return;
		
		int newCapacity = oldCapacity + oldCapacity>>1; //扩容为1.5倍，官方做法
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		
		System.out.println(oldCapacity + "扩容为" + newCapacity);
	}
	
	/**
	 * 缩容
	 * */
	private void trim() {
		//只要扩容乘以缩容不等于1，就不会造成复杂度震荡（比如扩容2， 缩容0.5）
		int oldCapacity = elements.length;
		int newCapacity = oldCapacity>>1;  //缩容为原来0.5；
		if (size>newCapacity || oldCapacity<= DEFAULT_CAPACITY) return; //容量过小，或者size内容够多不缩容
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
		
		System.out.println(oldCapacity + "缩容为" + newCapacity);
	}
	
	
	private void rangeCheck(int index) {
		if (index<0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	private void rangeCheckForAdd(int index) {
		if (index<0 || index > size) {
			outOfBounds(index);
		}
	}
	
	private void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("index:" + index + "size:" + size);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(elements[i]);
			
//			if (i != size - 1) {
//				string.append(", ");
//			}
		}
		string.append("]");
		return string.toString();
	}
}
