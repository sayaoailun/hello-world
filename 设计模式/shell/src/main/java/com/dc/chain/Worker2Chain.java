package com.dc.chain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Worker2Chain {

	private int position = -1;

	private List<Worker2> workers = new ArrayList<>();

	public void addWorker(Worker2 worker) {
		workers.add(worker);
	}

	public void doNext(Object product, Worker2Chain chain) {
		position++;
		if (position < workers.size()) {
			workers.get(position).work(product, chain);
		} else {
			System.out.println("end of chain");
		}
	}
	
	public static void main(String[] args) {
		Worker2Chain chain = new Worker2Chain();
		chain.addWorker(new Worker2() {
			
			@Override
			public void work(Object product, Worker2Chain chain) {
				System.out.println("worker 1");
				System.out.println(product);
				((Map<String, Object>) product).put("k1", "v1");
				chain.doNext(product, chain);
			}
		});
		chain.addWorker(new Worker2() {
			
			@Override
			public void work(Object product, Worker2Chain chain) {
				System.out.println("worker 2");
				System.out.println(product);
				((Map<String, Object>) product).put("k2", "v2");
				chain.doNext(product, chain);
			}
		});
		Map<String, Object> map = new HashMap<>();
		map.put("k", "v");
		chain.doNext(map, chain);
		System.out.println(map);
	}

}
