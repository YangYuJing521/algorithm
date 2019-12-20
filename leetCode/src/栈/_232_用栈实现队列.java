package 栈;
import java.util.Stack;;
/**
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 * */


public class _232_用栈实现队列 {
	
	Stack<Integer> inStack;  //入栈队列
	Stack<Integer> outStack; //出栈队列
	
	public _232_用栈实现队列() {
		inStack = new Stack<Integer>();
		outStack = new Stack<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }
    
    
    public int pop() {
        checkOutStack();
        return outStack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
    	checkOutStack();
        return outStack.peek();
    }
    
    //查看outStack
    /**
     * 如果outstack为空，就把instack全国pop到outStack中
     */
    private void checkOutStack() {
    	if (outStack.isEmpty()) {
        	while (!inStack.isEmpty()) outStack.push(inStack.pop()); //转移				
		}
	}
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

}
