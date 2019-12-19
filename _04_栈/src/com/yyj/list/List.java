package com.yyj.list;

public interface List<E> {

	static final int ELEMENT_NOT_FOUND = -1;
	
	//线性表中通用的接口方法
	void clear();
	
	boolean isEmpty();
	
	int size();
	
	int indexOf(E element);
	
	boolean contains(E element);
	
	void add(E element);
	
	E get(int index);
	
	/**
	 * 设置index位置的元素
	 * @param index
	 * @param element
	 * @return 原来的元素ֵ
	 */
	E set(int index, E element);

	void add(int index, E element);
	
	E remove(int index);
}
