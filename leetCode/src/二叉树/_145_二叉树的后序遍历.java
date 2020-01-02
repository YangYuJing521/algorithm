package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.BinaryOperator;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

/**
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * */
public class _145_二叉树的后序遍历 {
	
	/*  模板
	while( 栈非空 || p 非空)
	{
	if( p 非空)
	{

	}
	else
	{

	}
	}
	*/

	public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root; 
        TreeNode pre = null;  //记录上一个保存的节点， right == pre 说明 right 已经保存了， 可以保存top了
        while (!stack.isEmpty() || cur!= null) {
			if (cur != null) {
				stack.push(cur);
				cur = cur.left;  //二叉搜索树，先序后弦都是向左走到头
			}else {
				cur = stack.peek(); //先看一眼，有right先去指向right 以此来入栈right
                if(cur.right == null || cur.right == pre)  //前边的适用于叶子， 后面的适用于保存了右边的top
                {
                    list.add(cur.val);
                    pre = cur;    //记录下上一个保存的右节点，来判断是否记录top值
                    stack.pop();  //保存数据后出栈
                    cur = null;   //指针清空，以此循环到下个栈顶
                }
                else
                    cur = cur.right;
			}
		}
        
        return list;
    }
	
	public List<Integer> postorderTraversal(TreeNode root) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		if (root == null) return list;
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			
			TreeNode node = stack.pop();
			
			//先序 记录 上左右
			//后续 相反 这里插入到first 记录 l r t
			//出栈元素插入list头部
			list.addFirst(node.val);
			
			if (node.left != null) {
				stack.push(node.left);
			}
			if (node.right != null) {//栈顶先取右边，但是postorder记录数据是往第一个放，所以right在后
				stack.push(node.right);
			}
		}
		
		return list;

	}
	
	
	//官方解题
	/*
	  public List<Integer> postorderTraversal(TreeNode root) {
		    LinkedList<TreeNode> stack = new LinkedList<>();
		    LinkedList<Integer> output = new LinkedList<>();
		    if (root == null) {
		      return output;
		    }

		    stack.add(root);
		    while (!stack.isEmpty()) {
		      TreeNode node = stack.pollLast();
		      output.addFirst(node.val);
		      if (node.left != null) {
		        stack.add(node.left);
		      }
		      if (node.right != null) {
		        stack.add(node.right);
		      }
		    }
		    return output;
		  }
	  
	  	作者：LeetCode
		链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/er-cha-shu-de-hou-xu-bian-li-by-leetcode/
		来源：力扣（LeetCode）
		著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
		*/

}
	


	

	
	
