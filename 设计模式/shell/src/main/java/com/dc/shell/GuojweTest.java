package com.dc.shell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class GuojweTest {

	public static void main(String[] args) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("k1", "v1");
		String jsonStr = JSON.toJSONString(map1);
		JSONObject jsonObj = JSON.parseObject(jsonStr);
		Map<String, Object> all = new HashMap<>();
		all.put("map1", map1);
		all.put("map2", map1);
		all.put("new", jsonObj);
		System.out.println(JSON.toJSONString(all));
	}

}
