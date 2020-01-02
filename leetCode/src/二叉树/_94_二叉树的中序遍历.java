package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * */
public class _94_二叉树的中序遍历 {
	
	
	
	//这俩一个意思
	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        if (root == null) return list;  //root为空返回
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) { //判断条件很关键， stack为空也有可能cur有值
        	//操作stack的进退
			while (cur != null) {
				stack.push(cur); //循环到最左侧, node入栈
				cur = cur.left;  
			}
			cur = stack.pop();
			list.add(cur.val);
			cur = cur.right;  //关键，如果没有right会继续出栈node，记录数据
			
		}
        return list;
    }

	public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        if (root == null) return list;  //root为空返回
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) { //判断条件很关键， stack为空也有可能cur有值
        	//操作stack的进退
			if (cur != null) {
				stack.push(cur); //循环到最左侧, node入栈
				cur = cur.left;  
			}else {
				//最左侧为空了，出栈并保存值到list尾部
				//如果right不为空了说明left top都入list了
				cur = stack.pop();
				list.add(cur.val);
				cur = cur.right;  //关键，如果没有right会继续出栈node，记录数据
			}
		}
        return list;
    }
}
