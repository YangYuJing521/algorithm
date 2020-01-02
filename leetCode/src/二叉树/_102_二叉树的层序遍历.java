package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
public class _102_二叉树的层序遍历 {
	
	
	
	  public List<List<Integer>> levelOrder(TreeNode root) {
	        
		List<List<Integer>> resultA = new LinkedList<List<Integer>>();
	    if (root == null) return resultA;
	    
	    Queue<TreeNode> queue = new LinkedList<TreeNode>();
	    queue.offer(root);
	    
	    while (!queue.isEmpty()) {
			int levelDegree = queue.size();
			
		    List<Integer> ingredient = new LinkedList<Integer>();
			while (levelDegree != 0) {
				TreeNode node = queue.poll();
				ingredient.add(node.val);
				if (node.left != null) queue.offer(node.left);
				if (node.right != null) queue.offer(node.right);
				levelDegree--;
			}
	    	resultA.add(ingredient);
		}
		  return resultA;
	  }
	 
}
