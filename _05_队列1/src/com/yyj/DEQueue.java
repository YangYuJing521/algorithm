package com.yyj;

import com.yyj.list.bidirectionalLinkList;

//double ended queue 
public class DEQueue<E> {

	bidirectionalLinkList<E> list = new bidirectionalLinkList<>();
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	//从头进
	public void enQueueFornt(E element) {
		list.add(0, element);
	}
	
	//尾出
	public E deQueueRear() {
		return list.remove(list.size()-1);
	}
	//看尾
	public E peekRear() {
		return list.get(list.size()-1);
	}
	

	//单向Queue的默认==》
	public void enQueueRear(E element) {
		list.add(element);
	}
	
	public E deQueueFront() {
		return list.remove(0);
	}
	
	public E peekFront() {
		return list.get(0);
	}
}
