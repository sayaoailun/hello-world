package com.dc.intercepter;

public class BusinessClass implements BusinessInterface {

	public void doSomething() {
		System.out.println("doing business");
	}
	
	public static void main(String[] args) {
		DynamicProxyHandler handler = new DynamicProxyHandler();
		BusinessInterface business = new BusinessClass();
		BusinessInterface businessProxy = (BusinessInterface) handler.bind(business);
		businessProxy.doSomething();
	}

}
