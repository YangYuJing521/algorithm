package com.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.mj.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")

public class BinaryTree<E> implements BinaryTreeInfo{
	protected int size;
	protected Node<E> root;
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	
	/**
	 * 递归 前序遍历
	 * 树状结构展示（打印)
	 * */
	public void preOrderTraversal(Visitor<E> visitor) {
		preOrderTraversal(root, visitor);
	}
	
	private void preOrderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor == null) return;
		visitor.visit(node.element);
		preOrderTraversal(node.left, visitor);
		preOrderTraversal(node.right, visitor);
	}

	/**
	 * 递归 中序遍历
	 * 二叉搜索树升序降序处理节点
	 * */
	public void inOrderTraversal(Visitor<E> visitor) {
		inOrderTraversal(root, visitor);
	}
	private void inOrderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor == null) return;
		inOrderTraversal(node.left, visitor);
		visitor.visit(node.element);
		inOrderTraversal(node.right, visitor);
	}
	
	/**
	 * 递归 后序遍历
	 * 适用于一些先子后父的操作
	 * */
	public void postOrderTraversal(Visitor<E> visitor) {
		postOrderTraversal(root, visitor);
	}
	private void postOrderTraversal(Node<E> node, Visitor<E> visitor) {
		if (node == null) return;
		postOrderTraversal(node.left, visitor);
		postOrderTraversal(node.right, visitor);
		visitor.visit(node.element);
	}
	
	/**
	 * 迭代 层序遍历
	 * 计算二叉树的高度
	 * 判断一颗二叉树是否是完全二叉树
	 * */
	public void levelOrderTraversal(Visitor<E> visitor) {
		//存放元素
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			//取出并清空队列
			Node<E> node = queue.poll(); 
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
	
	/**
	 * 利用层序遍历，判断是否为完全二叉树
	 * */
	/*
	public boolean isCompleteTree() {
		if (root == null) return false;
		
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		Boolean isMustBeLeaf = false;
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			
			if (isMustBeLeaf && !node.isLeaf()) return false;
				
			if (node.haveTwoChildren()) {
				//最有都有就入队迭代
				queue.offer(node.left);
				queue.offer(node.right);
			}else if (node.left == null && node.right != null) {
				//左边为空， 右边不为空不是完全二叉树
				return false;
			}else {
				//(node.left!= null && node.right== null) || (node.left == null && node.right == null)
				//如左子树不为空，并且右子树为空， 或者左子树右子树都为空, 
				//那么他们的后续节点都为叶子节点才是完全二叉树
				isMustBeLeaf = true;
				if (node.left != null) queue.offer(node.left);   //bug修复， 红黑树删除补充
			}
						
		}
		return true;
	}
	*/
	
	
	/**
	 * 利用层序遍历，判断是否为完全二叉树(优化版)
	 * 思路： 涉及到层序遍历， 先把遍历写完,保证每个节点都能遍历到
	 * */
	public boolean isComppleteTree() {
		if (root == null) return false;
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		boolean isMustBeLeaf = false;
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			
			if (isMustBeLeaf && !node.isLeaf()) return false;
			
			if (node.left != null) {
				queue.offer(node.left);
			}else if (node.right != null) {//左为空，又不为空
				return false;
			}
			
			if (node.right != null) {
				queue.offer(node.right);
			}else {
				//(node.left!= null && node.right== null) || (node.left == null && node.right == null)
				isMustBeLeaf = true;
			}
		}
		
		return true;
	}
	
	
	/**
	 * 二叉搜索树高度  递归
	 * */
	public int height() {
		return height(root, 0);
	}
	
	private int height(Node<E> node, int height) {
		if (node == null) return height;
		height += 1;
		int maxheight = Math.max(height(node.left, height), height(node.right, height));
		return maxheight;
	}
	
	/**
	 * 老师写的递归
	 * */
	public int height2() {
		return height(root);
	}
	
	private int height(Node<E> node) {
		if (node == null) return 0;
		return 1 + Math.max(height(node.left), height(node.right));
	}
	
	/**
	 * 层序遍历计算高度
	 * */
	public int treeHeight() {
		if (root == null) return 0;

		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(root);
		int leverSize = 1; //第一层只有一个root
		int height = 0; 
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			leverSize --;
						
			if (node.left != null) {
				queue.offer(node.left);
			}
			if (node.right != null) {
				queue.offer(node.right);
			}
			//这个方法很关键
			if (leverSize == 0) { //一层没有了，开始下一层了
				leverSize = queue.size(); //队列里有几个元素，这一层就有多少个
				height++;
			}

		}
		return height;
	}
	
	/**
	 * 前驱节点 中序遍历的前一个节点
	 * */
	protected Node<E> predecessor(Node<E> node) {
		if (node == null) return null;
		
		// 前驱节点在左子树当中（left.right.right.right....）
		Node<E> tmp = node.left;
		if (tmp != null) {
		   while (tmp.right != null) {
			   tmp = tmp.right;
		   }
		   return tmp;
		}
		
		//left == null parent != null
		//终止条件 node在parent的右子树中
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		
		//left == null parent == null
//		if (node.parent == null) {  //没有前驱节点
//			return node.parent;
//		}
		return node.parent;
	}
	
	/**
	 * 后继节点 中序遍历的后一个节点
	 * 和前驱正好相反
	 * */
	protected Node<E> successor(Node<E> node) {
		if (node == null) return null;
		// 前驱节点在右子树当中（right.left.left.left....）
		Node<E> tmp = node.right;
		if (tmp != null) {
		   while (tmp.left != null) {
			   tmp = tmp.left;
		   }
		   return tmp;
		}
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		return node.parent;
	}


	
	protected static class Node<E> {
		public E element;
		public Node<E> left;
		public Node<E> right;
		public Node<E> parent;
		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}
		
		public boolean haveTwoChildren() {
			return left != null && right != null;
		}
		
		public boolean isLeaf() {
			return left == null && right == null;
		}
	}
	
	//访问器，供外部访问遍历结果
	public static interface Visitor<E> {
		void visit(E element);
	}
	
	/**
	 * 打印相关
	 * */
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
