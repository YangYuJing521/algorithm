package 线性表;

//https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
public class _83_删除排序链表中的重复元素 {
	
}

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
    	if (head == null || head.next == null) {
			return head;
		}
    
    	ListNode temp = head;
    	while (temp.next!=null) {
			if (temp.val == temp.next.val) {
				temp.next = temp.next.next;
			}else {
				temp = temp.next;
			}
		}
    	return head;
    }
}