package com.yyj;

import java.util.Comparator;
import com.mj.printer.BinaryTrees;
import com.tree.BinarySearchTree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

	
	static void test1() {
		//系统默认比较器
		BinarySearchTree<Integer> bst1 = new BinarySearchTree<Integer>();
		//穿进去的自定义比较器
		BinarySearchTree<Integer> bst2 = new BinarySearchTree<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer e1, Integer e2) {
				return e2 - e1;    //更改比较规则
			}
		});
		
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11, 3, 12, 1
		};
		
		for (int i = 0; i < data.length; i++) {
			bst1.add(data[i]);
		}
		for (int i = 0; i < data.length; i++) {
			bst2.add(data[i]);
		}
		//第三方打印二叉树
		BinaryTrees.println(bst1);
//		BinaryTrees.println(bst2);
		
		bst1.remove(11);
		bst1.remove(2);
		BinaryTrees.println(bst1);
		
		System.out.println(bst1.contains(7));
		//前序遍历打印二叉树
//		System.out.println(bst1);

		
//		bst1.preOrderTraversal();
		//中序遍历 升序
//		bst1.inOrderTraversal(new Visitor<Integer>() {
//			public void visit(Integer element) {
//				System.out.println(element);
//			}
//		});
//		bst1.postOrderTraversal(new Visitor<Integer>() {
//		public void visit(Integer element) {
//			System.out.println(element);
//			}
//		});
		
		
//		System.out.println();
//		//中序遍历 降序
//		bst2.inOrderTraversal(new Visitor<Integer>() {
//			public void visit(Integer element) {
//				System.out.println(element);
//			}
//		});
//		
//		System.out.println("搜索树高度："+bst1.height());
//		System.out.println("搜索树高度："+bst1.treeHeight());
//		System.out.println(bst1.isCompleteTree());

	}
}
