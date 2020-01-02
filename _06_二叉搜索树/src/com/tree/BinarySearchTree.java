package com.tree;

import java.util.Comparator;

@SuppressWarnings("unchecked")

public class BinarySearchTree<E> extends BinaryTree<E>{ //实现这个可以打印
	private Comparator<E> comparator; //比较器，java提供，可以提供比较规则

	public BinarySearchTree() {
		this(null);
	}
	
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	
	//添加元素
	public void add(E element) {
		elementNotNulCheck(element);
		
		if (root == null) { //root 节点
			root = new Node<>(element, null);
			size++;
			return;
		}
		
		//找到目标节点，并保存新节点比目标节点的大小
		Node<E> tmp = root;
		Node<E> parent = root; //保存节点
		int cmp = 0;  //比较结果
		while (tmp != null) {
			parent = tmp;
			cmp = compare(element, tmp.element);
			if (cmp>0) {//肯定是往右边加
				tmp = tmp.right;
			}else if (cmp<0) {
				tmp = tmp.left;
			}else {
				//相等，就覆盖
				tmp.element = element;
				return;
			}
		}
		
		//目标位置添加
		Node<E> newNode = new Node<>(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		}else {
			parent.left = newNode;
		}
		size++;
	}
		
	public void remove(E element) {
		remove(node(element));
	}
	
	
	private void remove(Node<E> node) {
		
		//度为2的节点
		if (node.haveTwoChildren()) {
			//获取后继节点
			Node<E> successor = successor(node);
			//将后继节点的值复制到要删除的节点上
			node.element = successor.element;
			//将后继节点删掉(转为度为0或者1的情况)
			node = successor;
		}
		
		Node<E> childNode = node.left != null ? node.left : node.right;
		if (childNode != null) {//度为1的节点
			childNode.parent = node.parent;  //更新父节点
			if (node.parent == null) { //根节点
				root = childNode;
			}else if (node == node.parent.left) {
				node.parent.left = childNode;
			}else {
				node.parent.right = childNode;
			}
		}else if (node.parent == null) { //叶子节点为跟节点
			root = null;
		}else {//叶子节点
			if (node == node.parent.left) {
				node.parent.left = null;
			}else {
				node.parent.right = null;
			}
		}
		
	}
	
		
	
	public boolean contains(E element) {
		return node(element) != null;
	}
	
	private Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) {
				return node;
			}else if (cmp > 0) {
				node = node.right;
			}else {
				node = node.left;
			}			
		}
		return null;
	}

	
	
	
	//如果 e1=e2返回0 e1>e2返回>0 e1<e2返回小于0
	private int compare(E e1, E e2) {
		//如果有比较器调用比较器的比较方法
		if (comparator != null) {
			return comparator.compare(e1, e2);
		}		
		//java.lang 系统的类型都有默认实现
		return ((Comparable<E>)e1).compareTo(e2);
	}
	
	private void elementNotNulCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element should not be null");
		}
	}
	
	
//	@Override
//	public String toString() {
//		StringBuffer sb = new StringBuffer();
//		toString(root, sb, "");
//		return sb.toString();
//	}
//	
//	/**
//	 * 利用前序遍历打印二叉树
//	 * */
//	private void toString(Node<E> node, StringBuffer sb, String prefix) {
//		if (node == null) return;
//		sb.append(prefix).append(node.element).append("\n");
//		toString(node.left, sb, prefix+"L--");
//		toString(node.right, sb, prefix+"R--");
//	}
	
	
}
