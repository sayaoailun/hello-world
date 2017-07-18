package com.dc.springboot;

import org.springframework.stereotype.Service;

@Service("demoDAO")
public class DemoDAO {

	public String hello() {
		return "hello";
	}

}
