package com.yyj;
import com.yyj.list.bidirectionalCircleLinkList;


public class stack<E> {
	//包含一个链表
	private bidirectionalCircleLinkList<E> list = new bidirectionalCircleLinkList<E>();

	//对外接口
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void push(E element) {
		list.add(element);
	}
	public E pop() {
		return list.remove(list.size()-1);
	}
	
	public E top() {
		return list.get(list.size()-1);
	}
	
}
