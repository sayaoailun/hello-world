package com.dc.etcd;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.promises.EtcdResponsePromise;
import mousio.etcd4j.responses.EtcdAuthenticationException;
import mousio.etcd4j.responses.EtcdException;
import mousio.etcd4j.responses.EtcdKeysResponse;
import mousio.etcd4j.responses.EtcdKeysResponse.EtcdNode;

public class SDK {
	
	private URI[] uri;
	private String dir;
	private String key;
	
	public SDK(URI[] uri, String dir, String key) {
		this.uri = uri;
		this.dir = dir;
		this.key = key;
	}
	
	public void watch() {
		EtcdClient etcd = new EtcdClient(uri);
		while (true) {
			try {
				EtcdResponsePromise promise = etcd.get(key).waitForChange().send();
				promise.get();
				promise = etcd.getDir(dir).send();
				EtcdKeysResponse response = (EtcdKeysResponse) promise.get();
				for (EtcdNode node : response.node.nodes) {
					System.out.println(node.key.split("/")[3] + "=" + node.value);
				}
				System.out.println("**********************");
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
	
	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				URI[] uri = {URI.create("http://10.126.3.161:2379"),URI.create("http://10.126.3.161:12379"),URI.create("http://10.126.3.161:22379")};
		SDK sdk = new SDK(uri, "guojwe/v1/", "guojwe.v1");
		sdk.watch();
			}
		});
		thread.start();
		System.out.println("sdk watching");
	}

}
