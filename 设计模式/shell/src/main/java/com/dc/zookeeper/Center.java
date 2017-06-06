package com.dc.zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class Center implements Watcher {
	
	private ZooKeeper zk = null;
	
	public Center(String address) {
		try {
			zk = new ZooKeeper(address, 3000, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getApp() {
		try {
			Stat stat = zk.exists("/app", false);
			if (stat != null) {
				if (stat.getNumChildren() > 0) {
					List<String> children = zk.getChildren("/app", false);
					System.out.println(children);
				}
			} else {
				zk.create("/app", null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setData() {
		try {
			Stat stat = zk.exists("/config", false);
			if (stat == null) {
				zk.create("/config", null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}
			stat = zk.exists("/config/app1", false);
			if (stat == null) {
				zk.create("/config/app1", "k=v".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			} else {
				zk.setData("/config/app1", "k=v".getBytes(), -1);
			}
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Center center = new Center("10.126.3.161:2181");
		center.setData();
		center.getApp();
	}

	@Override
	public void process(WatchedEvent event) {
		if (event != null) {
			System.out.println("#########################");
			System.out.println(event.getType());
			System.out.println(event.getState());
			System.out.println(event.getPath());
			System.out.println("#########################");
		}
	}

}
