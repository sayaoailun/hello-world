package com.dc.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.AsyncCallback.DataCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class SDK implements Watcher {
	
	private ZooKeeper zk = null;
	private Watcher watcher = null;
	private DataCallback dataCallback = null;
	
	public SDK(String address, Watcher watcher, DataCallback dataCallback) {
		try {
			zk = new ZooKeeper(address, 3000, this);
			this.watcher = watcher;
			this.dataCallback = dataCallback;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void process(WatchedEvent event) {
		if (event != null) {
			System.out.println("####SDK#####################");
			System.out.println(event.getType());
			System.out.println(event.getState());
			System.out.println(event.getPath());
			System.out.println("####SDK#####################");
		}
	}
	
	public void register() {
		try {
			zk.create("/app/app1", null, Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getConfig() {
		zk.getData("/config/app1", watcher, dataCallback, null);
	}
	
	public static void main(String[] args) {
		ConfigWatcher cw = new ConfigWatcher("10.126.3.161:2181", "/config/app1");
		SDK sdk = new SDK("10.126.3.161:2181", cw, cw);
		sdk.register();
		sdk.getConfig();
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
