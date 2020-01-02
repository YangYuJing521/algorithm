package com.yyj;


public class bidirectionalCircleLinkList<E> extends AbstractList<E> {

	//头结点
	private Node<E> first;
	//尾结点
	private Node<E> last;
	//当前指向节点
	private Node<E> currentNode;
	//节点
	private static class Node<E> {
		E element;
		Node<E> pre;
		Node<E> next;
		public Node(Node<E> pre, E element, Node<E> next) {
			this.pre = pre;
			this.element = element;
			this.next = next;
		}
	}
	
	//约瑟夫问题增加三个方法, 有这三个方法才能发挥循环链表的最大威力
	//将current指向头结点
	public void reset() {
		currentNode = first;
	}
	
	//current 移动一步
	public E next() {
		if (currentNode == null) return null;
		currentNode = currentNode.next;
		return currentNode.element;
	}
	
	//移除current对应节点，并且current后移一步
	public E removeCurrent() {
		if (currentNode == null) return null;
		
		Node<E> next = currentNode.next; 
		E element = remove(currentNode);
		if (size == 0) {//如果只剩一个的时候删除完了current指向next，这时候next其实就是current，会导致current删不掉
			currentNode = null;
		} else {
			currentNode = next;
		}
		return element;
	}
	
	@Override
	public void clear() {
		first = null;
		last = null;
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
		//重点 ， 往最后面添加 
		//index = 0, size = 0
		if (index == size) {   //往最后面添加
			Node<E> oldlast = last;
			last = new Node<>(oldlast, element, first); //指前指头
			if (oldlast == null) { //添加第一个元素
				first = last;
				first.pre = first;
				first.next = first;
			}else {
				oldlast.next = last;
				first.pre = last;
			}
		}else { //其它位置添加，需要注意头结点
			Node<E> nexNode = node(index);
			Node<E> preNode = nexNode.pre;
			Node<E> current = new Node<>(preNode, element, nexNode);
			nexNode.pre = current;
			preNode.next = current;
			if (nexNode == first) {//index==0  (如果不是循环链表的话，pre是空的)
				first = current;
			}
		}
		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		return remove(node(index));
	}
	
	private E remove(Node<E> node) {
		Node<E> current = first;
		if (size==1) { //只有一个元素
			first = null;
			last = null;
		}else {
			current = node;
			Node<E> prev = current.pre;
			Node<E> next = current.next;
			next.pre = prev;
			prev.next = next;  
			if (current == first) {//第一个 Index = 0
				first = next;	//prev是last
			}
			if (current == last) {//最后一个 index = size-1
				last = prev;	//next是first
			}
		}
		size--;
		return current.element;
	}

	private Node<E> node(int index) {
		rangeCheck(index);
		Node<E> node;
		if (index < (size>>1)) {
			node = first;
			for (int i = 0; i < index; i++) {
				node = node.next;
			}
		}else {
			node = last;
			for (int i = size-1; i > index; i--) {
				node = node.pre;
			}
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
