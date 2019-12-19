package 栈;
/**
 * https://leetcode-cn.com/problems/score-of-parentheses/   难
 * */
import java.util.Stack;


public class _856_括号分数 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	 int score = scoreOfParentheses("((()(()())))"); //20
	 System.out.println(score);
	}

	//迭代加栈
	public static int scoreOfParentheses(String S) {
		if (S.length()<2) return 0;

		//用来存放结果值
		Stack<Integer> stac = new Stack<Integer>();
		
		int len = S.length();
		int res = 0;  //初始结果是
		
		for (int i = 0; i < len; i++) {
			char c = S.charAt(i);
			if (c == '(') {
				//只有左括号入栈0
				//右括号计算完成了， 到下一个左括号的时候压入结果
				stac.push(res); 
				res = 0; //清零不感染下次计算，值已入栈
			}else {//右括号
				// stac.peek() 加级别的 上次结果last res 或者 0 
				// Math.max(res*2, 1)， 通过上次入栈结果，有值是乘级别， 没值是加级别
				res = stac.peek() + Math.max(res*2, 1);
				stac.pop(); //去掉同级别的数据 或者去掉左括号的0
			}
		}
		return res;
	}
        
    
}
