package com.yyj;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Integer> intsAryArrayList = new ArrayList<>();
		intsAryArrayList.add(11);
		intsAryArrayList.add(22);
		intsAryArrayList.add(0, 10);
		intsAryArrayList.add(33);
		intsAryArrayList.remove(intsAryArrayList.size()-1);
		System.out.println(intsAryArrayList);
	}

}
