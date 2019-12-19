package 栈;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 */

public class _20_有效的括号 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isValid("{([])}"));
	}
	
	public static boolean isValid(String s) {
		Stack<Character> stac = new Stack<Character>();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c == '{' || c == '(' || c == '[') {
				stac.push(c);
			}else {
				if (stac.isEmpty()) return false; //左边空了，右边还有
				char left = stac.pop();
				if (left == '(' && c != ')') return false;
				if (left == '[' && c != ']') return false;
				if (left == '{' && c != '}') return false;					
			}
		}
        return stac.isEmpty(); //如果空了说明完全匹配了,没空就是有右括号
    }

}
