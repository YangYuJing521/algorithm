package com.yyj;


public class Main {

	public static void main(String[] args) {

		stack<Integer> stack = new stack<Integer>();
		for (int i = 1; i <= 6; i++) {
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

}
