package com.dc.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.AsyncCallback.DataCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ConfigWatcher implements Watcher, DataCallback {
	
	private ZooKeeper zk = null;
	private String path = null;
	
	public ConfigWatcher(String address, String path) {
		try {
			zk = new ZooKeeper(address, 3000, this);
			this.path = path;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void process(WatchedEvent event) {
		if (event != null) {
			System.out.println("####Config#####################");
			System.out.println(event.getType());
			System.out.println(event.getState());
			System.out.println(event.getPath());
			System.out.println("####Config#####################");
			if (EventType.NodeDataChanged.equals(event.getType())) {
				System.out.println("###NodeDataChanged###");
				zk.getData(path, this, this, null);
			}
		}
	}

	@Override
	public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
		System.out.println("######");
		System.out.println(rc);
		System.out.println(path);
		System.out.println(new String(data));
		System.out.println(stat.getVersion());
		System.out.println("######");
	}

}
