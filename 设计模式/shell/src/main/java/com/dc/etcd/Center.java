package com.dc.etcd;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeoutException;

import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;
import mousio.etcd4j.responses.EtcdAuthenticationException;
import mousio.etcd4j.responses.EtcdException;
import mousio.etcd4j.responses.EtcdKeysResponse;
import mousio.etcd4j.responses.EtcdKeysResponse.EtcdNode;

public class Center {
	
	public static void main(String[] args) {
		EtcdClient etcd = new EtcdClient(URI.create("http://10.126.3.161:2379"), URI.create("http://10.126.3.161:12379"), URI.create("http://10.126.3.161:22379"));
		String name = "guojwe";
		String version = "v1";
		String key = name + "." + version;
		String dir = name + "/" + version + "/";
		try {
			EtcdResponsePromise promise = etcd.getDir(dir).send();
			try {
				EtcdKeysResponse response = (EtcdKeysResponse) promise.get();
				for (EtcdNode node : response.node.nodes) {
					promise = etcd.delete(node.key).send();
					promise.get();
				}
				promise = etcd.deleteDir(dir).send();
				promise.get();
			} catch (Exception e) {
				e.printStackTrace();
			}
			promise = etcd.putDir(dir).send();
			promise.get();
			promise = etcd.put(dir + "system.executor-memory", "1g").send();
			promise.get();
			promise = etcd.put(dir + "system.executor-cores", "4").send();
			promise.get();
			promise = etcd.put(dir + "test.key", "test.value").send();
			promise.get();
			promise = etcd.put(key, "true").send();
			promise.get();
			System.out.println("center updated");
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EtcdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EtcdAuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
