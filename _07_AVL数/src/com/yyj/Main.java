package com.yyj;
import com.mj.printer.BinaryTrees;
import com.tree.AVLTree;
import com.tree.BinarySearchTree;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

	static void test1() {
		
		Integer data[] = new Integer[] {
				67, 52, 92, 96, 53, 95, 13, 63, 34, 82, 76, 54, 9, 68, 39
		};
		AVLTree<Integer> avl = new AVLTree<>();
		for (int i = 0; i < data.length; i++) {
			avl.add(data[i]);
		}
		
		BinaryTrees.println(avl);
	}
}
