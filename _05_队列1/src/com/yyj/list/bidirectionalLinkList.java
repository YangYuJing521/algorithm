package com.yyj.list;

public class bidirectionalLinkList<E> extends AbstractList<E> {

	//头结点
	private Node<E> first;
	//尾结点
	private Node<E> last;
	
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
			last = new Node<>(oldlast, element, null); //指前指后
			if (oldlast == null) { //添加第一个元素
				first = last;
			}else {
				oldlast.next = last;
			}
		}else {
			Node<E> nexNode = node(index);
//			Node<E> preNode = node(index-1); //这么写会越界
			Node<E> preNode = nexNode.pre;
			Node<E> current = new Node<>(preNode, element, nexNode);
			nexNode.pre = current;
			if (preNode == null) { //第一个 index=0
				first = current;
			}else {
				preNode.next = current;
			}
		}
		size++;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		
		Node<E> current = node(index);
//		Node<E> prev = node(index-1);  这么弄会崩溃的
//		Node<E> next = node(index+1);
		Node<E> prev = current.pre;
		Node<E> next = current.next;
		
		if (prev == null) { //index == 0  从前往后纸考虑左端
			first = next;
		}else {
			prev.next = next;
		}
		
		if (next == null) { //index = size-1 从后往前指 考虑右端
			last = prev;
		}else {
			next.pre = prev;
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
