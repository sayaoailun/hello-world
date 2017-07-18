package com.dc.shell;

public class MyImpl implements IMyInterface {

	public void test() {
		System.out.println(test);
	}
	
	public static void main(String[] args) {
		IMyInterface mif = new MyImpl();
		System.out.println("begin");
		mif.test();
		System.out.println("end");
	}

}
