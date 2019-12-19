package 线性表;

//https://leetcode-cn.com/problems/reverse-linked-list/


// 这是一道面试题
public class _206_反转链表 {
	
	//递归的关键在于搞清楚这个方法是干啥的(难想)
	public ListNode reverseList(ListNode head) {
        //如果只有一个头结点，或者头结点为空就返回 头结点
		if (head == null || head.next == null) return head;
		//假设1->2->3->4->5->null
		ListNode newHead = reverseList(head.next); //关键：从2开始递归到新的头结点
		head.next.next = head;  //head的下一个节点2的next指向head，完成了反转
		head.next = null;  //反转最后一步,尾结点指向null
		return newHead;
	}
	//迭代的方法
	public ListNode reverseList2(ListNode head) {
		//能拿到的只有head , head是突破点
		//1.为了不断一开始就让tmp指向head.next
		//2.创建反转后的尾巴newHead，用来挪动
		//3.head.next -> newhead, newhead->head, head->head.next（tmp）并重复这个步骤
		
		ListNode newHead = null;
		while (head != null) {//防止head.next崩溃
			ListNode tmp = head.next;
			head.next = newHead;
			newHead = head;
			head = tmp;
		}
		return newHead;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

}