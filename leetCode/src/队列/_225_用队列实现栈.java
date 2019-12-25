package 队列;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * */
public class _225_用队列实现栈 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int x = 10;
		  MyStack obj = new MyStack();
		  obj.push(x);
		  int param_2 = obj.pop();
		  int param_3 = obj.top();
		  boolean param_4 = obj.empty();
		  
	}

}

class MyStack {
	
	private Queue<Integer> offerQueue;
	private Queue<Integer> tempQueue;
	private int size;

    /** Initialize your data structure here. */
    public MyStack() {
    	this.offerQueue = new LinkedList<Integer>();
    	this.tempQueue = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
    	offerQueue.offer(x);
        size++;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
    	int element = 0;

    	for (int i = 0; i < size; i++) {
				int num = offerQueue.poll();
				if (i != size-1) {
					tempQueue.offer(num);
				}else {
					element = num;
				}
		}
    	offerQueue = tempQueue;
    	tempQueue = new LinkedList<Integer>();
        size--;
    	return element;
    }
    
    /** Get the top element. */
    public int top() {
    	int element = 0;
    	for (int i = 0; i < size; i++) {
				int num = offerQueue.poll();
				tempQueue.offer(num);
				if (i == size-1) {
					element = num;
				}				
		}
    	offerQueue = tempQueue;
    	tempQueue = new LinkedList<Integer>();
    	return element;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return offerQueue.isEmpty();
    }
}