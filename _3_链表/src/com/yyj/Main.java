package com.yyj;

public class Main {

	public static void main(String[] args) {

//		SingleLinkList<Integer> list =  new SingleLinkList<Integer>();
//		list.add(10);
//		list.remove(0);
//		list.add(11);
//		list.add(0, 1);
//		System.out.println(list);
//		System.out.println(list.isEmpty());
//		list.clear();
//		list.add(10);
//		list.add(null);
//		System.out.println(list.indexOf(null));
		
//		ArrayListSuo<Integer> arrayList = new ArrayListSuo<Integer>();
//		for (int i = 0; i < 50; i++) {
//			arrayList.add(i);
//		}
//		for (int i = 0; i < 50; i++) {
//			arrayList.remove(0);
//		}
		
		//size=2, [1, 11]
		//false
		//1

//		bidirectionalLinkList<Integer> list =  new bidirectionalLinkList<Integer>();
//		list.add(10);
//		list.remove(0);
//		list.add(11);
//		list.add(0, 1);
//		System.out.println(list);
//		System.out.println(list.isEmpty());
//		list.clear();
//		list.add(10);
//		list.add(null);
//		System.out.println(list.indexOf(null)); 
		
//		SingleCircleLinkList<Integer> list =  new SingleCircleLinkList<Integer>();
//		list.add(10);
//		list.remove(0);
//		list.add(11);
//		list.add(0, 1);
//		System.out.println(list);
//		System.out.println(list.isEmpty());
//		list.clear();
//		list.add(10);
//		list.add(null);
//		System.out.println(list.indexOf(null)); 
		
//		bidirectionalCircleLinkList<Integer> list =  new bidirectionalCircleLinkList<Integer>();
//		list.add(10);
//		list.remove(0);
//		list.add(11);
//		list.add(0, 1);
//		System.out.println(list);
//		System.out.println(list.isEmpty());
//		list.clear();
//		list.add(10);
//		list.add(null);
//		System.out.println(list.indexOf(null)); 
		yeSeFu();
	}

	static void yeSeFu() {
		bidirectionalCircleLinkList<Integer> list = new bidirectionalCircleLinkList<Integer>();
		for (int i = 1; i <= 8; i++) {
			list.add(i);
		}
		//指向1
		list.reset();
		
		while (!list.isEmpty()) {
			list.next();
			list.next();
			System.out.println(list.removeCurrent());
		}
	}
	

}
