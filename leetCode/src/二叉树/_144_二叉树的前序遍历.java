package 二叉树;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.w3c.dom.Node;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/comments/
 * */
public class _144_二叉树的前序遍历 {
	
	
	// 方法一 递归（过于简单）
	LinkedList<Integer> list = new LinkedList<Integer>();
	public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return list;
        list.add(root.val);
        if (root.left != null) preorderTraversal(root.left);
        if (root.right != null) preorderTraversal(root.right);
        return list;
	}
	
	
	/*  官方迭代解法2 莫里斯 不懂
		  public List<Integer> preorderTraversal(TreeNode root) {
		    LinkedList<Integer> output = new LinkedList<>();

		    TreeNode node = root;
		    while (node != null) {
		      if (node.left == null) {
		        output.add(node.val);
		        node = node.right;
		      }
		      else {
		        TreeNode predecessor = node.left;
		        while ((predecessor.right != null) && (predecessor.right != node)) {
		          predecessor = predecessor.right;
		        }

		        if (predecessor.right == null) {
		          output.add(node.val);
		          predecessor.right = node;
		          node = node.left;
		        }
		        else{
		          predecessor.right = null;
		          node = node.right;
		        }
		      }
		    }
		    return output;
		  }
		


		作者：LeetCode
		链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/er-cha-shu-de-qian-xu-bian-li-by-leetcode/
		来源：力扣（LeetCode）
		著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
	
		*/
	
	// 官方 迭代
	public List<Integer> preorderTraversal(TreeNode root) {
		LinkedList<Integer> list = new LinkedList<Integer>();
        if (root == null) return list;
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //压入栈顶
        stack.push(root);
        while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			//出栈元素插入list尾部
			list.add(node.val);
			//栈底
			if (node.right != null) stack.push(node.right);
			//栈顶
        	if (node.left != null) stack.push(node.left);
		}
        return list;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
