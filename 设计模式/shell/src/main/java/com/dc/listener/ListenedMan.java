package com.dc.listener;

import java.util.ArrayList;
import java.util.List;

public class ListenedMan {
	
	private List<MyListener> listeners = new ArrayList<>();
	
	public void addListener(MyListener listener) {
		listeners.add(listener);
	}
	
	public void triggerAll() {
		for (MyListener listener : listeners) {
			listener.trigger();
		}
		System.out.println("trigger all already");
	}
	
	public static void main(String[] args) {
		ListenedMan man = new ListenedMan();
		man.addListener(new MyListener() {
			
			@Override
			public void trigger() {
				System.out.println("this is listener 1");
			}
		});
		man.addListener(new MyListener() {
			
			@Override
			public void trigger() {
				System.out.println("this is listener 2");
			}
		});
		man.triggerAll();
	}

}
