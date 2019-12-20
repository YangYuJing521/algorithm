package com.yyj;
import com.yyj.circleQueue.DequeCircle;
import com.yyj.circleQueue.QueueCircle;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		test1();
//		test2();
		test3();
	}
	
	static void test3() {
		DequeCircle<Integer> queue = new DequeCircle<>();
		// 头5 4 3 2 1  100 101 102 103 104 105 106 8 7 6 尾
		
		// 头 8 7 6  5 4 3 2 1  100 101 102 103 104 105 106 107 108 109 null null 10 9 尾
		for (int i = 0; i < 10; i++) {
			queue.enQueueFront(i + 1);
			queue.enQueueRear(i + 100);
		}
		
		// 头 null 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null null 尾
		for (int i = 0; i < 3; i++) {
			queue.deQueueFront();
			queue.deQueueRear();
		}
		
		// 头 11 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null 12 尾
		queue.enQueueFront(11);
		queue.enQueueFront(12);
		System.out.println(queue);
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueFront());
		}
	}
	
	static void test2() {
		QueueCircle<Integer> queue = new QueueCircle<Integer>();
		// 0 1 2 3 4 5 6 7 8 9
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
		}
		// null null null null null 5 6 7 8 9
		for (int i = 0; i < 5; i++) {
			queue.deQueue();
		}
		// 15 16 17 18 19 5 6 7 8 9
		for (int i = 15; i < 20; i++) {
			queue.enQueue(i);
		}
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
		System.out.println(queue);
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
		
		//  头 44 33  11 22 尾
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
