package com.yyj;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import com.mj.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")

public class BinarySearchTree<E> implements BinaryTreeInfo{ //实现这个可以打印
	
	private int size;
	private Node<E> root;
	private Comparator<E> comparator; //比较器，java提供，可以提供比较规则
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public BinarySearchTree() {
		this(null);
	}
	
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	
	//添加元素
	public void add(E element) {
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
	
	/**
	 * 递归 前序遍历
	 * */
	public void preOrderTraversal(Visitor<E> visitor) {
		preOrderTraversal(root, visitor);
	}
	
	private void preOrderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor == null) return;
//		System.out.println(node.element);
		visitor.visit(node.element);
		preOrderTraversal(node.left, visitor);
		preOrderTraversal(node.right, visitor);
	}

	/**
	 * 递归 中序遍历
	 * */
	public void inOrderTraversal(Visitor<E> visitor) {
		inOrderTraversal(root, visitor);
	}
	private void inOrderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor == null) return;
		inOrderTraversal(node.left, visitor);
//		System.out.println(node.element);
		visitor.visit(node.element);
		inOrderTraversal(node.right, visitor);
	}
	
	/**
	 * 递归 后序遍历
	 * */
	public void postOrderTraversal(Visitor<E> visitor) {
		postOrderTraversal(root, visitor);
	}
	private void postOrderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null) return;
		postOrderTraversal(node.left, visitor);
		postOrderTraversal(node.right, visitor);
		visitor.visit(node.element);
//		System.out.println(node.element);
	}
	
	/**
	 * 迭代 层序遍历
	 * */
	public void levelOrderTraversal(Visitor<E> visitor) {
		//存放元素
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			//取出并清空队列
			Node<E> node = queue.poll(); 
//			System.out.println(node.element);
			visitor.visit(node.element);
			//下一层的放入队列
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
		}
	}
	
	public void clear() {
		
	}
	
	public void remove(E element) {
		
	}
	
	public boolean contains(E element) {
		return false;
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
	
	private static class Node<E> {
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
	}
	
	//访问器，供外部访问
	public static interface Visitor<E> {
		void visit(E element);
	}

	@Override
	public Object root() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public Object left(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).left;
	}

	@Override
	public Object right(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).right;
	}

	@Override
	public Object string(Object node) {
		Node<E> myNode = (Node<E>)node;
		String parentString = "null";
		if (myNode.parent != null) {
			parentString = myNode.parent.element.toString();
		}
		return myNode.element + "_p(" + parentString + ")";
	}
	
	
}
