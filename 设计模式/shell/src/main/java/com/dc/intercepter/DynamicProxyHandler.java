package com.dc.intercepter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyHandler implements InvocationHandler {
	
	private Object business; //被代理对象
	private InterceptorClass interceptor = new InterceptorClass(); //拦截器

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		interceptor.before();
		result = method.invoke(business,args);
		interceptor.after();
		return result;
	}
	
	public Object bind(Object business) {
		this.business = business;
		return Proxy.newProxyInstance(business.getClass().getClassLoader(), business.getClass().getInterfaces(), this);
	}

}
