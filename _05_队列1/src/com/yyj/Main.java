package com.yyj;

import com.mj.Queue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test1();
	}

	static void test1() {
//		Queue<Integer> queue = new Queue<>();
//		queue.enQueue(11);
//		queue.enQueue(22);
//		queue.enQueue(33);
//		queue.enQueue(44);
//		
//		while (!queue.isEmpty()) {
//			System.out.println(queue.deQueue());
//		}
		
		//  头44 33  11 22尾
		DEQueue<Integer> queue = new DEQueue<>();
		queue.enQueueRear(11);
		queue.enQueueRear(22);
		queue.enQueueFornt(33);
		queue.enQueueFornt(44);
		
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueRear());
		}
	}
		
}
