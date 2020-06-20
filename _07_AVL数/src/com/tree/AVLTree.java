package com.tree;

import java.util.Comparator;

public class AVLTree<E> extends BinarySearchTree<E>{

	public AVLTree() {
		this(null);
	}
	
	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * 重写binaryTree方法， 提供AVLNode
	 * */
	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode<E>(element, parent);
	}
	
	@Override
	protected void afterAdd(Node<E> node) {
		
		//到这里node是node的parent、grand。。。。
		//即 添加节点他爹开始的一串上一直到根节点的父节点null
		while ((node = node.parent) != null) {
			
			//如果是平衡的， 更新node 父亲往上一条线的高度
			if (isBalance(node)) {
				updateHeiht(node);
			}else {//如果不是平衡的就恢复平衡，并且更新高度
				reBalance(node);
				// 经过调整，整棵树恢复平衡，退出循环!!！
				break;
			}
			
		}

	}
	
	@Override
	protected void afterRemove(Node<E> node) {

		while ((node = node.parent)!= null) {
			if (isBalance(node)) {
				updateHeiht(node);  //和添加一样如果平衡就更新高度
			}else {
				//如果不平衡就恢复平衡， 但是恢复平衡可能会使父节点及祖父节点失衡， 所以不执行break  logn
				reBalance(node);
			}
		}
	}
	
	/**
	 * @param grand 是传进来的不平衡节点, grandFa
	 * 重点， 恢复平衡
	 * */
	private void reBalance(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>)grand).tailerChild();
		Node<E> node = ((AVLNode<E>)parent).tailerChild();
		
		if (parent.isLeftChild()) { //L
			if (node.isLeaf()) { //LL
//				rotateRight(grand);
				rotate2(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
			}else { //LR
//				rotateLeft(parent);
//				rotateRight(grand);
				rotate2(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
			}
		}else { //R
			if (node.isLeftChild()) { //RL
				rotateRight(parent);
				rotateLeft(grand);
//				rotate2(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
			}else { //RR
				rotateLeft(grand);
//				rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
			}
		}
	}
	
	/**
	 * 坐旋转
	 * 注意更新 parent
	 * */
	private void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
		grand.right = child;
		parent.left = grand;
		
		//旋转后调整
		afterRotate(grand, parent, child);
	}
	
	/**
	 * 右旋转
	 * */
	private void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		grand.left = child;
		parent.right = grand;
		
		afterRotate(grand, parent, child);
	}
	
	private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
				//根维护
				if (grand.isLeftChild()) {
					grand.parent.left = parent;
				}else if (grand.isRightChild()) {
					grand.parent.right = parent;
				}else {
					root = parent;
				}
				
				//parent维护
				if (child != null) {
					child.parent = grand;
				}
				parent.parent = grand.parent;
				grand.parent= parent;
				
				//更新高度（先更新矮的再更新高的）
				updateHeiht(grand);
				updateHeiht(parent);		
	}
	
	private void rotate2(Node<E> n,Node<E> a,Node<E> b,Node<E> c,Node<E> d,Node<E> e,Node<E> f,Node<E> g) {
		d.parent = n.parent;
		if (n.parent == null) {
			root = d;
		}else if (n.isLeftChild()) {
			n.parent.left = d;
		}else {
			n.parent.right = d;
		}
		
		b.left = a;
		if (a != null) a.parent = b;
		b.right = c;
		if (c != null) c.parent = b;
		updateHeiht(b);
		
		f.left = e;
		if (e != null) e.parent = f;
		f.right = g;
		if (g != null) g.parent = f;
		updateHeiht(f);
		
		d.left = b;
		b.parent = d;
		d.right = f;
		f.parent = d;
		updateHeiht(d);
	}
	
	
	//判断是否平衡
	private boolean isBalance(Node<E> node) {
		//平衡因子绝对值小于等于1  -1,0,1
		return Math.abs(((AVLNode<E>)node).balenceFactor()) <= 1;
	}
	
	//更新高度
	private void updateHeiht(Node<E> node) {
		((AVLNode<E>)node).updateHeight();
	}
	
	private static class AVLNode<E> extends Node<E> {

		int height = 1; 
		
		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}
		
		// -1， 0， 1 视为平衡
		public int balenceFactor() {
			int leftH = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightH = right == null ? 0 : ((AVLNode<E>)right).height;
			return leftH - rightH;
		}
		
		
		public void updateHeight() {
			int leftH = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightH = right == null ? 0 : ((AVLNode<E>)right).height;
			//自己的高度等于左右字数最高的加一
			height = 1 + Math.max(leftH, rightH);
		}
		
		//返回左右字数中较高的一个，如果一样高就返回自己方向的
		public Node<E> tailerChild() {
			int leftH = left == null ? 0 : ((AVLNode<E>)left).height;
			int rightH = right == null ? 0 : ((AVLNode<E>)right).height;
			if (leftH > rightH) return left;
			if (leftH < rightH) return right;
			//左右高度一样,返回parent(这里是自己)方向的，
			return isLeftChild()? left : right;
		}
	}
	
	
	
}
