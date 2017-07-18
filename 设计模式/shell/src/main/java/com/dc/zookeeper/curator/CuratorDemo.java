package com.dc.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorDemo {
	
	public static void main(String[] args) {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.newClient("10.126.3.161:2181", retryPolicy);
		client.start();
//		System.out.println(client.getNamespace());
		try {
			System.out.println(new String(client.getData().forPath("/application.properties")));
			client.setData().forPath("/application.properties", "k1=v1".getBytes());
			System.out.println(new String(client.getData().forPath("/application.properties")));
			final NodeCache nodeCache = new NodeCache(client, "/path/data");
			nodeCache.start();
			nodeCache.getListenable().addListener(new NodeCacheListener() {
				
				public void nodeChanged() throws Exception {
					if (nodeCache.getCurrentData() == null) {
						System.out.println("data delete");
					} else {
						System.out.println(new String(nodeCache.getCurrentData().getData()));
						System.out.println(nodeCache.getCurrentData().getPath());
					}
				}
			});
			final PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/path", true);
			pathChildrenCache.start();
			pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
				
				public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
					switch (event.getType()) {
					case CHILD_ADDED:
						System.out.println("add children");
						System.out.println(new String(event.getData().getData()));
						System.out.println(event.getData().getPath());
						break;
					case CHILD_UPDATED:
						System.out.println("update children");
						System.out.println(new String(event.getData().getData()));
						System.out.println(event.getData().getPath());
						break;
					case CHILD_REMOVED:
						System.out.println("remove children");
						System.out.println(new String(event.getData().getData()));
						System.out.println(event.getData().getPath());
						break;

					default:
						System.out.println(event.getType());
						break;
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				Thread.sleep(Integer.MAX_VALUE);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		client.close();
	}

}
