package 二叉树;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/
 * */
public class _450_删除二叉搜索树中节点 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        TreeNode node = root;
		TreeNode parent = null;
		while (node!= null) {
			int cmp = key - node.val;
			if (cmp == 0) { //相等删除
				if (node.left != null && node.right != null) {//度为2转化为度为1
					TreeNode successor = GetSuccessor(node);
					node.val = successor.val;
                    parent = node;   //更新父节点
					node = successor;  //删除successor
				}
				TreeNode childNode = node.left != null ? node.left : node.right;
				if (childNode != null) {//度为1
					if (parent == null) {//根节点
						root = childNode;
                        return root;
					}
					if (parent.left == node) {
						parent.left = childNode;
					}else {
						parent.right = childNode;
					}
                     return root;
				}else if (parent == null) { //度为0 且是根节点
                    return null;  //root = null
				}else {
					if (parent.left != null){
                        if (parent.left.val == node.val) parent.left = null;
                    }
                    if (parent.right != null){
                        if (parent.right.val == node.val) parent.right = null;
                    } 
					return root;
				}
				
			}else if (cmp > 0) {//当前节点小于key
				parent = node;
				node = node.right;
			}else {
				parent = node;
				node = node.left;
			}
		}
		
        return root;

    }


    private TreeNode GetSuccessor(TreeNode node) {
		//因为是度为2才找后继，所以node.right肯定不为空， 这里就不用考虑node.right为空的情况了
		TreeNode tmp = node.right; 
		TreeNode successor = tmp;
		while (tmp.left != null) {
			successor = tmp;
			tmp = tmp.left;
		}
		return successor;
	}
	
}
