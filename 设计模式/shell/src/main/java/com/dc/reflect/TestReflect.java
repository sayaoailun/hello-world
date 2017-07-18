package com.dc.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflect {
	
	public static void main(String[] args) {
		try {
			ClassLoader cl = Thread.currentThread().getContextClassLoader();
			Class<?> demo = Class.forName("com.dc.reflect.Demo", false, cl);
			Object obj = demo.newInstance();
			Method m = demo.getDeclaredMethod("print");
			m.invoke(obj);
			Method test = demo.getDeclaredMethod("test");
			test.invoke(null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
