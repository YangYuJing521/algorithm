package 线性表;

//https://leetcode-cn.com/problems/delete-node-in-a-linked-list/

public class _237_删除链表中的节点 {
	 
	class Solution {
	    public void deleteNode(ListNode node) {
	        node.val = node.next.val; //拷贝下一个node的值
	        node.next = node.next.next; //删除下一个node
	    }
	}
	
	public static void main(String[] args) {

		
	}

}
