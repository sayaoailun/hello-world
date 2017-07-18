package com.dc.redis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestRedis {
	
	private static Jedis jedis = new Jedis("10.126.3.161", 16379);
	
	public static void testKey() {
		jedis.set("foo", "bar");
		boolean flag = jedis.exists("foo");
		System.out.println(flag);
		String type = jedis.type("foo");
		System.out.println(type);
		Set<String> set = jedis.keys("foo");
		System.out.println(set);
		String key = jedis.randomKey();
		System.out.println(key);
		long size = jedis.dbSize();
		System.out.println(size);
	}
	
	public static void testString() {
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		System.out.println(value);
		jedis.del("foo");
		value = jedis.get("foo");
		System.out.println(value);
	}
	
	public static void testList() {
		jedis.lpush("foo_list", "b1");
		jedis.rpush("foo_list", "b2");
		long size = jedis.llen("foo_list");
		System.out.println(size);
		List<String> list = jedis.lrange("foo_list", 0, -1);
		System.out.println(list);
		for (String value : list) {
			String v = jedis.lpop("foo_list");
			System.out.println(value + "=" + v);
		}
	}
	
	public static void main(String[] args) {
//		TestRedis.testString();
//		TestRedis.testKey();
		TestRedis.testList();
	}

}
