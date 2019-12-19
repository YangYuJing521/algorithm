package 线性表;


//https://leetcode-cn.com/problems/remove-linked-list-elements/

//没有虚拟头结点
class Solution {    
	public ListNode removeElements(ListNode head, int val) {
    	while (head!=null && head.val == val) head = head.next; //如果头部有值并且和要删的元素一致，则头结点后移
    	ListNode prev = head; //中间变量
    	if (prev != null) { //安全判断
    		
			while (prev.next != null) {//循环的终止
				if (prev.next.val == val) 
					prev.next = prev.next.next;  //删除
				else 
					prev = prev.next;   //移动指针
			}
			
		}
    	return head;
    }
}

//设置虚拟头结点，使代码逻辑统一
class Solution2 {    
	public ListNode removeElements(ListNode head, int val) {
		
	ListNode virtualNode = new ListNode(-1) ; //虚拟头结点
	virtualNode.next = head; //连线
	ListNode prev = virtualNode; //中间变量
	while (prev.next != null) {
		if (prev.next.val == val) { //删除元素
			prev.next = prev.next.next;
		}else { 
			prev = prev.next;  //移动变量指针
		}
	}
  	return virtualNode.next;
  }
}

class Solution3 {    
	public ListNode removeElements(ListNode head, int val) {
	if (head == null) return head;
	removeElements(head.next, val);
	return head.val == val? head.next : head;
  }
}

