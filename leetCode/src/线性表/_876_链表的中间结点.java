package 线性表;


//快慢指针
class Solution2 {
  public ListNode middleNode(ListNode head) {
    if (head == null) return head;
  	ListNode fast = head;
  	while (fast != null && fast.next != null) {
		fast = fast.next.next;
		head = head.next;  //慢指针
	}
  	return head;
  }
}

//自己想出来的，时间复杂度较高
class Solution {
    public ListNode middleNode(ListNode head) {
        if (head == null) return head;
    	int size = 1;
    	ListNode temp = head;
    	while (temp.next != null) {
			temp = temp.next;
			size ++;
		}
    	int middle = size/2;
    	ListNode mideNode = head;
    	for (int i = 0; i < middle; i++) {
			mideNode = mideNode.next;
		}
    	
    	return mideNode;
    }
}