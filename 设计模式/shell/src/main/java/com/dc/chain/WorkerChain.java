package com.dc.chain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkerChain {
	
	private List<Worker> workers = new ArrayList<>();
	
	public void addWorker(Worker worker) {
		workers.add(worker);
	}
	
	public void work(Object product) {
		for (Worker worker : workers) {
			worker.doSomething(product);
		}
	}
	
	public static void main(String[] args) {
		WorkerChain chain = new WorkerChain();
		chain.addWorker(new Worker() {
			
			@Override
			public void doSomething(Object product) {
				System.out.println("worker 1");
				System.out.println(product.toString());
				((Map<String, Object>) product).put("k1", "v1");
			}
		});
		
		chain.addWorker(new Worker() {
			
			@Override
			public void doSomething(Object product) {
				System.out.println("worker 2");
				System.out.println(product.toString());
				((Map<String, Object>) product).put("k2", "v2");
			}
		});
		Map<String, Object> map = new HashMap<>();
		map.put("k", "v");
		chain.work(map);
		System.out.println(map);
	}

}
