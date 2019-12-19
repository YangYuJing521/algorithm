package com.yyj;

public class SingleLinkList<E> extends AbstractList<E> {

	//头结点
	private Node<E> first;
	
	//节点
	private static class Node<E> {
		E element;
		Node<E> next;
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}
	
	@Override
	public void clear() {
		first = null;
		size = 0;
	}

	@Override
	public int indexOf(E element) {
		if (element == null) {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element == null) return i;
				node = node.next;
			}
		}else {
			Node<E> node = first;
			for (int i = 0; i < size; i++) {
				if (node.element.equals(element)) return i;
				node = node.next;
			}
		}
		return ELEMENT_NOT_FOUND;
	}

	@Override
	public E get(int index) {
		return node(index).element;
	}

	@Override
	public E set(int index, E element) {
		rangeCheck(index);
		Node<E> node = node(index);
		E oldE = node.element;
		node.element = element;
		return oldE;
	}

	@Override
	public void add(int index, E element) {
		rangeCheckForAdd(index);
		//这里要注意第一个的位置(如果引入虚拟头结点，可以使代码逻辑统一)
		if (index == 0) {
			first = new Node<>(element, first);
		}else {
			Node<E> preNode = node(index-1);
			preNode.next = new Node<>(element, preNode.next);
		}
		
		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		//这里要注意第一个的位置
		Node<E> node = first;
		if (index == 0) {
			first = first.next;
		}else {
			Node<E> preNode = node(index - 1);
			node = preNode.next;
			preNode.next = node.next;
		}
		size--;
		return node.element;
	}

	private Node<E> node(int index) {
		rangeCheck(index);
		Node<E> node = first;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("size=").append(size).append(", [");
		Node<E> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				string.append(", ");
			}
			
			string.append(node.element);
			
			node = node.next;
		}
		string.append("]");
		
//		Node<E> node1 = first;
//		while (node1 != null) {
//			
//			
//			node1 = node1.next;
//		}
		return string.toString();
	}
}
