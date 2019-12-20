package com.yyj;
import com.yyj.list.bidirectionalLinkList;

public class Queue<E> {
	private bidirectionalLinkList<E> list = new bidirectionalLinkList<>();
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void enQueue(E element) {
		list.add(element);
	}
	
	public E deQueue() {
		return list.remove(0);	
	}
	
	public E peek() {
		return list.get(0);
	}
}
