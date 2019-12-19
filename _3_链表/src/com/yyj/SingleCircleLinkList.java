package com.yyj;


//单向循环比起单向，需注意在index = 0 处的添加删除问题。 此时改变last的next
//添加时如果是第一个要指向自己， 删除时只有first要清空
//用循环链表解决约瑟夫问题
public class SingleCircleLinkList<E> extends AbstractList<E> {

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
		//添加删除是重点，添加到index=0的位置时，last的指向也要改变，还要考虑添加第一个元素时是自己指向自己
		if (index == 0) {
			Node<E> newFirst = new Node<>(element, first);
			Node<E> lastNode = (size == 0) ? newFirst : node(size-1);
			first = newFirst;
			lastNode.next = first;
		}else {
			Node<E> preNode = node(index-1);
			preNode.next = new Node<>(element, preNode.next);
		}
		
		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		//删除的时候第一个位置很重要，尾结点的next也要相应更改， 同时如果只有一个元素的话就把first指向空
		Node<E> node = first;
		if (index == 0) {
			if (size == 1) {
				first = null;
			}else {
				Node<E> lasNode = node(size-1);
				first = first.next;
				lasNode.next = first;
			}
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
