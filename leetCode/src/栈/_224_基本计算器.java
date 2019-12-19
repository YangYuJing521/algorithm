package 栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/basic-calculator/   hard
 * */
public class _224_基本计算器 {
	public static void main(String[] args) {
		int res = calculate("(1+(4+5+2)-3)  +(6+108)");
		System.out.println(res);
	}
	
	public static int calculate(String s) {
		Stack<Integer> stac = new Stack<Integer>();
		int res = 0;
		int sigal = 1; //加减
		int len = s.length();
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
                int cur = c - '0'; //转数字
                //处理大于9的数字
                while ((i+1)<len && Character.isDigit(s.charAt(i+1))) {
					cur = cur*10 + s.charAt(++i)-'0';  //计算结果i后移
				}
                res = res + sigal*cur; //计算结果
			}
			else if (c == '(') {
				stac.push(res); //结果入栈
				res = 0;
				stac.push(sigal);  //运算符入栈 (后入先出)
				sigal = 1;
			}else if (c==')') {
				//最上面的是加减， 下面的数字
				res = stac.pop() * res + stac.pop();
			}else if (c=='-') {
				sigal = -1;
			}else if (c=='+') {
				sigal = 1;
			}else if (c == ' '){ 
				continue;
			}
		}
        return res;
    }
	
//	private boolean isNumber(char c) {
//		if (c == '') {
//			
//		}
//	}
}
