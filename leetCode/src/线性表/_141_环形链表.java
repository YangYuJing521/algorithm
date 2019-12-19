package 线性表;

//https://leetcode-cn.com/problems/linked-list-cycle/
public class _141_环形链表 {

	
	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) return false;
		//一个在前一个在后
		ListNode fast = head.next;
		ListNode slow = head;
		while (fast != null && fast.next != null) {  //fast 或者 fast.next为空说明fast快到头了，
			//两人相差一步，如果有环早晚相遇
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) return true;
		}
        return false;
    }
}
